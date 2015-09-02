package com.bap.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bap.bos.dao.SettingSellPriceDao;
import com.bap.bos.domain.SettingSellPrice;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;


/**
 * 销售价格变价操作实现类
 * @author zhulong
 *
 */
@Repository
public class SettingSellPriceDaoImpl extends DaoTemplate<Object,String> implements SettingSellPriceDao{
	
	/**
	 * 查询变价结果
	 * @param start 变价时间
	 * @param end	变价时间
	 * @param ProductType	产品类型
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selSellPrice(String start,String end,String ProductType,Page page){
		String hql="from Product pt,PosSetPriceRecord sp where sp.PosSetPriceRecord_ProductNum=pt.Product_Num ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductType.trim()))){
			/*没时间*/
			hql+="and pt.Product_Type='"+ProductType.trim()+"' order by PosSetPriceRecord_ChDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductType.trim())){
			/*没产品类型*/
			hql+="and PosSetPriceRecord_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by PosSetPriceRecord_ChDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductType.trim())){
			/*时间、产品都有*/
			hql+="and pt.Product_Type='"+ProductType.trim()+"' and PosSetPriceRecord_ChDate between '"+start+"' and '"+end+"' order by PosSetPriceRecord_ChDate desc";
		}else{
			hql+="order by PosSetPriceRecord_ChDate desc";
		}
		return this.selectObject(hql, page);
	}
	
	
	public List<Object> selSellPrice(String start,String end,String ProductType){
		String hql="from Product pt,PosSetPriceRecord sp where 1=1 and sp.PosSetPriceRecord_ProductNum=pt.Product_Num ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductType.trim()))){
			/*没时间*/
			hql+="and pt.Product_Type='"+ProductType.trim()+"' order by PosSetPriceRecord_ChDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductType.trim())){
			/*没产品类型*/
			hql+="and PosSetPriceRecord_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by PosSetPriceRecord_ChDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductType.trim())){
			/*时间、产品都有*/
			hql+="and pt.Product_Type='"+ProductType.trim()+"' and PosSetPriceRecord_ChDate between '"+start+"' and '"+end+"' order by PosSetPriceRecord_ChDate desc";
		}else{
			hql+="order by PosSetPriceRecord_ChDate desc";
		}
		return this.selectObject(hql);
	}
	/**
	 * 查询变价结果-精确查询
	 * @param ProductName 产品名称
	 * @return
	 */
	
	public List<Object> selSellPrice(String ProductName,Page page){
		String hql="from Product pt,PosSetPriceRecord sp where sp.PosSetPriceRecord_ProductNum=pt.Product_Num and pt.Product_Name='"+ProductName.trim()+"' order by PosSetPriceRecord_ChDate desc";
		return this.selectObject(hql,page);
	}
	
	public List<Object> selSellPrice(String ProductName){
		String hql="from Product pt,PosSetPriceRecord sp where sp.PosSetPriceRecord_ProductNum=pt.Product_Num and pt.Product_Name='"+ProductName.trim()+"' order by PosSetPriceRecord_ChDate desc";
		return this.selectObject(hql);
	}
	/**
	 * 保存变价计划
	 * @param SettingSellPrice
	 */
	
	public void saveSellPricePlan(SettingSellPrice SettingSellPrice){
		this.getSession().save(SettingSellPrice);
	}
	/**
	 * 删除变价计划
	 * @param SettingSellPrice
	 */
	
	public void delSellPricePlan(SettingSellPrice SettingSellPrice){
		this.getSession().delete(SettingSellPrice);
	}
	/**
	 * 查询变价计划
	 * @param start	开始时间
	 * @param end	结束时间
	 * @param ProductNum	产品编号
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selSellPricePlan(String start,String end,String ProductNum,Page page){
		String hql="from Product pt,SettingSellPrice sp,Staff sf where 1=1 and " +
				"sp.SettingSellPrice_ProductNum=pt.Product_Num and sf.Staff_No=sp.SettingSellPrice_StaffNo ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductNum.trim()))){
			/*没时间*/
			hql+="and sp.SettingSellPrice_ProductNum='"+ProductNum.trim()+"' order by SettingSellPrice_EffDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductNum.trim())){
			/*没产品类型*/
			hql+="and SettingSellPrice_EffDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by SettingSellPrice_EffDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductNum.trim())){
			/*时间、产品都有*/
			hql+="and sp.SettingSellPrice_ProductNum='"+ProductNum.trim()+"' " +
					"and SettingSellPrice_EffDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by SettingSellPrice_EffDate desc";
		}else{
			hql+="order by SettingSellPrice_EffDate desc";
		}
		return this.selectObject(hql, page);
	}
	
	public List<Object> selSellPricePlan(String start,String end,String ProductNum){
		String hql="from Product pt,SettingSellPrice sp,Staff sf where 1=1 and " +
						"sp.SettingSellPrice_ProductNum=pt.Product_Num and sf.Staff_No=sp.SettingSellPrice_StaffNo ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductNum.trim()))){
			/*没时间*/
			hql+="and sp.SettingSellPrice_ProductNum='"+ProductNum.trim()+"' order by SettingSellPrice_EffDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductNum.trim())){
			/*没产品类型*/
			hql+="and SettingSellPrice_EffDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by SettingSellPrice_EffDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductNum.trim())){
			/*时间、产品都有*/
			hql+="and sp.SettingSellPrice_ProductNum='"+ProductNum.trim()+"' " +
					"and SettingSellPrice_EffDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by SettingSellPrice_EffDate desc";
		}else{
			hql+="order by SettingSellPrice_EffDate desc";
		}
		return this.selectObject(hql);
	}
	
	
	/**
	 * 查询变价计划
	 * @param start	开始时间
	 * @param end	结束时间
	 * @param ProductName	产品名称 
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selSellPriceByProductName(String start,String end,String ProductName,Page page){
		
		String hql="from Product pt,PosSetPriceRecord sp where 1=1 and sp.PosSetPriceRecord_ProductNum=pt.Product_Num ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductName.trim()))){
			/*没时间*/
			hql+="and pt.Product_Name='"+ProductName.trim()+"' order by PosSetPriceRecord_ChDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductName.trim())){
			/*没产品类型*/
			hql+="and PosSetPriceRecord_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by PosSetPriceRecord_ChDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductName.trim())){
			/*时间、产品都有*/
			hql+="and pt.Product_Name='"+ProductName.trim()+"' and PosSetPriceRecord_ChDate between '"+start+"' and '"+end+"' order by PosSetPriceRecord_ChDate desc";
		}else{
			hql+="order by PosSetPriceRecord_ChDate desc";
		}
		return this.selectObject(hql, page);
		

	}
	
	public List<Object> selSellPriceByProductName(String start,String end,String ProductName){
		String hql="from Product pt,PosSetPriceRecord sp where 1=1 and sp.PosSetPriceRecord_ProductNum=pt.Product_Num ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductName.trim()))){
			/*没时间*/
			hql+="and pt.Product_Name='"+ProductName.trim()+"' order by PosSetPriceRecord_ChDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductName.trim())){
			/*没产品类型*/
			hql+="and PosSetPriceRecord_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by PosSetPriceRecord_ChDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductName.trim())){
			/*时间、产品都有*/
			hql+="and pt.Product_Name='"+ProductName.trim()+"' and PosSetPriceRecord_ChDate between '"+start+"' and '"+end+"' order by PosSetPriceRecord_ChDate desc";
		}else{
			hql+="order by PosSetPriceRecord_ChDate desc";
		}
		return this.selectObject(hql);
	}


}
