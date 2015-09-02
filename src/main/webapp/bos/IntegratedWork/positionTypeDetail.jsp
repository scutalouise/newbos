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

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
</style>
		<link href="css/bos.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body bgcolor="#c6dffc">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
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
														基本业务->职位划分表
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
					<table id="subtree1" style="DISPLAY: " width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">

									<tr>
										<td height="10"></td>
									</tr>

									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03" >
												<tr class="CTitle">
													<td height="35" colspan="3" align="center"
														style="font-size: 16px">
														职位划分表
													</td>
												</tr>
												<tr bgcolor="#f9f9f9" align="center">
													<td width="7%" align="center" height="30">
														职位编号
													</td>
													<td width="12%">
														职位描述
													</td>
													<td width="12%">
														最后修改时间
													</td>
													
												</tr>
											<s:iterator value="Position">
												<tr bgcolor="#FFFFFF" align="center">
													<td height="20">
														${PositionType_Num}
													</td>
													<td>
														${PositionType_Desc}
													</td>
													<td>
														<fmt:formatDate value='${PositionType_SyncDate}' type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
													</td>													
												</tr>
											</s:iterator>
											</table>
										</td>
									</tr>
								</table>
								
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</body>
</html>
