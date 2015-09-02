package com.bap.bos.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.OilUnloadingDao;
import com.bap.bos.domain.OilUnloadingData;
import com.bap.bos.util.DaoTemplate;

@Repository
public class OilUnloadingDaoImpl extends DaoTemplate<OilUnloadingData, String> implements OilUnloadingDao {
	@Resource(name = "sessionFactory")   
	public SessionFactory sessionFactory;
	
	/**
	 * 查询卸油数据
	 * @param OrderNum	订单号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<OilUnloadingData> selOilUnloadingData(String OrderNum){
		String hql="from OilUnloadingData where OilUnloadingData_OrderNum='"+OrderNum+"'";
		return (List<OilUnloadingData>)getSession().createQuery(hql).list();		
	}
	/**
	 * 添加卸油数据
	 * @param OilUnloadingData
	 */
	
	public void saveOilUnloadingData(OilUnloadingData OilUnloadingData){
		getSession().save(OilUnloadingData);
	}
	
	/**
	 * 
	 * @param orderNum
	 * @author edgar_chan
	 * @since 2015年4月14日
	 */
	public void deleteOilUnloadingData(String orderNum,String status){
		String hql = "delete from OilUnloadingData where OilUnloadingData_OrderNum = ? and OilUnloadingData_State = ?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, orderNum);
		query.setString(1, status);
		query.executeUpdate();
	}
	
}
