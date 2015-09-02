package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 入库单实体类
 * @author zhulong
 *
 */
public class RestockBill implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String RestockBill_OrderNum;
	private String RestockBill_RestockNum;
	private String RestockBill_StationNo;
	private String RestockBill_ProductType;
	private String RestockBill_ProductNum;
	private String RestockBill_ShiftDate;
	private String RestockBill_ShiftNo;
	private Date  RestockBill_StartDate;
	private String RestockBill_Unit;
	private String RestockBill_CarID;
	private String RestockBill_BottlesCarNo;
	private String RestockBill_MStationNo;
	private Double RestockBill_MIntoTemp;
	private Double RestockBill_MOutTemp;
	private Double RestockBill_MIntoPrs;
	private Double RestockBill_MOutPrs;
	private Date RestockBill_Mintodate;
	private Date RestockBill_MOutdate;
	private String RestockBill_DriverName;
	private String RestockBill_weighname;
	private String RestockBill_Mheader;
	private String RestockBill_Shipper;
	private Date RestockBill_GasArrived;
	private Date RestockBill_GasLeft;
	private float RestockBill_CIntoTemp;
	private float RestockBill_COutTemp;
	private float RestockBill_CIntoPrs;
	private float RestockBill_COutPrs;
	private String RestockBill_Seal;
	private String RestockBill_EvilWork;
	private String RestockBill_CHead;
	private Date RestockBill_UnloadDate;
	private String RestockBill_weather;
	private String RestockBill_hairplat;
	private Double RestockBill_Heavy;
	private Double RestockBill_Empty;
	private Double RestockBill_Delivery;
	private Double RestockBill_receipts;
	private Double RestockBill_lossrate;
	private String RestockBill_IsSync;
	private Date RestockBill_SyncDate;
	private Double tankDensity;
	
	public String getRestockBill_OrderNum() {
		return RestockBill_OrderNum;
	}
	public void setRestockBill_OrderNum(String restockBillOrderNum) {
		RestockBill_OrderNum = restockBillOrderNum;
	}
	public String getRestockBill_RestockNum() {
		return RestockBill_RestockNum;
	}
	public void setRestockBill_RestockNum(String restockBillRestockNum) {
		RestockBill_RestockNum = restockBillRestockNum;
	}
	public String getRestockBill_StationNo() {
		return RestockBill_StationNo;
	}
	public void setRestockBill_StationNo(String restockBillStationNo) {
		RestockBill_StationNo = restockBillStationNo;
	}
	public String getRestockBill_ProductType() {
		return RestockBill_ProductType;
	}
	public void setRestockBill_ProductType(String restockBillProductType) {
		RestockBill_ProductType = restockBillProductType;
	}
	public String getRestockBill_ProductNum() {
		return RestockBill_ProductNum;
	}
	public void setRestockBill_ProductNum(String restockBillProductNum) {
		RestockBill_ProductNum = restockBillProductNum;
	}
	public String getRestockBill_ShiftDate() {
		return RestockBill_ShiftDate;
	}
	public void setRestockBill_ShiftDate(String restockBillShiftDate) {
		RestockBill_ShiftDate = restockBillShiftDate;
	}
	public String getRestockBill_ShiftNo() {
		return RestockBill_ShiftNo;
	}
	public void setRestockBill_ShiftNo(String restockBillShiftNo) {
		RestockBill_ShiftNo = restockBillShiftNo;
	}
	public String getRestockBill_CarID() {
		return RestockBill_CarID;
	}
	public void setRestockBill_CarID(String restockBillCarID) {
		RestockBill_CarID = restockBillCarID;
	}
	public String getRestockBill_BottlesCarNo() {
		return RestockBill_BottlesCarNo;
	}
	public void setRestockBill_BottlesCarNo(String restockBillBottlesCarNo) {
		RestockBill_BottlesCarNo = restockBillBottlesCarNo;
	}
	public String getRestockBill_MStationNo() {
		return RestockBill_MStationNo;
	}
	public void setRestockBill_MStationNo(String restockBillMStationNo) {
		RestockBill_MStationNo = restockBillMStationNo;
	}
	
	public Double getRestockBill_MIntoTemp() {
		return RestockBill_MIntoTemp;
	}
	public void setRestockBill_MIntoTemp(Double restockBill_MIntoTemp) {
		RestockBill_MIntoTemp = restockBill_MIntoTemp;
	}
	public Double getRestockBill_MOutTemp() {
		return RestockBill_MOutTemp;
	}
	public void setRestockBill_MOutTemp(Double restockBill_MOutTemp) {
		RestockBill_MOutTemp = restockBill_MOutTemp;
	}
	public Double getRestockBill_MIntoPrs() {
		return RestockBill_MIntoPrs;
	}
	public void setRestockBill_MIntoPrs(Double restockBill_MIntoPrs) {
		RestockBill_MIntoPrs = restockBill_MIntoPrs;
	}
	public Double getRestockBill_MOutPrs() {
		return RestockBill_MOutPrs;
	}
	public void setRestockBill_MOutPrs(Double restockBill_MOutPrs) {
		RestockBill_MOutPrs = restockBill_MOutPrs;
	}
	public Date getRestockBill_Mintodate() {
		return RestockBill_Mintodate;
	}
	public void setRestockBill_Mintodate(Date restockBillMintodate) {
		RestockBill_Mintodate = restockBillMintodate;
	}
	public Date getRestockBill_MOutdate() {
		return RestockBill_MOutdate;
	}
	public void setRestockBill_MOutdate(Date restockBillMOutdate) {
		RestockBill_MOutdate = restockBillMOutdate;
	}
	public String getRestockBill_DriverName() {
		return RestockBill_DriverName;
	}
	public void setRestockBill_DriverName(String restockBillDriverName) {
		RestockBill_DriverName = restockBillDriverName;
	}
	public String getRestockBill_weighname() {
		return RestockBill_weighname;
	}
	public void setRestockBill_weighname(String restockBillWeighname) {
		RestockBill_weighname = restockBillWeighname;
	}
	public String getRestockBill_Mheader() {
		return RestockBill_Mheader;
	}
	public void setRestockBill_Mheader(String restockBillMheader) {
		RestockBill_Mheader = restockBillMheader;
	}
	public String getRestockBill_Shipper() {
		return RestockBill_Shipper;
	}
	public void setRestockBill_Shipper(String restockBillShipper) {
		RestockBill_Shipper = restockBillShipper;
	}
	public Date getRestockBill_GasArrived() {
		return RestockBill_GasArrived;
	}
	public void setRestockBill_GasArrived(Date restockBillGasArrived) {
		RestockBill_GasArrived = restockBillGasArrived;
	}
	public Date getRestockBill_GasLeft() {
		return RestockBill_GasLeft;
	}
	public void setRestockBill_GasLeft(Date restockBillGasLeft) {
		RestockBill_GasLeft = restockBillGasLeft;
	}
	public float getRestockBill_CIntoTemp() {
		return RestockBill_CIntoTemp;
	}
	public void setRestockBill_CIntoTemp(float restockBillCIntoTemp) {
		RestockBill_CIntoTemp = restockBillCIntoTemp;
	}
	public float getRestockBill_COutTemp() {
		return RestockBill_COutTemp;
	}
	public void setRestockBill_COutTemp(float restockBillCOutTemp) {
		RestockBill_COutTemp = restockBillCOutTemp;
	}
	public float getRestockBill_CIntoPrs() {
		return RestockBill_CIntoPrs;
	}
	public void setRestockBill_CIntoPrs(float restockBillCIntoPrs) {
		RestockBill_CIntoPrs = restockBillCIntoPrs;
	}
	public float getRestockBill_COutPrs() {
		return RestockBill_COutPrs;
	}
	public void setRestockBill_COutPrs(float restockBillCOutPrs) {
		RestockBill_COutPrs = restockBillCOutPrs;
	}
	public String getRestockBill_Seal() {
		return RestockBill_Seal;
	}
	public void setRestockBill_Seal(String restockBillSeal) {
		RestockBill_Seal = restockBillSeal;
	}
	public String getRestockBill_EvilWork() {
		return RestockBill_EvilWork;
	}
	public void setRestockBill_EvilWork(String restockBillEvilWork) {
		RestockBill_EvilWork = restockBillEvilWork;
	}
	public String getRestockBill_CHead() {
		return RestockBill_CHead;
	}
	public void setRestockBill_CHead(String restockBillCHead) {
		RestockBill_CHead = restockBillCHead;
	}
	public Date getRestockBill_UnloadDate() {
		return RestockBill_UnloadDate;
	}
	public void setRestockBill_UnloadDate(Date restockBillUnloadDate) {
		RestockBill_UnloadDate = restockBillUnloadDate;
	}
	public String getRestockBill_weather() {
		return RestockBill_weather;
	}
	public void setRestockBill_weather(String restockBillWeather) {
		RestockBill_weather = restockBillWeather;
	}
	public String getRestockBill_hairplat() {
		return RestockBill_hairplat;
	}
	public void setRestockBill_hairplat(String restockBillHairplat) {
		RestockBill_hairplat = restockBillHairplat;
	}
	public Double getRestockBill_Heavy() {
		return RestockBill_Heavy;
	}
	public void setRestockBill_Heavy(Double restockBill_Heavy) {
		RestockBill_Heavy = restockBill_Heavy;
	}
	public Double getRestockBill_Empty() {
		return RestockBill_Empty;
	}
	public void setRestockBill_Empty(Double restockBill_Empty) {
		RestockBill_Empty = restockBill_Empty;
	}
	public Double getRestockBill_Delivery() {
		return RestockBill_Delivery;
	}
	public void setRestockBill_Delivery(Double restockBill_Delivery) {
		RestockBill_Delivery = restockBill_Delivery;
	}
	public Double getRestockBill_receipts() {
		return RestockBill_receipts;
	}
	public void setRestockBill_receipts(Double restockBill_receipts) {
		RestockBill_receipts = restockBill_receipts;
	}
	public Double getRestockBill_lossrate() {
		return RestockBill_lossrate;
	}
	public void setRestockBill_lossrate(Double restockBill_lossrate) {
		RestockBill_lossrate = restockBill_lossrate;
	}
	public String getRestockBill_IsSync() {
		return RestockBill_IsSync;
	}
	public void setRestockBill_IsSync(String restockBillIsSync) {
		RestockBill_IsSync = restockBillIsSync;
	}
	public Date getRestockBill_SyncDate() {
		return RestockBill_SyncDate;
	}
	public void setRestockBill_SyncDate(Date restockBillSyncDate) {
		RestockBill_SyncDate = restockBillSyncDate;
	}
	public Date getRestockBill_StartDate() {
		return RestockBill_StartDate;
	}
	public void setRestockBill_StartDate(Date restockBillStartDate) {
		RestockBill_StartDate = restockBillStartDate;
	}
	public String getRestockBill_Unit() {
		return RestockBill_Unit;
	}
	public void setRestockBill_Unit(String restockBillUnit) {
		RestockBill_Unit = restockBillUnit;
	}
	public Double getTankDensity() {
		return tankDensity;
	}
	public void setTankDensity(Double tankDensity) {
		this.tankDensity = tankDensity;
	}
	
	

	
}
