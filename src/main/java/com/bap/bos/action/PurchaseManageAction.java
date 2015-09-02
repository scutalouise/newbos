package com.bap.bos.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import com.bap.bos.action.enums.ActionOfRequirement;
import com.bap.bos.action.enums.ResultOfRequireTankDataPacket;
import com.bap.bos.action.enums.ResultOfRequireTankdata;
import com.bap.bos.components.RepositoryChangedEvent;
import com.bap.bos.domain.Density;
import com.bap.bos.domain.NozzleSetting;
import com.bap.bos.domain.OilUnloadingData;
import com.bap.bos.domain.OrderBill;
import com.bap.bos.domain.Product;
import com.bap.bos.domain.RestockBill;
import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Station;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.net.Impl.UnloadingOilThread;
import com.bap.bos.service.DensityService;
import com.bap.bos.service.NozzleSettingService;
import com.bap.bos.service.OilUnloadingService;
import com.bap.bos.service.ProductService;
import com.bap.bos.service.PurchaseManageService;
import com.bap.bos.service.RepertoryManageService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 *采购作业管理Action
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("purchaseManage")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class PurchaseManageAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(PurchaseManageAction.class);
	
	/* 注入 */
	@Resource
	private PurchaseManageService purchaseManageService;
	@Resource
	private ProductService productService;
	@Resource
	private RepertoryManageService repertoryManageService;
	@Resource
	private OilUnloadingService oilUnloadingService;
	@Resource
	private DensityService densityService;
	@Resource
	private NozzleSettingService nozzleSettingService;
	@Resource
	private UnloadingOilThread unloadingOilThread;
	@Resource
	private ApplicationContext applicationContext;
	
	
	/* 分页 */
	private Page page = new Page();
	private String to_PageNum = "";
	private String pageMsg;
	/* 属性值 */
	//订单
	private String StationNo;
	private String Num;
	private String NumAliases;
	private String ProductType;
	private String ProductNum;
	private String TankNum;
	private String Unit;
	private String AddVolume;
	private String AddWeight;
	private String ExpectDate;
	
	private String startDate;
	private String endDate;
	private String Status;
	private String QueryType;
	private String QueryContext;
	
	private String CreateDate;
	private Date CreateDate1;
	private String SupplierName;
	private String StaffNo;
	private String ActAddVolume;
	private String CostPrice;
	private String ActPlanDate;
	//入库单
	private String RestockNum;
	private String ShiftDate;
	private String ShiftNo;
	private String ComplementStartDate;
	private String CarID;
	private String BottlesCarNo;
	private String MStationNo;
	private String MIntoTemp;
	private String MOutTemp;
	private String MIntoPrs;
	private String MOutPrs;
	private String Mintodate;
	private String MOutdate;
	private String DriverName;
	private String weighname;
	private String Mheader;
	private String Shipper;
	private String GasArrived;
	private String GasLeft;
	private String CIntoTemp;
	private String COutTemp;
	private String CIntoPrs;
	private String COutPrs;
	private String Seal;
	private String EvilWork;
	private String CHead;
	private String UnloadDate;
	private String weather;
	private String hairplat;
	private String Heavy;
	private String Empty;
	private String Delivery;
	private String receipts;
