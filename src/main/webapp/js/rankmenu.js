/**
 * @author zhulong
 */
//$(document).ready(function() {
//	
//})
/**
 * 添加功能时，上级功能列表
 * @return {TypeName} 
 */
function getParentList(){
	var SubNo=document.getElementById("SubNo").value;
	var SystemFlag=document.getElementById("SystemFlag").value;
	$.get("./authorityJson/selParentFunction.action?SubNo="+SubNo+"&SystemFlag="+SystemFlag, null, function(date){
		var FunctionList=eval(date);
		try{
			if(SubNo==1){
				 $("#ParentNo").empty(); //清空下拉列表
				 $("#ParentNo").append("<option value='0' selected='selected'>无上级</option>");
			}else{
				$("#ParentNo").empty(); //清空下拉列表
				 for(var i=0;i<FunctionList.length;i++){
					$("#ParentNo").append("<option value='" + FunctionList[i].function_FunctionNo + "'>" + FunctionList[i].function_FunctionName + "</option>");
				}	
			}	 
		}catch (e) {
			 alert(e);
			 return;
		 }		
	})
}


/**
 * 更新功能详情查询显示
 * @return {TypeName} 

function getUpdateFunctionDetails(){
	var FunctionNo=document.getElementById("FunctionNo").value;
	$.get("./authorityJson/selUpdateFunctionDetails.action?FunctionNo="+FunctionNo, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#FunctionName").val(""); //清空
			$("#FunctionNo").val(""); //清空
			$("#State").empty(); //清空
			$("#URL").val(""); //清空
			if(FunctionList[0].function_FunctionState==0){//给select添加内容用append
				$("#State").append("<option value='1' >是</option>" +
				"<option value='0' selected='selected'>否</option>");
			}else{
				$("#State").append("<option value='1' selected='selected'>是</option>" +
				"<option value='0'>否</option>");
			}
			$("#URL").val(FunctionList[0].function_URL);
			
			$("#FunctionName").val(FunctionList[0].function_FunctionName);//给文本框添加内内容用Val
			$("#FunctionNo").val(FunctionList[0].Function_functionNo);
			
			
		}catch (e) {
			 alert(e);
			 return;
		 }		
	})
}
 */

/*系统角色管理*/
/**
 * 组别角色查询List
 * @return {TypeName} 
 */
function getselSysRole(){
	var GroupNo=document.getElementById("GroupNo").value;
	$.get("./authorityJson/selSysRole.action?GroupNo="+GroupNo, null, function(date){
		var RoleList=eval(date);
		$("#RoleNo").empty(); //清空下拉列表
		for(var i=0;i<RoleList.length;i++){
			try{	
				$("#RoleNo").append("<option value='" + RoleList[i].role_RoleNo + "'>" + RoleList[i].role_RoleName + "</option>");		
			}catch (e) {
				alert(e);
				return;
			 }	
	}	
	})
}
function ResetRoleSel(){
	$("#GroupNo").empty(); //清空下拉列表
	$("#RoleNo").empty(); //清空下拉列表
}
/**
 * 组别角色查询List-显示组别下的所有角色
 * @return {TypeName} 
 */
function getselSysRole_add(){
	var GroupNo=document.getElementById("GroupNo").value;
	if(""==GroupNo||null==GroupNo){
		alert("请选择：角色‘所属组别’。");
	}else{
		$.get("./authorityJson/selSysRole.action?GroupNo="+GroupNo, null, function(date){
		var RoleList=eval(date);
		$("#GroupRoleShow").empty();
		for(var i=0;i<RoleList.length;i++){
			try{	
				$("#GroupRoleShow").append("<span>" + RoleList[i].role_RoleName + "</span>  ");		
			}catch (e) {
				alert(e);
				return;
			 }	
		}	
	 })
	}
}

/**
 * 系统角色更新详情查询显示
 * @return {TypeName} 
 */
function getUpdateRoleDetails(){
	var RoleNo=document.getElementById("RoleNo").value;
	$.get("./authorityJson/selSysRoleDetails.action?RoleNo="+RoleNo, null, function(date){
		var RoleList=eval(date);
		
			$("#RoleName").val(""); //清空
			$("#State").empty(); //清空
			$("#Remark").val(""); //清空
					
			$("#RoleName").val(RoleList[0].role_RoleName);//给文本框添加内内容用Val
			if(RoleList[0].role_RoleState==0){//给select添加内容用append
				$("#State").append("<option value='1' >是</option>" +
				"<option value='0' selected='selected'>否</option>");
			}else{
				$("#State").append("<option value='1' selected='selected'>是</option>" +
				"<option value='0'>否</option>");
			}
			$("#Remark").val(RoleList[0].role_Remark);
			
	})
}

