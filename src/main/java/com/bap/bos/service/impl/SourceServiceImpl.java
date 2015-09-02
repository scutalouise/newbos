package com.bap.bos.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.SourceDao;
import com.bap.bos.domain.DailySourceed;
import com.bap.bos.domain.SourceUsed;
import com.bap.bos.service.SourceService;
import com.bap.bos.util.Page;
/**
 * 资源管理Service
 * @author zhulong
 *
 */

@Service
public class SourceServiceImpl implements SourceService   {
	
	Logger logger = LoggerFactory.getLogger(SourceServiceImpl.class);
	
	@Resource private SourceDao sourceDao;
	
	/**
	 *每日能耗保存
	 */
	@Transactional
	public String saveDailySourceed(DailySourceed ds) throws Exception{
		/*本月已经使用的水电*/
		float MonthUsedElecNum=0;
		float MonthUsedWaterNum=0;
		/*本月已经度过的日期*/
		String MonthStartDay="";
		String MonthEndDay="";
		
		/*得到当前年月*/
		DateFormat format = new SimpleDateFormat("yyyyMM");
		String currYearMonth=format.format(ds.getDailySourced_CurrDate());
		/*得到当月计划能耗*/
		List<SourceUsed> currMonthPlan=sourceDao.selSourcePlan(currYearMonth);
		/*得到录入日期和当前月的第一天*/
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String currDate=format1.format(ds.getDailySourced_CurrDate());
		String curr[]=currDate.split("-");
		MonthStartDay=curr[0]+"-"+curr[1]+"-"+"01";
		if("01"==curr[2]){
			//每月第一天录入时
			 MonthEndDay=curr[0]+"-"+curr[1]+"-"+"01";
		}else{
			 MonthEndDay=curr[0]+"-"+curr[1]+"-"+(Integer.valueOf(curr[2])-1);
		}
		/*查询当前月份,计算月初到昨天为止的水电能耗*/
		List<DailySourceed> total=sourceDao.selSourceed(MonthStartDay, MonthEndDay);
		for(int i=0;i<total.size();i++){
			MonthUsedElecNum+=total.get(i).getDailySourced_ElecNum();
			MonthUsedWaterNum+=total.get(i).getDailySourced_WaterNum();
		}
		/*设定是否超标能耗值*/	
		if(currMonthPlan.size()>0){
			if(MonthUsedElecNum>=currMonthPlan.get(0).getSourceUsed_MonthlyElec()){
				//超标
				ds.setDailySourced_ElecNumOver("1");
			}else{
				ds.setDailySourced_ElecNumOver("0");
			}
			if(MonthUsedWaterNum>=currMonthPlan.get(0).getSourceUsed_MonthlyWater()){
				ds.setDailySourced_WaterNumOver("1");
			}else{
				ds.setDailySourced_WaterNumOver("0");
			}
			/*添加*/
			sourceDao.saveDailySourceed(ds);
			return "success";
		}else{
			return "UnPlan";
		}	
	}
	
	/**
	 * 能耗查询
	 */
	@Transactional(readOnly=true)
	public List<DailySourceed> selSourceed(String start,String end,Page page){
		List<DailySourceed> total=sourceDao.selSourceed(start, end,page);
		return total;
	}
	/**
	 * 能耗更新
	 */
	@Transactional
	public String updateDailySourceed(DailySourceed ds,String Date){
		List<DailySourceed> updateDailySou=sourceDao.selSourceed(Date,Date);
		if("2".equals(updateDailySou.get(0).getDailySourced_IsSync())){
			String sql="update tb_DailySourceed set DailySourced_ElecNum='"+ds.getDailySourced_ElecNum()+"', " +
			"DailySourced_WaterNum='"+ds.getDailySourced_WaterNum()+"' " +
			"where DailySourced_StationNo='"+ds.getDailySourced_StationNo().trim()+"' and DailySourced_CurrDate='"+Date+"'";
			sourceDao.updateDailySourceed(sql);
			return "success";
		}else{
			return "UnUpdate";
		}
		
	}
	/**
	 * 能耗删除
	 */
	@Transactional
	public String delDailySourceed(DailySourceed ds,String Date){
		List<DailySourceed> updateDailySou=sourceDao.selSourceed(Date,Date);
		if("2".equals(updateDailySou.get(0).getDailySourced_IsSync())){
			sourceDao.delDailySourceed(ds);
			return "success";
		}else{
			return "UnDel";
		}
		
	}
	/**
	 * 每日能耗签核
	 * @param StationNo	站级编号
	 * @param Date	能耗日期
	 * @return
	 */
	@Transactional
	public boolean signDailySourceed(String StationNo,String Date){
		try {
			String sql="update tb_DailySourceed set DailySourced_IsSync='1' where DailySourced_StationNo='"+StationNo+"' and " +
						"DailySourced_CurrDate=to_date('"+Date+"','yyyy-mm-dd') ";
			sourceDao.updateDailySourceed(sql);
			return true;
		} catch (Exception e) {
			logger.warn("每日能耗签核异常！",e);
			return false;
		}
	}

	@Override
	@Transactional
	public void delDailySourceed(DailySourceed ds) {
		sourceDao.delDailySourceed(ds);
	}

	@Override
	@Transactional
	public void updateDailySourceed(String sql) {
		sourceDao.updateDailySourceed(sql);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DailySourceed> selSourceed(String start, String end) {
		return sourceDao.selSourceed(start, end);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SourceUsed> selSourcePlan(String date) {
		return sourceDao.selSourcePlan(date);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SourceUsed> selSourcePlan(String start, String end) {
		return sourceDao.selSourcePlan(start, end);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SourceUsed> selSourcePlan(String start, String end, Page page) {
		return sourceDao.selSourcePlan(start, end, page);
	}
	
}
