package com.bap.bos.domain;

import java.io.Serializable;
/**
 * 供应商信息实体类
 * @author zhulong
 *
 */
public class ProductSupplier implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ProductSupplier_Name;
	private String ProductSupplier_Tel;
	private String ProductSupplier_Cell;
	private String ProductSupplier_Fax;
	private String ProductSupplier_Address;
	private String ProductSupplier_Remark;
	private Integer ProductSupplier_key;
	public String getProductSupplier_Name() {
		return ProductSupplier_Name;
	}
	public void setProductSupplier_Name(String productSupplierName) {
		ProductSupplier_Name = productSupplierName;
	}
	public String getProductSupplier_Tel() {
		return ProductSupplier_Tel;
	}
	public void setProductSupplier_Tel(String productSupplierTel) {
		ProductSupplier_Tel = productSupplierTel;
	}
	public String getProductSupplier_Cell() {
		return ProductSupplier_Cell;
	}
	public void setProductSupplier_Cell(String productSupplierCell) {
		ProductSupplier_Cell = productSupplierCell;
	}
	public String getProductSupplier_Fax() {
		return ProductSupplier_Fax;
	}
	public void setProductSupplier_Fax(String productSupplierFax) {
		ProductSupplier_Fax = productSupplierFax;
	}
	public String getProductSupplier_Address() {
		return ProductSupplier_Address;
	}
	public void setProductSupplier_Address(String productSupplierAddress) {
		ProductSupplier_Address = productSupplierAddress;
	}
	public String getProductSupplier_Remark() {
		return ProductSupplier_Remark;
	}
	public void setProductSupplier_Remark(String productSupplierRemark) {
		ProductSupplier_Remark = productSupplierRemark;
	}
	public Integer getProductSupplier_key() {
		return ProductSupplier_key;
	}
	public void setProductSupplier_key(Integer productSupplierKey) {
		ProductSupplier_key = productSupplierKey;
	}
	
	
}
