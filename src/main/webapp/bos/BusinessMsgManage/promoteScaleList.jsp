<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/bos/common/commontop.jsp"%>
<c:forEach items="${promotionInfoList}" var="promotionInfo">
	<tr bgcolor="#f9f9f9" align="center">
		<td width="25%" align="center" height="30">
			${promotionInfo.promotionInfo_Name}</td>
		<td width="15%" align="center">${promotionInfo.promotionInfo_Count}</td>
		<td width="40%">${promotionInfo.promotionInfo_Remark}</td>
		<td><a onclick="delcurrentRow(this,${promotionInfo.promotionInfo_Num})" href="javascript:void(0);" style="cursor: pointer;">删除 </a></td>
	</tr>
</c:forEach>
<tr style="display: none;">
<td><input name="totalPages" id="totalPages" value=${totalPages}  /></td>
</tr>
