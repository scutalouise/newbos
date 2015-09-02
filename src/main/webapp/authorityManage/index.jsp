<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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

		<title>权限管理（模块）系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="${pageContext.request.contextPath}/css/default.css"
			type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/dtree.css"
			type="text/css" rel="stylesheet">
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript">
</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/dtree.js"
			type="text/javascript">
</script>
		


	</head>

	<body>

		<table id="indextablebody" cellpadding="0">
			<thead>
				<tr>
					<th>
						<img alt="logo" style="margin-right:55px" src="${pageContext.request.contextPath}/images/manage/logo.gif" width="89" height="33">
					</th>
					<th>
						<strong><font style="font-family:'楷体';font-size:23px;">权限管理（模块）系统</font></strong>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						管理员：${currentAdmin.admin_admin}&nbsp;&nbsp;&nbsp;&nbsp; 
						<a target="right" href="${pageContext.request.contextPath}/authorityManage/update_pas.jsp">修改密码</a>	&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/DengChu!delSession.action" title="安全退出">安全退出</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</th>
				</tr>
			</thead>

			<tbody>
				<tr>
				<!-- 加油站图标 -->
					<td class="menu">
						<ul class="bigbtu">
						</ul>
					</td>
				<!-- 加油站图标结束 -->
					<td class="tab">
						 <ul id="tabpage1">
                            <li id="tab1" title="监控页面"><span id="spanTitle">管理页面</span></li>
                        </ul>
					</td>
				</tr>
				<tr>
					<td class="t1">
						<div id="contents">
							<table cellpadding="0">
								<tr class="t1">
									<td>
										<div class="menu_top">
										</div>
									</td>
								</tr>
								<tr class="t2">
									<td>
										<div id="menu" class="menu">
											<div id="left_menu_cnt1" class="left_menu_cnt">

												<!-- Tree开始 -->
												<div class="dtree">

	<script type="text/javascript">
		d = new dTree('d');
		d.add(0,-1,'权限管理（模块）系统');
		
		/**功能管理*/
		d.add(1,0,'系统功能管理','./authorityManage/welcome.jsp','系统功能管理','right');
		d.add(3,1,'系统功能增加','./authorityManage/addFunction.jsp','系统功能增加','right');
		d.add(4,1,'系统功能删除','./authorityManage/deleteFunction.jsp','系统功能删除','right');
		d.add(5,1,'系统功能修改','./authorityManage/updateFunction.jsp','系统功能修改','right');
		/**角色管理*/
		d.add(2,0,'系统角色管理','./authorityManage/welcome.jsp','系统角色管理','right');
		d.add(6,2,'系统角色增加','./authorityManage/addRole.jsp','系统角色增加','right');
		d.add(8,2,'系统角色删除','./authorityManage/deleteRole.jsp','系统角色删除','right');
		d.add(9,2,'系统角色修改','./authorityManage/updateRole.jsp','系统角色修改','right');
		/**组别管理*/
		d.add(10,0,'系统组别管理','./authorityManage/welcome.jsp','系统组别管理','right');
		d.add(11,10,'系统组别增加','./authorityManage/addGroup.jsp','系统组别增加','right');
		d.add(12,10,'系统组别删除','./authorityManage/deleteGroup.jsp','系统组别删除','right');
		d.add(13,10,'系统组别修改','./authorityManage/updateGroup.jsp','系统组别修改','right');
		/**功能-角色-组别管理*/
		d.add(14,0,'角色-功能管理','./authorityManage/welcome.jsp','系统组别管理','right');
		d.add(15,14,'角色-功能管理','./authorityManage/addRole_Function.jsp','角色-功能管理','right');
		/*写出所生成的树*/
		document.write(d);

		//-->
	</script>
	</div>

												<!-- Tree结束 -->

											</div>
										</div>
								</td>
								<tr class="t3">
									<td>
										<div class="menu_end">
										</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
					<td class="t2">
						<div id="cnt">
							<div id="dtab1" style="height: 100%;">
								 <iframe name="right" src="${pageContext.request.contextPath}/authorityManage/welcome.jsp" frameborder="0"></iframe>					
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
