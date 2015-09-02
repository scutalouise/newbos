package com.bap.bos.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.bap.bos.components.RepositoryChangedEvent;
import com.bap.bos.dao.DayAndMouthStatisticsDao;
import com.bap.bos.dao.DayTransVerifyDao;
import com.bap.bos.dao.DensityDao;
import com.bap.bos.dao.ShiftDao;
import com.bap.bos.dao.StationDao;
import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.domain.MonthDateAera;
import com.bap.bos.domain.TransDay;
import com.bap.bos.domain.TransMonth;
import com.bap.bos.domain.TransType;
import com.bap.bos.service.DayAndMouthStatisticsService;
import com.bap.bos.service.ShiftVerifyService;
import com.bap.utils.DateTimeAddition;

/**
 * 日、月统计
 * @author long
 *
 */
@Service
public class DayAndMouthStatisticsServiceImpl implements DayAndMouthStatisticsService {
	@Resource
	private DayAndMouthStatisticsDao dayAndMouthStatisticsDao;
	@Resource
	private StationDao stationDao;
	@Resource
	private DayTransVerifyDao dayTransVerifyDao;
	@Resource
	private ShiftDao shiftDao;
	@Resource
	private ShiftVerifyService shiftVerifyService;
	@Autowired
	private ApplicationContext applicationContext;
	@Resource
	private DensityDao densityDao;
	
	
	
	Logger logger = LoggerFactory.getLogger(DayAndMouthStatisticsServiceImpl.class);
	
