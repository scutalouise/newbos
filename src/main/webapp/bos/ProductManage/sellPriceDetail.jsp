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

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
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
	</head>
	<body bgcolor="#c6dffc">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" bgcolor="#7fb5ec">

								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="45" bgcolor="#7fb5ec">
										<form action="selSellPrice.action">
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0" style="margin-top: 5px;">
												<tr>
													<td width="4%">
														<img src="./images/bos/ico07.png" width="20" height="18" />
													</td>
													<td width="96%">
													<span style="color: white;font-family:'黑体';font-size: 13px;">
														变价时间：
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
		                               	 							<!--  <option value="1"selected="selected">气品</option>-->
		                               	 						</c:if>                          	 							
		                            					</select>
		                            					&nbsp;&nbsp;&nbsp;&nbsp;
		                            					<span style="color: white;font-family:'黑体';font-size: 13px;">
		                            					产品名称：
		                            					</span>
		                            					<input type="text" name="ProductName" value="${ProductName}"/>
		                            					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="submit" class="right-button02"
															value="查 询" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			<tr>
				<td>
					<table id="subtree1" style="DISPLAY: " width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="20"></td>
									</tr>

									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<tr class="CTitle">
													<td height="35" colspan="6" align="center"
														style="font-size: 16px">
														产品变价详情
													</td>
												</tr>
												<tr bgcolor="#f9f9f9" align="center">
												<!--  
													<td width="14%" align="center" height="30">
														站点名称
													</td>
												-->
													<td width="14%" height="30">
														产品名称
													</td>
													<td width="14%" height="30">
														枪号
													</td>
													<td width="14%">
														变价时间
													</td>
													<td width="14%">
														生效时间
													</td>
													<td width="14%">
														新单价
													</td>
													<td width="14%">
														变价结果
													</td>
												</tr>
												<s:iterator value="SellPrice" status="st">
													<tr bgcolor="#FFFFFF" align="center" height="20">
													<!--  
														<td height="20">
															${Station.station_Name}
														</td>
													-->
														<td>
															<s:property value="SellPrice[#st.index][0].product_Name"/>
														</td>
														<td>
															<s:property value="SellPrice[#st.index][1].posSetPriceRecord_Pump"/>
														</td>
														<td>
															<s:date format="yyyy-MM-dd HH:mm:ss"  name="SellPrice[#st.index][1].PosSetPriceRecord_ChDate"/>
														</td>
														<td height="20">
															<s:date format="yyyy-MM-dd HH:mm:ss"  name="SellPrice[#st.index][1].PosSetPriceRecord_EffDate"/>
														</td>
														<td>
															<s:property value="SellPrice[#st.index][1].PosSetPriceRecord_SellPrice"/>
														</td>
														<td>
															<s:set name="Result" value='SellPrice[#st.index][1].PosSetPriceRecord_Result'/>
															<c:if test="${Result=='99'}">
																成功					
															</c:if>
															<c:if test="${Result=='35'}">
																失败（油品号不匹配）				
															</c:if>
															<c:if test="${Result=='36'}">
																失败（油机变价超时）				
															</c:if>
															<c:if test="${Result=='37'}">
																失败（油机变价失败）				
															</c:if>
														</td>			
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
														<a href="bos/selSellPrice.action?startDate=${startDate}&endDate=${endDate}&ProductName=${ProductName}&ProductType=${ProductType}&page.pageNum=1"
															class="right-font08">首页</a> |
														<a
															href="bos/selSellPrice.action?startDate=${startDate}&endDate=${endDate}&ProductName=${ProductName}&ProductType=${ProductType}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selSellPrice.action?startDate=${startDate}&endDate=${endDate}&ProductName=${ProductName}&ProductType=${ProductType}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selSellPrice.action?startDate=${startDate}&endDate=${endDate}&ProductName=${ProductName}&ProductType=${ProductType}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selSellPrice.action" onsubmit="return chenckPageNum();">
													<td width="7%" class="right-font08">
														转至：<input id="to_PageNum" name="to_PageNum" type="text"
															class="right-textfield03" size="1" />&nbsp;
															<input value="${startDate}" name="startDate" type="hidden">
															<input value="${endDate}" name="endDate" type="hidden">
															<input value="${ProductName}" name="ProductName" type="hidden">
															<input value="${ProductType}" name="ProductType" type="hidden">
													</td>
													<td width="3%">
														<input name="Submit23222" type="submit"
															class="right-button06" value="" />
													</td>
													</form>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="100">
											
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
