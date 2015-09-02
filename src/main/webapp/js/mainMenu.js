$(document).ready(function() {
	mainMenuOne();
});

function mainMenuOne(){
	var StaffNo=document.getElementById("StaffNo").value;
	var StaffPW=document.getElementById("StaffPW").value;
	$.get("./bosJson/leftList.action?StaffNo="+StaffNo+"&StaffPW="+StaffPW, null, function(date){
		var FunctionList=eval(date);

		var html="";
		var i=0;
		var k=0;
		
		html+='<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">';
		for(i=0;i<FunctionList.length;i++){	
			if(FunctionList[i].function_ParentNo==0){

				if(k%4==1 && k>=4){
					//每行的第一个（从第二行开始）
					html+='<tr>' +
					'<td align="center">'+
					'<br/>'+
					'<a onclick="MainMenuTwo('+FunctionList[i].function_FunctionNo+')" href="javascript:" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
					'	<img alt="" src="images/bos/'+FunctionList[i].function_FunctionNo+'.png" width="70" height="70" style="border:none;"/>'+
					'<br/>'+ 
					FunctionList[i].function_FunctionName +
					'</a>'+
					'</td>';
				}else if(k%4==0 && k>=4){
					//每行的最后一个
					html+='<td align="center">'+
					'<br/>'+
					'<a onclick="MainMenuTwo('+FunctionList[i].function_FunctionNo+')" href="javascript:" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
					'	<img alt="" src="images/bos/'+FunctionList[i].function_FunctionNo+'.png" width="70" height="70" style="border:none;"/>'+
					'<br/>'+ 
					FunctionList[i].function_FunctionName +
					'</a>'+
					'</td>'
					'</tr>';
				}else{
					html+='<td align="center">'+
					'<br/>'+
					'<a onclick="MainMenuTwo('+FunctionList[i].function_FunctionNo+')" href="javascript:" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
					'	<img alt="" src="images/bos/'+FunctionList[i].function_FunctionNo+'.png" width="70" height="70" style="border:none;"/>'+
					'<br/>'+ 
					FunctionList[i].function_FunctionName +
					'</a>'+
					'</td>';
				}
				k++;
			}
		}
		html+='</table>';
		document.getElementById("mainMenu").innerHTML=html;
		
	})
} 

function MainMenuTwo(id){
	var StaffNo=document.getElementById("StaffNo").value;
	var StaffPW=document.getElementById("StaffPW").value;
	$.get("./bosJson/leftList.action?StaffNo="+StaffNo+"&StaffPW="+StaffPW, null, function(date){
		var FunctionList=eval(date);
		var html="";
		var i=0;
		var k=0;
		html+='<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">';
		for(i=0;i<FunctionList.length;i++){	
			if(FunctionList[i].function_ParentNo==id){
				if(k%4==1 && k>=4){
					//每行的第一个（从第二行开始）
					html+='<tr>' +
					'<td align="center">'+
					'<br/>'+
					'<a href="'+FunctionList[i].function_URL+'" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
					'	<img alt="" src="images/bos/'+id+'.png" width="70" height="70" style="border:none;"/>'+
					'<br/>'+ 
					FunctionList[i].function_FunctionName +
					'</a>'+
					'</td>';
				}else if(k%4==0 && k>=4){
					//每行的最后一个
					html+='<td align="center">'+
					'<br/>'+
					'<a href="'+FunctionList[i].function_URL+'" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
					'	<img alt="" src="images/bos/'+id+'.png" width="70" height="70" style="border:none;"/>'+
					'<br/>'+ 
					FunctionList[i].function_FunctionName +
					'</a>'+
					'</td>'
					'</tr>';
				}else{
					html+='<td align="center">'+
					'<br/>'+
					'<a href="'+FunctionList[i].function_URL+'" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
					'	<img alt="" src="images/bos/'+id+'.png" width="70" height="70" style="border:none;"/>'+
					'<br/>'+ 
					FunctionList[i].function_FunctionName +
					'</a>'+
					'</td>';
				}
				k++;
			}							
		}
		
		//添加返回主页导航
		if(k%4==1 && k>=4){
			//每行的第一个（从第二行开始）
			html+='<tr>' +
			'<td align="center">'+
			'<br/>'+
			'<a onclick="mainMenuOne();" href="javascript:" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
			'	<img alt="" src="images/bos/daohang.png" width="70" height="70" style="border:none;"/>'+
			'<br/>' +
			'返回上级导航'+
			'</a>'+
			'</td>';
		}else if(k%4==0 && k>=4){
			//每行的最后一个
			html+='<td align="center">'+
			'<br/>'+
			'<a onclick="mainMenuOne();" href="javascript:" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
			'	<img alt="" src="images/bos/daohang.png" width="70" height="70" style="border:none;"/>'+
			'<br/>'+
			'返回上级导航'+
			'</a>'+
			'</td>'
			'</tr>';
		}else{
			html+='<td align="center">'+
			'<br/>'+
			'<a onclick="mainMenuOne();" href="javascript:" style="text-decoration: none;color: #000000;font-family: 黑体;font-size: 14px">'+
			'	<img alt="" src="images/bos/daohang.png" width="70" height="70" style="border:none;"/>'+
			'<br/>'+
			'返回上级导航'+
			'</a>'+
			'</td>';
		}
		html+='</table>';
		document.getElementById("mainMenu").innerHTML=html;
		
	})
	
}