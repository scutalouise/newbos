package com.bap.bos.web.pojo;

/**
 * 月盘点报表
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年4月8日
 */
public class MonthCheckingDetail {
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
	 * 公司自用
	 */
	private Double selfUserForCompanyVol;
	/**
	 * 回罐
	 */
	private Double innerTransVol;
	/**
	 * 赊消
	 */
	private Double creditConsumeVol;
	/**
	 * 预存
	 */
	private Double preStoreVol;
	/**
	 * 优惠卡
	 */
	private Double preferentialCardVol;
	/**
	 * 现金
	 */
	private Double cashVol;
	/**
	 * 其他
	 */
	private Double otherVol;
	/**
	 * 本期剩余体积
	 */
	private Double surplusVol;
	
	/**
	 * 本期剩余重量
	 */
	private Double surplusWeight;
	
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
	public Double getSelfUserForCompanyVol() {
		return selfUserForCompanyVol;
	}
	public void setSelfUserForCompanyVol(Double selfUserForCompanyVol) {
		this.selfUserForCompanyVol = selfUserForCompanyVol;
	}
	public Double getInnerTransVol() {
		return innerTransVol;
	}
	public void setInnerTransVol(Double innerTransVol) {
		this.innerTransVol = innerTransVol;
	}
	public Double getCreditConsumeVol() {
		return creditConsumeVol;
	}
	public void setCreditConsumeVol(Double creditConsumeVol) {
		this.creditConsumeVol = creditConsumeVol;
	}
	public Double getPreStoreVol() {
		return preStoreVol;
	}
	public void setPreStoreVol(Double preStoreVol) {
		this.preStoreVol = preStoreVol;
	}
	public Double getPreferentialCardVol() {
		return preferentialCardVol;
	}
	public void setPreferentialCardVol(Double preferentialCardVol) {
		this.preferentialCardVol = preferentialCardVol;
	}
	public Double getCashVol() {
		return cashVol;
	}
	public void setCashVol(Double cashVol) {
		this.cashVol = cashVol;
	}
	public Double getOtherVol() {
		return otherVol;
	}
	public void setOtherVol(Double otherVol) {
		this.otherVol = otherVol;
	}
	public Double getSurplusVol() {
		return surplusVol;
	}
	public void setSurplusVol(Double surplusVol) {
		this.surplusVol = surplusVol;
	}
	public Double getSurplusWeight() {
		return surplusWeight;
	}
	public void setSurplusWeight(Double surplusWeight) {
		this.surplusWeight = surplusWeight;
	}
	
}
