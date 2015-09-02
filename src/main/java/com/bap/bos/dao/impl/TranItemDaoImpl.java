package com.bap.bos.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.TranItemDao;
import com.bap.bos.domain.TransItem;
import com.bap.bos.util.DaoTemplate;

@Repository
public class TranItemDaoImpl extends DaoTemplate<TransItem,String> implements TranItemDao {
	/**
	 * 按班次进行报表查询
	 * @param TransItem_ShiftDate
	 * @param TransItem_ShiftNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<TransItem> selTransItem(String TransItem_ShiftDate,String TransItem_ShiftNo){
		/*获取年月*/
		String YearMonth=TransItem_ShiftDate.substring(0,6);
	//	String date=c[0]+c[1]+c[2];
//		String sql="select TransItem_TransNo,TransItem_TransDate,TransItem_StationNo,TransItem_ShiftNo," +
//			"TransItem_ShiftDate,TransItem_SellPrice,TransItem_PayVol,TransItem_PayMoney,TransItem_TransStat," +
//			"TransItem_Tend,p.Product_Name as TransItem_ProductName,TransItem_ItemCode " +
//			"from tb_TransItem"+YearMonth+" t,tb_Product p " +
//			"where p.Product_Num=t.TransItem_ItemCode and TransItem_ShiftDate between to_date('"+date+"000000','yyyy-mm-dd:hh24:mi:ss') and to_date('"+date+"235959','yyyy-mm-dd:hh24:mi:ss') and " +
//				"TransItem_ShiftNo='"+TransItem_ShiftNo+"'";
		String sql="select TransItem_TransNo,TransItem_TransDate,TransItem_StationNo,TransItem_ShiftNo," +
		"TransItem_ShiftDate,TransItem_SellPrice,TransItem_PayVol,TransItem_PayMoney,TransItem_TransStat," +
		"TransItem_Tend,tb_Product.Product_Name as TransItem_ProductName,TransItem_ItemCode " +
		"from tb_TransItem left join tb_Product on tb_Product.Product_Num=tb_TransItem.TransItem_ItemCode " +
		"where TransItem_ShiftDate='"+TransItem_ShiftDate+"' and " +
			"TransItem_ShiftNo='"+TransItem_ShiftNo+"'";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(TransItem.class);//建立映射关系
		return (List<TransItem>)query.list();	
	}
	/**
	 * 班结交易补录
	 * @param TransItem	交易类
	 */
	
	public void saveTransItem(TransItem TransItem){
		/*获取年月*/
		String YearMonth=TransItem.getTransItem_ShiftDate().substring(0,6);
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=formatDate.format(new Date());
		String sql="insert into tb_TransItem" +
				"(TransItem_TransNo,TransItem_TransDate,TransItem_StationNo,TransItem_ShiftNo,TransItem_ShiftDate," +
				"TransItem_NozzleNo,TransItem_ItemCode,TransItem_SellPrice,TransItem_PayVol,TransItem_PayMoney," +
				"TransItem_TransStat,TransItem_Tend,TransItem_IsSync,TransItem_SyncDate) " +
				"values " +
				"('"+TransItem.getTransItem_TransNo()+"','"+formatDate.format(TransItem.getTransItem_TransDate())+"','"+TransItem.getTransItem_StationNo()+"'," +
				"'"+TransItem.getTransItem_ShiftNo()+"','"+TransItem.getTransItem_ShiftDate()+"','"+TransItem.getTransItem_NozzleNo()+"'," +
				"'"+TransItem.getTransItem_ItemCode()+"',"+TransItem.getTransItem_SellPrice()+","+TransItem.getTransItem_PayVol()+"," +
				""+TransItem.getTransItem_PayMoney()+",'05','01','1','"+date+"')";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(TransItem.class);//建立映射关系
		query.executeUpdate();
	}
	
	/**
	 * 按时间查询交易
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<TransItem> selTransItemDate(Date startDate,Date endDate){
		/*获取年月*/
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		String YearMonth=formatDate.format(startDate);
		String sql="select TransItem_TransNo,TransItem_TransDate,TransItem_StationNo,TransItem_ShiftNo," +
				"TransItem_ShiftDate,TransItem_SellPrice,TransItem_PayVol,TransItem_PayMoney,TransItem_TransStat," +
				"TransItem_Tend,tb_Product.Product_Name as TransItem_ProductName,TransItem_ItemCode " +
				"from tb_TransItem left join tb_Product on tb_Product.Product_Num=tb_TransItem"+YearMonth+".TransItem_ItemCode " +
				"where TransItem_TransDate between "+startDate+" and "+endDate+"";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(TransItem.class);//建立映射关系
		return (List<TransItem>)query.list();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getGroupedTransItemDate(String shiftDate,String shiftNo,String tend) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("select p.Product_Name as t_productName,t.TransItem_ItemCode as t_oilcode,t.TransItem_SellPrice as t_price,sum(t.TransItem_PayVol) as t_vol,sum(t.TransItem_PayMoney) as t_amount,count(*) as t_billNum ");
		hql.append(" from TransItem t,Product p  ");
		
		hql.append("where  p.Product_Num = t.TransItem_ItemCode and t.TransItem_ShiftDate = ? and t.TransItem_ShiftNo = ? ");
		
		if(StringUtils.isBlank(tend)){
		}else if(tend.indexOf(',') > 0){
			hql.append("and t.TransItem_Tend in (:tend) "); 
		}else{
			
			hql.append("and t.TransItem_Tend = ? "); 
		}
		if(StringUtils.isBlank(tend)){ //假如 支付类型为空 则不group 单价， 藉此以获取某种油品的总输出量
			hql.append("group by t.TransItem_ItemCode order by t.TransItem_ItemCode asc,t.TransItem_SellPrice");
		}else{
			hql.append("group by t.TransItem_ItemCode,t.TransItem_SellPrice order by t.TransItem_ItemCode asc,t.TransItem_SellPrice");
		}
		
		
		Query query = this.getSession().createQuery(hql.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		query.setString(0, shiftDate);
		query.setString(1, shiftNo);
		
		if(StringUtils.isBlank(tend)){
		}else if(tend.indexOf(',') > 0){
			query.setParameterList("tend", StringUtils.split(tend,','));
		}else{
			query.setString(2, tend);
		}
		return query.list();
	}

	@Override
	public List<Map<String, Object>> getGroupedTransItemDate(String shiftDate,String shiftNo) {
		return getGroupedTransItemDate(shiftDate,shiftNo,null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getNozzlePumpValOnSellPriceChanged(String shiftDate,String shiftNo) {
		StringBuilder sql = new StringBuilder();
		
		
		sql.append("select  TransItem_ItemCode,TransItem_SellPrice,sum(tt.endvol)  from " );
		sql.append("(select TransItem_ItemCode , TransItem_NozzleNo,TransItem_SellPrice,max(TransItem_PT_End)  as endvol from ");
		sql.append("tb_TransItem where TransItem_ShiftDate = ? and TransItem_ShiftNo = ? group by  TransItem_NozzleNo,TransItem_SellPrice ) tt group by TransItem_ItemCode,TransItem_SellPrice");
		sql.append(" order by transitem_itemcode,transitem_sellprice asc ");
		
	//	hql.append("select * from (select TransItem_SellPrice,TransItem_NozzleNo,max(TransItem_PT_End) from TransItem where TransItem_ShiftDate = ? and TransItem_ShiftNo = ? group by TransItem_SellPrice,TransItem_NozzleNo");
		
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setString(0, shiftDate);
		query.setString(1, shiftNo);
		
		return query.list();
	}
	
	
	
	
}
