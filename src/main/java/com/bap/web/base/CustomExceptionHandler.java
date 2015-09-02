package com.bap.web.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception re, HttpServletRequest request) {
		if(BindException.class.isInstance(re)){
			request.setAttribute("errorMsg", "输入数据有误！");
		}
		return "error";
	}
}
