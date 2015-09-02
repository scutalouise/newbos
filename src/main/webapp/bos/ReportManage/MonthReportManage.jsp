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
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js">
		</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/reportManage.js">
		</script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
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
		<script type="text/javascript">
		function chenckform(){
			var StationNo=document.getElementById("StationNo").value;
			var TransItem_ShiftDate=document.getElementById("TransItem_ShiftDate").value;
			var endDate=document.getElementById("endDate").value;
			var Type=document.getElementById("Type").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else if("1"==Type){
				if(TransItem_ShiftDate==null||TransItem_ShiftDate==""
					||Type==null||Type==""){
					alert("请选择日期、报表类型。")
					return false;
				}else{
					return true;
				}
			}else if("2"==Type){
				if(TransItem_ShiftDate==null||TransItem_ShiftDate==""
					||Type==null||Type==""||endDate==null||endDate==""){
					alert("请选择日期、报表类型。")
					return false;
				}else{
					return true;
				}
			}else if("3"==Type){
				if(TransItem_ShiftDate==null||TransItem_ShiftDate==""
					||Type==null||Type==""){
					alert("请选择日期、报表类型。")
					return false;
				}else{
					return true;
				}
			}else {
				return true;
			}
		}
		</script>
	</head>
	<body bgcolor="#c6dffc">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
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
											报表管理->月盘点/结算报表
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
										<td height="150" align="center">
											
										</td>
									</tr>

									<tr>
										<td height="40" class="font42">
										
											<s:set name="DayTransV_Time" value="#parameters.DayTransV_Time[0]"></s:set>
											<table width="70%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														月盘点/结算报表
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="45%" align="center" height="50">
													<form id="MonthReport" name="MonthReport" target="_blank">
														报表：
														<input type="hidden" id="StationNo" name="StationNo" value="${Station.station_No}"/>
														<input type="hidden" id="StationName" name="StationName" value="${Station.station_Name}"/>
														<select id="Type" size="1" name="Type" style="width: 160px;" onblur="getMonthReportType();">
                               	 							 <option value="1" selected="selected">月盘点报表</option>
                               	 							 <option value="3">月盘点(销量细化)报表</option>
                               	 							 <option value="2">月结算报表</option>               	 							
                            							</select>
														月份：
														<input  id="TransItem_ShiftDate" name="TransItem_ShiftDate" 
														value="${TransItem_ShiftDate}" readonly="readonly" size="10" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
															onfocus="getMonthReportType();"/>	
														&nbsp;&nbsp;-&nbsp;&nbsp;
														<input  id="endDate" name="endDate" 
															value="不可用" readonly="readonly" size="10" disabled="disabled"
															onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>	
														&nbsp;&nbsp;
														<input type="button" class="right-button08" value="获取报表" 
															onclick="MonthReport.action='bosReport/MonthReport.action';if(chenckform()) MonthReport.submit();"/>
														<input type="button" class="right-button08" value="导出报表" 
															onclick="MonthReport.action='bosReport/MonthReportXls.action';if(chenckform()) MonthReport.submit();"/>
													</form>
													</td>
												</tr>											
											</table>										
										
											<table height="200">
												<tr>
												<td></td>
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
