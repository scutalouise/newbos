package com.bap.bos.net;

import java.io.InputStream;

import com.bap.bos.net.util.Protocol;

public interface UnloadingOilPacketOperate {

	/**
	 * 发送数据-明文封装
	 * @param PCommand 命令
	 * @param PNo 流水号
	 * @param OrderBillNum 订单号
	 * @param TankNum	灌号
	 * @param Status	卸油状态
	 * @return
	 * @throws Exception
	 */
	public abstract Protocol originalProtocol(byte PCommand, long PNo,
			String OrderBillNum, String TankNum, String Status)
			throws Exception;

	/**
	 * 创建-发送数据包
	 * @param Protocol 原文
	 * @return
	 */
	public abstract byte[] createPacket(Protocol Protocol) throws Exception;

	/**
	 * 解析数据流包
	 * 
	 * @param Packet 读取出来的一个数据包
	 * @return
	 */
	public abstract Protocol analysisPacket(byte[] Packet);
	/**
	 * inputStream转Byte[]
	 * @param inReader Socket的输入流对象
	 * @return 流中的数据-byte[]
	 * @throws Exception
	 */
	public byte[] inputStream2Packet(InputStream inReader) throws Exception;

}