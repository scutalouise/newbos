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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js">
</script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/page.js"
			type="text/javascript">
</script>
<script type="text/javascript">
	function ConfirmDel(){
	   if(confirm("您确定要删除这条促销信息？删除后不可恢复！"))
	     return true;
	   else
	     return false;
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/repertory.js"></script>

	</head>
	<body bgcolor="#c6dffc">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="45" bgcolor="#7fb5ec">
								<form action="bos/selPromotion.action">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="margin-top: 5px;">
										<tr>
											<td width="4%">
												<img src="./images/bos/ico07.png" width="20" height="18" />
											</td>
											<td width="96%">
											<span style="color: white;font-family:'黑体';font-size: 13px;">
												<input id="StationNo" name="StationNo" type="hidden" value="${Station.station_No}"/>
												产品类型：
												<select id="ProductType" size="1" name="ProductType" style="width:50px;">
                               	 					<option value="0" selected="selected">油品</option>
                               	 					<!-- <option value="1" >气品</option> -->
                            					</select>
                            					&nbsp;&nbsp;&nbsp;&nbsp;
                            					<select id="ProductNum" size="1" name="ProductNum" style="width: 80px;" onfocus="getProdunctList();">
													<!-- 加载产品名称，选择气品或者油品时添加 -->							
                            					</select>
											</span>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="submit" class="right-button02"
												value="查 询" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" class="right-button08" value="添加促销信息" 
												onclick="location.href='${pageContext.request.contextPath}/bos/BusinessMsgManage/addPromotion.jsp'"/>
										
											</td>

										</tr>
									</table>
								</form>
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
										<td height="30" align="center">
											<span style="color: #0afb92; font-size: 14px;">${Msg}</span>
											<span style="color: red; font-size: 13px;">${error}</span>
										</td>
									</tr>
									<tr>
										<td height="40" class="font42">

											<table width="100%" border="0" cellpadding="0"
												cellspacing="1" bgcolor="#cccccc" class="newfont03">
												<tr class="CTitle">
													<td height="35" colspan="9" align="center"
														style="font-size: 16px">
														促销详情
													</td>
												</tr>
												<tr bgcolor="#f9f9f9">
													
													<td width="9%" align="center" height="35">
														产品名称
													</td>
													<td width="9%" align="center">
														促销开始时间
													</td>
													<td width="9%" align="center">
														促销结束时间
													</td>
													<td width="9%" align="center">
														消费金额最低
													</td>
													<td width="9%" align="center">
														消费金额最高
													</td>
													<td width="9%" align="center">
														促销品名称
													</td>
													<td width="9%" align="center">
														促销品数量
													</td>
													<td width="14%" align="center">
														促销品备注
													</td>
													<td width="9%" align="center">
														操作
													</td>
												</tr>
												<s:iterator value="promotion" status="st">
													<tr bgcolor="#FFFFFF" height="20">
														<td align="center" height="20">
															<s:set name="Product_Name" value='promotion[#st.index][0]'></s:set>
															<s:set name="ProductNum" value='promotion[#st.index][8]'></s:set>
															${Product_Name}
														</td>
														<td align="center">
															<s:set name="Promotion_TimeStart" value='promotion[#st.index][1]'></s:set>
															${Promotion_TimeStart}
														</td>
														<td align="center">
															<s:set name="Promotion_TimeEnd" value='promotion[#st.index][2]'></s:set>
															${Promotion_TimeEnd}
														</td>
														<td align="center">
															<s:set name="Promotion_MoneyLow" value='promotion[#st.index][3]'></s:set>
															${Promotion_MoneyLow}
														</td>
														<td align="center">
															<s:set name="Promotion_MoneyHigh" value='promotion[#st.index][4]'></s:set>
															${Promotion_MoneyHigh}
														</td>
														<td align="center">
															<s:set name="PromotionInfo_Name" value='promotion[#st.index][5]'></s:set>
															${PromotionInfo_Name}
														</td>
														<td align="center">
															<s:set name="PromotionInfo_Count" value='promotion[#st.index][6]'></s:set>
															${PromotionInfo_Count}
														</td>
														<td align="center">
															<s:set name="PromotionInfo_Remark" value='promotion[#st.index][7]'></s:set>
															${PromotionInfo_Remark}
														</td>
														<td align="center">
															<a
																href="bos/delPromotion.action?ProductNum=${ProductNum}
																&Promotion_TimeStart=${Promotion_TimeStart}&Promotion_TimeEnd=${Promotion_TimeEnd}
																&Promotion_MoneyLow=${Promotion_MoneyLow}&Promotion_MoneyHigh=${Promotion_MoneyHigh}" 
																onclick="javascript:return ConfirmDel();">删除</a> 
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
													<td width="40%" align="right">
														[
														<a
															href="bos/selPromotion.action?page.pageNum=1&ProductNum=${ProductNum}"
															class="right-font08">首页</a> |
														<a
															href="bos/selPromotion.action?ProductNum=${ProductNum}&page.pageNum=<s:if test="page.pageNum>=2"><s:property value="page.pageNum-1"/></s:if><s:else><s:property value="page.pageNum"/></s:else>"
															class="right-font08">上一页</a> |
														<a
															href="bos/selPromotion.action?ProductNum=${ProductNum}&page.pageNum=<s:property value="page.pageNum+1>page.pages?page.pageNum:page.pageNum+1"/>"
															class="right-font08">下一页</a> |
														<a
															href="bos/selPromotion.action?ProductNum=${ProductNum}&page.pageNum=<s:property value="page.pages"/>"
															class="right-font08">末页</a>]&nbsp;&nbsp;
													</td>
													<form action="bos/selPromotion.action" onsubmit="return chenckPageNum();">
														<td width="7%" class="right-font08">
															转至：
															<input id="to_PageNum" name="to_PageNum" type="text"
																class="right-textfield03" size="1" />
															<input value="${ProductNum}" name="ProductNum"
																type="hidden">
														</td>
														<td width="3%">
															<input type="submit" class="right-button06" value="" />
														</td>
													</form>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="70">
											
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
