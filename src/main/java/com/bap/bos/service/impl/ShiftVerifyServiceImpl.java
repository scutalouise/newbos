package com.bap.bos.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bap.bos.dao.ProductDao;
import com.bap.bos.dao.ReportManageDao;
import com.bap.bos.dao.ShiftDao;
import com.bap.bos.domain.NozzleShift;
import com.bap.bos.domain.SellPrice;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.TransItem;
import com.bap.bos.service.ShiftVerifyService;
import com.bap.bos.util.Page;
import com.bap.bos.util.ShiftVerify;


@Service
public class ShiftVerifyServiceImpl implements ShiftVerifyService {
	
	Logger logger = LoggerFactory.getLogger(ShiftVerifyServiceImpl.class);
	
	@Resource private ShiftDao shiftDao;
	@Resource private ProductDao productDao;
	@Resource private ReportManageDao reportManageDao;
	
	
	
	/**
	 * 查询班别详情
	 * @param start 开班日期
	 * @param end	开班日期
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Object> selShiftDetail(String start,String end){
		return shiftDao.selShiftDetail(start, end);
	}@Transactional(readOnly=true)
	
	public List<Object> selShiftDetail(String start,String end,Page page){
		return shiftDao.selShiftDetail(start, end,page);
	}
	/**
	 * 查询班别油枪详情
	 * @param TransItem_ShiftDate	班别日期
	 * @param TransItem_ShiftNo	班别编号
	 * @param TransItem_NozzleNo	油枪编号
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ShiftVerify> selShiftNozzleDetails(String ShiftDate,String ShiftNo,
			String NozzleShift_CreateTime,String Shift_StartTime,String Shift_EndTime,String stationNo){
		List<ShiftVerify> ShiftVerifyList=new ArrayList<ShiftVerify>();
		//获取当班油枪数据
		List<NozzleShift> NozzleShift=shiftDao.selShiftNozzle(ShiftDate, ShiftNo,NozzleShift_CreateTime);
		
		//计算每把油枪差值差值
		for(int i=0;i<NozzleShift.size();i++){
			double totalPT=0;
			double NozzlePT=NozzleShift.get(i).getNozzleShift_EndVol()-NozzleShift.get(i).getNozzleShift_StartVol();
			List<TransItem> TransItems=shiftDao.selShiftTransItem(ShiftDate, ShiftNo, 
					NozzleShift.get(i).getNozzleShift_NozzleId(),Shift_StartTime,Shift_EndTime);
			for(int j=0;j<TransItems.size();j++){
				totalPT+=TransItems.get(j).getTransItem_PayVol();
			}
			double SubtractionValue=NozzlePT-totalPT;
			//写入显示列表中
			DecimalFormat df=new DecimalFormat("###############.###");
			ShiftVerify ShiftVerify=new ShiftVerify();
			ShiftVerify.setProductName(productDao.selProductName(NozzleShift.get(i).getNozzleShift_ProductNum()));
			ShiftVerify.setProductNum(NozzleShift.get(i).getNozzleShift_ProductNum());
			ShiftVerify.setNozzleId(NozzleShift.get(i).getNozzleShift_NozzleId());
			ShiftVerify.setStartVol(Double.valueOf(df.format(NozzleShift.get(i).getNozzleShift_StartVol())));
			ShiftVerify.setEndVol(Double.valueOf(df.format(NozzleShift.get(i).getNozzleShift_EndVol())));
			ShiftVerify.setSubtractionValue(Double.valueOf(df.format(SubtractionValue)));
			ShiftVerifyList.add(ShiftVerify);
		}
		return ShiftVerifyList;
	}
	/**
	 * 查询产品当前价格
	 * @param Phy_Noz	物理枪号
	 * @return
	 */
	@Transactional(readOnly=true)
	public float SellPrice(String Phy_Noz){
		List<SellPrice> SellPrice=shiftDao.selSellPrice(Phy_Noz);
		return SellPrice.get(0).getSellPrice_SellPrice();
	}
	/**
	 * 班结审核通过更新
	 * @param StationNo	站点编号
	 * @param ShiftDate	班别日期
	 * @param ShiftNo	辨别编号
	 * @param staffNo	审核员工编号
	 */
	@Transactional
	public void shiftVerify(String StationNo,String ShiftDate,String ShiftNo,String staffNo,String Shift_CreateTime){
		shiftDao.shiftVerify(StationNo, ShiftDate, ShiftNo, staffNo,Shift_CreateTime);
	}
	
