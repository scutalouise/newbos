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
html { overflow-x: auto; overflow-y: auto; border:0;} 
</style>
		<link href="css/bos.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js">
		</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/page.js"
			type="text/javascript"></script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/validate.js"
			type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/repertory.js"></script>
		<script type="text/javascript">
		function ConfirmVerify(){
		   if(confirm("你确定要执行‘确认’操作？确认后，将不能修改|删除。"))
		     return true;
		   else
		     return false;
		}
		function ConfirmDel(){
		   if(confirm("你确定要执行‘删除’操作？删除后，不可恢复。"))
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
							<td height="45"  bgcolor="#7fb5ec">
							<form action="bos/selDensity.action">
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0" style="margin-top: 5px;">
									<tr>
										<td width="4%">
											<img src="./images/bos/ico07.png" width="20" height="18" />
										</td>
										<td width="96%">	
										<span style="color: white;font-family:'黑体';font-size: 13px;">									
											<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>
											产品类型：
											<select id="ProductType" size="1" name="ProductType" style="width:50px;">
                              	 					<option value="0" selected="selected">油品</option>
                              	 					<!-- <option value="1" >气品</option> -->
                           					</select>
                           					&nbsp;&nbsp;&nbsp;&nbsp;
                           					<select id="ProductNum" size="1" name="Density_ProdunctNum" style="width: 80px;" onfocus="getProdunctList();">
												<!-- 加载产品名称，选择气品或者油品时添加 -->							
                           					</select>
										</span>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="Submit4" type="submit" class="right-button02"
												value="查 询" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="right-button08" value="新增产品密度" 
											onclick="location.href='<%=basePath%>bos/PurchaseManage/addDensity.jsp'"/>
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
								<table width="80%" border="0" align="center" cellpadding="0"
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
													<td height="35" colspan="6" align="center"
														style="font-size: 16px">
														产品密度详情
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													<td width="12%" align="center">
														产品名称<br/>
													</td>
													<td width="12%" align="center">
														密度<br/>
													</td>
													<td width="12%" align="center" height="35">
														开始时间
													</td>
													<td width="12%" align="center" height="35">
														结束时间
													</td>
													<td width="8%" align="center">
														操作<br/>
													</td>
												</tr>
												<s:iterator value="DensityList" status="st">
												<tr bgcolor="#FFFFFF">
													<td height="20" align="center">
													<s:set name="Product_Name" value='DensityList[#st.index][0]'></s:set>	
														${Product_Name}
													</td>
													<td align="center">
													<s:set name="Density_Density" value='DensityList[#st.index][1]'></s:set>		
														${Density_Density}			
													</td >
													<td align="center">
													<s:set name="Density_startDate" value='DensityList[#st.index][2]'></s:set>	
														${fn:substring(Density_startDate,0,19)}
													</td>
													<td align="center">
													<s:set name="Density_endDate" value='DensityList[#st.index][3]'></s:set>	
														${fn:substring(Density_endDate,0,19)}
													</td>
													<td align="center">
													<s:set name="Product_Num" value='DensityList[#st.index][4]'></s:set>
													<a href="./bos/PurchaseManage/updateDensity.jsp?StationNo=${Station.station_No}
															&Density_Density=${Density_Density}
															&Density_startDate=${Density_startDate}&Density_endDate=${Density_endDate}&Product_Num=${Product_Num}
															&Product_Name=${Product_Name}">
													修改</a>	
														|
													<a href="<%=basePath%>bos/delDensity.action?StationNo=${Station.station_No}&Density_Density=${Density_Density}
															&Density_startDate=${Density_startDate}&Density_endDate=${Density_endDate}&Density_ProdunctNum=${Product_Num}
															&Product_Name=${Product_Name}" onclick="javascript:return ConfirmDel();">
													删除</a>
													
													</td>
												</tr>
												</s:iterator>
												
											</table>
										</td>
									</tr>
								</table>
								<table width="85%" border="0" align="center" cellpadding="0"
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
														<a href="bos/selSellingPlan1.action?ProductName=${ProductName}&page.pageNum=1&startDate=${startDate}&endDate=${endDate}"
															class="right-font08">首页</a> |
														<a
															href="bos/selSellingPlan1.action?ProductName=${ProductName}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selSellingPlan1.action?ProductName=${ProductName}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selSellingPlan1.action?ProductName=${ProductName}&startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selSellingPlan1.action" onsubmit="return chenckPageNum();">
													<td width="8%" class="right-font08">
														转至：<input id="to_PageNum" name="to_PageNum" type="text"
															class="right-textfield03" size="1" />&nbsp;
															<input value="${startDate}" name="startDate" type="hidden">
															<input value="${endDate}" name="endDate" type="hidden">
															<input value="${ProductName}" name="ProductName" type="hidden">
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

	</body>
</html>
