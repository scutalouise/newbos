package com.bap.bos.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bap.bos.domain.TransItem;

public interface TranItemDao {

	/**
	 * 按班次进行报表查询
	 * @param TransItem_ShiftDate 
	 * @param TransItem_ShiftNo
	 * @return
	 */
	
	public List<TransItem> selTransItem(String TransItem_ShiftDate,
			String TransItem_ShiftNo);
	/**
	 * 班结交易补录
	 * @param TransItem	交易类
	 */
	
	public void saveTransItem(TransItem TransItem);
	/**
	 * 按时间查询交易
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	
	public List<TransItem> selTransItemDate(Date startDate,Date endDate);
	
	List<Map<String, Object>> getGroupedTransItemDate(String shiftDate,String shiftNo,String tend);

	List<Map<String, Object>> getGroupedTransItemDate(String shiftDate,String shiftNo);
	
	/**
	 * 获取某个班接日期的某个班 变价时的油枪泵码值
	 * @param shiftDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月19日
	 */
	List<Object[]> getNozzlePumpValOnSellPriceChanged(String shiftDate,String shiftNo);
}