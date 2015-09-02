<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
	<head>
		<base href="<%=basePath%>">

		<title>权限管理系统登录</title>

<style type="text/css">
.STYLE3 {font-size: 12px; color: #adc9d9; }
</style>
<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript"></script>
	</head>

	<body>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td bgcolor="#1075b1">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="608" background="images/login/login_03.gif">
					<table width="847" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="318" background="images/login/login_04.gif">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="84">
							<form action="${pageContext.request.contextPath}/authority/Login_authority" method="post">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="381" height="84"
											background="images/login/login_06.gif">
											&nbsp;
										</td>
										<td width="162" valign="middle"
											background="images/login/login_07.gif">
											<span style="font-size: 12px; color: red;">${error}</span>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="44" height="24" valign="bottom">
														<div align="right">
															<span class="STYLE3">用&nbsp;&nbsp;&nbsp;户</span>
														</div>
													</td>
													<td width="10" valign="bottom">
														&nbsp;
													</td>
													<td height="24" colspan="2" valign="bottom">
														<div align="left">
															<input type="text" name="admin"
																style="width: 100px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;">
														</div>
													</td>
												</tr>
												<tr>
													<td height="24" valign="bottom">
														<div align="right">
															<span class="STYLE3">密&nbsp;&nbsp;&nbsp;码</span>
														</div>
													</td>
													<td width="10" valign="bottom">
														&nbsp;
													</td>
													<td height="24" colspan="2" valign="bottom">
														<input type="password" name="pas"
															style="width: 100px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;">
													</td>
												</tr>
												
												<tr></tr>
											</table>
										</td>
										<td width="26">
											<img src="images/login/login_08.gif" width="26" height="84">
										</td>
										<td width="67" background="images/login/login_09.gif">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="25">
														<div align="center">
															<input type="submit" src="images/login/dl.gif" value="登  录">
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="211" background="images/login/login_10.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</form>
							</td>
						</tr>
						<tr>
							<td height="196" background="images/login/login_11.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#152753">
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>

