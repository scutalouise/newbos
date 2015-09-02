package com.bap.bos.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.domain.DailySourceed;
import com.bap.bos.domain.SourceUsed;
import com.bap.bos.util.Page;

public interface SourceDao {

	/**
	 * 保存每日能耗信息
	 * @param ds
	 */
	
	public void saveDailySourceed(DailySourceed ds);

	/**
	 * 删除每日能耗信息
	 * @param ds
	 */
	
	public void delDailySourceed(DailySourceed ds);

	/**
	 * 更新每日能耗信息
	 * @param sql
	 */
	
	public void updateDailySourceed(String sql);

	/**
	 * 查询每日能耗信息
	 * @param start
	 * @param end
	 * @return
	 */
	
	public List<DailySourceed> selSourceed(String start, String end);
	
	
	public List<DailySourceed> selSourceed(String start,String end,Page page);
	/**
	 * 查询每月能耗计划
	 * @param date 年月201310
	 * @param StationNo 气站编号
	 * @return
	 */
	
	public List<SourceUsed> selSourcePlan(String date);
	/**
	 * 查询能耗计划
	 * @param start	年月201310
	 * @param end	年月201310
	 * @return
	 */
	
	public List<SourceUsed> selSourcePlan(String start,String end);
	
	public List<SourceUsed> selSourcePlan(String start,String end,Page page);

}