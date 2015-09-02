<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/bos/common/commontop.jsp"%>
<c:forEach items="${cellList}" var="cell">
	<tr bgcolor="#f9f9f9" align="center">
		<td width="10%" align="center" height="30">
			${cell.productName}</td>
		<td width="20%" align="center">${cell.density_Density}</td>
		<td width="20%">${cell.densityManual}</td>
		<td width="15%">${fn:substring(cell.density_StartDate,0,19)}</td>
		<td width="25%">${fn:substring(cell.density_endDate,0,19)}</td>
		<td><a onclick="editCurrentRow(this,${cell.id},'${cell.densityManual}')" href="javascript:void(0);" style="cursor: pointer;">修正</a>
			<a onclick="updateCurrentRow(this,${cell.id},'${cell.density_Density}')" href="javascript:void(0);" style="cursor: pointer;display: none;">保存</a>
			<a onclick="cancelEditRow(this,'${cell.densityManual}')" href="javascript:void(0);" style="cursor: pointer;display: none;">取消</a>
		</td>
	</tr>
</c:forEach>
<tr style="display: none;">
<td><input name="totalPages" id="totalPages" value='${totalPages}' /></td>
</tr>
