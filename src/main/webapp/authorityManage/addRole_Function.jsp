<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%> 
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

		<title>添加角色功能</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${pageContext.request.contextPath}/css/form.css"
			type="text/css" rel="stylesheet">
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/rankmenu.js"
			type="text/javascript"></script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/selectFunction.js"
			type="text/javascript"></script>	
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript"></script>	
		<base target="_self">
	</head>

	<body>
		<center>
			<div class="form">
				<h3>
					角色-权限管理
				</h3>
				<span id="msg" style="color: #0afb92; font-size: 14px;"></span>
				<span id="error" style="color: red; font-size: 13px;"></span>
					<fieldset>
						<dl>
							<dt>
								<label>
									所属系统：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="SystemFlag" size="1" name="SystemFlag" style="width: 200px;" onblur="selSysFunction();">
                               	 	<option value="-1" selected="selected">===========请选择===========</option>
                               	 	<option value="06" >Card系统</option>
                               	 	<option value="07">Hos总部系统</option>
                               	 	<option value="08">Bos站级系统</option>
                            	</select>&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						
						<dl>
							<dt>
								<label>
									所属组别：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="GroupNo" size="1" name="GroupNo" style="width: 200px;" onfocus="getselSysGroup();">
                               	 	<!-- 系统自动加载 -->
                            	</select>&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						
						<dl>
							<dt>
								<label>
									角色：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="RoleNo" size="1" name="RoleNo" style="width: 200px;" onfocus="getselSysRole();" onblur="selRoleFunction();">
									<!-- 加载系统角色 -->		
                            	</select>&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						
						<dl>
							<dt>
								<label>
									功能情况：
								</label>
							</dt>
						</dl>	
					</fieldset>
							
					<div align="left" class="dtree">
							<div id="functionTree"></div>
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<script type="text/javascript">
							
						</script>				
						<input type="hidden" id="RoleFunctionNo" name="RoleFunctionNo">	
						<input type="button" value="更新角色功能" onclick="UpdateRoleFunction();">	
					</div>
							
			</div>
						
		</center>
		
	</body>
</html>