//	private String lossrate;
	
	private String UnloadingOilStatus;
	private String SupplierNum;
	//密度
	private String Density_ID;
	private String Density_ProdunctNum;
	private String Density_Density;
	private String Density_startDate;
	private String Density_endDate;
	//液位仪读数
	private String OilUnloadingData_PumpSum;
	private String OilUnloadingData_TotalVol;
	private String OilUnloadingData_OilVol;
	private String OilUnloadingData_Temp;
	private String OilUnloadingData_WaterHeight;
	private String OilUnloadingData_OilHeight;
	/* 返回值 */
	private String Msg;
	private String Msg1;
	private String error;
	private String OrderBillNum;
	private List<OrderBill> OrderBill;
	private List<Product> product;
	private String OrderBillDetail;
	private List<Object> DensityList;
	private String Tank_Nozzles;
	private String tank_density;
	
	/**
	 * 创建订单编号
	 * @return
	 */
	public String createOrderBillNum(){
		OrderBillNum=purchaseManageService.createOrderBillNum(StationNo);
		return "success";
	}
	
	/**
	 * 保存订单
	 * @return
	 * @throws Exception
	 */
	public String saveOrderBill() throws Exception {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OrderBill ob = new OrderBill();
		ob.setOrderBill_Num(this.getNum().trim());
		ob.setOrderBill_NumAliases(this.getNumAliases());
		ob.setOrderBill_StationNo(this.getStationNo());
		ob.setOrderBill_TankNum(this.getTankNum());
		ob.setOrderBill_ProductType(this.getProductType());
		ob.setOrderBill_ProductNum(this.getProductNum());
		ob.setOrderBill_CreateDate(new Date());
		ob.setOrderBill_ExpectDate(formatDate.parse(this.getExpectDate()));
	//	ob.setOrderBill_Status("00");
		ob.setOrderBill_Unit(this.getUnit());
		ob.setOrderBill_CreateStaffNo(this.getStaffNo());
		ob.setOrderBill_AddVolumeL(Double.valueOf(this.getAddVolume().trim()));
		ob.setOrderBill_AddWeight(Double.valueOf(this.getAddWeight().trim()));
		ob.setOrderBill_SupplierNum(SupplierNum);
		//针对交通油料加油站流程变更为01-已订货
		ob.setOrderBill_Status("01");
		ob.setOrderBill_ActVolumeL(Double.valueOf(this.getAddVolume().trim()));
		ob.setOrderBill_ActWeight(Double.valueOf(this.getAddWeight().trim()));
		ob.setRecordManual(false);
		try {
			purchaseManageService.saveOrderBill(ob);
			this.setMsg("订单提交成功。");
			return "success";
		} catch (Exception e) {
			logger.error("创建订单异常！",e);
			this.setError("由于网络等原因，订单提交失败。");
			return "input";
		}
	}
	
	/**
	 * 查询采购信息
	 * @return
	 */
	public String selOrderBill(){
		if(QueryType==null||QueryType==""){
			QueryType="0";
		}
		/*查询站级所有产品信息*/
		product=productService.selProductDetail();
		/*获取站级编号*/
		System.out.print("StationNo:"+StationNo);
		if(session.get("Station")!=null){
			Station s=(Station)session.get("Station");
			StationNo=s.getStation_No();
		}
		/*日期格式化-格式*/
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");	
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null&&this.getEndDate()==null){
			/*第一次初始化*/
			String currDate=formatDate.format(new Date());
			String curr[]=currDate.split("-");
			startDate=curr[0]+"-"+curr[1]+"-"+"01";
			if("01"==curr[2]){
				//每月第一天录入时
				 endDate=curr[0]+"-"+curr[1]+"-"+"01";
			}else{
				 endDate=curr[0]+"-"+curr[1]+"-"+(Integer.valueOf(curr[2]));
			}
			totalCounts=purchaseManageService.selOrderBill(StationNo,startDate, endDate,"0","",this.getStatus()).size();
			
		}else{
			try {
				totalCounts=purchaseManageService.selOrderBill(StationNo,this.getStartDate(),this.getEndDate(),this.getQueryType(),this.getQueryContext().trim(),this.getStatus()).size();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		/*分页查询显示*/
		if("0".equals(this.getQueryType())||null==this.getQueryType()||""==this.getQueryType()){
			/*设定页数*/
			Integer pages=0;
			if((totalCounts%page.getPageSize())==0){
				pages=Math.round(totalCounts/page.getPageSize());
			}else{
				pages=Math.round(totalCounts/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*分页查询*/
			OrderBill=purchaseManageService.selOrderBill(StationNo,this.getStartDate(), this.getEndDate(),"0","",this.getStatus(), page);
			return "success";
		}else{
			/*单项选择*/
			/*设定页数*/
			Integer pages=0;
			if((totalCounts%page.getPageSize())==0){
				pages=Math.round(totalCounts/page.getPageSize());
			}else{
				pages=Math.round(totalCounts/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*分页查询*/
			OrderBill=purchaseManageService.selOrderBill(StationNo,this.getStartDate(), this.getEndDate(),this.getQueryType(),this.getQueryContext().trim(),this.getStatus(), page);
			return "success";
		}
	
	}
	/**
	 * 确认订单
	 * @return
	 */
	public String verifyOrderBill(){
		if(purchaseManageService.verifyOrderBill(OrderBillNum,StaffNo)){
			this.setMsg("订单编号为："+OrderBillNum+"的订单已确认，准备订货中……");
			return "success";
		}else{
			this.setError("由于网络等原因，订单确认失败");
			return "input";
		}
	}
	/**
	 * 取消订单
	 * @return
	 */
	public String cancelOrderBill(){
		if(purchaseManageService.cancelOrderBill(OrderBillNum)){
			this.setMsg("订单编号为："+NumAliases+"的订单已取消。");
			return "success";
		}else{
			this.setError("由于网络等原因，订单取消失败");
			return "input";
		}
	}
	/**
	 * 查询订单详情
	 * @return
	 */
	public String selOrderBillDetail(){
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			OrderBillDetail = objectMapper.writeValueAsString(purchaseManageService.selOrderBill(this.getOrderBillNum()));
		}catch(Exception e){
			logger.error("油罐查询条件加载异常！",e);
		}
		
		System.out.print(OrderBillDetail);
		return "success";
	}
	/**
	 * 订货
	 * @return
	 * @throws ParseException 
	 */
	public String orderGoods() throws ParseException{
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("0".equals(ProductType)){
			//油品订货
			OrderBill OrderBill=new OrderBill();
			OrderBill.setOrderBill_Num(Num);
			OrderBill.setOrderBill_ActPlanDate(formatDate.parse(ActPlanDate));
			switch(Integer.valueOf(Unit.trim())){
				case 1:
					OrderBill.setOrderBill_Unit("1");
					OrderBill.setOrderBill_ActVolumeM(Double.valueOf(ActAddVolume));
					break;
				case 2:
					OrderBill.setOrderBill_Unit("2");
					OrderBill.setOrderBill_ActVolumeL(Double.valueOf(ActAddVolume));
					break;
				case 3:
					OrderBill.setOrderBill_Unit("3");
					OrderBill.setOrderBill_ActWeight(Double.valueOf(ActAddVolume));
					break;
				default:
					break;
			}
			OrderBill.setOrderBill_CostPrice(Double.valueOf(CostPrice));
			OrderBill.setOrderBill_ActOrderStaffNo(StaffNo);
			OrderBill.setOrderBill_SupplierNum(SupplierNum);
			if(purchaseManageService.orderGoods(OrderBill)){
				this.setMsg("订货成功！");
				return "success";
			}else{
				this.setError("订货失败，请检查网络情况！");
				return "input";
			}
		
		}else if("1".equals(ProductType)){
			//气品订货
			return "success";
		}else {
			return "input";
		}
	}
	
	/**
	 * 卸油
	 * @return
	 * replaced by method com.bap.bos.action.PurchaseManageAction.fetchTanksData()
	 */
	@Deprecated
	public String UnloadingOil(){
		String OrderBillNum=this.getNum();
		String TankNum=this.getTankNum();
		String Status=this.getUnloadingOilStatus();
		try {
			FutureTask<String> threadResult=null; //new FutureTask<String>(new UnloadingOilThread(OrderBillNum,TankNum,Status));
			new Thread(threadResult).start();
			String threadresult=threadResult.get();
			logger.debug("Threadresult:"+threadresult+" Status:"+Integer.valueOf(Status));
			String jieguo="";
			switch (Integer.valueOf(Status)) {
			/*卸油*/
			case 1:
				if("B1ACK_PacketNull".equals(threadresult)){
					this.setError("读取油罐信息为空，请检查FCC/液位通讯仪运行情况，重新请求卸油。");
					jieguo= "input01";
				}else if("B1ACK_PacketPNoError".equals(threadresult)){
					this.setError("读取油罐信息错误，请检查FCC/液位通讯仪/Pos运行情况，重新请求卸油。");
					jieguo= "input01";
				}else if("B1_OperateFalse".equals(threadresult)){
					this.setError("同时有其他油罐车对该罐进行卸油，卸油申请被拒绝。");
					jieguo= "input01";
				}else if(threadresult.length()>=21&&threadresult.length()<=24){
					TankNum=threadresult.substring(0,2);
					Num=threadresult.substring(2,18);
					String OilStatus=threadresult.substring(18,20);
					String Rusutl=threadresult.substring(20,threadresult.length());
					logger.debug("TankNum:"+TankNum+"Num:"+Num+"OilStatus:"+OilStatus+"Rusutl:"+Rusutl);
					if("119".equals(Rusutl)){
						this.setMsg("液位仪数据读取成功，可以开始卸油作业。");
						//更新订单状态为-卸油中
						purchaseManageService.unloadingOil(Num,"06");
						jieguo= "success01";
					}else if("0".equals(Rusutl)){
						//this.setError("锁枪失败，请检查对应油枪是否在加油，若是，请暂停加油，申请卸油成功后，恢复加油作业。");
						jieguo= "input01_1";
					}else if("01".equals(Rusutl)){
						//this.setError("读取液位仪超时,请检查FCC/液位通讯仪是否连接正常，重新申请卸油。");
						jieguo= "input01_2";
					}else{
						this.setError("未知错误，请联系维护人员。");
						jieguo= "input";
					}
				}else{
					jieguo= "input";
				}
				break;
				
			case 0:
				/*卸油确认*/
				if("B1ACK_PacketNull".equals(threadresult)){
					this.setError("读取油罐信息为空，请检查FCC/液位通讯仪运行情况，重新请确认‘卸油完成’。");
					jieguo= "input00";
				}else if("B1ACK_PacketPNoError".equals(threadresult)){
					this.setError("读取油罐信息错误，请检查FCC/液位通讯仪/Pos运行情况，重新确认‘卸油完成’。");
					jieguo= "input00";
				}else if("B1_OperateFalse".equals(threadresult)){
					this.setError("同时有其他油罐进行卸油，确认‘卸油完成’被拒绝。");
					jieguo= "input00";
				}else if(threadresult.length()>=21&&threadresult.length()<=24){
					TankNum=threadresult.substring(0,2);	
					Num=threadresult.substring(2,18);
					String OilStatus=threadresult.substring(18,20);
					String Rusutl=threadresult.substring(20,threadresult.length());
					System.out.print("TankNum:"+TankNum+"Num:"+Num+"OilStatus:"+OilStatus+"Rusutl:"+Rusutl);
					if("119".equals(Rusutl)){
						this.setMsg("液位仪数据读取成功，卸油完成。");
						//更新订单状态为-已卸油
						purchaseManageService.unloadingOil(Num,"05");
						jieguo= "success00";
					}else if("0".equals(Rusutl)){
						//this.setError("锁枪失败，请检查对应油枪是否在加油，若是，请暂停加油，重新确认‘卸油完成’后，恢复加油作业。");
						jieguo= "input00_1";
					}else if("01".equals(Rusutl)){
						//this.setError("读取液位仪超时,请检查FCC/液位通讯仪是否连接正常，重新确认‘卸油完成’。");
						jieguo= "input00_2";
					}else{
						jieguo= "input";
					}
				}else{
					jieguo= "input";
				}
				break;
			default:
				jieguo= "input";
				break;
			}
			return "input01_1";
		} catch (Exception e) {
			/*网络异常*/
			logger.error("网络异常，或者Pos系统未开启，不能进行卸油操作",e);
			this.setError("网络异常，或者Pos系统未开启，不能进行卸油操作");
			return "success01";
		}
	}
	

	/**
	 * 卸油前后 读取油罐相关数据
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月1日
	 */
	public String fetchTanksData(){
		String orderBillNum=this.getNum();
		String tankNum=this.getTankNum();
		String status=this.getUnloadingOilStatus();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("exeFeedback", "abnormal"); //携带此次处理的结果状态
		String recordManual = request.getParameter("recordManual");
		
		List<Integer> tankIds_int = new ArrayList<Integer>();
		
		if(StringUtils.isBlank(tankNum))
			return "shallowStone";
		
		if(tankNum.indexOf(',')> -1){ //如果为多罐卸油
			String[] tankIdArr = StringUtils.split(tankNum,",");
			Integer[] tankIdArr_int = new Integer[tankIdArr.length];
			
			int i = 0;
			for(String tankidCell : tankIdArr){
				tankIdArr_int[i++] = Integer.valueOf(tankidCell.trim());
			}
			
			tankIds_int =  Arrays.asList(tankIdArr_int);
		}else{
			tankIds_int =  Arrays.asList(Integer.valueOf(tankNum));
		
		}
		
		List<Tanksetting> tankSettingList =	repertoryManageService.selTank(ProductNum, tankIds_int);
		
		List<String> autoFetchTankNumList = new ArrayList<String>();
		List<String> manualRecordTankNumList = new ArrayList<String>();
		
		/*更新数据库状态*/
		if("1".equals(recordManual)){
			purchaseManageService.upadteOrderBillRecordStatus(orderBillNum, true);
		}else{
			purchaseManageService.upadteOrderBillRecordStatus(orderBillNum, false);
		}
		
		for(Tanksetting tankSetting : tankSettingList){
			if(tankSetting.getTanksetting_ManualVal() == 1 || "1".equals(recordManual)){ //1 表示手动录入 recordManual 页面显式指定
				manualRecordTankNumList.add(tankSetting.getTanksetting_TankNum());
			}else{
				autoFetchTankNumList.add(tankSetting.getTanksetting_TankNum());
			}
		}
		
		if(CollectionUtils.isEmpty(autoFetchTankNumList)){ //如果本次订单中的油罐均未配置液位仪 则返回页面进行手动录入
			toSaveOilUnloadingData(StringUtils.join(manualRecordTankNumList,","));
			ServletActionContext.getRequest().setAttribute("exeFeedback", "normal"); //油罐全为手动录入时 则转向页面 设置标志为正常处理
			return "shallowStone";
		}
		
		List<String> totalTankNumList = new ArrayList<String>(); //所有油罐编号list
		
		try {
			//为解决 BOS方卸油操作中途人为退出而导致再次对该订单卸油时 液位仪数据无法写入的情况，
			//在每次远程读取液位仪之前 删除BOS方前一次可能已读取到的液位仪数据
			oilUnloadingService.deleteOilUnloadingData(orderBillNum, status);
			
			unloadingOilThread.setOrderBillNum(orderBillNum);
			unloadingOilThread.setTankNum(StringUtils.join(autoFetchTankNumList,","));
			unloadingOilThread.setStatus(status);
			
			FutureTask<String> threadResult=new FutureTask<String>(unloadingOilThread);
			Executors.newSingleThreadExecutor().execute(threadResult);
			String threadresult=threadResult.get();
			
			logger.debug("threadresult:"+threadresult+" status:"+status);
			
			ActionOfRequirement	actionOfRequirement = ActionOfRequirement.values()[Integer.valueOf(status)];
			
		//	String threadresult = "B1ACK_PacketNull";
			ResultOfRequireTankDataPacket resultOfRequireTankDataPacket = null;
			/*翻译返回标记*/
			try{
				resultOfRequireTankDataPacket = ResultOfRequireTankDataPacket.valueOf(threadresult);
			}catch(Exception e){
			}
			if(null == resultOfRequireTankDataPacket){ /*翻译为空则进一步萃取判断*/
				/*翻译为空 则从包中萃取关键标志*/
				TankNum=threadresult.substring(0,2);
				Num=threadresult.substring(2,18);
				String oilStatus=threadresult.substring(18,20);
				String rusutl=threadresult.substring(20,threadresult.length());
				logger.debug("TankNum:"+TankNum+"Num:"+Num+"OilStatus:"+oilStatus+"Rusutl:"+rusutl);
				
				ResultOfRequireTankdata resultOfRequireTankdata = null;
				/*翻译关键标志*/
				try{
					resultOfRequireTankdata = ResultOfRequireTankdata.valueOf("r_"+rusutl);
				}catch(Exception e){
					
				}
				
				if(null != resultOfRequireTankdata){
					this.setMsg(String.format(resultOfRequireTankdata.getRemark(),actionOfRequirement.getRemark()));
					if(resultOfRequireTankdata.equals(ResultOfRequireTankdata.r_119)){
						if(CollectionUtils.isEmpty(manualRecordTankNumList)){ //如果不存在需要手动录入的油罐 表示此次卸油非常成功
							ServletActionContext.getRequest().setAttribute("exeFeedback", "complete");//油罐全为自动获取时  则转向页面 设置标志为完成
							//不存在手动录入时候 则设置订单状态为相应的状态 否则等待手动录入完成 后一起处理
							if(actionOfRequirement.equals(ActionOfRequirement.BEGIN_UNLOADING_OIL)){
								purchaseManageService.unloadingOil(Num,"06");
							}else{
								purchaseManageService.unloadingOil(Num,"05");
							}
						}else{
							toSaveOilUnloadingData(StringUtils.join(manualRecordTankNumList,","));
							ServletActionContext.getRequest().setAttribute("exeFeedback", "normal");//油罐自动获取部分正常完成 ，存在手动录入时  设置标志为正常处理
							this.setMsg("油罐："+StringUtils.join(autoFetchTankNumList,",")+"液位仪读数成功！");
						}
						return "shallowStone";
					}else{
						this.setMsg("通讯异常，或其他原因，导致“"+actionOfRequirement.getRemark()+"”申请失败。请检查Pos/液位仪/FCC是否开启或是否故障。重新申请“"+actionOfRequirement.getRemark()+"”。");
					}
				}
			}else{
				this.setMsg(String.format(resultOfRequireTankDataPacket.getRemark(),actionOfRequirement.getRemark()));
			}
			//如果自动获取油罐信息出现异常 则手动填写所有油罐信息，因此准备为所有油罐号查询绑定枪号
			
			totalTankNumList.addAll(autoFetchTankNumList);
			totalTankNumList.addAll(manualRecordTankNumList);
			
		} catch (Exception e) {
			/*网络异常*/
			logger.error("网络异常，或者Pos系统未开启，不能进行申请的操作",e);
			this.setMsg("网络异常，或者Pos系统未开启，不能进行申请的操作");
			
		}
		toSaveOilUnloadingData(StringUtils.join(totalTankNumList,",")); 
		return "shallowStone";
	}
	
	/**
	 * 获取油罐对应油枪
	 * @return
	 */
	public void toSaveOilUnloadingData(String tankNum){
		List<NozzleSetting> nozzles=nozzleSettingService.selNozzleTank(tankNum);
		
		Map<String,List<String>> sortouter = new LinkedHashMap<String, List<String>>();
		
		for(NozzleSetting nozzleSetting : nozzles){
			List<String> nozznumList = sortouter.get(nozzleSetting.getNozzleSetting_TankNum());
			if(CollectionUtils.isEmpty(nozznumList)){
				nozznumList = new ArrayList<String>();
				sortouter.put(nozzleSetting.getNozzleSetting_TankNum(),nozznumList);
			}
			nozznumList.add(nozzleSetting.getNozzleSetting_Phy_Noz());
		}
		
		List<String[]> tanksVarData = new ArrayList<String[]>();
		
		for(Map.Entry<String, List<String>> entry : sortouter.entrySet()){
			String[] strs = new String[2];
			strs[0] = entry.getKey(); //油罐号
			strs[1] = StringUtils.join(entry.getValue(),','); //油枪号
			tanksVarData.add(strs);
		}
		
		Set<String> tankNumSet = sortouter.keySet();
		
		String[] tankNums = StringUtils.split(tankNum,",");
		
		for(String tankNumCell : tankNums){
			if(!tankNumSet.contains(tankNumCell)){
				String[] strs = new String[2];
				strs[0] = tankNumCell; 
				strs[1] = "";
				tanksVarData.add(strs);
			}
		}
		
		ServletActionContext.getRequest().setAttribute("tanksVarData",tanksVarData);
		
	}
	
	/**
	 * 保存卸油数据-自动 出现异常后，手动录入
	 * @return
	 */
	public void saveOilUnloadingData(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response =  ServletActionContext.getResponse();
		
		String orderNum = request.getParameter("orderNum");
		
		String[] totalNozzleVals = request.getParameterValues("totalNozzleVal");
		String[] tankNums = request.getParameterValues("tankNum");
		String[] oTemperatures = request.getParameterValues("oTemperature");
		String[] wHeights = request.getParameterValues("wHeight");
		String[] oHeight = request.getParameterValues("oHeight");
		String[] totalCubage = request.getParameterValues("totalCubage");
		String[] oCubage = request.getParameterValues("oCubage");
		
		List<OilUnloadingData> oilUnloadingDataList = new ArrayList<OilUnloadingData>();
		int i = 0;
		for(String totalNozzleVal : totalNozzleVals){
			OilUnloadingData oilUnloadingData = new OilUnloadingData();
			oilUnloadingData.setOilUnloadingData_OrderNum(orderNum);
			oilUnloadingData.setOilUnloadingData_PumpSum(Float.valueOf(totalNozzleVal));
			oilUnloadingData.setOilUnloadingData_State(UnloadingOilStatus);
			oilUnloadingData.setOilUnloadingData_TotalVol(Float.valueOf(totalCubage[i]));
			oilUnloadingData.setOilUnloadingData_OilVol(Float.valueOf(oCubage[i]));
			oilUnloadingData.setOilUnloadingData_Temp(Float.valueOf(oTemperatures[i]));
			oilUnloadingData.setOilUnloadingData_WaterHeight(Float.valueOf(wHeights[i]));
			oilUnloadingData.setOilUnloadingData_OilHeight(Float.valueOf(oHeight[i]));
			oilUnloadingData.setOilUnloadingData_TankId(tankNums[i]);
			oilUnloadingDataList.add(oilUnloadingData);
			i ++;
		}
		
		boolean flag = true;
		
		try {
			//保存卸油数据
			oilUnloadingService.saveOilUnloadingData(oilUnloadingDataList);
			//更新订单状态
			if("00".equals(UnloadingOilStatus)){
				purchaseManageService.unloadingOil(orderNum,"05");
			}else if("01".equals(UnloadingOilStatus)){
				purchaseManageService.unloadingOil(orderNum,"06");
			}
		} catch (Exception e) {
			flag = false;
			logger.error("手动录入保存失败！",e);
		}
		try {
			response.setContentType("text; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			if(flag)
				writer.write("手动录入保存成功！");
			else 
				writer.write("手动录入保存失败！");
		} catch (Exception e) {
			logger.error("手动录入回执失败！", e);
		}
	}
	
	/**
	 * 跳转到入库单页面
	 * @return
	 * @throws Exception
	 */
	public String toAddRestockBillHtml() throws Exception{
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		/*查询站级所有产品信息*/
		product=productService.selProductDetail();
		/*查询当前班别情况*/
		Shift CurrentShift=repertoryManageService.selCurrentShift(this.getStationNo()).get(0);
		/*获得入库单号*/
		String RestockNum=purchaseManageService.createRestockBillNum(this.getStationNo());
		/*赋值*/
		this.setShiftDate(CurrentShift.getShift_ShiftDate());
		this.setShiftNo(CurrentShift.getShift_ShiftNo());
		this.setRestockNum(RestockNum);
		this.setCreateDate1(formatDate.parse(this.getCreateDate()));
		this.setNumAliases(NumAliases);
		System.out.println("NumAliases"+NumAliases);
		return "success";
	}
	/**
	 * 保存入库单
	 * @return
	 * * @throws Exception
	 */
	public String saveRestockBill() throws Exception{
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		RestockBill rb=new RestockBill();
		rb.setRestockBill_OrderNum(this.getNum());
		rb.setRestockBill_RestockNum(this.getRestockNum());
		rb.setRestockBill_CarID(this.getCarID());
		rb.setRestockBill_StartDate(formatDate.parse(this.getComplementStartDate()));
		rb.setRestockBill_Unit(this.getUnit());
		rb.setRestockBill_Delivery(new BigDecimal(this.getDelivery().toString()).setScale(2).doubleValue());
		rb.setRestockBill_DriverName(this.getDriverName());
		rb.setRestockBill_GasArrived(formatDate.parse(this.getGasArrived()));
		rb.setRestockBill_GasLeft(formatDate.parse(this.getGasLeft()));
		rb.setRestockBill_IsSync("1");
		rb.setRestockBill_ProductNum(this.getProductNum());
		rb.setRestockBill_ProductType(this.getProductType());
		
		rb.setRestockBill_Seal(this.getSeal());
		rb.setRestockBill_ShiftDate(this.getShiftDate());
		rb.setRestockBill_ShiftNo(this.getShiftNo());
		rb.setRestockBill_Shipper(this.getShipper());
		rb.setRestockBill_StationNo(this.getStationNo());
		rb.setRestockBill_SyncDate(new Date());
		rb.setRestockBill_UnloadDate(formatDate.parse(this.getUnloadDate()));
		rb.setRestockBill_weather(this.getWeather());
		rb.setRestockBill_weighname(this.getWeighname());
		rb.setRestockBill_CHead(this.getCHead());
		rb.setRestockBill_EvilWork(this.getEvilWork());
		rb.setTankDensity(Double.valueOf(this.getTank_density()));
		
		if("0".equals(this.getProductType())){
//			rb.setRestockBill_hairplat(this.getHairplat());
//			rb.setRestockBill_Heavy(Float.valueOf(this.getHeavy()));
//			rb.setRestockBill_Empty(Float.valueOf(this.getEmpty()));
			//损耗、实收
			List<OilUnloadingData> OilUnloadingDatas=oilUnloadingService.selOilUnloadingData(this.getNum().trim());
			
			float Vol1=0,Vol2=0,startPump=0,endPump=0;
			for(int i=0;i<OilUnloadingDatas.size();i++){
				if("01".equals(OilUnloadingDatas.get(i).getOilUnloadingData_State())){
					//卸油开始
					Vol1 += OilUnloadingDatas.get(i).getOilUnloadingData_OilVol();
					startPump += OilUnloadingDatas.get(i).getOilUnloadingData_PumpSum();
					System.out.println(" Vol1:"+Vol1);
				}else if("00".equals(OilUnloadingDatas.get(i).getOilUnloadingData_State())){
					//卸油完成
					Vol2 += OilUnloadingDatas.get(i).getOilUnloadingData_OilVol();
					endPump += OilUnloadingDatas.get(i).getOilUnloadingData_PumpSum();
					System.out.println(" Vol2:"+Vol2);
				}else{
					System.out.println("cuowu");
				}
			}
			//小数*1000,整数化
			//卸油时间内的交易量（油罐磅码数差）
			float TransVol=endPump*1000-startPump*1000;
			//液位仪读数差
			float addVol=Vol2*1000-Vol1*1000;
			//损耗率
			BigDecimal lossrate=  new BigDecimal(this.getDelivery()).multiply(new BigDecimal(1000)).subtract(new BigDecimal(addVol)).subtract(new BigDecimal(TransVol))
					.divide(new BigDecimal(this.getDelivery()).multiply(new BigDecimal(1000)).setScale(2,RoundingMode.HALF_DOWN),2,RoundingMode.HALF_DOWN);
					
			System.out.println("OilUnloadingDatas.size()"+OilUnloadingDatas.size()+" addVol:"+addVol+" lossrate:"+lossrate);
			//*100%后写入数据库
			rb.setRestockBill_lossrate(lossrate.multiply(new BigDecimal(100)).doubleValue());
			//实收数
			rb.setRestockBill_receipts((new BigDecimal(addVol).add(new BigDecimal(TransVol))).divide(new BigDecimal(1000),2,RoundingMode.HALF_DOWN).doubleValue());
			
		}
		if("1".equals(this.getProductType())){
			rb.setRestockBill_BottlesCarNo(this.getBottlesCarNo());
			rb.setRestockBill_CIntoPrs(Float.valueOf(this.getCIntoPrs()));
			rb.setRestockBill_CIntoTemp(Float.valueOf(this.getCIntoTemp()));
			rb.setRestockBill_COutPrs(Float.valueOf(this.getCOutPrs()));
			rb.setRestockBill_COutTemp(Float.valueOf(this.getCOutTemp()));
			rb.setRestockBill_Mheader(this.getMheader());
			rb.setRestockBill_Mintodate(formatDate.parse(this.getMintodate()));
			rb.setRestockBill_MIntoPrs(new BigDecimal(this.getMIntoPrs()).setScale(2,RoundingMode.HALF_DOWN).doubleValue());
			rb.setRestockBill_MIntoTemp(new BigDecimal(this.getMIntoTemp()).setScale(2,RoundingMode.HALF_DOWN).doubleValue());
			rb.setRestockBill_MOutdate(formatDate.parse(this.getMOutdate()));
			rb.setRestockBill_MOutPrs(new BigDecimal(this.getMOutPrs()).setScale(2,RoundingMode.HALF_DOWN).doubleValue());
			rb.setRestockBill_MOutTemp(new BigDecimal(this.getMOutTemp()).setScale(2,RoundingMode.HALF_DOWN).doubleValue());
			rb.setRestockBill_MStationNo(this.getMStationNo());
		}
		try {
			String jieguo=purchaseManageService.saveRestockBill(rb,StationNo);
			if("success".equals(jieguo)){
				//更新为已交货
				purchaseManageService.unloadingOil(Num,"03");
				this.setMsg("添加成功。");
				logger.info("触发任务：生成当月区间平均密度……");
				applicationContext.publishEvent(new RepositoryChangedEvent(this.getShiftDate()));
				return "success";
			}else{
				this.setError("添加失败,未知原因。");
				return "input";
			}
		} catch (Exception e) {
			logger.error("保存入库异常！",e);
			return "input";
		}
		
	}
	/**
	 * 运耗统计报表
	 * @return
	 */
	private Map<String,Object> Parameter=new HashMap<String,Object>();
	private Connection  con; 
	@Resource
	private ComboPooledDataSource dataSource;
	private String StationName;
	private String Type;
	public String lossrateReport() throws Exception{
		String a=this.getStartDate();
		String b=this.getEndDate();
//		String[] c=a.split("-");
//		String[] d=b.split("-");
//		String startDate=c[0]+c[1]+c[2];
//		String endDate=d[0]+d[1]+d[2];
		Parameter.put("startDate", a);
		Parameter.put("endDate", b);
		Parameter.put("stationName", StationName);
		con=dataSource.getConnection();	
		return "success";
	}
	/**
	 * 收油记录报表
	 * @return
	 * @throws Exception
	 */
	public String receiveOilReport() throws Exception{
		String a=this.getStartDate();
		String b=this.getEndDate();
		String result="";
		switch(Integer.valueOf(Type)){
			case 1:
				Parameter.put("startDate", a);
				Parameter.put("endDate", b);
				Parameter.put("stationName", StationName);
				con=dataSource.getConnection();	
				result="shouyou";
				break;
			case 2:
				Parameter.put("startDate", a);
				Parameter.put("endDate", b);
				Parameter.put("stationName", StationName);
				con=dataSource.getConnection();	
				result="xieyou";
				break;
		}		
		return result;	
	}
	/**
	 * 保存密度
	 * @return
	 */
	public String saveDensity(){
		Density Density=new Density();
		Density.setDensity_Density(Double.valueOf(this.getDensity_Density()));
		Density.setDensity_ProdunctNum(this.getDensity_ProdunctNum());
		Density.setDensity_StartDate(DateTime.parse(this.getDensity_startDate(),DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate());
		Density.setDensity_endDate(DateTime.parse(this.getDensity_endDate(),DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate());
		Density.setDensity_IsSync("1");
		Density.setDensity_SyncDate(new Date());
		Density.setDensity_StationNo(StationNo);
		try {
			String flag = densityService.saveDensity(Density);
			if("conflicting".equals(flag)){
				this.setError("保存密度起止日期段与系统已有的该油品起止日期段存在冲突！请检查。");
				return "input";
			}
			this.setMsg("保存密度成功。");
			return "success";
		} catch (Exception e) {
			logger.error("保存密度失败！",e);
			this.setError("保存密度失败。");
			return "input";
		}
	}
	/**
	 * 查询密度
	 * @return
	 */
	public String selDensity(){
		/*设定页数大小*/
		if(null==this.getDensity_ProdunctNum()){
			/*设定页数*/
			Integer pages=0;
			if((densityService.selDensity("-1").size()%page.getPageSize())==0){
				pages=Math.round(densityService.selDensity("-1").size()/page.getPageSize());
			}else{
				pages=Math.round(densityService.selDensity("-1").size()/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*查询数据*/
			DensityList=densityService.selDensity("-1",page);
		}else{
			Integer pages=0;
			if((densityService.selDensity(this.getDensity_ProdunctNum()).size()%page.getPageSize())==0){
				pages=Math.round(densityService.selDensity(this.getDensity_ProdunctNum()).size()/page.getPageSize());
			}else{
				pages=Math.round(densityService.selDensity(this.getDensity_ProdunctNum()).size()/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*查询数据*/
			DensityList=densityService.selDensity(this.getDensity_ProdunctNum(),page);

		}
		return "success";
		
	}
	/**
	 * 删除密度
	 * @return
	 */
	public String delDensity(){
		try {
			densityService.deleteDensity(StationNo, Density_startDate, Density_ProdunctNum);
			this.setMsg("删除密度成功。");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.setError("删除密度失败。");
			return "input";
		}
	}
	/**
	 * 更新密度
	 * @return
	 */
	public String updateDensity(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Density density=new Density();
			density.setDensity_Density(Double.valueOf(this.getDensity_Density()));
			density.setDensity_IsSync("1");
			density.setDensity_SyncDate(new Date());
			density.setDensity_ProdunctNum(request.getParameter("ProductNum"));
			density.setDensity_StationNo(StationNo);
			String startDateStr = request.getParameter("startDate");
			density.setDensity_StartDate(DateTime.parse(startDateStr.substring(0,startDateStr.length()-2),DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate());
			
			densityService.updateDensity(density);
			this.setMsg("修改密度成功。");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.setError("修改密度失败。");
			return "input";
		}
	}
	
	public String getComplementStartDate() {
		return ComplementStartDate;
	}

	public void setComplementStartDate(String complementStartDate) {
		ComplementStartDate = complementStartDate;
	}
	public PurchaseManageService getPurchaseManageService() {
		return purchaseManageService;
	}

	public void setPurchaseManageService(PurchaseManageService purchaseManageService) {
		this.purchaseManageService = purchaseManageService;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getTo_PageNum() {
		return to_PageNum;
	}

	public void setTo_PageNum(String toPageNum) {
		to_PageNum = toPageNum;
	}

	public String getPageMsg() {
		return pageMsg;
	}

	public void setPageMsg(String pageMsg) {
		this.pageMsg = pageMsg;
	}

	public String getStationNo() {
		return StationNo;
	}

	public void setStationNo(String stationNo) {
		StationNo = stationNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getOrderBillNum() {
		return OrderBillNum;
	}

	public void setOrderBillNum(String orderBillNum) {
		OrderBillNum = orderBillNum;
	}

	public String getNum() {
		return Num;
	}

	public void setNum(String num) {
		Num = num;
	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String productType) {
		ProductType = productType;
	}

	public String getProductNum() {
		return ProductNum;
	}

	public void setProductNum(String productNum) {
		ProductNum = productNum;
	}

	public String getTankNum() {
		return TankNum;
	}

	public void setTankNum(String tankNum) {
		TankNum = tankNum;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getAddVolume() {
		return AddVolume;
	}

	public void setAddVolume(String addVolume) {
		AddVolume = addVolume;
	}

	public String getExpectDate() {
		return ExpectDate;
	}

	public void setExpectDate(String expectDate) {
		ExpectDate = expectDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<OrderBill> getOrderBill() {
		return OrderBill;
	}

	public void setOrderBill(List<OrderBill> orderBill) {
		OrderBill = orderBill;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

	public String getCarID() {
		return CarID;
	}

	public void setCarID(String carID) {
		CarID = carID;
	}

	public String getBottlesCarNo() {
		return BottlesCarNo;
	}

	public void setBottlesCarNo(String bottlesCarNo) {
		BottlesCarNo = bottlesCarNo;
	}

	public String getMStationNo() {
		return MStationNo;
	}

	public void setMStationNo(String mStationNo) {
		MStationNo = mStationNo;
	}

	public String getMIntoTemp() {
		return MIntoTemp;
	}

	public void setMIntoTemp(String mIntoTemp) {
		MIntoTemp = mIntoTemp;
	}

	public String getMOutTemp() {
		return MOutTemp;
	}

	public void setMOutTemp(String mOutTemp) {
		MOutTemp = mOutTemp;
	}

	public String getMIntoPrs() {
		return MIntoPrs;
	}

	public void setMIntoPrs(String mIntoPrs) {
		MIntoPrs = mIntoPrs;
	}

	public String getMOutPrs() {
		return MOutPrs;
	}

	public void setMOutPrs(String mOutPrs) {
		MOutPrs = mOutPrs;
	}

	public String getMintodate() {
		return Mintodate;
	}

	public void setMintodate(String mintodate) {
		Mintodate = mintodate;
	}

	public String getMOutdate() {
		return MOutdate;
	}

	public void setMOutdate(String mOutdate) {
		MOutdate = mOutdate;
	}

	public String getDriverName() {
		return DriverName;
	}

	public void setDriverName(String driverName) {
		DriverName = driverName;
	}

	public String getWeighname() {
		return weighname;
	}

	public void setWeighname(String weighname) {
		this.weighname = weighname;
	}

	public String getMheader() {
		return Mheader;
	}

	public void setMheader(String mheader) {
		Mheader = mheader;
	}

	public String getShipper() {
		return Shipper;
	}

	public void setShipper(String shipper) {
		Shipper = shipper;
	}

	public String getGasArrived() {
		return GasArrived;
	}

	public void setGasArrived(String gasArrived) {
		GasArrived = gasArrived;
	}

	public String getGasLeft() {
		return GasLeft;
	}

	public void setGasLeft(String gasLeft) {
		GasLeft = gasLeft;
	}

	public String getCIntoTemp() {
		return CIntoTemp;
	}

	public void setCIntoTemp(String cIntoTemp) {
		CIntoTemp = cIntoTemp;
	}

	public String getCOutTemp() {
		return COutTemp;
	}

	public void setCOutTemp(String cOutTemp) {
		COutTemp = cOutTemp;
	}

	public String getCIntoPrs() {
		return CIntoPrs;
	}

	public void setCIntoPrs(String cIntoPrs) {
		CIntoPrs = cIntoPrs;
	}

	public String getCOutPrs() {
		return COutPrs;
	}

	public void setCOutPrs(String cOutPrs) {
		COutPrs = cOutPrs;
	}

	public String getSeal() {
		return Seal;
	}

	public void setSeal(String seal) {
		Seal = seal;
	}

	public String getEvilWork() {
		return EvilWork;
	}

	public void setEvilWork(String evilWork) {
		EvilWork = evilWork;
	}

	public String getCHead() {
		return CHead;
	}

	public void setCHead(String cHead) {
		CHead = cHead;
	}

	public String getUnloadDate() {
		return UnloadDate;
	}

	public void setUnloadDate(String unloadDate) {
		UnloadDate = unloadDate;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getHairplat() {
		return hairplat;
	}

	public void setHairplat(String hairplat) {
		this.hairplat = hairplat;
	}

	public String getHeavy() {
		return Heavy;
	}

	public void setHeavy(String heavy) {
		Heavy = heavy;
	}

	public String getEmpty() {
		return Empty;
	}

	public void setEmpty(String empty) {
		Empty = empty;
	}

	public String getDelivery() {
		return Delivery;
	}

	public void setDelivery(String delivery) {
		Delivery = delivery;
	}

	public String getReceipts() {
		return receipts;
	}

	public void setReceipts(String receipts) {
		this.receipts = receipts;
	}

	public String getShiftDate() {
		return ShiftDate;
	}

	public void setShiftDate(String shiftDate) {
		ShiftDate = shiftDate;
	}

	public String getShiftNo() {
		return ShiftNo;
	}

	public void setShiftNo(String shiftNo) {
		ShiftNo = shiftNo;
	}

	public String getRestockNum() {
		return RestockNum;
	}

	public void setRestockNum(String restockNum) {
		RestockNum = restockNum;
	}

	public Date getCreateDate1() {
		return CreateDate1;
	}

	public void setCreateDate1(Date createDate1) {
		CreateDate1 = createDate1;
	}

	public String getStaffNo() {
		return StaffNo;
	}

	public void setStaffNo(String staffNo) {
		StaffNo = staffNo;
	}

	public String getOrderBillDetail() {
		return OrderBillDetail;
	}

	public void setOrderBillDetail(String orderBillDetail) {
		OrderBillDetail = orderBillDetail;
	}

	public String getActAddVolume() {
		return ActAddVolume;
	}

	public void setActAddVolume(String actAddVolume) {
		ActAddVolume = actAddVolume;
	}

	public String getCostPrice() {
		return CostPrice;
	}

	public void setCostPrice(String costPrice) {
		CostPrice = costPrice;
	}

	public String getActPlanDate() {
		return ActPlanDate;
	}

	public void setActPlanDate(String actPlanDate) {
		ActPlanDate = actPlanDate;
	}

	public String getUnloadingOilStatus() {
		return UnloadingOilStatus;
	}

	public void setUnloadingOilStatus(String unloadingOilStatus) {
		UnloadingOilStatus = unloadingOilStatus;
	}

	public String getMsg1() {
		return Msg1;
	}

	public void setMsg1(String msg1) {
		Msg1 = msg1;
	}

	public String getQueryType() {
		return QueryType;
	}

	public void setQueryType(String queryType) {
		QueryType = queryType;
	}

	public String getQueryContext() {
		return QueryContext;
	}

	public void setQueryContext(String queryContext) {
		QueryContext = queryContext;
	}

	public String getSupplierNum() {
		return SupplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		SupplierNum = supplierNum;
	}

	public Map<String, Object> getParameter() {
		return Parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		Parameter = parameter;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public ComboPooledDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(ComboPooledDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getAddWeight() {
		return AddWeight;
	}

	public void setAddWeight(String addWeight) {
		AddWeight = addWeight;
	}

	public String getDensity_ProdunctNum() {
		return Density_ProdunctNum;
	}

	public void setDensity_ProdunctNum(String densityProdunctNum) {
		Density_ProdunctNum = densityProdunctNum;
	}

	public String getDensity_Density() {
		return Density_Density;
	}

	public void setDensity_Density(String densityDensity) {
		Density_Density = densityDensity;
	}

	public String getDensity_startDate() {
		return Density_startDate;
	}

	public void setDensity_startDate(String densityStartDate) {
		Density_startDate = densityStartDate;
	}

	public String getDensity_endDate() {
		return Density_endDate;
	}

	public void setDensity_endDate(String densityEndDate) {
		Density_endDate = densityEndDate;
	}

	public List<Object> getDensityList() {
		return DensityList;
	}

	public void setDensityList(List<Object> densityList) {
		DensityList = densityList;
	}

	public String getDensity_ID() {
		return Density_ID;
	}

	public void setDensity_ID(String densityID) {
		Density_ID = densityID;
	}

	public String getStationName() {
		return StationName;
	}

	public void setStationName(String stationName) {
		StationName = stationName;
	}

	public String getNumAliases() {
		return NumAliases;
	}

	public void setNumAliases(String numAliases) {
		NumAliases = numAliases;
	}

	public String getOilUnloadingData_PumpSum() {
		return OilUnloadingData_PumpSum;
	}

	public void setOilUnloadingData_PumpSum(String oilUnloadingDataPumpSum) {
		OilUnloadingData_PumpSum = oilUnloadingDataPumpSum;
	}

	public String getOilUnloadingData_TotalVol() {
		return OilUnloadingData_TotalVol;
	}

	public void setOilUnloadingData_TotalVol(String oilUnloadingDataTotalVol) {
		OilUnloadingData_TotalVol = oilUnloadingDataTotalVol;
	}

	public String getOilUnloadingData_OilVol() {
		return OilUnloadingData_OilVol;
	}

	public void setOilUnloadingData_OilVol(String oilUnloadingDataOilVol) {
		OilUnloadingData_OilVol = oilUnloadingDataOilVol;
	}

	public String getOilUnloadingData_Temp() {
		return OilUnloadingData_Temp;
	}

	public void setOilUnloadingData_Temp(String oilUnloadingDataTemp) {
		OilUnloadingData_Temp = oilUnloadingDataTemp;
	}

	public String getOilUnloadingData_WaterHeight() {
		return OilUnloadingData_WaterHeight;
	}

	public void setOilUnloadingData_WaterHeight(String oilUnloadingDataWaterHeight) {
		OilUnloadingData_WaterHeight = oilUnloadingDataWaterHeight;
	}

	public String getOilUnloadingData_OilHeight() {
		return OilUnloadingData_OilHeight;
	}

	public void setOilUnloadingData_OilHeight(String oilUnloadingDataOilHeight) {
		OilUnloadingData_OilHeight = oilUnloadingDataOilHeight;
	}

	public String getTank_Nozzles() {
		return Tank_Nozzles;
	}

	public void setTank_Nozzles(String tankNozzles) {
		Tank_Nozzles = tankNozzles;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getTank_density() {
		return tank_density;
	}

	public void setTank_density(String tank_density) {
		this.tank_density = tank_density;
	}

	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal("22222222222.22");
		
		System.out.println();
		
		
	}
	
}
