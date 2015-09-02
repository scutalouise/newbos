package com.bap.bos.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.ReportManageDao;
import com.bap.bos.domain.Shift;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.SellDetails;

/**
 * 报表管理页面动态选择数据操作类
 * @author zhulong
 *
 */
@Repository
public class ReportManageDaoImpl extends  DaoTemplate<Shift, String> implements ReportManageDao{
	/**
	 * 查询开班记录
	 * @param Shift_StationNo 开班日期
	 * @return
	 */
	
	public List<Shift> selCurrentShift(String Shift_StationNo,String Shift_ShiftDate){
		String hql="from Shift where Shift_StationNo='"+Shift_StationNo+"' and Shift_ShiftDate='"+Shift_ShiftDate+"' order by Shift_StartTime desc";
		return this.selectObject(hql);		
	}
	
	/**
	 * 返回最近的一条班接数据
	 * @param Shift_StationNo
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月10日
	 */
	public Shift selLatestShift(String Shift_StationNo){
		String hql="from Shift where Shift_StationNo='"+Shift_StationNo+"' order by Shift_StartTime desc";
		Query query = this.getSession().createQuery(hql);
		return (Shift)query.setMaxResults(1).uniqueResult();
	}
	
	
	/**
	 * 交易详情
	 * @param startDate - 开始时间
	 * @param endDate	- 结束时间
	 * @param staffNo	员工编号
	 * @param cardNo	客户卡号
	 * @param nozzleNo	枪号
	 * @return
	 * @throws ParseException 
	 */
	
	@SuppressWarnings("unchecked")
	public List<SellDetails> sellDetails(String startDate,String endDate,String staffNo,String cardNo,String nozzleNo) throws ParseException{
		String[] c=startDate.split("-");
		String YearMonth=c[0]+c[1];
		String sql= "select TransItem_ShiftNo,TransItem_ShiftDate,TransItem_TransDate,TransItem_TransNo,TransCard_CardNo," +
				"TransItem_NozzleNo,CustomerType_Desc,TransItem_SellPrice,TransItem_PayVol,TransItem_PayMoney,Station_No," +
				"Station_Name,TaxRete_TaxRate,TransItem_Tend,TransCard_PaidinAmount " +
				"from tb_TransItem " +
				"left join tb_TransCard on TransCard_TransNo=TransItem_TransNo " +
				"left join tb_CardHolder on CardHolder_CardNo=TransCard_CardNo " +
				"left join tb_Customer on Customer_CustID=CardHolder_CustID " +
				"left join tb_CustomerType on CustomerType_ID=Customer_CustomerType " +
				"left join (select Station_No,Station_Name from tb_Station)Station on Station.Station_No=TransItem_StationNo " +
				"left join tb_TaxRate on TaxRete_StationNo=TransItem_StationNo " +
				"where TransItem_TransDate between '"+startDate+" 00:00:00' and '"+endDate+" 23:59:59' ";
		if("".equals(staffNo)&&"".equals(cardNo)&&nozzleNo.equals(nozzleNo)){
			sql+="order by TransItem_ShiftDate,TransItem_ShiftNo";
		}
		
		SQLQuery query=this.getSession().createSQLQuery(sql);
//		query.addEntity(SellDetails.class);
//		List<SellDetails> result=(List<SellDetails>)query.list();
//		for(int i=0;i<result.size();i++){
//			System.out.println(result.get(i).getTransItem_TransNo());
//		}
//		return result;
		List<Object> list=(List<Object>)query.list();
		List<SellDetails> newlist = new ArrayList<SellDetails>();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		if(list != null && list.size()>0){
			    SellDetails sd;
			    for(int i = 0; i < list.size();i++){
			     Object[] object = (Object[])list.get(i);// 每行记录不在是一个对象 而是一个数组
			     String TransItem_ShiftNo =  (String)object[0];
			     String TransItem_ShiftDate =  (String)object[1];
			     Date TransItem_TransDate =  formatDate.parse(String.valueOf(object[2]));
			     String TransItem_TransNo =  (String)object[3];
			     String TransCard_CardNo =  (String)object[4];
			     if(object[4]==null){
			    	 TransCard_CardNo="";
			     }else{
			    	 TransCard_CardNo =  (String)object[4];;	
			     } 		 
			     String TransItem_NozzleNo =  (String)object[5];
			     String CustomerType_Desc =  (String)object[6];
			     float TransItem_SellPrice = Float.valueOf(String.valueOf(object[7])) ;
			     float TransItem_PayVol = Float.valueOf(String.valueOf(object[8]));
			     float TransItem_PayMoney = Float.valueOf(String.valueOf(object[9]));
			     String Station_No =  (String)object[10];
			     String Station_Name =  (String)object[11];
			     int TaxRete_TaxRate =  Integer.valueOf(String.valueOf(object[12]));
			     String TransItem_Tend =  (String)object[13];
			     float TransCard_PaidinAmount;
			     if(object[14]==null){
			    	 TransCard_PaidinAmount=0;
			     }else{
			    	  TransCard_PaidinAmount =  Float.valueOf(String.valueOf(object[14]));	
			     } 		     
			     // 重新封装在一个javabean里面
			     
			     sd = new SellDetails(TransItem_ShiftNo, TransItem_ShiftDate, TransItem_TransDate, TransItem_TransNo, 
			    		 TransCard_CardNo, TransItem_NozzleNo, CustomerType_Desc, TransItem_SellPrice, 
			    		 TransItem_PayVol, TransItem_PayMoney, Station_No, Station_Name, TaxRete_TaxRate, 
			    		 TransItem_Tend, TransCard_PaidinAmount);
			   /*  sd.setCustomerType_Desc(CustomerType_Desc);
			     sd.setStation_Name(Station_Name);
			     sd.setStation_No(Station_No);
			     sd.setTaxRete_TaxRate(TaxRete_TaxRate);
			     sd.setTransCard_CardNo(TransCard_CardNo);
			     sd.setTransCard_PaidinAmount(TransCard_PaidinAmount);
			     sd.setTransItem_NozzleNo(TransItem_NozzleNo);
			     sd.setTransItem_PayMoney(TransItem_PayMoney);
			     sd.setTransItem_PayVol(TransItem_PayVol);
			     sd.setTransItem_SellPrice(TransItem_SellPrice);
			     sd.setTransItem_ShiftDate(TransItem_ShiftDate);
			     sd.setTransItem_ShiftNo(TransItem_ShiftNo);
			     sd.setTransItem_Tend(TransItem_Tend);
			     sd.setTransItem_TransDate(TransItem_TransDate);
			     sd.setTransItem_TransNo(TransItem_TransNo);*/
			     newlist.add(sd); // 最终封装在list中
			    }
		}
		return newlist;
	}
}
