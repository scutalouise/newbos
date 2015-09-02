package com.bap.bos.dao;

import java.util.List;
import java.util.Map;

/**
 * 油枪交班泵码记录
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月8日
 */
public interface NozzleShiftDao {

	/**
	 * 按班接日期 班号 油品获取泵码卷积
	 * @param shiftdate
	 * @param shiftNo
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月8日
	 */
	List<Map<String,Object>> getGroupedNozzleVal(String shiftdate,String shiftNo);

}