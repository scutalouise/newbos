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

		<title>修改密码</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${pageContext.request.contextPath}/css/form.css"
			type="text/css" rel="stylesheet">
	</head>

	<body>
		<center>
			<div class="form">
				<h3>
					管理员密码修改
				</h3>
				<span style="color: #0afb92; font-size: 14px;">${msg}</span>
				<span style="color: red; font-size: 13px;">${error}</span>
				<form action="updatepas.action" method="post" class="niceform">
					<fieldset>
						<dl>
							<dt>
								<label>
									账&nbsp;&nbsp;号：
								</label>
							</dt>
							<dd>
								<input name="userName" type="text" readonly="readonly"
									value="${currentAdmin.admin_admin}" size="50">
							</dd>
						</dl>
						<dl>
							<dt>
								<label>
									旧密码：
								</label>
							</dt>
							<dd>
								<input name="oldPas" type="password" size="50">
							</dd>
						</dl>
						<dl>
							<dt>
								<label>
									新密码：
								</label>
							</dt>
							<dd>
								<input name="newPas" type="password" size="50">
								<span style="color: red; font-size: 13px;">*</span>
							</dd>
						</dl>
						<dl>
							<dt>
								<label>
									密码确认：
								</label>
							</dt>
							<dd>
								<input name="newPas1" type="password" size="50">
								<span style="color: red; font-size: 13px;">*</span>
							</dd>
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
