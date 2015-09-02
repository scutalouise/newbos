package com.bap.bos.net.util;


/**
 * 加密解密工具类
 * 
 * @author zhulong
 * 
 */
public class Encryption {
	/*设置加密因子*/
	public byte PSecretKey=0;
	/* 随机取值数组 */
	private byte[] keys = { (byte) 0xcd, (byte) 0xf0, (byte) 0x35, (byte) 0x03,
			(byte) 0xbd, (byte) 0xe6, (byte) 0xb9, (byte) 0x1e, (byte) 0x07,
			(byte) 0xcc, (byte) 0x43, (byte) 0xd5, (byte) 0x2c, (byte) 0x6b,
			(byte) 0x9e, (byte) 0x2b, (byte) 0x45, (byte) 0x62, (byte) 0x97,
			(byte) 0x5c, (byte) 0x77, (byte) 0xdd, (byte) 0xa6, (byte) 0x27,
			(byte) 0x1d, (byte) 0xf9, (byte) 0xa5, (byte) 0x6d, (byte) 0xd7,
			(byte) 0x9e, (byte) 0xcb, (byte) 0xa4, (byte) 0xad, (byte) 0xcc,
			(byte) 0x4c, (byte) 0x28, (byte) 0xb5, (byte) 0x0a, (byte) 0xc5,
			(byte) 0xd8, (byte) 0xc5, (byte) 0x35, (byte) 0x9b, (byte) 0x11,
			(byte) 0xd0, (byte) 0x77, (byte) 0x57, (byte) 0xb7, (byte) 0x8a,
			(byte) 0x10, (byte) 0xf8, (byte) 0x27, (byte) 0xc0, (byte) 0x00,
			(byte) 0xca, (byte) 0xe2, (byte) 0x99, (byte) 0xf6, (byte) 0x22,
			(byte) 0x26, (byte) 0x92, (byte) 0xad, (byte) 0xda, (byte) 0xbb,
			(byte) 0x6a, (byte) 0x85, (byte) 0xe8, (byte) 0x29, (byte) 0xdf,
			(byte) 0x65, (byte) 0x6f, (byte) 0xa9, (byte) 0x1b, (byte) 0x76,
			(byte) 0xc8, (byte) 0x2b, (byte) 0x75, (byte) 0x31, (byte) 0xa3,
			(byte) 0xe3, (byte) 0x49, (byte) 0x2a, (byte) 0x24, (byte) 0xd9,
			(byte) 0xa7, (byte) 0xba, (byte) 0x5c, (byte) 0x56, (byte) 0xf9,
			(byte) 0x51, (byte) 0xa7, (byte) 0xe4, (byte) 0x16, (byte) 0xbd,
			(byte) 0x8d, (byte) 0xcd, (byte) 0x45, (byte) 0x4d, (byte) 0x68,
			(byte) 0x54, (byte) 0xa6, (byte) 0x31, (byte) 0x29, (byte) 0x00,
			(byte) 0x6b, (byte) 0xc2, (byte) 0x96, (byte) 0xfe, (byte) 0xe2,
			(byte) 0x53, (byte) 0x0f, (byte) 0xcc, (byte) 0x90, (byte) 0xc2,
			(byte) 0x11, (byte) 0x66, (byte) 0x23, (byte) 0x96, (byte) 0xbf,
			(byte) 0x0e, (byte) 0xf2, (byte) 0xa8, (byte) 0xca, (byte) 0xb2,
			(byte) 0x1b, (byte) 0xbb, (byte) 0xc9, (byte) 0xc3, (byte) 0xfb,
			(byte) 0xa3, (byte) 0x9e, (byte) 0xdd, (byte) 0xa3, (byte) 0x2b,
			(byte) 0x8f, (byte) 0x4c, (byte) 0x86, (byte) 0x77, (byte) 0x10,
			(byte) 0x0a, (byte) 0x89, (byte) 0x39, (byte) 0x4d, (byte) 0x1a,
			(byte) 0x9e, (byte) 0xe3, (byte) 0x10, (byte) 0x77, (byte) 0xb0,
			(byte) 0x18, (byte) 0x58, (byte) 0x84, (byte) 0x8f, (byte) 0x32,
			(byte) 0x6b, (byte) 0x16, (byte) 0x96, (byte) 0x70, (byte) 0xcc,
			(byte) 0x58, (byte) 0xd5, (byte) 0x12, (byte) 0x2f, (byte) 0x83,
			(byte) 0x0b, (byte) 0x86, (byte) 0x72, (byte) 0x83, (byte) 0x1b,
			(byte) 0x85, (byte) 0xb7, (byte) 0x10, (byte) 0x4c, (byte) 0xb7,
			(byte) 0x7e, (byte) 0xb9, (byte) 0x7f, (byte) 0x03, (byte) 0xed,
			(byte) 0xd9, (byte) 0xba, (byte) 0x94, (byte) 0x09, (byte) 0xbe,
			(byte) 0xcc, (byte) 0xbf, (byte) 0x83, (byte) 0xfb, (byte) 0x67,
			(byte) 0x75, (byte) 0x17, (byte) 0xd1, (byte) 0xa9, (byte) 0x4d,
			(byte) 0xde, (byte) 0xbf, (byte) 0x25, (byte) 0x75, (byte) 0x82,
			(byte) 0xd1, (byte) 0x94, (byte) 0xf8, (byte) 0xa9, (byte) 0x26,
			(byte) 0x85, (byte) 0x72, (byte) 0x75, (byte) 0x21, (byte) 0x3f,
			(byte) 0x28, (byte) 0xac, (byte) 0x58, (byte) 0xcc, (byte) 0xfc,
			(byte) 0x74, (byte) 0x8c, (byte) 0x69, (byte) 0xc9, (byte) 0x03,
			(byte) 0xa6, (byte) 0xe4, (byte) 0x6d, (byte) 0xbd, (byte) 0x8a,
			(byte) 0x92, (byte) 0x55, (byte) 0xad, (byte) 0xce, (byte) 0x5a,
			(byte) 0x35, (byte) 0xb7, (byte) 0x25, (byte) 0xa4, (byte) 0x95,
			(byte) 0x04, (byte) 0xa5, (byte) 0x93, (byte) 0x6e, (byte) 0x7d,
			(byte) 0x30, (byte) 0x23, (byte) 0xa7, (byte) 0x86, (byte) 0x98,
			(byte) 0x23, (byte) 0x1b, (byte) 0xd1, (byte) 0xa3, (byte) 0x5b,
			(byte) 0x9c, (byte) 0xb5, (byte) 0x61, (byte) 0xc0, (byte) 0xb4,
			(byte) 0x5f, (byte) 0x46 };
	/**
	 * 加密
	 * @param original 原文
	 * @return 密钥起始位置+密文
	 */
	
