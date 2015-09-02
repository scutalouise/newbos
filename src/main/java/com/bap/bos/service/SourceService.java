package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.DailySourceed;
import com.bap.bos.domain.SourceUsed;
import com.bap.bos.util.Page;

public interface SourceService {
	/**
	 * 保存每日能耗
	 * @param ds
	 * @return
	 * @throws Exception
	 */
	public String saveDailySourceed(DailySourceed ds)  throws Exception;
	
	/**
	 * 能耗查询
	 * @param start 开始日期
	 * @param end	结束日期
	 * @param page	分页属性
	 * @return List<DailySourceed>
	 */
	public List<DailySourceed> selSourceed(String start,String end,Page page);
	/**
	 * 能耗修改
	 * @param ds
	 * @return
	 */
	public String updateDailySourceed(DailySourceed ds,String Date) ;
	/**
	 * 删除能耗
	 * @param ds
	 * @param Date
	 * @return
	 */
	public String delDailySourceed(DailySourceed ds,String Date);
	/**
	 * 每日能耗签核
	 * @param StationNo	站级编号
	 * @param Date	能耗日期
	 * @return
	 */
	public boolean signDailySourceed(String StationNo,String Date);



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