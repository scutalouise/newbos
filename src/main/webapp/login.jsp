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

		<title>后台管理系统登录</title>

	</head>

	<body>
	<c:if test="${session.LoginStaff  != null}">
	<script type="text/javascript">
		location.href = "./bos/Login_bos.action";
	</script>
	</c:if>
		<table width="1280" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td height="8%">
					<img src="images/login/loginhead.png" height="70" width="1280"></img>
				</td>
			</tr>
			<tr>
				<td height="92%" align="center" bgcolor="#88b9fc">
					<table width="400" height="254" border="0" cellspacing="0"
						cellpadding="0" bgcolor="#f2f2f2">
						<tr height="22%">
							<td>
								<img src="images/login/logintableTop.png" height="55" width="400"></img>
							</td>
						</tr>
						<tr height="78%">
							<td>
								<form action="./bos/Login_bos.action" method="post">
									<table width="210" align="center">
										<tr height="20">
											<td colspan="2" align="center">
												<span style="font-size: 12px; color: red;">${msg}</span>
											</td>
										</tr>
										<tr height="50">
											<td colspan="2">
												<input name="StaffNo" type="text"
													style="width: 200px; height: 30px;text-indent:30px;background: #d6d6d6; background-image: url(images/login/admin.png); background-repeat: no-repeat; line-height: 30px; font-size: 18px; border: solid 1px #d6d6d6;" />
											</td>
										</tr>
										<tr height="50">
											<td colspan="2">
												<input name="StaffPW" type="password"
													style="width: 200px; height: 30px;text-indent:30px;background: #d6d6d6; background-image: url(images/login/pas.png); background-repeat: no-repeat; line-height: 30px; font-size: 18px; border: solid 1px #d6d6d6;" />
											</td>
										</tr>
										<tr height="70">
											<td align="left">
												<input type="submit" value=""
													style="width: 70px; height: 33px; background-image: url(images/login/submit.png);background-repeat: no-repeat; border: none;" />
											</td>
											<td align="right">
												<input type="reset" value=""
													style="width: 70px; height: 33px; background-image: url(images/login/reset.png); background-repeat: no-repeat; border: none;" />
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

