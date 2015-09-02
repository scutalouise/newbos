package com.bap.bos.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.PromotionDao;
import com.bap.bos.domain.Promotion;
import com.bap.bos.domain.PromotionInfo;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;


@Repository("promotionDao")
public class PromotionDaoImpl extends DaoTemplate<Promotion, String> implements PromotionDao {
	@Resource(name = "sessionFactory")   
	public SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#savePromotion(com.bos.daomain.Promotion)
	 */
	public void savePromotion(Promotion Promotion){
		getSession().save(Promotion);
	}
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#savePromotionInfo(com.bos.daomain.PromotionInfo)
	 */
	public void savePromotionInfo(PromotionInfo PromotionInfo){
		getSession().save(PromotionInfo);	
	}
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#deletePromotion(com.bos.daomain.Promotion)
	 */
	public void deletePromotion(Promotion Promotion){
		getSession().delete(Promotion);
	}
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#deletePromotionInfo(int)
	 */
	public void deletePromotionInfo(int PromotionInfo_Num){
		String sql="delete from tb_PromotionInfo where PromotionInfo_Num="+PromotionInfo_Num+"";
		getSession().createSQLQuery(sql).executeUpdate();	
	}
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#updatePromotionInfo(com.bos.daomain.PromotionInfo)
	 */
	public void updatePromotionInfo(PromotionInfo PromotionInfo){
		String sql="update tb_PromotionInfo " +
				" set PromotionInfo_Name='"+PromotionInfo.getPromotionInfo_Name()+"'," +
						"PromotionInfo_Count="+PromotionInfo.getPromotionInfo_Count()+"," +
						"PromotionInfo_Remark='"+PromotionInfo.getPromotionInfo_Remark()+"'" +
				" where PromotionInfo_Num="+PromotionInfo.getPromotionInfo_Num()+"";
		getSession().createSQLQuery(sql).executeUpdate();	
	}
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#selPromotion(java.lang.String, com.bos.util.Page)
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selPromotion(String Promotion_ProductNum,Page page){
		String sql="select Product_Name,Promotion_TimeStart,Promotion_TimeEnd,Promotion_MoneyLow," +
			"Promotion_MoneyHigh,PromotionInfo_Name,PromotionInfo_Count,PromotionInfo_Remark,Promotion_ProductNum " +
			" from tb_Promotion " +
			" left join tb_PromotionInfo on PromotionInfo_Num=Promotion_InfoNum" +
			" left join tb_Product on Product_Num=Promotion_ProductNum " +
			"  ";
		if("-1".equals(Promotion_ProductNum)){
			sql+="order by Promotion_TimeStart";
		}else{
			sql+="where Promotion_ProductNum='"+Promotion_ProductNum+"' order by Promotion_TimeStart";
		}		
		Query query=this.getSession().createSQLQuery(sql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#selPromotion(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selPromotion(String Promotion_ProductNum){
		String sql="select Product_Name,Promotion_TimeStart,Promotion_TimeEnd,Promotion_MoneyLow," +
		"Promotion_MoneyHigh,PromotionInfo_Name,PromotionInfo_Count,PromotionInfo_Remark,Promotion_ProductNum " +
		" from tb_Promotion " +
		" left join tb_PromotionInfo on PromotionInfo_Num=Promotion_InfoNum" +
		" left join tb_Product on Product_Num=Promotion_ProductNum " +
		"  ";
		if("-1".equals(Promotion_ProductNum)){
			sql+="order by Promotion_TimeStart";
		}else{
			sql+="where Promotion_ProductNum='"+Promotion_ProductNum+"' order by Promotion_TimeStart";
		}		
		Query query=this.getSession().createSQLQuery(sql);
		return (List<Object>)query.list();
	}
	/**
	 * 查询唯一性
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selPromotionUniqueness(String sql){		
		Query query=this.getSession().createSQLQuery(sql);
		return (List<Object>)query.list();
	}
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.PromotionDao#selPromotionInfo()
	 */
	@SuppressWarnings("unchecked")
	
	public List<PromotionInfo> selPromotionInfo(){
		String hql="from PromotionInfo";			
		Query query=this.getSession().createQuery(hql);
		return (List<PromotionInfo>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PromotionInfo> getPromotionInfos(Page page) {
		String hql = "from PromotionInfo";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<PromotionInfo>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PromotionInfo> getPromotionInfos(String name, Page page) {
		StringBuilder hql = new StringBuilder("from PromotionInfo where 1=1 ");
		
		if(!StringUtils.isEmpty(name)){
			hql.append(" and PromotionInfo_Name = ? ");
		}
		
		Query query  = this.getSession().createQuery(hql.toString());
		
		if(!StringUtils.isEmpty(name)){
			query.setString(0, name);
		}
		
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		
		return (List<PromotionInfo>)query.list();
	}
	
	@Override
	public Long countPromotionInfo(String name) {
		StringBuilder hql = new StringBuilder("select count(*) from PromotionInfo where 1=1 ");
		
		if(!StringUtils.isEmpty(name)){
			hql.append(" and PromotionInfo_Name = ? ");
		}
		
		Query query  = this.getSession().createQuery(hql.toString());
		
		if(!StringUtils.isEmpty(name)){
			query.setString(0, name);
		}
		
		return (Long)query.uniqueResult();
	}
	@Override
	public boolean existsSameNamePromotionInfo(PromotionInfo promotionInfo) {
	
		StringBuilder hql = new StringBuilder("select count(*) from PromotionInfo where PromotionInfo_Name = ?");
		
		if(null != promotionInfo.getPromotionInfo_Num()){
			hql.append(" and PromotionInfo_Num <> ? ");
		}
		
		Query query  = this.getSession().createQuery(hql.toString());
		
		query.setString(0, promotionInfo.getPromotionInfo_Name());
		if(null != promotionInfo.getPromotionInfo_Num()){
			query.setInteger(1, promotionInfo.getPromotionInfo_Num());
		}
		
		return (Long)query.uniqueResult() > 0;
	}
	
	
}
