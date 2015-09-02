package com.bap.bos.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.domain.SellingTarget;
import com.bap.bos.service.SellingTargetService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;

/**
 *销售计划管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("sellingPlanMangeAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SellingPlanMangeAction extends BaseAction {
	/*注入*/
	@Resource SellingTargetService sellingTargetService;
	/*分页*/
	private Page page=new Page();
	private String to_PageNum="";
	private String pageMsg;
	/*属性*/
	private String startDate;
	private String endDate;
	private String ProductName;
	
	private String StationNo;
	private String TheMonth;
	private String ProductNum;
	private String PlanQTY;
	/*返回值*/
	private String Msg;
	private String error;
	private List<Object> selSellingPlan;

	public String selSellingPlan(){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null||this.getEndDate()==null){
			/*第一次初始化*/
			String currDate=formatDate.format(new Date());
			String curr[]=currDate.split("-");
			startDate=curr[0]+"-01";
			endDate=curr[0]+"-12";
			totalCounts=sellingTargetService.selSellTarget(curr[0]+"01",curr[0]+"12","").size();
		}else{
			String start[] = this.getStartDate().split("-");
			startDate = start[0]+"-"+start[1];
			String end[] = this.getEndDate().split("-");
			endDate = end[0]+"-"+end[1];
			if(this.getProductName()==null || "".equals(this.getProductName().trim())){
				totalCounts=sellingTargetService.selSellTarget(start[0]+start[1],end[0]+end[1],"").size();
			}else{
				totalCounts=sellingTargetService.selSellTarget(start[0]+start[1],end[0]+end[1],this.getProductName().trim()).size();
			}
			
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
			selSellingPlan=sellingTargetService.selSellTarget(start[0]+start[1],end[0]+end[1],"",page);
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
			String start[] = this.getStartDate().split("-");
			startDate = start[0]+"-"+start[1];
			String end[] = this.getEndDate().split("-");
			endDate = end[0]+"-"+end[1];
			/*分页查询*/
			if(this.getProductName()==null || "".equals(this.getProductName().trim())){
				selSellingPlan=sellingTargetService.selSellTarget(start[0]+start[1],end[0]+end[1],"",page);
			}else{
				selSellingPlan=sellingTargetService.selSellTarget(start[0]+start[1],end[0]+end[1],this.getProductName().trim(),page);
			}
			return "success";
		}
		
	}

	/**
	 * 添加销售计划
	 * @return
	 */
	public String addSellingPlan(){
		SellingTarget SellingTarget = new SellingTarget();
		SellingTarget.setSellingTarget_StationNo(StationNo);
		SellingTarget.setSellingTarget_TheMonth(TheMonth.substring(0, 4)+TheMonth.substring(5, 7));
		SellingTarget.setSellingTarget_ProductNum(ProductNum);
		SellingTarget.setSellingTarget_PlanQTY(PlanQTY.trim());
		SellingTarget.setSellingTarget_IsSync("2");
		SellingTarget.setSellingTarget_SyncDate(new Date());
		try {
			sellingTargetService.saveObject(SellingTarget);
			this.setMsg("销售计划添加成功！");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.setError("由于重复添加或Session失效，添加失败！");
			return "input";
		}		
	}
	/**
	 * 修改销售计划
	 * @return
	 */
	public String updateSellingPlan(){
		SellingTarget SellingTarget = new SellingTarget();
		SellingTarget.setSellingTarget_StationNo(StationNo);
		SellingTarget.setSellingTarget_TheMonth(TheMonth);
		SellingTarget.setSellingTarget_ProductNum(ProductNum);
		SellingTarget.setSellingTarget_PlanQTY(PlanQTY.trim());
		SellingTarget.setSellingTarget_IsSync("2");
		SellingTarget.setSellingTarget_SyncDate(new Date());
		try {
			sellingTargetService.updateObject(SellingTarget);
			this.setMsg("销售计划修改成功！");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.setError("由于网络或Session失效，修改失败！");
			return "input";
		}
	}
	/**
	 * 确认销售计划
	 * @return
	 */
	public String verifySellingPlan(){
		SellingTarget SellingTarget = new SellingTarget();
		SellingTarget.setSellingTarget_StationNo(StationNo);
		SellingTarget.setSellingTarget_TheMonth(TheMonth);
		SellingTarget.setSellingTarget_ProductNum(ProductNum);
		SellingTarget.setSellingTarget_PlanQTY(PlanQTY.trim());
		SellingTarget.setSellingTarget_IsSync("1");
		SellingTarget.setSellingTarget_SyncDate(new Date());
		try {
			sellingTargetService.updateObject(SellingTarget);
			this.setMsg("销售计划已确认！");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "input";
		}
	}
	/**
	 * 删除销售计划
	 * @return
	 */
	public String deleteSellingPlan(){
		String sql="delete from tb_SellingTarget where" +
				" SellingTarget_StationNo='"+StationNo+"' and" +
				" SellingTarget_TheMonth='"+TheMonth+"' and" +
				" SellingTarget_ProductNum='"+ProductNum+"'";
		try {
			sellingTargetService.deleteSQL(sql);
			this.setMsg("删除销售计划成功！");
			return "success";
		} catch (Exception e) {
			this.setError("由于网络或Session失效，删除失败！");
			return "input";
		}
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

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
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

	public List<Object> getSelSellingPlan() {
		return selSellingPlan;
	}

	public void setSelSellingPlan(List<Object> selSellingPlan) {
		this.selSellingPlan = selSellingPlan;
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

	public String getTheMonth() {
		return TheMonth;
	}

	public void setTheMonth(String theMonth) {
		TheMonth = theMonth;
	}

	public String getProductNum() {
		return ProductNum;
	}

	public void setProductNum(String productNum) {
		ProductNum = productNum;
	}

	public String getPlanQTY() {
		return PlanQTY;
	}

	public void setPlanQTY(String planQTY) {
		PlanQTY = planQTY;
	}
	
	
	
}
