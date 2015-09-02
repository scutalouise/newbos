package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有交易实体类
 * @author zhulong
 *
 */
public class TransItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String TransItem_TransNo;
	private String TransItem_TransDate;
	private String TransItem_StationNo;
	private String TransItem_ShiftNo;
	private String TransItem_ShiftDate;
	private String TransItem_NozzleNo;
	private String TransItem_ItemCode;
	private float TransItem_SellPrice;
	private float TransItem_PayVol;
	private float TransItem_PayMoney;
	private String TransItem_TransStat;
	private Double TransItem_PT_Begin;
	private Double TransItem_PT_End;
	private String TransItem_Tend;
	private String TransItem_DitNo;
	private Date TransItem_OpenTime;
	private Date TransItem_StopTime;
	private Float TransItem_Temp;
	private Float TransItem_Density;
	private Float TransItem_StartPressure;
	private Float TransItem_EndPressure;
	private String TransItem_IsSync;
	private Date TransItem_SyncDate;
	
	public String getTransItem_TransNo() {
		return TransItem_TransNo;
	}
	public void setTransItem_TransNo(String transItemTransNo) {
		TransItem_TransNo = transItemTransNo;
	}
	public String getTransItem_TransDate() {
		return TransItem_TransDate;
	}
	public void setTransItem_TransDate(String transItem_TransDate) {
		TransItem_TransDate = transItem_TransDate;
	}
	public String getTransItem_StationNo() {
		return TransItem_StationNo;
	}
	public void setTransItem_StationNo(String transItemStationNo) {
		TransItem_StationNo = transItemStationNo;
	}
	public String getTransItem_ShiftNo() {
		return TransItem_ShiftNo;
	}
	public void setTransItem_ShiftNo(String transItemShiftNo) {
		TransItem_ShiftNo = transItemShiftNo;
	}
	public String getTransItem_ShiftDate() {
		return TransItem_ShiftDate;
	}
	public void setTransItem_ShiftDate(String transItemShiftDate) {
		TransItem_ShiftDate = transItemShiftDate;
	}
	public String getTransItem_ItemCode() {
		return TransItem_ItemCode;
	}
	public void setTransItem_ItemCode(String transItemItemCode) {
		TransItem_ItemCode = transItemItemCode;
	}
	public float getTransItem_SellPrice() {
		return TransItem_SellPrice;
	}
	public void setTransItem_SellPrice(float transItemSellPrice) {
		TransItem_SellPrice = transItemSellPrice;
	}
	public float getTransItem_PayVol() {
		return TransItem_PayVol;
	}
	public void setTransItem_PayVol(float transItemPayVol) {
		TransItem_PayVol = transItemPayVol;
	}
	public float getTransItem_PayMoney() {
		return TransItem_PayMoney;
	}
	public void setTransItem_PayMoney(float transItemPayMoney) {
		TransItem_PayMoney = transItemPayMoney;
	}
	public String getTransItem_TransStat() {
		return TransItem_TransStat;
	}
	public void setTransItem_TransStat(String transItemTransStat) {
		TransItem_TransStat = transItemTransStat;
	}
	public String getTransItem_Tend() {
		return TransItem_Tend;
	}
	public void setTransItem_Tend(String transItemTend) {
		TransItem_Tend = transItemTend;
	}
	public String getTransItem_NozzleNo() {
		return TransItem_NozzleNo;
	}
	public void setTransItem_NozzleNo(String transItemNozzleNo) {
		TransItem_NozzleNo = transItemNozzleNo;
	}
	public Double getTransItem_PT_Begin() {
		return TransItem_PT_Begin;
	}
	public void setTransItem_PT_Begin(Double transItemPTBegin) {
		TransItem_PT_Begin = transItemPTBegin;
	}
	public Double getTransItem_PT_End() {
		return TransItem_PT_End;
	}
	public void setTransItem_PT_End(Double transItemPTEnd) {
		TransItem_PT_End = transItemPTEnd;
	}
	public String getTransItem_DitNo() {
		return TransItem_DitNo;
	}
	public void setTransItem_DitNo(String transItemDitNo) {
		TransItem_DitNo = transItemDitNo;
	}
	public Date getTransItem_OpenTime() {
		return TransItem_OpenTime;
	}
	public void setTransItem_OpenTime(Date transItemOpenTime) {
		TransItem_OpenTime = transItemOpenTime;
	}
	public Date getTransItem_StopTime() {
		return TransItem_StopTime;
	}
	public void setTransItem_StopTime(Date transItemStopTime) {
		TransItem_StopTime = transItemStopTime;
	}
	public Float getTransItem_Temp() {
		return TransItem_Temp;
	}
	public void setTransItem_Temp(Float transItemTemp) {
		TransItem_Temp = transItemTemp;
	}
	public Float getTransItem_Density() {
		return TransItem_Density;
	}
	public void setTransItem_Density(Float transItemDensity) {
		TransItem_Density = transItemDensity;
	}
	public Float getTransItem_StartPressure() {
		return TransItem_StartPressure;
	}
	public void setTransItem_StartPressure(Float transItemStartPressure) {
		TransItem_StartPressure = transItemStartPressure;
	}
	public Float getTransItem_EndPressure() {
		return TransItem_EndPressure;
	}
	public void setTransItem_EndPressure(Float transItemEndPressure) {
		TransItem_EndPressure = transItemEndPressure;
	}
	public String getTransItem_IsSync() {
		return TransItem_IsSync;
	}
	public void setTransItem_IsSync(String transItemIsSync) {
		TransItem_IsSync = transItemIsSync;
	}
	public Date getTransItem_SyncDate() {
		return TransItem_SyncDate;
	}
	public void setTransItem_SyncDate(Date transItemSyncDate) {
		TransItem_SyncDate = transItemSyncDate;
	}


	

	
	
}
