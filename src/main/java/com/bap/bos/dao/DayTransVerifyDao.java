package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.util.Page;

public interface DayTransVerifyDao {

	/**
	 * 保存日结记录
	 * @param DayTransVerify 
	 */
	
	public void saveDayTransVerify(DayTransVerify DayTransVerify);

	/**
	 * 更新日结记录-status
	 * @param DayTransVerify
	 * @param Status
	 */
	
	public void updateDayTransVerify(String DayTransVerify, String Status,
			String StaffNo,String isSync);

	/**
	 * 查询日结记录情况
	 * @return
	 */
	
	public List<Object> selDayTransVerify(String start,String end);
	
	public List<Object> selDayTransVerify(String start, String end,
			Page page);

	
	boolean exists(DayTransVerify DayTransVerify);
	
	/**
	 * 获取最近一条数据的日结日期
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月12日
	 */
	String getCurrentTransVerifyDate();
	/**
	 * 获取最远一条数据的日结日期
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月12日
	 */
	public String getFarestTransVerifyDate();
}