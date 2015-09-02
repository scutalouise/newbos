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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/repertory.js"></script>
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
			}else if(is2decimals(document.getElementById("ProductVol"))
					&&is1decimals(document.getElementById("Temp"))
					&&is2decimals(document.getElementById("PetrolLevel"))
					&&is2decimals(document.getElementById("WaterLevel"))
					&&is2decimals(document.getElementById("TotalVol"))){
					return true;
			}else{
				alert("输入有误，请检查油品库存信息输入是否正确。");
				return false;
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
											库存作业->班结库存录入
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
										<form action="bos/saveRepertory.action" target="mainFrame" onsubmit="return chenckform();">
											<table width="58%" border="0" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														班结库存录入
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														站点名称
													</td>
													<td width="60%">
														&nbsp;&nbsp;${Station.station_Name}
													<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>	
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														产品类型
													</td>
													<td width="60%" align="left">
														<select id="ProductType" size="1" name="ProductType" style="width: 200px;" onblur="getProdunctList();">
                               	 							<option value="0" selected="selected">油品</option>
                               	 							<!-- <option value="1" >气品</option> -->
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														产品名称
													</td>
													<td width="60%" align="left">
														<select id="ProductNum" size="1" name="ProductNum" style="width: 200px;" onblur="getTankList();">
															<!-- 加载产品名称，选择气品或者油品时添加 -->								
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油罐编号
														<!-- /气罐编号 -->
													</td>
													<td width="60%" align="left">
														<select id="TankNum" size="1" name="TankNum" style="width: 200px;" onfocus="getTankList();">
															<!-- 加载油罐或者气罐编号，选择气品或者油品时添加 -->								
                            							</select>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油品体积（L）<!-- /气品气量（m³） -->
													</td>
													<td width="60%" align="left">
														<input id="ProductVol" name="ProductVol" size="30" onblur="is2decimals(this);"/>
														<span>例：99.99</span>
													</td>
												</tr>
												<!-- 
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														计量单位
													</td>
													<td width="60%" align="left">
														<input id="Unit" name="Unit" size="25"/>
													</td>
												</tr>
												 -->
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														温度
													</td>
													<td width="60%" align="left">
														<input id="Temp" name="Temp" size="25" onblur="is1decimals(this);"/>
														<span>例：15.1</span>
													</td>
												</tr>
												<!-- 
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														密度
													</td>
													<td width="60%" align="left">
														<input id="Density" name="Density" size="25" onblur="is4decimals(this);"/>
														<span>例：31.1478</span>
													</td>
												</tr>
												 -->
												<!-- 选择气品才可填 -->
												<!-- 
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														压力
													</td>
													<td width="60%" align="left">
														<input id="Pressure" name="Pressure" size="25" onblur="is4decimals(this);"/>
														<span>例：31.1478</span>
													</td>
												</tr>
												 -->
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														水高<!-- 选择油品才可填 -->
													</td>
													<td width="60%" align="left">
														<input id="WaterLevel" name="WaterLevel" size="25" onblur="is2decimals(this);"/>
														<span>例：0.12</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油高<!-- 选择油品才可填 -->
													</td>
													<td width="60%" align="left">
														<input id="PetrolLevel" name="PetrolLevel" size="25" onblur="is2decimals(this);"/>
														<span>例：131.14</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														油水总体积<!-- 选择油品才可填 -->
													</td>
													<td width="60%" align="left">
														<input id="TotalVol" name="TotalVol" size="25" onblur="is2decimals(this);"/>
														<span>例：131.14</span>
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="30">
														班别编号
													</td>
													<td width="60%" align="left">
														<input id="ShiftNo" name="ShiftNo" size="25" readonly="readonly" style="color:gray"/>
													</td>
												</tr>											
												<tr bgcolor="#ffffff">
													<td width="40%" align="center" height="25">
														备注
													</td>
													<td width="60%" align="left">
														<textarea id="Remark" rows="1" cols="50" name="Remark"></textarea>
													
													</td>
												</tr>
												<tr bgcolor="#ffffff">
													<td width="40%" colspan="2" align="center" height="30">
														<input type="submit" class="right-button02" value="添  加" />
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="reset" class="right-button02" value="取  消" />
													
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