/*组别管理*/
/**
 * 系统组别查询List
 * @return {TypeName} 
 */
function getselSysGroup(){
	var SystemFlag=document.getElementById("SystemFlag").value;
	$.get("./authorityJson/selSysGroup.action?SystemFlag="+SystemFlag, null, function(date){
		var GroupList=eval(date);
		$("#GroupNo").empty(); //清空下拉列表
		$("#RoleNo").empty(); //清空下拉列表
		for(var i=0;i<GroupList.length;i++){
			try{	
				$("#GroupNo").append("<option value='" + GroupList[i].group_GroupNo + "'>" + GroupList[i].group_GroupName + "</option>");		
			}catch (e) {
				alert(e);
				return;
			 }	
	}	
	})
}
function ResetRoleUpdate(){
	document.getElementById("RoleName").value="";
	$("#State").empty();
	document.getElementById("Remark").value="";
}
/**
 * 系统组别查询List-添加时显示系统组别
 * @return {TypeName} 
 */
function getselSysGroup_add(){
	var SystemFlag=document.getElementById("SystemFlag").value;
	if("-1"==SystemFlag||null==SystemFlag){
		alert("请选择系统类型");
	}else{
		$.get("./authorityJson/selSysGroup.action?SystemFlag="+SystemFlag, null, function(date){
		var GroupList=eval(date);
		$("#SysGroupShow").empty();
		for(var i=0;i<GroupList.length;i++){
			try{	
				$("#SysGroupShow").append("<span>" + GroupList[i].group_GroupName + "</span>  ");		
			}catch (e) {
				alert(e);
				return;
			 }	
		}	
	 })
	}
}
/**
 * 系统组别-更新详情查询显示
 * @return {TypeName} 
 */
function getUpdateGroupDetails(){
	var GroupNo=document.getElementById("GroupNo").value;
	$.get("./authorityJson/selSysGroupDetails.action?GroupNo="+GroupNo, null, function(date){
		var GroupList=eval(date);
		
			$("#GroupName").val(""); //清空
			$("#State").empty(); //清空
			$("#Remark").val(""); //清空
					
			$("#GroupName").val(GroupList[0].group_GroupName);//给文本框添加内内容用Val
			if(GroupList[0].group_GroupState==0){//给select添加内容用append
				$("#State").append("<option value='1' >是</option>" +
				"<option value='0' selected='selected'>否</option>");
			}else{
				$("#State").append("<option value='1' selected='selected'>是</option>" +
				"<option value='0'>否</option>");
			}
			$("#Remark").val(GroupList[0].group_Remark);
			
	})
}
function ResetGroupUpdate(){
	document.getElementById("GroupName").value="";
	$("#State").empty();
	document.getElementById("Remark").value="";
}
/**
 * 查询系统功能-用Tree呈现出来（Tree通过修改Dtree得到）
 */
function selSysFunction() {
	var SystemFlag = document.getElementById("SystemFlag").value;
	$.get("./authorityJson/selSysFunction.action?SystemFlag=" + SystemFlag,null, function(date) {
				t = new dTree("t");
				t.add(0, -1, '系统功能');
				var FunctionList = eval(date);
				for ( var i = 0; i < FunctionList.length; i++) {
					t.add(FunctionList[i].function_FunctionNo,
							FunctionList[i].function_ParentNo,
							FunctionList[i].function_FunctionName);
				}
				$("#functionTree").html(t.toString());
			})
}
/**
 * 查询角色功能
 */
function selRoleFunction(){
	var RoleNo=document.getElementById("RoleNo").value;
	$.get("./authorityJson/selRoleFunction.action?RoleNo="+RoleNo, null, function(date){
		var myinput = document.body.getElementsByTagName("input");
		var RoleFunctionList=eval(date);
		/**初始化复选框*/
		for(var j=0;j<myinput.length;j++){
				if(myinput[j].type=="checkbox" && myinput[j].name == "dTreeChk"){
					myinput[j].checked = false;			
			}
		}	
		/**已经存功能显示*/
		for(var i=0;i<RoleFunctionList.length;i++){
			for(var j=0;j<myinput.length;j++){
				if(myinput[j].type=="checkbox" && myinput[j].name == "dTreeChk"){
					if(myinput[j].id == (RoleFunctionList[i].function_FunctionNo+"-"+RoleFunctionList[i].function_ParentNo)){
						myinput[j].checked = true;
				}
			}
		}	
	}
		
	});
	
}
/**
 * 更新角色-功能关系
 */
