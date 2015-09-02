$(document).ready(function() {
	getFunctionList();
})
function tupian(idt){
    var nametu="xiaotu"+idt;
    var tp = document.getElementById(nametu);
    tp.src="./images/bos/ico05.gif";//图片ico04为白色的正方形
	
	for(var i=1;i<2000;i++)
	{
	  
	  var nametu2="xiaotu"+i;
	  if(i!=idt*1)
	  {
	    var tp2=document.getElementById('xiaotu'+i);
		if(tp2!=undefined)
	    {tp2.src="./images/bos/ico06.gif";}//图片ico06为蓝色的正方形
	  }
	}
}

function list(idstr){
	var name1="subtree"+idstr;
	var name2="img"+idstr;
	var objectobj=document.all(name1);
	var imgobj=document.all(name2);
	//alert(imgobj);
	if(objectobj.style.display=="none"){
		for(i=1;i<2000;i++){
			var name3="img"+i;
			var name="subtree"+i;
			var o=document.all(name);
			if(o!=undefined){
				o.style.display="none";
				var image=document.all(name3);
				image.src="./images/bos/ico04.gif";
			}
		}
		objectobj.style.display="";
		imgobj.src="./images/bos/ico03.gif";
	}
	else{
		objectobj.style.display="none";
		imgobj.src="./images/bos/ico04.gif";
	}
}
function getFunctionList(){
	var StaffNo=document.getElementById("StaffNo").value;
	var StaffPW=document.getElementById("StaffPW").value;
	$.get("./bosJson/leftList.action?StaffNo="+StaffNo+"&StaffPW="+StaffPW, null, function(date){
		var FunctionList=eval(date);
		var html="";
		var i=0;
		var m=0;
		var j=0;
		var k=0;
		var OneList = new Array();
		for(i=0;i<FunctionList.length;i++){	
			if(FunctionList[i].function_ParentNo==0){
				 OneList[m]=FunctionList[i].function_FunctionNo;
				// alert(m+":"+OneList[m]);
				 m=m+1;
			 }
				 
		}
		for(i=0;i<FunctionList.length;i++){	
			if(FunctionList[i].function_ParentNo==0){
				html+='<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"'+
							'	class="left-table03">'+
							'	<tr>'+
							'		<td height="29">'+
							'			<table width="85%" border="0" align="center" cellpadding="0"'+
							'				cellspacing="0">'+
							'				<tr>'+
							'					<td width="8%">'+
							'						<img name="img'+FunctionList[i].function_FunctionNo+'" id="img'+FunctionList[i].function_FunctionNo+'" src="./images/bos/ico04.gif"'+
							'							width="8" height="11" />'+
							'					</td>'+
							'					<td width="92%">'+
							'						<a href="javascript:" target="mainFrame"'+
							'							class="left-font03" onClick="list('+FunctionList[i].function_FunctionNo+');">'+FunctionList[i].function_FunctionName+'</a>'+
							'					</td>'+
							'				</tr>'+
							'			</table>'+
							'		</td>'+
							'	</tr>'+
							'</TABLE>';
				
			
				html+='<table id="subtree'+FunctionList[i].function_FunctionNo+'" style="DISPLAY: none" width="80%" border="0"'+
						'	align="center" cellpadding="0" cellspacing="0"'+
						'	class="left-table02">';
						
				
			
				for(k=0;k<FunctionList.length;k++){		
					if(FunctionList[i].function_FunctionNo==FunctionList[k].function_ParentNo){
					
					html+='<tr>'+
						'	<td width="9%" height="20">'+
						'		<img id="xiaotu'+FunctionList[k].function_FunctionNo+'" src="./images/bos/ico06.gif" width="8"'+
						'			height="12" />'+
						'	</td>' +
						'	<td width="91%">'+
						'		<a href="'+FunctionList[k].function_URL+'" target="mainFrame" class="left-font04"'+
						'			onClick="tupian('+FunctionList[k].function_FunctionNo+');">'+FunctionList[k].function_FunctionName+'</a>'+
						'	</td>'+
						'  </tr>';	
				}			
			}
			html+='</table>';
		}							
	}
	document.getElementById("left").innerHTML=html;
		
	})
}



