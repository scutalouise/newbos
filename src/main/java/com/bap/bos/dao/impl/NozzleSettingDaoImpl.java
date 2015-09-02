package com.bap.bos.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.NozzleSettingDao;
import com.bap.bos.domain.NozzleSetting;
import com.bap.bos.util.DaoTemplate;


@Repository
public class NozzleSettingDaoImpl extends DaoTemplate<NozzleSetting, String> implements NozzleSettingDao  {
	@Resource(name = "sessionFactory")   
	public SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<NozzleSetting> selNozzleSetting(){
		String hql="from NozzleSetting order by NozzleSetting_Phy_Noz";
		return (List<NozzleSetting>)getSession().createQuery(hql).list();
	}
	/**
	 * 查询油罐对应油枪
	 * @param TankNum
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NozzleSetting> selNozzleTank(String tankNum){
		String hql = "";
		if(tankNum.contains(",")){
			hql="from NozzleSetting where NozzleSetting_TankNum in (:list) order by NozzleSetting_TankNum";
		}else{
			hql="from NozzleSetting where NozzleSetting_TankNum=:tanknum order by NozzleSetting_TankNum";
		}
		
		Query query = getSession().createQuery(hql);
		if(tankNum.contains(",")){
			query.setParameterList("list", StringUtils.split(tankNum,','));
		}else{
			query.setString("tanknum", tankNum);
		}
		return (List<NozzleSetting>)query.list();
	}
	
}
