<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>修改功能</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${pageContext.request.contextPath}/css/form.css"
			type="text/css" rel="stylesheet">
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.10.1.js"
			type="text/javascript"></script>
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/rankmenu.js"
			type="text/javascript"></script>
		<script type="text/javascript">
			function ConfirmDel(){
			   var	FunctionName = document.getElementById("FunctionName").value;
			   if(FunctionName==null || FunctionName==""){
			   		alert("请选择需要修改的功能。");
			   		return false;
			   }else{
		   		if(confirm("您确定要修改本项功能？"))
		    		return true;
		  		else
		     		return false;
			   }
			   
			}
		</script>		
	</head>

	<body>
		<center>
			<div class="form">
				<h3>
					系统功能修改
				</h3>
				<span style="color: #0afb92; font-size: 14px;">${msg}</span>
				<span style="color: red; font-size: 13px;">${error}</span>
				
					<fieldset>
						<dl>
							<dt>
								<label>
									所属系统：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="SystemFlag" size="1" name="SystemFlag" style="width: 200px;">
                               	 	<option value="06" >Card系统</option>
                               	 	<option value="07">Hos总部系统</option>
                               	 	<option value="08" selected="selected">Bos站级系统</option>
                            	</select>&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
							<dl>
							<dt>
								<label>
									修改功能选择：
								</label>
							</dt>
							<div align="left">
							<dd>				
								<select id="SubNo1" size="1" name="SubNo1" style="width: 250px;" onfocus="getOneFunctionList();" onblur="getOneFunctionDetails();">
                               	 	<option value="-1" selected="selected">===========请选择===========</option>
                               	 	<!-- 一级功能加载 -->
                            	</select>
                            	<label>
									一级功能
								</label>
							</dd>
							</div>
							<div align="left">
							<dd>				
								<select id="SubNo2" size="1" name="SubNo2" style="width: 250px;" onfocus="getTwoFunctionList();" onblur="getTwoFunctionDetails();">
                               	 	<option value="-1" selected="selected">===========请选择===========</option>
                               	 	<!-- 二级功能加载 -->
                            	</select>
                            	<label>
									二级功能
								</label>
							</dd>	
							</div>					
							<dd>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
								<select id="SubNo3" size="1" name="SubNo3" style="width: 250px;"  onfocus="getThreeFunctionList();" onblur="getThreeFunctionDetails();">
                               	 	<option value="-1" selected="selected">===========请选择===========</option>
                               	 
                               	 	<!-- 三级功能加载 -->
                            	</select>
                            	<label>
									三级功能
								</label>
							</dd>					
							<dd>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
								<select id="SubNo4" size="1" name="SubNo4" style="width: 250px;" onfocus="getFourFunctionList();" onblur="getFourFunctionDetails();">
                               	 	<option value="-1" selected="selected">===========请选择===========</option>
                               	 	<!-- 四级功能加载 -->
                            	</select>
                            	<label>
									四级功能
								</label>
							</dd>
							
						</dl>
					
						<div align="center" style="margin-right: 130px">功能信息</div>
						<form action="updateFunction.action" class="niceform" method="post" onsubmit="return ConfirmDel();"> 
						<dl>
							<dt>
								<label>
									功能名称：
								</label>
							</dt>
							<div align="left">
							<dd>
								<input id="FunctionName" name="FunctionName" type="text" size="50">
								&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						<dl>
							<dt>
								<label>
									功能编号：
								</label>
							</dt>
							<div align="left">
							<dd>
								<input id="FunctionNo" name="FunctionNo" type="text" size="30" readonly="readonly">
							</dd>
							</div>
						</dl>
						<dl>
							<dt>
								<label>
									是否可用：
								</label>
							</dt>
							<div align="left">
							<dd>
								<select id="State" size="1" name="State" style="width: 200px;">
                               	 	
                            	</select>&nbsp;<span style="color: red">*</span>
                            </dd>
                            </div>
						</dl>
						
						<dl>
							<dt>
								<label>
									功能URL：
								</label>
							</dt>
							<div align="left">
							<dd>
								<input id="URL" name="URL" type="text" size="50">
								&nbsp;<span style="color: red">*</span>
							</dd>
							</div>
						</dl>
						<dl>
							<input type="submit" value="确认修改">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重置修改信息">
						</dl>
					</form>
				</fieldset>
			</div>
		</center>
	</body>
</html>
