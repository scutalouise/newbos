package com.bap.bos.net.Impl;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bap.bos.dao.StationDao;
import com.bap.bos.domain.Station;
import com.bap.bos.domain.TransCard;
import com.bap.bos.net.PacketOperate;
import com.bap.bos.net.util.BCDDecode;
import com.bap.bos.net.util.Encryption;
import com.bap.bos.net.util.Protocol;
/**
 * 封装-解析数据包实体类
 * @author zhulong
 *
 */
@Component
public class PacketOperateImpl implements PacketOperate {
	@Resource
	private StationDao stationDao;
	/* 进行BCD转码操作工具类 */
	BCDDecode BCDDecode = new BCDDecode();
	/* 加密工具类 */
	Encryption Encryption = new Encryption();
	
	Logger logger = LoggerFactory.getLogger(PacketOperateImpl.class);
	/**
	 * 发送数据-明文封装
	 * 
	 * @param PCommand 命令
	 * @param PNo 数据流水号
	 * @param NoPayTransCard 未结卡交易（一条）
	 * @param OperateDate 需要结算的日期
	 * @return 明文数据包
	 */
	public Protocol originalProtocol(byte PCommand, long PNo,TransCard NoPayTransCard,String OperateDate) throws Exception {
		/*油站信息*/
		List<Station> station=null;
		/*封包类*/
		Protocol p = new Protocol();
		/*初始化协议头、流水号、命令*/
		byte[] PHeader = { (byte) (0xCF), (byte) (0xCF) };
		byte[] PNo_byte = String.format("%08d", PNo).getBytes();
		byte[] PData;
		/*设置协议头、流水号、命令*/
		p.setPHeader(PHeader);
		p.setPNo(PNo_byte);
		p.setPCommand(PCommand);
		/*数据域设置*/
		switch (PCommand) {
		case (byte) 0xD1:
			/* 请求同步 */
			station = stationDao.selStationDetail();
			PData = (station.get(0).getStation_No() + OperateDate).getBytes();
			p.setPData(PData);
			break;
		case (byte) 0xD2:
			/* 同步结果ACK */
			byte[] ACK_D2={(byte) 0xff};
			PData =ACK_D2;
			p.setPData(PData);
			break;
		case (byte) 0xD3:
			/* 未结交易设置 (一条)*/
			String TransNo=NoPayTransCard.getTransCard_TransNo();
			String StationNo=NoPayTransCard.getTransCard_StatinNo();
			String CardNo=NoPayTransCard.getTransCard_CardNo();
			String StaffNo=NoPayTransCard.getTransCard_StaffNo();
			String DitNo=NoPayTransCard.getTransItem_DitNo();
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String TxApplyTime=format.format(NoPayTransCard.getTransCard_TxApplyTime());
			String PayAmount=String.format("%012d", (int)((NoPayTransCard.getTransCard_PayAmount()+0.005)*100));
			String PayVol=String.format("%012d", (int)(NoPayTransCard.getTransCard_PayVol()*100));
			String ItemCode=NoPayTransCard.getTransCard_ItemCode();
			String TransType=NoPayTransCard.getTransCard_TransType();
			String SellPrice=String.format("%04d",(int)(NoPayTransCard.getTransCard_SellPrice()*100));
			String ShiftDate=NoPayTransCard.getTransCard_ShiftDate();
			String ShiftNo=NoPayTransCard.getTransCard_ShiftNo();
			String reqType=NoPayTransCard.getTransCard_ReqType(); //请求方式（1代表有卡 2无卡  ）
			
			logger.debug("测试TransNo:"+TransNo);
			
			byte[] NoPayTransCardBytes=null;
			if(StaffNo==null||"".equals(StaffNo)){
				System.out.println("StaffNo:"+StaffNo);
				NoPayTransCardBytes=(TransNo+StationNo+CardNo+DitNo+"00000000"+TxApplyTime+PayAmount+PayVol+ItemCode+TransType+SellPrice+ShiftDate+ShiftNo+"0"+reqType).getBytes();		
				logger.debug("NoPayTransCardString1:"+TransNo+" "+StationNo+" "+CardNo+" "+DitNo+" "+"00000000"+" "+TxApplyTime+" "+PayAmount+" "+PayVol+" "+ItemCode+" "+TransType+" "+SellPrice+" "+ShiftDate+" "+ShiftNo+" "+reqType);
			}else{
				System.out.println("StaffNo:"+StaffNo);
				NoPayTransCardBytes=(TransNo+StationNo+CardNo+DitNo+StaffNo+TxApplyTime+PayAmount+PayVol+ItemCode+TransType+SellPrice+ShiftDate+ShiftNo+reqType).getBytes();		
				logger.debug("NoPayTransCardString2:"+TransNo+" "+StationNo+" "+CardNo+" "+DitNo+" "+StaffNo+" "+TxApplyTime+" "+PayAmount+" "+PayVol+" "+ItemCode+" "+TransType+" "+SellPrice+" "+ShiftDate+" "+ShiftNo+" "+reqType);
			}	
			
			logger.debug("NoPayTransCardBytes:"+NoPayTransCardBytes);
			p.setPData(NoPayTransCardBytes);
			break;
		case (byte) 0xD4:
			/* 同步结果ACK */
			byte[] ACK_D4={(byte) 0xff};
			PData =ACK_D4;
			p.setPData(PData);
			break;
		case (byte) 0xD5:
			/*对账申请*/
			station = stationDao.selStationDetail();
			PData = (station.get(0).getStation_No() + OperateDate ).getBytes();
			p.setPData(PData);
			break;
		case (byte) 0xD6:
			/* 同步结果ACK */
			byte[] ACK_D6={(byte) 0xff};
			PData =ACK_D6;
			p.setPData(PData);
			break;
		case (byte) 0xD7:
			/*设置对账标识*/
			station = stationDao.selStationDetail();
			PData = (station.get(0).getStation_No() + OperateDate).getBytes();
			p.setPData(PData);
			break;
		case (byte) 0xD8:
			/* 同步结果ACK */
			byte[] ACK_D8={(byte) 0xff};
			PData =ACK_D8;
			p.setPData(PData);
			break;
		default:
			break;
		}
		return p;
	}

