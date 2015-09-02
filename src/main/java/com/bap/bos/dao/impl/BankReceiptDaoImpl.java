package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.BankReceiptDao;
import com.bap.bos.domain.SaveToBank;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;
/**
 * 银行存款作业数据库操作实现类
 * @author zhulong
 *
 */
@Repository
public class BankReceiptDaoImpl extends DaoTemplate<SaveToBank, String> implements BankReceiptDao {
	/**
	 * 保存存款信息
	 * @param ds
	 */
	
	public void saveSaveToBank(SaveToBank sb){
		this.saveObject(sb);
	}
	/**
	 * 删除存款信息
	 * @param ds
	 */
	
	public void delSaveToBank(SaveToBank sb){
		this.deleteObject(sb);
	}
	/**
	 * 更新存款信息
	 * @param sql
	 */
	
	public void updateSaveToBank(String sql){
		this.updateSQL(sql);
	}
	/**
	 * 查询存款信息
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selSaveToBank(String start,String end){
		String hql="from SaveToBank sb,Staff sf where sb.SaveToBank_StaffNo=sf.Staff_No and " +
				"sb.SaveToBank_SaveTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
				"order by sb.SaveToBank_IsSync desc ,sb.SaveToBank_SaveTime desc";		
		Query query=this.getSession().createQuery(hql);
		return (List<Object>)query.list();
	}
	@SuppressWarnings("unchecked")
	
	public List<Object> selSaveToBank(String start,String end,Page page){
		String hql="from SaveToBank sb,Staff sf where sb.SaveToBank_StaffNo=sf.Staff_No and " +
				"sb.SaveToBank_SaveTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
				"order by sb.SaveToBank_IsSync desc , sb.SaveToBank_SaveTime desc";			
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	
	public List<SaveToBank> selOneSaveToBank(String StationNo,String Receipt){
		String hql="from SaveToBank where SaveToBank_StationNo='"+StationNo+"' and SaveToBank_Receipt='"+Receipt+"'";		
		return this.selectObject(hql);
	}
	
}
