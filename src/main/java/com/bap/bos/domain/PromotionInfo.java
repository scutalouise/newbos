package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 促销信息实体类
 * @author zhulong
 *
 */
public class PromotionInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer PromotionInfo_Num;
	private String PromotionInfo_Name;
	private int PromotionInfo_Count;
	private String PromotionInfo_Remark;
	public Integer getPromotionInfo_Num() {
		return PromotionInfo_Num;
	}
	public void setPromotionInfo_Num(Integer promotionInfoNum) {
		PromotionInfo_Num = promotionInfoNum;
	}
	public String getPromotionInfo_Name() {
		return PromotionInfo_Name;
	}
	public void setPromotionInfo_Name(String promotionInfoName) {
		PromotionInfo_Name = promotionInfoName;
	}
	public int getPromotionInfo_Count() {
		return PromotionInfo_Count;
	}
	public void setPromotionInfo_Count(int promotionInfoCount) {
		PromotionInfo_Count = promotionInfoCount;
	}
	public String getPromotionInfo_Remark() {
		return PromotionInfo_Remark;
	}
	public void setPromotionInfo_Remark(String promotionInfoRemark) {
		PromotionInfo_Remark = promotionInfoRemark;
	}
	


}
