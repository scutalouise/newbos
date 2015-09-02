package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 客户类型实体类
 * @author zhulong
 *
 */
public class MonthDateAera implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String MonthDateAera_StartDate;
	private String MonthDateAera_NextEndDate;
	public String getMonthDateAera_StartDate() {
		return MonthDateAera_StartDate;
	}
	public void setMonthDateAera_StartDate(String monthDateAeraStartDate) {
		MonthDateAera_StartDate = monthDateAeraStartDate;
	}
	public String getMonthDateAera_NextEndDate() {
		return MonthDateAera_NextEndDate;
	}
	public void setMonthDateAera_NextEndDate(String monthDateAeraNextEndDate) {
		MonthDateAera_NextEndDate = monthDateAeraNextEndDate;
	}
	
	

	
}
