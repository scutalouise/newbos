package com.bap.bos.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.DayTransVerifyDao;
import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;
/**
 * 日结记录表
 * @author zhulong
 *
 */
@Repository
public class DayTransVerifyDaoImpl extends DaoTemplate<DayTransVerify,String> implements DayTransVerifyDao {
	/**
	 * 保存日结记录
	 * @param DayTransVerify 
	 */
	
	public void saveDayTransVerify(DayTransVerify DayTransVerify){
		this.saveObject(DayTransVerify);
	}
	/**
	 * 更新日结记录-status
	 * @param DayTransVerify
	 * @param Status
	 */
	
	public void updateDayTransVerify(String DayTransVerify,String Status,String StaffNo,String isSync){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		String sql="update tb_DayTransVerify set DayTransV_Status='"+Status+"'," +
				"DayTransV_VerifyTime='"+formatDate.format(new Date())+"'," +
				"DayTransV_VerifyStaffNo='"+StaffNo+"'," +
				"DayTransV_SyncDate='"+formatDate.format(new Date())+"',DayTransV_IsSync = " + isSync +
				" where DayTransV_Time='"+DayTransVerify+"'";
		this.updateSQL(sql);
	}
	/**
	 * 查询日结记录情况
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selDayTransVerify(String start,String end){
		//查询出当日所有班结成功的日期，才可以用于日结操作
		String sql="select DayTransV_Time,DayTransV_Status,DayTransV_VerifyTime,Staff_Name,DayTransV_autosure " +
				"from tb_DayTransVerify left join tb_Staff on " +
				"DayTransV_VerifyStaffNo=Staff_No where DayTransV_Time between '"+start+"' and '"+end+"' " +
				"and DayTransV_Time not in " +
				"(select Shift_ShiftDate from tb_Shift where Shift_ShiftDate  between '"+start+"' and '"+end+"' and (Shift_EndTime is null or Shift_ShiftStatus = '0'))" +
				" "+
				"order by DayTransV_Time desc";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		return (List<Object>)query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object> selDayTransVerify(String start,String end,Page page){
		String sql="select DayTransV_Time,DayTransV_Status,DayTransV_VerifyTime,Staff_Name,DayTransV_autosure " +
			"from tb_DayTransVerify left join tb_Staff on " +
			"DayTransV_VerifyStaffNo=Staff_No where DayTransV_Time between '"+start+"' and '"+end+"' " +
			"and DayTransV_Time not in " +
			"(select Shift_ShiftDate from tb_Shift where Shift_ShiftDate  between '"+start+"' and '"+end+"' and (Shift_EndTime is null or Shift_ShiftStatus = '0'))" +
			"order by DayTransV_Time desc";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	
	@Override
	public boolean exists(DayTransVerify dayTransVerify) {
		
		String hql = "select count(*) from DayTransVerify where DayTransV_StationNo = ? and DayTransV_VerifyTime = ?";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, dayTransVerify.getDayTransV_StationNo());
		query.setString(1, dayTransVerify.getDayTransV_Time());
		
		return (Long)query.uniqueResult() > 0;
	}
	
	
	/**
	 * 获取最近一条数据的日结日期
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月12日
	 */
	public String getCurrentTransVerifyDate(){
		String hql = "select max(DayTransV_Time) from DayTransVerify";
		Query query = this.getSession().createQuery(hql);
		return (String)query.uniqueResult();
	}
	
	/**
	 * 获取最远一条数据的日结日期
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月12日
	 */
	public String getFarestTransVerifyDate(){
		String hql = "select min(DayTransV_Time) from DayTransVerify";
		Query query = this.getSession().createQuery(hql);
		return (String)query.uniqueResult();
	}
}
