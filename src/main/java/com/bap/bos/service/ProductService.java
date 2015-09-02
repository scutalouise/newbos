package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.Product;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年3月16日
 */
public interface ProductService {

	/**
	 * 查询产品详细信息
	 * @return
	 */
	
	public List<Product> selProductDetail();
	/**
	 * 查询产品详细信息
	 * @param Product_Type 产品类型
	 * @return
	 */
	
	public List<Product> selProductDetail(String Product_Type);
	
	/**
	 * 查询产品详细信息-名称
	 * @param Product_Num 产品编号
	 * @return
	 */
	
	public String selProductName(String Product_Num);
	/**
	 * 查询产品详细信息-名称
	 * @param ProductName 产品名称
	 * @return
	 */
	
	public String selProductNum(String ProductName);
}
