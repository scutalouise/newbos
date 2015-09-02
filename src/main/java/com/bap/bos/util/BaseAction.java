package com.bap.bos.util;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author zhulong
 *
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements SessionAware{

	public Map<String, Object> session = null;
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public Map<String, Object> getSession() {
		return session;
	}



	
}
