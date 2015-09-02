package com.bap.bos.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.bap.bos.dao.ReferredCubageDao;
import com.bap.bos.domain.OrderBill;
import com.bap.bos.domain.ReferredCubage;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月3日
 */
@Repository
public class ReferredCubageDaoImpl extends DaoTemplate<OrderBill, String> implements ReferredCubageDao{

	@Override
	public ReferredCubage get(Long id) {
		String hql = "from ReferredCubage where enable = true and id = ?";
		return (ReferredCubage)this.getSession().createQuery(hql).setLong(0, id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReferredCubage> get(Page page) {
		String hql = "from ReferredCubage where enable = true";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<ReferredCubage>)query.list();
	}

	@Override
	public void add(ReferredCubage referredCubage) {
		this.getSession().save(ReferredCubage.class.getName(),referredCubage);
	}

	@Override
	public void fakeDelete(Long id) {
		String hql = "update ReferredCubage set enable = false where id = ?";
		this.getSession().createQuery(hql).setLong(0, id).executeUpdate();
	}
	
	/**
	 * 根据油罐信息和高度 删除 数据
	 * @param stationNo
	 * @param tankNum
	 * @param height
	 * @author edgar_chan
	 * @since 2015年4月3日
	 */
	@Override
	public void fakeDelete(String stationNo,String tankNum,Integer height) {
		String hql = "update ReferredCubage set enable = false where tankSetting = :tanksetting and height = :hi";
		this.getSession().createQuery(hql).setEntity("tanksetting", new Tanksetting(stationNo, tankNum)).setInteger("hi", height).executeUpdate();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<ReferredCubage> get(Map<String, Object> params, Page page) {
		StringBuilder hql = new StringBuilder("from ReferredCubage where enable = true ");
		
		if(params.containsKey("tanknum")){
			hql.append(" and tankSetting.Tanksetting_TankNum = :tanknum ");
		}
		
		Object minHeight = params.get("minHeight");
		Object maxHeight = params.get("maxHeight");
		
		if(minHeight != null && maxHeight != null){
			hql.append(" and height between :minheight and :maxheight ");
		}else if(minHeight != null){
			hql.append(" and height = :minheight ");
		}else if(maxHeight != null){
			hql.append(" and height = :maxheight ");
		}
		hql.append(" order by tankSetting.Tanksetting_TankNum,height");
		
		Query query  = this.getSession().createQuery(hql.toString());
		
		if(params.containsKey("tanknum")){
			query.setString("tanknum", params.get("tanknum").toString());
		}
		
		if(minHeight != null && maxHeight != null){
			query.setDouble("minheight", Double.valueOf(minHeight.toString()));
			query.setDouble("maxheight", Double.valueOf(maxHeight.toString()));
		}else if(minHeight != null){
			query.setDouble("minheight", Double.valueOf(minHeight.toString()));
		}else if(maxHeight != null){
			query.setDouble("maxheight", Double.valueOf(maxHeight.toString()));
		}
		
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		
		return (List<ReferredCubage>)query.list();
	}
	
	@Override
	public int get(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder("select count(*) from ReferredCubage where enable = true ");
		
		if(params.containsKey("tanknum")){
			hql.append(" and tankSetting.Tanksetting_TankNum = :tanknum ");
		}
		
		Object minHeight = params.get("minHeight");
		Object maxHeight = params.get("maxHeight");
		
		if(minHeight != null && maxHeight != null){
			hql.append(" and height between :minheight and :maxheight ");
		}else if(minHeight != null){
			hql.append(" and height = :minheight ");
		}else if(maxHeight != null){
			hql.append(" and height = :maxheight ");
		}
		hql.append(" order by tankSetting.Tanksetting_TankNum,height");
		
		Query query  = this.getSession().createQuery(hql.toString());
		
		if(params.containsKey("tanknum")){
			query.setString("tanknum", params.get("tanknum").toString());
		}
		
		if(minHeight != null && maxHeight != null){
			query.setDouble("minheight", Double.valueOf(minHeight.toString()));
			query.setDouble("maxheight", Double.valueOf(maxHeight.toString()));
		}else if(minHeight != null){
			query.setDouble("minheight", Double.valueOf(minHeight.toString()));
		}else if(maxHeight != null){
			query.setDouble("maxheight", Double.valueOf(maxHeight.toString()));
		}
		
		
		return ((Long)query.uniqueResult()).intValue();
	}
	

	@Override
	public Double get(String tankNum,Integer height) {
		Assert.notEmpty(new Object[]{tankNum,height},"参数不能为空！");
		
		StringBuilder hql = new StringBuilder("select cubage from ReferredCubage where enable = true ");
		hql.append(" and tankSetting.Tanksetting_TankNum = :tanknum and height = :height ");
		
		Query query  = this.getSession().createQuery(hql.toString());
		
		query.setString("tanknum", tankNum);
		query.setDouble("height", height);

		return (Double)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReferredCubage> get(Tanksetting tankSetting,List<Integer> heightList){
		String hql = "from ReferredCubage where tankSetting = :tanksetting and height in (:hiList) and enable = true order by height asc";
		
		Query query  = this.getSession().createQuery(hql.toString());
		query.setEntity("tanksetting", tankSetting);
		query.setParameterList("hiList",heightList,new IntegerType());
		
		return query.list();
	}
	
}
