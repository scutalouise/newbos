package com.bap.authority.util;

import java.io.Serializable;

/**
 * 角色-功能关系管理实体类
 * @author zhulong
 *
 */
public class RoleFunction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Function_FunctionNo;
	private Integer RFRelation_RoleNo;
	private String Function_FunctionName;
	private Integer Function_ParentNo;
	private Integer Function_SubNo;
	private String Function_Type;
	private String Function_URL;
	private String Function_State;
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
	public Integer getRFRelation_RoleNo() {
		return RFRelation_RoleNo;
	}
	public void setRFRelation_RoleNo(Integer rFRelationRoleNo) {
		RFRelation_RoleNo = rFRelationRoleNo;
	}
	public Integer getFunction_ParentNo() {
		return Function_ParentNo;
	}
	public void setFunction_ParentNo(Integer functionParentNo) {
		Function_ParentNo = functionParentNo;
	}
	public String getFunction_Type() {
		return Function_Type;
	}
	public void setFunction_Type(String functionType) {
		Function_Type = functionType;
	}
	public String getFunction_URL() {
		return Function_URL;
	}
	public void setFunction_URL(String functionURL) {
		Function_URL = functionURL;
	}
	public String getFunction_State() {
		return Function_State;
	}
	public void setFunction_State(String functionState) {
		Function_State = functionState;
	}
	public Integer getFunction_SubNo() {
		return Function_SubNo;
	}
	public void setFunction_SubNo(Integer functionSubNo) {
		Function_SubNo = functionSubNo;
	}

	
	
	
	


	
}
