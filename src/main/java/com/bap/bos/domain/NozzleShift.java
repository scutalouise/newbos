package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 油枪交班泵码记录
 * @author zhulong
 *
 */
public class NozzleShift implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String NozzleShift_StationNo;
	private String NozzleShift_ShiftDate;
	private String NozzleShift_ShiftNo;
	private String NozzleShift_NozzleId;
	private String NozzleShift_CreateTime;
	private String NozzleShift_ProductNum;
	private double NozzleShift_StartVol;
	private double NozzleShift_EndVol;
	private String NozzleShift_IsSync;
	private Date NozzleShift_SyncDate;
	public String getNozzleShift_StationNo() {
		return NozzleShift_StationNo;
	}
	public void setNozzleShift_StationNo(String nozzleShiftStationNo) {
		NozzleShift_StationNo = nozzleShiftStationNo;
	}
	public String getNozzleShift_ShiftDate() {
		return NozzleShift_ShiftDate;
	}
	public void setNozzleShift_ShiftDate(String nozzleShiftShiftDate) {
		NozzleShift_ShiftDate = nozzleShiftShiftDate;
	}
	public String getNozzleShift_ShiftNo() {
		return NozzleShift_ShiftNo;
	}
	public void setNozzleShift_ShiftNo(String nozzleShiftShiftNo) {
		NozzleShift_ShiftNo = nozzleShiftShiftNo;
	}
	public String getNozzleShift_NozzleId() {
		return NozzleShift_NozzleId;
	}
	public void setNozzleShift_NozzleId(String nozzleShiftNozzleId) {
		NozzleShift_NozzleId = nozzleShiftNozzleId;
	}
	public String getNozzleShift_ProductNum() {
		return NozzleShift_ProductNum;
	}
	public void setNozzleShift_ProductNum(String nozzleShiftProductNum) {
		NozzleShift_ProductNum = nozzleShiftProductNum;
	}
	public double getNozzleShift_StartVol() {
		return NozzleShift_StartVol;
	}
	public void setNozzleShift_StartVol(double nozzleShiftStartVol) {
		NozzleShift_StartVol = nozzleShiftStartVol;
	}
	public double getNozzleShift_EndVol() {
		return NozzleShift_EndVol;
	}
	public void setNozzleShift_EndVol(double nozzleShiftEndVol) {
		NozzleShift_EndVol = nozzleShiftEndVol;
	}
	public String getNozzleShift_IsSync() {
		return NozzleShift_IsSync;
	}
	public void setNozzleShift_IsSync(String nozzleShiftIsSync) {
		NozzleShift_IsSync = nozzleShiftIsSync;
	}
	public Date getNozzleShift_SyncDate() {
		return NozzleShift_SyncDate;
	}
	public void setNozzleShift_SyncDate(Date nozzleShiftSyncDate) {
		NozzleShift_SyncDate = nozzleShiftSyncDate;
	}
	public String getNozzleShift_CreateTime() {
		return NozzleShift_CreateTime;
	}
	public void setNozzleShift_CreateTime(String nozzleShiftCreateTime) {
		NozzleShift_CreateTime = nozzleShiftCreateTime;
	}
	
	
}
