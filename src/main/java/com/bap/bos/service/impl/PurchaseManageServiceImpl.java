package com.bap.bos.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.OrderBillDao;
import com.bap.bos.dao.ProductDao;
import com.bap.bos.dao.RestockBillDao;
import com.bap.bos.dao.ShiftDao;
import com.bap.bos.domain.OrderBill;
import com.bap.bos.domain.RestockBill;
import com.bap.bos.domain.Shift;
import com.bap.bos.service.PurchaseManageService;
import com.bap.bos.util.Page;
import com.bap.utils.DateTimeAddition;

/**
 * 采购作业管理
 * @author zhulong
 *
 */

@Service
public class PurchaseManageServiceImpl implements PurchaseManageService  {
	
	@Resource private RestockBillDao restockBillDao;
	@Resource private OrderBillDao orderBillDao;
	@Resource private ProductDao productDao;
	@Resource private ShiftDao shiftDao;
	
	/**
	 * 保存订单
	 * @param OrderBill
	 */
	@Transactional
	public void saveOrderBill(OrderBill OrderBill){
		orderBillDao.saveOrderBill(OrderBill);
	}
	/**
	 * 创建订单编号
	 * @param StationNo
	 * @return
	 */
	@Transactional
	public String createOrderBillNum(String StationNo){
		String OrderBillNum="";
		/*得到当前年月日 6位*/
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String currdate=format.format(new Date()).substring(2);
		/*拼接站点编号*/
		String OrderBillNumFront=StationNo.trim()+currdate;
		/*得到后四位编码*/
		Integer OrderBillsize=orderBillDao.selOrderBill(OrderBillNumFront);
		char[] ary1 = OrderBillsize.toString().toCharArray();
	    char[] ary2 = {'0','0','0','0'};
		System.arraycopy(ary1, 0, ary2, ary2.length-ary1.length, ary1.length);
		String OrderBillNumlast = new String(ary2);
		OrderBillNum=OrderBillNumFront+OrderBillNumlast;
		return OrderBillNum;
		
	}
	/**
	 * 查询订单
	 * @param start 开始时间	
	 * @param end 结束时间
	 * @param QueryType 查询类型
	 * * @param QueryType 查询内容
	 * @param Status 订单状态
	 * @return
	 */
	@Transactional
	public List<OrderBill> selOrderBill(String StationNo,String start,String end,
			String QueryType,String QueryContext,String Status){
		if("2".equals(QueryType)){
			//若按照名称查询
			String ProNum=productDao.selProductNum(QueryContext);
			if(ProNum.length()>0){
				return orderBillDao.selOrderBill(StationNo,start, end,QueryType,ProNum, Status);
			}else{
				return null;
			}
		}else{
			return orderBillDao.selOrderBill(StationNo,start, end,QueryType,QueryContext, Status);
		}
		
	}
	@Transactional
	public List<OrderBill> selOrderBill(String StationNo,String start,String end,
			String QueryType,String QueryContext,String Status,Page page){
		if("2".equals(QueryType)){
			//若按照名称查询
			String ProNum=productDao.selProductNum(QueryContext);
			if(null==ProNum){
				return null;
			}else{
				return orderBillDao.selOrderBill(StationNo,start, end,QueryType,ProNum, Status,page);
			}
			
		}else{
			return orderBillDao.selOrderBill(StationNo,start, end,QueryType,QueryContext, Status,page);
		}
		
	}
	/**
	 * 创建入库单编号
	 * @param StationNo 站点编号
	 * @return
	 */
	@Transactional
	public String createRestockBillNum(String StationNo){
		String RestockBillNum="";
		/*得到当前年月日 6位*/
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String currdate=format.format(new Date()).substring(2);
		/*拼接站点编号*/
		String RestockBillNumFront=StationNo.trim()+currdate;
		/*得到后四位编码*/
		Integer OrderBillsize=restockBillDao.selRestockBill(RestockBillNumFront);
		char[] ary1 = OrderBillsize.toString().toCharArray();
	    char[] ary2 = {'0','0','0','0'};
		System.arraycopy(ary1, 0, ary2, ary2.length-ary1.length, ary1.length);
		String RestockBillNumlast = new String(ary2);
		RestockBillNum=RestockBillNumFront+RestockBillNumlast;
		return RestockBillNum;
	}
	/**
	 * 保存入库单
	 * @param RestockBill 入库单实体类
	 */
	@Transactional
	public String saveRestockBill(RestockBill restockBill,String stationNo){
		
		Shift currentShift = shiftDao.getTheLatestShift(stationNo, DateTime.now().toString(DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_0)));

