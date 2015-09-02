package com.bap.bos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.TransCardDao;
import com.bap.bos.domain.TransCard;
import com.bap.bos.util.DaoTemplate;
/**
 * 查询卡系统每日交易
 * @author zhulong
 *
 */

@Repository
public class TransCardDaoImpl extends DaoTemplate<TransCard,String> implements TransCardDao  {
	/**
	 * 查询每日未结交易
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<TransCard> selDaysNoPayTransCard(String[] dateStrArr) throws Exception{
//		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String date1=format1.format(format.parse(TxCreateTime));
	//	String sql="select * from tb_TransCard"+YearMonth+" where TransCard_Paystate='01' and TransCard_TxCreateTime between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' order by TransCard_TxCreateTime desc";
		String sql="select * from tb_TransCard where TransCard_Paystate='01' and TransCard_TxCreateTime  between '"+dateStrArr[0]+"' and '"+dateStrArr[1]+"' order by TransCard_TxCreateTime desc";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(TransCard.class);//建立映射关系
		return (List<TransCard>)query.list();
	}
	/**
	 * 查询每日交易总账
	 * @return int[0]-交易笔数；int[1]-交易总金额;int[2]-交易总升数
	 */
	@SuppressWarnings("unchecked")
	
	public int[] selDaysTransTotal(String[] dates) throws Exception{
		int[] result=new int[3];
//		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String date1=format1.format(format.parse(TxCreateTime));
	//	String sql="select * from tb_TransCard"+YearMonth+" where TransCard_TxCreateTime between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' order by TransCard_TxCreateTime desc";
		String sql="select * from tb_TransCard where TransCard_TxCreateTime  between '"+dates[0]+"' and '"+dates[1]+"' order by TransCard_TxCreateTime desc";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(TransCard.class);//建立映射关系
		List<TransCard> DayTransCards=(List<TransCard>)query.list();;
		/*设置返回值*/
		int TransCount=DayTransCards.size();
		int TransMoney=0;
		int TransVol=0;
		for(int i=0;i<TransCount;i++){
			/*金额，升数，通过四舍五入后*100，转换为整数，方便和传输过来的数据比较*/
			TransMoney+=(int)((DayTransCards.get(i).getTransCard_PaidinAmount()+0.005)*100);
			TransVol+=(int)((DayTransCards.get(i).getTransCard_PayVol()+0.005)*100);
		}
		result[0]=TransCount;
		result[1]=TransMoney;
		result[2]=TransVol;
		return result;
	} 
	/**
	 * 更新当日是否对账标识-已对账
	 * @param TxCreateTime
	 */
	
	public void updateBalanceAcc(String[] dates) throws Exception{
//		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String date1=format1.format(format.parse(TxCreateTime));
	//	String sql="update tb_TransCard"+YearMonth+" set TransCard_BalanceAcc='00' where TransCard_TxCreateTime between '"+date1+" 00:00:00' and '"+date1+" 23:59:59'";
		String sql="update tb_TransCard set TransCard_BalanceAcc='00' where TransCard_TxCreateTime  between '"+dates[0]+"' and '"+dates[1]+"'" ;
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(TransCard.class);//建立映射关系
		query.executeUpdate();
	}
}
