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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/lhgcalendar.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js">
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
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else {
				if(isNull(document.getElementById("CarID"))&&isNull(document.getElementById("DriverName"))
						&&isNull(document.getElementById("Shipper"))&&isNull(document.getElementById("UnloadDate"))
						&&isNull(document.getElementById("weighname"))&&isNull(document.getElementById("weather"))
						&&is3decimals(document.getElementById("Delivery"))
						&&isNull(document.getElementById("CHead"))&&isNull(document.getElementById("EvilWork"))){
					if(!$("#ShiftDate").val()){
						alert("请选择开班日期！")
						return false;
					}
					if(!$("#ShiftNo").val()){
						alert("请选择开班编号！")
						return false;
					}
					return true;
				}else{
					alert("输入有误，请检查'油品-入库单'信息输入是否正确");
					return false;
				}
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
											进货验收管理->油品收货确认
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
										<td height="20" align="center">
											<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>

									<tr>
										<td height="40" class="font42">
										<form action="bos/addOilRestockbill.action" target="mainFrame" onsubmit="return chenckform();">
											<table width="80%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														油品入库单
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														订单号
													</td>
													<td width="30%">
														${NumAliases}
														<input id="Num" name='Num' type="hidden" value="${Num}" size="30"/>
													</td>
													<td width="20%" align="center" height="25">
														入库单号
													</td>
													<td width="30%" align="left">
														${RestockNum}
														<input id="RestockNum" name='RestockNum' type="hidden" size="30"
															value="${RestockNum}"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														站点名称
													</td>
													<td width="30%" align="left">
														${Station.station_Name}
														<input id="StationNo" name='StationNo' type="hidden"
															class="multiText4" value="${Station.station_No}"/>
													</td>
												
													<td width="20%" align="center" height="25">
														产品类型
													</td>
													<td width="30%" align="left">
														油品
														<input name='ProductType' type="hidden"
															class="multiText4" value="0" size="30"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														产品名称
													</td>
													<td width="30%" align="left">
														<s:iterator value="product">
																<c:if test="${ProductNum==Product_Num}">
																	${Product_Name}
																</c:if>
														</s:iterator>
														<input id='ProductNum' name='ProductNum' type="hidden"
															class="multiText4" value="${ProductNum}" size="30"/>
													</td>
												
													<td width="20%" align="center" height="25">
														开班日期
													</td>
													<td width="30%" align="left">
														<c:choose>
														<c:when test="${param.recManual eq true}">
															<input name="ShiftDate" class="runcode" id="ShiftDate" readonly="readonly" size="30" value="${ShiftDate}"
														onClick="WdatePicker({dateFmt:'yyyyMMdd',onpicked:function(dp){getShiftNos(dp.cal.getNewDateStr())}})"/>
														</c:when>
														<c:otherwise>
															${ShiftDate}
															<input value="${ShiftDate}" name="ShiftDate" class="runcode"
															id="ShiftDate" readonly="readonly" type="hidden"/>
														</c:otherwise>
														</c:choose>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														开班编号
													</td>
													<td width="30%" align="left">
														<c:choose>
														<c:when test="${param.recManual eq true}">
															<select id="ShiftNo" name="ShiftNo">
															</select>
														</c:when>
														<c:otherwise>
															${ShiftNo}
														<input value="${ShiftNo}" id="ShiftNo" name='ShiftNo'
															class="multiText4" value="" type="hidden" size="30"/>
														</c:otherwise>
														</c:choose>
														
														
														
													</td>
												
													<td width="20%" align="center" height="25">
														订货时间
													</td>
													<td width="30%" align="left">
														<fmt:formatDate value="${CreateDate1}"  pattern="yyyy-MM-dd HH:mm:ss"/>
														<input id="ComplementStartDate" name='ComplementStartDate' type="hidden"
															class="multiText4" value="<fmt:formatDate value="${CreateDate1}" pattern="yyyy-MM-dd HH:mm:ss"/>" size="30"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="10%" align="center" height="30" nowrap>
														计量单位
													</td>
													<td width="23%">
														升（L）
														<input value="2" id="Unit" name='Unit'
															class="multiText4" type="hidden" size="30"/>
													<%--
														<select id="Unit" size="1" name="Unit" style="width: 100px;">
	                               	 						<c:if test="${Unit==2}">	
	                               	 							<option value="2" selected="selected">升（L）</option>
	                               	 							<option value="3">吨（t）</option>
	                               	 						</c:if>
	                               	 						<c:if test="${Unit==3}">
	                               	 							<option value="2">升（L）</option>
	                               	 							<option value="3" selected="selected">吨（t）</option>
	                               	 						</c:if>
	                               	 					</select>		
													--%>
													</td>
													<td width="20%" align="center" height="25">
														油罐车到站日期
													</td>
													<td width="30%" align="left">
														<input name="GasArrived" class="runcode" id="GasArrived" readonly="readonly" size="30"
														onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
												
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														油罐车离站日期
													</td>
													<td width="30%" align="left">
														<input name="GasLeft" class="runcode" id="GasLeft" readonly="readonly" size="30"
														onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
													
													</td>
												
													<td width="20%" align="center" height="25">
														车牌号
													</td>
													<td width="30%" align="left">
														<input id="CarID" name='CarID' type="text" class="multiText4" size="30" maxlength="10"   onblur="isNull(this)"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														驾驶员姓名
													</td>
													<td width="30%" align="left">
														<input id="DriverName" name='DriverName' type="text" class="multiText4" size="30" maxlength="20"   onblur="isNull(this)"/>
														
													</td>
												
													<td width="20%" align="center" height="25">
														运输单位
													</td>
													<td width="30%" align="left">
														<input id="Shipper" name='Shipper' type="text" class="multiText4" size="30" maxlength="30"   onblur="isNull(this)"/>
													</td>
												</tr>											
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														取货日期
													</td>
													<td width="30%" align="left">
														<input name="UnloadDate" class="runcode" id="UnloadDate" readonly="readonly" size="30"
														onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
													</td>
													<td width="20%" align="center" height="25">
													<!-- 过磅人 -->
														押运员
													</td>
													<td width="30%" align="left">
														<input id="weighname" name='weighname' type="text" class="multiText4" size="30" maxlength="15"   onblur="isNull(this)"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														发运数
													</td>
													<td width="30%" align="left">
														<input id="Delivery" name='Delivery' type="text" class="multiText4" size="30" maxlength="6"  onblur="is3decimals(this)"/>
													</td>
													<td width="20%" align="center" height="25">
															卸油工
													</td>
													<td width="30%" align="left">
														<input id="EvilWork" name='EvilWork' type="text" class="multiText4" size="30" onblur="isNull(this)" maxlength="20"  />
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														负责人（领班）
													</td>
													<td width="30%" align="left">
														<input id="CHead" name='CHead' type="text" class="multiText4" size="30" onblur="isNull(this)" maxlength="20"  />
													</td>
													<td width="20%" align="center" height="25">
														铅封
													</td>
													<td width="30%" align="left">
														<input id="Seal" name='Seal' type="text" class="multiText4" size="30" maxlength="13"  />
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="20%" align="center" height="25">
														天气
													</td>
													<td width="30%" align="left">
														<input id="weather" name='weather' type="text" class="multiText4" size="30" maxlength="8"   onblur="isNull(this)"/>
													</td>
													<td width="20%" align="center" height="25">
														油罐车载油密度
													</td>
													<td width="30%" align="left">
														<input id="tank_density" name='tank_density' type="text" class="multiText4" size="30" maxlength="6"  onblur="is3decimals(this)"/>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" colspan="4" align="center" height="30">
														<input type="submit" class="right-button02" value="添  加" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="reset" class="right-button02" value="返  回" 
														onclick="location.href='<%=basePath%>bos/selOrderBill_01.action?Status=03'"/>						
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
		var inital = true;
		
		function getShiftNos(shiftDate){
			var shiftDate1 = shiftDate.substring(0,4)+'-'+shiftDate.substring(4,6)+'-'+shiftDate.substring(6,8);
			 $.get("bos/selShiftNo.action?TransItem_ShiftDate="+shiftDate1+"&StationNo=${param.StationNo}", function(result){
				   var shifts = eval(result);
				   var $shiftNo = $("#ShiftNo");
				   $shiftNo.empty();
				   for(var i = 0;i < shifts.length;i++){
					   var cShift = shifts[i];
					   var option = $("<option>").val(cShift.shift_ShiftNo).text(cShift.shift_ShiftNo);
					   $shiftNo.append(option);
				   }
				   if(inital){
					   inital = !inital;
					   $shiftNo.find("option[value='${ShiftNo}']").attr("selected",true);
				   }
				  });
		}
		
		function initalPage(){
			getShiftNos("${ShiftDate}");
		}
		
		initalPage();
		
	</script>
	
	</body>
</html>
