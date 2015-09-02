package com.bap.bos.net.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.dao.TransCardDao;
import com.bap.bos.domain.TransCard;
import com.bap.bos.net.PacketOperate;
import com.bap.bos.net.util.Protocol;
import com.bap.bos.service.ShiftVerifyService;
import com.bap.bos.util.Config;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BosClientThread implements Callable<String> {
	
	Logger logger = LoggerFactory.getLogger(UnloadingOilThread.class);
	
	/*数据打包-解析类*/
	@Resource PacketOperate packetOperate;
	/*未结交易查询类*/
	@Resource TransCardDao transCardDao;
	
	@Resource ShiftVerifyService shiftVerifyService;
	
	/*Socket接口*/
	//Socket s=null;
	/*输入输出字节流*/
	private InputStream inReader=null;
	private OutputStream OutWriter=null;
	/*发包流水号*/
	private long PNo=0;
	private String date;
	
	/*站号*/
	private String stationNo;
	
	@Resource
	@Qualifier("card")
	private Config card;
	
	public BosClientThread(){
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String call() throws Exception {
		Socket s=null;
		try {
			/*初始化Scoket*/
			String ip=card.getIp();
			logger.info("=================获取远程Card基本信息 card_IP: "+ip+" port:65009================");
			s=new Socket(ip,65009);
			/*设置超时 3分钟未读到数据*/
			s.setSoTimeout(60*1000);
			/*初始化Scoket的输入-输出流*/
			
			
			inReader=s.getInputStream();
			OutWriter=s.getOutputStream();
			/*申请同步
			Protocol protocol=packetOperate.originalProtocol((byte)0xD1, PNo, null,date);
			byte[] Send_D1=packetOperate.createPacket(protocol);
			System.out.print(Send_D1.length);
			OutWriter.write(Send_D1);*/
			/*返回-收到确认信息ACK_D1*/
		//	s.setSoTimeout(60000);
			/*Protocol Receive_D1ACK=null;
			long Receive_D1ACK_PNo=-1;
			Receive_D1ACK=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
			Receive_D1ACK_PNo=Long.parseLong((new String(Receive_D1ACK.getPNo())));
			if(null==Receive_D1ACK){
				return "D1ACK_PacketError";
			}else if(0xFF!=Integer.valueOf(new String(Receive_D1ACK.getPData()))||PNo!=Receive_D1ACK_PNo){
				return "D1ACK_PacketDataError";
			}else{*/
				
				/*System.out.println("开始等待同步结果");
				提取结果
		//		s.setSoTimeout(60000);//设置等待时间为60s
				Protocol Receive_D2=null;
				Receive_D2=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
			//	while((Receive_D2=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader)))!=null);
				long Receive_D2ACK_PNo=-1;
				if(null==Receive_D2){
					return "D2_PacketError";
				}else if(0x77!=Integer.valueOf(new String(Receive_D2.getPData()))){
					同步失败，返回ACK_D2
					Receive_D2ACK_PNo=Long.parseLong((new String(Receive_D2.getPNo())));//获取ACK_D2的流水号
					System.out.print("同步失败");
					byte[] Send_D2ACK=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD2,Receive_D2ACK_PNo, null,date));
					OutWriter.write(Send_D2ACK);
					return "D2_false";
				}else{
					同步成功,返回ACK_D2
					Receive_D2ACK_PNo=Long.parseLong((new String(Receive_D2.getPNo())));//获取ACK_D2的流水号
					byte[] Send_D2ACK=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD2, Receive_D2ACK_PNo, null,date));
					OutWriter.write(Send_D2ACK);
					Thread.sleep(1000);*/
					/*开始上传未结交易*/
					int UploadCount;//上传条数
					int LastTransNo=0;//循环中，上一次循环的次数
					int SameTransUploadCount=0;//同一次交易上传次数
					
					DateFormat format = new SimpleDateFormat("yyyyMMdd");
					
					String[] dateArr = shiftVerifyService.getShiftDateSpanAsString(stationNo, format.format(format.parse(date)));
					
					List<TransCard> NoPayTransCard = new ArrayList<TransCard>();
					if(!ArrayUtils.isEmpty(dateArr) && dateArr[0] != null && dateArr[1] != null) 
						NoPayTransCard=transCardDao.selDaysNoPayTransCard(dateArr);
					logger.info("提交时间："+date);
					logger.info("未结交易条数："+NoPayTransCard.size());
					/*逐条上传*/
					for(UploadCount=0;UploadCount<NoPayTransCard.size();UploadCount++){
						/*判断是否为上传同一条记录*/
						if(LastTransNo==UploadCount){
							SameTransUploadCount++;
						}else{
							/*流水号自增*/
							PNo++;
							SameTransUploadCount=0;
						}
						/*同一条交易上传次数小于3次*/
						if(SameTransUploadCount<=3){
							/*发送一条交易*/
							byte[] Send_D3=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD3, PNo, NoPayTransCard.get(UploadCount),date));
							OutWriter.write(Send_D3);
							OutWriter.flush();
							logger.debug("发送Send_D3："+publishByte2Str(Send_D3));
							Protocol Receive_D3ACK=null;
							Receive_D3ACK=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
							//s.setSoTimeout(3000);//设置等待D3ACK回复时间为1s
							long Receive_D3ACK_PNo=Long.parseLong((new String(Receive_D3ACK.getPNo())));//获取ACK_D3的流水号
							if(null==Receive_D3ACK){
								return "D3_PacketError";
							}else if(0xFF!=Integer.valueOf(new String(Receive_D3ACK.getPData()))||PNo!=Receive_D3ACK_PNo){
								return "D3_PacketDataError";
							}else{
								logger.debug("上传第"+(UploadCount+1)+"条交易成功，等待回复中...");
								try{
								//	s.setSoTimeout(30000);//设置上传后处理等待时间为3s
									Protocol Receive_D4=null;
									Receive_D4=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
									/*发送D4_ACK回复*/
									logger.debug("发送"+(UploadCount+1)+"个ACK");
									long Receive_D4ACK_PNo=Long.parseLong((new String(Receive_D4.getPNo())));//获取ACK_D4的流水号
									byte[] Send_D4ACK=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD4, Receive_D4ACK_PNo, null,date));
									OutWriter.write(Send_D4ACK);
									OutWriter.flush();
									/*判断D4信息*/
									byte[] Receive_D4_DataResult={Receive_D4.getPData()[0],Receive_D4.getPData()[1],Receive_D4.getPData()[2]};
									
									System.out.println("Receive_D4.getPData().length:"+Receive_D4.getPData().length);
									System.out.print("Receive_D4_DataResult:"+Integer.valueOf(new String(Receive_D4_DataResult)));
									if(0x77==Integer.valueOf(new String(Receive_D4_DataResult))){
										logger.debug("处理第"+(UploadCount+1)+"条交易成功。");
										//记录本次循环编号
										LastTransNo=UploadCount;
									}else{
										logger.debug("处理第"+(UploadCount+1)+"条交易失败。");
										//记录本次循环编号
										LastTransNo=UploadCount;
										//继续执行这条命令
										UploadCount--;
									}	
									
								}catch (SocketTimeoutException e) {
									//超时
									return "D3_UploadTimeOut";
								}
							}
						}else{
							logger.debug("处理第"+(UploadCount+1)+"条交易失败，尝试处理次数超过3次，推测服务异常，全线退出。");
							/*服务器处理错误*/
							if(inReader!=null){
								inReader.close();
							}
							if(OutWriter!=null){
								OutWriter.close();
							}
							if(s!=null){
								s.close();
							}
							return "ServerError";
						}			
					}
					
					/*完全上传成功*/
					if(NoPayTransCard.size()==UploadCount){
						/*第二次请求同步*/
						PNo++;
						Protocol protocol_2=packetOperate.originalProtocol((byte)0xD1, PNo, null,date);
						byte[] Send_D1_2=packetOperate.createPacket(protocol_2);
						logger.debug("==============Send_D1_2 length:"+Send_D1_2.length);
						OutWriter.write(Send_D1_2);
						OutWriter.flush();
						/*返回-收到确认信息ACK_D1_2*/
						Protocol Receive_D1ACK_2=null;
						long Receive_D1ACK_2_PNo=-1;
						Receive_D1ACK_2=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
						Receive_D1ACK_2_PNo=Long.parseLong((new String(Receive_D1ACK_2.getPNo())));
						if(null==Receive_D1ACK_2){
							return "D1ACK_2_PacketError";
						}else if(0xFF!=Integer.valueOf(new String(Receive_D1ACK_2.getPData()))||PNo!=Receive_D1ACK_2_PNo){
							return "D1ACK_2_PacketDataError";
						}else{
							logger.debug("开始等待第二次同步结果");
							/*提取结果*/
							Protocol Receive_D2_2=null;
							Receive_D2_2=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
							long Receive_D2ACK_2_PNo=-1;
							if(null==Receive_D2_2){
								return "D2_2_PacketError";
							}else if(0x77!=Integer.valueOf(new String(Receive_D2_2.getPData()))){
								/*第二次同步失败，返回ACK_D2_2*/
								Receive_D2ACK_2_PNo=Long.parseLong((new String(Receive_D2_2.getPNo())));//获取ACK_D2_2的流水号
								logger.debug("同步失败！");
								byte[] Send_D2ACK_2=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD2,Receive_D2ACK_2_PNo, null,date));
								OutWriter.write(Send_D2ACK_2);
								OutWriter.flush();
								return "D2_2_false";
							}else{
								/*第二次同步成功,返回ACK_D2_2*/
								Receive_D2ACK_2_PNo=Long.parseLong((new String(Receive_D2_2.getPNo())));//获取ACK_D2_2的流水号
								byte[] Send_D2ACK_2=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD2, Receive_D2ACK_2_PNo, null,date));
								OutWriter.write(Send_D2ACK_2);
								OutWriter.flush();
								Thread.sleep(1000);
								/*请求对账*/
								PNo++;
								byte[] Send_D5=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD5, PNo, null,date));
								OutWriter.write(Send_D5);
								OutWriter.flush();
								/*接受D5_ACK*/
								Protocol Receive_D5ACK=null;
								long Receive_D5ACK_PNo=-1;
								Receive_D5ACK=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
								Receive_D5ACK_PNo=Long.parseLong((new String(Receive_D5ACK.getPNo())));
								if(null==Receive_D5ACK){
									return "D5ACK_PacketError";
								}else if(0xFF!=Integer.valueOf(new String(Receive_D5ACK.getPData()))||PNo!=Receive_D5ACK_PNo){
									return "D5ACK_PacketDataError";
								}else{
									Thread.sleep(2000);
									/*开始对账*/
									logger.debug("开始对账！");
									Protocol Receive_D6=null;
									long Receive_D6_PNo=-1;
									Receive_D6=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
									Receive_D6_PNo=Long.parseLong((new String(Receive_D6.getPNo())));
									/*返回D6_ACK*/
									byte[] Send_D6_ACK=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD6, Receive_D6_PNo,null,date));
									OutWriter.write(Send_D6_ACK);
									OutWriter.flush();
									/*获取站级当日账目信息*/
									int[] DayTransDetail=transCardDao.selDaysTransTotal(dateArr);
									/*D5申请处理结果*/
									byte[] Receive_D6_DataResult={Receive_D6.getPData()[0],Receive_D6.getPData()[1],Receive_D6.getPData()[2]};
									if(0x77==Integer.valueOf(new String(Receive_D6_DataResult))){
										/*D5申请处理成功*/
										byte[] Receive_D6_DataTransCount=new byte[8];
										System.arraycopy( Receive_D6.getPData(), 3, Receive_D6_DataTransCount, 0, 8);
										int DataTransCount=Integer.valueOf(new String(Receive_D6_DataTransCount));
										System.out.print("DataTransCount:"+DataTransCount);
										byte[] Receive_D6_DataTransMoney=new byte[12];
										System.arraycopy( Receive_D6.getPData(), 11, Receive_D6_DataTransMoney, 0, 12);
										int DataTransMoney=Integer.valueOf(new String(Receive_D6_DataTransMoney));
										System.out.print("DataTransMoney:"+DataTransMoney);
										byte[] Receive_D6_DataTransVol=new byte[12];
										System.arraycopy( Receive_D6.getPData(), 23, Receive_D6_DataTransVol, 0, 12);
										int DataTransVol=Integer.valueOf(new String(Receive_D6_DataTransVol));
										
										System.out.print("  MyDataTransCount:"+DayTransDetail[0]);
										System.out.print("  DataTransMoney:"+DayTransDetail[1]);
										System.out.print("  MyDataTransV:"+DayTransDetail[2]);
										if(DataTransCount==DayTransDetail[0]&&DataTransMoney==DayTransDetail[1]&&DataTransVol==DayTransDetail[2]){
											/*对账成功*/
											logger.debug("对账成功！");
											/*设置对账标识*/
											logger.debug("申请设置对账成功标识！");
											PNo++;
											byte[] Send_D7=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD7, PNo,null,date));
											OutWriter.write(Send_D7);
											OutWriter.flush();
											/*接受D7_ACK*/
											Protocol Receive_D7ACK=null;
											long Receive_D7ACK_PNo=-1;
											Receive_D7ACK=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
											Receive_D7ACK_PNo=Long.parseLong((new String(Receive_D7ACK.getPNo())));
											if(null==Receive_D5ACK){
												return "D7ACK_PacketError";
											}else if(0xFF!=Integer.valueOf(new String(Receive_D5ACK.getPData()))||PNo!=Receive_D7ACK_PNo){
												return "D7ACK_PacketDataError";
											}else{
												Thread.sleep(2000);
												Protocol Receive_D8=null;
												long Receive_D8ACK_PNo=-1;
												Receive_D8=packetOperate.analysisPacket(packetOperate.inputStream2Packet(inReader));
												System.out.print("ccc:"+Integer.valueOf(new String(Receive_D8.getPData())));
												if(0x77==Integer.valueOf(new String(Receive_D8.getPData()))){
													/*设置对账标识成功*/
													logger.info("设置对账标识成功！");
													/*返回D8_ACK*/
													Receive_D8ACK_PNo=Long.parseLong((new String(Receive_D8.getPNo())));
													byte[] Send_D8_ACK=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD8, Receive_D8ACK_PNo,null,date));
													OutWriter.write(Send_D8_ACK);
													OutWriter.flush();
													return "success";
												}else{
													/*设置对账标识失败*/
													logger.info("设置对账标识失败！");
													/*返回D8_ACK*/
													Receive_D8ACK_PNo=Long.parseLong((new String(Receive_D8.getPNo())));
													byte[] Send_D8_ACK=packetOperate.createPacket(packetOperate.originalProtocol((byte)0xD8, Receive_D8ACK_PNo,null,date));
													OutWriter.write(Send_D8_ACK);
													if(inReader!=null){
														inReader.close();
													}
													if(OutWriter!=null){
														OutWriter.close();
													}
													if(s!=null){
														s.close();
													}
													return "D8_false";
													
												}
											}
										}else{
											/*对账失败*/
											logger.debug("对账失败！");
											if(inReader!=null){
												inReader.close();
											}
											if(OutWriter!=null){
												OutWriter.close();
											}
											if(s!=null){
												s.close();
											}
											return "D6_false";
										}
									}else{
										/*D5申请处理失败*/
										if(inReader!=null){
											inReader.close();
										}
										if(OutWriter!=null){
											OutWriter.close();
										}
										if(s!=null){
											s.close();
										}
										return "D5_false";
									}
								}
										
								}
							}
					}else{
						if(inReader!=null){
							inReader.close();
						}
						if(OutWriter!=null){
							OutWriter.close();
						}
						if(s!=null){
							s.close();
						}
						return "D3_UploadInComplete";
					}
				
			//	}	
		//	}
		} catch (UnknownHostException e) {
			logger.error("不能发现远端服务器！",e);
			/*找不到远程服务器*/
			e.printStackTrace();
			return "NoFindServer";
		}catch (SocketTimeoutException e){
			logger.error("网络连接超时！",e);
			/*超时*/
			if(inReader!=null){
				inReader.close();
			}
			if(OutWriter!=null){
				OutWriter.close();
			}
			if(s!=null){
				s.close();
			}
			return "TimeOut";
		}catch (IOException e) {
			/*网络异常*/
			logger.error("card 连接网络异常",e);
			if(inReader!=null){
				inReader.close();
			}
			if(OutWriter!=null){
				OutWriter.close();
			}
			if(s!=null){
				s.close();
			}
			return "NetError";
		}finally{
			if(inReader!=null){
				inReader.close();
			}
			if(OutWriter!=null){
				OutWriter.close();
			}
			if(s!=null){
				s.close();
			}
		}
	}

	public PacketOperate getPacketOperate() {
		return packetOperate;
	}

	public void setPacketOperate(PacketOperate packetOperate) {
		this.packetOperate = packetOperate;
	}

	public TransCardDao getTransCardDao() {
		return transCardDao;
	}

	public void setTransCardDao(TransCardDao transCardDao) {
		this.transCardDao = transCardDao;
	}
	
	private  static String publishByte2Str(byte[] byteArr){
		StringBuilder ss = new StringBuilder();
		for(byte b : byteArr){
			ss.append(((int)b&0xFF) + "").append("-");
		}
		return ss.toString();
	}
}
