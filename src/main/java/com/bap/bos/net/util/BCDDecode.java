package com.bap.bos.net.util;

/**
 * BCD-十进制转码工具类
 * 
 * @author zhulong
 * 
 */

public class BCDDecode {

	/**
	 * bcd->十进制（数字）
	 * @param bytes BCD码
	 * @return	10进制串
	 */
	public String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		/*return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
				.toString().substring(1) : temp.toString();*/
		return temp.toString();
	}

	/**
	 * 十进制（数字）-> bcd
	 * @param asc 十进制字符串
	 * @return	bcd编码
	 */
	public byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}
	
	/*test:BCD<->十进制*/
	public static void main(String[] args) {
		BCDDecode c=new BCDDecode();
		byte[] b = c.str2Bcd("000001011402121551380108");
		System.out.print("十进制->bcd：");
		for(int i=0;i<b.length;i++){
			System.out.print(b[i]);
		}
		System.out.println("；bcd->十进制："+c.bcd2Str(b));
		
	}

}
