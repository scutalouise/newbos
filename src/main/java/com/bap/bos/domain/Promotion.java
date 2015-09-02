package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 促销实体类
 * @author zhulong
 *
 */
public class Promotion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Promotion_ProductNum;
	private double Promotion_MoneyLow;
	private double Promotion_MoneyHigh;
	private String Promotion_TimeStart;
	private String Promotion_TimeEnd;
	private int Promotion_InfoNum;
	public String getPromotion_ProductNum() {
		return Promotion_ProductNum;
	}
	public void setPromotion_ProductNum(String promotionProductNum) {
		Promotion_ProductNum = promotionProductNum;
	}
	public double getPromotion_MoneyLow() {
		return Promotion_MoneyLow;
	}
	public void setPromotion_MoneyLow(double promotionMoneyLow) {
		Promotion_MoneyLow = promotionMoneyLow;
	}
	public double getPromotion_MoneyHigh() {
		return Promotion_MoneyHigh;
	}
	public void setPromotion_MoneyHigh(double promotionMoneyHigh) {
		Promotion_MoneyHigh = promotionMoneyHigh;
	}
	public String getPromotion_TimeStart() {
		return Promotion_TimeStart;
	}
	public void setPromotion_TimeStart(String promotionTimeStart) {
		Promotion_TimeStart = promotionTimeStart;
	}
	public String getPromotion_TimeEnd() {
		return Promotion_TimeEnd;
	}
	public void setPromotion_TimeEnd(String promotionTimeEnd) {
		Promotion_TimeEnd = promotionTimeEnd;
	}
	public int getPromotion_InfoNum() {
		return Promotion_InfoNum;
	}
	public void setPromotion_InfoNum(int promotionInfoNum) {
		Promotion_InfoNum = promotionInfoNum;
	}


}
