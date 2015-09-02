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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
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
			var today=document.getElementById("today").value;
			var ElecNum=document.getElementById("ElecNum").value;
			var WaterNum=document.getElementById("WaterNum").value;
			var StationNo=document.getElementById("StationNo").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else if(""==today.replace(/(^\s*)|(\s*$)/g, "")||""==ElecNum.replace(/(^\s*)|(\s*$)/g, "")
					||""==WaterNum.replace(/(^\s*)|(\s*$)/g, "")){
				alert("日期或水电值不能为空！");
				return false;
			}else {
				return true;
			}
		}
		</script>
	</head>
	<body bgcolor="#f4f4f4">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" background="./images/bos/nav04.gif">
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="21">
											<img src="./images/bos/ico07.gif" width="20" height="18" />
										</td>
										<td width="538">
											<span style="color: white;font-family:'黑体';font-size: 13px;">
											能耗管理->添加每日能耗
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
										<td height="100" align="center">
											<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>

									<tr>
										<td height="40" class="font42">
										<form action="bos/addDailySource.action" target="mainFrame" onsubmit="return chenckform();">
											<table width="40%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#464646" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														添加每日能耗
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="30%" align="center" height="30">
														站点名称
													</td>
													<td width="70%" align="center">
														${Station.station_Name}
													<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>	
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="30%" align="center" height="30">
														能耗日期
													</td>
													<td width="70%" align="left">
														<input name="CurrDate" id="today" size="20" readonly="readonly" onClick="WdatePicker()"/>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="30%" align="center" height="30">
														电耗（kw/h）
													</td>
													<td width="70%" align="left">
														<input id="ElecNum" name="ElecNum" size="20" onblur="is2decimals(this);"/><span>例：15.12</span>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="30%" align="center" height="30">
														水耗（m³）
													</td>
													<td width="70%" align="left">
														<input id="WaterNum" name="WaterNum" size="20" onblur="is2decimals(this);"/><span>例：8.15</span>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="30%" colspan="2" align="center" height="30">
														<input type="submit" class="right-button02" value="添  加" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="button" class="right-button02" value="返  回" 
															onclick="location.href='<%=basePath%>bos/showSource.action'"/>
													
													</td>
												</tr>
											</table>
										</form>
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
