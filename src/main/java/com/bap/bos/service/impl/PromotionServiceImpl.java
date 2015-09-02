package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.PromotionDao;
import com.bap.bos.domain.Promotion;
import com.bap.bos.domain.PromotionInfo;
import com.bap.bos.service.PromotionService;
import com.bap.bos.util.Page;
/**
 * 促销信息
 * @author zhulong
 *
 */

@Service
public class PromotionServiceImpl implements PromotionService {
	@Resource(name = "promotionDao")
	private PromotionDao promotionDao;
	
	/**
	 * 保存促销信息
	 * @param Promotion
	 */
	@Transactional
	public String savePromotion(Promotion Promotion){
		//判断促销信息在某个时间段相应的价格区间内，是否唯一
		String startTime=Promotion.getPromotion_TimeStart();
		String endTime=Promotion.getPromotion_TimeEnd();
		double LowMoney=Promotion.getPromotion_MoneyLow();
		double HighMoney=Promotion.getPromotion_MoneyHigh();
		String ProductNum=Promotion.getPromotion_ProductNum();
		String sql="select * from tb_Promotion where " +
				"(('"+startTime+"'>=Promotion_TimeStart and '"+startTime+"'<Promotion_TimeEnd) or " +
				 "('"+endTime+"'>=Promotion_TimeStart and '"+endTime+"'<Promotion_TimeEnd)) " +
				"and(("+LowMoney+">=Promotion_MoneyLow and "+LowMoney+"<=Promotion_MoneyHigh) or " +
					"("+HighMoney+">=Promotion_MoneyLow and "+HighMoney+"<=Promotion_MoneyHigh)) " +
				"and Promotion_ProductNum='"+ProductNum+"'";
		int Count=promotionDao.selPromotionUniqueness(sql).size();
		System.out.print("Count:"+Count);
		if(Count==0){
			//唯一
			promotionDao.savePromotion(Promotion);
			return "success";
		}else{
			//不唯一
			return "false";
		}	
	}

	/**
	 * 保存促销品信息
	 * @param PromotionInfo
	 */
	@Transactional
	public String savePromotionInfo(PromotionInfo promotionInfo){
		if(existsSameNamePromotionInfo(promotionInfo)) return "repeatName";
		promotionDao.savePromotionInfo(promotionInfo);
		return "success";
	}
	/**
	 * 删除促销信息
	 * @param Promotion
	 */
	@Transactional
	public void deletePromotion(Promotion Promotion){
		promotionDao.deletePromotion(Promotion);
	//	promotionDao.deletePromotionInfo(Promotion.getPromotion_InfoNum());
	}
	
	/**
	 * 删除促销品信息
	 * @param Promotion
	 */
	@Transactional
	public void deletePromotionInfo(Integer id){
		promotionDao.deletePromotionInfo(id);
	}
	
	/**
	 * 更新促销品信息
	 * @param PromotionInfo
	 */
	@Transactional
	public String updatePromotionInfo(PromotionInfo promotionInfo){
		if(existsSameNamePromotionInfo(promotionInfo)) return "repeatName";
		promotionDao.updatePromotionInfo(promotionInfo);
		return "success";
	}
	
	/**
	 * 查询促销信息
	 * @param Promotion_ProductNum
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Object> selPromotion(String Promotion_ProductNum, Page page){
		return promotionDao.selPromotion(Promotion_ProductNum, page);
	}


	@Transactional(readOnly=true)
	public List<Object> selPromotion(String Promotion_ProductNum){
		return promotionDao.selPromotion(Promotion_ProductNum);
	}

	/**
	 * 查询促销品信息
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<PromotionInfo> selPromotionInfo(){
		return promotionDao.selPromotionInfo();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PromotionInfo> getPromotionInfos(String name,Page page) {
		return promotionDao.getPromotionInfos(name, page);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PromotionInfo> getPromotionInfos(Page page) {
		return promotionDao.getPromotionInfos(page);
	}

	@Override
	@Transactional(readOnly=true)
	public Long getTotalPromotionInfoRowNum(String name) {
		return promotionDao.countPromotionInfo(name);
	}

	/**
	 * 是否存在同名促销品
	 * @param promotionInfo
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月14日
	 */
	private boolean existsSameNamePromotionInfo(PromotionInfo promotionInfo){
		return promotionDao.existsSameNamePromotionInfo(promotionInfo);
	}
}
