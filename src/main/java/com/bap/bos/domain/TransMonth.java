package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 每月交易统计实体类
 * @author zhulong
 *
 */
public class TransMonth implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String TransMonth_TransDate;
	private String TransMonth_StationNo;
	private String TransMonth_CustomerType;
	private String TransMonth_NozzleNo;
	private double TransMonth_TransMoney;
	private double TransMonth_PaidInMoney;
	private double TransMonth_TransVol;
	private String TransMonth_TransCount;
	private String TransMonth_IsSync;
	private Date TransMonth_SyncDate;
	public String getTransMonth_TransDate() {
		return TransMonth_TransDate;
	}
	public void setTransMonth_TransDate(String transMonthTransDate) {
		TransMonth_TransDate = transMonthTransDate;
	}
	public String getTransMonth_StationNo() {
		return TransMonth_StationNo;
	}
	public void setTransMonth_StationNo(String transMonthStationNo) {
		TransMonth_StationNo = transMonthStationNo;
	}
	public String getTransMonth_CustomerType() {
		return TransMonth_CustomerType;
	}
	public void setTransMonth_CustomerType(String transMonthCustomerType) {
		TransMonth_CustomerType = transMonthCustomerType;
	}
	public double getTransMonth_TransMoney() {
		return TransMonth_TransMoney;
	}
	public void setTransMonth_TransMoney(double transMonthTransMoney) {
		TransMonth_TransMoney = transMonthTransMoney;
	}
	public double getTransMonth_PaidInMoney() {
		return TransMonth_PaidInMoney;
	}
	public void setTransMonth_PaidInMoney(double transMonthPaidInMoney) {
		TransMonth_PaidInMoney = transMonthPaidInMoney;
	}
	public double getTransMonth_TransVol() {
		return TransMonth_TransVol;
	}
	public void setTransMonth_TransVol(double transMonthTransVol) {
		TransMonth_TransVol = transMonthTransVol;
	}
	public String getTransMonth_IsSync() {
		return TransMonth_IsSync;
	}
	public void setTransMonth_IsSync(String transMonthIsSync) {
		TransMonth_IsSync = transMonthIsSync;
	}
	public Date getTransMonth_SyncDate() {
		return TransMonth_SyncDate;
	}
	public void setTransMonth_SyncDate(Date transMonthSyncDate) {
		TransMonth_SyncDate = transMonthSyncDate;
	}
	public String getTransMonth_NozzleNo() {
		return TransMonth_NozzleNo;
	}
	public void setTransMonth_NozzleNo(String transMonthNozzleNo) {
		TransMonth_NozzleNo = transMonthNozzleNo;
	}
	public String getTransMonth_TransCount() {
		return TransMonth_TransCount;
	}
	public void setTransMonth_TransCount(String transMonthTransCount) {
		TransMonth_TransCount = transMonthTransCount;
	}
	
	
	
	


	
}
