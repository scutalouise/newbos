package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 油枪信息表
 * @author zhulong
 *
 */
public class NozzleSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String NozzleSetting_StationNo;
	private String NozzleSetting_Phy_Noz;
	private String NozzleSetting_node;
	private String NozzleSetting_FP;
	private String NozzleSetting_Log_Noz;
	private String NozzleSetting_TankNum;
	public String getNozzleSetting_StationNo() {
		return NozzleSetting_StationNo;
	}
	public void setNozzleSetting_StationNo(String nozzleSettingStationNo) {
		NozzleSetting_StationNo = nozzleSettingStationNo;
	}
	public String getNozzleSetting_Phy_Noz() {
		return NozzleSetting_Phy_Noz;
	}
	public void setNozzleSetting_Phy_Noz(String nozzleSettingPhyNoz) {
		NozzleSetting_Phy_Noz = nozzleSettingPhyNoz;
	}
	public String getNozzleSetting_node() {
		return NozzleSetting_node;
	}
	public void setNozzleSetting_node(String nozzleSettingNode) {
		NozzleSetting_node = nozzleSettingNode;
	}
	public String getNozzleSetting_FP() {
		return NozzleSetting_FP;
	}
	public void setNozzleSetting_FP(String nozzleSettingFP) {
		NozzleSetting_FP = nozzleSettingFP;
	}
	public String getNozzleSetting_Log_Noz() {
		return NozzleSetting_Log_Noz;
	}
	public void setNozzleSetting_Log_Noz(String nozzleSettingLogNoz) {
		NozzleSetting_Log_Noz = nozzleSettingLogNoz;
	}
	public String getNozzleSetting_TankNum() {
		return NozzleSetting_TankNum;
	}
	public void setNozzleSetting_TankNum(String nozzleSetting_TankNum) {
		NozzleSetting_TankNum = nozzleSetting_TankNum;
	}
	
	
	
}
