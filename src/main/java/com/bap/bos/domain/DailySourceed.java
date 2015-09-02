package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 每日能耗实体类
 * @author zhulong
 *
 */
public class DailySourceed implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String DailySourced_StationNo;
	private Date DailySourced_CurrDate;
	
	private float DailySourced_ElecNum;
	private String DailySourced_ElecNumOver;
	private float DailySourced_WaterNum;
	private String DailySourced_WaterNumOver;
	private String DailySourced_IsSync;
	private Date DailySourced_SyncDate;
	public String getDailySourced_StationNo() {
		return DailySourced_StationNo;
	}
	public void setDailySourced_StationNo(String dailySourcedStationNo) {
		DailySourced_StationNo = dailySourcedStationNo;
	}
	public Date getDailySourced_CurrDate() {
		return DailySourced_CurrDate;
	}
	public void setDailySourced_CurrDate(Date dailySourcedCurrDate) {
		DailySourced_CurrDate = dailySourcedCurrDate;
	}
	public float getDailySourced_ElecNum() {
		return DailySourced_ElecNum;
	}
	public void setDailySourced_ElecNum(float dailySourcedElecNum) {
		DailySourced_ElecNum = dailySourcedElecNum;
	}
	public String getDailySourced_ElecNumOver() {
		return DailySourced_ElecNumOver;
	}
	public void setDailySourced_ElecNumOver(String dailySourcedElecNumOver) {
		DailySourced_ElecNumOver = dailySourcedElecNumOver;
	}
	public float getDailySourced_WaterNum() {
		return DailySourced_WaterNum;
	}
	public void setDailySourced_WaterNum(float dailySourcedWaterNum) {
		DailySourced_WaterNum = dailySourcedWaterNum;
	}
	public String getDailySourced_WaterNumOver() {
		return DailySourced_WaterNumOver;
	}
	public void setDailySourced_WaterNumOver(String dailySourcedWaterNumOver) {
		DailySourced_WaterNumOver = dailySourcedWaterNumOver;
	}
	public String getDailySourced_IsSync() {
		return DailySourced_IsSync;
	}
	public void setDailySourced_IsSync(String dailySourcedIsSync) {
		DailySourced_IsSync = dailySourcedIsSync;
	}
	public Date getDailySourced_SyncDate() {
		return DailySourced_SyncDate;
	}
	public void setDailySourced_SyncDate(Date dailySourcedSyncDate) {
		DailySourced_SyncDate = dailySourcedSyncDate;
	}
	
	
	
	


	
	


	
}
