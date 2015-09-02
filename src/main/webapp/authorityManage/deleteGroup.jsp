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

		<title>删除组别</title>

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
<script type="text/javascript">
	function ConfirmDel(){
	   var	Name = document.getElementById("GroupNo").value;
	   if(Name==null || Name==""){
	   		alert("请选择需要删除的组别。");
	   		return false;
	   }else{
   		if(confirm("您确定要删除本组别？删除后不可恢复！"))
    		return true;
  		else
     		return false;
	   }
	   
	}
</script>		
			
	</head>

	<body>
		<center>
			<div class="form">
				<h3>
					系统组别删除
				</h3>
				<span style="color: #0afb92; font-size: 14px;">${msg}</span>
				<span style="color: red; font-size: 13px;">${error}</span>
				<form action="delGroup.action" class="niceform" method="post" onsubmit="return ConfirmDel();">
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
									组别：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="GroupNo" size="1" name="GroupNo" style="width: 300px;" onfocus="getselSysGroup();">
									<!-- 加载系统角色 -->		
                            	</select>&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
					
						<dl>
							<input type="submit" value="组别删除">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="重新选择" onclick="javascript:ResetRoleSel();">
						</dl>

					</fieldset>
				</form>
			</div>

		</center>
	</body>
</html>
