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
								<form action="bos/selShiftVerify.action">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="margin-top: 5px;">
										<tr>
											<td width="4%">
												<img src="./images/bos/ico07.png" width="20" height="18" />
											</td>
											<td width="96%">
											<span style="color: white;font-family:'黑体';font-size: 13px;">
												开班时间：
											</span>
											<input value="${startDate}" name="startDate" class="runcode"
												id="start" size="12" readonly="readonly" onClick="WdatePicker()"/>
											<span style="color: white;font-family:'黑体';font-size: 13px;">至</span>		
											<input value="${endDate}" name="endDate" class="runcode"
												id="end" size="12" readonly="readonly" onClick="WdatePicker()"/>
											&nbsp;&nbsp;&nbsp;&nbsp;
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
														班结审核情况
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													<td width="6%" align="center" height="35">
														开班日期
													</td>
													<td width="4%" align="center">
														班别编号
													</td>
													<td width="5%" align="center">
														开班员工
													</td>
													<td width="8%" align="center">
														开班时间
													</td>
													<td width="8%" align="center">
														接班时间
													</td>
													<td width="4%" align="center">
														审核状态
													</td>
													<td width="12%" align="center">
														操作<br/>(当前查询中有<span style="color: red;"> ${UnSignCount} </span>条班别未审核)
													</td>
												</tr>
												<s:iterator value="shiftDetail" status="st">
													<tr bgcolor="#FFFFFF">
														<td align="center" height="20">
															<s:set name="Shift_ShiftDate" value='shiftDetail[#st.index][0]'></s:set>
															${Shift_ShiftDate}
														</td>
														<td align="center">
															<s:set name="Shift_ShiftNo" value='shiftDetail[#st.index][1]'></s:set>
															${Shift_ShiftNo}
														</td>
														<td align="center">
															<s:set name="StaffName1" value='shiftDetail[#st.index][2]'></s:set>
															${StaffName1}
														</td>
														<td align="center">
															<s:set name="Shift_StartTime" value='shiftDetail[#st.index][3]'></s:set>
															<fmt:formatDate value='${Shift_StartTime}' type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
														</td>
														<td align="center">
															<s:set name="Shift_EndTime" value='shiftDetail[#st.index][4]'></s:set>
															<fmt:formatDate value='${Shift_EndTime}' type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
															
														</td>
														<td align="center">
															<s:set name="Shift_VerifyStatus" value='shiftDetail[#st.index][5]'></s:set>
															<s:if test="shiftDetail[#st.index][5]=='1'">
																审核通过
															</s:if>
															<s:set name="Shift_CreateTime" value='shiftDetail[#st.index][6]'></s:set>
														</td>
														
														<%-- <td align="center">
															<s:set name="Shift_VerifyTime" value='shiftDetail[#st.index][7]'></s:set>
															<fmt:formatDate value='${Shift_VerifyTime}' type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
															<!-- 创建时间010101 -->
															<s:set name="Shift_CreateTime" value='shiftDetail[#st.index][8]'></s:set>
															
														</td> --%>
														<td align="center">
														<s:if test="shiftDetail[#st.index][5] == '0'">
															<s:if test="shiftDetail[#st.index][4]!=NULL">
															<a
																href="bos/BusinessMsgManage/selShiftNozzle.action?
																		startDate=${startDate}&endDate=${endDate}
																		&ShiftDate=${Shift_ShiftDate}&ShiftNo=${Shift_ShiftNo}
																		&Shift_StartTime=${Shift_StartTime}&Shift_EndTime=${Shift_EndTime}
																		&Shift_CreateTime=${Shift_CreateTime}">进行审核</a>								
															</s:if>
														</s:if>
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
															href="bos/selShiftVerify.action?page.pageNum=1&startDate=${startDate}&endDate=${endDate}"
															class="right-font08">首页</a> |
														<a
															href="bos/selShiftVerify.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selShiftVerify.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selShiftVerify.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selShiftVerify.action" onsubmit="return chenckPageNum();">
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
