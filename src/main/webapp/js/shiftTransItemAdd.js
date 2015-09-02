$(document).ready(function() {
	shiftNozzle();
});
function shiftNozzle(){
	var ShiftDate=document.getElementById("ShiftDate").value;
	var ShiftNo=document.getElementById("ShiftNo").value;
	var Shift_CreateTime=document.getElementById("Shift_CreateTime").value;
	var Shift_StartTime=document.getElementById("Shift_StartTime").value;
	var Shift_EndTime=document.getElementById("Shift_EndTime").value;
	var html="";
	var s="";
	var d=new Date();
	 s += d.getYear();  
	 s += (d.getMonth() + 1) ;  
	 s += d.getDate() ; 
	 s += d.getHours() ; 
	 s += d.getMinutes();  
	 s += d.getSeconds();   
	 s += d.getMilliseconds();
	$.get("./bosJson/selShiftNozzleJson.action?ShiftDate="+ShiftDate+"&ShiftNo="+ShiftNo+"&" +
	"Shift_CreateTime="+Shift_CreateTime+"&Shift_StartTime="+Shift_StartTime+"&Shift_EndTime="+Shift_EndTime+"&s="+s, null, function(date){
		var ShiftNozzle=eval(date);
		for(var i=0;i<ShiftNozzle.length;i++){
		$("#tianjia"+i+"").remove();//移除之前的元素
		html+='<tr bgcolor="#FFFFFF" id="tianjia'+i+'">'+
				'<td align="center" height="20">'+
					ShiftNozzle[i].nozzleId
				+'<input id="NozzleId'+ShiftNozzle[i].nozzleId+'" name="NozzleId'+ShiftNozzle[i].nozzleId+'" value="'+ShiftNozzle[i].nozzleId+'" type="hidden">'+
				'</td>'+
				'<td align="center">'+
					ShiftNozzle[i].productName
					+'<input id="ProductName'+ShiftNozzle[i].nozzleId+'" name="ProductName'+ShiftNozzle[i].nozzleId+'" value="'+ShiftNozzle[i].productName+'" type="hidden">'+
					'<input id="ProductNum'+ShiftNozzle[i].nozzleId+'" name="ProductNum'+ShiftNozzle[i].nozzleId+'" value="'+ShiftNozzle[i].productNum+'" type="hidden">'+
				'</td>'+
				'<td align="center">'+
					ShiftNozzle[i].startVol
				+'</td>'+
				'<td align="center">'+
					ShiftNozzle[i].endVol														
				+'</td>'+
				'<td align="center">'+
					ShiftNozzle[i].subtractionValue
				+'<input id="SubtractionValue'+ShiftNozzle[i].nozzleId+'" name="SubtractionValue'+ShiftNozzle[i].nozzleId+'" value="'+ShiftNozzle[i].subtractionValue+'" type="hidden">'+
				'</td>'+
				'<td align="center">';
				if(ShiftNozzle[i].subtractionValue > 0.1){
					html+='<a href="javascript:" onclick="javascript:return transItemAdd(NozzleId'+ShiftNozzle[i].nozzleId+'.value,ProductNum'+ShiftNozzle[i].nozzleId+'.value,ProductName'+ShiftNozzle[i].nozzleId+'.value,SubtractionValue'+ShiftNozzle[i].nozzleId+'.value);">补录交易</a>';
				}			
				html+='</td>'+
					'</tr>';	
		}

		$("#Nozzle").append(html);
	})
	
}


function transItemAdd(NozzleNo,ProductNum,ProductName,payVol){
	var StationNo=document.getElementById("StationNo").value;
	var ShiftDate=document.getElementById("ShiftDate").value;
	var ShiftNo=document.getElementById("ShiftNo").value;
	var Shift_EndTime=document.getElementById("Shift_EndTime").value;
	var s="";
	var d=new Date();
	 s += d.getYear();  
	 s += (d.getMonth() + 1) ;  
	 s += d.getDate() ; 
	 s += d.getHours() ; 
	 s += d.getMinutes();  
	 s += d.getSeconds();   
	 s += d.getMilliseconds();
	$.get("./bosJson/SellPrice.action?NozzleNo="+NozzleNo+"&s="+s, null, function(date){
		var SellPrice=eval(date);
		var total=Math.round(payVol*SellPrice*100)/100;
		  if(confirm("补录信息\n枪号："+NozzleNo+"\n产品名称："+ProductName+"\n产品单价："+SellPrice+"\n" +
		  "补录升数："+payVol+"\n补录金额："+total+"")){
			 $.get("./bosJson/saveTransItem.action?PayMoney="+total+"&PayVol="+payVol+"&ProductSellPrice="+SellPrice+"&NozzleNo="+NozzleNo+"&ShiftNo="+ShiftNo+"&ShiftDate="+ShiftDate+"&StationNo="+StationNo+"&ProductNum="+ProductNum+"&Shift_EndTime="+Shift_EndTime+"&s="+s, null, function(date){
				var cc=eval("("+date+")");
				document.getElementById("Msg").innerHTML=cc.Msg;
			 })
			 //刷新
			 shiftNozzle();
			 return true; 
		  }else{
			 return false;
		   }
		    
	})	
}

function onShiftVerify(){
 	var ShiftDate=document.getElementById("ShiftDate").value;
	var ShiftNo=document.getElementById("ShiftNo").value;
	var StationNo=document.getElementById("StationNo").value;
	var StaffNo=document.getElementById("StaffNo").value;
	var startDate=document.getElementById("startDate").value;
	var endDate=document.getElementById("endDate").value;
	var Shift_StartTime=document.getElementById("Shift_StartTime").value;
	var Shift_EndTime=document.getElementById("Shift_EndTime").value;
	var Shift_CreateTime=document.getElementById("Shift_CreateTime").value;
	$.get("./bosJson/selShiftNozzleJson.action?ShiftDate="+ShiftDate+"&ShiftNo="+ShiftNo+"&" +
		"Shift_CreateTime="+Shift_CreateTime+"&Shift_StartTime="+Shift_StartTime+"&Shift_EndTime="+Shift_EndTime, null, function(date){
		var ShiftNozzle=eval(date);
		var flag=true;
		var i;
		for(i=0;i<ShiftNozzle.length;i++){
			if(ShiftNozzle[i].subtractionValue<0.1){
				/**不做操作*/
			}else{
				flag=false;
			}	
		}
		if(flag){
			$.get("./bosJson/shiftVerify.action?StaffNo="+StaffNo+"&StationNo="+StationNo+"&ShiftDate="+ShiftDate+"&ShiftNo="+ShiftNo+"&Shift_CreateTime="+Shift_CreateTime, null,null)
			alert("日期："+ShiftDate+" 班别："+ShiftNo+" 班结审核通过。");
		}else{
			alert("存在未补录交易，不能通过审核。");
		}
	})
}


