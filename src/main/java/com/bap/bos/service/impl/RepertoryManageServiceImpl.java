package com.bap.bos.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.RepertoryManageDao;
import com.bap.bos.domain.Repertory;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.service.RepertoryManageService;
import com.bap.bos.util.Page;
/**
 * 库存作业Service
 * @author zhulong
 *
 */

@Service
public class RepertoryManageServiceImpl implements RepertoryManageService {
	
	@Resource
	private RepertoryManageDao repertoryManageDao;
	
	Logger logger = LoggerFactory.getLogger(RepertoryManageServiceImpl.class);
	
	/**
	 * 保存库存记录
	 * @param R
	 */
	@Transactional
	public void saveRepertory(Repertory R){
		repertoryManageDao.saveRepertory(R);
	} 
	/**
	 * 删除库存记录
	 * @param R
	 * @return
	 */
	@Transactional
	public String delRepertory(Repertory R){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Repertory> delRepertory=repertoryManageDao.selRepertory(formatDate.format(R.getRepertory_AddTime()), formatDate.format(R.getRepertory_AddTime()), R.getRepertory_ProductType());
		if("0".equals(delRepertory.get(0).getRepertory_IsSync())){
			/*未同步到总部，可以进行删除*/
			repertoryManageDao.delRepertory(R);
			return "success";
		}else{
			return "UnDel";
		}
		
	}
	/**
	 * 修改库存信息
	 * @param R
	 * @return
	 */
	@Transactional
	public boolean updateRepertory(Repertory R) {
		try {
			repertoryManageDao.updateRepertory(R);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 签核库存信息
	 * @param sql
	 * @return
	 */
	@Transactional
	public boolean signRepertory(Repertory R){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String SyncDate=formatDate.format(new Date());
		try {
			String sql="update tb_Repertory set Repertory_IsSync='1',Repertory_SyncDate='"+SyncDate+"' " +
					" where Repertory_StationNo='"+R.getRepertory_StationNo()+"' and " +
			"Repertory_TankNum='"+R.getRepertory_TankNum()+"' and Repertory_ProductType='"+R.getRepertory_ProductType()+"' and " +
					"Repertory_ShiftNo='"+R.getRepertory_ShiftNo()+"'";
			repertoryManageDao.updateSQLRepertory(sql);
			return true;
		} catch (Exception e) {
			logger.error("签核库存信息异常！",e);
			return false;
		}
		
	}
	
	/**
	 * 查询库存记录
	 * @param start 开始时间
	 * @param end	结束时间	
	 * @param ProductType  产品类型 0-油       1-气
	 * @param page	分页类
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Object> selRepertory(String start,String end,String ProductType,Page page){
		return repertoryManageDao.selRepertory(start, end, ProductType, page);
	}
	@Transactional(readOnly=true)
	public List<Repertory> selRepertory(String start,String end,String ProductType){
		return repertoryManageDao.selRepertory(start, end, ProductType);
	}
	/**
	 * 查询库存记录（精确）
	 * @param ShiftNo-班别编号
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Object> selRepertory(String ShiftNo,Page page){
		return repertoryManageDao.selRepertory(ShiftNo, page);
	}
	/**
	 * 查询产品类型对应灌
	 * @param ProductType
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Tanksetting> selTank(String Tanksetting_ProductNum){
		return repertoryManageDao.selTank(Tanksetting_ProductNum);
	}
	
	
	
	public List<Tanksetting> selTank(String tanksetting_ProductNum,List<Integer> tankIds){
		return repertoryManageDao.selTank(tanksetting_ProductNum,tankIds);
	}
	
	/**
	 * 当前班别编号-库存表 （站点编号6 年月日 6  班别2）
	 * @param StationNo 站点编号
	 * @return
	 */
	@Transactional(readOnly=true)
	public String currentShiftNo(String StationNo){
		List<Shift> CurrentShift=repertoryManageDao.selCurrentShift(StationNo);
		if(org.springframework.util.CollectionUtils.isEmpty(CurrentShift))
			return "";
		/*年月日6位*/
		String currentDate=CurrentShift.get(0).getShift_ShiftDate().trim().substring(2);
		/*班别编号-站点编号6+ 年月日 6 + 班别2*/
		String currentShiftNo=CurrentShift.get(0).getShift_StationNo()+
								currentDate+CurrentShift.get(0).getShift_ShiftNo().trim();
		return currentShiftNo;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Shift> selCurrentShift(String shift_StationNo) {
		return repertoryManageDao.selCurrentShift(shift_StationNo);
	}
	
	

}
