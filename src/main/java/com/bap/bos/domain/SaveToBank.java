package com.bap.bos.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 存款记录实体类
 * @author zhulong
 *
 */
public class SaveToBank implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String SaveToBank_StationNo;
	private String SaveToBank_Receipt;
	private BigDecimal SaveToBank_LastAmount;
	private BigDecimal SaveToBank_TodayAmount;
	private BigDecimal SaveToBank_CanSaved;
	private BigDecimal SaveToBank_ActSaved;
	private String SaveToBank_Bank;
	private String SaveToBank_StaffNo;
	private Date SaveToBank_SaveTime;
	private Date SaveToBank_OperateTime;
	private String SaveToBank_Remark;
	private String SaveToBank_IsSync;	
	private Date SaveToBank_SyncDate;
	public String getSaveToBank_StationNo() {
		return SaveToBank_StationNo;
	}
	public void setSaveToBank_StationNo(String saveToBankStationNo) {
		SaveToBank_StationNo = saveToBankStationNo;
	}
	public String getSaveToBank_Receipt() {
		return SaveToBank_Receipt;
	}
	public void setSaveToBank_Receipt(String saveToBankReceipt) {
		SaveToBank_Receipt = saveToBankReceipt;
	}
	public BigDecimal getSaveToBank_LastAmount() {
		return SaveToBank_LastAmount;
	}
	public void setSaveToBank_LastAmount(BigDecimal saveToBank_LastAmount) {
		SaveToBank_LastAmount = saveToBank_LastAmount;
	}
	public BigDecimal getSaveToBank_TodayAmount() {
		return SaveToBank_TodayAmount;
	}
	public void setSaveToBank_TodayAmount(BigDecimal saveToBank_TodayAmount) {
		SaveToBank_TodayAmount = saveToBank_TodayAmount;
	}
	public BigDecimal getSaveToBank_CanSaved() {
		return SaveToBank_CanSaved;
	}
	public void setSaveToBank_CanSaved(BigDecimal saveToBank_CanSaved) {
		SaveToBank_CanSaved = saveToBank_CanSaved;
	}
	public BigDecimal getSaveToBank_ActSaved() {
		return SaveToBank_ActSaved;
	}
	public void setSaveToBank_ActSaved(BigDecimal saveToBank_ActSaved) {
		SaveToBank_ActSaved = saveToBank_ActSaved;
	}
	public String getSaveToBank_Bank() {
		return SaveToBank_Bank;
	}
	public void setSaveToBank_Bank(String saveToBankBank) {
		SaveToBank_Bank = saveToBankBank;
	}
	public String getSaveToBank_StaffNo() {
		return SaveToBank_StaffNo;
	}
	public void setSaveToBank_StaffNo(String saveToBankStaffNo) {
		SaveToBank_StaffNo = saveToBankStaffNo;
	}
	public Date getSaveToBank_SaveTime() {
		return SaveToBank_SaveTime;
	}
	public void setSaveToBank_SaveTime(Date saveToBankSaveTime) {
		SaveToBank_SaveTime = saveToBankSaveTime;
	}
	public Date getSaveToBank_OperateTime() {
		return SaveToBank_OperateTime;
	}
	public void setSaveToBank_OperateTime(Date saveToBankOperateTime) {
		SaveToBank_OperateTime = saveToBankOperateTime;
	}
	public String getSaveToBank_Remark() {
		return SaveToBank_Remark;
	}
	public void setSaveToBank_Remark(String saveToBankRemark) {
		SaveToBank_Remark = saveToBankRemark;
	}
	public String getSaveToBank_IsSync() {
		return SaveToBank_IsSync;
	}
	public void setSaveToBank_IsSync(String saveToBankIsSync) {
		SaveToBank_IsSync = saveToBankIsSync;
	}
	public Date getSaveToBank_SyncDate() {
		return SaveToBank_SyncDate;
	}
	public void setSaveToBank_SyncDate(Date saveToBankSyncDate) {
		SaveToBank_SyncDate = saveToBankSyncDate;
	}
	
	
	
	


	
}
