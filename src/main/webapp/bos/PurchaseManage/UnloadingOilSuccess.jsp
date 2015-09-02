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
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/orderBillManage.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/UnloadingOil.js"></script>
		
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
		<link href="css/jquery.ui.progressbar.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function chenckform(){		
				document.getElementById("tishi").innerHTML='<font style="color: green;size:6;">读取油罐数据中......请勿进行其他操作！</font>';
				return true;
			}
			function tishi(){
				alert("正在读取数据中，请等待...");
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
										<td height="15" align="center">
											
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
											<s:set name="OrderBillNum" value="#parameters.Num[0]"></s:set>
											<s:set name="TankNum" value="#parameters.TankNum[0]"></s:set>
											<s:set name="ProductName" value="#parameters.ProductName[0]"></s:set>
											<s:set name="ActVol" value="#parameters.ActVol[0]"></s:set>
											<s:set name="startDate" value="#parameters.startDate[0]"></s:set>
											<s:set name="endDate" value="#parameters.endDate[0]"></s:set>
											<form action="UnloadingOil.action" method="post" onsubmit="return chenckform();">
												<input name="UnloadingOilStatus" value="01" type="hidden">
												<input name="startDate" value="${startDate}" type="hidden">
												<input name="endDate" value="${endDate}" type="hidden">
												<table width="50%" border="0" cellpadding="0" cellspacing="1"
													bgcolor="#cccccc" class="newfont03" align="center">
													<tr class="CTitle">
														<td height="35" colspan="2" align="center"
															style="font-size: 16px">
															读取液位仪数据成功，可以进行卸油作业。
														</td>
													</tr>
											
													<tr bgcolor="#ffffff">
														<td width="40%" colspan="2" align="center" height="30">
														<input type="reset" id="fanhui" class="right-button02" value="返  回" 
															onclick="location.href='<%=basePath%>bos/selOrderBill_01.action?startDate=${startDate}&endDate=${endDate}&QueryType=1&QueryContext=${OrderBillNum}&Status=${Status}'"/>
														</td>
													</tr>
												</table>
											</form>
											<br/><br/><br/>
											<center>
												<div id="myProgressBar"></div>
												<div id="tishi" style="font-size: 20"></div>
												<span style="color: #0afb92; font-size: 20px;">${Msg}</span>
												<span style="color: red; font-size: 18px;">${error}</span>
											</center>
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
