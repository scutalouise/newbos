package com.bap.bos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bap.bos.components.RepositoryChangedEvent;
import com.bap.bos.dao.DayAndMouthStatisticsDao;
import com.bap.bos.dao.DensityDao;
import com.bap.bos.dao.ProductDao;
import com.bap.bos.dao.RepertoryManageDao;
import com.bap.bos.dao.ReportManageDao;
import com.bap.bos.dao.RestockBillDao;
import com.bap.bos.dao.ShiftDao;
import com.bap.bos.dao.StationDao;
import com.bap.bos.domain.Density;
import com.bap.bos.domain.MonthDateAera;
import com.bap.bos.domain.Product;
import com.bap.bos.domain.Shift;
import com.bap.bos.service.DensityService;
import com.bap.bos.util.Page;
import com.bap.utils.DateTimeAddition;

/**
 * 
 * @author edgar_chan    lineshow7@gmail.com
 * @since 2015-3-16
 */
@Service
public class DensityServiceImpl implements DensityService,ApplicationListener<RepositoryChangedEvent>{
	
	@Resource
	private DensityDao densityDao; 
	@Resource
	private RepertoryManageDao repertoryManageDao; 
	@Resource
	private DayAndMouthStatisticsDao dayAndMouthStatisticsDao; 
	@Resource
	private ReportManageDao reportManageDao; 
	@Resource
	private StationDao stationDao; 
	@Resource
	private ShiftDao shiftDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private RestockBillDao restockBillDao;
	
	

	@Override
	@Transactional
	public String saveDensity(Density density) {
		if(densityDao.existConfictingDensityDateSpan(density)){
			return "conflicting";
		}
		densityDao.saveDensity(density);
		return "success";
				
	}
	

	@Override
	@Transactional
	public void deleteDensity(int density_ID) {
		densityDao.deleteDensity(density_ID);
	}
	

	@Override
	@Transactional
	public void deleteDensity(String stationNo,String startDate,String productNum) {
		densityDao.deleteDensity(stationNo,startDate,productNum);
	}
	

	@Override
	@Transactional
	public String updateDensity(Density density) {
		if(!reasonableCorrectedDensity(density)){
			return "unreasonable";
		}
		densityDao.updateDensity(density);
		return "success";
	}

