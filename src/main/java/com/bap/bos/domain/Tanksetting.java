package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 油/气罐设定实体类
 * @author zhulong
 *
 */
public class Tanksetting implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Tanksetting_StationNo;
	private String Tanksetting_Type;
	private String Tanksetting_TankNum;
	private String Tanksetting_TotalVolume;
	private String Tanksetting_ProductNum;
	private String Tanksetting_HHAlarm;
	private String Tanksetting_HDAlarm;
	private String Tanksetting_WHAlarm;
	private String Tanksetting_GasAlarm;
	private String Tanksetting_IsSync;
	private Date Tanksetting_SyncDate;
	private Integer Tanksetting_ManualVal;
	
	
	public String getTanksetting_StationNo() {
		return Tanksetting_StationNo;
	}
	public void setTanksetting_StationNo(String tanksettingStationNo) {
		Tanksetting_StationNo = tanksettingStationNo;
	}
	public String getTanksetting_Type() {
		return Tanksetting_Type;
	}
	public void setTanksetting_Type(String tanksettingType) {
		Tanksetting_Type = tanksettingType;
	}
	public String getTanksetting_TankNum() {
		return Tanksetting_TankNum;
	}
	public void setTanksetting_TankNum(String tanksettingTankNum) {
		Tanksetting_TankNum = tanksettingTankNum;
	}
	public String getTanksetting_TotalVolume() {
		return Tanksetting_TotalVolume;
	}
	public void setTanksetting_TotalVolume(String tanksettingTotalVolume) {
		Tanksetting_TotalVolume = tanksettingTotalVolume;
	}
	public String getTanksetting_ProductNum() {
		return Tanksetting_ProductNum;
	}
	public void setTanksetting_ProductNum(String tanksettingProductNum) {
		Tanksetting_ProductNum = tanksettingProductNum;
	}
	public String getTanksetting_HHAlarm() {
		return Tanksetting_HHAlarm;
	}
	public void setTanksetting_HHAlarm(String tanksettingHHAlarm) {
		Tanksetting_HHAlarm = tanksettingHHAlarm;
	}
	public String getTanksetting_HDAlarm() {
		return Tanksetting_HDAlarm;
	}
	public void setTanksetting_HDAlarm(String tanksettingHDAlarm) {
		Tanksetting_HDAlarm = tanksettingHDAlarm;
	}
	public String getTanksetting_GasAlarm() {
		return Tanksetting_GasAlarm;
	}
	public void setTanksetting_GasAlarm(String tanksettingGasAlarm) {
		Tanksetting_GasAlarm = tanksettingGasAlarm;
	}
	public String getTanksetting_IsSync() {
		return Tanksetting_IsSync;
	}
	public void setTanksetting_IsSync(String tanksettingIsSync) {
		Tanksetting_IsSync = tanksettingIsSync;
	}
	public Date getTanksetting_SyncDate() {
		return Tanksetting_SyncDate;
	}
	public void setTanksetting_SyncDate(Date tanksettingSyncDate) {
		Tanksetting_SyncDate = tanksettingSyncDate;
	}
	public String getTanksetting_WHAlarm() {
		return Tanksetting_WHAlarm;
	}
	public void setTanksetting_WHAlarm(String tanksettingWHAlarm) {
		Tanksetting_WHAlarm = tanksettingWHAlarm;
	}
	public Integer getTanksetting_ManualVal() {
		return Tanksetting_ManualVal;
	}
	public void setTanksetting_ManualVal(Integer tanksetting_ManualVal) {
		Tanksetting_ManualVal = tanksetting_ManualVal;
	}
	
	public Tanksetting(String tanksetting_StationNo, String tanksetting_TankNum) {
		Tanksetting_StationNo = tanksetting_StationNo;
		Tanksetting_TankNum = tanksetting_TankNum;
	}
	
	public Tanksetting() {
	
	}

	
}
