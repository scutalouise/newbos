package com.bap.authority.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 组别实体类
 * @author zhulong
 *
 */
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Group_GroupNo;
	private String Group_GroupState;
	private String Group_GroupName;
	private Date Group_GroupSetTime;
	private String Group_SystemFlag;
	private String Group_Remark;
	private String Group_IsSync;
	private Date Group_SyncDate;
	public Integer getGroup_GroupNo() {
		return Group_GroupNo;
	}
	public void setGroup_GroupNo(Integer groupGroupNo) {
		Group_GroupNo = groupGroupNo;
	}
	public String getGroup_GroupState() {
		return Group_GroupState;
	}
	public void setGroup_GroupState(String groupGroupState) {
		Group_GroupState = groupGroupState;
	}
	public String getGroup_GroupName() {
		return Group_GroupName;
	}
	public void setGroup_GroupName(String groupGroupName) {
		Group_GroupName = groupGroupName;
	}
	public Date getGroup_GroupSetTime() {
		return Group_GroupSetTime;
	}
	public void setGroup_GroupSetTime(Date groupGroupSetTime) {
		Group_GroupSetTime = groupGroupSetTime;
	}
	public String getGroup_SystemFlag() {
		return Group_SystemFlag;
	}
	public void setGroup_SystemFlag(String groupSystemFlag) {
		Group_SystemFlag = groupSystemFlag;
	}
	public String getGroup_Remark() {
		return Group_Remark;
	}
	public void setGroup_Remark(String groupRemark) {
		Group_Remark = groupRemark;
	}
	public String getGroup_IsSync() {
		return Group_IsSync;
	}
	public void setGroup_IsSync(String groupIsSync) {
		Group_IsSync = groupIsSync;
	}
	public Date getGroup_SyncDate() {
		return Group_SyncDate;
	}
	public void setGroup_SyncDate(Date groupSyncDate) {
		Group_SyncDate = groupSyncDate;
	}
	
	

}
