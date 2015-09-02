<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>修改角色</title>

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
					系统角色修改
				</h3>
				<span style="color: #0afb92; font-size: 14px;">${msg}</span>
				<span style="color: red; font-size: 13px;">${error}</span>
				<form action="updateRole.action" class="niceform" method="post">
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
									所属组别：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="GroupNo" size="1" name="GroupNo" style="width: 200px;" onfocus="getselSysGroup();ResetRoleUpdate();">
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
								<select id="RoleNo" size="1" name="RoleNo" style="width: 300px;"  onfocus="getselSysRole();" onblur="getUpdateRoleDetails();">
									<!-- 加载系统角色 -->		
                            	</select>&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						
						<div align="center" style="margin-right: 130px">角色信息</div>
						<dl>
							<dt>
								<label>
									角色名称：
								</label>
							</dt>
							<div align="left">
							<dd>
								<input id="RoleName" name="RoleName" type="text" size="50">
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
								<select id="State" size="1" name="State" style="width: 200px;">
                               	 	
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
								<input id="Remark" name="Remark" type="text" size="50">
							</dd>
							</div>
						</dl>
						<dl>
							<input type="submit" value="确认修改">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重新输入">
						</dl>

					</fieldset>
				</form>
			</div>
		</center>
	</body>
</html>
