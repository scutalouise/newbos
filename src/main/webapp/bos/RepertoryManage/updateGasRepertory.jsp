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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/lhgcalendar.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
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
			var StationNo=document.getElementById("StationNo").value;
			if(""==StationNo.replace(/(^\s*)|(\s*$)/g, "")){
				alert("Session已经失效，请刷新页面或重新登录！");
				return false;
			}else if("0"==$("#ProductType").val()){
				if(is2decimals(document.getElementById("ProductVol"))&&is1decimals(document.getElementById("Temp"))
					&&is4decimals(document.getElementById("Density"))&&is2decimals(document.getElementById("PetrolLevel"))
					&&is2decimals(document.getElementById("WaterLevel"))&&is2decimals(document.getElementById("TotalVol"))){
					return true;
				}else{
					alert("输入有误，请检查油品库存信息输入是否正确");
					return false;
				}
			}else if("1"==$("#ProductType").val()){
				if(is2decimals(document.getElementById("ProductVol"))&&is1decimals(document.getElementById("Temp"))
						&&is4decimals(document.getElementById("Density"))&&is4decimals(document.getElementById("Pressure"))){
					return true;	
				}else{
					alert("输入有误，请检查气品库存信息输入是否正确");
					return false;
				}
			}else{
				alert("输入有误");
				return false;
			}
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
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="21">
											<img src="./images/bos/ico07.gif" width="20" height="18" />
										</td>
										<td width="538">
										<span style="color: white;font-family:'黑体';font-size: 13px;">
											库存作业->班结库存修改
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
										<td height="20" align="center">
											<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>

									<tr>
										<td height="40" class="font42">
										<form action="bos/updateRepertory.action" target="mainFrame" onsubmit="return chenckform();">
											<!-- 赋值 -->
											<s:set name="TankNum" value="#parameters.TankNum[0]"></s:set>
											<s:set name="ProductNum" value="#parameters.ProductNum[0]"></s:set>
											<s:set name="ProductName" value="#parameters.ProductName[0]"></s:set>
											<s:set name="ShiftNo" value="#parameters.ShiftNo[0]"></s:set>
											<s:set name="ProductVol" value="#parameters.ProductVol[0]"></s:set>
											<s:set name="Temp" value="#parameters.Temp[0]"></s:set>
											<s:set name="Density" value="#parameters.Density[0]"></s:set>
											<s:set name="Pressure" value="#parameters.Pressure[0]"></s:set>
											<s:set name="Remark" value="#parameters.Remark[0]"></s:set>
											<s:set name="AddTime" value="#parameters.AddTime[0]"></s:set>
											<input name="AddTime" value="${AddTime}" type="hidden">
											<table width="58%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#464646" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														班结库存修改（气品）
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														站点名称
													</td>
													<td width="60%" align="center">
														${Station.station_Name}
													<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>	
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														产品类型
													</td>
													<td width="60%" align="left">
														<select id="ProductType" size="1" name="ProductType" style="width: 200px;" onblur="getProdunctList();">
                               	 							<option value="1" selected="selected">气品</option>
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														产品名称
													</td>
													<td width="60%" align="left">
														<select id="ProductNum" size="1" name="ProductNum" style="width: 200px;" onblur="getTankList();">
															<option value="${ProductNum}" selected="selected">${ProductName}</option>								
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														气罐编号
													</td>
													<td width="60%" align="left">
														<select id="TankNum" size="1" name="TankNum" style="width: 200px;">
															<option value="${TankNum}" selected="selected">${TankNum}</option>							
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														气品气量（m³）
													</td>
													<td width="60%" align="left">
														<input id="ProductVol" name="ProductVol" value="${ProductVol}" size="30" onblur="is2decimals(this);"/>
														<span>例：99.99</span>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														温度
													</td>
													<td width="60%" align="left">
														<input id="Temp" name="Temp" value="${Temp}" size="25" onblur="is1decimals(this);"/>
														<span>例：15.1</span>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														密度
													</td>
													<td width="60%" align="left">
														<input id="Density" name="Density" value="${Density}"  size="25"  onblur="is4decimals(this);"/>
														<span>例：31.1478</span>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														压力<!-- 选择气品才可填 -->
													</td>
													<td width="60%" align="left">
														<input id="Pressure" name="Pressure" value="${Pressure}"  size="25" onblur="is4decimals(this);"/>
														<span>例：31.1478</span>
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="30">
														班别编号
													</td>
													<td width="60%" align="left">
														<input id="ShiftNo" name="ShiftNo" value="${ShiftNo}" size="25" readonly="readonly"/>
													</td>
												</tr>											
												<tr bgcolor="#faedd6">
													<td width="40%" align="center" height="25">
														备注
													</td>
													<td width="60%" align="left">
														<textarea rows="1" cols="50" name="Remark">${Remark}</textarea>
													
													</td>
												</tr>
												<tr bgcolor="#faedd6">
													<td width="40%" colspan="2" align="center" height="30">
														<input type="submit" class="right-button02" value="修  改" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													
													<input type="button" class="right-button02" value="返  回" 
														onclick="location.href='<%=basePath%>bos/selRepertory.action?ShiftNo=${ShiftNo}'"/>
													
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