	public byte[] encrypt(Protocol original) throws Exception {
		byte[] PData = original.getPData();
		byte[] PNo = original.getPNo();
		byte[] PCommand ={ original.getPCommand()};
		byte PSecretKey=original.getPSecretKey();
		/* (PSecretKey & 0xff) 转成无符号整型 
		 * System.out.println(PSecretKey & 0xff);*/
		/*拼接-需要加密的部分*/
		System.out.println("PSecretKey:"+(PSecretKey & 0xff));
		byte[] encryptbytes_front=new byte[(PNo.length+1)];	
		System.arraycopy(PNo, 0, encryptbytes_front, 0, PNo.length);
		System.arraycopy(PCommand, 0, encryptbytes_front,PNo.length, 1);
		byte[] encryptbytes=null;
		if("".getBytes()==PData||null==PData){
			encryptbytes=encryptbytes_front;
		}else{
			encryptbytes=new byte[encryptbytes_front.length+PData.length];
			System.arraycopy(encryptbytes_front, 0, encryptbytes, 0, encryptbytes_front.length);
			System.arraycopy(PData, 0, encryptbytes, encryptbytes_front.length, PData.length);
		}
		
		System.out.println("原始：");
		for(int i=0;i<encryptbytes.length;i++){	
			System.out.print(encryptbytes[i]);
		}
		/*start加密*/
		//int j=0;
		int k=0;
		for(int i=0;i<encryptbytes.length;i++){
			//int start=(PSecretKey & 0xff);
			k =(PSecretKey + i) & 0xff;
			encryptbytes[i]^=keys[k];
		//	k = (start+i)%256;
			/*if((start+j)<=255){
				encryptbytes[i]^=keys[start+(j)];
			}
			if((start+j)>255){
				if((start+j)%255==1){
					k=0;
				}else{
					encryptbytes[i]^=keys[k];
					k++;
				}	
			}
			j++;*/
		}	
		return encryptbytes;
		
/*		System.out.println("异或后：");
		for(int i=0;i<encryptbytes.length;i++){	
			System.out.print(encryptbytes[i]);
		}
		System.out.println("二次异或：");
		j=0;
		k=0;
		for(int i=0;i<encryptbytes.length;i++){
			k =(PSecretKey + i) & 0xff;
			encryptbytes[i]^=keys[k];
		}
		for(int i=0;i<encryptbytes.length;i++){	
			System.out.print(encryptbytes[i]);
		}
		return encryptbytes;*/
	}

	/**
	 * 解密
	 * @param keysStart  密钥起始位置
	 * @param cryptOgraph 密文
	 * @return
	 */
	public byte[] dencrypt(Integer keysStart, byte[] cryptOgraph) {
		//int j=0;
		int k=0;
		for(int i=0;i<cryptOgraph.length;i++){
			k =(keysStart + i) & 0xff;
			cryptOgraph[i]^=keys[k];		
			/*if((keysStart+j)<=255){
				cryptOgraph[i]^=keys[keysStart+(j)];
			}
			if((keysStart+j)>255){
				if((keysStart+j)%255==1){
					k=0;
				}else{
					cryptOgraph[i]^=keys[k];
					k++;
				}	
			}
			j++;*/
		}
		return cryptOgraph;
	}
	
/*	public static void main(String[] args) throws Exception {
		Protocol p = new Protocol();
		byte[] PHeader = { (byte) (0xCF), (byte) (0xCF) };
		p.setPHeader(PHeader);
		byte[] pDate = "84211111111".getBytes();
		p.setPData(pDate);
		byte[] PNo = "000001".getBytes();
	//	byte[] PNo = BCDDecode.str2Bcd(PNoS);
		p.setPNo(PNo);
		byte PCommand = (byte) 0xD1;
		p.setPCommand(PCommand);
		p.setPSecretKey((byte)253);
		Encryption Encryption = new Encryption();
		Encryption.encrypt(p);
		System.out.println("原文："+p.getPData().toString());
		byte[] Mw=Encryption.encrypt(p);
		System.out.println("迷文："+Mw.toString());
		byte[] yuan=Encryption.dencrypt(248,Mw);
		System.out.println("解密后"+yuan.toString());
	}*/
	/**
	 * --byte转为string-- String s = new String(pDate, "GB2312");
	 * System.out.print(s);0001-47505150
	 */
}
