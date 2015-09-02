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
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript">
		</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/page.js"
			type="text/javascript">
		</script>
		<script language="javascript"
					src="${pageContext.request.contextPath}/js/shiftTransItemAdd.js"
					type="text/javascript">
		</script>
	</head>
	<body bgcolor="#c6dffc">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" bgcolor="#7fb5ec">
								<form action="bos/selShiftVerify.action">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="21">
												<img src="./images/bos/ico07.png" width="20" height="18" />
											</td>
											<td width="538">
											<span style="color: white;font-family:'黑体';font-size: 13px;">
												营业资料管理->班结审核确认->班结审核
											</span>
											</td>

										</tr>
									</table>
								</form>
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
								<table width="70%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="30" align="center">
											<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>
									<tr>
											
										<td height="40" class="font42">
											<center>
											<!-- 提示信息 -->
											<strong>
												<div id="Msg" style="color: #0afb92; font-size: 14px;" align="center"></div>
											</strong>
											<br/>
											</center>
											<table width="100%" border="0" cellpadding="0" id="Nozzle"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<tr class="CTitle">
													<td height="35" colspan="9" align="center"
														style="font-size: 16px">
														班结审核（日期:${ShiftDate}&nbsp;&nbsp;班别:${ShiftNo}&nbsp;&nbsp;开班时间:${Shift_StartTime}）
														<input id="ShiftDate" type="hidden" value="${ShiftDate}" name="ShiftDate">
														<input id="ShiftNo" type="hidden" value="${ShiftNo}" name="ShiftNo">
														<input id="Shift_StartTime" type="hidden" value="${Shift_StartTime}" name="Shift_StartTime">
														<input id="Shift_EndTime" type="hidden" value="${Shift_EndTime}" name="Shift_EndTime">
														<input id="Shift_CreateTime" type="hidden" value="${Shift_CreateTime}" name="Shift_CreateTime">
														<input type="hidden" name="StationNo" id="StationNo" value="${Station.station_No}" />
														<input type="hidden" name="StaffNo" id="StaffNo" value="${LoginStaff.staff_No}" />
														<input type="hidden" name="startDate" id="startDate" value="${startDate}" />
														<input type="hidden" name="endDate" id="endDate" value="${endDate}" />
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													<td width="6%" align="center" height="35">
														枪号
													</td>
													<td width="8%" align="center">
														产品名称
													</td>
													<td width="8%" align="center">
														开班磅码
													</td>
													<td width="8%" align="center">
														接班磅码
													</td>
													<td width="8%" align="center">
														差额（±0.1）<br/>（油机磅码数-交易升数）
													</td>
													<td width="6%" align="center">
														操作
													</td>
												</tr>
											
												<%--<s:iterator value="shiftVerify" status="st">
												
													<tr bgcolor="#FFFFFF">
														<td align="center" height="20">
															${NozzleId}
															<input id="NozzleId" name="NozzleId" value="${NozzleId}" type="hidden">
														</td>
														<td align="center">
															${ProductName}
															<input id="ProductName" name="ProductName" value="${ProductName}" type="hidden">
															<input id="ProductNum" name="ProductNum" value="${ProductNum}" type="hidden">
														</td>
														<td align="center">
															${StartVol}
														</td>
														<td align="center">
															${EndVol}															
														</td>
														<td align="center">
															${SubtractionValue}
															<input id="SubtractionValue" name="SubtractionValue" value="${SubtractionValue}" type="hidden">
														</td>
														<td align="center">
														<c:if test="${SubtractionValue>0.1}">
															<a
																href="javascript:" onclick="javascript:return transItemAdd(NozzleId.value,ProductNum.value,ProductName.value,SubtractionValue.value);">补录交易</a>								
														</c:if>
														</td>
													</tr>
												</s:iterator>--%>
											</table>
											<br/>
											<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0">
												<tr>
													<td height="35" colspan="9" align="center">
														<input type="button" class="right-button09" value="确认审核" onclick="onShiftVerify();"/>
														<input type="button" class="right-button09" value="返  回"
															onclick="location.href='<%=basePath%>bos/selShiftVerify.action?startDate=${startDate}&endDate=${endDate}'"/>
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
