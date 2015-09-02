package com.bap.authority.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体类
 * @author zhulong
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Role_RoleNo;
	private String Role_RoleState;
	private String Role_RoleName;
	private Date Role_RoleSetTime;
	private String Role_SystemFlag;
	private String Role_GroupNo;
	private String Role_Remark;
	private String Role_IsSync;
	private Date Role_SyncDate;
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
	public Date getRole_RoleSetTime() {
		return Role_RoleSetTime;
	}
	public void setRole_RoleSetTime(Date roleRoleSetTime) {
		Role_RoleSetTime = roleRoleSetTime;
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
	public String getRole_IsSync() {
		return Role_IsSync;
	}
	public void setRole_IsSync(String roleIsSync) {
		Role_IsSync = roleIsSync;
	}
	public Date getRole_SyncDate() {
		return Role_SyncDate;
	}
	public void setRole_SyncDate(Date roleSyncDate) {
		Role_SyncDate = roleSyncDate;
	}
	public String getRole_GroupNo() {
		return Role_GroupNo;
	}
	public void setRole_GroupNo(String roleGroupNo) {
		Role_GroupNo = roleGroupNo;
	}
	


	
}
