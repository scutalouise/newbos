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
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js">
</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/page.js"
			type="text/javascript">
</script>
<script type="text/javascript">
	function ConfirmDel(){
	   if(confirm("您确定要签核本条库存吗？签核后将上传总部，不可修改！"))
	     return true;
	   else
	     return false;
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
								<form action="bos/selRepertory.action">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="margin-top: 5px;">
										<tr>
											<td width="4%" >
												<img src="./images/bos/ico07.png" width="20" height="18" />
											</td>
											<td width="96%">
												<span style="color: white;font-family:'黑体';font-size: 13px;">
													时间：
												</span>
												<input value="${startDate}" name="startDate" class="runcode"
														id="start" size="12" readonly="readonly" onClick="WdatePicker()"/>
												<span style="color: white;font-family:'黑体';font-size: 13px;">至</span>		
												<input value="${endDate}" name="endDate" class="runcode"
													id="end" size="12" readonly="readonly" onClick="WdatePicker()"/>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<span style="color: white;font-family:'黑体';font-size: 13px;">
												产品类型：
												</span>
												<select id="ProductType" size="1" name="ProductType" style="width: 80px;">
	                       	 						<c:if test="${ProductType==-1||ProductType==null}">	
	                       	 							<option value="-1" selected="selected">全部</option>
	                       	 							<option value="0">油品</option>
	                       	 						<!-- <option value="1">气品</option> -->	
	                       	 						</c:if>
	                       	 						<c:if test="${ProductType==0}">	
	                       	 							<option value="-1" >全部</option>
	                       	 							<option value="0" selected="selected">油品</option>
	                       	 						<!-- <option value="1">气品</option>-->
	                       	 						</c:if>
	                       	 						<c:if test="${ProductType==1}">	
	                       	 							<option value="-1" >全部</option>
	                       	 							<option value="0" >油品</option>
	                       	 						<!--<option value="1"selected="selected">气品</option>-->
	                       	 						</c:if>                          	 							
                            					</select>
                            					&nbsp;&nbsp;&nbsp;&nbsp;
                            					<span style="color: white;font-family:'黑体';font-size: 13px;">
                            					&nbsp;&nbsp;班别编号：
                            					<input type="text" name="ShiftNo" value="${ShiftNo}"/>
                            					&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="submit" class="right-button02"
													value="查 询" />
                            					
                            					</span>
											</td>

										</tr>
									</table>
								</form>
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
										<td height="40" class="font42">

											<table width="100%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<tr class="CTitle">
													<td height="35" colspan="13" align="center"
														style="font-size: 16px">
														站点库存详情
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													<!--  
													<td width="10%" align="center" height="35">
														站点名称
													</td>
													-->
													<td width="6%" align="center" height="25">
														产品类型
													</td>
													<td width="6%" align="center">
														产品名称
													</td>
													<td width="6%" align="center">
														油罐编号
														<!-- /气罐编号 -->
													</td>
													<td width="8%" align="center">
														油品体积（L）
														<!-- /气品气量（m³） -->
													</td>
													<td width="5%" align="center">
														油高
													</td>
													<td width="5%" align="center">
														水高
													</td>
													<td width="5%" align="center">
														温度
													</td>
												<!-- <td width="5%" align="center">
														密度
													</td> 	
													<td width="5%" align="center">
														压力
													</td>-->
													<td width="8%" align="center">
														班别编号
													</td>
													<td width="10%" align="center">
														录入时间
													</td>
													<td width="12%" align="center">
														备注
													</td>
													<!--  
													<td width="8%" align="center">
														操作
													</td>
													-->
												</tr>
												<s:iterator value="repertory" status="st">
													<tr bgcolor="#FFFFFF" height="20">
													<!--  
														<td height="20" align="center">
															${Station.station_Name}
														</td>
													-->
														<td align="center">
														<s:set name="Repertory_ProductType" value='repertory[#st.index][0].repertory_ProductType'></s:set>	
														<c:if test="${Repertory_ProductType==0}">
															油品
														</c:if>
														<c:if test="${Repertory_ProductType==1}">
															气品
														</c:if>
														</td>
														<td align="center">
															<s:set name="product_Name" value='repertory[#st.index][1].product_Name'></s:set>	
															<s:set name="Repertory_ProductNum" value='repertory[#st.index][0].repertory_ProductNum'></s:set>
															<s:property value="repertory[#st.index][1].product_Name"/>
														</td>
														<td align="center">
															<s:set name="Repertory_TankNum" value='repertory[#st.index][0].repertory_TankNum'></s:set>
															<s:property value="repertory[#st.index][0].repertory_TankNum"/>
														</td>
														<td align="center">
															<s:set name="repertory_ProductVol" value='repertory[#st.index][0].repertory_ProductVol'></s:set>
															<s:property value="repertory[#st.index][0].repertory_ProductVol"/> 
														</td>
														<td align="center">
															<s:set name="repertory_PetrolLevel" value='repertory[#st.index][0].repertory_PetrolLevel'></s:set>
															<s:property value="repertory[#st.index][0].repertory_PetrolLevel"/> 
														</td>
														<td align="center">
															<s:set name="repertory_WaterLevel" value='repertory[#st.index][0].repertory_WaterLevel'></s:set>
															<s:property value="repertory[#st.index][0].repertory_WaterLevel"/> 
														</td>
														<td align="center">
															<s:set name="repertory_Temp" value='repertory[#st.index][0].repertory_Temp'></s:set>
															<s:property value="repertory[#st.index][0].repertory_Temp"/> 
														</td>
														<!--
														<td align="center">
															<s:set name="repertory_Density" value='repertory[#st.index][0].repertory_Density'></s:set>
															<s:property value="repertory[#st.index][0].repertory_Density"/> 
														</td>
														<td align="center">
															<s:set name="repertory_Pressure" value='repertory[#st.index][0].repertory_Pressure'></s:set>
															<s:property value="repertory[#st.index][0].repertory_Pressure"/> 
														</td>
														  -->
														<td align="center">
															<s:set name="Repertory_ShiftNo" value='repertory[#st.index][0].repertory_ShiftNo'></s:set>
															<s:property value="repertory[#st.index][0].repertory_ShiftNo"/>
														</td>
														<td align="center">
															<s:set name="repertory_AddTime" value='repertory[#st.index][0].repertory_AddTime'></s:set>
															<s:date format="yyyy-MM-dd HH:mm:ss"  name="repertory[#st.index][0].repertory_AddTime"/>

														</td>
														<td align="center">
															<s:set name="repertory_Remark" value='repertory[#st.index][0].repertory_Remark'></s:set>
															<s:property value="repertory[#st.index][0].repertory_Remark"/>
														</td>
														<!--  
														<td align="center">
														
															<a
																href="bos/delRepertory.action?StationNo=${Station.station_No}
																&ProductType=${Repertory_ProductType}&TankNum=${Repertory_TankNum}
																&ShiftNo=${Repertory_ShiftNo}&AddTime=${repertory_AddTime}
																&startDate=${startDate}&endDate=${endDate}">删除</a>
														
														<s:set name="Repertory_IsSync" value='repertory[#st.index][0].repertory_IsSync'></s:set>
														<s:set name="Repertory_TotalVol" value='repertory[#st.index][0].repertory_TotalVol'></s:set>	
														<c:if test="${Repertory_IsSync==2}">
															<a
																href="bos/signRepertory.action?StationNo=${Station.station_No}
																&ProductType=${Repertory_ProductType}&TankNum=${Repertory_TankNum}
																&ShiftNo=${Repertory_ShiftNo}&AddTime=${repertory_AddTime}
																&startDate=${startDate}&endDate=${endDate}" onclick="javascript:return ConfirmDel();">
																签核</a> | 
															<c:if test="${Repertory_ProductType==0}">
															<a
																href="bos/RepertoryManage/updateOilRepertory.jsp?ProductNum=${Repertory_ProductNum}
																&ProductName=${product_Name}&ShiftNo=${Repertory_ShiftNo}&TankNum=${Repertory_TankNum}
																&ProductVol=${repertory_ProductVol}&Temp=${repertory_Temp}&Density=${repertory_Density}
																&WaterLevel=${repertory_WaterLevel}&PetrolLevel=${repertory_PetrolLevel}
																&TotalVol=${repertory_ProductVol}&Remark=${repertory_Remark}&AddTime=${repertory_AddTime}">
																修改</a>	
															</c:if>	
															<c:if test="${Repertory_ProductType==1}">
															<a
																href="bos/RepertoryManage/updateGasRepertory.jsp?ProductNum=${Repertory_ProductNum}
																&ProductName=${product_Name}&ShiftNo=${Repertory_ShiftNo}&TankNum=${Repertory_TankNum}
																&ProductVol=${repertory_ProductVol}&Temp=${repertory_Temp}&Density=${repertory_Density}
																&Pressure=${repertory_Pressure}&Remark=${repertory_Remark}&AddTime=${repertory_AddTime}">
																修改</a>	

															</c:if>																							
															
														</c:if>
														
														</td>
														-->
													</tr>
												</s:iterator>

											</table>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="6">
											<img src="./images/bos/spacer.gif" width="1" height="1" />
										</td>
									</tr>
									<tr>
										<td height="33">
											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="0" class="right-font08">
												<tr>
													<td width="40%" class="right-font08">
														共
														<span class="right-text09">${page.pages}</span> 页 | 第
														<span class="right-text09">${page.pageNum}</span> 页 | 每页显示
														<span class="right-text09">${page.pageSize}</span> 条数据
														<input id="pages" type="hidden" value="${page.pages}">
													</td>
													<td width="40%" align="right">
														[
														<a
															href="bos/selRepertory.action?ShiftNo=${ShiftNo}&ProductType=${ProductType}&page.pageNum=1&startDate=${startDate}&endDate=${endDate}"
															class="right-font08">首页</a> |
														<a
															href="bos/selRepertory.action?ShiftNo=${ShiftNo}&ProductType=${ProductType}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selRepertory.action?ShiftNo=${ShiftNo}&ProductType=${ProductType}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selRepertory.action?ShiftNo=${ShiftNo}&ProductType=${ProductType}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selRepertory.action" onsubmit="return chenckPageNum();">
														<td width="7%" class="right-font08">
															<input type="hidden" name="ProductType" value="${ProductType}">
															转至：
															<input id="to_PageNum" name="to_PageNum" type="text"
																class="right-textfield03" size="1" />
															<input value="${startDate}" name="startDate"
																type="hidden">
															<input value="${endDate}" name="endDate" type="hidden">
															<input type="hidden" name="ShiftNo" value="${ShiftNo}"/>
														</td>
														<td width="3%">
															<input type="submit" class="right-button06" value="" />
														</td>
													</form>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="70">
											
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
