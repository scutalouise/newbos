package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 客户类型实体类
 * @author zhulong
 *
 */
public class CustomerType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String CustomerType_ID;
	private String CustomerType_Desc;
	public String getCustomerType_ID() {
		return CustomerType_ID;
	}
	public void setCustomerType_ID(String customerTypeID) {
		CustomerType_ID = customerTypeID;
	}
	public String getCustomerType_Desc() {
		return CustomerType_Desc;
	}
	public void setCustomerType_Desc(String customerTypeDesc) {
		CustomerType_Desc = customerTypeDesc;
	}
	

	
}
