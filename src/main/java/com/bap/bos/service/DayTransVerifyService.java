package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.util.Page;

public interface DayTransVerifyService {
	/**
	 * 保存-日结作业记录（定时器每天00:00:00执行）
	 */
	public void saveDayTransVerify();
	/**
	 * 更新日结作业信息
	 * @param DayTransVerify
	 */
	public void updateDayTransVerify(DayTransVerify DayTransVerify,String[] dateStrArr) throws Exception;

	/**
	 * 查询日结记录信息
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> selDayTransVerify(String start, String end);

	public List<Object> selDayTransVerify(String start, String end,
			Page page);
	
}