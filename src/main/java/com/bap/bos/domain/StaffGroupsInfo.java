package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工上下班信息实体类
 * @author zhulong
 *
 */
public class StaffGroupsInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String StaffGroupInfo_ShiftNo;
	private String StaffGroupInfo_StationNo;
	private String StaffGroupInfo_StaffNo;
	private Date   StaffGroupInfo_StartWorkTime;
	private Date   StaffGroupInfo_OffWorkTime;
	private String StaffGroupInfo_IsSync;
	private Date   StaffGroupInfo_SyncDate;
	public String getStaffGroupInfo_ShiftNo() {
		return StaffGroupInfo_ShiftNo;
	}
	public void setStaffGroupInfo_ShiftNo(String staffGroupInfoShiftNo) {
		StaffGroupInfo_ShiftNo = staffGroupInfoShiftNo;
	}
	public String getStaffGroupInfo_StationNo() {
		return StaffGroupInfo_StationNo;
	}
	public void setStaffGroupInfo_StationNo(String staffGroupInfoStationNo) {
		StaffGroupInfo_StationNo = staffGroupInfoStationNo;
	}
	public String getStaffGroupInfo_StaffNo() {
		return StaffGroupInfo_StaffNo;
	}
	public void setStaffGroupInfo_StaffNo(String staffGroupInfoStaffNo) {
		StaffGroupInfo_StaffNo = staffGroupInfoStaffNo;
	}
	public Date getStaffGroupInfo_StartWorkTime() {
		return StaffGroupInfo_StartWorkTime;
	}
	public void setStaffGroupInfo_StartWorkTime(Date staffGroupInfoStartWorkTime) {
		StaffGroupInfo_StartWorkTime = staffGroupInfoStartWorkTime;
	}
	public Date getStaffGroupInfo_OffWorkTime() {
		return StaffGroupInfo_OffWorkTime;
	}
	public void setStaffGroupInfo_OffWorkTime(Date staffGroupInfoOffWorkTime) {
		StaffGroupInfo_OffWorkTime = staffGroupInfoOffWorkTime;
	}
	public String getStaffGroupInfo_IsSync() {
		return StaffGroupInfo_IsSync;
	}
	public void setStaffGroupInfo_IsSync(String staffGroupInfoIsSync) {
		StaffGroupInfo_IsSync = staffGroupInfoIsSync;
	}
	public Date getStaffGroupInfo_SyncDate() {
		return StaffGroupInfo_SyncDate;
	}
	public void setStaffGroupInfo_SyncDate(Date staffGroupInfoSyncDate) {
		StaffGroupInfo_SyncDate = staffGroupInfoSyncDate;
	}
	
	
	


	
}
