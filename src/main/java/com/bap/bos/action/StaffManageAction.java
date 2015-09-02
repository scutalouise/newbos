package com.bap.bos.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bap.bos.service.StaffService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;

/**
 * 员工管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("staffManageAction")
public class StaffManageAction extends BaseAction {
	/*分页*/
	private Page page=new Page();
	private String to_PageNum="";
	private String pageMsg;
	/*注入*/
	@Resource StaffService staffService;
	/*属性*/
	private String StationNo;
	private String startDate;
	private String endDate;
	private String selType;
	private String selWord;
	/*返回值*/
	private String Msg;
	private String error;
	private List<Object> StaffGroupInfo;
	
	/**
	 * 查询员工上下班情况
	 * @return
	 */
	public String showStaffGroupInfo(){
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null||this.getEndDate()==null){
			/*第一次初始化*/
			String currDate=formatDate.format(new Date());
			String curr[]=currDate.split("-");
			startDate=curr[0]+"-"+curr[1]+"-"+"01";
			selType="-1";
			if("01"==curr[2]){
				//每月第一天录入时
				 endDate=curr[0]+"-"+curr[1]+"-"+"01";
			}else{
				 endDate=curr[0]+"-"+curr[1]+"-"+(Integer.valueOf(curr[2]));
			}
			totalCounts=staffService.selAllStaffGroupsInfo(startDate,endDate,"-1","").size();
			
		}else{
			if("-1".equals(this.getSelType())){
				totalCounts=staffService.selAllStaffGroupsInfo(startDate,endDate,"-1","").size();
			}else{
				totalCounts=staffService.selAllStaffGroupsInfo(startDate,endDate,this.getSelType(),this.getSelWord()).size();
			}
			
		}
		
		/*分页查询显示*/
		if(this.getStartDate()==null||this.getEndDate()==null||this.getSelType()==null||"".equals(this.getSelType())){
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
			StaffGroupInfo=staffService.selAllStaffGroupsInfo(this.getStartDate(), this.getEndDate(),"-1","", page);
			return "success";
		}else{
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
			if("-1".equals(this.getSelType())){
				StaffGroupInfo=staffService.selAllStaffGroupsInfo(this.getStartDate(), this.getEndDate(),"-1","", page);
			}else{
				StaffGroupInfo=staffService.selAllStaffGroupsInfo(this.getStartDate(), this.getEndDate(),this.getSelType(),this.getSelWord(), page);
			}
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


	public List<Object> getStaffGroupInfo() {
		return StaffGroupInfo;
	}

	public void setStaffGroupInfo(List<Object> staffGroupInfo) {
		StaffGroupInfo = staffGroupInfo;
	}
	public String getSelType() {
		return selType;
	}
	public void setSelType(String selType) {
		this.selType = selType;
	}
	public String getSelWord() {
		return selWord;
	}
	public void setSelWord(String selWord) {
		this.selWord = selWord;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
