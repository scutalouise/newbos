package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.util.Page;

public interface SellingTargetDao {

	/**
	 * 查询能耗指标
	 * @param start	计划时间
	 * @param end	计划时间
	 * @param ProductName	产品名称	
	 * @return
	 */
	
	public List<Object> selSellTarget(String start, String end,
			String ProductName);

	
	public List<Object> selSellTarget(String start, String end,
			String ProductName, Page page);
	
	public void deleteSQL(String s);
	
	public void saveObject(Object o) ;
	
	public void updateSQL(String s);
	
	public void updateObject(Object o) ;

}