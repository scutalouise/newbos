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
	<script language="javascript"
			src="${pageContext.request.contextPath}/js/orderQueryType.js"
			type="text/javascript">
</script>
	<script type="text/javascript">
		function ConfirmDel(){
		   if(confirm("你确定要执行此操作？"))
		     return true;
		   else
		     return false;
		}
		function checkform(){
			var StationNo=document.getElementById("StationNo").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
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
							<td id="templateRegion" style="display: none;">
								<input value="${QueryContext}" name="QueryContext" class="runcode"
												id="queryContextInput" size="20" style="height: 19px;"/>
								<select id="queryContextSelect" name="QueryContext">
                            		<option value="0">否</option>
                            		<option value="1">是</option>
                            	</select>
							</td>
						</tr>
						<tr>
							<td height="45" bgcolor="#7fb5ec">
								<form action="bos/selOrderBill.action" onsubmit="return checkform();">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="margin-top: 5px;">
										<tr>
											<td width="4%">
												<img src="./images/bos/ico07.png" width="20" height="18" />
											</td>
											<td width="96%">
											<div style="color: white;font-family:'黑体';font-size: 13px;display: inline;" id="queryBox">
											<input id="StationNo" name='StationNo' type="hidden" value="${Station.station_No}"/>
												创建订单时间：
												<input value="${startDate}" name="startDate" class="runcode"
												id="start" size="12" readonly="readonly" onClick="WdatePicker()"/>
												<span style="color: white;font-family:'黑体';font-size: 13px;">至</span>		
												<input value="${endDate}" name="endDate" class="runcode"
												id="end" size="12" readonly="readonly" onClick="WdatePicker()"/>
												单据状态：
												<select id="Status" size="1" name="Status" style="width: 80px;">
                               	 						<c:if test="${Status==-1||Status==null||''==Status}">	
                               	 							<option value="-1" selected="selected">全部</option>
                               	 						<!-- 	<option value="00">处理中 </option>
                               	 							<option value="02">已确认</option>-->
                               	 							<option value="04">订单取消 </option> 
                               	 							<option value="01">已定货</option>
                               	 							<option value="06">卸油中</option>
                               	 							<option value="05">已卸油</option>
                               	 							<option value="03">已交货</option>
                               	 							
                               	 						</c:if><%--
                               	 						<c:if test="${Status=='00'}">	
                               	 							<option value="-1" >全部</option>
                               	 							<option value="00" selected="selected">处理中 </option>
                               	 							<option value="01">已定货</option>
                               	 							<option value="02">已确认</option>
                               	 							<option value="06">卸油中</option>
                               	 							<option value="05">已卸油</option>
                               	 							<option value="03">已交货</option>
                               	 							<option value="04">订单取消 </option>
                               	 						</c:if>
                               	 						--%><c:if test="${Status=='01'}">	
                               	 							<option value="-1">全部</option>
                               	 						<!--  	<option value="00">处理中 </option>
                               	 							<option value="02">已确认</option>-->
                               	 							<option value="04">订单取消 </option>
                               	 							<option value="01" selected="selected">已定货</option>
                               	 							<option value="06">卸油中</option>
                               	 							<option value="05">已卸油</option>
                               	 							<option value="03">已交货</option>
                               	 						</c:if>
                               	 						<%--<c:if test="${Status=='02'}">	
                               	 							<option value="-1" >全部</option>
                               	 							<option value="00">处理中 </option>
                               	 							<option value="01">已定货</option>
                               	 							<option value="02" selected="selected">已确认</option>
                               	 							<option value="06">卸油中</option>
                               	 							<option value="05">已卸油</option>
                               	 							<option value="03">已交货</option>
                               	 							<option value="04">订单取消 </option>
                               	 						</c:if>
                               	 						--%><c:if test="${Status=='06'}">	
                               	 							<option value="-1" >全部</option>
                               	 						<%--<option value="00">处理中 </option>
                               	 							<option value="02">已确认</option>--%>
                               	 							<option value="04">订单取消 </option>
                               	 							<option value="01">已定货</option>
                               	 							<option value="06" selected="selected">卸油中</option>
                               	 							<option value="05">已卸油</option>
                               	 							<option value="03" >已交货</option>
                               	 						</c:if>
                               	 						<c:if test="${Status=='05'}">	
                               	 							<option value="-1" >全部</option>
                               	 							<%--<option value="00">处理中 </option>
                               	 							<option value="02">已确认</option>--%>
                               	 							<option value="04">订单取消 </option>
                               	 							
                               	 							<option value="01">已定货</option>
                               	 							<option value="06">卸油中</option>
                               	 							<option value="05" selected="selected">已卸油</option>
                               	 							<option value="03">已交货</option>
                               	 							
                               	 						</c:if>
                               	 						<c:if test="${Status=='03'}">	
                               	 							<option value="-1" >全部</option>
                               	 							<%--<option value="00">处理中 </option>
                               	 							<option value="02">已确认</option>--%>
                               	 							<option value="04">订单取消 </option>
                               	 							
                               	 							<option value="01">已定货</option>
                               	 							<option value="06">卸油中</option>
                               	 							<option value="05">已卸油</option>
                               	 							<option value="03" selected="selected">已交货</option>
                               	 							
                               	 						</c:if>
                               	 						<c:if test="${Status=='04'}">	
                               	 							<option value="-1" >全部</option>
                               	 						<%--<option value="00">处理中 </option>
                               	 							<option value="02">已确认</option>--%>
                               	 							<option value="01">已定货</option>
                               	 							<option value="06">卸油中</option>
                               	 							<option value="05">已卸油</option>
                               	 							<option value="03" >已交货</option>
                               	 							<option value="04" selected="selected">订单取消 </option>
                               	 						</c:if>
                               	 					</select>
                            					&nbsp;&nbsp;
												||
												&nbsp;&nbsp;
												<select id="QueryType" size="1" name="QueryType" style="width: 80px;margin-right:5px;" onclick="getFindType();">
													<c:if test="${QueryType=='0'||null==QueryType||''==QueryType}">
														<option value="0" selected="selected">全部</option>
	                       	 							<option value="1">订单号</option>
	                       	 							<option value="2">产品名称</option>
	                       	 							<option value="3">罐号</option>
	                       	 							<option value="4">是否补录</option>
													</c:if>
                       	 							<c:if test="${QueryType=='1'}">
														<option value="0">全部</option>
	                       	 							<option value="1" selected="selected">订单号</option>
	                       	 							<option value="2">产品名称</option>
	                       	 							<option value="3">罐号</option>	
														<option value="4">是否补录</option>	
													</c:if>	
													<c:if test="${QueryType=='2'}">
														<option value="0">全部</option>
	                       	 							<option value="1">订单号</option>
	                       	 							<option value="2" selected="selected">产品名称</option>
	                       	 							<option value="3">罐号</option>
	                       	 							<option value="4">是否补录</option>		
													</c:if>	
													<c:if test="${QueryType=='3'}">
														<option value="0">全部</option>
	                       	 							<option value="1">订单号</option>
	                       	 							<option value="2">产品名称</option>
	                       	 							<option value="3" selected="selected">罐号</option>
														<option value="4">是否补录</option>	
													</c:if>	
													<c:if test="${QueryType=='4'}">
														<option value="0">全部</option>
	                       	 							<option value="1">订单号</option>
	                       	 							<option value="2">产品名称</option>
	                       	 							<option value="3" >罐号</option>
														<option value="4" selected="selected">是否补录</option>	
													</c:if>		                          	 							
                            					</select>
												&nbsp;&nbsp;
												<input type="submit" class="right-button02"
													value="查 询" />
												&nbsp;&nbsp;
												<%--<input type="button" class="right-button08" value="创建订单" 
												onclick="location.href='<%=basePath%>bos/createOrderBillNum.action?StationNo=${Station.station_No}'"/>
												--%>
												<input type="button" class="right-button08" value="创建订单" 
												onclick="location.href='<%=basePath%>bos/createOrderBillNum.action?StationNo=${Station.station_No}'"/>
												</div>
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
													<td height="35" colspan="12" align="center"
														style="font-size: 16px">
														站点订单详情
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													<td width="4%" align="center" height="35">
														订单号
													</td>
													<td width="3%" align="center">
														产品类型
													</td>
													<td width="4%" align="center">
														产品名称
													</td>
													<td width="2%" align="center">
														罐号
													</td>
													<td width="5%" align="center">
														补货量
													</td>
													<td width="3%" align="center">
														单据状态
													</td>
													<td width="6%" align="center">
														创建订单时间
													</td>
													<td width="6%" align="center">
														到达时间
													</td>
													<!--  
													<td width="6%" align="center">
														确认订单时间
													</td>
													-->
													<td width="5%" align="center">
														订货量
													</td>
													<!--
													<td width="6%" align="center">
														实际订货时间
													</td>
													<td width="6%" align="center">
														预计到达时间
													</td>
													  -->
													<td width="5%" align="center">
														操作
													</td>
												</tr>
												<s:iterator value="OrderBill">
													<tr bgcolor="#FFFFFF">
														<td height="20" align="center">
															${OrderBill_NumAliases}
														</td>
														<td align="center">
														<c:if test="${OrderBill_ProductType==0}">
															油品
														</c:if>
														<c:if test="${OrderBill_ProductType==1}">
															气品
														</c:if>
														</td>
														<td align="center">	
															<s:iterator value="product">
																<c:if test="${OrderBill_ProductNum==Product_Num}">
																	${Product_Name}
																</c:if>
															</s:iterator>
														</td>
														<td align="center">
															${OrderBill_TankNum}
														</td>
														<td align="center">
														<c:if test="${OrderBill_AddVolumeM>0}">
																${OrderBill_AddVolumeM}(m³)
														</c:if>
														<c:if test="${OrderBill_AddVolumeL>0}">
																${OrderBill_AddVolumeL}(L)
														</c:if>
														<c:if test="${OrderBill_AddWeight>0}">
																${OrderBill_AddWeight}(t)
														</c:if>
														</td>
														<td align="center">
														<c:if test="${OrderBill_Status=='00'}">
																处理中
														</c:if>
														<c:if test="${OrderBill_Status=='01'}">
																已定货
														</c:if>
														<c:if test="${OrderBill_Status=='02'}">
																已确认
														</c:if>
														<c:if test="${OrderBill_Status=='03'}">
																已交货
														</c:if>
														<c:if test="${OrderBill_Status=='04'}">
																订单取消
														</c:if>
														<c:if test="${OrderBill_Status=='05'}">
																已卸油
														</c:if>
														<c:if test="${OrderBill_Status=='06'}">
																卸油中
														</c:if>	
														</td>
														<td align="center">
															<s:date format="yyyy-MM-dd hh:mm:ss"  name="OrderBill_CreateDate"/>
														</td>
														<td align="center">
															<s:date format="yyyy-MM-dd hh:mm:ss"  name="OrderBill_ExpectDate"/>
														</td>
														<!--  
														<td align="center">
															<s:date format="yyyy-MM-dd hh:mm:ss"  name="OrderBill_AckDate"/>
														</td>
														-->
														<td align="center">
															<c:if test="${OrderBill_ActVolumeM>0}">
																${OrderBill_ActVolumeM}(m³)
															</c:if>
															<c:if test="${OrderBill_ActVolumeL>0}">
																${OrderBill_ActVolumeL}(L)
															</c:if>
															<c:if test="${OrderBill_ActWeight>0}">
																${OrderBill_ActWeight}(t)
															</c:if>
														</td>
														<td align="center">
															<c:if test="${OrderBill_Status=='01'}">
																<a href="bos/cancelOrderBill.action?OrderBillNum=${OrderBill_Num}&NumAliases=${OrderBill_NumAliases}
																				&startDate=${startDate}&endDate=${endDate}&QueryType=1
																				&QueryContext=${OrderBill_Num}&Status=${Status}" 
																	onclick="javascript:return ConfirmDel();">取消</a>
															</c:if>
														</td>
														<!--  
														<td align="center">
															<s:date format="yyyy-MM-dd hh:mm:ss"  name="OrderBill_ActOrderDate"/>
														</td>
														<td align="center">
															<s:date format="yyyy-MM-dd hh:mm:ss"  name="OrderBill_ActPlanDate"/>
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
															href="bos/selOrderBill.action?page.pageNum=1&startDate=${startDate}&endDate=${endDate}&QueryType=${QueryType}&QueryContext=${QueryContext}&Status=${Status}"
															class="right-font08">首页</a> |
														<a
															href="bos/selOrderBill.action?QueryType=${QueryType}&QueryContext=${QueryContext}&Status=${Status}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selOrderBill.action?QueryType=${QueryType}&QueryContext=${QueryContext}&Status=${Status}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selOrderBill.action?QueryType=${QueryType}&QueryContext=${QueryContext}&Status=${Status}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selOrderBill.action" onsubmit="return chenckPageNum();">
														<td width="7%" class="right-font08">
														<input type="hidden" name="QueryType" value="${QueryType}">
														<input type="hidden" name="QueryContext" value="${QueryContext}">
														<input type="hidden" name="Status" value="${Status}">
															转至：
															<input id="to_PageNum" name="to_PageNum" type="text"
																class="right-textfield03" size="1" />
															<input value="${startDate}" name="startDate"
																type="hidden">
															<input value="${endDate}" name="endDate" type="hidden">
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
										<td height="50">
											
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
	$(function(){
		$("#queryBox").find("[name='QueryContext']").val('${QueryContext}');
	})
	
</script>
	</body>
</html>
