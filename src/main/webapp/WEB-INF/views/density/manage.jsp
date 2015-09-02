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
	
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.1.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>	
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery.form.js" type="text/javascript"></script>
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
										<form action="mvc/density/getlist" method="post" id="queryListForm">
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0" style="margin-top: 12px;">
												<tr>
													<td width="4%">
														<img src="./images/bos/ico07.png" width="20" height="18" />
													</td>
													<td width="20%">
													<span style="color: white;font-family:'黑体';font-size: 13px;">
														油品：
													</span>
													<select id="productId" name="productId" size="1" style="width: 70px;">
                               	 						 	<option value="-1" selected="selected">全部</option>
                               	 						 	<c:forEach var="cell" items="${productList}">
                               	 								<option value="${cell.product_Num}" style="padding-left: 10px;">${cell.product_Name}</option>
                               	 							</c:forEach>
                            						</select>
													</td>
													<td>
													<span style="color: white;font-family:'黑体';font-size: 13px;">
														月份：
													</span>
													<input  name="yearMonth" class="runcode" id="yearMonth" size="12" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>
													<input id="getTotalNum" name="getTotalNum"  value="false" type="hidden" />
													</td>
													<td>
														<input name="pageSize" id="pageSize" value="15" type="hidden" />
														<input name="pageNo" id="pageNo" value="1" type="hidden" />
														
														<input id="queryDensityBt"  type="button" value="查询"/>
													</td>
												</tr>
											</table>
											</form>
											<form action="mvc/density/update" method="post" id="updateDensityQueryForm"  style="display: none;">
												<input name="id" id="exeId" />
												<input name="densityManual" id="n_density" />
												<input name="Density_Density" id="o_density" />
											</form>
										</td>
									</tr>
									<tr bgcolor="#f9f9f9" align="center" id="rowTemplate" style="display: none;">
													<td width="10%" align="center" height="30">
													<select id="new_tanknum">
                               	 						 <c:forEach var="tankcell" items="${tankList}">
                               	 							<option value="${tankcell.tanksetting_TankNum}" style="padding-left: 10px;">${tankcell.tanksetting_TankNum}</option>
                               	 						</c:forEach>
													</select>
													</td>
													<td width="20%" align="center" >
														<input id="new_height" size="8" maxlength="6" /> ${referredCubage.height}
													</td>
													<td width="20%">
														<input id="new_cubage" size="8" maxlength="8"/>
													</td>
													<td width="15%">
														无须填写
													</td>
													<td width="25%">
														无须填写
													</td>
													<td >
														<a onclick="sureAddRow(this)" href="javascript:void(0);" style="cursor: pointer;">保存 </a>
														<a onclick="cancelAddRow(this)" href="javascript:void(0);" style="cursor: pointer;">取消 </a>
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
										<td height="30" align="center"><span style="color: green;" id="adBoard1">FEFDEFWE</span><span style="color: red;" id="adBoard2">FEFDEFWE</span></td>
									</tr>

									<tr>
										<td height="40" class="font42" align="center">
											<table width="90%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<thead>
													<tr class="CTitle">
														<td height="35" colspan="6" align="center"
															style="font-size: 16px">
															产品密度列表
														</td>
													</tr>
													<tr bgcolor="#f9f9f9" align="center">
													<td width="10%" align="center" height="30">
														油品名称
													</td>
													<td width="20%" align="center" >
														初始密度(L/T)
													</td>
													<td width="20%">
														修正密度(L/T)
													</td>
													<td width="15%">
														开始时间
													</td>
													<td width="20%">
														结束时间
													</td>
													<td >
														操作
													</td>
												</tr>
												</thead>
												<tbody id="itemShowList">
													
												</tbody>
												
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
													<td width="40%" class="right-font08" style="color: #000">
														共
														<span class="right-text09" id="totalPageNum" style="color: green;">0</span> 页 | 第
														<span class="right-text09" id="currentPageNum" style="color: green;">0</span> 页 | 每页显示
														<span class="right-text09" id="pageRowNum" style="color: green;">15</span> 条数据
														<input id="pages" type="hidden" value="${page.pages}">
													</td>
													<td width="30%" align="right">
														[
														<a onclick="javascript:void(0)"
															class="right-font08" style="color: #000;cursor: pointer;" id="firstPage" >首页</a> |
														<a
															onclick="javascript:void(0)"
															class="right-font08" style="color: #000;cursor: pointer;" id="prevPage">上一页</a> |
														<a
															onclick="javascript:void(0)"
															class="right-font08" style="color: #000;cursor: pointer;" id="nextPage">下一页</a> |
														<a
															onclick="javascript:void(0)"
															class="right-font08" style="color: #000;cursor: pointer;" id="lastPage">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/showStaffDetail.action" onsubmit="return chenckPageNum();">
													<td width="7%" class="right-font08" style="color: #000">
														转至：<input id="to_PageNum" name="to_PageNum" type="text"
															 size="3" />&nbsp;
													</td>
													<td width="3%">
														<input type="button"
															class="right-button06" value="" id="gotoSomePage" style="cursor: pointer;" />
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

<script type="text/javascript">
	
	<%--global space--%>
		var needdealrow = null;

	<%--global space--%>

	$(document).ready(function() { 
		var options = {
			target:'#itemShowList',
			success: function() {
				if( $("#getTotalNum").val() == 'true'){
					var totalpages = $("#totalPages").val();
					$("#totalPageNum").text($("#totalPages").val());
					if(totalpages > 0)$("#currentPageNum").text(1);
					else  $("#currentPageNum").text(0);
				}
			}
		}
	   $('#queryListForm').ajaxForm(options);
		
	   function queryCubages(){
	   		$("#adBoard1").text('');
	   		$("#adBoard2").text('');
	   		$("#itemShowList").html('');
			$('#queryListForm').submit();
	   }
	   <%--来来去去的按钮事件使用--%>
	   function queryCubagesByBt(){
		   $("#getTotalNum").val(false);
		   queryCubages();
	   }
		
	   $("#queryDensityBt").click(function(){
		   $("#getTotalNum").val(true);
		   $("#pageNo").val(1);
		   queryCubages();
	   });
	   
	   $("#firstPage").click(function(){
		   var $currentPageNum = $("#currentPageNum");
		   if($currentPageNum.text() == 0) return;
		   $currentPageNum.text(1);
		   $("#pageNo").val(1);
		   queryCubagesByBt();
	   });
	   $("#lastPage").click(function(){
		   var $currentPageNum = $("#currentPageNum");
		   if($currentPageNum.text() == 0) return;
		   var currentPageNo = $("#totalPageNum").text();
		   $currentPageNum.text(currentPageNo);
		   $("#pageNo").val(currentPageNo);
		   queryCubagesByBt();
	   });
	   
	   $("#prevPage").click(function(){
		   var $currentPageNum = $("#currentPageNum");
		   var currentPaggeNo_ = parseInt($currentPageNum.text());
		   if(currentPaggeNo_ == 0 || currentPaggeNo_ == 1) return;
		   currentPaggeNo_ = currentPaggeNo_ - 1;
		   $currentPageNum.text(currentPaggeNo_);
		   $("#pageNo").val(currentPaggeNo_);
		   queryCubagesByBt();
	   });
	   
	   
	   $("#nextPage").click(function(){
		   var $currentPageNum = $("#currentPageNum");
		   var currentPaggeNo_ = parseInt($currentPageNum.text());
		   var totalPageNo_ = parseInt($("#totalPageNum").text());
		   if(currentPaggeNo_ == 0 || currentPaggeNo_ == totalPageNo_) return;
		   currentPaggeNo_ = currentPaggeNo_ + 1;
		   $currentPageNum.text(currentPaggeNo_);
		   $("#pageNo").val(currentPaggeNo_);
		   queryCubagesByBt();
	   });
	   
	   
	   $("#gotoSomePage").click(function(){
		   var $currentPageNum = $("#currentPageNum");
		   if($currentPageNum.text() == 0) return;
		   var topagenum = $("#to_PageNum").val();
		   if($.trim(topagenum) == ''){alert("请输入跳转页号再查询。");return false};
		   var totalPageNum = parseInt($("#totalPageNum").text());
		   if(topagenum > totalPageNum){topagenum = totalPageNum;};
		   if(topagenum < 1){topagenum = 1;} ;
		   $currentPageNum.text(topagenum);
		   $("#pageNo").val(topagenum);
		   $("#to_PageNum").val(topagenum);
		   queryCubagesByBt();
	   });
	   
	   $("#to_PageNum").bind('keypress',function(event){
		   if(event.keyCode == "13"){
			   $("#gotoSomePage").trigger('click');
			   return false;
           }
	   });
	   
		<%--初始查询--%>
	   $("#queryDensityBt").trigger('click');
	   
	   var options1 = {
			target:'#adBoard1',
			success: function(text) {
				
				if("unreasonable" == text){
					$("#adBoard1").html("");
					$("#adBoard2").html("请保证修正密度值与初始密度差值浮动在初始密度的±10%内！");
					return;
				}
				if("success" != text) return ;
				$("#adBoard2").html("");
				$("#adBoard1").html("修正成功！");
				setTimeout(function(){
					 $("#queryDensityBt").trigger('click');
				},1200)
			},
			failure:function(text){
			},
			complete: function(){
				$("#exeId").val('');
			}
		}
	   
	   $('#updateDensityQueryForm').ajaxForm(options1);
		
	});
	
	function editCurrentRow(cobj,rowid,curval){
		needdealrow = $(cobj).parent().parent();
		needdealrow.children(":eq(2)").html('<input  size="8" maxlength="8" value="'+curval+'"/>')
		var $cobj = $(cobj);
		$cobj.hide();
		var $cobjnext = $cobj.next(); 
		$cobjnext.show();
		$cobjnext.next().show();
	}
	
	function cancelEditRow(cobj,formerVal){
		needdealrow = $(cobj).parent().parent();
		needdealrow.children(":eq(2)").html(formerVal);
		needdealrow = null;
		var $cobj = $(cobj);
		$cobj.hide();
		var $cobjprev = $cobj.prev(); 
		$cobjprev.hide();
		$cobjprev.prev().show();
	}

	function updateCurrentRow(cobj,rid,formerDensity){
		needdealrow = $(cobj).parent().parent();
		var amendedval = needdealrow.children(":eq(2)").children(":first").val();
		
		if(!!!amendedval){alert("请填写修正密度后再保存！");return ;}
		
		$("#n_density").val(amendedval);
		$("#o_density").val(formerDensity);
		$("#exeId").val(rid);
		$('#updateDensityQueryForm').submit();
	}

</script>



	</body>
</html>
