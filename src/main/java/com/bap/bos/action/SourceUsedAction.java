package com.bap.bos.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.domain.DailySourceed;
import com.bap.bos.domain.SourceUsed;
import com.bap.bos.service.SourceService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;

/**
 * 能耗管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("sourceUsedAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SourceUsedAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(SourceUsedAction.class);
	
	/*分页*/
	private Page page=new Page();
	private String to_PageNum="";
	private String pageMsg;
	/*注入*/
	@Resource SourceService sourceService;
	/*属性值*/
	private String StationNo;
	private String CurrDate;
	private String ElecNum;
	private String WaterNum;
	private String startDate;
	private String endDate;
	/*返回值*/
	private String Msg;
	private String error;
	private List<DailySourceed> sourceed;
	private Integer UnSignCount;
	private List<SourceUsed> SourcePlan;
	
	/**
	 * 保存每日能耗
	 * @return
	 * @throws Exception
	 */
	public String saveDailySourceed()  throws Exception {
		try {
			DailySourceed ds=new DailySourceed();
			/*格式化日期*/
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			ds.setDailySourced_CurrDate(formatDate.parse(this.getCurrDate()));
			/*设置添加信息*/
			ds.setDailySourced_StationNo(this.getStationNo());
			ds.setDailySourced_ElecNum(Float.valueOf(this.getElecNum()));
			ds.setDailySourced_WaterNum(Float.valueOf(this.getWaterNum()));
			ds.setDailySourced_IsSync("2");
			ds.setDailySourced_SyncDate(new Date());
			/*添加*/
			String jieguo=sourceService.saveDailySourceed(ds);
			if("success".equals(jieguo)){
				this.setMsg("添加当日能耗成功。");
			}else if("UnPlan".equals(jieguo)){
				this.setError("本月能耗计划总部未设定或气站未同步，添加失败。");
			}else{
				this.setError("由于网络等其他原因，添加能耗失败。");
			}	
			return "success";
		} catch (Exception e) {
			logger.error("添加能耗失败，当前日期已经添加过能耗。",e);
			this.setError("添加能耗失败，当前日期已经添加过能耗。");
			return "input";
		}
	}
	/**
	 * 查询每日能耗
	 * @return
	 */
	public String showSourceed(){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		List<DailySourceed> totalData=null;//总共的记录详情
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
			totalData=sourceService.selSourceed(startDate,endDate);
			totalCounts=totalData.size();
			
		}else{
			totalData=sourceService.selSourceed(this.getStartDate(),this.getEndDate());
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
			sourceed=sourceService.selSourceed(this.getStartDate(), this.getEndDate(), page);
			/*未签核数据*/
			UnSignCount=0;
			for(int i=0;i<totalData.size();i++){
				if("2".equals(totalData.get(i).getDailySourced_IsSync())){
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
			sourceed=sourceService.selSourceed(this.getStartDate(), this.getEndDate(), page);
			UnSignCount=0;
			for(int i=0;i<totalData.size();i++){
				if("2".equals(totalData.get(i).getDailySourced_IsSync())){
					UnSignCount++;
				}
			}
			return "success";
		}
	}
	/**
	 * 修改每日能耗
	 * @return
	 */
	public String updateDailySourceed(){
		try {
			DailySourceed ds=new DailySourceed();
			/*设置添加信息*/
			ds.setDailySourced_StationNo(this.getStationNo());
			ds.setDailySourced_ElecNum(Float.valueOf(this.getElecNum()));
			ds.setDailySourced_WaterNum(Float.valueOf(this.getWaterNum()));
			String jieguo=sourceService.updateDailySourceed(ds,this.getCurrDate());
			if("success".equals(jieguo)){
				this.setMsg("修改能耗成功。");
			}else if("UnUpdate".equals(jieguo)){
				this.setError("当前日期能耗已经同步到总部服务器，不能进行修改。");
			}else{
				this.setError("由于网络等其他原因，修改能耗失败。");
			}	
			return "success";
		} catch (Exception e) {
			logger.error("编辑能耗失败。",e);
			this.setError("编辑能耗失败。");
			return "input";
		}
	}
	/**
	 * 能耗删除
	 * @return
	 */
	public String delDailySourceed(){
		try {
			DailySourceed ds=new DailySourceed();
			/*格式化日期*/
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			/*设置添加信息*/
			ds.setDailySourced_CurrDate(formatDate.parse(this.getCurrDate()));
			ds.setDailySourced_StationNo(this.getStationNo());
			String jieguo=sourceService.delDailySourceed(ds, formatDate.format(formatDate.parse(this.getCurrDate())));
			if("success".equals(jieguo)){
				this.setMsg("删除能耗成功。");
			}else if("UnDel".equals(jieguo)){
				this.setError("当前日期能耗已经同步到总部服务器，不能进行删除。");
			}else{
				this.setError("由于网络等其他原因，删除能耗失败。");
			}	
			return "success";
		} catch (Exception e) {
			logger.error("删除能耗失败。",e);
			this.setError("删除能耗失败。");
			return "input";
		}
	}
	/**
	 * 签核每日能耗
	 * @return
	 * @throws ParseException 
	 */
	public String signDailySourceed() throws ParseException{
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		if(sourceService.signDailySourceed(this.getStationNo(),formatDate.format(formatDate.parse(this.getCurrDate())))){
			this.setMsg("签核成功。");
			return "success";
		}else{
			this.setError("由于网络等其他原因，签核失败。");
			return "input";
		}	
			
	}
	/**
	 * 查询能耗计划-月份
	 * @return
	 * @throws ParseException 
	 */
	public String selSourcePlan() throws ParseException{
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null||this.getEndDate()==null){
			/*第一次初始化*/
			String currDate=formatDate.format(new Date());
			String curr[]=currDate.split("-");
			startDate=curr[0]+"-01";
			endDate=curr[0]+"-12";
			totalCounts=sourceService.selSourcePlan(curr[0]+"01",curr[0]+"12").size();
		}else{
			String start[] = this.getStartDate().split("-");
			startDate = start[0]+"-"+start[1];
			String end[] = this.getEndDate().split("-");
			endDate = end[0]+"-"+end[1];
			totalCounts=sourceService.selSourcePlan(start[0]+start[1],end[0]+end[1]).size();
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

			String start[] = this.getStartDate().split("-");
			startDate = start[0]+"-"+start[1];
			String end[] = this.getEndDate().split("-");
			endDate = end[0]+"-"+end[1];
			/*分页查询*/
			SourcePlan=sourceService.selSourcePlan(start[0]+start[1],end[0]+end[1], page);
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
			String start[] = this.getStartDate().split("-");
			startDate = start[0]+"-"+start[1];
			String end[] = this.getEndDate().split("-");
			endDate = end[0]+"-"+end[1];
			/*分页查询*/
			SourcePlan=sourceService.selSourcePlan(start[0]+start[1],end[0]+end[1], page);
			return "success";
		}
		
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
	public String getCurrDate() {
		return CurrDate;
	}
	public void setCurrDate(String currDate) {
		CurrDate = currDate;
	}
	public String getElecNum() {
		return ElecNum;
	}
	public void setElecNum(String elecNum) {
		ElecNum = elecNum;
	}
	public String getWaterNum() {
		return WaterNum;
	}
	public void setWaterNum(String waterNum) {
		WaterNum = waterNum;
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
	public List<DailySourceed> getSourceed() {
		return sourceed;
	}
	public void setSourceed(List<DailySourceed> sourceed) {
		this.sourceed = sourceed;
	}
	public Integer getUnSignCount() {
		return UnSignCount;
	}
	public void setUnSignCount(Integer unSignCount) {
		UnSignCount = unSignCount;
	}
	public List<SourceUsed> getSourcePlan() {
		return SourcePlan;
	}
	public void setSourcePlan(List<SourceUsed> sourcePlan) {
		SourcePlan = sourcePlan;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
