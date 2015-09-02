package com.bap.authority.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工-角色关联实体类
 * @author zhulong
 *
 */
public class SRRelation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer SRRelation_RoleNo;
	private String SRRelation_StaffNo;
	private String SRRelation_IsSync;
	private Date SRRelation_SyncDate;
	public Integer getSRRelation_RoleNo() {
		return SRRelation_RoleNo;
	}
	public void setSRRelation_RoleNo(Integer sRRelationRoleNo) {
		SRRelation_RoleNo = sRRelationRoleNo;
	}
	public String getSRRelation_StaffNo() {
		return SRRelation_StaffNo;
	}
	public void setSRRelation_StaffNo(String sRRelationStaffNo) {
		SRRelation_StaffNo = sRRelationStaffNo;
	}
	public String getSRRelation_IsSync() {
		return SRRelation_IsSync;
	}
	public void setSRRelation_IsSync(String sRRelationIsSync) {
		SRRelation_IsSync = sRRelationIsSync;
	}
	public Date getSRRelation_SyncDate() {
		return SRRelation_SyncDate;
	}
	public void setSRRelation_SyncDate(Date sRRelationSyncDate) {
		SRRelation_SyncDate = sRRelationSyncDate;
	}
	
	
	
	


	
}
