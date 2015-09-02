package com.bap.bos.action.enums;

/**
 * 与pos通讯的返回包状态枚举
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月1日
 */
public enum ResultOfRequireTankDataPacket {
	B1ACK_PacketNull("读取油罐信息为空，请检查FCC/液位通讯仪运行情况，重新请求“%s”。")
	,B1ACK_PacketPNoError("读取油罐信息错误，请检查FCC/液位通讯仪/Pos运行情况，重新请求“%s”。")
	,B1_OperateFalse("同时有其他油罐车对该罐进行卸油，“%s”申请被拒绝。")
	,NetError("网络错误。")
	,TimeOut("读取油罐数据过程超时。")
	,NoFindServer("未能发现远程POS服务，请检查连接地址是否正确或者POS是否正常运行。");
	
	private String remark;
	
	ResultOfRequireTankDataPacket(String remark){
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static ResultOfRequireTankDataPacket get(String text){
		switch(text){
		case "B1ACK_PacketNull":
			return B1ACK_PacketNull;
		case "B1ACK_PacketPNoError":
			return B1ACK_PacketPNoError;
		case "B1_OperateFalse":
			return B1_OperateFalse;
		}
			return null;
	}
	
	
}
