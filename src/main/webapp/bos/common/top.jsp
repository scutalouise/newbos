<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/bos/common/commontop.jsp"%>
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

		<title>后台管理系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
a:link { 
color:#000000; 
text-decoration:underline; 
} 
a:visited { 
color:#000000; 
text-decoration:none; 
} 
a:hover { 
color:#000000; 
text-decoration:none; 
} 
a:active { 
color:#FFFFFF; 
text-decoration:none; 
} 
</style>
		<link href="css/bos.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<c:set var="StaffNo" value="${LoginStaff.staff_No}"></c:set>
	<c:set var="StaffPW" value="${LoginStaff.staff_PW}"></c:set>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="70">
					<table width="99%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="1%">
							<div style="width:105%; height:70px; background-image:url(./images/bos/logo.png)">
								<div style="margin-left: 1153;">
									<div style="height:30px;">&nbsp;</div>
									<a href="./bos/mainfra.jsp" target="mainFrame" style="text-decoration: none;">
										<img alt="首页导航" src="./images/bos/daohang.png" width="30" height="30">
									</a>
									&nbsp;
									<a href="./bos/TuiChu.action" target="_top" style="text-decoration: none;">
										<img alt="退出系统" src="./images/bos/exit.png" width="30" height="30">
									</a>	
								</div>
							</div>
							<!-- <img src="./images/bos/logo.jpg" width="105%" height="70" border="0" />-->					
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</body>
</html>
