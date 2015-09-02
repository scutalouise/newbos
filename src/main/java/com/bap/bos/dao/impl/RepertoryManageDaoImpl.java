package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.RepertoryManageDao;
import com.bap.bos.domain.Repertory;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;
/**
 * 库存作业数据库操作实现类
 * @author zhulong
 *
 */

@Repository
public class RepertoryManageDaoImpl extends DaoTemplate<Repertory, String> implements RepertoryManageDao {
	/**
	 * 保存库存信息
	 * @param R
	 */
	
	public void saveRepertory(Repertory R){
		this.saveObject(R);
	}
	/**
	 * 删除库存信息
	 * @param R
	 */
	
	public void delRepertory(Repertory R){
		this.deleteObject(R);
	}
	/**
	 * 修改库存信息
	 * @param R -库存实体
	 */
	
	public void updateRepertory(Repertory R){
		this.updateObject(R);
	}
	/**
	 * SQL修改库存信息
	 * @param sql
	 */
	
	public void updateSQLRepertory(String sql){
		this.updateSQL(sql);
	}
	/**
	 * 查询库存信息
	 * @param start 开始时间
	 * @param end	结束时间	
	 * @param ProductType 产品类型
	 * @return
	 */
	
	public List<Repertory> selRepertory(String start,String end,String ProductType){
		String hql="from Repertory where '1'='1' ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductType.trim()))){
			/*没时间*/
			hql+="and Repertory_ProductType='"+ProductType.trim()+"' order by Repertory_AddTime desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductType.trim())){
			/*没产品类型*/
			hql+="and Repertory_AddTime between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by Repertory_AddTime desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductType.trim())){
			/*时间、产品都有*/
			hql+="and Repertory_ProductType='"+ProductType.trim()+"' and Repertory_AddTime between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by Repertory_AddTime desc";
		}else{
			hql+="order by Repertory_AddTime desc";
		}
		return this.selectObject(hql);
	}
	@SuppressWarnings("unchecked")
	
	public List<Object> selRepertory(String start,String end,String ProductType,Page page){
		String hql="from Repertory r,Product p where '1'='1' and r.Repertory_ProductNum = p.Product_Num ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(ProductType.trim()))){
			/*没时间*/
			hql+="and Repertory_ProductType='"+ProductType.trim()+"' order by Repertory_AddTime desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(ProductType.trim())){
			/*没产品类型*/
			hql+="and Repertory_AddTime between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by Repertory_AddTime desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(ProductType.trim())){
			/*时间、产品都有*/
			hql+="and Repertory_ProductType='"+ProductType.trim()+"' and Repertory_AddTime between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by Repertory_AddTime desc";
		}else{
			hql+="order by Repertory_AddTime desc";
		}
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	/**
	 * 查询库存表
	 * @param ShiftNo - 班别编号
	 * @param page - 分页类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selRepertory(String ShiftNo,Page page){
		String hql="from Repertory r,Product p where r.Repertory_ProductNum = p.Product_Num and r.Repertory_ShiftNo='"+ShiftNo+"'";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	
	/**
	 * 查询油罐信息-相同产品的油罐
	 * @param Tanksetting_ProductNum 产品编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tanksetting> selTank(String Tanksetting_ProductNum){
		String hql="from Tanksetting where Tanksetting_ProductNum='"+Tanksetting_ProductNum+"'";
		Query query=this.getSession().createQuery(hql);
		return (List<Tanksetting>)query.list();
	}
	
	/**
	 * 查询油罐信息
	 * @param Tanksetting_ProductNum 产品编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tanksetting> selTank(String tanksetting_ProductNum,List<Integer> tankIds){
		StringBuilder hql= new StringBuilder("from Tanksetting where Tanksetting_ProductNum=:productNum ");
		if(tankIds.size() == 1){
			hql.append("and Tanksetting_tankNum = :tankid ");
		}else{
			hql.append("and Tanksetting_tankNum in (:tanksid) ");
		}
		
		Query query=this.getSession().createQuery(hql.toString());
		
		query.setString("productNum", tanksetting_ProductNum);
		if(tankIds.size() == 1){
			query.setInteger("tankid", tankIds.get(0));
		}else{
			query.setParameterList("tanksid",tankIds);
		}
		return (List<Tanksetting>)query.list();
	}
	
	/**
	 * 查询最近一次开班记录
	 * @param Shift_StationNo 站点编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Shift> selCurrentShift(String shift_StationNo){
		String hql="from Shift where Shift_StationNo='"+shift_StationNo+"' order by Shift_StartTime desc";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (List<Shift>)query.list();		
	}
	
	/**
	 * 获取某个班的库存信息
	 * @param shift
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getByShiftNoAndProduct(Shift shift){
		StringBuilder hql = new StringBuilder();
		hql.append("select Repertory_ProductNum,sum(Repertory_ProductVol),sum(Repertory_PetrolLevel) from Repertory ");
		hql.append("where Repertory_ShiftNo = ? and Repertory_CreateTime = ? group by Repertory_ProductNum ");
		
		Query query = this.getSession().createQuery(hql.toString());
		query.setString(0, shift.getShift_StationNo()+shift.getShift_ShiftDate().substring(2)+shift.getShift_ShiftNo());
		query.setString(1, shift.getShift_CreateTime());
		
		return query.list();
	}
}
