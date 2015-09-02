package com.bap.bos.service;

import java.util.Date;
import java.util.List;

import com.bap.bos.util.Page;
import com.bap.bos.util.ShiftVerify;

public interface ShiftVerifyService {

	/**
	 * 查询班别详情
	 * @param start 开班日期
	 * @param end	开班日期
	 * @return
	 */
	public List<Object> selShiftDetail(String start, String end);

	public List<Object> selShiftDetail(String start, String end, Page page);
	/**
	 * 查询班别油枪详情
	 * @param TransItem_ShiftDate	班别日期
	 * @param TransItem_ShiftNo	班别编号
	 * @param TransItem_NozzleNo	油枪编号
	 * @return
	 */
	public List<ShiftVerify> selShiftNozzleDetails(String ShiftDate,String ShiftNo,
			String NozzleShift_CreateTime,String Shift_StartTime,String Shift_EndTime,String stationNo);
	/**
	 * 查询产品当前价格
	 * @param Phy_Noz	物理枪号
	 * @return
	 */
	public float SellPrice(String Phy_Noz);
	/**
	 * 班结审核通过更新
	 * @param StationNo	站点编号
	 * @param ShiftDate	班别日期
	 * @param ShiftNo	辨别编号
	 * @param staffNo	审核员工编号
	 */
	public void shiftVerify(String StationNo,String ShiftDate,String ShiftNo,String staffNo,String Shift_CreateTime);
	
	/**
	 * 根据参数日期 获取当天班接开始 结束时间
	 * @param dateStr 日期字符串
	 * @param Shift_StationNo 站点号
	 * @return
	 * @author edgar_chan
	 * @since 2015年3月24日
	 */
	public Date[] getShiftDateSpan(String Shift_StationNo,String dateStr);
	
	/**
	 * 获取一个日期段的班接最前时间 和最后时间
	 * 默认 endDateStr > dateStr
	 * @param Shift_StationNo
	 * @param dateStr
	 * @param endDateStr
	 * @return
	 * @author edgar_chan
	 * @since 2015年3月25日
	 */
	public Date[] getShiftDateSpan(String Shift_StationNo,String dateStr,String endDateStr);
	
	
	/**
	 * 根据参数日期 获取当天班接开始 结束时间
	 * @param Shift_StationNo
	 * @param dateStr
	 * @return
	 * @author edgar_chan
	 * @since 2015年3月25日
	 */
	public String[] getShiftDateSpanAsString(String Shift_StationNo,String dateStr);
	
	/**
	 * 获取一个日期段的班接最前时间 和最后时间
	 * @param Shift_StationNo
	 * @param dateStr
	 * @param endDateStr
	 * @return
	 * @author edgar_chan
	 * @since 2015年3月25日
	 */
	public String[] getShiftDateSpanAsString(String Shift_StationNo,String dateStr,String endDateStr);
}