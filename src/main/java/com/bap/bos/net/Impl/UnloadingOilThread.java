package com.bap.bos.net.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.net.UnloadingOilPacketOperate;
import com.bap.bos.net.util.Protocol;
import com.bap.bos.util.Config;

@Component("unloadingOilThread")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UnloadingOilThread implements Callable<String> {
	/*数据打包-解析类*/
	@Resource 
	private UnloadingOilPacketOperate uOpacketOperate;
	
	Logger logger = LoggerFactory.getLogger(UnloadingOilThread.class);
	
	@Resource
	@Qualifier("pos")
	private Config pos;
	
	
	/*初始化数据*/
	private String OrderBillNum;
	private String TankNum;
	private String Status;
	/*输入输出字节流*/
	private InputStream inReader=null;
	private OutputStream OutWriter=null;
	/*发包流水号*/
	private long PNo=0;
	
	public void setOrderBillNum(String orderBillNum) {
		OrderBillNum = orderBillNum;
	}


	public void setTankNum(String tankNum) {
		TankNum = tankNum;
	}



	public void setStatus(String status) {
		Status = status;
	}

	public UnloadingOilThread(String OrderBillNum,String TankNum,String Status){
		this.OrderBillNum=OrderBillNum;
		this.TankNum=TankNum;
		this.Status=Status;
	}
	
	public UnloadingOilThread(){
	}
	
	/**
	 * 拼接多个数据包
	 * @param srcArrays
	 * @return	数据包组
	 */
	public static byte[] sysCopy(List<byte[]> srcArrays) {
		int len = 0;
		for (byte[] srcArray : srcArrays) {
			len += srcArray.length;
		}
		byte[] destArray = new byte[len];
		int destLen = 0;
		for (byte[] srcArray : srcArrays) {
			System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
			destLen += srcArray.length;
		}
		return destArray;
	} 
	
	public String call() throws Exception {
		Socket s=null;
		try {
			/*初始化Scoket*/
			String ip=pos.getIp();
			logger.info("=================获取远程POS基本信息 pos_IP: "+ip+" port:65008================");
			s=new Socket(ip,65008);
			/*设置超时 3分钟未读到数据*/
			s.setSoTimeout(3*60*1000);
			/*初始化Scoket的输入-输出流*/
			inReader=s.getInputStream();
			OutWriter=s.getOutputStream();
		
			/****卸油开始****/
			String[] tankNums = TankNum.split(",");
			//组合多罐协议包
			List<byte[]> temp = new ArrayList<byte[]>();
			for(int i=0;i<tankNums.length;i++){
				temp.add(uOpacketOperate.createPacket(uOpacketOperate.
						originalProtocol((byte)0xB1, PNo, OrderBillNum, tankNums[i].trim(), Status)));
				
			}
			/*byte[] B1=uOpacketOperate.createPacket(uOpacketOperate.
					originalProtocol((byte)0xB1, PNo, OrderBillNum, TankNum, Status));*/
			byte[] B1=sysCopy(temp);
			logger.info("B1密文："+B1);
			OutWriter.write(B1);
			Protocol Receive_B1ACK=null;
			Receive_B1ACK=uOpacketOperate.analysisPacket(uOpacketOperate.inputStream2Packet(inReader));
			long Receive_B1ACK_PNo=-1;
			Receive_B1ACK_PNo=Long.parseLong((new String(Receive_B1ACK.getPNo())));
			if(null==Receive_B1ACK){
				logger.info("异常返回标志：B1ACK_PacketNull");
				return "B1ACK_PacketNull";
			}else if(PNo!=Receive_B1ACK_PNo){
				logger.info("异常返回标志：B1ACK_PacketPNoError");
				return "B1ACK_PacketPNoError";
			}else if(0x00==Integer.valueOf(new String(Receive_B1ACK.getPData()))){
				logger.info("异常返回标志：B1_OperateFalse");
				return "B1_OperateFalse";
			}else{
				logger.info("B2开始");
				/*等待B2中*/
				Protocol Receive_B2=null;
				Receive_B2=uOpacketOperate.analysisPacket(uOpacketOperate.inputStream2Packet(inReader));
				//得到数据域
				String jieguo=new String(Receive_B2.getPData());
				//返回ACK
				long B2_PNo=Long.valueOf(new String(Receive_B2.getPNo()));
				logger.debug("返回ACK:"+B2_PNo);
				
				byte[] B2_ACK=uOpacketOperate.createPacket(uOpacketOperate.
						originalProtocol((byte)0xB2,B2_PNo, OrderBillNum, TankNum, Status));
				logger.debug("写回ACK:"+B2_ACK);
				OutWriter.write(B2_ACK);
				OutWriter.flush();
				if(inReader!=null){
					inReader.close();
				}
				if(OutWriter!=null){
					OutWriter.close();
				}
				if(s!=null){
					s.close();
				}
				return jieguo;
			}
			
		} catch (UnknownHostException e) {
			/*找不到远程服务器*/
			logger.debug("找不到远程服务器！",e);
			return "NoFindServer";
		}catch (SocketTimeoutException e){
			logger.debug("连接超时！",e);
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
			logger.debug("其他异常！",e);
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
		}
	}

	public UnloadingOilPacketOperate getuOpacketOperate() {
		return uOpacketOperate;
	}

	public void setuOpacketOperate(UnloadingOilPacketOperate uOpacketOperate) {
		this.uOpacketOperate = uOpacketOperate;
	}

}


