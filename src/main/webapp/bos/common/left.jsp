<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<link href="css/bos.css" rel="stylesheet" type="text/css" />
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript">
</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/left.js"
			type="text/javascript">
</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/dtree.js"
			type="text/javascript">
</script>

		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
	</head>

	<body bgcolor="#055ab0">

		<table width="198" border="0" cellpadding="0" cellspacing="0"
			class="left-table01">
			<tr>
				<TD>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="207" height="43" bgcolor="#055ab0">
								<table width="90%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
									<td width="100%" height="22" align="center">
											<span class="left-font02">${Station.station_Name}</span><br/>
											<span class="left-font02">管理员：${LoginStaff.staff_Name}</span>
											&nbsp;&nbsp;
											<input type="hidden" name="StaffNo" id="StaffNo"
												value="${LoginStaff.staff_No}" />
											<input type="hidden" name="StaffPW" id="StaffPW"
												value="${LoginStaff.staff_PW}" />
										</td>
									</tr>
									
								</table>
							</td>
						</tr>
					</table>
					<!-- Left功能开始 -->
						<div id="left"></div>
					<!-- Left功能结束 -->
				</TD>
			</tr>
		</table>
		<table height="1500" width="198" border="0" cellpadding="0"
			cellspacing="0" class="left-tableLine">
			<tr>
				<TD>
				</TD>
			</tr>
		</table>
	</body>
</html>