	/**
	 * 创建-发送数据包
	 * @param Protocol 原文
	 * @return
	 */
	public byte[] createPacket(Protocol Protocol) throws Exception{
		/*需要转BCD码部分*/
		byte[] PNo=Protocol.getPNo();
		byte[] PData=Protocol.getPData();
		/*转BCD码*/
		byte[] PNo_bcd=BCDDecode.str2Bcd(new String(PNo));
		byte[] PData_bcd=BCDDecode.str2Bcd(new String(PData));
		/***加密开始***/
		byte PSecretKey =(byte)(Math.random() * 255);
		
		/*设置已经转换成BCD码的明文*/
		Protocol.setPNo(PNo_bcd);
		Protocol.setPData(PData_bcd);
		Protocol.setPSecretKey(PSecretKey);
		System.out.println("发送数据明文BCD：");
		for(int i=0;i<PData_bcd.length;i++){
			logger.debug("内容BCD："+(PData_bcd[i] & 0xff));
		}
		logger.debug("内容BCD："+bytesToHexString(PData_bcd));
		
		logger.debug("***发送数据明文BCD结束*****");
		/*调用加密算法，进行部分加密*/
		byte[] encryptPart=Encryption.encrypt(Protocol);
		/*创建发送包*/
		byte[] PHeader=Protocol.getPHeader();
		byte[] PSecret={Protocol.getPSecretKey()};
		byte[] PSize=new byte[2];
		Short k=(short)PData_bcd.length;
		
		logger.debug("内容字节数组长："+k);
		
		for (int i = 0; i < 2; i++) {  
			PSize[1]=(byte)(k & 0xff);
			PSize[0]=(byte)((k>>8) & 0xff);
		}
		System.out.print("PSize:"+k);
		byte[] Packet1=new byte[3];
		System.arraycopy(PHeader, 0, Packet1, 0, 2);
		System.arraycopy(PSecret, 0, Packet1, 2, 1);
		byte[] Packet2=new byte[5];
		System.arraycopy(Packet1, 0, Packet2, 0, 3);
		System.arraycopy(PSize, 0, Packet2, 3, 2);
		byte[] Packet3=new byte[10+PData_bcd.length];
		System.arraycopy(Packet2, 0, Packet3, 0, 5);
		System.arraycopy(encryptPart, 0, Packet3, 5, encryptPart.length);
		/*获取校验码*/
		byte[] VerifyKey={this.createVerifyKey(Packet3)};
		System.out.print("VerifyKey:"+(VerifyKey[0]&0xff));
		byte[] Packet=new byte[10+PData_bcd.length+1];
		System.arraycopy(Packet3, 0, Packet, 0, 5+encryptPart.length);
		System.arraycopy(VerifyKey, 0, Packet, 5+encryptPart.length,1);
		return Packet;
	}

