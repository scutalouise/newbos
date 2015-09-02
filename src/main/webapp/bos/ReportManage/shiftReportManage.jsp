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
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/reportManage.js"
			type="text/javascript"></script>
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
			var ShiftNo=document.getElementById("ShiftNo").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else if(TransItem_ShiftDate==null||TransItem_ShiftDate==""
					||ShiftNo==null||ShiftNo==""){
				alert("请选择日期和班别编号。")
				return false;
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
											报表管理->销售班报表
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
										<!-- action="bosReport/reportShift.action" onsubmit="return chenckform();" -->
										<form name="reportShift"  target="_blank" >
											<s:set name="DayTransV_Time" value="#parameters.DayTransV_Time[0]"></s:set>
											
											<table width="73%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														销售（班）-报表
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="50">
														班别日期：
														<input type="hidden" id="StationName" name="StationName" value="${Station.station_Name}"/>
														<input  id="TransItem_ShiftDate" name="TransItem_ShiftDate" 
														value="${TransItem_ShiftDate}" readonly="readonly" size="10" onClick="WdatePicker()"/>	
														&nbsp;&nbsp;
														班别编号：
														<input type="hidden" id="StationNo" name="StationNo" value="${Station.station_No}"/>
														<select id="ShiftNo" size="1" name="ShiftNo" style="width: 285px;" onfocus="getShiftNo();">
                               	 							 <!-- AJAX加载 -->              	 							
                            							</select>
														<input type="button" class="right-button08" value="获取报表" 
															onclick="reportShift.action='reportShift.action';if(chenckform()) reportShift.submit();"/>
														<input type="button" class="right-button08" value="导出报表" 
															onclick="reportShift.action='reportShiftXls.action';if(chenckform()) reportShift.submit();"/>
													</td>
												</tr>											
											</table>										
										</form>
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
