package com.bap.bos.web.pojo;

/**
 * 月盘点报表
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年4月8日
 */
public class MonthChecking {
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 上期库存体积
	 */
	private Double prevRepertoryVol;
	/**
	 * 上期库存重量
	 */
	private Double prevRepertoryWeight;
	/**
	 * 本期进油体积
	 */
	private Double currentIncomeVol;
	/**
	 * 本期进油重量
	 */
	private Double currentIncomeWeight;
	/**
	 * 本期销售体积
	 */
	private Double currentSellVol;
	/**
	 * 本期销售重量
	 */
	private Double currentSellWeight;
	/**
	 * 本期剩余体积
	 */
	private Double currentBalanceVol;
	/**
	 * 本期剩余重量
	 */
	private Double currentBalanceWeight;
	/**
	 * 回罐体积
	 */
	private Double innerTransferVol;
	/**
	 * 回罐重量
	 */
	private Double innerTransferWeight;
	/**
	 * 罐内油高
	 */
	private Double tankOilHeight;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrevRepertoryVol() {
		return prevRepertoryVol;
	}

	public void setPrevRepertoryVol(Double prevRepertoryVol) {
		this.prevRepertoryVol = prevRepertoryVol;
	}

	public Double getPrevRepertoryWeight() {
		return prevRepertoryWeight;
	}

	public void setPrevRepertoryWeight(Double prevRepertoryWeight) {
		this.prevRepertoryWeight = prevRepertoryWeight;
	}

	public Double getCurrentIncomeVol() {
		return currentIncomeVol;
	}

	public void setCurrentIncomeVol(Double currentIncomeVol) {
		this.currentIncomeVol = currentIncomeVol;
	}

	public Double getCurrentIncomeWeight() {
		return currentIncomeWeight;
	}

	public void setCurrentIncomeWeight(Double currentIncomeWeight) {
		this.currentIncomeWeight = currentIncomeWeight;
	}

	public Double getCurrentSellVol() {
		return currentSellVol;
	}

	public void setCurrentSellVol(Double currentSellVol) {
		this.currentSellVol = currentSellVol;
	}

	public Double getCurrentSellWeight() {
		return currentSellWeight;
	}

	public void setCurrentSellWeight(Double currentSellWeight) {
		this.currentSellWeight = currentSellWeight;
	}

	public Double getCurrentBalanceVol() {
		return currentBalanceVol;
	}

	public void setCurrentBalanceVol(Double currentBalanceVol) {
		this.currentBalanceVol = currentBalanceVol;
	}

	public Double getCurrentBalanceWeight() {
		return currentBalanceWeight;
	}

	public void setCurrentBalanceWeight(Double currentBalanceWeight) {
		this.currentBalanceWeight = currentBalanceWeight;
	}

	public Double getInnerTransferVol() {
		return innerTransferVol;
	}

	public void setInnerTransferVol(Double innerTransferVol) {
		this.innerTransferVol = innerTransferVol;
	}

	public Double getInnerTransferWeight() {
		return innerTransferWeight;
	}

	public void setInnerTransferWeight(Double innerTransferWeight) {
		this.innerTransferWeight = innerTransferWeight;
	}

	public Double getTankOilHeight() {
		return tankOilHeight;
	}

	public void setTankOilHeight(Double tankOilHeight) {
		this.tankOilHeight = tankOilHeight;
	}

	
}
