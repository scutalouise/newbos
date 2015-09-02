package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.bap.bos.dao.TransMonthDao;
import com.bap.bos.domain.TransMonth;
import com.bap.bos.util.DaoTemplate;


@Repository
public class TransMonthDaoImpl extends DaoTemplate<TransMonth,String> implements TransMonthDao{

	
	/**
	 * 获取月销售量
	 * @param ym
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月7日
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getProductTransAmounts(String ym ){
		StringBuilder hql = new StringBuilder();
		hql.append("select Tanksetting_ProductNum,sum(TransMonth_TransVol) as SellVol from tb_transmonthnozzleno ");
		hql.append("left join tb_NozzleSetting on NozzleSetting_Phy_Noz=TransMonth_NozzleNo ");
		hql.append("left join tb_Tanksetting on Tanksetting_TankNum=NozzleSetting_TankNum ");
		hql.append("where TransMonth_TransDate=? and TransMonth_CustomerType<>'08' and TransMonth_CustomerType<>'09' ");
		hql.append("group by Tanksetting_ProductNum ");
				 
		Query query = this.getSession().createSQLQuery(hql.toString());
		query.setString(0, ym);
		
		return query.list();
	}
	
	/**
	 * 获取回罐量
	 * @param ym
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月7日
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getProductTransInfo(String ym ,String... types){
		StringBuilder hql = new StringBuilder();
		hql.append("select Tanksetting_ProductNum,sum(TransMonth_TransVol) as RechargeVol from tb_transmonthnozzleno ");
		hql.append("left join tb_NozzleSetting on NozzleSetting_Phy_Noz=TransMonth_NozzleNo ");
		hql.append("left join tb_Tanksetting on Tanksetting_TankNum=NozzleSetting_TankNum ");
		hql.append("where TransMonth_TransDate=? ");

		Assert.notNull(types, "types must be have value!");
		
		if(types.length == 1){
			hql.append("and TransMonth_CustomerType=? ");
		}else{
			hql.append("and TransMonth_CustomerType in (:types) ");
		}
		hql.append(" group by Tanksetting_ProductNum ");
		
		Query query = this.getSession().createSQLQuery(hql.toString());
		query.setString(0, ym);
		if(types.length == 1){
			query.setString(1, types[0]);
		}else{
			query.setParameterList("types", types);
		}
		
		return query.list();
	}
	
	
	
	
	
}