	/**
	 * 新的密度必须与原密度差值不大于10%
	 * @param density
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月4日
	 */
	private boolean reasonableCorrectedDensity(Density density){
		Double oldDensity = density.getDensity_Density();
		
		if(oldDensity == 0d) return true;
		
		Double newDensity = density.getDensityManual();
		
		Double minLimit = oldDensity * 0.9;
		Double maxLimit = oldDensity * 1.1;
		
		return (newDensity >= minLimit && newDensity <= maxLimit);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Object> selDensity(String density_ProdunctNum, Page page) {
		return densityDao.selDensity(density_ProdunctNum,page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selDensity(String density_ProdunctNum) {
		return densityDao.selDensity(density_ProdunctNum);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Density> get(Map<String,Object> params,Page page) {
		return densityDao.get(params, page);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public int getTotalRowNum(Map<String,Object> params) {
		return densityDao.get(params);
	}

	/**
	 * 根据传入日期生成影响月的初始平均密度
	 * @param densityAffectDateStr
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	@Transactional
	public void generateMonthAreaDensity(String densityAffectDateStr) throws Exception {
		/**获取站点编号**/
		String stationNo = stationDao.selStationDetail().get(0).getStation_No();
		
		List<DateTime[]> twoMonthAreaDateTimeList = getDensityAffectedMonths(densityAffectDateStr,stationNo);
		
		if(CollectionUtils.isEmpty(twoMonthAreaDateTimeList)){
			return ;
		}
			
		
		Iterator<DateTime[]> it = twoMonthAreaDateTimeList.iterator();
		if(twoMonthAreaDateTimeList.size() == 2){
			generateDensity(it.next(),stationNo);
			generateDensity(it.next(),stationNo);
		}else{
			generateDensity(it.next(),stationNo);
		}
	}
	
	/**
	 * 生成月区间的初始平均密度
	 * @param monthSpan
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	public void generateDensity(DateTime[] monthSpan,String stationNo){
		List<Product> productList =	productDao.selProductDetail();
		
		Map<String,Double[]> amountVolMap = getProductAmountMap(monthSpan);
		Map<String,Double[]> densitesMap = getDensitiesMap(monthSpan,true);
		
		
		for(Product product : productList){
			Double newDensity = calculateNewDensity(product,amountVolMap,densitesMap);
			Density density = new Density();
			density.setDensity_Density(newDensity);
			density.setDensity_StartDate(monthSpan[0].toDate());
			density.setDensity_endDate(monthSpan[1].toDate());
			density.setDensity_IsSync("0");
			density.setDensity_ProdunctNum(product.getProduct_Num());
			density.setDensity_SyncDate(new Date());
			density.setDensity_StationNo(stationNo);
			densityDao.saveDensity(density);
		}
	}
	
	/**
	 * 计算平均密度
	 * 本期平均密度=（本期进油累积升数+上期结余升数）/(本期进油累计重量+上期结余重量)
	 * @param product
	 * @param amountVolMap
	 * @param densitesMap
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	private Double calculateNewDensity(Product product,Map<String,Double[]> amountVolMap,Map<String,Double[]> densitesMap){
		
		Double[] volAmounts = amountVolMap.get(product.getProduct_Num());
		Double[] densities = densitesMap.get(product.getProduct_Num());
		
		if(volAmounts == null)  volAmounts = new Double[]{0d,0d};
		if(densities == null)  densities = new Double[]{0d,0d};
		
		/*上期库存体积*/
		BigDecimal repertoryBalance = new BigDecimal(volAmounts[0] == null ? 0 : volAmounts[0]);
		/*本期进油体积*/
		BigDecimal stockAccumulate = new BigDecimal(volAmounts[1] == null ? 0 : volAmounts[1]);
		
		/*本期密度*/
		BigDecimal currentDensity = new BigDecimal(densities[0] == null ? 0 : densities[0]);
		/*上期密度*/
		BigDecimal prevDensity = new BigDecimal(densities[1] == null ? 0 : densities[1]);
		
		BigDecimal dividedNum = new BigDecimal(0);
		if(currentDensity.doubleValue() != 0d)
			dividedNum = dividedNum.add(stockAccumulate.divide(currentDensity,4,RoundingMode.HALF_DOWN));
		if(prevDensity.doubleValue() != 0d)		
			dividedNum = dividedNum.add(repertoryBalance.divide(prevDensity,4,RoundingMode.HALF_DOWN));
		if(dividedNum.doubleValue() == 0d) return 0d;
		/*体积和除以重量和*/
		BigDecimal newDensity = (stockAccumulate.add(repertoryBalance)).divide(dividedNum,4,RoundingMode.HALF_DOWN);
		
		return newDensity.doubleValue();
	}
	
	
	/**
	 * 获取产品本期累积升数  上期节余升数
	 * @param monthSpan
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	public Map<String,Double[]> getProductAmountMap(DateTime[] monthSpan){
		String stationNo = stationDao.selStationDetail().get(0).getStation_No();
		
		/*装载所有产品本期累积升数  上期节余升数*/
		Map<String,Double[]> amountVolMap = new HashMap<String, Double[]>(); 
		
		Shift prevMonthShift = shiftDao.getTheLatestAffectiveVerifyShift(stationNo, monthSpan[0].plusDays(-1).toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0)));
		

		/*本期剩余量及油高*/
		List<Object[]> repertoryList = new ArrayList<Object[]>();
		if(prevMonthShift != null){
			repertoryList = repertoryManageDao.getByShiftNoAndProduct(prevMonthShift);
		}
		
		List<Object[]> restockBillList = restockBillDao.getRestockAmountBySpan(monthSpan);
		
		for(Object[] os : repertoryList){
			amountVolMap.put(os[0].toString(),new Double[]{Double.valueOf(os[1].toString()),0.0d});
		}
		for(Object[] os : restockBillList){
			Double[] amounts = amountVolMap.get(os[0].toString());
			if(amounts == null){
				amounts = new Double[]{0.0d,0.0d};
				amountVolMap.put(os[0].toString(), amounts);
			}
			amounts[1] = Double.valueOf(os[1].toString());
		}
		return amountVolMap;
	}
	
	/**
	 * 获取产品 本月区间 上月区间 有效平均密度
	 * @param monthSpan
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	private Map<String,Double[]> getDensitiesMap(DateTime[] monthSpan,boolean needDeleteCurrentDensity){
		/*装载所有产品密度用于计算*/
		Map<String,Double[]> densitiesMap = new HashMap<String, Double[]>(); 
		List<Density> currentDensityList = densityDao.getDensities(monthSpan);
		List<Density> prevDensityList = densityDao.getDensities(new DateTime[]{monthSpan[0].plusMonths(-1),monthSpan[1].plusMonths(-1)});
		
		for(Density density : currentDensityList){
			densitiesMap.put(density.getDensity_ProdunctNum(), new Double[]{density.getDensityManual() == null?density.getDensity_Density() : density.getDensityManual(),0d});
			if(needDeleteCurrentDensity)
				/*删除本月区间平均密度 等待重新生成*/
				densityDao.delete(density);
		}
		for(Density density : prevDensityList){
			Double[] densities = densitiesMap.get(density.getDensity_ProdunctNum());
			
			if(densities == null){
				densities = new Double[]{0d,0d};
				densitiesMap.put(density.getDensity_ProdunctNum(), densities);
			}
			densities[1] = density.getDensityManual() == null?density.getDensity_Density() : density.getDensityManual();
		}
		
