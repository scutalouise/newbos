<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/bos/common/commontop.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<link href="css/bos.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/validate.js">
</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/lhgcalendar.min.js">
</script>
		<script language="JavaScript" type="text/javascript">
		function chenckform(){
			var StationNo=document.getElementById("StationNo").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else {
				if(isNull(document.getElementById("CarID"))&&isNull(document.getElementById("BottlesCarNo"))
					&&isNull(document.getElementById("MStationNo"))&&is1decimals(document.getElementById("MIntoTemp"))
					&&is1decimals(document.getElementById("MOutTemp"))&&is2decimals(document.getElementById("MIntoPrs"))
					&&is2decimals(document.getElementById("MOutPrs"))&&isNull(document.getElementById("MIntodate"))
					&&isNull(document.getElementById("MOutdate"))&&isNull(document.getElementById("DriverName"))
					&&isNull(document.getElementById("weighname"))&&isNull(document.getElementById("Mheader"))
					&&isNull(document.getElementById("Shipper"))&&isNull(document.getElementById("GasArrived"))
					&&isNull(document.getElementById("GasLeft"))&&is1decimals(document.getElementById("CIntoTemp"))
					&&is1decimals(document.getElementById("COutTemp"))&&is2decimals(document.getElementById("CIntoPrs"))
					&&is2decimals(document.getElementById("COutPrs"))
					&&isNull(document.getElementById("EvilWork"))&&isNull(document.getElementById("CHead"))
					&&isNull(document.getElementById("UnloadDate"))&&isNull(document.getElementById("weather"))
					&&is3decimals(document.getElementById("Delivery"))&&is3decimals(document.getElementById("receipts"))
					&&is3decimals(document.getElementById("lossrate"))){
					return true;
				}else{
					alert("输入有误，请检查'气品-入库单'信息输入是否正确");
					return false;
				}
			}
		}
