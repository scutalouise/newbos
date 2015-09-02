package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 采购订单实体类
 * @author zhulong
 *
 */
public class OrderBill implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String OrderBill_Num;
	private String OrderBill_NumAliases;
	private String OrderBill_StationNo;
	private String OrderBill_TankNum;
	private String OrderBill_ProductType;
	private String OrderBill_ProductNum;
	private String OrderBill_Unit;
	private Double OrderBill_AddVolumeM;
	private Double OrderBill_AddVolumeL;
	private Double OrderBill_AddWeight;
	private String OrderBill_SupplierNum;
	private String OrderBill_Status;
	private Double OrderBill_ActVolumeM;
	private Double OrderBill_ActVolumeL;
	private Double OrderBill_ActWeight;
	private Double OrderBill_CostPrice;
	private Date OrderBill_CreateDate;
	private Date OrderBill_AckDate;
	private Date OrderBill_ActOrderDate;
	private Date OrderBill_ExpectDate;
	private Date OrderBill_RealityDate;
	private Date OrderBill_ActPlanDate;
	private String OrderBill_CreateStaffNo;
	private String OrderBill_AckStaffNo;
	private String OrderBill_ActOrderStaffNo;
	private String OrderBill_SignoffStaffNo;
	private Boolean recordManual; //手动卸油
	private Boolean isRemedy; //是否为补录
	
	public String getOrderBill_Num() {
		return OrderBill_Num;
	}
	public void setOrderBill_Num(String orderBillNum) {
		OrderBill_Num = orderBillNum;
	}
	public String getOrderBill_StationNo() {
		return OrderBill_StationNo;
	}
	public void setOrderBill_StationNo(String orderBillStationNo) {
		OrderBill_StationNo = orderBillStationNo;
	}
	public String getOrderBill_TankNum() {
		return OrderBill_TankNum;
	}
	public void setOrderBill_TankNum(String orderBillTankNum) {
		OrderBill_TankNum = orderBillTankNum;
	}
	public String getOrderBill_ProductType() {
		return OrderBill_ProductType;
	}
	public void setOrderBill_ProductType(String orderBillProductType) {
		OrderBill_ProductType = orderBillProductType;
	}
	public String getOrderBill_ProductNum() {
		return OrderBill_ProductNum;
	}
	public void setOrderBill_ProductNum(String orderBillProductNum) {
		OrderBill_ProductNum = orderBillProductNum;
	}
	public String getOrderBill_Unit() {
		return OrderBill_Unit;
	}
	public void setOrderBill_Unit(String orderBillUnit) {
		OrderBill_Unit = orderBillUnit;
	}
	public Double getOrderBill_AddVolumeM() {
		return OrderBill_AddVolumeM;
	}
	public void setOrderBill_AddVolumeM(Double orderBillAddVolumeM) {
		OrderBill_AddVolumeM = orderBillAddVolumeM;
	}
	public Double getOrderBill_AddVolumeL() {
		return OrderBill_AddVolumeL;
	}
	public void setOrderBill_AddVolumeL(Double orderBillAddVolumeL) {
		OrderBill_AddVolumeL = orderBillAddVolumeL;
	}
	public Double getOrderBill_AddWeight() {
		return OrderBill_AddWeight;
	}
	public void setOrderBill_AddWeight(Double orderBillAddWeight) {
		OrderBill_AddWeight = orderBillAddWeight;
	}
	public String getOrderBill_SupplierNum() {
		return OrderBill_SupplierNum;
	}
	public void setOrderBill_SupplierNum(String orderBillSupplierNum) {
		OrderBill_SupplierNum = orderBillSupplierNum;
	}
	public String getOrderBill_Status() {
		return OrderBill_Status;
	}
	public void setOrderBill_Status(String orderBillStatus) {
		OrderBill_Status = orderBillStatus;
	}
	public Double getOrderBill_ActVolumeM() {
		return OrderBill_ActVolumeM;
	}
	public void setOrderBill_ActVolumeM(Double orderBillActVolumeM) {
		OrderBill_ActVolumeM = orderBillActVolumeM;
	}
	public Double getOrderBill_ActVolumeL() {
		return OrderBill_ActVolumeL;
	}
	public void setOrderBill_ActVolumeL(Double orderBillActVolumeL) {
		OrderBill_ActVolumeL = orderBillActVolumeL;
	}
	public Double getOrderBill_ActWeight() {
		return OrderBill_ActWeight;
	}
	public void setOrderBill_ActWeight(Double orderBillActWeight) {
		OrderBill_ActWeight = orderBillActWeight;
	}
	public Double getOrderBill_CostPrice() {
		return OrderBill_CostPrice;
	}
	public void setOrderBill_CostPrice(Double orderBillCostPrice) {
		OrderBill_CostPrice = orderBillCostPrice;
	}
	public Date getOrderBill_CreateDate() {
		return OrderBill_CreateDate;
	}
	public void setOrderBill_CreateDate(Date orderBillCreateDate) {
		OrderBill_CreateDate = orderBillCreateDate;
	}
	public Date getOrderBill_AckDate() {
		return OrderBill_AckDate;
	}
	public void setOrderBill_AckDate(Date orderBillAckDate) {
		OrderBill_AckDate = orderBillAckDate;
	}
	public Date getOrderBill_ActOrderDate() {
		return OrderBill_ActOrderDate;
	}
	public void setOrderBill_ActOrderDate(Date orderBillActOrderDate) {
		OrderBill_ActOrderDate = orderBillActOrderDate;
	}
	public Date getOrderBill_ExpectDate() {
		return OrderBill_ExpectDate;
	}
	public void setOrderBill_ExpectDate(Date orderBillExpectDate) {
		OrderBill_ExpectDate = orderBillExpectDate;
	}
	public Date getOrderBill_RealityDate() {
		return OrderBill_RealityDate;
	}
	public void setOrderBill_RealityDate(Date orderBillRealityDate) {
		OrderBill_RealityDate = orderBillRealityDate;
	}
	public Date getOrderBill_ActPlanDate() {
		return OrderBill_ActPlanDate;
	}
	public void setOrderBill_ActPlanDate(Date orderBillActPlanDate) {
		OrderBill_ActPlanDate = orderBillActPlanDate;
	}
	public String getOrderBill_CreateStaffNo() {
		return OrderBill_CreateStaffNo;
	}
	public void setOrderBill_CreateStaffNo(String orderBillCreateStaffNo) {
		OrderBill_CreateStaffNo = orderBillCreateStaffNo;
	}
	public String getOrderBill_AckStaffNo() {
		return OrderBill_AckStaffNo;
	}
	public void setOrderBill_AckStaffNo(String orderBillAckStaffNo) {
		OrderBill_AckStaffNo = orderBillAckStaffNo;
	}
	public String getOrderBill_ActOrderStaffNo() {
		return OrderBill_ActOrderStaffNo;
	}
	public void setOrderBill_ActOrderStaffNo(String orderBillActOrderStaffNo) {
		OrderBill_ActOrderStaffNo = orderBillActOrderStaffNo;
	}
	public String getOrderBill_SignoffStaffNo() {
		return OrderBill_SignoffStaffNo;
	}
	public void setOrderBill_SignoffStaffNo(String orderBillSignoffStaffNo) {
		OrderBill_SignoffStaffNo = orderBillSignoffStaffNo;
	}
	public String getOrderBill_NumAliases() {
		return OrderBill_NumAliases;
	}
	public void setOrderBill_NumAliases(String orderBillNumAliases) {
		OrderBill_NumAliases = orderBillNumAliases;
	}
	public Boolean getRecordManual() {
		return recordManual;
	}
	public void setRecordManual(Boolean recordManual) {
		this.recordManual = recordManual;
	}
	public Boolean getIsRemedy() {
		return isRemedy;
	}
	public void setIsRemedy(Boolean isRemedy) {
		this.isRemedy = isRemedy;
	}

}
