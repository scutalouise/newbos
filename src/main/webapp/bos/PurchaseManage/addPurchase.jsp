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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/SupplierSel.js"></script>
		<script type="text/javascript">
		function chenckform(){
			var StationNo=document.getElementById("StationNo").value;
			var ExpectDate=document.getElementById("o_ExpectDate").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else{
				var $NumAliasesVal = $("#NumAliases").val();
				if(!$.trim($NumAliasesVal)){
					alert("请填写订单编号！");return false;
				}
				if($("#tankId").find(":checked").size() < 1){
					alert("请勾选油罐号！");return false;
				}
				var $supplierNumVal = $("#OrderBill_SupplierNum").val();
				if(!$NumAliasesVal){
					alert("请选择供应商！");return false;
				}
				var $addVolumeVal = $("#o_AddVolume").val();
				if(!$.trim($addVolumeVal)){
					alert("请填写补货量！");return false;
				}
				var $addWeight = $("#o_AddWeight").val();
				if(!$.trim($addWeight)){
					alert("请填写补货量！");return false;
				}
				var $expectDateVal = $("#o_ExpectDate").val();
				if(!$expectDateVal){
					alert("请选择到达时间！");return false;
				}
				
				if(is2decimals(document.getElementById("o_AddVolume"))&&is3decimals(document.getElementById("o_AddWeight"))){
						return true;
			}}}
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
										<td width="4%">
											<img src="./images/bos/ico07.png" width="20" height="18" />
										</td>
										<td width="96%">
											<span style="color: white;font-family:'黑体';font-size: 13px;">
											采购作业->创建订单
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
											<span style="color: green; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>

									<tr>
										<td height="40" class="font42">
										<form action="bos/addOrderBill.action" target="mainFrame" onsubmit="return chenckform();">
											<table width="55%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														创建订单
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														订单编号
													</td>
													<td width="60%">
														<!-- 订单 -->
														<input id="o_Num" name="Num" type="hidden" value="${OrderBillNum}"/>
														<input id="NumAliases" name="NumAliases" onblur="isNull(this);"/>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														站点名称
													</td>
													<td width="60%">
														&nbsp;${Station.station_Name}
													<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														产品类型
													</td>
													<td width="60%" align="left">
														<select id="ProductType" size="1" name="ProductType" style="width: 200px;" onclick="getProdunctList();">
                               	 							<option value="0">油品</option>
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														产品名称
													</td>
													<td width="60%" align="left">
														<select id="ProductNum" size="1" name="ProductNum" style="width: 200px;" onclick="getTankCheckbox();" >
															<!-- 加载产品名称，选择气品或者油品时添加 -->								
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油罐编号
													</td>
													<td width="60%" align="left">
														<div id="tankId"></div>
														<!--<select id="TankNum" size="1" name="TankNum" style="width: 200px;" onfocus="getTankList();">
															 加载油罐或者气罐编号，选择气品或者油品时添加							
                            							</select> -->	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														供货商
													</td>
													<td width="60%" align="left">
														<select id="OrderBill_SupplierNum" size="1" name="SupplierNum" style="width: 225px;">
															<!-- 加载供货商 -->
														</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														补货单位
													</td>
													<td width="60%" align="left">
														<select id="Unit" size="1" name="Unit" style="width: 150px;">
                               	 						<!--  <option value="1" >立方米（m³）</option>-->	
                               	 							<option value="2" selected="selected">升（L）</option>
                               	 						<!-- <option value="3">吨（t）</option> -->	
                               	 						 </select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														补货量（L）
													</td>
													<td width="60%" align="left">
														<input id="o_AddVolume" name="AddVolume" size="30" onblur="is2decimals(this);"/>
														<span>例：99.99</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														补货单位
													</td>
													<td width="60%" align="left">
														<select id="Unit" size="1" style="width: 150px;">
                               	 						<!--  <option value="1" >立方米（m³）</option>
                               	 							<option value="2" selected="selected">升（L）</option>-->	
                               	 							<option value="3">吨（t）</option>
                               	 						 </select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														补货量（t）
													</td>
													<td width="60%" align="left">
														<input id="o_AddWeight" name="AddWeight" size="30" onblur="is3decimals(this);"/>
														<span>例：99.999</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														到达时间
													</td>
													<td width="60%" align="left">
													<input value="${ExpectDate}" name="ExpectDate" class="runcode"
														id="o_ExpectDate" size="30" readonly="readonly" 
														onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
														
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" colspan="2" align="center" height="30">
													<input type="hidden" name="StaffNo" id="StaffNo" value="${LoginStaff.staff_No}" />
														<input type="submit" class="right-button02" value="添  加" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="reset" class="right-button02" value="返  回" 
															onclick="location.href='<%=basePath%>bos/selOrderBill.action'"/>
													
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
