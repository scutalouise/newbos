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

</style>
		<link href="css/bos.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript">
		</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/mainMenu.js"
			type="text/javascript">
		</script>
		
	</head>
	<body bgcolor="#c6dffc">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
				<input type="hidden" name="StaffNo" id="StaffNo"
					value="${LoginStaff.staff_No}" />
				<input type="hidden" name="StaffPW" id="StaffPW"
					value="${LoginStaff.staff_PW}" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" bgcolor="#7fb5ec">

								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="45" bgcolor="#7fb5ec">
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="21">
														<img src="./images/bos/ico07.png" width="20" height="18" />
													</td>
													<td width="538">
														<span style="color: white;font-family:'黑体';font-size: 13px;">
															系统导航菜单
														</span>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="mainMenu"></div>
				</td>
			</tr>
		</table>

	</body>
</html>
