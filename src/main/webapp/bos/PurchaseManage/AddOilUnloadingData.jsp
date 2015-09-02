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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Purchase.js"></script>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/repertory.js"></script>
		<script type="text/javascript">
		function chenckform(){
			var StationNo=document.getElementById("StationNo").value;
			var OilUnloadingData_PumpSum=document.getElementById("OilUnloadingData_PumpSum").value;
			var OilUnloadingData_TotalVol=document.getElementById("OilUnloadingData_TotalVol").value;
			var OilUnloadingData_OilVol=document.getElementById("OilUnloadingData_OilVol").value;
			var OilUnloadingData_Temp=document.getElementById("OilUnloadingData_Temp").value;
			var OilUnloadingData_WaterHeight=document.getElementById("OilUnloadingData_WaterHeight").value;
			var OilUnloadingData_OilHeight=document.getElementById("OilUnloadingData_OilHeight").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else if(is3decimals(document.getElementById("OilUnloadingData_PumpSum"))&&is3decimals(document.getElementById("OilUnloadingData_TotalVol"))
					&&is3decimals(document.getElementById("OilUnloadingData_OilVol"))&&is2decimals(document.getElementById("OilUnloadingData_Temp"))
					&&is2decimals(document.getElementById("OilUnloadingData_WaterHeight"))&&is2decimals(document.getElementById("OilUnloadingData_OilHeight"))){
				return true;
			}else{
				alert("输入有误，请检查录入信息。");
				return false;
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
											采购作业->卸油
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
										<form action="bos/saveOilUnloadingData.action" target="mainFrame" onsubmit="return chenckform();">
											<input name="UnloadingOilStatus" value="${UnloadingOilStatus}" type="hidden">
											<table width="50%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														添加液位仪数据
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														订单编号
													</td>
													<td width="60%" align="left">
														${Num}
														<input name="Num" value="${Num}" type="hidden">
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油罐编号
													</td>
													<td width="60%" align="left">
														${TankNum}号罐 (油枪：${Tank_Nozzles})
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油罐对应枪磅码数和
													</td>
													<td width="60%">
														<input id="OilUnloadingData_PumpSum" name="OilUnloadingData_PumpSum" class="runcode" id="OilUnloadingData_PumpSum" 
															onblur="is3decimals(this)"/>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油罐油水总体积
													</td>
													<td width="60%">
														<input id="OilUnloadingData_TotalVol" name="OilUnloadingData_TotalVol" class="runcode" id="OilUnloadingData_TotalVol" 
															onblur="is3decimals(this)"/>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油罐油体积
													</td>
													<td width="60%" align="left">
														<input id="OilUnloadingData_OilVol" name="OilUnloadingData_OilVol" id="OilUnloadingData_OilVol" onblur="is3decimals(this)"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油温度
													</td>
													<td width="60%" align="left">
														<input id="OilUnloadingData_Temp" name="OilUnloadingData_Temp" id="OilUnloadingData_Temp" onblur="is2decimals(this)"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														水高
													</td>
													<td width="60%" align="left">
														<input id="OilUnloadingData_WaterHeight" name="OilUnloadingData_WaterHeight" id="OilUnloadingData_WaterHeight" onblur="is2decimals(this)"/>
														(mm)
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油高
													</td>
													<td width="60%" align="left">
														<input id="OilUnloadingData_OilHeight" name="OilUnloadingData_OilHeight" id="OilUnloadingData_OilHeight" onblur="is2decimals(this)"/>
														(mm)
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" colspan="2" align="center" height="30">
													<input type="hidden" name=StationNo id="StationNo" value="${Station.station_No}" />
														<input type="submit" class="right-button02" value="添  加" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="button" class="right-button02" value="返  回" 
															onclick="location.href='<%=basePath%>bos/selOrderBill_01.action?'"/>
													
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
