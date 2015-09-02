package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.Promotion;
import com.bap.bos.domain.PromotionInfo;
import com.bap.bos.util.Page;

public interface PromotionService {

	/**
	 * 保存促销信息
	 * @param Promotion
	 */
	String savePromotion(Promotion Promotion);

	/**
	 * 保存促销品信息
	 * @param PromotionInfo
	 */
	String savePromotionInfo(PromotionInfo PromotionInfo);

	/**
	 * 删除促销信息
	 * @param Promotion
	 */
	void deletePromotion(Promotion Promotion);

	/**
	 * 更新促销品信息
	 * @param PromotionInfo
	 */
	String updatePromotionInfo(PromotionInfo PromotionInfo);

	/**
	 * 查询促销信息
	 * @param Promotion_ProductNum
	 * @param page
	 * @return
	 */
	List<Object> selPromotion(String Promotion_ProductNum, Page page);

	List<Object> selPromotion(String Promotion_ProductNum);

	/**
	 * 查询促销品信息
	 * @return
	 */
	List<PromotionInfo> selPromotionInfo();

	List<PromotionInfo> getPromotionInfos(String name,Page page);

	List<PromotionInfo> getPromotionInfos(Page page);
	
	/**
	 * 获取总行数
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月13日
	 */
	Long getTotalPromotionInfoRowNum(String name);
	
	
	/**
	 * 删除促销品信息
	 * @param Promotion
	 */
	void deletePromotionInfo(Integer id);
	
}