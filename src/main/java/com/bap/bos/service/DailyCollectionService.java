package com.bap.bos.service;

import java.util.List;

import com.bap.bos.web.pojo.DailyCollection;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月8日
 */
public interface DailyCollectionService {
	 
	/**
	 * 根据年月日和班号 获取日报展现数据
	 * @param ymd
	 * @param shiftNo
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月8日
	 */
	List<DailyCollection> getDataForShow(String ymd,String shiftNo);
}
