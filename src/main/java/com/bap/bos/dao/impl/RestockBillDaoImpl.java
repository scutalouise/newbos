package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.RestockBillDao;
import com.bap.bos.domain.RestockBill;
import com.bap.bos.util.DaoTemplate;
import com.bap.utils.DateTimeAddition;

@Repository
public class RestockBillDaoImpl extends DaoTemplate<RestockBill, String> implements RestockBillDao {
	/**
	 * 保存入库单
	 * @param RestockBill
	 */
	
	public void saveRestockBill(RestockBill RestockBill){
		this.saveObject(RestockBill);
	}
	/**
	 * 查询当前日期已经存在的入库单数
	 * @param OrderBillNumFront
	 * @return
	 */
	
	public Integer selRestockBill(String RestockBillNumFront){
		String hql="from RestockBill where RestockBill_RestockNum like '"+RestockBillNumFront+"%'";
		return this.getSession().createQuery(hql).list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRestockAmountBySpan(DateTime[] dates) {
		String hql = "select RestockBill_ProductNum,sum(RestockBill_receipts) from RestockBill where restockbill_shiftDate between ? and ? group by RestockBill_ProductNum ";
		
		Query query = this.getSession().createQuery(hql);
		query.setString(0, dates[0].toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0)));
		query.setString(1, dates[1].plusDays(-1).toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0)));
		
		return query.list();
	}
	
}
