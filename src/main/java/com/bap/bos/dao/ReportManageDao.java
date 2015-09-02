package com.bap.bos.dao;

import java.text.ParseException;
import java.util.List;

import com.bap.bos.domain.Shift;
import com.bap.bos.util.SellDetails;

public interface ReportManageDao {

	/**
	 * 查询开班记录
	 * 
	 * @param Shift_StationNo
	 *            开班日期
	 * @return
	 */
	
	public List<Shift> selCurrentShift(String Shift_StationNo,
			String Shift_ShiftDate);

	/**
	 * 交易详情
	 * 
	 * @param startDate
	 *            - 开始时间
	 * @param endDate
	 *            - 结束时间
	 * @param staffNo
	 *            员工编号
	 * @param cardNo
	 *            客户卡号
	 * @param nozzleNo
	 *            枪号
	 * @return
	 */
	
	public List<SellDetails> sellDetails(String startDate, String endDate,
			String staffNo, String cardNo, String nozzleNo)throws ParseException;
	
	/**
	 * 返回最近的一条班接数据
	 * @param Shift_StationNo
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月10日
	 */
	Shift selLatestShift(String Shift_StationNo);
	
}