function UpdateRoleFunction(){
	$("#RoleFunctionNo").val(getCheckedboxIds());
	var RoleNo=document.getElementById("RoleNo").value;
	var RoleFunctionNo=document.getElementById("RoleFunctionNo").value;
	try{
		$.get("./authorityJson/updateRoleFunction.action?RoleNo="+RoleNo+"&RoleFunctionNo="+RoleFunctionNo, null, function(date){
			$("#msg").html("修改成功！");
		})
	}catch(e){
		$("#error").html("修改失败！");
	}
	
	
}




/*功能情况查询*/
	

/**
 * 查询一级功能
 * @return {TypeName} 
 */
function getOneFunctionList(){
	var SystemFlag=document.getElementById("SystemFlag").value;
	$.get("./authorityJson/selDelFunction.action?SubNo="+1+"&SystemFlag="+SystemFlag, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#SubNo1").empty(); //清空下拉列表
			$("#SubNo2").empty(); //清空下拉列表
			$("#SubNo2").append("<option value='-1' selected='selected'>===========请选择===========</option>");
			$("#SubNo3").empty(); //清空下拉列表
			$("#SubNo3").append("<option value='-1' selected='selected'>===========请选择===========</option>");
			$("#SubNo4").empty(); //清空下拉列表
			$("#SubNo4").append("<option value='-1' selected='selected'>===========请选择===========</option>");
			for(var i=0;i<FunctionList.length;i++){
				$("#SubNo1").append("<option value='" + FunctionList[i].function_FunctionNo + "'>" + FunctionList[i].function_FunctionName + "</option>");
			}	
			
		}catch (e) {
			 alert(e);
			 return;
		 }		
	})
}

/**
 * 一级功能-详情查询显示
 * @return {TypeName} 
 */
function getOneFunctionDetails(){
	var FunctionNo=document.getElementById("SubNo1").value;
	$.get("./authorityJson/selUpdateFunctionDetails.action?FunctionNo="+FunctionNo, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#FunctionName").val(""); //清空
			$("#FunctionNo").empty(); //清空
			$("#SubNo").val(""); //清空
			
			$("#State").empty(); //清空
			$("#URL").val(""); //清空
			if(FunctionList[0].function_FunctionState==0){//给select添加内容用append
				$("#State").append("<option value='1' >是</option>" +
				"<option value='0' selected='selected'>否</option>");
			}else{
				$("#State").append("<option value='1' selected='selected'>是</option>" +
				"<option value='0'>否</option>");
			}
			$("#URL").val(FunctionList[0].function_URL);
			
			
			$("#FunctionName").val(FunctionList[0].function_FunctionName);//给文本框添加内内容用Val
			$("#FunctionNo").val(FunctionList[0].function_FunctionNo);//给文本框添加内内容用Val
			$("#SubNo").val(FunctionList[0].function_SubNo);//给文本框添加内内容用Val
			
		}catch (e) {
			 alert(e);
			 return;
		 }		
	})
}

/**
 * 查询二级功能
 * @return {TypeName} 
 */
function getTwoFunctionList(){
	var SystemFlag=document.getElementById("SystemFlag").value;
	var ParentNo=document.getElementById("SubNo1").value;
	if(null==ParentNo||"-1"==ParentNo){
		alert("请选择一级功能");
	}else{
		$.get("./authorityJson/selDelFunction.action?ParentNo="+ParentNo+"&SystemFlag="+SystemFlag, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#SubNo2").empty(); //清空下拉列表
			$("#SubNo3").empty(); //清空下拉列表
			$("#SubNo3").append("<option value='-1' selected='selected'>===========请选择===========</option>");
			$("#SubNo4").empty(); //清空下拉列表
			$("#SubNo4").append("<option value='-1' selected='selected'>===========请选择===========</option>");
			for(var i=0;i<FunctionList.length;i++){
				$("#SubNo2").append("<option value='" + FunctionList[i].function_FunctionNo + "'>" + FunctionList[i].function_FunctionName + "</option>");
			}	
			
		}catch (e) {
			
			 return;
		 }		
	})
	}
	
}

/**
 * 二级功能-详情查询显示
 * @return {TypeName} 
 */
function getTwoFunctionDetails(){
	var FunctionNo=document.getElementById("SubNo2").value;
	$.get("./authorityJson/selUpdateFunctionDetails.action?FunctionNo="+FunctionNo, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#FunctionName").val(""); //清空
			$("#FunctionNo").empty(); //清空
			$("#SubNo").val(""); //清空
			
			$("#State").empty(); //清空
			$("#URL").val(""); //清空
			if(FunctionList[0].function_FunctionState==0){//给select添加内容用append
				$("#State").append("<option value='1' >是</option>" +
				"<option value='0' selected='selected'>否</option>");
			}else{
				$("#State").append("<option value='1' selected='selected'>是</option>" +
				"<option value='0'>否</option>");
			}
			$("#URL").val(FunctionList[0].function_URL);
			
			$("#FunctionName").val(FunctionList[0].function_FunctionName);//给文本框添加内内容用Val
			$("#FunctionNo").val(FunctionList[0].function_FunctionNo);//给文本框添加内内容用Val
			$("#SubNo").val(FunctionList[0].function_SubNo);//给文本框添加内内容用Val
			
		}catch (e) {
			 
			 return;
		 }		
	})
}

