package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 卸油数据实体类
 * @author zhulong
 *
 */
public class OilUnloadingData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String OilUnloadingData_OrderNum;
	private String oilUnloadingData_TankId; //added on 2015/4/7 0:20 by edgar
	private String OilUnloadingData_State;
	private float OilUnloadingData_PumpSum;
	private float OilUnloadingData_TotalVol;
	private float OilUnloadingData_OilVol;
	private float OilUnloadingData_Temp;
	private float OilUnloadingData_WaterHeight;
	private float OilUnloadingData_OilHeight;
	public String getOilUnloadingData_OrderNum() {
		return OilUnloadingData_OrderNum;
	}
	public void setOilUnloadingData_OrderNum(String oilUnloadingDataOrderNum) {
		OilUnloadingData_OrderNum = oilUnloadingDataOrderNum;
	}
	public String getOilUnloadingData_State() {
		return OilUnloadingData_State;
	}
	public void setOilUnloadingData_State(String oilUnloadingDataState) {
		OilUnloadingData_State = oilUnloadingDataState;
	}
	public float getOilUnloadingData_TotalVol() {
		return OilUnloadingData_TotalVol;
	}
	public void setOilUnloadingData_TotalVol(float oilUnloadingDataTotalVol) {
		OilUnloadingData_TotalVol = oilUnloadingDataTotalVol;
	}
	public float getOilUnloadingData_OilVol() {
		return OilUnloadingData_OilVol;
	}
	public void setOilUnloadingData_OilVol(float oilUnloadingDataOilVol) {
		OilUnloadingData_OilVol = oilUnloadingDataOilVol;
	}
	public float getOilUnloadingData_Temp() {
		return OilUnloadingData_Temp;
	}
	public void setOilUnloadingData_Temp(float oilUnloadingDataTemp) {
		OilUnloadingData_Temp = oilUnloadingDataTemp;
	}
	public float getOilUnloadingData_WaterHeight() {
		return OilUnloadingData_WaterHeight;
	}
	public void setOilUnloadingData_WaterHeight(float oilUnloadingDataWaterHeight) {
		OilUnloadingData_WaterHeight = oilUnloadingDataWaterHeight;
	}
	public float getOilUnloadingData_OilHeight() {
		return OilUnloadingData_OilHeight;
	}
	public void setOilUnloadingData_OilHeight(float oilUnloadingDataOilHeight) {
		OilUnloadingData_OilHeight = oilUnloadingDataOilHeight;
	}
	public float getOilUnloadingData_PumpSum() {
		return OilUnloadingData_PumpSum;
	}
	public void setOilUnloadingData_PumpSum(float oilUnloadingDataPumpSum) {
		OilUnloadingData_PumpSum = oilUnloadingDataPumpSum;
	}
	public String getOilUnloadingData_TankId() {
		return oilUnloadingData_TankId;
	}
	public void setOilUnloadingData_TankId(String oilUnloadingData_TankId) {
		this.oilUnloadingData_TankId = oilUnloadingData_TankId;
	}
	
}
