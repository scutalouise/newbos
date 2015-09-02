package com.bap.authority.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色功能关联实体类
 * @author zhulong
 *
 */
public class RFRelation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer RFRelation_RoleNo;
	private Integer RFRelation_FunctionNo;
	private String RFRelation_IsSync;
	private Date RFRelation_SyncDate;
	public String getRFRelation_IsSync() {
		return RFRelation_IsSync;
	}
	public void setRFRelation_IsSync(String rFRelationIsSync) {
		RFRelation_IsSync = rFRelationIsSync;
	}
	public Date getRFRelation_SyncDate() {
		return RFRelation_SyncDate;
	}
	public void setRFRelation_SyncDate(Date rFRelationSyncDate) {
		RFRelation_SyncDate = rFRelationSyncDate;
	}
	public Integer getRFRelation_RoleNo() {
		return RFRelation_RoleNo;
	}
	public void setRFRelation_RoleNo(Integer rFRelationRoleNo) {
		RFRelation_RoleNo = rFRelationRoleNo;
	}
	public Integer getRFRelation_FunctionNo() {
		return RFRelation_FunctionNo;
	}
	public void setRFRelation_FunctionNo(Integer rFRelationFunctionNo) {
		RFRelation_FunctionNo = rFRelationFunctionNo;
	}
	
	
	


	
}