</script>
	</head>

	<body>
		
			<table width="99%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="45" background="./images/bos/nav04.gif">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="21">
												<img src="./images/bos/ico07.gif" width="20" height="18" />
											</td>
											<td width="538">
												<span style="color: white;font-family:'黑体';font-size: 13px;">
													进货验收管理->气品收货确认
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
						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 100%">
							<tr>
								<td height="20" align="center">
									<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
									<span style="color: red; font-size: 13px;">${error}</span>
								</td>
							</tr>			
							<tr>
								<td width="100%">
								<form action="bos/addGasRestockbill.action" method="post" target="mainFrame" onsubmit="return chenckform();">
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 90%" bgcolor="#464646" class="newfont03"
												align="center">
																
											<tr class="CTitle">
												<td height="35" colspan="7" align="center"
													style="font-size: 18px">
													气  品  入  库   单
												</td>
											</tr>
											<tr bgcolor="#faedd6">
												<td nowrap align="center" height="30" width="10%">
													订单号
												</td>
												<td width="23%">
													${Num}
													<input id="Num" name='Num' type="hidden"
														class="multiText4" value="${Num}"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													入库单号
												</td>
												<td width="23%">	
													${RestockNum}
													<input id="RestockNum" name='RestockNum' type="hidden"
														class="multiText4" value="${RestockNum}"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													站点名称
												</td>				
												<td width="23%">
													${Station.station_Name}
													<input id="StationNo" name='StationNo' type="hidden"
														class="multiText4" value="${Station.station_No}"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">
												<td nowrap align="center" height="30" width="10%">
													产品类型
												</td>
												<td width="23%" >
													气品
													<input name='ProductType' type="hidden"
														class="multiText4" value="1" />
												</td>
												<td width="10%" align="center" height="30" nowrap>
													产品名
												</td>
												<td width="23%">
													<s:iterator value="product">
																<c:if test="${ProductNum==Product_Num}">
																	${Product_Name}
																</c:if>
														</s:iterator>
														<input id='ProductNum' name='ProductNum' type="hidden"
															class="multiText4" value="${ProductNum}" size="30"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													开班日期
												</td>
												<td width="23%">
													${ShiftDate}	
													<input value="${ShiftDate}" name="ShiftDate" class="runcode"
														id="ShiftDate" readonly="readonly" type="hidden"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">
												<td nowrap align="center" height="30" width="10%">
													开班编号
												</td>
												<td width="23%">
													${ShiftNo}
													<input value="${ShiftNo}"  id="ShiftNo" name='ShiftNo'
														class="multiText4" type="hidden"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													订货时间
												</td>
												<td width="23%">
													<fmt:formatDate value="${CreateDate1}"  pattern="yyyy-MM-dd HH:mm:ss"/>
													<input id="ComplementStartDate" name='ComplementStartDate' type="hidden"
														class="multiText4" value="<fmt:formatDate value="${CreateDate1}"/>"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													重量单位
												</td>
												<td width="23%">
													<select id="Unit" size="1" name="Unit" style="width: 100px;">
                               	 						<c:if test="${Unit==1}">
                               	 							<option value="1" selected="selected">立方米（m³）</option>
                               	 							<option value="2">升（L）</option>
                               	 							<option value="3">吨（t）</option>
                               	 						</c:if>
                               	 						<c:if test="${Unit==2}">	
                               	 							<option value="1" >立方米（m³）</option>
                               	 							<option value="2" selected="selected">升（L）</option>
                               	 							<option value="3">吨（t）</option>
                               	 						</c:if>
                               	 						<c:if test="${Unit==3}">
                               	 							<option value="1" >立方米（m³）</option>
                               	 							<option value="2">升（L）</option>
                               	 							<option value="3" selected="selected">吨（t）</option>
                               	 						</c:if>                          	 							
                            						</select>		
												</td>
											</tr>
											<tr bgcolor="#faedd6">
												<td width="10%" align="center" height="30" nowrap>
													车牌号
												</td>
												<td width="23%">
													<input id="CarID" name='CarID' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													瓶组车号
												</td>
												<td width="23%">
													<input id="BottlesCarNo" name='BottlesCarNo' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
												<td nowrap align="center" height="30" width="10%">
													母站编号
												</td>
												<td width="23%">
													<input id="MStationNo" name='MStationNo' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">
												<td width="10%" align="center" height="30" nowrap>
													母站进站温度
												</td>
												<td width="23%">
													<input id="MIntoTemp" name='MIntoTemp' type="text" class="multiText4" onblur="is1decimals(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													母站出站温度
												</td>
												<td width="23%">
													<input id="MOutTemp" name='MOutTemp' type="text" class="multiText4" onblur="is1decimals(this)"/>
												</td>
												<td nowrap align="center" height="30" width="10%">
													母站进站压力
												</td>
												<td width="23%">
													<input id="MIntoPrs" name='MIntoPrs' type="text" class="multiText4" onblur="is2decimals(this)"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">			
												<td width="10%" align="center" height="30" nowrap>
													母站出站压力
												</td>
												<td width="23%">
													<input id="MOutPrs" name='MOutPrs' type="text" class="multiText4" onblur="is2decimals(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													母站进站时间
												</td>
												<td width="23%">
													<input name="Mintodate" class="runcode" id="Mintodate" readonly="readonly"/>
													<script type="text/javascript">
														$(function() {
													 	   $('#Mintodate').calendar({format:'yyyy-MM-dd HH:mm:ss'});
															});
													</script>
												</td>
												<td nowrap align="center" height="30" width="10%">
													母站出站时间
												</td>
												<td width="23%">
													<input name="MOutdate" class="runcode" id="MOutdate" readonly="readonly"/>
													<script type="text/javascript">
														$(function() {
													 	   $('#MOutdate').calendar({format:'yyyy-MM-dd HH:mm:ss'});
															});
													</script>
												</td>
											</tr>
											<tr bgcolor="#faedd6">	
												<td width="10%" align="center" height="30" nowrap>
													驾驶员姓名
												</td>
												<td width="23%">
													<input id="DriverName" name='DriverName' type="text" class="multiText4"  onblur="isNull(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													充装工
												</td>
												<td width="23%">
													<input id="weighname" name='weighname' type="text" class="multiText4"  onblur="isNull(this)"/>
												</td>
												<td nowrap align="center" height="30" width="10%">
													母站负责
												</td>
												<td width="23%">
													<input id="Mheader" name='Mheader' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">
												<td width="10%" align="center" height="30" nowrap>
													运输单位
												</td>
												<td width="23%">
													<input id="Shipper" name='Shipper' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													气罐车到站（子站）日期
												</td>
												<td width="23%">
													<input name="GasArrived" class="runcode" id="GasArrived" readonly="readonly"/>
													<script type="text/javascript">
														$(function() {
													 	   $('#GasArrived').calendar({format:'yyyy-MM-dd HH:mm:ss'});
															});
													</script>
												</td>
												<td nowrap align="center" height="30" width="10%">
													气罐车离站（子站）日期
												</td>
												<td width="23%">
													<input name="GasLeft" class="runcode" id="GasLeft" readonly="readonly"/>
													<script type="text/javascript">
														$(function() {
													 	   $('#GasLeft').calendar({format:'yyyy-MM-dd HH:mm:ss'});
															});
													</script>
												</td>
											</tr>
											<tr bgcolor="#faedd6">												
												<td width="10%" align="center" height="30" nowrap>
													子站进站温度
												</td>
												<td width="23%">
													<input id="CIntoTemp" name='CIntoTemp' type="text" class="multiText4" onblur="is1decimals(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													子站出站温度
												</td>
												<td width="23%">
													<input id="COutTemp" name='COutTemp' type="text" class="multiText4" onblur="is1decimals(this)"/>
												</td>
												<td nowrap align="center" height="30" width="10%">
													子站进站压力
												</td>
												<td width="23%">
													<input id="CIntoPrs" name='CIntoPrs' type="text" class="multiText4" onblur="is2decimals(this)"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">												
												<td width="10%" align="center" height="30" nowrap>
													子站出站压力
												</td>
												<td width="23%">
													<input id="COutPrs" name='COutPrs' type="text" class="multiText4" onblur="is2decimals(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													铅封
												</td>
												<td width="23%">
													<input id="Seal" name='Seal' type="text" class="multiText4"/>
												</td>
												<td nowrap align="center" height="30" width="10%">
													卸气工
												</td>
												<td width="23%">
													<input id="EvilWork" name='EvilWork' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">											
												<td width="10%" align="center" height="30" nowrap>
													子站负责人
												</td>
												<td width="23%">
													<input id="CHead" name='CHead' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													取货日期
												</td>
												<td width="23%">
													<input name="UnloadDate" class="runcode" id="UnloadDate" readonly="readonly"/>
													<script type="text/javascript">
														$(function() {
													 	   $('#UnloadDate').calendar({format:'yyyy-MM-dd HH:mm:ss'});
															});
													</script>
												</td>
												<td nowrap align="center" height="30" width="10%">
													天气
												</td>
												<td width="23%">
													<input id="weather" name='weather' type="text" class="multiText4" onblur="isNull(this)"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">
												<td width="10%" align="center" height="30" nowrap>
													发运数
												</td>
												<td width="23%">
													<input id="Delivery" name='Delivery' type="text" class="multiText4" onblur="is3decimals(this)"/>
												</td>
												<td width="10%" align="center" height="30" nowrap>
													实收
												</td>
												<td width="23%">
													<input id="receipts" name='receipts' type="text" class="multiText4" onblur="is3decimals(this)"/>
												</td>
												<td nowrap align="center" height="30" width="10%">
													损耗率
												</td>
												<td width="20%">
													<input id="lossrate" name='lossrate' type="text" class="multiText4" onblur="is3decimals(this)"/>
												</td>
											</tr>
											<tr bgcolor="#faedd6">
													<td width="40%" colspan="6" align="center" height="45">
														<input type="submit" class="right-button08" value="添加入库单" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="reset" class="right-button08" value="取  消" />
													
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
	</body>
</html>
