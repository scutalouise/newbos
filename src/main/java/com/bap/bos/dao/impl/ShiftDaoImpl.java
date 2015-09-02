package com.bap.bos.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.ShiftDao;
import com.bap.bos.domain.NozzleShift;
import com.bap.bos.domain.SellPrice;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.TransItem;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;

/**
 * 交班-班结操作实现类
 * 
 * @author zhulong
 * 
 */

@Repository
public class ShiftDaoImpl extends DaoTemplate<Object, String> implements ShiftDao  {
	
	/**
	 * 查询班结审核情况
	 * @param start 开班日期
	 * @param end	开班日期
	 * @return
	 */
	
	public List<Object> selShiftDetail(String start,String end){   
//		String sql="select s.Shift_ShiftDate as Shift_ShiftDate,s.Shift_ShiftNo as Shift_ShiftNo," +
//				"sf1.Staff_Name as Staff_Name,s.Shift_StartTime as Shift_StartTime," +
//				"s.Shift_EndTime as Shift_EndTime,s.Shift_VerifyStatus as Shift_VerifyStatus," +
//				"sf2.Staff_Name as Staff_Name,s.Shift_VerifyTime as Shift_VerifyTime,s.Shift_IsSync as Shift_IsSync " +
//				"from tb_shift s left join tb_staff sf1 on sf1.Staff_No=s.Shift_StaffNo " +
//				"left join tb_staff sf2 on sf2.Staff_No=s.Shift_VerifyStaffNo " +
//				"where s.Shift_ShiftDate>='"+start+"' and s.Shift_ShiftDate<='"+end+"' " +
//						"order by s.Shift_IsSync desc, s.Shift_ShiftDate desc";
		String sql="select s.Shift_ShiftDate,s.Shift_ShiftNo," +
				"sf1.Staff_Name as staffname1,s.Shift_StartTime ," +
				"s.Shift_EndTime ,s.Shift_ShiftStatus ,s.Shift_CreateTime " +
				"from tb_shift s left join tb_staff sf1 on sf1.Staff_No=s.Shift_StaffNo " +
				"where s.Shift_ShiftDate>='"+start+"' and s.Shift_ShiftDate<='"+end+"' " +
						"order by s.Shift_IsSync desc, s.Shift_ShiftDate desc,s.Shift_ShiftNo";
		return this.selectSQLObject(sql);
	}
	
