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
			var startDate=document.getElementById("startDate").value;
			var endDate=document.getElementById("endDate").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else if(startDate==null||startDate==""
					||endDate==null||endDate==""){
					alert("请选择起始日期。")
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
											报表管理->加油明细报表
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
										<td height="60" class="font42">
										<!--action="bosReport/TransDetailsReport.action"  onsubmit="return chenckform(); -->
										<form name="TransDetailsReport" target="_blank" ">
											<s:set name="DayTransV_Time" value="#parameters.DayTransV_Time[0]"></s:set>
											
											<table width="90%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														加油明细报表
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="60%" align="center" height="80">
														报表：
														<input type="hidden" id="StationNo" name="StationNo" value="${Station.station_No}"/>
														<input type="hidden" id="StationName" name="StationName" value="${Station.station_Name}"/>
														<select id="Type" size="1" name="Type" style="width: 140px;" onblur="getDetailsReportType();">
                               	 							 <option value="1" selected="selected">加油明细报表</option>
                               	 							 <option value="2">补录交易明细报表</option>
                               	 							 <option value="3">回灌交易明细报表</option>
                               	 							 <option value="4">自用卡交易明细报表</option>
                               	 							 <option value="5">客户卡交易细报表</option>
                               	 							 <option value="6">优惠卡交易明细报表</option>
                               	 							 <option value="7">现金交易明细报表</option>          	 							
                            							</select>
														日期：
														<input id="startDate" name="startDate" 
														value="${startDate}" readonly="readonly" size="10" 
															onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
															onfocus="getDetailsReportType();"/>	
														-
														<input  id="endDate" name="endDate" 
															value="" readonly="readonly" size="10" 
															onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>	
														&nbsp;
														<span id="nozzleSpan">枪号：</span>	
														<select id="nozzleNo" name="nozzleNo" style="width: 50px;">
															<!-- 加载 -->
														</select>
														&nbsp;
														<span id="ProductSpan">产品名称：	</span>	
														<select id="ProductNum" name="ProductNum" style="width: 60px;" >
															<!-- 加载 -->
														</select>		
														&nbsp;
														<span id="cardNoSpan">客户卡号：</span>	
														<input  id="cardNo" name="cardNo" size="15"/>
														<br/><br/>
														<input type="button" class="right-button08" value="获取报表" 
															onclick="TransDetailsReport.action='TransDetailsReport.action';if(chenckform()) TransDetailsReport.submit();"/>
														<input type="button" class="right-button08" value="导出报表" 
															onclick="TransDetailsReport.action='TransDetailsReportXls.action';if(chenckform()) TransDetailsReport.submit();"/>
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
