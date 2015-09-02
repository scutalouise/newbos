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
	<script type="text/javascript">
		function ConfirmDel(){
		   if(confirm("您确定要签核当前能耗信息吗？签核后将上传总部，不可修改！"))
		     return true;
		   else
		     return false;
		}
	</script>
	</head>
	<body bgcolor="#c6dffc">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="45">

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" bgcolor="#7fb5ec">
								<form action="bos/selProductSupplier.action">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="margin-top: 5px;">
										<tr>
											<td width="4%">
												<img src="./images/bos/ico07.png" width="20" height="18" />
											</td>
											<td width="96%">
											<span style="color: white;font-family:'黑体';font-size: 13px;">
												供应商名称：
											</span>
												<input value="${Name}" name="Name" class="runcode"
												id="start" size="20"/>	
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="submit" class="right-button02"
													value="查 询" />
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="button" class="right-button08" value="添加供应商信息" 
													onclick="location.href='${pageContext.request.contextPath}/bos/IntegratedWork/addSupplier.jsp'"/>
										
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
													<td height="35" colspan="9" align="center"
														style="font-size: 16px">
														供应商信息
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													<td width="9%" align="center" height="35">
														供应商名称
													</td>
													<td width="10%" align="center">
														供应商电话（座机）
													</td>
													<td width="10%" align="center">
														供应商电话（手机）
													</td>
													<td width="10%" align="center">
														供应商传真
													</td>
													<td width="15%" align="center">
														供应商地址
													</td>
													<td width="10%" align="center">
														备注
													</td>
													<td width="5%" align="center">
														操作
													</td>
												</tr>
												<s:iterator value="Supplier" status="st">
													<tr bgcolor="#FFFFFF">
														<td align="center" height="20">
															<s:set name="ProductSupplier_Name" value='Supplier[#st.index][0].ProductSupplier_Name'></s:set>
															${ProductSupplier_Name}
														</td>
														<td align="center">
															<s:set name="ProductSupplier_Tel" value='Supplier[#st.index][0].ProductSupplier_Tel'></s:set>
															${ProductSupplier_Tel}
														</td>
														<td align="center">
															<s:set name="ProductSupplier_Cell" value='Supplier[#st.index][0].ProductSupplier_Cell'></s:set>
															${ProductSupplier_Cell}
														</td>
														<td align="center">
															<s:set name="ProductSupplier_Fax" value='Supplier[#st.index][0].ProductSupplier_Fax'></s:set>
															${ProductSupplier_Fax}
														</td>
														<td align="center">
															<s:set name="ProductSupplier_Address" value='Supplier[#st.index][0].ProductSupplier_Address'></s:set>
															${ProductSupplier_Address}
														</td>
														<td align="center">
															<s:set name="ProductSupplier_Remark" value='Supplier[#st.index][1].ProductSupplier_Remark'></s:set>
															${ProductSupplier_Remark}
														</td>
														<td align="center">
															<a
																href="${pageContext.request.contextPath}/bos/IntegratedWork/updateSupplier.jsp?
																Name=${ProductSupplier_Name}
																&Tel=${ProductSupplier_Tel}&Cell=${ProductSupplier_Cell}
																&Fax=${ProductSupplier_Fax}&Address=${ProductSupplier_Address}
																&Remark=${ProductSupplier_Remark}&SupplierKey=${ProductSupplier_key}">修改</a>
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
														<a
															href="bos/selProductSupplier.action?page.pageNum=1&startDate=${startDate}&endDate=${endDate}"
															class="right-font08">首页</a> |
														<a
															href="bos/selProductSupplier.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selProductSupplier.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selProductSupplier.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selProductSupplier.action" onsubmit="return chenckPageNum();">
														<td width="7%" class="right-font08">
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
