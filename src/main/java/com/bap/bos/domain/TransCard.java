package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 卡交易实体类
 * @author zhulong
 *
 */
public class TransCard implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String TransCard_TransNo;
	private String TransCard_StatinNo;
	private String TransCard_StaffNo;
	private String TransCard_CardNo;
	private String TransItem_DitNo;
	private Date TransCard_TxApplyTime;
	private Float TransCard_PayAmount;
	private Float TransCard_PaidinAmount;
	private Float TransCard_PayVol;
	private String TransCard_ItemCode;
	private String TransCard_TransType;
	private Float TransCard_SellPrice;
	private String TransCard_ShiftNo;
	private String TransCard_ShiftDate;
	private Date TransCard_TxCreateTime;
	private String TransCard_ReqType;
	
	private String CustomerType_ID;

	public String getTransCard_TransNo() {
		return TransCard_TransNo;
	}

	public void setTransCard_TransNo(String transCardTransNo) {
		TransCard_TransNo = transCardTransNo;
	}

	public String getTransCard_StatinNo() {
		return TransCard_StatinNo;
	}

	public void setTransCard_StatinNo(String transCardStatinNo) {
		TransCard_StatinNo = transCardStatinNo;
	}

	public String getTransCard_StaffNo() {
		return TransCard_StaffNo;
	}

	public void setTransCard_StaffNo(String transCardStaffNo) {
		TransCard_StaffNo = transCardStaffNo;
	}

	public String getTransCard_CardNo() {
		return TransCard_CardNo;
	}

	public void setTransCard_CardNo(String transCardCardNo) {
		TransCard_CardNo = transCardCardNo;
	}

	public String getTransItem_DitNo() {
		return TransItem_DitNo;
	}

	public void setTransItem_DitNo(String transItemDitNo) {
		TransItem_DitNo = transItemDitNo;
	}

	public Date getTransCard_TxApplyTime() {
		return TransCard_TxApplyTime;
	}

	public void setTransCard_TxApplyTime(Date transCardTxApplyTime) {
		TransCard_TxApplyTime = transCardTxApplyTime;
	}

	public Float getTransCard_PayAmount() {
		return TransCard_PayAmount;
	}

	public void setTransCard_PayAmount(Float transCardPayAmount) {
		TransCard_PayAmount = transCardPayAmount;
	}

	public Float getTransCard_PaidinAmount() {
		return TransCard_PaidinAmount;
	}

	public void setTransCard_PaidinAmount(Float transCardPaidinAmount) {
		TransCard_PaidinAmount = transCardPaidinAmount;
	}

	public Float getTransCard_PayVol() {
		return TransCard_PayVol;
	}

	public void setTransCard_PayVol(Float transCardPayVol) {
		TransCard_PayVol = transCardPayVol;
	}

	public String getTransCard_ItemCode() {
		return TransCard_ItemCode;
	}

	public void setTransCard_ItemCode(String transCardItemCode) {
		TransCard_ItemCode = transCardItemCode;
	}

	public String getTransCard_TransType() {
		return TransCard_TransType;
	}

	public void setTransCard_TransType(String transCardTransType) {
		TransCard_TransType = transCardTransType;
	}

	public Float getTransCard_SellPrice() {
		return TransCard_SellPrice;
	}

	public void setTransCard_SellPrice(Float transCardSellPrice) {
		TransCard_SellPrice = transCardSellPrice;
	}

	public String getTransCard_ShiftNo() {
		return TransCard_ShiftNo;
	}

	public void setTransCard_ShiftNo(String transCardShiftNo) {
		TransCard_ShiftNo = transCardShiftNo;
	}

	public String getTransCard_ShiftDate() {
		return TransCard_ShiftDate;
	}

	public void setTransCard_ShiftDate(String transCardShiftDate) {
		TransCard_ShiftDate = transCardShiftDate;
	}

	public Date getTransCard_TxCreateTime() {
		return TransCard_TxCreateTime;
	}

	public void setTransCard_TxCreateTime(Date transCardTxCreateTime) {
		TransCard_TxCreateTime = transCardTxCreateTime;
	}

	public String getCustomerType_ID() {
		return CustomerType_ID;
	}

	public void setCustomerType_ID(String customerTypeID) {
		CustomerType_ID = customerTypeID;
	}

	public String getTransCard_ReqType() {
		return TransCard_ReqType;
	}

	public void setTransCard_ReqType(String transCard_ReqType) {
		TransCard_ReqType = transCard_ReqType;
	}
}
