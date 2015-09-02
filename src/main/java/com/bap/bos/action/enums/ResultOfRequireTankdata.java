package com.bap.bos.action.enums;

/**
 * 与pos通讯的返回标志枚举
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月1日
 */
public enum ResultOfRequireTankdata {
	r_119("液位仪数据读取成功！")
	,r_01("由于液位仪故障、FCC故障或通讯故障，读取液位仪数据超时，“%s”请求失败！")
	,r_0("由于油罐对应油枪出现脱机、加油中或其他原因，锁枪超时，“%s”请求失败！");
	
	private String remark;
	
	ResultOfRequireTankdata(String remark){
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
