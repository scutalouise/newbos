package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.Repertory;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.util.Page;

public interface RepertoryManageDao {

	/**
	 * 保存库存信息
	 * @param ds
	 */
	
	public void saveRepertory(Repertory R);

	/**
	 * 删除库存信息
	 * @param ds
	 */
	
	public void delRepertory(Repertory R);
	/**
	 * 修改库存信息
	 * @param R -库存实体
	 */
	
	public void updateRepertory(Repertory R);
	/**
	 * SQL修改库存信息
	 * @param sql
	 */
	
	public void updateSQLRepertory(String sql);
	/**
	 * 查询库存信息
	 * @param start 开始时间
	 * @param end	结束时间	
	 * @param ProductType 产品类型
	 * @return
	 */
	
	public List<Repertory> selRepertory(String start, String end,
			String ProductType);

	
	public List<Object> selRepertory(String start, String end,
			String ProductType, Page page);
	/**
	 * 查询库存表
	 * @param ShiftNo - 班别编号
	 * @param page - 分页类
	 * @return
	 */
	
	public List<Object> selRepertory(String ShiftNo,Page page);
	/**
	 * 查询油罐信息-相同产品的油罐
	 * @param Tanksetting_ProductNum 产品编号
	 * @return
	 */
	
	public List<Tanksetting> selTank(String Tanksetting_ProductNum);
	/**
	 * 查询最近一次开班记录
	 * @param Shift_StationNo 站点编号
	 * @return
	 */
	
	public List<Shift> selCurrentShift(String Shift_StationNo);
	
	/**
	 * 查询油罐信息
	 * @param Tanksetting_ProductNum 产品编号
	 * @return
	 */
	public List<Tanksetting> selTank(String tanksetting_ProductNum,List<Integer> tankIds);
	
	/**
	 * 获取某个班的库存信息
	 * @param shift
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	 List<Object[]> getByShiftNoAndProduct(Shift shift);

}