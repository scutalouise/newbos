package com.bap.bos.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.ResultTransformer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.DensityDao;
import com.bap.bos.domain.Density;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;


@Repository
public class DensityDaoImpl extends DaoTemplate<Density, String> implements DensityDao {
/*	@Resource(name = "sessionFactory")   
	public SessionFactory sessionFactory;*/
	/**
	 * 保存密度
	 * @param Density
	 */
	
	public void saveDensity(Density Density){
		getSession().save(Density.getClass().getName(), Density);
	}
	/**
	 * 删除密度
	 * @param Density_ID
	 */
	
	public void deleteDensity(int Density_ID){
		String sql="delete from tb_Density where Density_ID="+Density_ID+"";
		getSession().createSQLQuery(sql).executeUpdate();	
	}
	
	/**
	 * 删除密度
	 * @param density
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	public void delete(Density density){
		this.getSession().delete(density);
	}
	
	/**
	 * 更新密度
	 * @param Density
	 */
	
	public void updateDensity(Density density){
		StringBuilder sqlBuilder = new StringBuilder("update Density set densityManual = ? where id = ? ");
		
		Query query = getSession().createQuery(sqlBuilder.toString());
		
		query.setDouble(0, density.getDensityManual());
		query.setLong(1, density.getId());
		query.executeUpdate();
	}
	/**
	 * 查询密度
	 * @param Density_ProdunctNum	产品编号
	 * @param page	分页类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selDensity(String Density_ProdunctNum,Page page){
		String sql="select Product_Name,Density_Density,Density_startDate,Density_endDate,Product_Num " +
			" from tb_Density " +
			" left join tb_Product on Product_Num=Density_ProdunctNum " +
			"  ";
		if("-1".equals(Density_ProdunctNum)){
			sql+="order by Density_ProdunctNum";
		}else{
			sql+="where Density_ProdunctNum='"+Density_ProdunctNum+"' order by Density_startDate";
		}		
		Query query=this.getSession().createSQLQuery(sql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selDensity(String Density_ProdunctNum){
		String sql="select Product_Name,Density_Density,Density_startDate,Density_endDate,Product_Num " +
		" from tb_Density " +
		" left join tb_Product on Product_Num=Density_ProdunctNum " +
		"  ";
		if("-1".equals(Density_ProdunctNum)){
			sql+="order by Density_ProdunctNum";
		}else{
			sql+="where Density_ProdunctNum='"+Density_ProdunctNum+"' order by Density_startDate";
		}
		Query query=this.getSession().createSQLQuery(sql);
		return (List<Object>)query.list();
	}
	
	@Override
	public void deleteDensity(String stationNo, String startDate,
			String productNum) {
		String sql="delete from tb_Density where Density_ProdunctNum=? and Density_StartDate = ? and Density_StationNo = ?";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, StringUtils.isNotBlank(productNum)?productNum:"-1");
		query.setString(1, startDate.substring(0,startDate.length()-2));
		query.setString(2, stationNo);
		
		query.executeUpdate();
	}
	
	/**
	 * 查看某种油品密度设置是否与已设置好的油品密度时间范围有冲突
	 * @param density
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月16日
	 */
	public boolean existConfictingDensityDateSpan(Density density){
		StringBuilder hql = new StringBuilder("select count(*) from Density where Density_ProdunctNum = :product ");
		hql.append(" and (Density_StartDate > :start and Density_StartDate < :end) or (Density_endDate > :start and Density_endDate < :end) or (Density_StartDate < :start and Density_endDate > :end)");
		Query query = this.getSession().createQuery(hql.toString());
		
		query.setString("product", density.getDensity_ProdunctNum());
		query.setDate("start", density.getDensity_StartDate());
		query.setDate("end", density.getDensity_endDate());
		
		return (Long)query.uniqueResult() > 0;
	}
	
	/**
	 * 获取数量
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月18日
	 */
	public long count(){
		String hql = "select count(*) from Density";
		return (Long)this.getSession().createQuery(hql).uniqueResult();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Density> get(Map<String, Object> params, Page page) {
		StringBuilder hql = new StringBuilder();
		
		hql.append("select p.Product_Name,d.id,d.densityManual,d.Density_StartDate,d.Density_endDate,d.Density_Density ");
		hql.append("from Density d,Product p where p.Product_Num = d.Density_ProdunctNum ");
		
		String productId = (String)params.get("productId");
		String yearMonth = (String)params.get("yearMonth");
		
		if(StringUtils.isNotBlank(productId)){
			hql.append(" and Density_ProdunctNum = :productId ");
		}
		if(StringUtils.isNotBlank(yearMonth)){
			hql.append("  and Density_StartDate >= :yearMonths and Density_StartDate < :yearMonthe  ");
		}

		hql.append(" order by Density_StartDate desc ");
		
		Query query  = this.getSession().createQuery(hql.toString()).setResultTransformer(new ResultTransformer(){
			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Density density = new Density();
				density.setId(Long.valueOf(tuple[1].toString()));
				density.setProductName(tuple[0].toString());
				density.setDensityManual(tuple[2] == null ? null : Double.valueOf(tuple[2].toString()));
				density.setDensity_StartDate((Date)tuple[3]);
				density.setDensity_endDate((Date)tuple[4]);
				density.setDensity_Density(Double.valueOf(tuple[5].toString()));
				return density;
			}

			@Override
			public List<Density> transformList(@SuppressWarnings("rawtypes") List collection) {
				return collection;
			}
		});
		
		if(StringUtils.isNotBlank(productId)){
			query.setString("Density_ProdunctNum", productId);
		}
		
		if(StringUtils.isNotBlank(yearMonth)){
			DateTime yearMonths = DateTime.parse(yearMonth, DateTimeFormat.forPattern("yyyy-MM"));
			DateTime yearMonthe = yearMonths.plusMonths(1);
			
			query.setDate("yearMonths", yearMonths.toDate());
			query.setDate("yearMonthe", yearMonthe.toDate());
		}
		
		
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		
		return (List<Density>)query.list();
	}
	
	@Override
	public int get(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder("select count(*) from Density where 1=1 ");
		
		String productId = (String)params.get("productId");
		String yearMonth = (String)params.get("yearMonth");
		
		
		if(StringUtils.isNotBlank(productId)){
			hql.append(" and Density_ProdunctNum = :productId ");
		}
		if(StringUtils.isNotBlank(yearMonth)){
			hql.append(" and Density_StartDate >= :yearMonths and Density_StartDate < :yearMonthe ");
		}

		Query query  = this.getSession().createQuery(hql.toString());
		
		if(StringUtils.isNotBlank(productId)){
			query.setString("Density_ProdunctNum", productId);
		}
		
		if(StringUtils.isNotBlank(yearMonth)){
			DateTime yearMonths = DateTime.parse(yearMonth, DateTimeFormat.forPattern("yyyy-MM"));
			DateTime yearMonthe = yearMonths.plusMonths(1);
			
			query.setDate("yearMonths", yearMonths.toDate());
			query.setDate("yearMonthe", yearMonthe.toDate());
		}
		
		return ((Long)query.uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Density> getDensities(DateTime[] dates) {
		String hql = "from Density where Density_StartDate = ? ";
		
		Query query = this.getSession().createQuery(hql);
		query.setDate(0, dates[0].toDate());
		
		return query.list();
	}
	
	
	
}
