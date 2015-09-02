package com.bap.bos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.DayAndMouthStatisticsDao;
import com.bap.bos.dao.DensityDao;
import com.bap.bos.dao.ProductDao;
import com.bap.bos.dao.RepertoryManageDao;
import com.bap.bos.dao.ShiftDao;
import com.bap.bos.dao.StationDao;
import com.bap.bos.dao.TransMonthDao;
import com.bap.bos.domain.MonthDateAera;
import com.bap.bos.domain.Product;
import com.bap.bos.domain.Shift;
import com.bap.bos.service.DensityService;
import com.bap.bos.service.MonthCheckingService;
import com.bap.bos.web.pojo.MonthChecking;
import com.bap.bos.web.pojo.MonthCheckingDetail;
import com.bap.utils.DateTimeAddition;

/**
 * 
 * 月盘点
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年5月7日
 */
@Service
public class MonthCheckingServiceImpl implements MonthCheckingService{

	@Resource
	private DensityService densityService;
	@Resource
	private DayAndMouthStatisticsDao dayAndMouthStatisticsDao;
	@Resource
	private DensityDao densityDao;
	@Resource
	private TransMonthDao transMonthDao;
	@Resource
	private ShiftDao shiftDao;
	@Resource
	private RepertoryManageDao repertoryManageDao;
	@Resource
	private StationDao stationDao;
	@Resource
	private ProductDao productDao;
	
	
	
	@Transactional(readOnly=true)
	public List<MonthChecking> integrateMonthCheckingData(String checkingMonth){
		DateTime[] monthSpan = getTheMonthDaySpan(checkingMonth);
		return integrateMonthCheckingData(monthSpan);
	}
	
