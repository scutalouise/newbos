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
	<script type="text/javascript">
		function ConfirmDel(){
		   if(confirm("您确定要签核当前能耗信息吗？签核后将上传总部，不可修改！"))
		     return true;
		   else
		     return false;
		}
		
		
	</script>
	</head>
	<body bgcolor="#f4f4f4">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
				
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" background="./images/bos/nav04.gif">
							<form action="bos/selSourcePlan.action">
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0" style="margin-top: 5px;">
									<tr>
										<td width="4%">
											<img src="./images/bos/ico07.gif" width="20" height="18" />
										</td>
										<td width="96%">
										<span style="color: white;font-family:'黑体';font-size: 13px;">										
											时间：
										</span>
										<input value="${startDate}" name="startDate" class="runcode"
											id="start" size="12" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>
										<span style="color: white;font-family:'黑体';font-size: 13px;">至</span>		
										<input value="${endDate}" name="endDate" class="runcode"
											id="end" size="12" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
											<input name="Submit4" type="submit" class="right-button02"
												value="查 询" />
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
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="35" colspan="6" align="center"
														style="font-size: 16px">
														能耗指标详情
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="12%" align="center" height="35">
														时间
													</td>
													<td width="12%" align="center">
														电耗指标（kw/h）<br/>
													</td>
													<td width="12%" align="center">
														水耗指标（m³）<br/>
													</td>
												</tr>
												<s:iterator value="SourcePlan">
												<tr bgcolor="#FFFFFF">
													<td height="20" align="center">
														${SourceUsed_TheDate}
													</td>
													<td align="center">	
														${SourceUsed_MonthlyElec}			
													</td >
													<td align="center">
														${SourceUsed_MonthlyWater}
													</td>
												</tr>
												</s:iterator>
												
											</table>
										</td>
									</tr>
								</table>
								<table width="80%" border="0" align="center" cellpadding="0"
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
													<td width="40%">
														共
														<span class="right-text09">${page.pages}</span> 页 | 第
														<span class="right-text09">${page.pageNum}</span> 页 | 每页显示
														<span class="right-text09">${page.pageSize}</span> 条数据
														<input id="pages" type="hidden" value="${page.pages}">
													</td>
													<td width="35%" align="right">
														[
														<a href="bos/selSourcePlan.action?page.pageNum=1&startDate=${startDate}&endDate=${endDate}"
															class="right-font08">首页</a> |
														<a
															href="bos/selSourcePlan.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selSourcePlan.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selSourcePlan.action?startDate=${startDate}&endDate=${endDate}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selSourcePlan.action" onsubmit="return chenckPageNum();">
													<td width="8%">
														转至：<input id="to_PageNum" name="to_PageNum" type="text"
															class="right-textfield03" size="1" />&nbsp;
															<input value="${startDate}" name="startDate" type="hidden">
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

	</body>
</html>
