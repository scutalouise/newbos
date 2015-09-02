package com.bap.bos.net;

import java.io.InputStream;

import com.bap.bos.domain.TransCard;
import com.bap.bos.net.util.Protocol;
/**
 * 封装-解析包接口
 * @author zhulong
 *
 */
public interface PacketOperate {

	/**
	 * 发送数据-原文封装
	 * @param PCommand 命令
	 * @param PNo 流水号
	 * @param NoPayTransCard 未结卡交易（一条）
	 * @param OperateDate 需要结算的日期
	 * @return 明文数据包
	 */
	public Protocol originalProtocol(byte PCommand, long PNo,TransCard NoPayTransCard,String OperateDate)throws Exception;

	/**
	 * 创建-发送数据包
	 * @param Protocol 原文
	 * @return
	 */
	public byte[] createPacket(Protocol Protocol) throws Exception;

	/**
	 * 解析数据流包
	 * @param InRead
	 * @return
	 */
	public Protocol analysisPacket(byte[] InRead);
	/**
	 * inputStream转Byte[]
	 * @param inReader Socket的输入流对象
	 * @return 流中的数据-byte[]
	 * @throws Exception
	 */
	public byte[] inputStream2Packet(InputStream inReader) throws Exception;
	/**
	 * byte数组转long
	 * @param b byte[]
	 * @return long
	 * @throws Exception
	 */
	public long bytes2long(byte[] b);

}