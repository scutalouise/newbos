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
				<td height="45">
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
											基本业务->站点基本资料
											</span>
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
											<table width="80%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														站点基本资料
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点编号
													</td>
													<td width="70%" align="center">
														${Station.station_Aliases}
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点名称
													</td>
													<td width="70%" align="center">
														${Station.station_Name}
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点类型
													</td>
													
													<td width="70%" align="center">
													<c:if test="${Station.station_Type==1}">
														CNG 
													</c:if>
													<c:if test="${Station.station_Type==2}">
														LNG
													</c:if>
													<c:if test="${Station.station_Type==3}">
														CNG
													</c:if>
													<c:if test="${Station.station_Type==4}">
														LPG  
													</c:if>
													<c:if test="${Station.station_Type==5}">
														油 
													</c:if>
													<c:if test="${Station.station_Type==6}">
														混合
													</c:if>				
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														经营模式
													</td>
													<td width="70%" align="center">
														<c:if test="${Station.station_BusinessModel==1}">
															全资
														</c:if>
														<c:if test="${Station.station_BusinessModel==2}">
															控股
														</c:if>
														<c:if test="${Station.station_BusinessModel==3}">
															租赁
														</c:if>
														<c:if test="${Station.station_BusinessModel==4}">
															其他 
														</c:if>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点种类
													</td>
													<td width="70%" align="center">
														<c:if test="${Station.station_Class==1}">
															母站
														</c:if>
														<c:if test="${Station.station_Class==2}">
															子站
														</c:if>
														<c:if test="${Station.station_Class==3}">
															标准站
														</c:if>
														<c:if test="${Station.station_Class==4}">
															撬装站
														</c:if>
														<c:if test="${Station.station_Class==5}">
															其他 
														</c:if>		
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点地址
													</td>
													<td width="70%" align="center">
														${Station.station_Address}
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点电话（1）
													</td>
													<td width="70%" align="center">
														${Station.station_Phone1}
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点电话（2）
													</td>
													<td width="70%" align="center">
														${Station.station_Phone2}
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点传真
													</td>
													<td width="70%" align="center">
														${Station.station_Fax}
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														最后更新时间
													</td>
													<td width="70%" align="center">
													<fmt:formatDate value='${Station.station_SyncDate}' type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
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
		</table>

	</body>
</html>
