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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Purchase.js"></script>
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
											产品管理->产品变价管理->添加变价预设信息
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
										<form action="bos/saveSellPricePlan.action" method="post" target="mainFrame"  onsubmit="return chenckCurrentform();">
											<table width="53%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														添加变价预设信息
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														站点名称
													</td>
													<td width="70%" align="center">
														${Station.station_Name}
													<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>	
													<input id="StaffNo" name="StaffNo" type="hidden" value="${LoginStaff.staff_No}"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														产品名称
													</td>
													<td width="70%" align="left" style="padding-left: 3px;">
														<input id="ProductType" name="ProductType" type="hidden" value="0"/>
														<select id="ProductNum" size="1" name="ProductNum" style="width: 200px;">
															<!-- 加载产品名称，选择气品或者油品时添加 -->								
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														生效时间
													</td>
													<td width="70%" align="left" style="padding-left: 3px;">
														<input name="EffDate" id="EffDate" size="24" 
														  	onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
														<span>例：2000-01-01 12:00:00</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														新单价(RMB)
													</td>
													<td width="70%" align="left" style="padding-left: 3px;">
														<input id="Price" name="Price" size="24" onblur="is2decimals(this)"/>
														<span>例：7.65</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" colspan="2" align="center" height="30">
														<input type="submit" class="right-button02" value="添  加" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="button" class="right-button02" value="返  回" 
															onclick="location.href='bos/selSellPricePlan.action'"/>
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
<script type="text/javascript">
		function formatDate(date){
			var year = date.getFullYear();
			var month = date.getMonth()+1;    //js从0开始取 
			month < 10 && (month = '0'+month);
			var date1 = date.getDate(); 
			date1 < 10 && (date1 = '0'+date1);
			var hour = '00'; 
			var minutes = '01'; 
			var second = '00';
		 	return year+"-"+month+"-"+date1+" "+hour+":"+minutes +":"+second+" ";
		}
		var datetime = formatDate(new Date());
		document.getElementById("EffDate").value = datetime;
		function chenckCurrentform(){
			var EffDate= document.getElementById("EffDate").value;
			var StationNo=document.getElementById("StationNo").value;
			var cPrice = document.getElementById("Price").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}
			if((!!!EffDate)){
				alert("生效时间不能为空！");
				return false;
			}
			if(!cPrice){
				alert("单价必填！");
				return false;
			}
				return true;
		}
		</script>
	</body>
</html>
