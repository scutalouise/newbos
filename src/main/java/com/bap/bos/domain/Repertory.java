package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存实体类
 * @author zhulong
 *
 */
public class Repertory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Repertory_StationNo;
	private String Repertory_TankNum;
	private String Repertory_ShiftNo;
	private String Repertory_CreateTime;
	private String Repertory_ProductType;
	private String Repertory_ProductNum;
	private float Repertory_ProductVol;
	private float Repertory_TotalVol;
	private float Repertory_WaterLevel;
	private float Repertory_PetrolLevel;
	private float Repertory_Temp;
	private Date Repertory_AddTime;
	private String Repertory_Unit;
	private String Repertory_Remark;
	private String Repertory_IsSync;
	private Date Repertory_SyncDate;
	/*非本表属性*/
	private String Product_Name;
	public String getRepertory_StationNo() {
		return Repertory_StationNo;
	}
	public void setRepertory_StationNo(String repertoryStationNo) {
		Repertory_StationNo = repertoryStationNo;
	}
	public String getRepertory_TankNum() {
		return Repertory_TankNum;
	}
	public void setRepertory_TankNum(String repertoryTankNum) {
		Repertory_TankNum = repertoryTankNum;
	}
	public String getRepertory_ProductType() {
		return Repertory_ProductType;
	}
	public void setRepertory_ProductType(String repertoryProductType) {
		Repertory_ProductType = repertoryProductType;
	}
	public String getRepertory_ProductNum() {
		return Repertory_ProductNum;
	}
	public void setRepertory_ProductNum(String repertoryProductNum) {
		Repertory_ProductNum = repertoryProductNum;
	}
	public float getRepertory_ProductVol() {
		return Repertory_ProductVol;
	}
	public void setRepertory_ProductVol(float repertoryProductVol) {
		Repertory_ProductVol = repertoryProductVol;
	}
	public float getRepertory_TotalVol() {
		return Repertory_TotalVol;
	}
	public void setRepertory_TotalVol(float repertoryTotalVol) {
		Repertory_TotalVol = repertoryTotalVol;
	}
	public float getRepertory_WaterLevel() {
		return Repertory_WaterLevel;
	}
	public void setRepertory_WaterLevel(float repertoryWaterLevel) {
		Repertory_WaterLevel = repertoryWaterLevel;
	}
	public float getRepertory_PetrolLevel() {
		return Repertory_PetrolLevel;
	}
	public void setRepertory_PetrolLevel(float repertoryPetrolLevel) {
		Repertory_PetrolLevel = repertoryPetrolLevel;
	}
	public float getRepertory_Temp() {
		return Repertory_Temp;
	}
	public void setRepertory_Temp(float repertoryTemp) {
		Repertory_Temp = repertoryTemp;
	}
	public String getRepertory_ShiftNo() {
		return Repertory_ShiftNo;
	}
	public void setRepertory_ShiftNo(String repertoryShiftNo) {
		Repertory_ShiftNo = repertoryShiftNo;
	}
	public Date getRepertory_AddTime() {
		return Repertory_AddTime;
	}
	public void setRepertory_AddTime(Date repertoryAddTime) {
		Repertory_AddTime = repertoryAddTime;
	}
	public String getRepertory_Unit() {
		return Repertory_Unit;
	}
	public void setRepertory_Unit(String repertoryUnit) {
		Repertory_Unit = repertoryUnit;
	}
	public String getRepertory_Remark() {
		return Repertory_Remark;
	}
	public void setRepertory_Remark(String repertoryRemark) {
		Repertory_Remark = repertoryRemark;
	}
	public String getRepertory_IsSync() {
		return Repertory_IsSync;
	}
	public void setRepertory_IsSync(String repertoryIsSync) {
		Repertory_IsSync = repertoryIsSync;
	}
	public Date getRepertory_SyncDate() {
		return Repertory_SyncDate;
	}
	public void setRepertory_SyncDate(Date repertorySyncDate) {
		Repertory_SyncDate = repertorySyncDate;
	}
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String productName) {
		Product_Name = productName;
	}
	public String getRepertory_CreateTime() {
		return Repertory_CreateTime;
	}
	public void setRepertory_CreateTime(String repertoryCreateTime) {
		Repertory_CreateTime = repertoryCreateTime;
	}
	
	


	
	


	
}
