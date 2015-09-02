package com.bap.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bap.bos.dao.SellingTargetDao;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;


/**
 * 销售计划操作实现类
 * @author zhulong
 *
 */

@Repository
public class SellingTargetDaoImpl extends DaoTemplate<Object,String> implements SellingTargetDao {
	
	/**
	 * 销售计划
	 * @param start	计划时间
	 * @param end	计划时间
	 * @param ProductName	产品名称	
	 * @return
	 */
	
	public List<Object> selSellTarget(String start,String end,String ProductName){
		String sql="select SellingTarget_TheMonth,Product_Name,SellingTarget_PlanQTY,SellingTarget_ProductNum,SellingTarget_IsSync " +
				"from tb_SellingTarget left join tb_Product on Product_Num=SellingTarget_ProductNum ";
		if(ProductName==null || "".equals(ProductName.trim())){
			sql+="where SellingTarget_TheMonth>='"+start+"' and SellingTarget_TheMonth<='"+end+"' order by SellingTarget_TheMonth";
		}else{
			sql+="where SellingTarget_TheMonth>='"+start+"' and SellingTarget_TheMonth<='"+end+"' " +
					"and Product_Name='"+ProductName+"' order by SellingTarget_TheMonth";
		}
		return this.selectSQLObject(sql); 	
	}
	
	public List<Object> selSellTarget(String start,String end,String ProductName,Page page){
		String sql="select SellingTarget_TheMonth,Product_Name,SellingTarget_PlanQTY,SellingTarget_ProductNum,SellingTarget_IsSync " +
				"from tb_SellingTarget left join tb_Product on Product_Num=SellingTarget_ProductNum ";
		if(ProductName==null || "".equals(ProductName.trim())){
			sql+="where SellingTarget_TheMonth>='"+start+"' and SellingTarget_TheMonth<='"+end+"' order by SellingTarget_TheMonth";
		}else{
			sql+="where SellingTarget_TheMonth>='"+start+"' and SellingTarget_TheMonth<='"+end+"' " +
					"and Product_Name='"+ProductName+"' order by SellingTarget_TheMonth";
		}
		return this.selectSQLObject(sql,page); 	
	}
	
	@Override
	public void deleteSQL(String s) {
		// TODO Auto-generated method stub
		super.deleteSQL(s);
	}
	
	@Override
	public void saveObject(Object o) {
		// TODO Auto-generated method stub
		super.saveObject(o);
	}
	
	@Override
	public void updateSQL(String s) {
		// TODO Auto-generated method stub
		super.updateSQL(s);
	}
	
	@Override
	public void updateObject(Object o) {
		// TODO Auto-generated method stub
		super.updateObject(o);
	}
	
	


}
