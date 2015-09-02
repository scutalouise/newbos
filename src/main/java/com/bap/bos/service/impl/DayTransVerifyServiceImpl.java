package com.bap.bos.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.DayTransVerifyDao;
import com.bap.bos.dao.ShiftDao;
import com.bap.bos.dao.StationDao;
import com.bap.bos.dao.TransCardDao;
import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.service.DayTransVerifyService;
import com.bap.bos.util.Page;

/**
 * 日结作业
 * @author zhulong
 *
 */

@Service
public class DayTransVerifyServiceImpl implements DayTransVerifyService {
	
	Logger logger = LoggerFactory.getLogger(DayTransVerifyServiceImpl.class);
	
	/*日结记录操作*/
	@Resource private DayTransVerifyDao dayTransVerifyDao;
	@Resource private StationDao stationDao;
	@Resource private TransCardDao transCardDao;
	@Resource private ShiftDao shiftDao;
	
	
	/**
	 * 保存-日结作业记录（定时器每天01:00:00执行）
	 * while there is any exception, the transaction will roll back, indicate this task is failing
	 */
	
	@Transactional(rollbackFor=Exception.class)
	public void saveDayTransVerify(){
		String stationNo = stationDao.selStationDetail().get(0).getStation_No();
		String maxCurrentVerifyTimeStr = dayTransVerifyDao.getCurrentTransVerifyDate();
		if(maxCurrentVerifyTimeStr == null){ //如果最近的日结日期没有（通常是系统刚上线的情况） 则查询首次班接日期
			 String farestDateStr = shiftDao.getTheFurthestAffectiveVerifyDate(stationNo);
			 if(farestDateStr == null) return;
			 //因为是要取 即将日结日期的前一天 因此－1
			 DateTime prevFarestDate = DateTime.parse(farestDateStr,  DateTimeFormat.forPattern("yyyyMMdd")).plus(-1);
			 maxCurrentVerifyTimeStr = prevFarestDate.toString(DateTimeFormat.forPattern("yyyyMMdd"));
		}
		
	
		
		DateTime dateTimePast = DateTime.parse(maxCurrentVerifyTimeStr,  DateTimeFormat.forPattern("yyyyMMdd"));
		
		String maxShiftDateStr =  shiftDao.getTheLatestAffectiveVerifyDate(stationNo,DateTime.now().toString(DateTimeFormat.forPattern("yyyyMMdd"))); 
		
		if(maxShiftDateStr == null){
			logger.info("最大有效日结日期（即最近有效班接日期）为空，日结无法继续，退出任务。");
			return;
		}
		
		DateTime maxShiftDate = DateTime.parse(maxShiftDateStr,  DateTimeFormat.forPattern("yyyyMMdd"));
		
		List<String> shiftDateList = shiftDao.getShiftDateListByDateSpan(dateTimePast.plusDays(1).toString( DateTimeFormat.forPattern("yyyyMMdd")),maxShiftDateStr);
		
		Set<String> shiftDateSet = new HashSet<String>();
		for(String shiftDateStr : shiftDateList){
			shiftDateSet.add(shiftDateStr);
		}
		
		int daysDiff = Days.daysBetween(dateTimePast, maxShiftDate).getDays();
		
		logger.info("日结任务将生成于"+dateTimePast+"之后"+daysDiff+"天的日结记录。");
		
		for(int i = 1;i <= daysDiff;i ++){
			String ymdCell = dateTimePast.plusDays(i).toString(DateTimeFormat.forPattern("yyyyMMdd"));
			
			DayTransVerify dayTransVerify=new DayTransVerify();
			dayTransVerify.setDayTransV_StationNo(stationNo);
			dayTransVerify.setDayTransV_SyncDate(new Date());
			dayTransVerify.setDayTransV_Time(ymdCell);
			dayTransVerify.setDayTransV_IsSync("1");
			if(shiftDateSet.contains(ymdCell)){
				dayTransVerify.setDayTransV_Status("2"); //如果存在班接的日期 等待日结
				dayTransVerify.setDayTransV_AutoSure(false);
			}else{
				dayTransVerify.setDayTransV_Status("1"); //否则直接设置为日结成功
				dayTransVerify.setDayTransV_AutoSure(true);
			}
			if(!dayTransVerifyDao.exists(dayTransVerify)){
				dayTransVerifyDao.saveDayTransVerify(dayTransVerify);
			}
		}
		logger.info("日结记录生成完毕！");
	}
	
	/**
	 * 更新日结作业信息
	 * @param DayTransVerify
	 */
	@Transactional
	public void updateDayTransVerify(DayTransVerify dayTransVerify,String[] dateStrArr) throws Exception{
		dayTransVerifyDao.updateDayTransVerify(dayTransVerify.getDayTransV_Time(), dayTransVerify.getDayTransV_Status(),
				dayTransVerify.getDayTransV_VerifyStaffNo(),dayTransVerify.getDayTransV_IsSync());	
		if("1".equals(dayTransVerify.getDayTransV_Status())){
			transCardDao.updateBalanceAcc(dateStrArr);
		}	
	}
	/**
	 * 查询日结记录信息
	 * @param start
	 * @param end
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Object> selDayTransVerify(String start,String end){
		return dayTransVerifyDao.selDayTransVerify(start, end);
	}
	@Transactional(readOnly=true)
	public List<Object> selDayTransVerify(String start,String end,Page page){
		return dayTransVerifyDao.selDayTransVerify(start, end,page);
	}
	
	
	public DayTransVerifyDao getDayTransVerifyDao() {
		return dayTransVerifyDao;
	}
	public void setDayTransVerifyDao(DayTransVerifyDao dayTransVerifyDao) {
		this.dayTransVerifyDao = dayTransVerifyDao;
	}
	public StationDao getStationDao() {
		return stationDao;
	}
	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}
	public TransCardDao getTransCardDao() {
		return transCardDao;
	}
	public void setTransCardDao(TransCardDao transCardDao) {
		this.transCardDao = transCardDao;
	}
	
	
	public static void main(String[] args) {
		DateTime dateTimePast = DateTime.parse("20150228",  DateTimeFormat.forPattern("yyyyMMdd"));
		System.out.println(dateTimePast.plusDays(-1).toString( DateTimeFormat.forPattern("yyyyMMdd")));
	}
}
