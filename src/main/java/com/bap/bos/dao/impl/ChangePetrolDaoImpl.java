package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.ChangePetrolDao;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;
/**
 * 油品换号操作实现类
 * @author zhulong
 *
 */
@Repository
public class ChangePetrolDaoImpl extends DaoTemplate<Object,String> implements ChangePetrolDao{
	/**
	 * 查询油品换号
	 * @param start 换号时间
	 * @param end	换号时间
	 * @param TankNum	油罐编号
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selChangePetrol(String start,String end,String TankNum,Page page){
		String sql="select ChangePetrol_TankNum,ifnull(pt2.Product_Name,''),ifnull(pt1.Product_Name,'')," +
		"ChangePetrol_ChDate,ChangePetrol_EffDate,ChangePetrol_SellPrice " +
		"from tb_changepetrol cp,tb_product pt1, tb_product pt2 where 1=1 and " +
		"cp.changepetrol_oldpetrolnum=pt1.product_num and cp.changepetrol_newpetrolnum=pt2.product_num ";
//		String sql="select ChangePetrol_TankNum,pt.Product_Name,pt1.Product_Name," +
//				"ChangePetrol_ChDate,ChangePetrol_EffDate,ChangePetrol_SellPrice " +
//				"from tb_changepetrol cp left join tb_product pt on cp.changepetrol_newpetrolnum=pt.product_num " +
//				"left join tb_product pt1 on cp.changepetrol_oldpetrolnum=pt1.product_num where 1=1 ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(TankNum.trim()))){
			/*没时间*/
			sql+="and ChangePetrol_TankNum='"+TankNum.trim()+"' order by ChangePetrol_ChDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(TankNum.trim())){
			/*没灌号*/
			sql+="and ChangePetrol_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by ChangePetrol_ChDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(TankNum.trim())){
			/*时间、灌号都有*/
			sql+="and ChangePetrol_TankNum='"+TankNum.trim()+"' and ChangePetrol_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by ChangePetrol_ChDate desc";
		}else{
			sql+="order by ChangePetrol_ChDate desc";
		}
		return this.selectSQLObject(sql, page);
	}
	
	public List<Object> selChangePetrol(String start,String end,String TankNum){
		String sql="select ChangePetrol_TankNum,ifnull(pt2.Product_Name,''),ifnull(pt1.Product_Name,'')," +
		"ChangePetrol_ChDate,ChangePetrol_EffDate,ChangePetrol_SellPrice " +
		"from tb_changepetrol cp,tb_product pt1, tb_product pt2 where 1=1 and " +
		"cp.changepetrol_oldpetrolnum=pt1.product_num and cp.changepetrol_newpetrolnum=pt2.product_num ";
		if("".equals(start.trim())&&"".equals(end.trim())&&!("".equals(TankNum.trim()))){
			/*没时间*/
			sql+="and ChangePetrol_TankNum='"+TankNum.trim()+"' order by ChangePetrol_ChDate desc";	
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&"".equals(TankNum.trim())){
			/*没灌号*/
			sql+="and ChangePetrol_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by ChangePetrol_ChDate desc";
		}else if(!"".equals(start.trim())&&!"".equals(end.trim())&&!"".equals(TankNum.trim())){
			/*时间、灌号都有*/
			sql+="and ChangePetrol_TankNum='"+TankNum.trim()+"' and ChangePetrol_ChDate between '"+start+" 00:00:00' and '"+end+" 23:59:59' order by ChangePetrol_ChDate desc";
		}else{
			sql+="order by ChangePetrol_ChDate desc";
		}
		return this.selectSQLObject(sql);
	}
	
	/**
	 * 查询油罐信息-所有油罐
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Tanksetting> selTank(){
		String hql="from Tanksetting order by Tanksetting_TankNum";
		Query query=this.getSession().createQuery(hql);
		return (List<Tanksetting>)query.list();
	}

}
