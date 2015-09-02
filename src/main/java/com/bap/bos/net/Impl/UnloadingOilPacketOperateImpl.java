package com.bap.bos.net.Impl;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.bap.bos.net.UnloadingOilPacketOperate;
import com.bap.bos.net.util.BCDDecode;
import com.bap.bos.net.util.Encryption;
import com.bap.bos.net.util.Protocol;

/**
 * 分装-解析卸油数据包
 * @author zhulong
 *
 */
@Component
public class UnloadingOilPacketOperateImpl implements UnloadingOilPacketOperate {
	/* 进行BCD转码操作工具类 */
	BCDDecode BCDDecode = new BCDDecode();
	/* 加密工具类 */
	Encryption Encryption = new Encryption();
	
	/* (non-Javadoc)
	 * @see com.bos.net.Impl.UnloadingOilPacketOperate#originalProtocol(byte, long, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Protocol originalProtocol(byte PCommand, long PNo,String OrderBillNum,String TankNum,String Status) throws Exception {
		/*封包类*/
		Protocol p = new Protocol();
		/*初始化协议头、流水号*/
		byte[] PHeader = { (byte) (0xCF), (byte) (0xCF) };
		byte[] PNo_byte = String.format("%08d", PNo).getBytes();
		byte[] PData;
		/*设置协议头、流水号、命令*/
		p.setPHeader(PHeader);
		p.setPNo(PNo_byte);
		p.setPCommand(PCommand);
		/*设置数据域*/
		switch (PCommand) {
		case (byte) 0xB1:
			/*请求油罐卸油 */
			PData = (TankNum+OrderBillNum+Status).getBytes();
			p.setPData(PData);
			break;
		case (byte) 0xB2:
			/* 数据回复结果ACK-灌号 */
			byte[] ACK_B2=TankNum.getBytes();
			PData =ACK_B2;
			p.setPData(PData);
			break;
		default:
			break;
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.bos.net.Impl.UnloadingOilPacketOperate#createPacket(com.bos.net.util.Protocol)
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
			System.out.print(PData_bcd[i] & 0xff);
		}
		/*调用加密算法，进行部分加密*/
		byte[] encryptPart=Encryption.encrypt(Protocol);
		/*创建发送包*/
		byte[] PHeader=Protocol.getPHeader();
		byte[] PSecret={Protocol.getPSecretKey()};
		byte[] PSize=new byte[2];
		short k=(short)PData_bcd.length;
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

	/* (non-Javadoc)
	 * @see com.bos.net.Impl.UnloadingOilPacketOperate#analysisPacket(byte[])
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
			case (byte)0xB1:
				System.out.print("B1:");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] B1_ACK={(byte)(originalBCD[5]&0xff)};
				PData=String.format("%d",((byte)(B1_ACK[0]))&0xff);
				System.out.print("PData:"+PData);
				break;
			case (byte)0xB2:
				System.out.print("B2:");
				PNo= BCDDecode.bcd2Str(PNoBCD);
				byte[] B2_Result1=new byte[PDataSize-1];
				System.arraycopy(originalBCD, 5 ,B2_Result1, 0, PDataSize-1);
				
				String B2_Result2=String.format("%d",(byte)(originalBCD[PDataSize+4]&0xff));
				PData=BCDDecode.bcd2Str(B2_Result1)+B2_Result2;
				System.out.print("PData:"+PData);
				break;
			default:
				break;
			}
			System.out.println("还原后："+PNo+' '+(PCommand[0] & 0xff)+' '+PData+"结束");	
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
	

}