	public List<Object> selShiftDetail(String start,String end,Page page){
		String sql="select s.Shift_ShiftDate,s.Shift_ShiftNo," +
			"sf1.Staff_Name as staffname1,s.Shift_StartTime ," +
			"s.Shift_EndTime ,s.Shift_ShiftStatus ,s.Shift_CreateTime " +
			"from tb_shift s left join tb_staff sf1 on sf1.Staff_No=s.Shift_StaffNo " +
			"where s.Shift_ShiftDate>='"+start+"' and s.Shift_ShiftDate<='"+end+"' " +
					"order by s.Shift_IsSync desc, s.Shift_ShiftDate desc,s.Shift_ShiftNo";
		return this.selectSQLObject(sql,page);
	}
	/**
	 * 查询每班交易详情
	 * @param TransItem_ShiftDate 班别日期
	 * @param TransItem_ShiftNo	班别编号	
	 * @param TransItem_NozzleNo	枪号
	 * @param Shift_StartTime		开始时间
	 * @param Shift_EndTime		结束时间
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<TransItem> selShiftTransItem(String TransItem_ShiftDate,String TransItem_ShiftNo,
			String TransItem_NozzleNo,String Shift_StartTime,String Shift_EndTime){
		/*执行查询*/
		String sql="select * from tb_TransItem " +
				"where TransItem_ShiftDate='"+TransItem_ShiftDate+"' and TransItem_ShiftNo='"+TransItem_ShiftNo+"' and " +
				"TransItem_NozzleNo='"+TransItem_NozzleNo+"' and " +
				"TransItem_TransDate between '"+Shift_StartTime+"' and '"+Shift_EndTime+"' ";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(TransItem.class);//建立映射关系
		return (List<TransItem>)query.list();	
	}
	/**
	 * 油枪交班泵码记录
	 * @param NozzleShift_ShiftDate		开班日期
	 * @param NozzleShift_ShiftNo	 	班别编号
	 * @param NozzleShift_CreateTime	创建时间
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<NozzleShift> selShiftNozzle(String NozzleShift_ShiftDate,String NozzleShift_ShiftNo,
			String NozzleShift_CreateTime){
		String hql="from NozzleShift where NozzleShift_ShiftDate='"+NozzleShift_ShiftDate+"' " +
						"and NozzleShift_ShiftNo='"+NozzleShift_ShiftNo+"' and NozzleShift_CreateTime='"+NozzleShift_CreateTime+"' " +
						"order by NozzleShift_NozzleId";
		Query query = this.getSession().createQuery(hql);
		return (List<NozzleShift>)query.list();
	}
	
	/**
	 * 查询当前产品价格
	 * @param Phy_Noz	物理枪号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<SellPrice> selSellPrice(String Phy_Noz){
		String hql="from SellPrice where SellPrice_Phy_Noz='"+Phy_Noz+"'";
		Query query=this.getSession().createQuery(hql);
		return (List<SellPrice>)query.list();
	}
	/**
	 * 班结审核通过更新
	 * @param StationNo	站点编号
	 * @param ShiftDate	班别日期
	 * @param ShiftNo	辨别编号
	 * @param staffNo	审核员工编号
	 */
	
	public void shiftVerify(String StationNo,String ShiftDate,String ShiftNo,String staffNo,String Shift_CreateTime){
		SimpleDateFormat formatDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		String SyncDate=formatDate1.format(new Date());
		String sql="update tb_Shift set " +
				"Shift_IsSync='1',Shift_ShiftStatus ='1',Shift_SyncDate='"+SyncDate+"' " +
				"where Shift_StationNo='"+StationNo+"' and Shift_ShiftDate='"+ShiftDate+"' and Shift_ShiftNo='"+ShiftNo+"' and Shift_CreateTime='"+Shift_CreateTime+"' ";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	/**
	 * 查询一天的班别信息
	 * @param ShiftDate
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Shift> selDayShift(String ShiftDate){
		String hql="from Shift where Shift_ShiftDate=?";
		Query query=this.getSession().createQuery(hql);
		query.setString(0, ShiftDate);
		return (List<Shift>) query.list();
	}

	/**
	 * 用  not exists( 原因是 一个shiftdate下有多条班接记录（这些记录中有的shiftendtime is not null）  只有如此查询 才能排除 max 到未结束的班接日期
	 */
	@Override
	public String getTheLatestAffectiveVerifyDate(String Shift_StationNo,String endDate) {
		StringBuilder hql = new StringBuilder("select max(Shift_ShiftDate) from Shift t1 where Shift_StationNo = ? and Shift_ShiftDate <= ?");
		hql.append(" and not exists(from Shift t2 where t2.Shift_ShiftDate = t1.Shift_ShiftDate and t2.Shift_EndTime is null)");
		Query query = this.getSession().createQuery(hql.toString());
		query.setString(0, Shift_StationNo);
		query.setString(1, endDate);
		return (String)query.uniqueResult();
	}
	
	@Override
	public Shift getTheLatestAffectiveVerifyShift(String Shift_StationNo,String endDate) {
		StringBuilder hql = new StringBuilder("from Shift t1 where Shift_StationNo = ? and Shift_ShiftDate <= ?");
		hql.append(" and not exists(from Shift t2 where t2.Shift_ShiftDate = t1.Shift_ShiftDate and t2.Shift_EndTime is null) order by t1.Shift_EndTime desc ");
		Query query = this.getSession().createQuery(hql.toString());
		query.setString(0, Shift_StationNo);
		query.setString(1, endDate);
		query.setMaxResults(1);
		return (Shift)query.uniqueResult();
	}
	
	@Override
	public Shift getTheLatestShift(String Shift_StationNo,String endDate) {
		StringBuilder hql = new StringBuilder("from Shift t1 where Shift_StationNo = ? and Shift_ShiftDate <= ?");
		hql.append(" order by t1.Shift_StartTime desc ");
		Query query = this.getSession().createQuery(hql.toString());
		query.setString(0, Shift_StationNo);
		query.setString(1, endDate);
		query.setMaxResults(1);
		return (Shift)query.uniqueResult();
	}
	
	
	
	public String getTheFurthestAffectiveVerifyDate(String Shift_StationNo,String shiftdate){
		StringBuilder hql=new StringBuilder("select min(Shift_ShiftDate) from Shift t1 where Shift_StationNo= ? and Shift_ShiftDate >= ?");
		hql.append(" and not exists(from Shift t2 where t2.Shift_ShiftDate = t1.Shift_ShiftDate and t2.Shift_EndTime is null)");
		Query query = this.getSession().createQuery(hql.toString());
		query.setString(0, Shift_StationNo);
		query.setString(1, shiftdate);
		return (String)query.uniqueResult();
	}
	
	@Override
	public String getTheFurthestAffectiveVerifyDate(String Shift_StationNo) {
		StringBuilder hql=new StringBuilder("select min(Shift_ShiftDate) from Shift t1 where Shift_StationNo= ?");
		hql.append(" and not exists(from Shift t2 where t2.Shift_ShiftDate = t1.Shift_ShiftDate and t2.Shift_EndTime is null)");
		
		Query query = this.getSession().createQuery(hql.toString());
		query.setString(0, Shift_StationNo);
		
		return (String)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getShiftDateListByDateSpan(String startShiftDate,String endShiftDate) {
		String hql = "select Shift_ShiftDate from Shift where Shift_ShiftDate between ? and ? group by Shift_ShiftDate";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, startShiftDate);
		query.setString(1, endShiftDate);
		return query.list();
	}
	
	
	
}
