<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@page import="com.bap.authority.domain.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>添加组别</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${pageContext.request.contextPath}/css/form.css"
			type="text/css" rel="stylesheet">
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript"></script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/rankmenu.js"
			type="text/javascript"></script>	
			
	</head>

	<body>
		<center>
			<div class="form">
				<h3>
					系统组别添加
				</h3>
				<span style="color: #0afb92; font-size: 14px;">${msg}</span>
				<span style="color: red; font-size: 13px;">${error}</span>
				<form action="addGroup.action" class="niceform" method="post">
					<fieldset>
						<dl>
							<dt>
								<label>
									所属系统：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="SystemFlag" size="1" name="SystemFlag" style="width: 200px;">
                               	 	<option value="06" >Card系统</option>
                               	 	<option value="07">Hos总部系统</option>
                               	 	<option value="08" selected="selected">Bos站级系统</option>
                            	</select>&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						
						<dl>
							<dt>
								<label>
									组别名：
								</label>
							</dt>
							<div align="left">
							<dd>
								<input name="GroupName" type="text" size="40" onfocus="getselSysGroup_add();">
								&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						
						<dl>
							<dt>
								<label>
									是否可用：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select size="1" name="State" style="width: 200px;">
                               	 	<option value="1" selected="selected">是</option>
                               		 <option value="0">否</option>
                            	</select>&nbsp;<span style="color: red">*</span>
                            </dd>
                            </div>
						</dl>
						
						<dl>
							<dt>
								<label>
									备注：
								</label>
							</dt>
							<div align="left">
							<dd>
								<input name="Remark" type="text" size="50">&nbsp;&nbsp;&nbsp;
							</dd>
							</div>
						</dl>
										
						<dl>
							<input type="submit" value="添加组别">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重新输入">
						</dl>
					
						<dl>
							<dt>
								<label>
									系统已有组别：
								</label>
							</dt>
							<div align="left">
							<dd>
								<label id="SysGroupShow">	
								</label>
							</dd>
							</div>
						</dl>
					</fieldset>
				</form>
				
			</div>

		</center>
	</body>
</html>
