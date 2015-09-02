package com.bap.bos.util;
/**
 * 班结审核实体类
 * @author zhulong
 *
 */
public class ShiftVerify {
	private String NozzleId;
	private String ProductName;
	private String ProductNum;
	private double StartVol;
	private double EndVol;
	private double SubtractionValue;
	public String getNozzleId() {
		return NozzleId;
	}
	public void setNozzleId(String nozzleId) {
		NozzleId = nozzleId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public double getStartVol() {
		return StartVol;
	}
	public void setStartVol(double startVol) {
		StartVol = startVol;
	}
	public double getEndVol() {
		return EndVol;
	}
	public void setEndVol(double endVol) {
		EndVol = endVol;
	}
	public double getSubtractionValue() {
		return SubtractionValue;
	}
	public void setSubtractionValue(double subtractionValue) {
		SubtractionValue = subtractionValue;
	}
	public String getProductNum() {
		return ProductNum;
	}
	public void setProductNum(String productNum) {
		ProductNum = productNum;
	}
	
	
}
