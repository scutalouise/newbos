package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.DayAndMouthStatisticsDao;
import com.bap.bos.domain.CustomerType;
import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.domain.MonthDateAera;
import com.bap.bos.domain.TransDay;
import com.bap.bos.domain.TransMonth;
import com.bap.bos.domain.TransType;
import com.bap.bos.util.DaoTemplate;
/**
 * 每日交易、每月交易统计实现类
 * @author zhulong
 *
 */
@Repository
public class DayAndMouthStatisticsDaoImpl extends DaoTemplate<Object, String> implements DayAndMouthStatisticsDao {
	
	
	public Object[] getTransItemSumVolAndSumAmount(String transType,String nozzleNo,String[] dates){
		
		StringBuilder hql = new StringBuilder("select sum(TransItem_PayVol),sum(TransItem_PayMoney),count(*) from TransItem ");
		hql.append("where TransItem_TransDate between ? and ? and TransItem_Tend=? and TransItem_NozzleNo=? group by TransItem_Tend,TransItem_NozzleNo");
		
		Query query = this.getSession().createQuery(hql.toString());
		
		query.setString(0, dates[0]);
		query.setString(1, dates[1]);
		query.setString(2, transType);
		query.setString(3, nozzleNo);
		
		return (Object[])query.uniqueResult();
	}

	
	public Object[] getTransCardSumVolAndSumAmount(String transType,String nozzleNo,String[] dates){
		
		StringBuilder hql = new StringBuilder("select sum(TransCard_PayVol),sum(TransCard_PayAmount),count(*),sum(TransCard_PaidinAmount) from TransCard ");
		hql.append("where TransCard_TxCreateTime between ? and ? and TransCard_TransType=? and TransCard_NozzleNo=? group by TransCard_TransType,TransCard_NozzleNo");
		
		Query query = this.getSession().createQuery(hql.toString());
		
		query.setString(0, dates[0]);
		query.setString(1, dates[1]);
		query.setString(2, transType);
		query.setString(3, nozzleNo);
		
		return (Object[])query.uniqueResult();
	}
	
	/**
	 * 查询客户类型
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CustomerType> selCustomerType(){
		String hql="from CustomerType";
		Query query=this.getSession().createQuery(hql);
		return (List<CustomerType>)query.list();	
	}
	/**
	 * 查询交易类型
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TransType> selTransType(){
		String hql="from TransType";
		Query query=this.getSession().createQuery(hql);
		return (List<TransType>)query.list();	
	}
	/**
	 * 查询日结记录表(已经结款的日期)
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<DayTransVerify> selDayTransVerify(){
		String hql="from DayTransVerify where DayTransV_Status='1'";
		Query query=this.getSession().createQuery(hql);
		return (List<DayTransVerify>)query.list();	
	}
	
	/**
	 * 获取已日结确认未日统计的日期
	 * @param standardDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月16日
	 */
	@SuppressWarnings("unchecked")
	public List<String> getNoStaticDayTransVerify(String standardDate){
		String hql="select DayTransV_Time from DayTransVerify where DayTransV_Status='1' and DayTransV_Time > ?";
		Query query=this.getSession().createQuery(hql);
		query.setString(0, standardDate);
		return query.list();	
	}
	
	/**
	 * 查询日统计所有日期
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> selTransDay(){
		String hql = "select TransDay_TransDate from TransDay group by TransDay_TransDate";
		return this.getSession().createQuery(hql).list();	
	}
	
	/**
	 * 获取最近一个日统计日期
	 * @return
	 */
	public String getlatestTransDayDate(){
		String hql = "select max(TransDay_TransDate) from TransDay";
		return (String)this.getSession().createQuery(hql).uniqueResult();	
	}
	
	/**
	 * 获取最远一个日统计日期
	 * @return
	 */
	public String getFarestTransDayDate(){
		String hql = "select min(TransDay_TransDate) from TransDay";
		return (String)this.getSession().createQuery(hql).uniqueResult();	
	}
	
	
	/**
	 * 查询本站所有油枪枪号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> selNozzleSetting(){
		String hql="select NozzleSetting_Phy_Noz from NozzleSetting";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}
	/**
	 * 保存日统计记录
	 * @param TransDay
	 */
	
	public void saveTransDay(TransDay TransDay){
		this.getSession().persist(TransDay);
	}
	/**
	 * 查询每月的日统计
	 * @param startDate - 年月日，例如20140201
	 * * @param startDate - 年月日，例如20140231
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TransDay> selTransDay(String startDate,String endDate){
		String hql="from TransDay where TransDay_TransDate between '"+startDate+"' and '"+endDate+"'";
		Query query=this.getSession().createQuery(hql);
		return (List<TransDay>)query.list();	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TransDay> getTransDay(String startDate,String endDate){
		String hql="from TransDay where TransDay_TransDate between '"+startDate+"' and '"+endDate+"'";
		Query query=this.getSession().createQuery(hql);
		return (List<TransDay>)query.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTransDaySumVolAndSumAmountAndCount(String startDate,String endDate){
		
		StringBuilder hql = new StringBuilder("select TransDay_NozzleNo,TransDay_CustomerType,sum(TransDay_TransVol),sum(TransDay_TransMoney),sum(TransDay_PaidInMoney),sum(TransDay_TransCount) from TransDay ");
		hql.append("where TransDay_TransDate between ? and ? group by TransDay_NozzleNo, TransDay_CustomerType");
		
		Query query = this.getSession().createQuery(hql.toString());
		
		query.setString(0, startDate);
		query.setString(1, endDate);
		
		return query.list();
	}

	
	
	/**
	 * 保存每月记录
	 * @param TransMonth
	 */
	
	public void saveMonthDay(TransMonth TransMonth){
		this.getSession().persist(TransMonth);
	}
	

	/**
	 * 获取最近的月统计月份
	 * @param TransMonth
	 */
	
	public String getLatestMonthDay(){
		String hql = "select max(TransMonth_TransDate) from TransMonth";
		return (String)this.getSession().createQuery(hql).uniqueResult();
	}
	
	/**
	 * 查询月结 天的跨度
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月15日
	 */
	public MonthDateAera getMonthStaticDaySpan(){
		String hql="from MonthDateAera";
		Query query=this.getSession().createQuery(hql);
		return (MonthDateAera)query.uniqueResult();	
	}
	
	
	public boolean haveDoneMonthStatistic(String ym){
		String hql = "select count(*) from TransMonth where TransMonth_TransDate = ?";
		return (Long)this.getSession().createQuery(hql).setString(0, ym).uniqueResult() > 0;
	}
	
}
