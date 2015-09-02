package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 能耗指标实体类
 * @author zhulong
 *
 */
public class SourceUsed implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String SourceUsed_StationNo;
	private String SourceUsed_TheDate;
	
	private float SourceUsed_MonthlyElec;
	private float SourceUsed_MonthlyWater;
	public String getSourceUsed_StationNo() {
		return SourceUsed_StationNo;
	}
	public void setSourceUsed_StationNo(String sourceUsedStationNo) {
		SourceUsed_StationNo = sourceUsedStationNo;
	}
	public String getSourceUsed_TheDate() {
		return SourceUsed_TheDate;
	}
	public void setSourceUsed_TheDate(String sourceUsedTheDate) {
		SourceUsed_TheDate = sourceUsedTheDate;
	}
	public float getSourceUsed_MonthlyElec() {
		return SourceUsed_MonthlyElec;
	}
	public void setSourceUsed_MonthlyElec(float sourceUsedMonthlyElec) {
		SourceUsed_MonthlyElec = sourceUsedMonthlyElec;
	}
	public float getSourceUsed_MonthlyWater() {
		return SourceUsed_MonthlyWater;
	}
	public void setSourceUsed_MonthlyWater(float sourceUsedMonthlyWater) {
		SourceUsed_MonthlyWater = sourceUsedMonthlyWater;
	}
	
	
	


	
	


	
}
