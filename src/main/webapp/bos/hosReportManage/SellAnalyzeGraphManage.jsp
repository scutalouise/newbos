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
			var startDate1=document.getElementById("startDate1").value;
			var endDate=document.getElementById("endDate").value;
			var Type=document.getElementById("Type").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else if("1"==Type||"2"==Type||"3"==Type){
				if(startDate1==null||startDate1==""){
					alert("请选择日期。")
					return false;
				}else{
					return true;
				}	
			}else if("4"==Type){
				if(startDate==null||startDate==""){
					alert("请选择年份。")
				return false;
				}else{
					return true;
				}	
			}else if("5"==Type){
				if(startDate==null||startDate==""||endDate==null||endDate==""){
					alert("请选择对比年份。")
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			} 
		}
		
		window.onload=function(){
     		selStation();
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
											总部报表管理->加油站销售分析图表
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
										<form action="hosGraph/hosSellAnalyzeGraph.action" target="_blank" onsubmit="return chenckform();">
											<s:set name="DayTransV_Time" value="#parameters.DayTransV_Time[0]"></s:set>
											
											<table width="80%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														加油站销售分析图表
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="50">
														报表：
														<select id="Type" size="1" name="Type" style="width: 190px;" onblur="getSellAnalyzeGraphType();">
                               	 							 <option value="1" selected="selected">加油站日时点加油分析图</option>
                               	 							 <option value="2">加油站日付油结构分析图</option>
                               	 							 <option value="3">加油站日销量交易类型分析图</option>
                               	 							 <option value="4">加油站月油品销量分析图</option>
                               	 							 <option value="5">加油站年销售对比分析图</option>          	 							
                            							</select>
                            							&nbsp;
                            							站点：
														<select id="StationNo" size="1" name="StationNo" style="width: 150px;">
															         	 							
                            							</select>
                            							&nbsp;&nbsp;
														日期：
														<input id="startDate1" name="startDate1" 
															value="${startDate1}" readonly="readonly" size="10" 
															onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>	
														
														<input id="startDate" name="startDate" 
															value="${startDate}" readonly="readonly" size="10" 
															onClick="WdatePicker({dateFmt:'yyyy'})" type="hidden"/>	
														<span id="split" style="display:none">&nbsp;与&nbsp;</span>
														<input id="endDate" name="endDate" readonly="readonly" size="10"
															onClick="WdatePicker({dateFmt:'yyyy'})" type="hidden"/>	
														&nbsp;&nbsp;
														<input type="submit" class="right-button08" value="获取报表" />
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
