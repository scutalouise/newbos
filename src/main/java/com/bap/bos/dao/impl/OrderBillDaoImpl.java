package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.OrderBillDao;
import com.bap.bos.domain.OrderBill;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;

@Repository
public class OrderBillDaoImpl extends DaoTemplate<OrderBill, String> implements OrderBillDao {
	/**
	 * 保存订单
	 * @param OrderBill
	 */
	public void saveOrderBill(OrderBill OrderBill){
		getSession().save(OrderBill);
	}
	/**
	 * 更新订单
	 * @param sql 更新sql语句
	 */

	public void updateOrderBill(String sql){
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	
	public void updateOrderBillOnRestock(String status,Boolean isRemedy,String billNum){
		StringBuilder hql = new StringBuilder();
		hql.append("update OrderBill set OrderBill_Status = ?,isRemedy = ? where OrderBill_Num = ? ");
	
		Query query = this.getSession().createQuery(hql.toString());
		query.setString(0, status);
		query.setBoolean(1, isRemedy);
		query.setString(2, billNum);
		
		query.executeUpdate();
	}
	
	/**
	 * 更新订单
	 * @param OrdereBill - 订单类
	 */
	public void updateOrdereBill(OrderBill OrdereBill){
		getSession().update(OrdereBill);
	}
	/**
	 * 查询当前日期已经存在的订单数
	 * @param OrderBillNumFront
	 * @return
	 */

	public Integer selOrderBill(String OrderBillNumFront){
		String hql="from OrderBill where OrderBill_Num like '"+OrderBillNumFront+"%'";
		return getSession().createQuery(hql).list().size();
	}
	/**
	 * 查询订单详情
	 * @param OrderBillNum
	 * @return
	 */
	public Object selOrderBillDetail(String OrderBillNum){
		String hql="from OrderBill,Product where OrderBill_Num = '"+OrderBillNum+"' and Product_Num=OrderBill_ProductNum";
		return (Object)getSession().createQuery(hql).list().get(0);
	}
	
	/**
	 * 查询订单
	 * @param start 创建订单时间-始
	 * @param end 创建订单时间-末
	 * @param ProductType 产品类型
	 * @param Status 订单状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderBill> selOrderBill(String StationNo,String start,String end,
			String QueryType,String QueryContext,String Status){
		String hql="from OrderBill where OrderBill_StationNo='"+StationNo+"' and '1'='1' ";
	//	System.out.print(Status);System.out.print(ProductType);
		if("0".equals(QueryType.trim())){
			/*未选择单项查询*/
			if(!"".equals(start.trim())&&!"".equals(end.trim())&&(Status==null||"".equals(Status)||"-1".equals(Status))){
				/*条件：时间*/
				hql+="and OrderBill_CreateDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by OrderBill_CreateDate desc";
			}else if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(Status.trim()))){
				/*条件：单据状态*/
				hql+="and OrderBill_Status='"+Status.trim()+"' order by OrderBill_CreateDate desc";
			}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&(!"".equals(Status.trim())&&!"-1".equals(Status))){
				/*条件：时间、单据状态*/
				hql+="and OrderBill_Status='"+Status.trim()+"' and OrderBill_CreateDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by OrderBill_CreateDate desc";
			}else{
				hql+="order by OrderBill_CreateDate desc";
			}
		}else{
			/*选择单项*/
			switch (Integer.valueOf(QueryType)) {
			case 1:
				//订单号
				hql+="and OrderBill_NumAliases='"+QueryContext+"' order by OrderBill_CreateDate desc";
				break;
			case 2:
				//产品编号
				hql+="and OrderBill_ProductNum='"+QueryContext+"' order by OrderBill_CreateDate desc";
				break;
			case 3:
				//灌号
				hql+="and OrderBill_TankNum like '%"+QueryContext+"%' order by OrderBill_CreateDate desc";
				break;
			case 4:
				hql+="and isRemedy = '"+QueryContext+"'";
				break;
			default:
				break;
			}
		}
		
		return (List<OrderBill>)getSession().createQuery(hql).list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OrderBill> selOrderBill(String StationNo,String start,String end,
			String QueryType,String QueryContext,String Status,Page page){
		String hql="from OrderBill where OrderBill_StationNo='"+StationNo+"' and  '1'='1' ";
		if("0".equals(QueryType.trim())){
			/*未选择单项查询*/
			if(!"".equals(start.trim())&&!"".equals(end.trim())&&((Status==null||"".equals(Status)||"-1".equals(Status)))){
				/*条件：时间*/
				hql+="and OrderBill_CreateDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by OrderBill_CreateDate desc";
			}else if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(Status.trim()))){
				/*条件：单据状态*/
				hql+="and OrderBill_Status='"+Status.trim()+"' order by OrderBill_CreateDate desc";
			}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&(!"".equals(Status.trim())&&!"-1".equals(Status))){
				/*条件：时间、单据状态*/
				hql+="and OrderBill_Status='"+Status.trim()+"' and OrderBill_CreateDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by OrderBill_CreateDate desc";
			}else{
				hql+="order by OrderBill_CreateDate desc";
			}
		}else{
			/*选择单项*/
			switch (Integer.valueOf(QueryType)) {
			case 1:
				//订单号
				hql+="and OrderBill_NumAliases='"+QueryContext+"' order by OrderBill_CreateDate desc";
				break;
			case 2:
				//产品编号
				hql+="and OrderBill_ProductNum='"+QueryContext+"' order by OrderBill_CreateDate desc";
				break;
			case 3:
				//灌号
				hql+="and OrderBill_TankNum like '%"+QueryContext+"%' order by OrderBill_CreateDate desc";
				break;
			case 4:
				//补录否
				hql+="and isRemedy = '"+QueryContext+"' order by OrderBill_CreateDate desc";
				break;
			default:
				break;
			}
		}
		Query query=getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<OrderBill>)query.list();
		
	}

	/**
	 * 更新订单录入方式
	 * @param orderNum
	 * @param recordStatus
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	public void upadteOrderBillRecordStatus(String orderNum,Boolean recordStatus){
		String hql = "update OrderBill set recordManual = ? where OrderBill_Num = ?";
		Query query = this.getSession().createQuery(hql);
		query.setBoolean(0, recordStatus);
		query.setString(1, orderNum);
		query.executeUpdate();
	}
	
}