		boolean isRemedy = false;
		if(!currentShift.getShift_ShiftDate().equals(restockBill.getRestockBill_ShiftDate())){
			isRemedy = true;
		}		
		orderBillDao.updateOrderBillOnRestock("03", isRemedy, restockBill.getRestockBill_OrderNum());
		restockBillDao.saveRestockBill(restockBill);
		return "success";
	}
	/**
	 * 确认订单
	 * @param OrderBillNum
	 * @return
	 */
	@Transactional
	public boolean verifyOrderBill(String OrderBillNum,String StaffNo){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=formatDate.format(new Date());
		String sql="update tb_OrderBill set OrderBill_Status='02',OrderBill_AckDate='"+date+"',OrderBill_AckStaffNo='"+StaffNo+"' where OrderBill_Num='"+OrderBillNum+"'";
		try {
			orderBillDao.updateOrderBill(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 取消订单
	 * @param OrderBillNum
	 * @return
	 */
	@Transactional
	public boolean cancelOrderBill(String OrderBillNum){
		String sql="update tb_OrderBill set OrderBill_Status='04' where OrderBill_Num='"+OrderBillNum+"'";
		try {
			orderBillDao.updateOrderBill(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 查询订单详情
	 * @param OrderBillNum - 订单编号
	 * @return
	 */
	@Transactional
	public Object selOrderBill(String OrderBillNum){
		return orderBillDao.selOrderBillDetail(OrderBillNum);
	}
	/**
	 * 订货
	 * @param OrdereBill - 订单详情
	 * @return
	 */
	@Transactional
	public boolean orderGoods(OrderBill OrdereBill){
		String sql="update tb_OrderBill set";
		String Unit=OrdereBill.getOrderBill_Unit();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		switch(Integer.valueOf(Unit.trim())){
		case 1:
			sql += " OrderBill_ActOrderDate='"+formatDate.format(new Date())+"', " +
				" OrderBill_ActPlanDate='"+formatDate.format(OrdereBill.getOrderBill_ActPlanDate())+"'," +
				" OrderBill_ActOrderStaffNo='"+OrdereBill.getOrderBill_ActOrderStaffNo()+"'," +
				" OrderBill_Status='01'," +
				" OrderBill_CostPrice="+OrdereBill.getOrderBill_CostPrice()+"," +
				" OrderBill_ActVolumeM="+OrdereBill.getOrderBill_ActVolumeM()+"," +
				" OrderBill_SupplierNum="+OrdereBill.getOrderBill_SupplierNum()+"" +
				" where OrderBill_Num='"+OrdereBill.getOrderBill_Num()+"'";
			break;
		case 2:
			sql += " OrderBill_ActOrderDate='"+formatDate.format(new Date())+"'," +
				" OrderBill_ActPlanDate='"+formatDate.format(OrdereBill.getOrderBill_ActPlanDate())+"'," +
				" OrderBill_ActOrderStaffNo='"+OrdereBill.getOrderBill_ActOrderStaffNo()+"'," +
				" OrderBill_Status='01'," +
				" OrderBill_CostPrice="+OrdereBill.getOrderBill_CostPrice()+"," +
				" OrderBill_ActVolumeL="+OrdereBill.getOrderBill_ActVolumeL()+"," +
				" OrderBill_SupplierNum="+OrdereBill.getOrderBill_SupplierNum()+"" +
				" where OrderBill_Num='"+OrdereBill.getOrderBill_Num()+"'";
			break;
		case 3:
			sql += " OrderBill_ActOrderDate='"+formatDate.format(new Date())+"'," +
				" OrderBill_ActPlanDate='"+formatDate.format(OrdereBill.getOrderBill_ActPlanDate())+"'," +
				" OrderBill_ActOrderStaffNo='"+OrdereBill.getOrderBill_ActOrderStaffNo()+"'," +
				" OrderBill_Status='01'," +
				" OrderBill_CostPrice="+OrdereBill.getOrderBill_CostPrice()+"," +
				" OrderBill_ActWeight="+OrdereBill.getOrderBill_ActWeight()+"," +
				" OrderBill_SupplierNum="+OrdereBill.getOrderBill_SupplierNum()+"" +
				" where OrderBill_Num='"+OrdereBill.getOrderBill_Num()+"'";
			break;
		default:
			break;
		}
		try {	
			orderBillDao.updateOrderBill(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 卸油状态更新
	 * @param Num	订单号
	 * @param status	订单状态
	 * @return
	 */
	@Transactional
	public boolean unloadingOil(String Num,String status){
		String sql="update tb_OrderBill set OrderBill_Status='"+status+"' where OrderBill_Num='"+Num+"'";
		try {	
			orderBillDao.updateOrderBill(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 卸油状态更新
	 * @param Num	订单号
	 * @param status	订单状态
	 * @return
	 */
	@Transactional
	public void upadteOrderBillRecordStatus(String num,Boolean status){
		orderBillDao.upadteOrderBillRecordStatus(num, status);
	}
	
	public RestockBillDao getRestockBillDao() {
		return restockBillDao;
	}
	public void setRestockBillDao(RestockBillDao restockBillDao) {
		this.restockBillDao = restockBillDao;
	}
	public OrderBillDao getOrderBillDao() {
		return orderBillDao;
	}
	public void setOrderBillDao(OrderBillDao orderBillDao) {
		this.orderBillDao = orderBillDao;
	}
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	
	
}
