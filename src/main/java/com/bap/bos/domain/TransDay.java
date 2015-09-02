package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 每日交易统计实体类
 * @author zhulong
 *
 */
public class TransDay implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String TransDay_TransDate;
	private String TransDay_StationNo;
	private String TransDay_CustomerType;
	private String TransDay_NozzleNo;
	private Double TransDay_TransMoney;
	private Double TransDay_PaidInMoney;
	private Double TransDay_TransVol;
	private String TransDay_TransCount;
	private String TransDay_IsSync;
	private Date TransDay_SyncDate;
	public String getTransDay_TransDate() {
		return TransDay_TransDate;
	}
	public void setTransDay_TransDate(String transDayTransDate) {
		TransDay_TransDate = transDayTransDate;
	}
	public String getTransDay_NozzleNo() {
		return TransDay_NozzleNo;
	}
	public void setTransDay_NozzleNo(String transDayNozzleNo) {
		TransDay_NozzleNo = transDayNozzleNo;
	}
	public String getTransDay_StationNo() {
		return TransDay_StationNo;
	}
	public void setTransDay_StationNo(String transDayStationNo) {
		TransDay_StationNo = transDayStationNo;
	}
	public String getTransDay_CustomerType() {
		return TransDay_CustomerType;
	}
	public void setTransDay_CustomerType(String transDayCustomerType) {
		TransDay_CustomerType = transDayCustomerType;
	}
	public Double getTransDay_TransMoney() {
		return TransDay_TransMoney;
	}
	public void setTransDay_TransMoney(Double transDayTransMoney) {
		TransDay_TransMoney = transDayTransMoney;
	}
	public Double getTransDay_PaidInMoney() {
		return TransDay_PaidInMoney;
	}
	public void setTransDay_PaidInMoney(Double transDayPaidInMoney) {
		TransDay_PaidInMoney = transDayPaidInMoney;
	}
	public Double getTransDay_TransVol() {
		return TransDay_TransVol;
	}
	public void setTransDay_TransVol(Double transDayTransVol) {
		TransDay_TransVol = transDayTransVol;
	}
	public String getTransDay_TransCount() {
		return TransDay_TransCount;
	}
	public void setTransDay_TransCount(String transDayTransCount) {
		TransDay_TransCount = transDayTransCount;
	}
	public String getTransDay_IsSync() {
		return TransDay_IsSync;
	}
	public void setTransDay_IsSync(String transDayIsSync) {
		TransDay_IsSync = transDayIsSync;
	}
	public Date getTransDay_SyncDate() {
		return TransDay_SyncDate;
	}
	public void setTransDay_SyncDate(Date transDaySyncDate) {
		TransDay_SyncDate = transDaySyncDate;
	}
	
	


	
}
