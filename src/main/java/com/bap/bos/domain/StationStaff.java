package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

public class StationStaff implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String StationStaff_StationNo;
	private String StationStaff_StaffNo;
	private String StationStaff_StaffStatus;
	private String StationStaff_IsSync;
	private Date StationStaff_SyncDate;
	public String getStationStaff_StationNo() {
		return StationStaff_StationNo;
	}
	public void setStationStaff_StationNo(String stationStaffStationNo) {
		StationStaff_StationNo = stationStaffStationNo;
	}
	public String getStationStaff_StaffNo() {
		return StationStaff_StaffNo;
	}
	public void setStationStaff_StaffNo(String stationStaffStaffNo) {
		StationStaff_StaffNo = stationStaffStaffNo;
	}
	public String getStationStaff_StaffStatus() {
		return StationStaff_StaffStatus;
	}
	public void setStationStaff_StaffStatus(String stationStaffStaffStatus) {
		StationStaff_StaffStatus = stationStaffStaffStatus;
	}
	public String getStationStaff_IsSync() {
		return StationStaff_IsSync;
	}
	public void setStationStaff_IsSync(String stationStaffIsSync) {
		StationStaff_IsSync = stationStaffIsSync;
	}
	public Date getStationStaff_SyncDate() {
		return StationStaff_SyncDate;
	}
	public void setStationStaff_SyncDate(Date stationStaffSyncDate) {
		StationStaff_SyncDate = stationStaffSyncDate;
	}
	
	
}
