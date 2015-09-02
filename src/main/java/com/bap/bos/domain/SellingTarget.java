package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 销售计划实体类
 * @author zhulong
 *
 */
public class SellingTarget implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String SellingTarget_StationNo;
	private String SellingTarget_TheMonth;
	private String SellingTarget_ProductNum;
	private String SellingTarget_PlanQTY;
	private String SellingTarget_IsSync;
	private Date SellingTarget_SyncDate;
	public String getSellingTarget_StationNo() {
		return SellingTarget_StationNo;
	}
	public void setSellingTarget_StationNo(String sellingTargetStationNo) {
		SellingTarget_StationNo = sellingTargetStationNo;
	}
	public String getSellingTarget_TheMonth() {
		return SellingTarget_TheMonth;
	}
	public void setSellingTarget_TheMonth(String sellingTargetTheMonth) {
		SellingTarget_TheMonth = sellingTargetTheMonth;
	}
	public String getSellingTarget_ProductNum() {
		return SellingTarget_ProductNum;
	}
	public void setSellingTarget_ProductNum(String sellingTargetProductNum) {
		SellingTarget_ProductNum = sellingTargetProductNum;
	}
	public String getSellingTarget_PlanQTY() {
		return SellingTarget_PlanQTY;
	}
	public void setSellingTarget_PlanQTY(String sellingTargetPlanQTY) {
		SellingTarget_PlanQTY = sellingTargetPlanQTY;
	}
	public String getSellingTarget_IsSync() {
		return SellingTarget_IsSync;
	}
	public void setSellingTarget_IsSync(String sellingTargetIsSync) {
		SellingTarget_IsSync = sellingTargetIsSync;
	}
	public Date getSellingTarget_SyncDate() {
		return SellingTarget_SyncDate;
	}
	public void setSellingTarget_SyncDate(Date sellingTargetSyncDate) {
		SellingTarget_SyncDate = sellingTargetSyncDate;
	}
	
	

	
}
