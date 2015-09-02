package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.Repertory;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.util.Page;

public interface RepertoryManageService {

	/**
	 * 保存库存记录
	 * @param R
	 */
	public void saveRepertory(Repertory R);

	/**
	 * 删除库存记录
	 * @param R
	 * @return
	 */
	public String delRepertory(Repertory R);
	/**
	 * 修改库存信息
	 * @param R
	 * @return
	 */
	public boolean updateRepertory(Repertory R);
	/**
	 * 签核库存信息
	 * @param sql
	 * @return
	 */
	public boolean signRepertory(Repertory R);
	/**
	 * 查询库存记录
	 * @param start 开始时间
	 * @param end	结束时间	
	 * @param ProductType  产品类型 0-油       1-气
	 * @param page	分页类
	 * @return
	 */
	public List<Object> selRepertory(String start, String end,
			String ProductType, Page page);

	public List<Repertory> selRepertory(String start, String end,
			String ProductType);
	/**
	 * 查询库存记录（精确）
	 * @param ShiftNo-班别编号
	 * @param page
	 * @return
	 */
	public List<Object> selRepertory(String ShiftNo,Page page);
	/**
	 * 查询产品对应灌
	 * @param Tanksetting_ProductNum
	 * @return
	 */
	public List<Tanksetting> selTank(String Tanksetting_ProductNum);

	/**
	 * 当前班别编号-库存表 （站点编号6 年月日 6  班别2）
	 * @param StationNo 站点编号
	 * @return
	 */
	public String currentShiftNo(String StationNo);
	
	
	/**
	 * 查询最近一次开班记录
	 * @param Shift_StationNo 站点编号
	 * @return
	 */
	
	public List<Shift> selCurrentShift(String Shift_StationNo);
	
	/**
	 * 根据产品号 油罐号查询油罐信息
	 * @param tanksetting_ProductNum
	 * @param tankIds
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月2日
	 */
	public List<Tanksetting> selTank(String tanksetting_ProductNum,List<Integer> tankIds);

}