package com.bap.hos.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Station;
import com.bap.hos.dao.HosReportDao;


/**
 * 油站操作实现类
 * @author zhulong
 *
 */
@Repository
public class HosReportDaoImpl implements HosReportDao{
	@Resource(name = "sessionFactoryHos")   
	public SessionFactory sessionFactoryHos;
	
	public List<Station> selStationDetail(){
		String hql="from Station";
		return (List<Station>)sessionFactoryHos.getCurrentSession().createQuery(hql).list();
	}
	/**
	 * 查询开班记录
	 * @param Shift_StationNo 开班日期
	 * @return
	 */
	
	public List<Shift> selCurrentShift(String Shift_StationNo,String Shift_ShiftDate){
		String hql="from Shift where Shift_StationNo='"+Shift_StationNo+"' and Shift_ShiftDate='"+Shift_ShiftDate+"' order by Shift_StartTime desc";
		return sessionFactoryHos.getCurrentSession().createQuery(hql).list();		
	}


}
