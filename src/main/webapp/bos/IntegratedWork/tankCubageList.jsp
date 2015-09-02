<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/bos/common/commontop.jsp"%>
<c:forEach items="${referredCubageList}" var="referredCubage">
	<tr bgcolor="#f9f9f9" align="center">
		<td width="10%" align="center" height="30">
			${referredCubage.tankSetting.tanksetting_TankNum}</td>
		<td width="20%" align="center">${referredCubage.height}</td>
		<td width="20%">${referredCubage.cubage}</td>
		<td width="15%">${referredCubage.createdBy.staff_Name}</td>
		<td width="25%">${fn:substring(referredCubage.createdTime,0,19)}</td>
		<td><a onclick="delcurrentRow(this,${referredCubage.id})" href="javascript:void(0);" style="cursor: pointer;">删除 </a></td>
	</tr>
</c:forEach>
<tr style="display: none;">
<td><input name="totalPages" id="totalPages" value=${totalPages}  /></td>
</tr>
