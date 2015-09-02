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
			}else if(!$("#selectMonth").val()){
				alert("请选择月份！");return false;
			}else if(startDate==null||startDate==""
					||endDate==null||endDate==""){
					alert("请选择起始日期！")
					return false;
			}else {
				return true;
			}
		}
		</script>
	</head>
	<body bgcolor="#c6dffc">
	
	<script type="text/javascript">
		
		var minDate = "";
		var maxDate = "";
		var monthFb = 'f'; 
		
		var monthspan = [];
		
		var csym = null;
		
		selectMonthTrigger = function(yyyyMonth){
			
			if(csym == yyyyMonth) return;
			csym = yyyyMonth;
			$("#startDate").val('');
			$("#endDate").val('');
			
			minDate = yyyyMonth+"-"+monthspan[0];
			maxDate = yyyyMonth+"-"+monthspan[1];
			if('f' == monthFb)
				maxDate = addmulMonth(maxDate,1);
			else
				minDate = addmulMonth(minDate,-1);
		}
		
		function  addmulMonth(dtstr,n){
			var s=dtstr.split("-");
			var yy = parseInt(s[0]);
			var mm = parseInt(s[1]);
			mm = mm + n;
			
			if(0 < mm < 13){
			}else{
				if(n < 0){yy--;}
				else{yy++;}
			}
			var dd = yy+"-"+((mm+"").length == 1?("0"+mm):mm)+"-"+s[2];
			return dd;
		}
		
		function getMinLimit(){
			return minDate;
		}
		function getMaxLimit(){
			return maxDate;
		}
		
		$(function(){
			$.get("${basePath}mvc/monthchecking/fetch/montharea", function(result){
				monthspan[0] = result.monthDateAera_StartDate;
				if(15 - parseInt(monthspan[0]) < 0)monthFb = 'b';
				monthspan[1] = result.monthDateAera_NextEndDate;
			});
		});
	
	</script>
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
												报表管理->库存盘点
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
										<!--action="bosReport/StocktakingReport.action"  onsubmit="return chenckform();"-->
										<form name="StocktakingReport" target="_blank" id="StocktakingReport" >
											<s:set name="DayTransV_Time" value="#parameters.DayTransV_Time[0]"></s:set>
											
											<table width="50%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														库存盘点
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" height="50" style="padding-left: 100px">
														报表：
														<input type="hidden" id="StationName" name="StationName" value="${Station.station_Name}"/>
														<input type="hidden" id="StationNo" name="StationNo" value="${Station.station_No}"/>
														<select id="Type" size="1" name="Type" style="width: 160px;" onblur="getStock_takingReportType();">
                               	 							 <option value="1" selected="selected">库存盘点报表</option>
                               	 							 <option value="2">库存盘点(销量细化)报表</option>
                               	 							 <option value="3">罐储油量测量记录报表</option>       	 							
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" height="50" style="padding-left: 100px">
                            							月份：
                            							<input id="selectMonth" name="selectMonth"
														value="${startDate}" readonly="readonly" size="10" 
															onClick="WdatePicker({dateFmt:'yyyy-MM',maxDate:'%y-{%M-1}',onpicked:function(dp){selectMonthTrigger(dp.cal.getNewDateStr())},oncleared:function(){minDate = '';maxDate = '';$('#startDate').val('');$('#endDate').val('');csym = null;}})">	
														
													</td>
												</tr>
												<tr  bgcolor="#ffffff">
												<td width="30%" height="50" style="padding-left: 100px">
														日期：
														<input id="startDate" name="startDate" 
														value="${startDate}" readonly="readonly" size="10" 
														onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{getMinLimit()}',maxDate:'#F{getMaxLimit()}'})"	>	
														-
														<input  id="endDate" name="endDate" 
														value="${endDate}" readonly="readonly" size="10" 
															onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{getMinLimit()}',maxDate:'#F{getMaxLimit()}'})"/>	
														&nbsp;&nbsp;
							
													</td>
												</tr>
												<tr  bgcolor="#ffffff">
													<td width="30%" align="center" height="50">
													<input type="button" class="right-button08" value="获取报表"  id="showReport" />
														<input type="button" class="right-button08" value="导出报表"  id="downloadReport" />
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
		<script type="text/javascript">
		
		$("#showReport").click(function(){
			if($("#Type").val() == 2){
				$("#StocktakingReport").attr("action",'StocktakingReport.action');
			}else{
				$("#StocktakingReport").attr("action",'${basePath}mvc/monthchecking/report/pdf');
			}
			if(chenckform())$("#StocktakingReport").submit();
		});
		
		$("#downloadReport").click(function(){
			if($("#Type").val() == 2){
				$("#StocktakingReport").attr("action",'StocktakingReportXls.action');
			}else{
				$("#StocktakingReport").attr("action",'${basePath}mvc/monthchecking/report/pdf_download');
			}
			if(chenckform())$("#StocktakingReport").submit();
		});
		
		</script>
		
		
	</body>
</html>
