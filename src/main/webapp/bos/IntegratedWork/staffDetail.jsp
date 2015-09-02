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
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/page.js"
			type="text/javascript">
</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/selStaffType.js"
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
										<form action="bos/showStaffDetail.action">
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="4%">
														<img src="./images/bos/ico07.png" width="20" height="18" />
													</td>
													<td width="96%">
													<span style="color: white;font-family:'黑体';font-size: 13px;">
														查询条件：
													</span>
													<select id="selType" size="1" name="selType" style="width: 80px;" onclick="getSelStaffType();">
                               	 						<c:if test="${selType==-1||selType==null}">	
                               	 							<option value="-1" selected="selected">==全部==</option>
                               	 							<option value="1">员工姓名</option>
                               	 							<option value="2">员工编号</option>
                               	 							<option value="3">身份证号</option>
                               	 							<option value="4">在职状态</option>
                               	 						</c:if>
                               	 						<c:if test="${selType==1}">	
                               	 							<option value="-1">==全部==</option>
                               	 							<option value="1" selected="selected">员工姓名</option>
                               	 							<option value="2">员工编号</option>
                               	 							<option value="3">身份证号</option>
                               	 							<option value="4">在职状态</option>
                               	 						</c:if>
                               	 						<c:if test="${selType==2}">	
                               	 							<option value="-1">==全部==</option>
                               	 							<option value="1">员工姓名</option>
                               	 							<option value="2" selected="selected">员工编号</option>
                               	 							<option value="3">身份证号</option>
                               	 							<option value="4">在职状态</option>
                               	 						</c:if> 
                               	 						<c:if test="${selType==3}">	
                               	 							<option value="-1" >==全部==</option>
                               	 							<option value="1">员工姓名</option>
                               	 							<option value="2">员工编号</option>
                               	 							<option value="3"  selected="selected">身份证号</option>
                               	 							<option value="4">在职状态</option>
                               	 						</c:if>  
                               	 						<c:if test="${selType==4}">	
                               	 							<option value="-1" >==全部==</option>
                               	 							<option value="1">员工姓名</option>
                               	 							<option value="2">员工编号</option>
                               	 							<option value="3">身份证号</option>
                               	 							<option value="4"  selected="selected">在职状态</option>
                               	 						</c:if>                              	 							
                            					</select>
                            					<span id="sel"></span>      
                            					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="submit" class="right-button02"
													value="查 询" />
													<!--自动补全框 -->
												<div id="auto"></div>
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
										<td height="10"></td>
									</tr>

									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<tr class="CTitle">
													<td height="35" colspan="12" align="center"
														style="font-size: 16px">
														员工基本资料
													</td>
												</tr>
												<tr bgcolor="#f9f9f9" align="center">
													<td width="6%" align="center" height="30">
														员工编号
													</td>
													<td width="6%">
														员工卡号
													</td>
													<td width="8%">
														员工姓名
													</td>
													<td width="8%">
														员工电话
													</td>
													<td width="14%">
														员工身份证号码
													</td>
													<td width="14%">
														员工住址
													</td>
													<td width="6%">
														员工职位
													</td>
													<td width="5%">
														员工在职状态
													</td>
													<!--  
													<td width="8%">
														员工登陆密码
													</td>
													-->
													<td width="8%">
														员工入职时间
													</td>
													<td width="8%">
														员工离职时间
													</td>
													<td width="8%">
														最后修改时间
													</td>

												</tr>
												<s:iterator value="Staff" status="st">
													<tr bgcolor="#FFFFFF" align="center">
														<td height="20">
															<s:property value="Staff[#st.index][0].staff_No"/>
														</td>
														<td>
															<s:property value="Staff[#st.index][0].Staff_CardNo"/>
														</td>
														<td>
															<s:property value="Staff[#st.index][0].Staff_Name"/>
														</td>
														<td height="20">
															<s:property value="Staff[#st.index][0].Staff_Phone"/>
														</td>
														<td>
															<s:property value="Staff[#st.index][0].Staff_ID"/>
														</td>
														<td>
															<s:property value="Staff[#st.index][0].Staff_Address"/>
														</td>
														<td height="20">
															<s:property value="Staff[#st.index][2].PositionType_Desc"/>
										
														</td>
														<td>
														<!--  <s:property value='Staff[#st.index][1].StationStaff_StaffStatus'/>-->	
															<s:set name="StaffStatus" value="Staff[#st.index][1].StationStaff_StaffStatus"></s:set>
															<c:if test="${StaffStatus==1}">
																在职
															</c:if>
															<c:if test="${StaffStatus==2}">
																离职
															</c:if>
															<c:if test="${StaffStatus==3}">
																已换站
															</c:if>
														</td>
														<!--  
														<td>
															${Staff_PW}
														</td>
														-->
														<td>
															<s:set name="Staff_JoinTime" value="Staff[#st.index][0].Staff_JoinTime"></s:set>
															<fmt:formatDate value='${Staff_JoinTime}'></fmt:formatDate>
														</td>
														<td>
															<s:set name="Staff_LeftTime" value="Staff[#st.index][0].Staff_LeftTime"></s:set>
															<fmt:formatDate value='${Staff_LeftTime}'></fmt:formatDate>
														</td>
														<td>
															<s:set name="Staff_SyncDate" value="Staff[#st.index][0].Staff_SyncDate"></s:set>
															<fmt:formatDate value='${Staff_SyncDate}'></fmt:formatDate>
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
													<td width="30%" align="right">
														[
														<a href="bos/showStaffDetail.action?page.pageNum=1"
															class="right-font08">首页</a> |
														<a
															href="bos/showStaffDetail.action?page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/showStaffDetail.action?page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/showStaffDetail.action?page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/showStaffDetail.action" onsubmit="return chenckPageNum();">
													<td width="7%" class="right-font08">
														转至：<input id="to_PageNum" name="to_PageNum" type="text"
															class="right-textfield03" size="1" />&nbsp;
													</td>
													<td width="3%">
														<input type="submit"
															class="right-button06" value="" />
													</td>
													</form>
												</tr>
											</table>
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
