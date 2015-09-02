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
										<form action="bos/getPromoteSalesList.action" method="post" id="promoteInfoQueryForm">
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0" style="margin-top: 12px;">
												<tr>
													<td width="4%">
														<img src="./images/bos/ico07.png" width="20" height="18" />
													</td>
													<td >
													<span style="color: white;font-family:'黑体';font-size: 13px;">
														促销品名称：
													</span>
													<input name="pname" value="" type="text" maxlength="20"/>
													<input id="getTotalNum" name="getTotalNum"  value="false" type="hidden" />
													</td>
													<td>
													</td>
													<td>
														<input name="pageSize" id="pageSize" value="15" type="hidden" />
														<input name="pageNo" id="pageNo" value="1" type="hidden" />
														<input id="queryCubageBt"  type="button" value="查询"/>
													</td>
												</tr>
											</table>
											</form>
											<form action="bos/addOrDelPromotionInfo.action" method="post" id="addPromoteInfoForm"  style="display: none;">
												<input name="exeId" id="exeId" />
												<input name="n_pname" id="n_pname" />
												<input name="n_pcount" id="n_pcount" />
												<input name="n_premark" id="n_premark" />
											</form>
										</td>
									</tr>
									<tr bgcolor="#f9f9f9" align="center" id="rowTemplate" style="display: none;">
													<td width="10%" align="center" height="30">
														<input id="new_name" size="8" maxlength="8"/>
													</td>
													<td width="20%" align="center" >
														<select id="new_count"> 
															<option value="1"  style="padding-left: 10px;">1</option>
															<option value="2"  style="padding-left: 10px;">2</option>
															<option value="3"  style="padding-left: 10px;">3</option>
															<option value="4"  style="padding-left: 10px;">4</option>
															<option value="5"  style="padding-left: 10px;">5</option>
															<option value="6"  style="padding-left: 10px;">6</option>
															<option value="7"  style="padding-left: 10px;">7</option>
															<option value="8"  style="padding-left: 10px;">8</option>
															<option value="9"  style="padding-left: 10px;">9</option>
															<option value="10"  style="padding-left: 10px;">10</option>
														</select>
													</td>
													<td width="20%">
														<input id="new_remark" size="8" maxlength="8"/>
													</td>
													<td >
														<a href="javascript:void(0);" onclick="sureAddRow(this)"  style="cursor: pointer;">保存 </a>
														<a href="javascript:void(0);" onclick="cancelAddRow(this)" style="cursor: pointer;">取消 </a>
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
										<td height="30" align="center"><span style="color: green;" id="adBoard"></span></td>
									</tr>

									<tr>
										<td height="40" class="font42" align="center">
											<table width="70%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<thead>
													<tr class="CTitle">
														<td height="35" colspan="3" align="center"
															style="font-size: 16px">
															促销品列表
														</td>
														<td width="12%" align="center"><span style="font-size: 2em;cursor: pointer;" title="添加" onclick="addCubageRow();">+</span></td>
													</tr>
													<tr bgcolor="#f9f9f9" align="center">
													<td width="25%" align="center" height="30">
														名称
													</td>
													<td width="15%" align="center" >
														数量
													</td>
													<td width="40%">
														备注
													</td>
													<td >
														操作
													</td>
												</tr>
												</thead>
												<tbody id="promoteList">
													
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
													
													<td width="7%" class="right-font08" style="color: #000">
														转至：<input id="to_PageNum" name="to_PageNum" type="text"
															 size="3" />&nbsp;
													</td>
													<td width="3%">
														<input type="button"
															class="right-button06" value="" id="gotoSomePage" style="cursor: pointer;" />
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
	
	<%--global space--%>
		var needdealrow = null;

	<%--global space--%>

	$(document).ready(function() { 
		var options = {
			target:'#promoteList',
			success: function() {
				if( $("#getTotalNum").val() == 'true'){
					var totalpages = $("#totalPages").val();
					$("#totalPageNum").text($("#totalPages").val());
					if(totalpages > 0)$("#currentPageNum").text(1);
					else  $("#currentPageNum").text(0);
				}
			}
		}
	   $('#promoteInfoQueryForm').ajaxForm(options);
		
	   function queryCubages(){
	   		$("#adBoard").text('');
	   		$("#promoteList").html('');
			$('#promoteInfoQueryForm').submit();
	   }
	   <%--来来去去的按钮事件使用--%>
	   function queryCubagesByBt(){
		   $("#getTotalNum").val(false);
		   queryCubages();
	   }
		
	   $("#queryCubageBt").click(function(){
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
	   $("#queryCubageBt").trigger('click');
	   
	   var options1 = {
			target:'#adBoard',
			success: function(text) {
				if(text.indexOf('成功') < 0) return ;
				!!needdealrow&&needdealrow.remove();
				setTimeout(function(){
					 $("#queryCubageBt").trigger('click');
				},1200)
			},
			complete: function(){
			//	$('#addPromoteInfoForm').resetForm();
				needdealrow = null;
				$("#exeId").val('');
			}
		}
	   
	   $('#addPromoteInfoForm').ajaxForm(options1);
		
	});
	
	
	function delcurrentRow(cobj,rowid){
		
		if(confirm("确认删除此行数据吗？")){
			$("#exeId").val(rowid);
			needdealrow = $(cobj).parent().parent();
			$('#addPromoteInfoForm').submit();
		}
	}
	
	function addCubageRow(){
		var rowClone = $("#rowTemplate").clone();
		rowClone.show();
		$("#promoteList").prepend(rowClone)
	}
	function cancelAddRow(cobj){
		 $(cobj).parent().parent().remove();
		 $('#addPromoteInfoForm').resetForm();
	}

	function sureAddRow(cobj){
		var $tr = $(cobj).parent().parent();
		var newtanknum = $tr.find("#new_name").val();
		newtanknum = $.trim(newtanknum);
		if(!!!newtanknum){alert("请填写促销品名称。"); return ;}
		$("#n_pname").val(newtanknum);
		var newheight = $tr.find("#new_count").val();
		if(!!!newheight){alert("请选择促销品数量。");return ;}
		$("#n_pcount").val(newheight);
		var newcubage = $tr.find("#new_remark").val();
		$("#n_premark").val(newcubage);
		
		$('#addPromoteInfoForm').submit();
	}
	
	
	

</script>



	</body>
</html>
