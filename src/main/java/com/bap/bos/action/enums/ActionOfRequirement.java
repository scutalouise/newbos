package com.bap.bos.action.enums;

/**
 * 请求POS返回相应数据的动作描述
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月1日
 */
public enum ActionOfRequirement {
	FINISH_UNLOADING_OIL("完成卸油")
	,BEGIN_UNLOADING_OIL("开始卸油");
	
	private String remark;
	
	ActionOfRequirement(String remark){
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
