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
			if(is3decimals(document.getElementById("ActAddVolume"))
					&&is2decimals(document.getElementById("CostPrice"))
					&&isNull(document.getElementById("ActPlanDate"))){
				return true;
			}else{
				alert("输入有误，请检查订单信息。");
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
											采购作业->油品订货
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
										<td height="30" align="center">
											<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>
										
									<tr>
										<s:set name="startDate" value="#parameters.startDate[0]"></s:set>
										<s:set name="endDate" value="#parameters.endDate[0]"></s:set>
										<s:set name="QueryType" value="#parameters.QueryType[0]"></s:set>
										<s:set name="QueryContext" value="#parameters.QueryContext[0]"></s:set>
										
										<td height="40" class="font42">
										<form action="bos/orderGoods.action" target="mainFrame" onsubmit="return chenckform();">
											<table width="50%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														油品订货管理
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														订单编号
													</td>
													<td width="60%">
														<s:set name="OrderBillNum" value="#parameters.OrderBill_Num[0]"></s:set>
														<input id="Num" name="Num" type="hidden" value="${OrderBillNum}"/>
														<span id="OrderBillNum"></span>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														站点名称
													</td>
													<td width="60%">
														&nbsp;${Station.station_Name}	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														产品类型
													</td>
													<td width="60%" align="left">
														&nbsp;油品
														<input id="ProductType" name="ProductType" type="hidden" value="0"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														产品名称
													</td>
													<td width="60%" align="left">
														<span id="ProductName"></span>		
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油罐编号
													</td>
													<td width="60%" align="left">
														<span id="TankNum"></span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														补货单位
													</td>
													<td width="60%" align="left">
														<span id="Unit"></span>	
														<input type="hidden" name="Unit" id="Unit1" />
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														补货量
													</td>
													<td width="60%" align="left">
														<span id="AddVolume"></span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														期望到达时间
													</td>
													<td width="60%" align="left">
														<span id="ExpectDate"></span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														发起订单员工编号
													</td>
													<td width="60%" align="left">
													
													<s:iterator value="Staff">
														<c:if test="${OrderBill_ProductNum==Product_Num}">
															${Product_Name}
														</c:if>
													</s:iterator>
														<span id="CreateStaffNo"></span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														订单确认时间
													</td>
													<td width="60%" align="left">
														<span id="AckDate"></span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														确认订单员工编号
													</td>
													<td width="60%" align="left">
														<span id="AckStaffNo"></span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														实际订货量
													</td>
													<td width="60%" align="left">
													<input name="ActAddVolume" class="runcode" id="ActAddVolume"  onblur="is3decimals(this)"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														订货总价
													</td>
													<td width="60%" align="left">
													<input name="CostPrice" class="runcode" size="30" id="CostPrice"  onblur="is2decimals(this)"/>
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
														预计到达时间
													</td>
													<td width="60%" align="left">
													<input name="ActPlanDate" class="runcode" id="ActPlanDate"
														size="30" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" colspan="2" align="center" height="30">
														<input type="hidden" name="StaffNo" id="StaffNo" value="${LoginStaff.staff_No}" />
														<input type="hidden" name="QueryType" value="1">
														<input type="hidden" name="QueryContext" value="${OrderBillNum}">
														<input type="hidden" name="Status" value="${Status}">			
														<input value="${startDate}" name="startDate" type="hidden">
														<input value="${endDate}" name="endDate" type="hidden">
														<input type="submit" class="right-button02" value="订  货" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="reset" class="right-button02" value="返  回" 
															onclick="location.href='<%=basePath%>bos/selOrderBill_02.action?startDate=${startDate}&endDate=${endDate}&QueryType=${QueryType}&QueryContext=${QueryContext}&Status=${Status}'"/>
													
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
