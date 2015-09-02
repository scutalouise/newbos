package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 日结记录实体类
 * @author zhulong
 *
 */
public class DayTransVerify implements Serializable {
	private static final long serialVersionUID = 1L;
	private String DayTransV_Time;
	private String DayTransV_Status;
	private String DayTransV_VerifyStaffNo;
	private Date DayTransV_VerifyTime;
	private String DayTransV_StationNo;
	private String DayTransV_IsSync;
	private Date DayTransV_SyncDate;
	private Boolean DayTransV_AutoSure = false;
	
	public String getDayTransV_Time() {
		return DayTransV_Time;
	}
	public void setDayTransV_Time(String dayTransVTime) {
		DayTransV_Time = dayTransVTime;
	}
	public String getDayTransV_Status() {
		return DayTransV_Status;
	}
	public void setDayTransV_Status(String dayTransVStatus) {
		DayTransV_Status = dayTransVStatus;
	}
	public String getDayTransV_VerifyStaffNo() {
		return DayTransV_VerifyStaffNo;
	}
	public void setDayTransV_VerifyStaffNo(String dayTransVVerifyStaffNo) {
		DayTransV_VerifyStaffNo = dayTransVVerifyStaffNo;
	}

	public Date getDayTransV_VerifyTime() {
		return DayTransV_VerifyTime;
	}
	public void setDayTransV_VerifyTime(Date dayTransVVerifyTime) {
		DayTransV_VerifyTime = dayTransVVerifyTime;
	}
	public String getDayTransV_StationNo() {
		return DayTransV_StationNo;
	}
	public void setDayTransV_StationNo(String dayTransVStationNo) {
		DayTransV_StationNo = dayTransVStationNo;
	}
	public String getDayTransV_IsSync() {
		return DayTransV_IsSync;
	}
	public void setDayTransV_IsSync(String dayTransVIsSync) {
		DayTransV_IsSync = dayTransVIsSync;
	}
	public Date getDayTransV_SyncDate() {
		return DayTransV_SyncDate;
	}
	public void setDayTransV_SyncDate(Date dayTransVSyncDate) {
		DayTransV_SyncDate = dayTransVSyncDate;
	}
	public Boolean getDayTransV_AutoSure() {
		return DayTransV_AutoSure;
	}
	public void setDayTransV_AutoSure(Boolean dayTransV_AutoSure) {
		DayTransV_AutoSure = dayTransV_AutoSure;
	}

	
}