	public ShiftDao getShiftDao() {
		return shiftDao;
	}
	public void setShiftDao(ShiftDao shiftDao) {
		this.shiftDao = shiftDao;
	}
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public Date[] getShiftDateSpan(String Shift_StationNo,String dateStr) {
		List<Shift> shiftList = reportManageDao.selCurrentShift(Shift_StationNo, dateStr);
		Date[] dates = new Date[2];
		if(!CollectionUtils.isEmpty(shiftList)){

			Date minDate = null;
			Date maxDate = null;
			
			Shift firstShift = shiftList.get(0);
			minDate = firstShift.getShift_StartTime();
			maxDate = firstShift.getShift_EndTime();
			
			if(maxDate == null) return dates; // 只要有一条数据班结束日期为空 则表示取班起止时间无效，返回    // maxDate = minDate;
			
			for(int i = 1;i < shiftList.size();i ++){
				Shift shift = shiftList.get(i);
				if(minDate.compareTo(shift.getShift_StartTime()) > 0){
					minDate = shift.getShift_StartTime();
				}
				if(shift.getShift_EndTime() == null) // 只要有一条数据班结束日期为空 则表示取班起止时间无效
				if(maxDate.compareTo(shift.getShift_EndTime()) < 0){
					maxDate = shift.getShift_EndTime();
				}
			}
			dates[0] = minDate;
			dates[1] = maxDate;
			logger.debug("=======getShiftDateSpan查询开始时间："+ new DateTime(dates[0]).toString("yyyy/MM/dd HH:mm:ss"));
			logger.debug("=======getShiftDateSpan查询结束时间："+ new DateTime(dates[1]).toString("yyyy/MM/dd HH:mm:ss"));
			return dates;
		}
		return dates;
	}
	
	@Transactional
	public String[] getShiftDateSpanAsString(String Shift_StationNo,String dateStr){
		Date[] dates = getShiftDateSpan(Shift_StationNo,dateStr);
		String[] dateStrArr = new String[2];
		if(ArrayUtils.isEmpty(dates) || (dates[0] == null && dates[1] == null))return dateStrArr;
		dateStrArr[0] = new DateTime(dates[0]).toString("yyyy/MM/dd HH:mm:ss");
		dateStrArr[1] = new DateTime(dates[1]).toString("yyyy/MM/dd HH:mm:ss");
		return dateStrArr;
	}
	
	@Override
	@Transactional
	public Date[] getShiftDateSpan(String Shift_StationNo, String dateStr,
			String endDateStr) {
		Date[] dates = new Date[]{null,null};
		
		//查询接近或等于开始日期的班接日期 同时保证该日期不逾越查询条件的endDate
		String earliestDate = shiftDao.getTheFurthestAffectiveVerifyDate(Shift_StationNo, dateStr);
		if (earliestDate == null || Integer.parseInt(earliestDate) > Integer.parseInt(endDateStr))
			return dates;
		List<Shift> shiftList = reportManageDao.selCurrentShift(Shift_StationNo,earliestDate);
		if (CollectionUtils.isEmpty(shiftList))
			return dates;
		
		Date minDate = null;

		Shift firstShift = shiftList.get(0);
		minDate = firstShift.getShift_StartTime();

		for (Shift shift : shiftList) {
			if(shift.getShift_EndTime() == null) return dates; //没有结班时间表示 当日班 未完
			if (minDate.compareTo(shift.getShift_StartTime()) > 0) {
				minDate = shift.getShift_StartTime();
			}
		}
		dates[0] = minDate;
		
		endDateStr = shiftDao.getTheLatestAffectiveVerifyDate(Shift_StationNo,endDateStr);
		
		List<Shift> endShiftList = reportManageDao.selCurrentShift(
				Shift_StationNo, endDateStr);
		
		if (CollectionUtils.isEmpty(endShiftList)) {
			return dates;
		}

		Date maxDate = null;

		firstShift = endShiftList.get(0);
		maxDate = firstShift.getShift_EndTime();
		if (maxDate == null)
			maxDate = firstShift.getShift_StartTime();
		for (Shift shift : endShiftList) {
			if (maxDate.compareTo(shift.getShift_EndTime()) < 0) {
				maxDate = shift.getShift_EndTime();
			}
		}
		dates[1] = maxDate;

		logger.debug("=======交易明细查询开始时间："
				+ new DateTime(dates[0]).toString("yyyy/MM/dd HH:mm:ss"));
		logger.debug("=======交易明细查询结束时间："
				+ new DateTime(dates[1]).toString("yyyy/MM/dd HH:mm:ss"));
		return dates;
	}
	
	@Transactional
	public String[] getShiftDateSpanAsString(String Shift_StationNo,String dateStr,String endDateStr){
		Date[] dates = getShiftDateSpan(Shift_StationNo,dateStr,endDateStr);
		String[] dateStrArr = new String[2];
		if(ArrayUtils.isEmpty(dates))return dateStrArr;
		dateStrArr[0] = new DateTime(dates[0]).toString("yyyy/MM/dd HH:mm:ss");
		dateStrArr[1] = new DateTime(dates[1]).toString("yyyy/MM/dd HH:mm:ss");
		return dateStrArr;
	}
	
}