		return densitiesMap;
	}
	
	/**
	 * 获取产品 本月区间 上月区间 有效平均密度
	 * @param monthSpan
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	public Map<String,Double[]> getDensitiesMap(DateTime[] monthSpan){
		return getDensitiesMap(monthSpan,false);
	}
	
	/**
	 * 由参数日期 获取密度受影响的月份（非自然月 详见 MonthDateAera）
	 * @param densityAffectDateStr yyyyMMdd
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	List<DateTime[]> getDensityAffectedMonths(String densityAffectDateStr,String stationNo){
		MonthDateAera daySpan = dayAndMouthStatisticsDao.getMonthStaticDaySpan(); //获取月统计 日区间
		/*获取当前班接日期*/
		Shift currentShift = reportManageDao.selLatestShift(stationNo);
		/*获取当前班接日期 天数*/
		String currentDayStr = StringUtils.substring(currentShift.getShift_ShiftDate(),6,8);
		String currentYM = StringUtils.substring(currentShift.getShift_ShiftDate(),0,6);
		DateTime shiftDateTime = DateTime.parse(currentShift.getShift_ShiftDate(),DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
		
		/*装载当月周期 与 上月周期*/
		List<DateTime[]> twoMonthAreaDateTimeList = new ArrayList<DateTime[]>();
		
		if(Integer.valueOf(currentDayStr) > Integer.valueOf(daySpan.getMonthDateAera_NextEndDate())){
			DateTime[] dateTimeSpan1 = new DateTime[2];
			DateTime startDateTime1 = DateTime.parse(currentYM+daySpan.getMonthDateAera_StartDate(),DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
			String endDateTime1Str = startDateTime1.plusMonths(1).toString(DateTimeAddition.FORMAT_YM_0)+daySpan.getMonthDateAera_NextEndDate();
			DateTime endDateTime1 = DateTime.parse(endDateTime1Str,DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
			
			dateTimeSpan1[0] = startDateTime1;
			dateTimeSpan1[1] = endDateTime1 ;
			
			
			DateTime[] dateTimeSpan2 = new DateTime[2];
			DateTime startDateTime2 = startDateTime1.plusMonths(-1);
			DateTime endDateTime2 = endDateTime1.plusMonths(-1);
			
			dateTimeSpan2[0] = startDateTime2;
			dateTimeSpan2[1] = endDateTime2;
			twoMonthAreaDateTimeList.add(dateTimeSpan2); //前一月周期
			twoMonthAreaDateTimeList.add(dateTimeSpan1);//当月周期
		}else{
			DateTime[] dateTimeSpan1 = new DateTime[2];
			
			DateTime startDateTime1 = DateTime.parse(shiftDateTime.plusMonths(-1).toString(DateTimeAddition.FORMAT_YM_0)+daySpan.getMonthDateAera_StartDate(),DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
			DateTime endDateTime1 = DateTime.parse(currentYM+daySpan.getMonthDateAera_NextEndDate(),DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
			
			dateTimeSpan1[0] = startDateTime1;
			dateTimeSpan1[1] = endDateTime1;
			
			DateTime[] dateTimeSpan2 = new DateTime[2];
			DateTime startDateTime2 = startDateTime1.plusMonths(-1);
			DateTime endDateTime2 = endDateTime1.plusMonths(-1);
			
			dateTimeSpan2[0] = startDateTime2;
			dateTimeSpan2[1] = endDateTime2;
			twoMonthAreaDateTimeList.add(dateTimeSpan2); //前一月周期
			twoMonthAreaDateTimeList.add(dateTimeSpan1);//当月周期
		}
		
		DateTime densityAffectDateTime = DateTime.parse(densityAffectDateStr, DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
		
		/*在当前月周期内 则只处理本月平均密度*/
		if(densityAffectDateTime.compareTo(twoMonthAreaDateTimeList.get(1)[0]) >= 0){
			twoMonthAreaDateTimeList.remove(0);
		}else 
			/*如果在上月月周期内 则处理上月兼顾本月平均密度*/
			if(densityAffectDateTime.compareTo(twoMonthAreaDateTimeList.get(0)[0]) >= 0){ 
			}else{
				twoMonthAreaDateTimeList = new ArrayList<DateTime[]>();
		}
		
		return twoMonthAreaDateTimeList;
	}
	
	

	@Override
	@Async
	public void onApplicationEvent(RepositoryChangedEvent event) {
		
		System.out.println(event.getSource());
		
	}
	
	
	
	
}