	/**
	 * 每日统计
	 * 每天23点59分50秒执行
	 */
	@Transactional(rollbackFor=Exception.class)
	public void dayStatistics(){
		logger.info(DateTime.now().toString("yyyy-MM-dd HH:ss:mm")+"日统计开始");
		String nearestTransDay = dayAndMouthStatisticsDao.getlatestTransDayDate();
		
		if(StringUtils.isBlank(nearestTransDay)){ //假如日统计表为空 则表示未做过日统计 获取最久远一条日结日期
			 String farestVerifyDate = dayTransVerifyDao.getFarestTransVerifyDate();
			 if(StringUtils.isBlank(farestVerifyDate)){logger.warn("在日统计表为空的情况下，没有获取到最久远的日结日期，任务退出。");return;}
			 //获取该日期的前一天
			 DateTime prevFarestDay = DateTime.parse(farestVerifyDate, DateTimeFormat.forPattern("yyyyMMdd")).plusDays(-1);
			 nearestTransDay = prevFarestDay.toString(DateTimeFormat.forPattern("yyyyMMdd"));
		}
		
		/**查询已日结确认未日统计的日期**/
		List<String> unStaticDates = dayAndMouthStatisticsDao.getNoStaticDayTransVerify(nearestTransDay);
		
		if(CollectionUtils.isEmpty(unStaticDates)){
			logger.info("没有需要做日统计的数据，日统计退出。");
			return;
		}
		
		/**获取交易类型**/
		List<TransType> transTypes=dayAndMouthStatisticsDao.selTransType();
		/**获取站点编号**/
		String stationNo = stationDao.selStationDetail().get(0).getStation_No();
		/**获取本站油枪号**/
		List<String> nozzleNoList=dayAndMouthStatisticsDao.selNozzleSetting();
		
		for(String unStaticDate : unStaticDates){
			String[] dateArr = shiftVerifyService.getShiftDateSpanAsString(stationNo, unStaticDate);
			if(ArrayUtils.isEmpty(dateArr) || (dateArr[0] == null && dateArr[1] == null)){
				logger.debug(String.format("日期 %s 没有开班记录，不做日统计。",unStaticDate));
				continue;}
			for(TransType transType : transTypes){
				String typeNo= transType.getTransType_Type();
				/**现金01、银行卡03、无卡回罐09、政府卡采购12-交易统计（这几类交易只存在于TransItem表）
				typeNo>12则为新添加交易类型，只存在于TransItem表中**/
				if("01".equals(typeNo)||"03".equals(typeNo)||"09".equals(typeNo)||"12".equals(typeNo)||Integer.valueOf(typeNo)>12){
					for(String nozzleNo : nozzleNoList){
						Object[] volAndAmountAndCount=dayAndMouthStatisticsDao.getTransItemSumVolAndSumAmount(typeNo,nozzleNo,dateArr);
						
						if(volAndAmountAndCount == null){logger.debug("枪号："+nozzleNo+"类型："+typeNo+" 没有查询到统计数据，放弃插入此条数据至日统计。"); continue;}
						
						/**统计**/
						Double vol= new Double(volAndAmountAndCount[0].toString());
						if(vol.doubleValue() == 0d){logger.debug("枪号："+nozzleNo+"类型："+typeNo+" 总升数为0，放弃插入此条数据至日统计。");continue;}  /**如果总升数为0 则默认总金额为0 这种情况就不需要写入数据库了**/
						
						Double payMoney= new Double(volAndAmountAndCount[1].toString());
						
						/**录入统计结果**/
						TransDay DayStatistics =new TransDay();
						DayStatistics.setTransDay_CustomerType(typeNo);
						DayStatistics.setTransDay_NozzleNo(nozzleNo);
						DayStatistics.setTransDay_PaidInMoney(payMoney);
						DayStatistics.setTransDay_TransMoney(payMoney);
						DayStatistics.setTransDay_TransVol(vol);
						DayStatistics.setTransDay_StationNo(stationNo);
						DayStatistics.setTransDay_TransDate(unStaticDate);
						DayStatistics.setTransDay_TransCount(volAndAmountAndCount[2].toString());
						DayStatistics.setTransDay_IsSync("1");
						DayStatistics.setTransDay_SyncDate(new Date());
						dayAndMouthStatisticsDao.saveTransDay(DayStatistics);	
						logger.debug("枪号："+nozzleNo+"类型："+typeNo+" 日统计数据保存成功！");
					}
				}else{
					/**系统卡交易（虚拟卡和IC卡）**/
					for(String nozzleNo : nozzleNoList){
						/**查询日期下    不同卡类型对应的枪号交易**/
						Object[] volAndAmountAndCount = dayAndMouthStatisticsDao.getTransCardSumVolAndSumAmount(typeNo,nozzleNo,dateArr);
						
						if(volAndAmountAndCount == null){logger.debug("枪号："+nozzleNo+"类型："+typeNo+" 没有查询到统计数据，放弃插入此条数据至日统计。"); continue;}
						
						/**统计**/
						Double vol= new Double(volAndAmountAndCount[0].toString());
						
						if(vol.doubleValue() == 0d){logger.debug("枪号："+nozzleNo+"类型："+typeNo+" 总升数为0，放弃插入此条数据至日统计。");continue;} /**如果总升数为0 则默认总金额为0 这种情况就不需要写入数据库了**/
						
						Double shouldPayMoney= new Double(volAndAmountAndCount[1].toString());
						Double paidMoney= new Double(volAndAmountAndCount[3].toString());
						
						/**录入统计结果**/
						TransDay DayStatistics =new TransDay();
						DayStatistics.setTransDay_CustomerType(typeNo);
						DayStatistics.setTransDay_NozzleNo(nozzleNo);
						DayStatistics.setTransDay_PaidInMoney(paidMoney);
						DayStatistics.setTransDay_TransMoney(shouldPayMoney);
						DayStatistics.setTransDay_TransVol(vol);
						DayStatistics.setTransDay_StationNo(stationNo);
						DayStatistics.setTransDay_TransDate(unStaticDate);
						DayStatistics.setTransDay_TransCount(volAndAmountAndCount[2].toString());
						DayStatistics.setTransDay_IsSync("1");
						DayStatistics.setTransDay_SyncDate(new Date());
						dayAndMouthStatisticsDao.saveTransDay(DayStatistics);
						logger.debug("枪号："+nozzleNo+"类型："+typeNo+" 日统计数据保存成功！");
					}	
				}
			}
		}	
		
		logger.info(DateTime.now().toString("yyyy-MM-dd HH:ss:mm")+"日统计结束");
	}
	
	
	public List<String> compareTransDayAndVerify(List<Object> transDays,List<DayTransVerify> DayTransVerify){
		boolean flag=false;
		List<String> result = new ArrayList<String>();
		for(int i=0;i<DayTransVerify.size();i++){
			flag=false;
			String date=DayTransVerify.get(i).getDayTransV_Time();
			for(int j=0;j<transDays.size();j++){
				if(date.equals(transDays.get(j))){
					flag=true;
					break;
				}
			}
			if(!flag){
				result.add(date);
			}	
		}
		return result;
	}
	
	
	/**
	 * 月统计（ 统计上月）
	 * @throws ParseException 
	 * 每月26号23点55分钟执行
	 */
	@Transactional(rollbackFor=Exception.class)
	public void monthStatistics() throws ParseException{
		MonthDateAera daySpan = dayAndMouthStatisticsDao.getMonthStaticDaySpan(); //获取月统计 日区间
		if(null == daySpan){
			logger.warn("没有发现月结统计时间，月统计结束返回。");
			return;
		}
		
		Assert.notNull(daySpan.getMonthDateAera_StartDate(),"月统计起始天不能为空！");
		Assert.notNull(daySpan.getMonthDateAera_NextEndDate(),"月统计终止天不能为空！");
		int startFactor = Integer.parseInt(daySpan.getMonthDateAera_StartDate()), endFactor = Integer.parseInt(daySpan.getMonthDateAera_NextEndDate());
		
		//寻找最近一个月统计月份
		String statisticalTransMonth = dayAndMouthStatisticsDao.getLatestMonthDay();
		
		if(statisticalTransMonth == null){
			logger.warn("在此之前，未做过任何月统计，将从日统计中查找可月统计的月份......");
			statisticalTransMonth = dayAndMouthStatisticsDao.getFarestTransDayDate();
			if(statisticalTransMonth == null){logger.warn("在日统计中未发现任何可作为月统计的数据，任务退出！");return ;}
			//如果查询到有需要用于月统计的的日统计数据 则获取该月的上一月 作为月统计的截止月份
			else{statisticalTransMonth = DateTime.parse(statisticalTransMonth, DateTimeFormat.forPattern("yyyyMMdd")).plusMonths(-1).toString(DateTimeFormat.forPattern("yyyyMM"));}
		}
		
		String cutOffMonth = null;//月统计截止月份
		
		if(DateTime.now().getDayOfMonth() < Integer.valueOf(daySpan.getMonthDateAera_NextEndDate())){
			cutOffMonth = DateTime.now().plusMonths(-1).toString(DateTimeFormat.forPattern("yyyyMM"));
		}else{
			cutOffMonth = DateTime.now().toString(DateTimeFormat.forPattern("yyyyMM"));
		}
		
		DateTime statisticalTransMonthDateTime = DateTime.parse(statisticalTransMonth, DateTimeFormat.forPattern("yyyyMM"));
		DateTime cutOffMonthDateTime = DateTime.parse(cutOffMonth, DateTimeFormat.forPattern("yyyyMM"));
		
		int diffMonth = Months.monthsBetween(statisticalTransMonthDateTime,cutOffMonthDateTime).getMonths();
		
		logger.info(String.format("系统检测到自%s后共%d个月需要做月统计。",statisticalTransMonth,diffMonth));
		
		String statisticMonth = null;
		for(int i = 1;i <= diffMonth;i ++){
			DateTime startStatisticDay = statisticalTransMonthDateTime.plusMonths(i-1).plusDays(startFactor-1); //由于月化datetime 初始天为1 +factor时则需要-1
			DateTime endStatisticDay = statisticalTransMonthDateTime.plusMonths(i).plusDays(endFactor-2); //默认处理到设置天数的前一天
			statisticMonth = endStatisticDay.toString(DateTimeFormat.forPattern("yyyyMM"));
			if(haveDoneMonthStatistic(statisticMonth)){
				logger.info("系统检测到该月已经做过月结统计，任务退出。");
				return;
			}
			logger.info(statisticMonth+" 月统计开始，日期段："+startStatisticDay+"-"+endStatisticDay);
			List<Object[]> staticResults = dayAndMouthStatisticsDao.getTransDaySumVolAndSumAmountAndCount(
					startStatisticDay.toString(DateTimeFormat.forPattern("yyyyMMdd")), endStatisticDay.toString(DateTimeFormat.forPattern("yyyyMMdd")));
			String stationNo=stationDao.selStationDetail().get(0).getStation_No();
			
			for(Object[] staticResult : staticResults){
				TransMonth transMonth = new TransMonth();
				transMonth.setTransMonth_StationNo(stationNo);
				transMonth.setTransMonth_TransDate(statisticMonth);
				transMonth.setTransMonth_CustomerType(staticResult[1].toString());
				transMonth.setTransMonth_NozzleNo(staticResult[0].toString());
				transMonth.setTransMonth_PaidInMoney(new Double(staticResult[4].toString()));
				transMonth.setTransMonth_TransMoney(new Double(staticResult[3].toString()));
				transMonth.setTransMonth_TransVol(new Double(staticResult[2].toString()));
				transMonth.setTransMonth_TransCount(staticResult[5].toString());
				transMonth.setTransMonth_IsSync("1");
				transMonth.setTransMonth_SyncDate(new Date());
				dayAndMouthStatisticsDao.saveMonthDay(transMonth);
			}
			logger.info(statisticMonth+" 月统计结束！");
		}
		
		
	}
	
	
	public void initDensity(){
		
		if(densityDao.count() == 0){
			logger.info("触发任务：生成下月平均密度……");
			applicationContext.publishEvent(new RepositoryChangedEvent(DateTime.now().toString(DateTimeAddition.FORMAT_DATE_0)));
		}
	}
	
	/**
	 * 是否做过月统计
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月15日
	 */
	private boolean haveDoneMonthStatistic(String ym){
		return dayAndMouthStatisticsDao.haveDoneMonthStatistic(ym);
	}
	
	
	public DayAndMouthStatisticsDao getDayAndMouthStatisticsDao() {
		return dayAndMouthStatisticsDao;
	}
	public void setDayAndMouthStatisticsDao(
			DayAndMouthStatisticsDao dayAndMouthStatisticsDao) {
		this.dayAndMouthStatisticsDao = dayAndMouthStatisticsDao;
	}
	public StationDao getStationDao() {
		return stationDao;
	}
	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}
	public ShiftDao getShiftDao() {
		return shiftDao;
	}
	public void setShiftDao(ShiftDao shiftDao) {
		this.shiftDao = shiftDao;
	}
	
}
