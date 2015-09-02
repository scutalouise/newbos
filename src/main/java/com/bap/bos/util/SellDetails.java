package com.bap.bos.util;

import java.util.Date;

/**
 * 销售详情
 * @author zhulong
 *
 */
public class SellDetails {
	private String TransItem_ShiftNo;
	private String TransItem_ShiftDate;
	private Date TransItem_TransDate;
	private String TransItem_TransNo;
	private String TransCard_CardNo;
	private String TransItem_NozzleNo;
	private String CustomerType_Desc;
	private Float  TransItem_SellPrice;
	private Float TransItem_PayVol;
	private Float TransItem_PayMoney;
	private String Station_No;
	private String Station_Name;
	private Integer TaxRete_TaxRate;
	private String TransItem_Tend;
	private Float TransCard_PaidinAmount;
	
	public SellDetails(String transItemShiftNo, String transItemShiftDate,
			Date transItemTransDate, String transItemTransNo,
			String transCardCardNo, String transItemNozzleNo,
			String customerTypeDesc, float transItemSellPrice,
			float transItemPayVol, float transItemPayMoney, String stationNo,
			String stationName, int taxReteTaxRate, String transItemTend,
			float transCardPaidinAmount) {
		this.TransItem_ShiftNo = transItemShiftNo;
		this.TransItem_ShiftDate = transItemShiftDate;
		this.TransItem_TransDate = transItemTransDate;
		this.TransItem_TransNo = transItemTransNo;
		this.TransCard_CardNo = transCardCardNo;
		this.TransItem_NozzleNo = transItemNozzleNo;
		this.CustomerType_Desc = customerTypeDesc;
		this.TransItem_SellPrice = transItemSellPrice;
		this.TransItem_PayVol = transItemPayVol;
		this.TransItem_PayMoney = transItemPayMoney;
		this.Station_No = stationNo;
		this.Station_Name = stationName;
		this.TaxRete_TaxRate = taxReteTaxRate;
		this.TransItem_Tend = transItemTend;
		this.TransCard_PaidinAmount = transCardPaidinAmount;
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
	public Date getTransItem_TransDate() {
		return TransItem_TransDate;
	}
	public void setTransItem_TransDate(Date transItemTransDate) {
		TransItem_TransDate = transItemTransDate;
	}
	public String getTransItem_TransNo() {
		return TransItem_TransNo;
	}
	public void setTransItem_TransNo(String transItemTransNo) {
		TransItem_TransNo = transItemTransNo;
	}
	public String getTransCard_CardNo() {
		return TransCard_CardNo;
	}
	public void setTransCard_CardNo(String transCardCardNo) {
		TransCard_CardNo = transCardCardNo;
	}
	public String getTransItem_NozzleNo() {
		return TransItem_NozzleNo;
	}
	public void setTransItem_NozzleNo(String transItemNozzleNo) {
		TransItem_NozzleNo = transItemNozzleNo;
	}
	public String getCustomerType_Desc() {
		return CustomerType_Desc;
	}
	public void setCustomerType_Desc(String customerTypeDesc) {
		CustomerType_Desc = customerTypeDesc;
	}
	public Float getTransItem_SellPrice() {
		return TransItem_SellPrice;
	}
	public void setTransItem_SellPrice(Float transItemSellPrice) {
		TransItem_SellPrice = transItemSellPrice;
	}
	public Float getTransItem_PayVol() {
		return TransItem_PayVol;
	}
	public void setTransItem_PayVol(Float transItemPayVol) {
		TransItem_PayVol = transItemPayVol;
	}
	public Float getTransItem_PayMoney() {
		return TransItem_PayMoney;
	}
	public void setTransItem_PayMoney(Float transItemPayMoney) {
		TransItem_PayMoney = transItemPayMoney;
	}
	public String getStation_No() {
		return Station_No;
	}
	public void setStation_No(String stationNo) {
		Station_No = stationNo;
	}
	public String getStation_Name() {
		return Station_Name;
	}
	public void setStation_Name(String stationName) {
		Station_Name = stationName;
	}
	public Integer getTaxRete_TaxRate() {
		return TaxRete_TaxRate;
	}
	public void setTaxRete_TaxRate(Integer taxReteTaxRate) {
		TaxRete_TaxRate = taxReteTaxRate;
	}
	public String getTransItem_Tend() {
		return TransItem_Tend;
	}
	public void setTransItem_Tend(String transItemTend) {
		TransItem_Tend = transItemTend;
	}
	public Float getTransCard_PaidinAmount() {
		return TransCard_PaidinAmount;
	}
	public void setTransCard_PaidinAmount(Float transCardPaidinAmount) {
		TransCard_PaidinAmount = transCardPaidinAmount;
	}
	
 
}
