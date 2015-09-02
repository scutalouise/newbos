package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.SettingSellPrice;
import com.bap.bos.util.Page;

public interface SettingSellPriceDao {

	/**
	 * 查询变价结果
	 * @param start 变价时间
	 * @param end	变价时间
	 * @param ProductType	产品类型
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selSellPrice(String start,String end,String ProductType,Page page);
	
	public List<Object> selSellPrice(String start,String end,String ProductType);
	/**
	 * 查询变价结果-精确查询
	 * @param ProductName 产品名称
	 * @return
	 */
	
	public List<Object> selSellPrice(String ProductName);
	
	public List<Object> selSellPrice(String ProductName,Page page);
	
	/**
	 * 保存变价计划
	 * @param SettingSellPrice
	 */
	
	public void saveSellPricePlan(SettingSellPrice SettingSellPrice);
	/**
	 * 删除变价计划
	 * @param SettingSellPrice
	 */
	
	public void delSellPricePlan(SettingSellPrice SettingSellPrice);
	/**
	 * 查询变价计划
	 * @param start	开始时间
	 * @param end	结束时间
	 * @param ProductNum	产品编号
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selSellPricePlan(String start,String end,String ProductNum,Page page);
	
	public List<Object> selSellPricePlan(String start,String end,String ProductNum);

	
	/**
	 * 查询变价计划
	 * @param start	开始时间
	 * @param end	结束时间
	 * @param ProductName	产品名称 
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selSellPriceByProductName(String start,String end,String ProductName,Page page);
	public List<Object> selSellPriceByProductName(String start,String end,String ProductName);
}