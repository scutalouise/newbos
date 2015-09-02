package com.bap.bos.service;

import java.util.Date;
import java.util.List;

import com.bap.bos.domain.TransItem;

/**
 * 
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年3月16日
 */
public interface TranItemService {
	/**
	 * 按班次进行报表查询
	 * 
	 * @param TransItem_ShiftDate
	 * @param TransItem_ShiftNo
	 * @return
	 */

	public List<TransItem> selTransItem(String TransItem_ShiftDate,
			String TransItem_ShiftNo);

	/**
	 * 班结交易补录
	 * 
	 * @param TransItem
	 *            交易类
	 */

	public void saveTransItem(TransItem TransItem);

	/**
	 * 按时间查询交易
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */

	public List<TransItem> selTransItemDate(Date startDate, Date endDate);
}
