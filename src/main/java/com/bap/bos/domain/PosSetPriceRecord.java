package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售价格变价记录实体类
 * @author zhulong
 *
 */
public class PosSetPriceRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String PosSetPriceRecord_PriceNum;
	private String PosSetPriceRecord_StationNo;
	private String PosSetPriceRecord_StaffNo;
	private String PosSetPriceRecord_ProductNum;
	private String PosSetPriceRecord_Pump;
	private Date PosSetPriceRecord_EffDate;
	private Date PosSetPriceRecord_ChDate;
	private float PosSetPriceRecord_SellPrice;
	private String PosSetPriceRecord_Result;
	public String getPosSetPriceRecord_PriceNum() {
		return PosSetPriceRecord_PriceNum;
	}
	public void setPosSetPriceRecord_PriceNum(String posSetPriceRecordPriceNum) {
		PosSetPriceRecord_PriceNum = posSetPriceRecordPriceNum;
	}
	public String getPosSetPriceRecord_StationNo() {
		return PosSetPriceRecord_StationNo;
	}
	public void setPosSetPriceRecord_StationNo(String posSetPriceRecordStationNo) {
		PosSetPriceRecord_StationNo = posSetPriceRecordStationNo;
	}
	public String getPosSetPriceRecord_StaffNo() {
		return PosSetPriceRecord_StaffNo;
	}
	public void setPosSetPriceRecord_StaffNo(String posSetPriceRecordStaffNo) {
		PosSetPriceRecord_StaffNo = posSetPriceRecordStaffNo;
	}
	public String getPosSetPriceRecord_ProductNum() {
		return PosSetPriceRecord_ProductNum;
	}
	public void setPosSetPriceRecord_ProductNum(String posSetPriceRecordProductNum) {
		PosSetPriceRecord_ProductNum = posSetPriceRecordProductNum;
	}
	public String getPosSetPriceRecord_Pump() {
		return PosSetPriceRecord_Pump;
	}
	public void setPosSetPriceRecord_Pump(String posSetPriceRecordPump) {
		PosSetPriceRecord_Pump = posSetPriceRecordPump;
	}
	public Date getPosSetPriceRecord_EffDate() {
		return PosSetPriceRecord_EffDate;
	}
	public void setPosSetPriceRecord_EffDate(Date posSetPriceRecordEffDate) {
		PosSetPriceRecord_EffDate = posSetPriceRecordEffDate;
	}
	public Date getPosSetPriceRecord_ChDate() {
		return PosSetPriceRecord_ChDate;
	}
	public void setPosSetPriceRecord_ChDate(Date posSetPriceRecordChDate) {
		PosSetPriceRecord_ChDate = posSetPriceRecordChDate;
	}
	public float getPosSetPriceRecord_SellPrice() {
		return PosSetPriceRecord_SellPrice;
	}
	public void setPosSetPriceRecord_SellPrice(float posSetPriceRecordSellPrice) {
		PosSetPriceRecord_SellPrice = posSetPriceRecordSellPrice;
	}
	public String getPosSetPriceRecord_Result() {
		return PosSetPriceRecord_Result;
	}
	public void setPosSetPriceRecord_Result(String posSetPriceRecordResult) {
		PosSetPriceRecord_Result = posSetPriceRecordResult;
	}
	
	
	
}
