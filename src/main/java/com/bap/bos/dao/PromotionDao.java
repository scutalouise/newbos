package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.Promotion;
import com.bap.bos.domain.PromotionInfo;
import com.bap.bos.util.Page;

public interface PromotionDao {

	/**
	 * 保存促销信息
	 * @param Promotion
	 */
	public void savePromotion(Promotion Promotion);

	/**
	 * 保存促销品信息
	 * @param PromotionInfo
	 */
	public void savePromotionInfo(PromotionInfo PromotionInfo);

	/**
	 * 删除促销信息
	 * @param Promotion
	 */
	public void deletePromotion(Promotion Promotion);

	/**
	 * 删除促销品信息
	 * @param PromotionInfo_Num
	 */
	public void deletePromotionInfo(int PromotionInfo_Num);

	/**
	 * 更新促销品信息
	 * @param PromotionInfo
	 */
	public void updatePromotionInfo(PromotionInfo promotionInfo);

	/**
	 * 查询促销信息
	 * @param Promotion_ProductNum
	 * @param page
	 * @return
	 */
	
	public List<Object> selPromotion(String Promotion_ProductNum, Page page);


	
	public List<Object> selPromotion(String Promotion_ProductNum);
	/**
	 * 查询唯一性
	 * @param sql
	 * @return
	 */
	
	public List<Object> selPromotionUniqueness(String sql);

	/**
	 * 查询促销品信息
	 * @return
	 */
	
	public List<PromotionInfo> selPromotionInfo();
	
	/**
	 * 获取促销信息
	 * @param page
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月13日
	 */
	public List<PromotionInfo> getPromotionInfos(Page page);
	
	/**
	 * 根据条件获取促销信息
	 * @param page
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月13日
	 */
	public List<PromotionInfo> getPromotionInfos(String name, Page page);
	
	/**
	 * 总数
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月13日
	 */
	public Long countPromotionInfo(String name);
	
	/**
	 * 存在同名促销品信息
	 * @param PromotionInfo
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月14日
	 */
	boolean existsSameNamePromotionInfo(PromotionInfo PromotionInfo);

}