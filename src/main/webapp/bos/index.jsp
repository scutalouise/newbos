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
	test1{width:80%;}

	</style>
	</head>
<frameset cols="*,1280,*" frameborder="no" border="0" framespacing="0">	
	<frame src="${pageContext.request.contextPath}/bos/empty.jsp"/>
	<frameset rows="70,*" cols="*" frameborder="no" border="0" framespacing="0">
	  <frame src="${pageContext.request.contextPath}/bos/common/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
	  <frameset cols="200,*" frameborder="no" border="0" framespacing="0">
	    <frame src="${pageContext.request.contextPath}/bos/common/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
	    <frame src="${pageContext.request.contextPath}/bos/mainfra.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
	  </frameset>
	</frameset>
	<frame src="${pageContext.request.contextPath}/bos/empty.jsp"/>
</frameset>
<noframes>

	<body>
	<!-- 
		<table border="0" width="100%" align="center">
			<tr>
				<td colspan="2" align="center" width="100%">
					<iframe name="top" src="${pageContext.request.contextPath}/common/commontop.jsp" frameborder="0" scrolling="No"></iframe>
				</td>
			</tr>
			<tr>
				<td align="center" width="30%">
					<iframe name="left" src="${pageContext.request.contextPath}/common/left.jsp" frameborder="0" scrolling="No"></iframe>
				</td>
				<td align="center" width="70%">
					<iframe name="right" src="${pageContext.request.contextPath}/common/mainfra.jsp" frameborder="0" scrolling="No"></iframe>
				</td>
			</tr>
		</table>
		
	</body>
	 -->
	
</noframes>
</html>
