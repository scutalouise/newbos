package com.bap.authority.util;

import java.io.Serializable;

/**
 * 员工-角色关系管理实体类
 * @author zhulong
 *
 */
public class StaffRole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Role_RoleNo;
	private String Role_RoleState;
	private String Role_RoleName;
	private String Role_SystemFlag;
	private String Role_Remark;
	
	public Integer getRole_RoleNo() {
		return Role_RoleNo;
	}
	public void setRole_RoleNo(Integer roleRoleNo) {
		Role_RoleNo = roleRoleNo;
	}
	public String getRole_RoleState() {
		return Role_RoleState;
	}
	public void setRole_RoleState(String roleRoleState) {
		Role_RoleState = roleRoleState;
	}
	public String getRole_RoleName() {
		return Role_RoleName;
	}
	public void setRole_RoleName(String roleRoleName) {
		Role_RoleName = roleRoleName;
	}
	public String getRole_SystemFlag() {
		return Role_SystemFlag;
	}
	public void setRole_SystemFlag(String roleSystemFlag) {
		Role_SystemFlag = roleSystemFlag;
	}
	public String getRole_Remark() {
		return Role_Remark;
	}
	public void setRole_Remark(String roleRemark) {
		Role_Remark = roleRemark;
	}

	

	
	
	
	


	
}
