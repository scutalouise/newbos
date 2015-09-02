package com.bap.bos.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.domain.Promotion;
import com.bap.bos.domain.PromotionInfo;
import com.bap.bos.domain.ReferredCubage;
import com.bap.bos.domain.SaveToBank;
import com.bap.bos.domain.Staff;
import com.bap.bos.domain.Station;
import com.bap.bos.domain.TransItem;
import com.bap.bos.net.Impl.BosClientThread;
import com.bap.bos.service.BankReceiptService;
import com.bap.bos.service.DayTransVerifyService;
import com.bap.bos.service.PromotionService;
import com.bap.bos.service.ShiftVerifyService;
import com.bap.bos.service.TranItemService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;
import com.bap.bos.util.ShiftVerify;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *营业资料管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("businessMsgManageAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BusinessMsgManageAction extends BaseAction implements BeanFactoryAware{
	
	Logger logger = LoggerFactory.getLogger(BusinessMsgManageAction.class);
	
	/* 注入 */
	@Resource
	private BankReceiptService bankReceiptService;
	@Resource
	private DayTransVerifyService dayTransVerifyService;
	@Resource
	private ShiftVerifyService shiftVerifyService;
	@Resource
	private TranItemService tranItemService;
	@Resource
	private PromotionService promotionService;
	@Resource
	private BosClientThread bosClientThread;
	
	
//	private String VERIFIED = "1"; //验证通过
	private String UNVERIFIED = "0"; //验证未通过
	
	
	
	/* 分页 */
	private Page page = new Page();
	private String to_PageNum = "";
	private String pageMsg;
	/* 属性值 */
	private String StaffNo;
	private String StationNo;
	private String SaveTime;
	private String Bank;
	private String Receipt;
	private String ActSaved;
	private String Remark;
	private String startDate;
	private String endDate;
	
	private String DayTransV_Time;
	private String StaffName;
	//班结
	private String ShiftDate;
	private String ShiftNo;
	
	private String ProductNum;
	
	private String NozzleNo;
	private float ProductSellPrice;
	private float PayVol;
	private float PayMoney;
	private String Shift_StartTime;
	private String Shift_EndTime;
	private String Shift_CreateTime;
	private String s;
	//促销品
	private String PromotionInfo_Name;
	private String PromotionInfo_Count;
	private String PromotionInfo_Remark;
	
	private String Promotion_TimeStart;
	private String Promotion_TimeEnd;
	private String Promotion_MoneyLow;
	private String Promotion_MoneyHigh;
	private String PromotionInfo;
	/* 返回值 */
	private String Msg;
	private String error;
	private List<Object> backReceipt;
	private List<Object> dayTransVerify;
	private Integer UnSignCount;
	private List<Object> shiftDetail;
	private List<ShiftVerify> shiftVerify;
	private String shiftVerifyJson;
	private String SellPrice;
	private String promotionInfoS;
	private List<Object> promotion;
	
	private BeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	/**
	 * 保存银行存款
	 * @return
	 * @throws Exception
	 */
	public String saveBankReceipt() throws Exception {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SaveToBank sb = new SaveToBank();
		sb.setSaveToBank_StationNo(this.getStationNo().trim());
		sb.setSaveToBank_Receipt(this.getReceipt().trim());
		sb.setSaveToBank_SaveTime(formatDate.parse(this.getSaveTime().trim()));
		sb.setSaveToBank_Bank(this.getBank().trim());
		sb.setSaveToBank_ActSaved(new BigDecimal(this.getActSaved().trim()));
		sb.setSaveToBank_Remark(this.getRemark().trim());
		sb.setSaveToBank_StaffNo(this.getStaffNo().trim());
	//	System.out.print("this.getStaffNo():" + this.getStaffNo());
		sb.setSaveToBank_OperateTime(new Date());
		sb.setSaveToBank_IsSync("2");
		sb.setSaveToBank_SyncDate(new Date());

		sb.setSaveToBank_LastAmount(new BigDecimal(0));
		sb.setSaveToBank_CanSaved(new BigDecimal(0));
		sb.setSaveToBank_TodayAmount(new BigDecimal(0));
		try {
			bankReceiptService.saveBankReceipt(sb);
			this.setMsg("银行存款信息添加成功。");
			return "success";
		} catch (Exception e) {
			logger.error("银行存款作业添加失败",e);
			this.setError("银行存款作业添加失败。");
			return "input";
		}
	}

	/**
	 * 查询银行存款
	 * @return
	 */
	public String showBankReceip() throws Exception{
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");	
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		List<Object> totalData=null;//获得总记录详情
		if(this.getStartDate()==null||this.getEndDate()==null){
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
			totalData=bankReceiptService.selSaveToBank(startDate,endDate);
			totalCounts=totalData.size();
		}else{
			totalData=bankReceiptService.selSaveToBank(this.getStartDate(),this.getEndDate());
			totalCounts=totalData.size();
		}
		
		/*分页查询显示*/
		if(this.getStartDate()==null||this.getEndDate()==null){
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
			backReceipt=bankReceiptService.selSaveToBank(this.getStartDate(), this.getEndDate(), page);
			/*未签核数据*/
			UnSignCount=0;
			for(int i=0;i<totalData.size();i++){
				Object[] o=(Object[])totalData.get(i);
				SaveToBank a = (SaveToBank)o[0];
			//	System.out.print(a.getSaveToBank_IsSync()+"   ");
				if("2".equals(a.getSaveToBank_IsSync())){
					UnSignCount++;
				}
			}
			return "success";
		}else{
			/*选择开始结束日期*/
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
			backReceipt=bankReceiptService.selSaveToBank(this.getStartDate(), this.getEndDate(), page);
			UnSignCount=0;
			for(int i=0;i<totalData.size();i++){
				Object[] o=(Object[])totalData.get(i);
				SaveToBank a = (SaveToBank)o[0];
			//	System.out.print(a.getSaveToBank_IsSync()+"   ");
				if("2".equals(a.getSaveToBank_IsSync())){
					UnSignCount++;
				}
			}
			return "success";
		}
	
	}
	
	/**
	 * 修改银行存款
	 * @return
	 */
	public String updateBankReceip(){
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			SaveToBank sb = new SaveToBank();
			sb.setSaveToBank_StationNo(this.getStationNo().trim());
			sb.setSaveToBank_Receipt(this.getReceipt().trim());
			sb.setSaveToBank_SaveTime(formatDate.parse(this.getSaveTime().trim()));
			sb.setSaveToBank_Bank(this.getBank().trim());
			sb.setSaveToBank_ActSaved(new BigDecimal(this.getActSaved().trim()));
			sb.setSaveToBank_Remark(this.getRemark().trim());
			sb.setSaveToBank_StaffNo(this.getStaffNo().trim());
			sb.setSaveToBank_IsSync("2");
			sb.setSaveToBank_SyncDate(new Date());

			sb.setSaveToBank_LastAmount(new BigDecimal(0));
			sb.setSaveToBank_CanSaved(new BigDecimal(0));
			sb.setSaveToBank_TodayAmount(new BigDecimal(0));
			String jieguo=bankReceiptService.updateBankReceipt(sb);
			if("success".equals(jieguo)){
				this.setMsg("修改银行存款记录成功。");
			}else if("UnUpdate".equals(jieguo)){
				this.setError("当前日期银行存款记录已经同步到总部服务器，不能进行修改。");
			}else{
				this.setError("由于网络等其他原因，银行存款记录修改失败。");
			}	
			return "success";
		} catch (Exception e) {
			logger.error("修改银行存款记录失败",e);
			this.setError("修改银行存款记录失败。");
			return "input";
		}
	}
	
	/**
	 * 银行存款删除
	 * @return
	 */
	public String delBankReceip(){
		try {
			SaveToBank sb = new SaveToBank();
			/*设置添加信息*/
			sb.setSaveToBank_StationNo(this.getStationNo().trim());
			sb.setSaveToBank_Receipt(this.getReceipt().trim());
			String jieguo=bankReceiptService.delBankReceipt(sb);
			if("success".equals(jieguo)){
				this.setMsg("删除银行存款记录成功。");
			}else if("UnDel".equals(jieguo)){
				this.setError("当前日期银行存款记录已经同步到总部服务器，不能进行删除。");
			}else{
				this.setError("由于网络等其他原因，银行存款记录不能被删除。");
			}	
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setError("删除银行存款记录失败。");
			return "input";
		}
	}
	/**
	 * 银行存款签核
	 * @return
	 */
	public String signBankReceip(){
		if(bankReceiptService.signBankReceipt(this.getStationNo().trim(), this.getReceipt().trim())){
			this.setMsg("签核成功。");
			return "success";
		}else{
			this.setError("由于网络等其他原因，签核失败。");
			return "input";
		}	
		
	}
	
	/**
	 * 查询日结记录情况
	 * @return
	 * @throws ParseException 
	 */
	public String selDayTransVerify() throws ParseException {
		SimpleDateFormat formatDate1 = new SimpleDateFormat("yyyy-MM-dd");	
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null||this.getEndDate()==null){
			/*第一次初始化*/
			String currDate=formatDate1.format(new Date());
			String curr[]=currDate.split("-");
			startDate=curr[0]+"-"+curr[1]+"-"+"01";
			if("01"==curr[2]){
				//每月第一天录入时
				 endDate=curr[0]+"-"+curr[1]+"-"+"01";
			}else{
				 endDate=curr[0]+"-"+curr[1]+"-"+(Integer.valueOf(curr[2]));
			}
			totalCounts=dayTransVerifyService.selDayTransVerify(curr[0]+curr[1]+"01",curr[0]+curr[1]+(Integer.valueOf(curr[2]))).size();
		}else{
			startDate=this.getStartDate();
			String start[]=startDate.split("-");
			endDate=this.getEndDate();
			String end[]=endDate.split("-");
			totalCounts=dayTransVerifyService.selDayTransVerify(start[0]+start[1]+start[2],end[0]+end[1]+end[2]).size();
		}
		
		/*分页查询显示*/
		if(this.getStartDate()==null||this.getEndDate()==null){
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
			startDate=this.getStartDate();
			String start[]=startDate.split("-");
			endDate=this.getEndDate();
			String end[]=endDate.split("-");
			dayTransVerify=dayTransVerifyService.selDayTransVerify(start[0]+start[1]+start[2],end[0]+end[1]+end[2], page);
			return "success";
		}else{
			/*选择开始结束日期*/
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
			startDate=this.getStartDate();
			String start[]=startDate.split("-");
			endDate=this.getEndDate();
			String end[]=endDate.split("-");
			dayTransVerify=dayTransVerifyService.selDayTransVerify(start[0]+start[1]+start[2],end[0]+end[1]+end[2], page);
			return "success";
		}
	
	}
	/**
	 * 日结操作
	 * @return
	 */
	public String dayTransCardAccountCheck(){
		String DayTransVTime=this.getDayTransV_Time().trim();	
		try {
			/*启动日结线程*/
			bosClientThread.setDate(DayTransVTime);
			if(StationNo == null){
				Station station = (Station)ServletActionContext.getRequest().getSession().getAttribute("Station");
				if(station == null){logger.warn("获取station失败，session过期！？");return "登录过期，请重新登录！";}
				StationNo = station.getStation_No();
			}
			bosClientThread.setStationNo(StationNo);
				
			FutureTask<String> threadResult=new FutureTask<String>(bosClientThread);
			Executors.newSingleThreadExecutor().execute(threadResult);
			String Threadresult=threadResult.get();
			System.out.print("Threadresult:"+Threadresult);
			int ThreadresultInt;
			if("success".equals(Threadresult)){
				ThreadresultInt=0;
			}else if("D2_PacketError".equals(Threadresult)){
				ThreadresultInt=1;
			}else if("D2_false".equals(Threadresult)){
				ThreadresultInt=2;
			}else if("ServerError".equals(Threadresult)){
				ThreadresultInt=3;
			}else if("D3_UploadTimeOut".equals(Threadresult)){
				ThreadresultInt=4;
			}else if("D2_2_PacketError".equals(Threadresult)){
				ThreadresultInt=5;
			}else if("D2_2_false".equals(Threadresult)){
				ThreadresultInt=6;
			}else if("D5_false".equals(Threadresult)){
				ThreadresultInt=7;
			}else if("D6_false".equals(Threadresult)){
				ThreadresultInt=8;
			}else if("D8_false".equals(Threadresult)){
				ThreadresultInt=9;
			}else if("NoFindServer".equals(Threadresult)){
				ThreadresultInt=10;
			}else if("TimeOut".equals(Threadresult)){
				ThreadresultInt=11;
			}else if("NetError".equals(Threadresult)){
				ThreadresultInt=12;
			}else{
				ThreadresultInt=100;
			}
			
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			String[] dateArr = shiftVerifyService.getShiftDateSpanAsString(StationNo, format.format(format.parse(DayTransVTime)));
			if(ArrayUtils.isEmpty(dateArr) || (dateArr[0] == null && dateArr[1] == null)){
				this.setError("日结失败，该日日结范围不明确！");
				return "false";
			}
			
			
			/*线程返回值判断*/
			DayTransVerify dayTransVerify=new DayTransVerify();
			String result=null;
			switch (ThreadresultInt) {
			case 0:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("1");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerify.setDayTransV_IsSync("1");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				this.setError("日结成功");
				result="success";
				break;
			case 1:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				this.setError("日结失败（第1次请求同步未得到正确的返回结果）");
				result= "false";
				break;
			case 2:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				this.setError("日结失败（第1次请求同步失败）");
				result= "false";
				break;
			case 3:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				this.setError("日结失败（上传未接交易，总部处理失败）");
				result= "false";
				break;
			case 4:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				this.setError("日结失败（上传未接交易，超时）");
				result= "false";
				break;
			case 5:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				this.setError("日结失败（第2次请求同步未得到正确的返回结果）");
				result= "false";
				break;
			case 6:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				this.setError("日结失败（第2次请求同步失败）");
				result= "false";
				break;
			case 7:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				this.setError("日结失败（申请和总部对账失败-总部未下发对账信息）");
				result= "false";
				break;
			case 8:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				this.setError("日结失败（总部账目和站级账目不一致，请重新日结）");
				result= "false";
				break;
			case 9:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("0");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				this.setError("日结失败（总部设置对账标识失败）");
				result= "false";
				break;
			case 10:
				this.setError("总部服务器关闭，不能进行日结操作");
				result= "false";
				break;
			case 11:
				this.setError("日结失败（连接超时）");
				result= "false";
				break;
			case 12:
				this.setError("日结失败（网络错误，请检查网络是否正常）");
				result= "false";
				break;
			case 100:
				dayTransVerify.setDayTransV_Time(DayTransVTime);
				dayTransVerify.setDayTransV_Status("2");
				dayTransVerify.setDayTransV_VerifyStaffNo(this.getStaffNo());
				dayTransVerify.setDayTransV_IsSync("2");
				dayTransVerify.setDayTransV_SyncDate(new Date());
				dayTransVerifyService.updateDayTransVerify(dayTransVerify,dateArr);
				this.setError("日结失败（ACK接收失败）");
				result= "false";
				break;
			default:
				this.setError("日结失败（错误-1）");
				result= "false";
				break;
			}
			return result;
		}catch (Exception e) {
			logger.error("网络异常，或者总部服务器关闭，不能进行日结操作",e);
			this.setError("网络异常，或者总部服务器关闭，不能进行日结操作");
			return "NetError";
		}
	}
	/**
	 * 查询班结审核状态
	 * @return
	 * @throws ParseException 
	 */
	public String selShiftVerify(){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");	
		
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		List<Object> totalData=null;//获得总记录详情
		if(this.getStartDate()==null||this.getEndDate()==null){
			/*第一次初始化*/
			String currDate=formatDate.format(new Date());
			String curr[]=currDate.split("-");
			startDate=curr[0]+"-"+curr[1]+"-"+"01";
			if("01"==curr[2]){
				//每月第一天录入时
				 endDate=curr[0]+"-"+curr[1]+"-"+"01";
				 totalData=shiftVerifyService.selShiftDetail(curr[0]+curr[1]+"01",curr[0]+curr[1]+"01");
				 totalCounts=totalData.size();
			}else{
				 endDate=curr[0]+"-"+curr[1]+"-"+(Integer.valueOf(curr[2]));
				 totalData=shiftVerifyService.selShiftDetail(curr[0]+curr[1]+"01",curr[0]+curr[1]+Integer.valueOf(curr[2]));
				 totalCounts=totalData.size();
			}
			
		}else{
			startDate=this.getStartDate();
			String start[]=startDate.split("-");
			endDate=this.getEndDate();
			String end[]=endDate.split("-");
			totalData=shiftVerifyService.selShiftDetail(start[0]+start[1]+start[2],end[0]+end[1]+end[2]);
			totalCounts=totalData.size();
		}
		
		/*分页查询显示*/
		if(this.getStartDate()==null||this.getEndDate()==null){
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
			startDate=this.getStartDate();
			String start[]=startDate.split("-");
			endDate=this.getEndDate();
			String end[]=endDate.split("-");
			shiftDetail=shiftVerifyService.selShiftDetail(start[0]+start[1]+start[2],end[0]+end[1]+end[2], page);
			/*未审核核数据*/
			UnSignCount=0;
			for(int i=0;i<totalCounts;i++){
				Object[] o=(Object[])totalData.get(i);
				if(o[5] != null && UNVERIFIED.equals(o[5].toString())){
					UnSignCount++;
				}
			}
			return "success";
		}else{
			/*选择开始结束日期*/
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
			startDate=this.getStartDate();
			String start[]=startDate.split("-");
			endDate=this.getEndDate();
			String end[]=endDate.split("-");
			shiftDetail=shiftVerifyService.selShiftDetail(start[0]+start[1]+start[2],end[0]+end[1]+end[2], page);
			UnSignCount=0;			
			for(int i=0;i<totalCounts;i++){
				Object[] o=(Object[])totalData.get(i);
				if(o[5] != null && UNVERIFIED.equals(o[5].toString())){
					UnSignCount++;
				}
			}
			return "success";
		}
	}
	/**
	 * 获得当班所有枪详细数据及其差额
	 * @return
	 */
	public String selShiftNozzle(){
		shiftVerify=shiftVerifyService.selShiftNozzleDetails(ShiftDate, ShiftNo,Shift_CreateTime,Shift_StartTime,Shift_EndTime,StationNo);
		return "success";
	}
	public String selShiftNozzleJson(){
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			shiftVerifyJson = objectMapper.writeValueAsString(shiftVerifyService.selShiftNozzleDetails(ShiftDate, ShiftNo,
			Shift_CreateTime,Shift_StartTime,Shift_EndTime,StationNo));
		}catch(Exception e){
			logger.error("获得当班所有枪详细数据及其差额异常！",e);
		}
		return "success";
	}
	
	/**
	 * 获得产品价格
	 * @return
	 */
	public String sellPrice() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			SellPrice = objectMapper.writeValueAsString(shiftVerifyService
					.SellPrice(this.getNozzleNo()));
		} catch (Exception e) {
			logger.error("获得产品价格异常！", e);
		}
		return "success";
	}
	/**
	 * 班结审核补录交易信息
	 * @return
	 * @throws ParseException 
	 */
	public String saveTransItem() throws ParseException{
		SimpleDateFormat formatDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TransItem TransItem = new TransItem();
		TransItem.setTransItem_ItemCode(this.getProductNum());
		TransItem.setTransItem_NozzleNo(this.getNozzleNo());
		TransItem.setTransItem_PayMoney(this.getPayMoney());
		TransItem.setTransItem_PayVol(this.getPayVol());
		TransItem.setTransItem_SellPrice(this.getProductSellPrice());
		TransItem.setTransItem_ShiftDate(this.getShiftDate());
		TransItem.setTransItem_ShiftNo(this.getShiftNo());
		TransItem.setTransItem_StationNo(this.getStationNo());
		TransItem.setTransItem_TransDate(Shift_EndTime);
		/*生成补录编号*/
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String transItemTransNo=this.getStationNo()+this.getNozzleNo()+
							   formatDate.format(formatDate1.parse(Shift_EndTime)).substring(2, 14)+"0000";
		TransItem.setTransItem_TransNo(transItemTransNo);
		logger.debug("生成补录编号："+transItemTransNo);
		try {
			tranItemService.saveTransItem(TransItem);

			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("Msg",this.getNozzleNo() + "号枪，补录交易成功! 补录金额为"
							+ this.getPayMoney());
			Msg = objectMapper.writeValueAsString(hashMap);
			logger.debug(Msg);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getNozzleNo() + "号枪，补录交易异常！", e);
			Msg = this.getNozzleNo() + "号枪，补录交易失败!";
			return "input";
		}
	}
	/**
	 * 班结审核确认
	 * @return
	 */
	public String shiftVerify(){
		shiftVerifyService.shiftVerify(StationNo, ShiftDate, ShiftNo, StaffNo,Shift_CreateTime);
		Msg="日期："+ShiftDate+" 班别："+ShiftNo+" 班结审核通过！";
		return "success";
	}
	
	/**
	 * 查询促销信息
	 * @return
	 */
	public String selPromotion(){
		/*设定页数大小*/
		if(null==this.getProductNum()){
			/*设定页数*/
			Integer pages=0;
			if((promotionService.selPromotion("-1").size()%page.getPageSize())==0){
				pages=Math.round(promotionService.selPromotion("-1").size()/page.getPageSize());
			}else{
				pages=Math.round(promotionService.selPromotion("-1").size()/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*查询数据*/
			promotion=promotionService.selPromotion("-1",page);
		}else{
			Integer pages=0;
			if((promotionService.selPromotion(this.getProductNum()).size()%page.getPageSize())==0){
				pages=Math.round(promotionService.selPromotion(this.getProductNum()).size()/page.getPageSize());
			}else{
				pages=Math.round(promotionService.selPromotion(this.getProductNum()).size()/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*查询数据*/
			promotion=promotionService.selPromotion(this.getProductNum(),page);

		}
		return "success";
	}
	/**
	 * 查询促销品信息
	 * @return
	 */
	public String selPromotionInfo(){
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try{
			promotionInfoS = objectMapper.writeValueAsString(promotionService.selPromotionInfo());
		}catch(Exception e){
			logger.error("查询促销品信息异常！",e);
		}
		return "success";
	}
	
	/**
	 * 
	 * 
	 * @author edgar_chan
	 * @since 2015年4月13日
	 */
	public void addOrDelPromotionInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		Staff loginStaff = (Staff)request.getSession().getAttribute("LoginStaff");
		
		if(loginStaff == null){
			try{
				
				PrintWriter writer = response.getWriter();
				writer.write("登录失效，请重新登录后再次操作！");
			}catch(Exception e){
				logger.error("回执信息失败！",e);
			}
			return ;
		}

		String delId = request.getParameter("exeId");
		String name = request.getParameter("n_pname");
		String count = request.getParameter("n_pcount");
		String remark = request.getParameter("n_premark");
		
		if(StringUtils.isNotBlank(delId)){
			promotionService.deletePromotionInfo(Integer.parseInt(delId));	
			try{
				PrintWriter writer = response.getWriter();
				writer.write("删除成功！");
			}catch(Exception e){
				logger.error("删除促销品信息操作回执信息失败！",e);
			}
		}else{
			if(!NumberUtils.isNumber(count)){
				try{
					PrintWriter writer = response.getWriter();
					writer.write("输入数据格式有误，请检查！");
				}catch(Exception e){
					logger.error("保存促销品信息操作回执失败！",e);
				}
				return ;
			}
			
			PromotionInfo promotionInfo=new PromotionInfo();
			
			promotionInfo.setPromotionInfo_Name(name);
			promotionInfo.setPromotionInfo_Count(Integer.parseInt(count));
			promotionInfo.setPromotionInfo_Remark(remark);
			
			try{
				String flag = promotionService.savePromotionInfo(promotionInfo);	
				PrintWriter writer = response.getWriter();
				if("repeatName".equals(flag)){
					writer.write("系统已存在同名促销品！");
					return ;
				}
				writer.write("添加成功！");
			}catch(Exception e){
				try{
					PrintWriter writer = response.getWriter();
					writer.write("添加促销品信息异常！");
					logger.error("添加促销品信息失败！",e);
				}catch(Exception e1){
					logger.error("添加促销品信息操作回执失败！",e);
				}
			}
		}
	}
	
	/**
	 * 保存促销品信息
	 * @return
	 */
	@Deprecated
	public String savePromotionInfo(){
		PromotionInfo PromotionInfo=new PromotionInfo();
		PromotionInfo.setPromotionInfo_Name(this.getPromotionInfo_Name().trim());
		PromotionInfo.setPromotionInfo_Count(Integer.valueOf(this.getPromotionInfo_Count()));
		PromotionInfo.setPromotionInfo_Remark(this.getPromotionInfo_Remark().trim());
		try {
			promotionService.savePromotionInfo(PromotionInfo);	
			
			this.setMsg("添加促销品信息成功。");
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setError("添加促销品信息失败。");
			return "input";
		}
	}
	/**
	 * 保存促销信息
	 * @return
	 */
	public String savePromotion(){
		Promotion Promotion=new Promotion();
		Promotion.setPromotion_ProductNum(this.getProductNum());
		Promotion.setPromotion_TimeStart(this.getPromotion_TimeStart());
		Promotion.setPromotion_TimeEnd(this.getPromotion_TimeEnd());
		Promotion.setPromotion_MoneyLow(Double.valueOf(this.getPromotion_MoneyLow()));
		Promotion.setPromotion_MoneyHigh(Double.valueOf(this.getPromotion_MoneyHigh()));
		Promotion.setPromotion_InfoNum(Integer.valueOf(this.getPromotionInfo()));
		try {
			String result=promotionService.savePromotion(Promotion);
			if("success".equals(result)){
				this.setMsg("添加促销品信息成功。");
				return "success";
			}else{
				this.setError("区间重复,添加促销品信息失败。");
				return "input";
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setError("添加促销品信息失败。");
			return "input";
		}
	}
	
	public String delPromotion(){
		Promotion Promotion=new Promotion();
		Promotion.setPromotion_ProductNum(this.getProductNum());
		Promotion.setPromotion_TimeStart(this.getPromotion_TimeStart());
		Promotion.setPromotion_TimeEnd(this.getPromotion_TimeEnd());
		Promotion.setPromotion_MoneyLow(Double.valueOf(this.getPromotion_MoneyLow()));
		Promotion.setPromotion_MoneyHigh(Double.valueOf(this.getPromotion_MoneyHigh()));
	//	Promotion.setPromotion_InfoNum(Integer.valueOf(this.getPromotionInfo()));
		try {
			promotionService.deletePromotion(Promotion);
			this.setMsg("删除促销信息成功。");
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setError("删除促销信息失败。");
			return "input";
		}
		
		
	}
	
	/**
	 * 
	 * 转向 促销品管理页面
	 * @author edgar_chan
	 * @since 2015年4月13日
	 */
	public String toPromoteSalesManage(){
		return "success";
	}
	
	
	
	/**
	 * 查询 促销品信息
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月3日
	 */
	
	public String getPromoteSalesList(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String name = request.getParameter("pname");
		String getTotalNum = request.getParameter("getTotalNum");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		Page page = new Page();
		page.setPageNum(pageNo);
		page.setPageSize(pageSize);
		
		if("true".equals(getTotalNum)){
			Long totalNum = promotionService.getTotalPromotionInfoRowNum(name);
			ServletActionContext.getRequest().setAttribute("totalNum", totalNum);
			int totalPages = totalNum.intValue()/15;
			totalPages += totalNum%15 > 0 ? 1 : 0;
			
			ServletActionContext.getRequest().setAttribute("totalPages", totalPages);
			ServletActionContext.getRequest().setAttribute("totalNum", totalNum);
			
			if(totalNum == 0){ //没有数据直接返回
				ServletActionContext.getRequest().setAttribute("promoteSalesList", new ArrayList<ReferredCubage>());
				return "success";
			}
			
		}
		
		List<PromotionInfo> promotionInfoList = promotionService.getPromotionInfos(name, page);
		
		ServletActionContext.getRequest().setAttribute("promotionInfoList", promotionInfoList);
		
		return "success";
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

	public String getSaveTime() {
		return SaveTime;
	}

	public void setSaveTime(String saveTime) {
		SaveTime = saveTime;
	}

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
	}

	public String getActSaved() {
		return ActSaved;
	}

	public void setActSaved(String actSaved) {
		ActSaved = actSaved;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
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

	public String getReceipt() {
		return Receipt;
	}

	public void setReceipt(String receipt) {
		Receipt = receipt;
	}

	public String getStaffNo() {
		return StaffNo;
	}

	public void setStaffNo(String staffNo) {
		StaffNo = staffNo;
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

	public List<Object> getBackReceipt() {
		return backReceipt;
	}

	public void setBackReceipt(List<Object> backReceipt) {
		this.backReceipt = backReceipt;
	}

	public List<Object> getDayTransVerify() {
		return dayTransVerify;
	}

	public void setDayTransVerify(List<Object> dayTransVerify) {
		this.dayTransVerify = dayTransVerify;
	}

	public String getDayTransV_Time() {
		return DayTransV_Time;
	}

	public void setDayTransV_Time(String dayTransVTime) {
		DayTransV_Time = dayTransVTime;
	}

	public String getStaffName() {
		return StaffName;
	}

	public void setStaffName(String staffName) {
		StaffName = staffName;
	}

	public Integer getUnSignCount() {
		return UnSignCount;
	}

	public void setUnSignCount(Integer unSignCount) {
		UnSignCount = unSignCount;
	}

	public List<Object> getShiftDetail() {
		return shiftDetail;
	}

	public void setShiftDetail(List<Object> shiftDetail) {
		this.shiftDetail = shiftDetail;
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

	public List<ShiftVerify> getShiftVerify() {
		return shiftVerify;
	}

	public void setShiftVerify(List<ShiftVerify> shiftVerify) {
		this.shiftVerify = shiftVerify;
	}

	public String getProductNum() {
		return ProductNum;
	}

	public void setProductNum(String productNum) {
		ProductNum = productNum;
	}

	public String getSellPrice() {
		return SellPrice;
	}

	public void setSellPrice(String sellPrice) {
		SellPrice = sellPrice;
	}

	public String getShiftVerifyJson() {
		return shiftVerifyJson;
	}

	public void setShiftVerifyJson(String shiftVerifyJson) {
		this.shiftVerifyJson = shiftVerifyJson;
	}

	public String getNozzleNo() {
		return NozzleNo;
	}

	public void setNozzleNo(String nozzleNo) {
		NozzleNo = nozzleNo;
	}

	public float getProductSellPrice() {
		return ProductSellPrice;
	}

	public void setProductSellPrice(float productSellPrice) {
		ProductSellPrice = productSellPrice;
	}

	public float getPayVol() {
		return PayVol;
	}

	public void setPayVol(float payVol) {
		PayVol = payVol;
	}

	public float getPayMoney() {
		return PayMoney;
	}

	public void setPayMoney(float payMoney) {
		PayMoney = payMoney;
	}

	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	public String getPromotionInfo_Name() {
		return PromotionInfo_Name;
	}

	public void setPromotionInfo_Name(String promotionInfoName) {
		PromotionInfo_Name = promotionInfoName;
	}

	public String getPromotionInfo_Count() {
		return PromotionInfo_Count;
	}

	public void setPromotionInfo_Count(String promotionInfoCount) {
		PromotionInfo_Count = promotionInfoCount;
	}

	public String getPromotionInfo_Remark() {
		return PromotionInfo_Remark;
	}

	public void setPromotionInfo_Remark(String promotionInfoRemark) {
		PromotionInfo_Remark = promotionInfoRemark;
	}

	public String getPromotion_TimeStart() {
		return Promotion_TimeStart;
	}

	public void setPromotion_TimeStart(String promotionTimeStart) {
		Promotion_TimeStart = promotionTimeStart;
	}

	public String getPromotion_TimeEnd() {
		return Promotion_TimeEnd;
	}

	public void setPromotion_TimeEnd(String promotionTimeEnd) {
		Promotion_TimeEnd = promotionTimeEnd;
	}

	public String getPromotion_MoneyLow() {
		return Promotion_MoneyLow;
	}

	public void setPromotion_MoneyLow(String promotionMoneyLow) {
		Promotion_MoneyLow = promotionMoneyLow;
	}

	public String getPromotion_MoneyHigh() {
		return Promotion_MoneyHigh;
	}

	public void setPromotion_MoneyHigh(String promotionMoneyHigh) {
		Promotion_MoneyHigh = promotionMoneyHigh;
	}

	public String getPromotionInfo() {
		return PromotionInfo;
	}

	public void setPromotionInfo(String promotionInfo) {
		PromotionInfo = promotionInfo;
	}

	public String getPromotionInfoS() {
		return promotionInfoS;
	}

	public void setPromotionInfoS(String promotionInfoS) {
		this.promotionInfoS = promotionInfoS;
	}

	public List<Object> getPromotion() {
		return promotion;
	}

	public void setPromotion(List<Object> promotion) {
		this.promotion = promotion;
	}

	public String getShift_StartTime() {
		return Shift_StartTime;
	}

	public void setShift_StartTime(String shiftStartTime) {
		Shift_StartTime = shiftStartTime;
	}

	public String getShift_EndTime() {
		return Shift_EndTime;
	}

	public void setShift_EndTime(String shiftEndTime) {
		Shift_EndTime = shiftEndTime;
	}

	public String getShift_CreateTime() {
		return Shift_CreateTime;
	}

	public void setShift_CreateTime(String shiftCreateTime) {
		Shift_CreateTime = shiftCreateTime;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	

	

}
