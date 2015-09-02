package com.bap.web.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * controller 基类 辅助类
 * @author edgar_chan    lineshow7@hotmail.com
 * @since 2014年2月9日
 */
public abstract class AssistantController {

	public final static String SUCCESS = "success";
	
	public final static String FAILURE = "failure";
	
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private MySession session;
	
	
	@ModelAttribute
	public void setReqAndResp(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.session = new MySession(request.getSession(true));
	}
	
	
	public HttpServletRequest request(){
		return this.request;
	}
	
	public HttpServletResponse response(){
		return this.response;
	}
	
	public MySession session(){
		return this.session;
	}
	
	public String getRootPath(){
		return this.request.getContextPath();
	}
	
	
	public String getParameter(String name) {
		return request.getParameter(name);
	}
	
	/**
	 * 获取组值
	 * @author edgar_chan
	 * @since 2014年2月9日
	 */
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);	
	}
	
	/**
	 * 参数的快速获取 支持泛型
	 * @author edgar_chan
	 * @since 2014年2月9日
	 */
	@SuppressWarnings("unchecked")
	public <T> T getParameter(String name,Class<T> tClass) {
			String parameter = request.getParameter(name);
			if(StringUtils.isNotBlank(parameter)) {
				if(Date.class.getName().equals(tClass.getName())) {
					return (T)DateTime.parse(parameter).toDate();
				}
				if(DateTime.class.getName().equals(tClass.getName())) {
					return (T)DateTime.parse(parameter);
				}
				if(Long.class.getName().equals(tClass.getName())) {
					return (T)Long.valueOf(parameter);
				}
				if(Integer.class.getName().equals(tClass.getName())) {
					return (T)Integer.valueOf(parameter);
				}
				if(Boolean.class.getName().equals(tClass.getName())) {
					if(NumberUtils.isDigits(parameter)){
						return (T)Boolean.valueOf(BooleanUtils.toBoolean(Integer.parseInt(parameter)));
					}
					return (T)Boolean.valueOf(BooleanUtils.toBoolean(parameter));
				}
				
				return (T)parameter;
			}else {
				return null;
			}
			
	}
	
}
