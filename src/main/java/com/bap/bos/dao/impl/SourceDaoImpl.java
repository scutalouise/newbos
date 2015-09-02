package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.SourceDao;
import com.bap.bos.domain.DailySourceed;
import com.bap.bos.domain.SourceUsed;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;


/**
 * 能耗操作实现类
 * @author zhulong
 *
 */

@Repository
public class SourceDaoImpl extends DaoTemplate<DailySourceed,String> implements SourceDao{
	/**
	 * 保存每日能耗信息
	 * @param ds
	 */
	
	public void saveDailySourceed(DailySourceed ds){
		this.saveObject(ds);
	}
	/**
	 * 删除每日能耗信息
	 * @param ds
	 */
	
	public void delDailySourceed(DailySourceed ds){
		this.deleteObject(ds);
	}
	/**
	 * 更新每日能耗信息
	 * @param sql
	 */
	
	public void updateDailySourceed(String sql){
		this.updateSQL(sql);
	}
	/**
	 * 查询每日能耗信息
	 * @param start
	 * @param end
	 * @return
	 */
	
	public List<DailySourceed> selSourceed(String start,String end){
		String hql="from DailySourceed where DailySourced_CurrDate between '"+start+"' and '"+end+"' order by DailySourced_IsSync desc,DailySourced_CurrDate desc";		
		return this.selectObject(hql);
	}
	
	
	public List<DailySourceed> selSourceed(String start,String end,Page page){
		String hql="from DailySourceed where DailySourced_CurrDate between '"+start+"' and '"+end+"' order by DailySourced_IsSync desc,DailySourced_CurrDate desc";		
		return this.selectObject(hql, page);
	}
	/**
	 * 查询每月能耗计划
	 * @param date 年月201310
	 * @param StationNo 气站编号
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SourceUsed> selSourcePlan(String date){
		String hql="from SourceUsed where SourceUsed_TheDate='"+date+"'";
		Query query=this.getSession().createQuery(hql);
		return	(List<SourceUsed>) query.list();
	}
	/**
	 * 查询能耗计划
	 * @param start	年月201310
	 * @param end	年月201310
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SourceUsed> selSourcePlan(String start,String end){
		String hql="from SourceUsed where SourceUsed_TheDate>='"+start+"' and  SourceUsed_TheDate<='"+end+"'";
		Query query=this.getSession().createQuery(hql);
		return	(List<SourceUsed>) query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SourceUsed> selSourcePlan(String start,String end,Page page){
		String hql="from SourceUsed where SourceUsed_TheDate>='"+start+"' and  SourceUsed_TheDate<='"+end+"'";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return	(List<SourceUsed>) query.list();
	}


}
