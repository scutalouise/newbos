package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 交易类型实体类
 * @author zhulong
 *
 */
public class TransType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String TransType_Type;
	private String TransType_Desc;
	public String getTransType_Type() {
		return TransType_Type;
	}
	public void setTransType_Type(String transTypeType) {
		TransType_Type = transTypeType;
	}
	public String getTransType_Desc() {
		return TransType_Desc;
	}
	public void setTransType_Desc(String transTypeDesc) {
		TransType_Desc = transTypeDesc;
	}
	
	

	
}