/**
 * 查询三级功能
 * @return {TypeName} 
 */
function getThreeFunctionList(){
	var SystemFlag=document.getElementById("SystemFlag").value;
	var ParentNo=document.getElementById("SubNo2").value;
	if(null==ParentNo||"-1"==ParentNo){
		alert("请选择二级功能");
	}else{
		$.get("./authorityJson/selDelFunction.action?ParentNo="+ParentNo+"&SystemFlag="+SystemFlag, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#SubNo3").empty(); //清空下拉列表
			$("#SubNo4").empty(); //清空下拉列表
			$("#SubNo4").append("<option value='-1' selected='selected'>===========请选择===========</option>");
			for(var i=0;i<FunctionList.length;i++){
				$("#SubNo3").append("<option value='" + FunctionList[i].function_FunctionNo + "'>" + FunctionList[i].function_FunctionName + "</option>");
			}	
			
		}catch (e) {
			
			 return;
		 }		
	})
	}
	
}

/**
 * 三级功能-详情查询显示
 * @return {TypeName} 
 */
function getThreeFunctionDetails(){
	var FunctionNo=document.getElementById("SubNo3").value;
	$.get("./authorityJson/selUpdateFunctionDetails.action?FunctionNo="+FunctionNo, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#FunctionName").val(""); //清空
			$("#FunctionNo").empty(); //清空
			$("#SubNo").val(""); //清空
			
			$("#State").empty(); //清空
			$("#URL").val(""); //清空
			if(FunctionList[0].function_FunctionState==0){//给select添加内容用append
				$("#State").append("<option value='1' >是</option>" +
				"<option value='0' selected='selected'>否</option>");
			}else{
				$("#State").append("<option value='1' selected='selected'>是</option>" +
				"<option value='0'>否</option>");
			}
			$("#URL").val(FunctionList[0].function_URL);
			
			$("#FunctionName").val(FunctionList[0].function_FunctionName);//给文本框添加内内容用Val
			$("#FunctionNo").val(FunctionList[0].function_FunctionNo);//给文本框添加内内容用Val
			$("#SubNo").val(FunctionList[0].function_SubNo);//给文本框添加内内容用Val
			
		}catch (e) {
			 
			 return;
		 }		
	})
}

/**
 * 查询四级功能
 * @return {TypeName} 
 */
function getFourFunctionList(){
	var SystemFlag=document.getElementById("SystemFlag").value;
	var ParentNo=document.getElementById("SubNo3").value;
		if(null==ParentNo||"-1"==ParentNo){
		alert("请选择三级功能");
	}else{
		$.get("./authorityJson/selDelFunction.action?ParentNo="+ParentNo+"&SystemFlag="+SystemFlag, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#SubNo4").empty(); //清空下拉列表
			for(var i=0;i<FunctionList.length;i++){
				$("#SubNo4").append("<option value='" + FunctionList[i].function_FunctionNo + "'>" + FunctionList[i].function_FunctionName + "</option>");
			}	
			
		}catch (e) {
			
			 return;
		 }		
	})
	}
	
}

/**
 * 四级功能-详情查询显示
 * @return {TypeName} 
 */
function getFourFunctionDetails(){
	var FunctionNo=document.getElementById("SubNo4").value;
	$.get("./authorityJson/selUpdateFunctionDetails.action?FunctionNo="+FunctionNo, null, function(date){
		var FunctionList=eval(date);
		try{
			$("#FunctionName").val(""); //清空
			$("#FunctionNo").empty(); //清空
			$("#SubNo").val(""); //清空
			
			$("#State").empty(); //清空
			$("#URL").val(""); //清空
			if(FunctionList[0].function_FunctionState==0){//给select添加内容用append
				$("#State").append("<option value='1' >是</option>" +
				"<option value='0' selected='selected'>否</option>");
			}else{
				$("#State").append("<option value='1' selected='selected'>是</option>" +
				"<option value='0'>否</option>");
			}
			$("#URL").val(FunctionList[0].function_URL);
			
			$("#FunctionName").val(FunctionList[0].function_FunctionName);//给文本框添加内内容用Val
			$("#FunctionNo").val(FunctionList[0].function_FunctionNo);//给文本框添加内内容用Val
			$("#SubNo").val(FunctionList[0].function_SubNo);//给文本框添加内内容用Val
			
		}catch (e) {
			
			 return;
		 }		
	})
}


















