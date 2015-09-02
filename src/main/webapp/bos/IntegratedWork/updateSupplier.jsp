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
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js">
		</script>
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

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
</style>
		<link href="css/bos.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		function chenckform(){
			var Num=document.getElementById("Num").value;
			var Name=document.getElementById("Name").value;
			var Tel=document.getElementById("Tel").value;
			if(""==Num.replace(/(^\s*)|(\s*$)/g, "")||""==Name.replace(/(^\s*)|(\s*$)/g, "")
					||""==Tel.replace(/(^\s*)|(\s*$)/g, "")){
				alert("供货商编号、名称、电话不能为空！");
				return false;
			}else {
				return true;
			}
		}
		</script>
	</head>
	<body bgcolor="#c6dffc">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" bgcolor="#7fb5ec">
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="21">
											<img src="./images/bos/ico07.png" width="20" height="18" />
										</td>
										<td width="538">
											<span style="color: white;font-family:'黑体';font-size: 13px;">
											基本业务->修改供应商资料
											</span>
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
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="100" align="center">
											<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">
										<form action="bos/updateProductSupplier.action" target="mainFrame" onsubmit="return chenckform();">
											<s:set name="Name" value="#parameters.Name[0]"></s:set>
											<s:set name="Tel" value="#parameters.Tel[0]"></s:set>
											<s:set name="Cell" value="#parameters.Cell[0]"></s:set>
											<s:set name="Fax" value="#parameters.Fax[0]"></s:set>
											<s:set name="Address" value="#parameters.Address[0]"></s:set>
											<s:set name="Remark" value="#parameters.Remark[0]"></s:set>
											<s:set name="SupplierKey" value="#parameters.SupplierKey[0]"></s:set>
											<input name="SupplierKey" value="${SupplierKey}" type="hidden">
											<table width="53%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														修改供应商信息
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														供应商名称
													</td>
													<td width="70%" align="left">
														<input name="Name" id="Name" size="30" value="${Name}"/>
														<span>例：XX石油销售有限公司</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														供应商电话（座机）
													</td>
													<td width="70%" align="left">
														<input id="Tel" name="Tel" size="30" value="${Tel}"/>
														<span>例：028-88888888</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														供应商电话（手机）
													</td>
													<td width="70%" align="left">
														<input id="Cell" name="Cell" size="30" value="${Cell}"/>
														<span>例：13900110011</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														供应商传真
													</td>
													<td width="70%" align="left">
														<input id="Fax" name="Fax" size="30" value="${Fax}" />
														<span>例：028-88888888</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														供应商地址
													</td>
													<td width="70%" align="left">
														<input id="Address" name="Address" size="30" value="${Address}"/>
														<span>例：XX市XX区XX街道XX号</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" align="center" height="30">
														备注
													</td>
													<td width="70%" align="left">
														<textarea rows="3" cols="55" name="Remark" style="resize: none;">
															${Remark}
														</textarea>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="30%" colspan="2" align="center" height="30">
														<input type="submit" class="right-button02" value="修  改" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="button" class="right-button02" value="返  回" 
															onclick="location.href='bos/selProductSupplier.action'"/>
														
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
		</table>

	</body>
</html>
