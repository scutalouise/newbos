package com.bap.bos.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.authority.util.RoleFunction;
import com.bap.bos.domain.Staff;
import com.bap.bos.service.Login;
import com.bap.bos.service.StationService;
import com.bap.bos.util.BaseAction;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *登陆管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("loginbosAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(LoginAction.class);
	
	/*注入*/
	@Resource private Login login;
	@Resource StationService stationService;
	/*属性*/
	private String StaffNo;
	private String StaffPW;
	/*返回值*/
	private String msg;
	private String functionList;
	
	
	public String skipStone(){
		if(session.get("LoginStaff") != null)
			return "success";
		return "input";
	}
	
	/**
	 * 登陆
	 * @return
	 */
	public String Login(){
		Staff staff = (Staff)ServletActionContext.getRequest().getSession().getAttribute("LoginStaff");
		if(staff != null) return "success";
		
		List<Staff> result=null;
		if("".equals(StaffNo)||StaffNo==null
				||StaffPW==null||"".equals(StaffPW)){
			this.setMsg("登陆失败！用户名密码不能为空。");
			return "input";			
		}else{
			result=login.adminCheck(this.getStaffNo(), this.getStaffPW());
			if(result==null){
				this.setMsg("登陆失败！用户名密码不能为空。");
				return "input";	
			}else if(result.size()<=0){
				this.setMsg("登陆失败！用户名密码错误。");
				return "input";	
			}else if(result.size()>0){
				session.put("Station", stationService.selStationDetail().get(0));
				session.put("LoginStaff", result.get(0));
				return "success";
			}else{
				return "input";
			}
		}
		
	}
	/**
	 * 退出
	 * @return
	 */
	public String delSession() {	
		session.put("LoginStaff", null);
		return "toLogin";
	}
	/**
	 * 左边List
	 * @return
	 */
	public String leftList(){
		List<RoleFunction> rfList = login.Login_function(this.getStaffNo(), this.getStaffPW());
		
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			functionList = objectMapper.writeValueAsString(rfList);
		}catch(Exception e){
			logger.error("解析功能菜单错误！",e);
		}
		
		//functionList=JSONArray.fromObject(rfList).toString();
		//System.out.print(functionList);
		return "success";
	}
	

	public String getStaffNo() {
		return StaffNo;
	}
	public void setStaffNo(String staffNo) {
		StaffNo = staffNo;
	}
	public String getStaffPW() {
		return StaffPW;
	}
	public void setStaffPW(String staffPW) {
		StaffPW = staffPW;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFunctionList() {
		return functionList;
	}
	public void setFunctionList(String functionList) {
		this.functionList = functionList;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
}
