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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/PromotionInfo.js"></script>
		
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
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}
			
			var Promotion_TimeStart = $.trim($("#Promotion_TimeStart").val());
			var Promotion_TimeEnd = $.trim($("#Promotion_TimeEnd").val());
			var Promotion_MoneyLow = $.trim($("#Promotion_MoneyLow").val());
			var Promotion_MoneyHigh = $.trim($("#Promotion_MoneyHigh").val());
			if(!Promotion_TimeStart || !Promotion_TimeEnd || !Promotion_MoneyLow || !Promotion_MoneyHigh){
				alert("请填写完全数据后再保存！");return false;
			}else 
				return true;
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
											营业资料管理->添加促销信息
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
										<form action="bos/savePromotion.action" target="mainFrame" onsubmit="return chenckform();">
											<table width="53%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														添加促销信息
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														产品名称
													</td>
													<td width="70%">
														<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>													
														<select id="ProductNum" size="1" name="ProductNum" style="width: 120px;">
															<!-- 加载产品名称，选择气品或者油品时添加 -->								
		                            					</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														促销时间
													</td>
													<td width="70%" align="left">	
														<input name="Promotion_TimeStart" class="runcode" id="Promotion_TimeStart" readonly="readonly" size="20"
														onClick="WdatePicker({dateFmt:'HH:mm:ss'})"/> -
														<input name="Promotion_TimeEnd" class="runcode" id="Promotion_TimeEnd" readonly="readonly" size="20"
														onClick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														消费金额
													</td>
													<td width="70%" align="left">
														<input id="Promotion_MoneyLow" name="Promotion_MoneyLow" value="${Promotion_MoneyLow}" onblur="is2decimals(this)"/> -
														<input id="Promotion_MoneyHigh" name="Promotion_MoneyHigh" value="${Promotion_MoneyHigh}" onblur="is2decimals(this)"/> 
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														促销品
													</td>
													<td width="70%" align="left">
														<select id="PromotionInfo" size="1" name="PromotionInfo" style="width: 200px;">
															<!-- 加载产品名称，选择气品或者油品时添加 -->								
		                            					</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" colspan="2" align="center" height="30">
														<input type="submit" class="right-button02" value="添  加" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="button" class="right-button02" value="返  回" 
															onclick="location.href='bos/selPromotion.action'"/>
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
