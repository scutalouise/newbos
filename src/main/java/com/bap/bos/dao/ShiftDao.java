package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.NozzleShift;
import com.bap.bos.domain.SellPrice;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.TransItem;
import com.bap.bos.util.Page;

public interface ShiftDao {

	/**
	 * 查询班结审核情况
	 * @param start 开班日期
	 * @param end	开班日期
	 * @return
	 */
	
	public List<Object> selShiftDetail(String start, String end);

	
	public List<Object> selShiftDetail(String start, String end, Page page);
	/**
	 * 查询每班交易详情
	 * @param TransItem_ShiftDate 班别日期
	 * @param TransItem_ShiftNo	班别编号	
	 * @param TransItem_NozzleNo	枪号
	 * @param Shift_StartTime		开始时间
	 * @param Shift_EndTime		结束时间
	 * @return
	 */
	
	public List<TransItem> selShiftTransItem(String TransItem_ShiftDate,String TransItem_ShiftNo,
			String TransItem_NozzleNo,String Shift_StartTime,String Shift_EndTime);
	/**
	 * 油枪交班泵码记录
	 * @param NozzleShift_ShiftDate		开班日期
	 * @param NozzleShift_ShiftNo	 	班别编号
	 * @param NozzleShift_CreateTime	创建时间
	 * @return
	 */
	
	public List<NozzleShift> selShiftNozzle(String NozzleShift_ShiftDate,String NozzleShift_ShiftNo,
			String NozzleShift_CreateTime);
	/**
	 * 查询当前产品价格
	 * @param Phy_Noz	物理枪号
	 * @return
	 */
	
	public List<SellPrice> selSellPrice(String Phy_Noz);
	/**
	 * 班结审核通过更新
	 * @param StationNo	站点编号
	 * @param ShiftDate	班别日期
	 * @param ShiftNo	辨别编号
	 * @param staffNo	审核员工编号
	 */
	
	public void shiftVerify(String StationNo,String ShiftDate,String ShiftNo,String staffNo,String Shift_CreateTime);
	/**
	 * 查询一天的班别信息
	 * @param ShiftDate
	 * @return
	 */
	
	public List<Shift> selDayShift(String ShiftDate);
	
	/**
	 * 获取最近的有效的班接日期
	 * @param nowDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月12日
	 */
	String getTheLatestAffectiveVerifyDate(String Shift_StationNo,String endDate);
	
	/**
	 * 获取大于等于参数日期的最久远的有效班接日期
	 * @param Shift_StationNo
	 * @param shiftdate
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月21日
	 */
	String getTheFurthestAffectiveVerifyDate(String Shift_StationNo,String shiftdate);
	
	/**
	 * 获取最久远的有效的班接日期
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月12日
	 */
	String getTheFurthestAffectiveVerifyDate(String Shift_StationNo);
	
	/**
	 * 获取两个ymd 之间的其他班接年月日
	 * @param startShiftDate
	 * @param endShiftDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月13日
	 */
	List<String> getShiftDateListByDateSpan(String startShiftDate,String endShiftDate);
	
	/**
	 * 获取shiftdate<=enddate的有效班接数据的最近一条
	 * @param Shift_StationNo
	 * @param endDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	Shift getTheLatestAffectiveVerifyShift(String Shift_StationNo,String endDate);
	
	/**
	 *  获取最近的班
	 * @param Shift_StationNo
	 * @param endDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月18日
	 */
	public Shift getTheLatestShift(String Shift_StationNo,String endDate);

}