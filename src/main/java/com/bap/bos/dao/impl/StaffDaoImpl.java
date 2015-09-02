package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.StaffDao;
import com.bap.bos.domain.PositionType;
import com.bap.bos.domain.Staff;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;

/**
 * 员工操作实现类
 * 
 * @author zhulong
 * 
 */
@Repository
public class StaffDaoImpl extends DaoTemplate<Staff, String> implements
		StaffDao {
	/**
	 * 查询职位划分表
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PositionType> selPositionType() {
		String hql = "from PositionType";
		Query query = getSession().createQuery(hql);
		List<PositionType> lists = (List<PositionType>) query.list();
		return lists;
	}

	/**
	 * 查询ALL员工信息
	 * 
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selAllStaffsDetail(Page page) {
		String hql = "from Staff,StationStaff,PositionType where StationStaff_StaffNo=Staff_No and Staff_Position=PositionType_Num order by Staff_No";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
		
	}

	/**
	 * 查询ALL员工信息
	 * 
	 * @return
	 */
	
	public List<Staff> selAllStaffsDetail() {
		String hql = "from Staff,StationStaff,PositionType where StationStaff_StaffNo=Staff_No and Staff_Position=PositionType_Num";
		return this.selectObject(hql);
	}

	/**
	 * 查询员工信息
	 * 
	 * @param hql
	 * @return
	 */
	
	public List<Staff> selStaffDetail(String StaffNo, String StaffPW) {
		String hql = "from Staff where Staff_No='" + StaffNo
				+ "' and Staff_PW='" + StaffPW + "'";
		return this.selectObject(hql);
	}
	/**
	 * 查询员工信息-自动补全
	 * @param FirstName - 姓氏
	 * @return
	 */
	
	public List<Staff> selStaffName(String FirstName){
		String hql = "from Staff where Staff_Name like '%"+FirstName+"%'";
		return this.selectObject(hql);
	}
	/**
	 * 查询员工信息
	 * @param Name -姓名
	 * @param page -分页类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selStaffName(String Name,Page page) {
		String hql = "from Staff,StationStaff,PositionType where StationStaff_StaffNo=Staff_No and Staff_Position=PositionType_Num and Staff_Name='"+Name+"' order by Staff_No";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
		
	}
	/**
	 * 查询员工信息-自动补全
	 * @param FirstNo - 员工编号前几位
	 * @return
	 */
	
	public List<Staff> selStaffNo(String FirstNo){
		String hql = "from Staff where Staff_No like '"+FirstNo+"%'";
		return this.selectObject(hql);
	}
	/**
	 * 查询员工信息
	 * @param No -员工编号
	 * @param page -分页类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selStaffNo(String No,Page page) {
		String hql = "from Staff,StationStaff,PositionType where StationStaff_StaffNo=Staff_No and Staff_Position=PositionType_Num  and Staff_No='"+No+"' order by Staff_No";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	/**
	 * 查询员工信息-自动补全
	 * @param FirstID - 员工身份证前几位
	 * @return
	 */
	
	public List<Staff> selStaffID(String FirstID){
		String hql = "from Staff where Staff_ID like '"+FirstID+"%'";
		return this.selectObject(hql);
	}
	/**
	 * 查询员工信息
	 * @param ID -员工身份证号
	 * @param page -分页类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selStaffID(String ID,Page page) {
		String hql = "from Staff,StationStaff,PositionType where StationStaff_StaffNo=Staff_No and Staff_Position=PositionType_Num and  Staff_ID='"+ID+"' order by Staff_No";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	
	/**
	 * 查询员工信息
	 * @param Status -员工在职状态
	 * @param page -分页类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selStaffStatus(String Status,Page page) {
		String hql = "from Staff,StationStaff,PositionType where StationStaff_StaffNo=Staff_No and Staff_Position=PositionType_Num  and StationStaff_StaffStatus='"+Status+"' order by Staff_No";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>)query.list();
	}
	@SuppressWarnings("unchecked")
	
	public List<Object> selStaffStatus(String Status) {
		String hql = "from Staff,StationStaff,PositionType where StationStaff_StaffNo=Staff_No and Staff_Position=PositionType_Num and StationStaff_StaffStatus='"+Status+"' order by Staff_No";
		Query query=getSession().createQuery(hql);
		return (List<Object>)query.list();
	}

	/**
	 * 查询员工上下班信息
	 * @param start	查询开始时间点
	 * @param end	查询结束时间点
	 * @param selType	查询类型   -1：全部    1：姓名    2：员工号    3：身份证号	
	 * @param selWord	查询关键字
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Object> selAllStaffGroupsInfo(String start, String end,String selType,String selWord) {
		Integer type=Integer.valueOf(selType);
		String hql="from StaffGroupsInfo sgi,Staff sf where sgi.StaffGroupInfo_StaffNo=sf.Staff_No ";
		switch (type) {
		case -1:
			hql += "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		case 1:
			hql	+= "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
					"and sf.Staff_Name='"+selWord.trim()+"' " +
					"order by StaffGroupInfo_StartWorkTime";		
			break;
		case 2:
			hql	+= "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
					"and sf.Staff_No='"+selWord.trim()+"' " +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		case 3:
			hql	+= "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59'" +
					"and sf.Staff_ID='"+selWord.trim()+"' " +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		default:
			hql += "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59'" +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		}
		Query query = this.getSession().createQuery(hql);
		return (List<Object>) query.list();
	}

	@SuppressWarnings("unchecked")
	
	public List<Object> selAllStaffGroupsInfo(String start, String end,String selType,String selWord,Page page) {
		Integer type=Integer.valueOf(selType);
		String hql="from StaffGroupsInfo sgi,Staff sf where sgi.StaffGroupInfo_StaffNo=sf.Staff_No ";
		switch (type) {
		case -1:
			hql += "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		case 1:
			hql	+= "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
					"and sf.Staff_Name='"+selWord.trim()+"' " +
					"order by StaffGroupInfo_StartWorkTime";		
			break;
		case 2:
			hql	+= "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59' " +
					"and sf.Staff_No='"+selWord.trim()+"' " +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		case 3:
			hql	+= "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59'" +
					"and sf.Staff_ID='"+selWord.trim()+"' " +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		default:
			hql += "and sgi.StaffGroupInfo_StartWorkTime between '"+ start+ " 00:00:00' and '"+ end+ " 23:59:59'" +
					"order by StaffGroupInfo_StartWorkTime";
			break;
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((page.getPageNum() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<Object>) query.list();
	}
	

}
