package com.bap.authority.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统功能实体类
 * @author zhulong
 *
 */
public class Function implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Function_FunctionNo;
	private String Function_FunctionName;
	private String Function_FunctionState;
	private String Function_Type;
	private Integer Function_ParentNo;
	private Integer Function_SubNo;
	private String Function_URL;
	private Date Function_CreateDate;
	private String Function_SystemFlag;
	private String Function_IsSync;
	private Date Function_SyncDate;
	
	
	public Integer getFunction_FunctionNo() {
		return Function_FunctionNo;
	}
	public void setFunction_FunctionNo(Integer functionFunctionNo) {
		Function_FunctionNo = functionFunctionNo;
	}
	public String getFunction_FunctionName() {
		return Function_FunctionName;
	}
	public void setFunction_FunctionName(String functionFunctionName) {
		Function_FunctionName = functionFunctionName;
	}
	public String getFunction_FunctionState() {
		return Function_FunctionState;
	}
	public void setFunction_FunctionState(String functionFunctionState) {
		Function_FunctionState = functionFunctionState;
	}
	public String getFunction_Type() {
		return Function_Type;
	}
	public void setFunction_Type(String functionType) {
		Function_Type = functionType;
	}
	public Integer getFunction_ParentNo() {
		return Function_ParentNo;
	}
	public void setFunction_ParentNo(Integer functionParentNo) {
		Function_ParentNo = functionParentNo;
	}
	public Integer getFunction_SubNo() {
		return Function_SubNo;
	}
	public void setFunction_SubNo(Integer functionSubNo) {
		Function_SubNo = functionSubNo;
	}
	public String getFunction_URL() {
		return Function_URL;
	}
	public void setFunction_URL(String functionURL) {
		Function_URL = functionURL;
	}
	public Date getFunction_CreateDate() {
		return Function_CreateDate;
	}
	public void setFunction_CreateDate(Date functionCreateDate) {
		Function_CreateDate = functionCreateDate;
	}
	public String getFunction_SystemFlag() {
		return Function_SystemFlag;
	}
	public void setFunction_SystemFlag(String functionSystemFlag) {
		Function_SystemFlag = functionSystemFlag;
	}
	public String getFunction_IsSync() {
		return Function_IsSync;
	}
	public void setFunction_IsSync(String functionIsSync) {
		Function_IsSync = functionIsSync;
	}
	public Date getFunction_SyncDate() {
		return Function_SyncDate;
	}
	public void setFunction_SyncDate(Date functionSyncDate) {
		Function_SyncDate = functionSyncDate;
	}
	
	

	
}
