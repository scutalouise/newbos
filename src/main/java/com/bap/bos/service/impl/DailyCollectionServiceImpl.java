package com.bap.bos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bap.bos.dao.NozzleShiftDao;
import com.bap.bos.dao.TranItemDao;
import com.bap.bos.service.DailyCollectionService;
import com.bap.bos.web.pojo.DailyCollection;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月8日
 */
@Service
public class DailyCollectionServiceImpl implements DailyCollectionService{

	@Resource
	TranItemDao tranItemDao;
	
	@Resource
	NozzleShiftDao nozzleShiftDao;
	
	
	/**
	 * 根据年月日 班号 获取日报数据
	 */
	@Override
	@Transactional(readOnly=true)
	public List<DailyCollection> getDataForShow(String ymd, String shiftNo) {
		
		List<Map<String,Object>> cashGroupList = tranItemDao.getGroupedTransItemDate(ymd, shiftNo, "01,06");
		List<Map<String,Object>> notCashGroupList = tranItemDao.getGroupedTransItemDate(ymd, shiftNo, "02,03,05,07,10,11,12,13");
		List<Map<String,Object>> realSpendGroupList = tranItemDao.getGroupedTransItemDate(ymd, shiftNo);
		List<Map<String,Object>> groupedNozzleValList = nozzleShiftDao.getGroupedNozzleVal(ymd, shiftNo);
		List<Object[]> nozzlePumpValList = tranItemDao.getNozzlePumpValOnSellPriceChanged(ymd,shiftNo);
		
		
		//当班油品总输出量
		Map<String,BigDecimal> oilTotalSpendNumMap = new HashMap<String, BigDecimal>();
		//当班油品总有效输入量（即 排除回罐等情况）
		Map<String,BigDecimal> gorupProductVolMap = new HashMap<String, BigDecimal>();
		//当班油品关联油枪总的起始泵码 及 之差
		Map<String,Object[]> groupedNozzleMap = new HashMap<String, Object[]>();
		//最终返回
		List<DailyCollection> dailyCollectionList = new ArrayList<DailyCollection>();
		
		for(Map<String,Object> realSpendGroupMap : realSpendGroupList){
			String t_oilcode = realSpendGroupMap.get("t_oilcode").toString();
			BigDecimal t_vol = new BigDecimal(realSpendGroupMap.get("t_vol").toString());
			oilTotalSpendNumMap.put(t_oilcode, t_vol);
		}
		
		for (Map<String, Object> cashGroupMap : cashGroupList) {
			String t_oilcode = cashGroupMap.get("t_oilcode").toString();
			
			BigDecimal totalProductVol = gorupProductVolMap.get(t_oilcode);
			if (totalProductVol == null) {
				totalProductVol = new BigDecimal(0);
			}
			totalProductVol = totalProductVol.add(
					new BigDecimal(cashGroupMap.get("t_vol").toString()));
			gorupProductVolMap.put(t_oilcode, totalProductVol);
		}
		
		Map<String,Map<String,Object>> notCashKeyMap = new HashMap<String, Map<String,Object>>();
		
		for (Map<String, Object> notCashGroupMap : notCashGroupList) {
			String t_oilcode = notCashGroupMap.get("t_oilcode").toString();
			
			notCashKeyMap.put(t_oilcode, notCashGroupMap);
			
			BigDecimal totalProductVol = gorupProductVolMap.get(t_oilcode);
			if (totalProductVol == null) {
				totalProductVol = new BigDecimal(0);
			}
			totalProductVol = totalProductVol.add(
					new BigDecimal(notCashGroupMap.get("t_vol").toString()));
			gorupProductVolMap.put(t_oilcode, totalProductVol);
		}
		
		/*处理由于变价带来的油品 起止泵码显示值   
		 * 一个班 最初和最终泵码值 来自于 班接统计 
		 * 其余变价带来的中间泵码值来源于交易明细统计*/
		for(Map<String,Object> groupedNozzleVal : groupedNozzleValList){
			String t_oilcode = groupedNozzleVal.get("t_oilcode").toString();
			Object[] vals = new Object[2];
			
			BigDecimal t_startVol =new BigDecimal(groupedNozzleVal.get("t_startVol").toString());
			BigDecimal t_endvol =new BigDecimal(groupedNozzleVal.get("t_endvol").toString());
			vals[0] = t_startVol;
			vals[1] = t_endvol;
			groupedNozzleMap.put(t_oilcode, vals);
		}
		
		Map<String,List<Object[]>> nozzlePumpValPerPriceMap = new HashMap<String, List<Object[]>>();
		
		for(Object[] nozzlePumpValCells : nozzlePumpValList){
			String key  = nozzlePumpValCells[0].toString();
			
			List<Object[]> nozzlePumpList = nozzlePumpValPerPriceMap.get(key);
			if(nozzlePumpList == null){
				nozzlePumpList = new ArrayList<Object[]>();
				nozzlePumpValPerPriceMap.put(key, nozzlePumpList);
			}
			nozzlePumpList.add(nozzlePumpValCells);
		}
		
		/*通过 单价 排序每个油品关于单价的list*/
		for(List<Object[]> nozzlePumpValPerPriceList : nozzlePumpValPerPriceMap.values()){
			
			Collections.sort(nozzlePumpValPerPriceList, new Comparator<Object[]>() {
				@Override
				public int compare(Object[] o1, Object[] o2) {
					BigDecimal do1 = (BigDecimal)o1[1];
					BigDecimal do2 = (BigDecimal)o2[1];
					return do1.compareTo(do2);
				}
			});
		}
		
		/*单价下的泵码值  key 格式为 油品编码+单价*/
		Map<String,Object[]> pumpValPerPriceMap = new HashMap<String, Object[]>();
		
		for(Map.Entry<String, List<Object[]>> entry : nozzlePumpValPerPriceMap.entrySet()){
			List<Object[]> cellList = entry.getValue();
			
			if(cellList.size() == 1){
				Object[] cellObjs = cellList.iterator().next(); //单价  
				pumpValPerPriceMap.put(entry.getKey()+new BigDecimal(cellObjs[1].toString()).setScale(2,RoundingMode.HALF_UP).toString(),groupedNozzleMap.get(entry.getKey()));
			}else{
				/*list中 第一个单价和最后一个单价特殊处理 即 第一个的起泵码来源于groupedNozzleMap  
				 * 最后一个单价止泵码来源于groupedNozzleMap*/
				Object[] cellObjs =  cellList.iterator().next();
				Object[] sevals = groupedNozzleMap.get(entry.getKey());
				pumpValPerPriceMap.put(entry.getKey()+new BigDecimal(cellObjs[1].toString()).setScale(2,RoundingMode.HALF_UP).toString(),new Object[]{sevals[0],cellObjs[2]});
				for(int i = 1;i < cellList.size()-1;i++){
					Object[] cellObjs2 = cellList.get(i);
					Object[] preCellObjs2 = cellList.get(i-1); /*上一条的泵码值作为这一条的起始泵码值*/
					pumpValPerPriceMap.put(entry.getKey()+ new BigDecimal(cellObjs2[1].toString()).setScale(2,RoundingMode.HALF_UP).toString(),new Object[]{preCellObjs2[2],cellObjs2[2]});
				}
				Object[] endCellObjs = cellList.get(cellList.size()-1);
				Object[] preEndCellObjs = cellList.get(cellList.size()-2);
				pumpValPerPriceMap.put(entry.getKey()+new BigDecimal(endCellObjs[1].toString()).setScale(2,RoundingMode.HALF_UP).toString(),new Object[]{preEndCellObjs[2],sevals[1]});
			}
		}
		
		
		
		for(Map<String,Object> cashGroupMap : cashGroupList){
			DailyCollection dailyCollection = new DailyCollection();
			String t_oilcode = cashGroupMap.get("t_oilcode").toString();
			
			dailyCollection.setProductName(cashGroupMap.get("t_productName").toString());
			
			BigDecimal price = new BigDecimal(cashGroupMap.get("t_price").toString()).setScale(2,RoundingMode.HALF_UP);
			
			Object[] vals = pumpValPerPriceMap.get(t_oilcode+price.toString());
			dailyCollection.setBeginOilNum(vals[0].toString());
			dailyCollection.setEndOilNum(vals[1].toString());
			
			BigDecimal pumpValDiff = ((BigDecimal)vals[1]).subtract((BigDecimal)vals[0]);
			
			dailyCollection.setShouldSpend(pumpValDiff.setScale(1, RoundingMode.HALF_UP).toString());
			BigDecimal realSpendVol = oilTotalSpendNumMap.get(t_oilcode);
			BigDecimal validSpendVol = gorupProductVolMap.get(t_oilcode);
			
			String invalidSpendVol = realSpendVol.subtract(validSpendVol).setScale(1,RoundingMode.HALF_UP).toString();
			String realSpendStr = realSpendVol.setScale(1,RoundingMode.HALF_UP).toString()+ ("0.0".equals(invalidSpendVol)?"":"(无效输出："+invalidSpendVol+")");
			dailyCollection.setRealSpend(realSpendStr);
			dailyCollection.setDiffVal(pumpValDiff.subtract(realSpendVol).setScale(1,RoundingMode.HALF_UP).toString());
			
			dailyCollection.setTransCount("0");
			dailyCollection.setOfficalSpend("0.0");
			
			Map<String, Object> noCashGroupMap  = notCashKeyMap.get(t_oilcode);
			if(noCashGroupMap != null){
				dailyCollection.setTransCount(noCashGroupMap.get("t_billNum").toString());
				dailyCollection.setOfficalSpend(new BigDecimal(noCashGroupMap.get("t_vol").toString()).setScale(1,RoundingMode.HALF_UP).toString());
			}
				
			dailyCollection.setSellPrice(price.toString());
			dailyCollection.setRetailSpend(new BigDecimal(cashGroupMap.get("t_vol").toString()).setScale(1,RoundingMode.HALF_UP).toString());
			dailyCollection.setRetailAmount(new BigDecimal(cashGroupMap.get("t_amount").toString()).setScale(2,RoundingMode.HALF_UP).toString());
			dailyCollectionList.add(dailyCollection);
		}
		
		return dailyCollectionList;
	}
	
	
}