	public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


	
	/**
	 * 解析数据流包
	 * 
	 * @param Packet 读取出来的一个数据包
	 * @return
	 */
	public Protocol analysisPacket(byte[] Packet) {	
		Protocol Protocol=new Protocol();
		if(this.Verify(Packet)){
			/****校验成功****/
			System.out.print("校验成功。");
			/*数据域长度*/
			short PDataSize=(short)(Packet[4] & 0xff |(Packet[3] & 0xff)<<8);
			
			/*密文->BCD码*/
			byte[] cryptOgraph=new byte[PDataSize+5];
			System.arraycopy(Packet, 5, cryptOgraph, 0, PDataSize+5);
			byte[] originalBCD=Encryption.dencrypt((int)(Packet[2] & 0xff), cryptOgraph);
			/*BCD码->原文*/
			byte[] PNoBCD=new byte[4];
			System.arraycopy(originalBCD, 0, PNoBCD, 0, 4);
			byte[] PCommand=new byte[1];
			System.arraycopy(originalBCD, 4, PCommand, 0, 1);
			
			String PNo=null,PData=null;
			//解析命令
			switch (PCommand[0]){		
			case (byte)0xD1:
				System.out.print("D1");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D1_ACK={(byte)(originalBCD[5]&0xff)};
				PData=String.format("%d",((byte)(D1_ACK[0]))&0xff);
				System.out.print(PData);
				break;
			case (byte)0xD2:
				System.out.print("D2");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D2_Result={(byte)(originalBCD[5]&0xff)};
				PData=String.format("%d",((byte)(D2_Result[0])&0xff));
				break;
			case (byte)0xD3:
				System.out.print("D3");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D3_ACK={(byte)(originalBCD[5]&0xff)};
				PData=String.format("%d",((byte)(D3_ACK[0])&0xff));
				break;
			case (byte)0xD4:
				System.out.print("D4");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D4_Result={(byte)(originalBCD[5]&0xff)};
				String PData_D4_Result=String.format("%d",(byte)(D4_Result[0]&0xff));
				byte[] D4_TransNoBCD=new byte[12];
				System.arraycopy(originalBCD, 6 ,D4_TransNoBCD, 0, (PDataSize-1));
				String PData_D4_TransNo=BCDDecode.bcd2Str(D4_TransNoBCD);
				PData=PData_D4_Result+PData_D4_TransNo;
				System.out.print(PData.length());
				break;
			case (byte)0xD5:
				System.out.print("D5");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D5_ACK={(byte)(originalBCD[5]&0xff)};
				PData=String.format("%d",((byte)(D5_ACK[0])&0xff));
				break;
			case (byte)0xD6:
				System.out.print("D6");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D6_Result={(byte)(originalBCD[5]&0xff)};
				String PData_D6_Result=String.format("%d",(byte)(D6_Result[0]&0xff));
				byte[] D6_TransDetailBCD=new byte[16];
				System.arraycopy(originalBCD, 6 ,D6_TransDetailBCD, 0, (PDataSize-1));
				String D6_TransDetail=BCDDecode.bcd2Str(D6_TransDetailBCD);
				PData=PData_D6_Result+D6_TransDetail;
				System.out.print(PData.length());
				break;
			case (byte)0xD7:
				System.out.print("D7");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D7_ACK={(byte)(originalBCD[5]&0xff)};
				PData=String.format("%d",((byte)(D7_ACK[0])&0xff));
				break;
			case (byte)0xD8:
				System.out.print("D8");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] D8_Result={(byte)(originalBCD[5]&0xff)};
				PData=String.format("%d",((byte)(D8_Result[0])&0xff));
				break;
			default:
				break;
			}
			/*数据域全为BCD时使用
			byte[] PDataBCD=new byte[PDataSize];
			System.arraycopy(originalBCD, 5, PDataBCD, 0, PDataSize);
			System.out.println("originalBCD Data:"+(originalBCD[5]&0xff));
			
			if(PDataSize==1){//加入数据域只有1个byte
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] ACK={(byte)(originalBCD[5]&0xff)};
			//	System.out.println("originalBCD:"+ACK[0]);
				PData=String.format("%d",(ACK[0]&0xff));
			}else{
				PNo= BCDDecode.bcd2Str(PNoBCD);
				PData= BCDDecode.bcd2Str(PDataBCD);
			}*/
			System.out.println("还原后："+PNo+(PCommand[0] & 0xff)+PData+"结束");	
			/*包装成Protocol对象*/
			byte[] PHeader={Packet[0],Packet[1]};
			Protocol.setPHeader(PHeader);
			Protocol.setPSecretKey(Packet[2]);
			byte[] PSize={Packet[3],Packet[4]};
			Protocol.setPSize(PSize);		
			Protocol.setPNo(PNo.getBytes());
			Protocol.setPCommand(PCommand[0]);
			Protocol.setPData(PData.getBytes());
			return Protocol;
		}else{
			/*校验失败，数据不完整*/
			System.out.print("校验失败。");
			Protocol=null;
			return Protocol;
		}	
	}
	/**
	 * 创建校验码
	 * @param VerifyData 校验数据
	 * @return
	 */
	public byte createVerifyKey(byte[] VerifyData){
		byte VerifyKey=0;
		for(int i=0;i<VerifyData.length;i++){
			VerifyKey^=VerifyData[i];
		}
		return VerifyKey;
	}
	/**
	 * 校验收到数据的正确性
	 * @param Packet 接收到的数据包
	 * @return
	 */
	public boolean Verify(byte[] Packet){
		byte VerifyKey=0;
		for(int i=0;i<Packet.length;i++){
			VerifyKey^=Packet[i];
		}
		return (VerifyKey==0);	 
	}
	/**
	 * inputStream转Byte[]
	 * @param inReader Socket的输入流对象
	 * @return 流中的数据-byte[]
	 * @throws Exception
	 */
	public byte[] inputStream2Packet(InputStream inReader) throws Exception{
		byte[] b = new byte[256];
		int i=inReader.read(b);
		System.out.println("输入流数据长度："+i);
		for(int j=0;j<i;j++){
			System.out.println(b[j]&0xff);
		}
		return b;
	}
	/**
	 * byte数组转long
	 * @param b byte[]
	 * @return long
	 * @throws Exception
	 */
	public long bytes2long(byte[] b){	
		return ((((long) b[ 0] & 0xff) << 56)
	              | (((long) b[ 1] & 0xff) << 48)
	              | (((long) b[ 2] & 0xff) << 40)
	              | (((long) b[ 3] & 0xff) << 32)
	              | (((long) b[ 4] & 0xff) << 24)
	              | (((long) b[ 5] & 0xff) << 16)
	              | (((long) b[ 6] & 0xff) << 8) 
	              | (((long) b[ 7] & 0xff) << 0));   

	}
	
	public StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}
	
	public static void main(String[] args) {
		
		BCDDecode BCDDecode = new BCDDecode();
		String ss = "00000123431";
		System.out.println(BCDDecode.str2Bcd(ss));
	}

}
