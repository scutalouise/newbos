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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language="javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery.form.js" type="text/javascript"></script>
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
									<td width="21"><img src="./images/bos/ico07.png"
										width="20" height="18" /></td>
									<td width="538"><span
										style="color: white; font-family: '黑体'; font-size: 13px;">
											采购作业->卸油 </span></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table id="subtree1" style="DISPLAY:" width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="95%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="15" align="center"></td>
								</tr>
								<tr>
									<td align="center" width="100%">
										<table id="subtree1" style="DISPLAY:" width="85%" border="0"
											cellspacing="0" cellpadding="0" >
											<tr>
											
											<td height="40" class="font42"><input
										name="UnloadingOilStatus" value="01" type="hidden">
										<table width="100%" border="0" cellpadding="0" cellspacing="1"
											bgcolor="#cccccc" class="newfont03" align="center">
											<tr class="CTitle">
												<td height="35" colspan="7" align="center"
													style="font-size: 16px">操作反馈</td>
											</tr>
											<tr bgcolor="#ffffff">
												<td width="100%" align="center" height="25">
												 <div id="cautionPlaceHidden" style="display: none;"></div>
												 <div id="cautionPlace">
												 <c:choose>
												 <c:when test="${exeFeedback eq 'normal' or exeFeedback eq 'complete'}">${Msg}</c:when>
												 <c:otherwise><span style="color: red">${Msg}</span></c:otherwise>
												 </c:choose>
												 </div>
												 <div id="blankFeedBack" style="display: none;"></div>
												 </td>
											</tr>
											<c:if test="${exeFeedback != 'complete'}">
											<tr bgcolor="#ffffff">
												<td width="100%" align="center" height="25">
												 	您可选择在以下表格中手工录入或者点击“返回”按钮重新操作。
												</td>
											</tr>
											</c:if>
										</table>
										</td>
											</tr>
											<tr>
												<td>
												<form action="bos/saveOilUnloadingData.action" method="post" id="manualRecordForm">
												<c:if test="${exeFeedback != 'complete'}">
												<table width="100%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<thead>
												<tr style="background-color: #54a1ff;">
													<td height="35" colspan="7" align="center"
														style="font-size: 16px">
														液位仪数据手工录入（订单号：${NumAliases}） 
														<input name="UnloadingOilStatus"  value="${UnloadingOilStatus}" type="hidden" />
														<input name="orderNum"  value="${Num}" type="hidden" />
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													<td width="20%" align="center" height="35">
														罐号
													</td>
													<td width="14%" align="center">
														对应枪泵码数和(L)
													</td>
													<td width="13%" align="center">
														油温度(C)
													</td>
													<td width="13%" align="center">
														水高(mm)
													</td>
													<td width="13%" align="center">
														油高(mm)
													</td>
														<td width="14%" align="center">
														油水总体积(L)
													</td>
													<td width="13%" align="center">
														油体积(L)
													</td>
												</tr>
												</thead>
												<tbody id="manualRecordTanks">
													
													<c:forEach items="${tanksVarData}" var="tankCell">
														<tr  bgcolor="#f9f9f9">
														<td align="center" height="35" style="padding-left: 5px;">${tankCell[0]}号罐（油枪：${tankCell[1]}）<input id="tankNum" name="tankNum" value="${tankCell[0]}" type="hidden"> </td>
														<td align="center">
														<input name="totalNozzleVal" size="8" maxlength="9" <c:if test="${tankCell[1] eq ''}">readonly="readonly"</c:if> value="0.00"  /></td>
														<td align="center"><input name="oTemperature" size="3" maxlength="4" onblur="strictTemperature(this)"/></td>
														<td align="center"><input name="wHeight" size="3" maxlength="4" onblur="getStandardCubage(this,'w',${tankCell[0]})" /></td>
														<td align="center"><input name="oHeight" size="3" maxlength="4" onblur="getStandardCubage(this,'o',${tankCell[0]})" /></td>
														<td align="center"><input name="totalCubage" size="5" maxlength="8" id="totalCubage" /></td>
														<td align="center"><input name="oCubage" size="5" maxlength="8" id="oCubage" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											</c:if>
											</form>
												</td>
											</tr>
											<tr>
											
											<td height="40" class="font42"><input
										name="UnloadingOilStatus" value="01" type="hidden">
										<table width="100%" border="0" cellpadding="0" cellspacing="1"
											bgcolor="#cccccc" class="newfont03" align="center">
											<tr bgcolor="#ffffff">
												<td width="100%" align="center" height="25">
												<input type="hidden" name=StationNo id="StationNo" value="${Station.station_No}" /> 
											<c:if test="${exeFeedback != 'complete'}"><input type="button" class="right-button02" value="添  加" id="addBt"/> </c:if>
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="reset" id="returnMainFunction" class="right-button02" value="返  回"
													onclick="location.href='<%=basePath%>bos/selOrderBill_01.action?startDate=${startDate}&endDate=${endDate}&QueryContext=${Num}&Status=${Status}'" />
												 </td>
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
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
	
		$(function(){
			
			var options = {
					target:'#cautionPlaceHidden',
					beforeSubmit : function(){
						var flag = true;
						$("#manualRecordTanks").children().each(function(){
							$(this).children().each(function(ind){
								if(ind == 0)return;
								var inputVal = $(this).children(":first").val();
								if(!inputVal){flag = !flag;return false;}
							});
							if(!flag)return false;
						});
						if(!flag){alert("填写不完整，请检查并填写后再次提交。");return false;} 
						return true;
					},
					success: function(text) {
						if(text.indexOf("成功")<0){
							$("#cautionPlace").html("<span style='color: red'>"+text+"</span>");
						}else{
							$("#cautionPlace").html("<span style='color: green'>"+text+"</span>");
							setTimeout(function(){
								$("#returnMainFunction").click();
							},1200);
						}
					}
				}
			   $('#manualRecordForm').ajaxForm(options);
			
			 $("#addBt").click(function(){
				 $('#manualRecordForm').submit();
			 })
			
		});
		
		function getStandardCubage(obj,flag,tankNum){
			var cHi = $(obj).val();
			var $tr = $(obj).parent().parent();
			if(!cHi) cHi = 0 ;
			var oHeight = '';
			var wHeight = '';
			var ask = "w"; //w o ow
			if(flag == 'w'){
				wHeight = cHi;
				oHeight = $(obj).parent().next().children(":first").val();
				if(!!oHeight){ask = 'ot';}else{ask = 't';oHeight = 0;};
			}else{
				oHeight = cHi;
				wHeight = $(obj).parent().prev().children(":first").val();
				if(!!wHeight){}else{wHeight = 0;}
				ask = 'ot';
			}
			if(!strictInteger(wHeight) || !strictInteger(oHeight)){alert("水高/油高请输入整数！");return;}
			
			$.ajax({type:'POST',url:'${basePath}bos/getTankCubageByHeight.action',
				data: {tankNum:tankNum,wHeight:wHeight,oHeight:oHeight,ask:ask},
				dataType:'json',
				success:function(rs){
					if(rs.flag == 'success'){
						 $tr.find("#totalCubage").val(rs.tcubage);
						 $tr.find("#oCubage").val(rs.ocubage);
					}
				}
			});
		}
		
		function strictInteger(param){
			var regex = /^\d{1,4}$/;
			return regex.test(param)
		}
		
		<%--oil温度2位整数（26） 或者 3位小数（32.3） --%>
		function strictTemperature(obj){
			var regex = /^\d{1,2}(\.\d)?$/;
			if(!regex.test($(obj).val())){
				alert("温度请输入1-2位整数或其后附上一位小数！");$(obj).val('');
			}
		}
	
	</script>

</body>
</html>
