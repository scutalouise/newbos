package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 班别开班记录实体类
 * @author zhulong
 *
 */
public class Shift implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Shift_StationNo;
	private String Shift_ShiftDate;
	private String Shift_ShiftNo;
	private String Shift_CreateTime;
	private String Shift_StaffNo;
	private Date Shift_StartTime;
	private Date Shift_EndTime;
/*	private Date Shift_VerifyTime;
	private String Shift_VerifyStaffNo;
	private String Shift_VerifyStatus; */
	private String Shift_ShiftStatus;
	private String Shift_IsSync;
	private Date Shift_SyncDate;
	public String getShift_StationNo() {
		return Shift_StationNo;
	}
	public void setShift_StationNo(String shiftStationNo) {
		Shift_StationNo = shiftStationNo;
	}
	public String getShift_ShiftDate() {
		return Shift_ShiftDate;
	}
	public void setShift_ShiftDate(String shiftShiftDate) {
		Shift_ShiftDate = shiftShiftDate;
	}
	public String getShift_ShiftNo() {
		return Shift_ShiftNo;
	}
	public void setShift_ShiftNo(String shiftShiftNo) {
		Shift_ShiftNo = shiftShiftNo;
	}
	public String getShift_StaffNo() {
		return Shift_StaffNo;
	}
	public void setShift_StaffNo(String shiftStaffNo) {
		Shift_StaffNo = shiftStaffNo;
	}
	public Date getShift_StartTime() {
		return Shift_StartTime;
	}
	public void setShift_StartTime(Date shiftStartTime) {
		Shift_StartTime = shiftStartTime;
	}
	public Date getShift_EndTime() {
		return Shift_EndTime;
	}
	public void setShift_EndTime(Date shiftEndTime) {
		Shift_EndTime = shiftEndTime;
	}
	public String getShift_IsSync() {
		return Shift_IsSync;
	}
	public void setShift_IsSync(String shiftIsSync) {
		Shift_IsSync = shiftIsSync;
	}
	public Date getShift_SyncDate() {
		return Shift_SyncDate;
	}
	public void setShift_SyncDate(Date shiftSyncDate) {
		Shift_SyncDate = shiftSyncDate;
	}
/*	public Date getShift_VerifyTime() {
		return Shift_VerifyTime;
	}
	public void setShift_VerifyTime(Date shiftVerifyTime) {
		Shift_VerifyTime = shiftVerifyTime;
	}
	public String getShift_VerifyStaffNo() {
		return Shift_VerifyStaffNo;
	}
	public void setShift_VerifyStaffNo(String shiftVerifyStaffNo) {
		Shift_VerifyStaffNo = shiftVerifyStaffNo;
	}
	public String getShift_VerifyStatus() {
		return Shift_VerifyStatus;
	}
	public void setShift_VerifyStatus(String shiftVerifyStatus) {
		Shift_VerifyStatus = shiftVerifyStatus;
	}*/
	public String getShift_ShiftStatus() {
		return Shift_ShiftStatus;
	}
	public void setShift_ShiftStatus(String shiftShiftStatus) {
		Shift_ShiftStatus = shiftShiftStatus;
	}
	public String getShift_CreateTime() {
		return Shift_CreateTime;
	}
	public void setShift_CreateTime(String shiftCreateTime) {
		Shift_CreateTime = shiftCreateTime;
	}
	
}
