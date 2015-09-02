package com.bap.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bap.bos.dao.ProductDao;
import com.bap.bos.domain.Product;
import com.bap.bos.util.DaoTemplate;


/**
 * 产品信息操作实现类
 * @author zhulong
 *
 */

@Repository
public class ProductDaoImpl extends DaoTemplate<Product,String> implements ProductDao{
	

	/**
	 * 查询产品详细信息
	 * @return
	 */
	
	public List<Product> selProductDetail(){
		String hql="from Product order by Product_Num";		
		return this.selectObject(hql);
	}
	/**
	 * 查询产品详细信息
	 * @param Product_Type 产品类型 0-油  1-气
	 * @return
	 */
	
	public List<Product> selProductDetail(String Product_Type){
		String hql="from Product where Product_Type='"+Product_Type+"'";		
		return this.selectObject(hql);
	}
	
	/**
	 * 查询产品详细信息-名称
	 * @param Product_Num 产品编号
	 * @return
	 */
	
	public String selProductName(String Product_Num){
		String hql="from Product where Product_Num='"+Product_Num+"'";		
		return this.selectObject(hql).get(0).getProduct_Name();
	}
	
	/**
	 * 查询产品详细信息-名称
	 * @param ProductName 产品名称
	 * @return
	 */
	
	public String selProductNum(String ProductName){
		String hql="from Product where Product_Name='"+ProductName+"'";		
		List<Product> cc=this.selectObject(hql);
		if(cc.size()<=0){
			return null;
		}else{
			return cc.get(0).getProduct_Num();
		}
	}


}