	@Transactional(readOnly=true)
	public List<MonthChecking> integrateMonthCheckingData(DateTime[] monthSpan){
		
		Map<String,Double[]> densityMap = densityService.getDensitiesMap(monthSpan);
		
		Map<String,Object[]> productDataMap = getProductAmountMap(monthSpan);
		
		List<MonthChecking> monthCheckingList = new ArrayList<MonthChecking>();
		
		for(Map.Entry<String, Object[]> entry : productDataMap.entrySet()){
			Double[] densitys = densityMap.get(entry.getKey());
			if(densitys == null) densitys = new Double[]{0d,0d};
			
			Object[] values = entry.getValue();
			MonthChecking monthChecking = new MonthChecking();
			
			monthChecking.setProductName(values[0].toString());
			monthChecking.setPrevRepertoryVol(new BigDecimal((Double)values[1]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			if(densitys[1] != 0d)
			monthChecking.setPrevRepertoryWeight(new BigDecimal((Double)values[1]).divide(new BigDecimal((Double)densitys[1]),2, RoundingMode.HALF_UP).doubleValue());
			monthChecking.setCurrentIncomeVol(new BigDecimal((Double)values[3]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			if(densitys[0] != 0d)
			monthChecking.setCurrentIncomeWeight(new BigDecimal((Double)values[3]).divide(new BigDecimal((Double)densitys[0]),2, RoundingMode.HALF_UP).doubleValue());
			monthChecking.setCurrentSellVol(new BigDecimal((Double)values[5]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			if(densitys[0] != 0d)
			monthChecking.setCurrentSellWeight(new BigDecimal((Double)values[5]).divide(new BigDecimal((Double)densitys[0]),2, RoundingMode.HALF_UP).doubleValue());
			monthChecking.setCurrentBalanceVol(new BigDecimal((Double)values[7]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			if(densitys[0] != 0d)
			monthChecking.setCurrentBalanceWeight(new BigDecimal((Double)values[7]).divide(new BigDecimal((Double)densitys[0]),2, RoundingMode.HALF_UP).doubleValue());
			monthChecking.setInnerTransferVol(new BigDecimal((Double)values[9]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			if(densitys[0] != 0d)
			monthChecking.setInnerTransferWeight(new BigDecimal((Double)values[9]).divide(new BigDecimal((Double)densitys[0]),2, RoundingMode.HALF_UP).doubleValue());
			monthChecking.setTankOilHeight(new BigDecimal((Double)values[11]).setScale(2, RoundingMode.HALF_UP).doubleValue());
		
			monthCheckingList.add(monthChecking);
		}
		
		return monthCheckingList;
	}
	
	
	/**
	 * 月盘点 分油品 销售细化
	 * @param checkingMonth
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月8日
	 */
	@Transactional(readOnly=true)
	public List<MonthCheckingDetail> integrateMonthCheckingDetailData(String checkingMonth){
		DateTime[] monthSpan = getTheMonthDaySpan(checkingMonth);
		
		return integrateMonthCheckingDetailData(monthSpan);
	}
	
	@Transactional(readOnly=true)
	public List<MonthCheckingDetail> integrateMonthCheckingDetailData(DateTime[] monthSpan){
	Map<String,Double[]> densityMap = densityService.getDensitiesMap(monthSpan);
		
		Map<String,Object[]> productDataMap = getVariousStatisticVolume(monthSpan);
		
		List<MonthCheckingDetail> monthCheckingDetailList = new ArrayList<MonthCheckingDetail>();
		
		for(Map.Entry<String, Object[]> entry : productDataMap.entrySet()){
			Double[] densitys = densityMap.get(entry.getKey());
			if(densitys == null) densitys = new Double[]{0d,0d};
			
			Object[] values = entry.getValue();
			MonthCheckingDetail monthCheckingDetail = new MonthCheckingDetail();
			
			monthCheckingDetail.setProductName(values[0].toString());
			monthCheckingDetail.setPrevRepertoryVol(new BigDecimal((Double)values[1]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setPrevRepertoryWeight(new BigDecimal((Double)densitys[1]).multiply(new BigDecimal((Double)values[1])).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setCurrentIncomeVol(new BigDecimal((Double)values[3]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setCurrentIncomeWeight(new BigDecimal((Double)densitys[0]).multiply(new BigDecimal((Double)values[3])).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setSelfUserForCompanyVol(new BigDecimal((Double)values[5]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setInnerTransVol(new BigDecimal((Double)values[6]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setCreditConsumeVol(new BigDecimal((Double)values[7]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setPreStoreVol(new BigDecimal((Double)values[8]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setPreferentialCardVol(new BigDecimal((Double)values[9]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setCashVol(new BigDecimal((Double)values[10]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setOtherVol(new BigDecimal((Double)values[11]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setSurplusVol(new BigDecimal((Double)values[12]).setScale(2, RoundingMode.HALF_UP).doubleValue());
			monthCheckingDetail.setSurplusWeight(new BigDecimal((Double)densitys[0]).multiply(new BigDecimal((Double)values[12])).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			monthCheckingDetailList.add(monthCheckingDetail);
		}
		return  monthCheckingDetailList;
	}
	
	
	private Map<String,Object[]> getVariousStatisticVolume(DateTime[] monthSpan){
		
		/**获取站点编号**/
		String stationNo = stationDao.selStationDetail().get(0).getStation_No();
		/*当前年月*/
		String cym =  getMonthBySpan(monthSpan);
		/*上期库存 本期进油*/
		Map<String,Double[]> productAmountMap = densityService.getProductAmountMap(monthSpan);
		/*本期最后一次班接信息*/
		Shift monthLastShift = shiftDao.getTheLatestAffectiveVerifyShift(stationNo, monthSpan[1].toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0)));
		
		/*本期剩余量及油高*/
		List<Object[]> currentBalanceList = new ArrayList<Object[]>();
		if(monthLastShift != null){
			 currentBalanceList = repertoryManageDao.getByShiftNoAndProduct(monthLastShift);
		}
		
		Map<String,Double[]> currentBalanceMap = new HashMap<String, Double[]>();
		for(Object[] os : currentBalanceList){
			currentBalanceMap.put(os[0].toString(), new Double[]{Double.valueOf(os[1].toString()),Double.valueOf(os[2].toString())});
		}
		
		/*公司自用*/
		List<Object[]> selfUserForCompanyVols = transMonthDao.getProductTransInfo(cym,"07");
		
		Map<String,Double> selfUserForCompanyMap = new HashMap<String, Double>();
		for(Object[] os : selfUserForCompanyVols){
			selfUserForCompanyMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*加油站回罐*/
		List<Object[]> innerTransVols = transMonthDao.getProductTransInfo(cym,"08","09");
		
		Map<String,Double> innerTransMap = new HashMap<String, Double>();
		for(Object[] os : innerTransVols){
			innerTransMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*赊消*/
		List<Object[]> creditConsumeVols = transMonthDao.getProductTransInfo(cym,"05","10");
		
		Map<String,Double> creditConsumeMap = new HashMap<String, Double>();
		for(Object[] os : creditConsumeVols){
			creditConsumeMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*预存*/
		List<Object[]> preStoreVols = transMonthDao.getProductTransInfo(cym,"02","11");
		
		Map<String,Double> preStoreMap = new HashMap<String, Double>();
		for(Object[] os : preStoreVols){
			preStoreMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*优惠卡*/
		List<Object[]> preferentialCardVols = transMonthDao.getProductTransInfo(cym,"06");
		
		Map<String,Double> preferentialCardMap = new HashMap<String, Double>();
		for(Object[] os : preferentialCardVols){
			preferentialCardMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*现金*/
		List<Object[]> cashVols = transMonthDao.getProductTransInfo(cym,"01");
		
		Map<String,Double> cashMap = new HashMap<String, Double>();
		for(Object[] os : cashVols){
			cashMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*其他*/
		List<Object[]> otherVols = transMonthDao.getProductTransInfo(cym,"03","04","12","13");
		
		Map<String,Double> otherMap = new HashMap<String, Double>();
		for(Object[] os : otherVols){
			otherMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*安产品整合所有数据*/
		Map<String,Object[]> productDataMap = new HashMap<String, Object[]>();
		
		List<Product> productList =	productDao.selProductDetail();
		
		for(Product product : productList){
			String key = product.getProduct_Num();
			Object[] dataArr = new Object[14];
			dataArr[0] = product.getProduct_Name();
			
			Double[] rAndRs = productAmountMap.get(key);
			if(rAndRs == null) rAndRs = new Double[]{0d,0d};
			
			dataArr[1] = rAndRs[0];
			//dataArr[2]上期库存重量
			dataArr[3] = rAndRs[1];
			//dataArr[4]本期进油重量
			Double transOilVol = selfUserForCompanyMap.get(key);
			dataArr[5] = transOilVol == null?0d:transOilVol;
			//加油站回罐
			Double innerTransVol = innerTransMap.get(key);
			dataArr[6] = innerTransVol == null?0d:innerTransVol;
			//赊消
			Double creditConsumeVol = creditConsumeMap.get(key);
			dataArr[7] = creditConsumeVol == null?0d:creditConsumeVol;
			//预存
			Double preStoreVol = preStoreMap.get(key);
			dataArr[8] = preStoreVol == null?0d:preStoreVol;
			//优惠卡
			Double preferentialCardVol = preferentialCardMap.get(key);
			dataArr[9] = preferentialCardVol == null?0d:preferentialCardVol;
			//现金
			Double cashVol = cashMap.get(key);
			dataArr[10] = cashVol == null?0d:cashVol;
			//现金
			Double otherVol = otherMap.get(key);
			dataArr[11] = otherVol == null?0d:otherVol;
			
			Double[] currentBalance = currentBalanceMap.get(key);
			if(currentBalance == null) currentBalance = new Double[]{0d,0d};
			dataArr[12] = currentBalance[0];
			
			productDataMap.put(key, dataArr);
		}
		
		return productDataMap;
	}
	
	
	/**
	 * 获取输入月 的月区间
	 * @param ym
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月7日
	 */
	private DateTime[] getTheMonthDaySpan(String ym){
		MonthDateAera daySpan = dayAndMouthStatisticsDao.getMonthStaticDaySpan();
		
		Integer startDateInt = Integer.valueOf(daySpan.getMonthDateAera_StartDate());
		
		DateTime startDay = DateTime.parse(ym+daySpan.getMonthDateAera_StartDate(),DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
		DateTime endDay = DateTime.parse(ym+daySpan.getMonthDateAera_NextEndDate(),DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0));
		
		if(15 > startDateInt){//从输入月份往下月
			endDay = endDay.plusMonths(1);
		}else{ //从上月到输入月份
			startDay = startDay.plusMonths(-1);
		}
		return new DateTime[]{startDay,endDay};
	}
	
	
	private Map<String,Object[]> getProductAmountMap(DateTime[] monthSpan){
		/**获取站点编号**/
		String stationNo = stationDao.selStationDetail().get(0).getStation_No();
		
		/*当前年月*/
		String cym = getMonthBySpan(monthSpan);
		/*上期库存 本期进油*/
		Map<String,Double[]> productAmountMap = densityService.getProductAmountMap(monthSpan);
		/*本期销售量*/
		List<Object[]> transOilVolList = transMonthDao.getProductTransAmounts(cym);
		
		Map<String,Double> transOilVolMap = new HashMap<String, Double>();
		for(Object[] os : transOilVolList){
			transOilVolMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		/*本期最后一次班接信息*/
		Shift monthLastShift = shiftDao.getTheLatestAffectiveVerifyShift(stationNo, monthSpan[1].toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0)));
		
		/*本期剩余量及油高*/
		List<Object[]> currentBalanceList = new ArrayList<Object[]>();
		if(monthLastShift != null){
			 currentBalanceList = repertoryManageDao.getByShiftNoAndProduct(monthLastShift);
		}
		
		Map<String,Double[]> currentBalanceMap = new HashMap<String, Double[]>();
		for(Object[] os : currentBalanceList){
			currentBalanceMap.put(os[0].toString(), new Double[]{Double.valueOf(os[1].toString()),Double.valueOf(os[2].toString())});
		}
		
		/*回罐*/
		List<Object[]> innerTransAmounts = transMonthDao.getProductTransInfo(cym,"08","09");
		
		Map<String,Double> innerTransAmountMap = new HashMap<String, Double>();
		for(Object[] os : innerTransAmounts){
			innerTransAmountMap.put(os[0].toString(), Double.valueOf(os[1].toString()));
		}
		
		
		/*安产品整合所有数据*/
		Map<String,Object[]> productDataMap = new HashMap<String, Object[]>();
		
		List<Product> productList =	productDao.selProductDetail();
		
		for(Product product : productList){
			String key = product.getProduct_Num();
			Object[] dataArr = new Object[12];
			dataArr[0] = product.getProduct_Name();
			
			Double[] rAndRs = productAmountMap.get(key);
			if(rAndRs == null) rAndRs = new Double[]{0d,0d};
			
			dataArr[1] = rAndRs[0];
			//dataArr[2]上期库存重量
			dataArr[3] = rAndRs[1];
			//dataArr[4]本期进油重量
			Double transOilVol = transOilVolMap.get(key);
			dataArr[5] = transOilVol == null?0d:transOilVol;
			//dataArr[6]本期销售重量
			Double[] currentBalance = currentBalanceMap.get(key);
			if(currentBalance == null) currentBalance = new Double[]{0d,0d};
			
			dataArr[7] = currentBalance[0];
			//dataArr[8] 本期剩余重量;
			Double innerTransAmount = innerTransAmountMap.get(key);
			dataArr[9] = innerTransAmount == null ? 0d : innerTransAmount;
			//dataArr[10] 回罐重量;
			dataArr[11] = currentBalance[1];
			
			productDataMap.put(key, dataArr);
		}
		
		return productDataMap;
	}
	
	@Transactional(readOnly=true)
	public MonthDateAera getMonthAreaSpan(){
		return dayAndMouthStatisticsDao.getMonthStaticDaySpan();
	}
	
	/**
	 * 根据月区间获取月份
	 * @param monthSpan
	 * @return
	 * @author edgar_chan
	 * @since 2015年6月27日
	 */
	public String getMonthBySpan(DateTime[] monthSpan){
		String cym = "";
		if(15 > monthSpan[0].getDayOfMonth()){
			cym =  monthSpan[0].toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_YM_0));
		}else{
			cym =  monthSpan[1].toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_YM_0));
		}
		return cym;
	}
	
}
