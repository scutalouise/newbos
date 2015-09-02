$(document).ready(function() {
	OrderBillDetail();
});
function formatDate(date){
	var year = date.getFullYear();
	var month = date.getMonth()+1;    //js从0开始取 
	var date1 = date.getDate(); 
	var hour = date.getHours(); 
	var minutes = date.getMinutes(); 
	var second = date.getSeconds();
 	return year+"-"+month+"-"+date1+" "+hour+":"+minutes +":"+second+" ";
}

function OrderBillDetail(){
	var Num=document.getElementById("Num").value;
	$.get("./bosJson/selOrderBillDetail.action?OrderBillNum="+Num, null, function(date){
		var OrderBill=eval(date);
		try{
			 $("#OrderBillNum").html("&nbsp;"+OrderBill[0].orderBill_Num); 
			 $("#ProductName").html("&nbsp;"+OrderBill[1].product_Name);
			 $("#TankNum").html("&nbsp;"+OrderBill[0].orderBill_TankNum);
			 switch(OrderBill[0].orderBill_Unit){
			 	case '1' : 
			 		$("#Unit").html("&nbsp;立方米（m³）");
			 		$("#Unit1").attr("value","1");
			 		$("#AddVolume").html("&nbsp;"+OrderBill[0].orderBill_AddVolumeM);
			 		break;
			 	case '2' : 
			 		$("#Unit").html("&nbsp;升（L）");
			 		$("#Unit1").attr("value","2");
			 		$("#AddVolume").html("&nbsp;"+OrderBill[0].orderBill_AddVolumeL);
			 		break;
			 	case '3' : 
			 		$("#Unit").html("&nbsp;吨（t）");
			 		$("#Unit1").attr("value","3");
			 		$("#AddVolume").html("&nbsp;"+OrderBill[0].orderBill_AddWeight);
			 		break;
			 	default : 
			 		alert(OrderBill[0].orderBill_Unit);
			 		break;
			 }
			 if(!!OrderBill[0].orderBill_ExpectDate)
				 $("#ExpectDate").html("&nbsp;"+formatDate(new Date(OrderBill[0].orderBill_ExpectDate)));
			 $.get("./bosJson/staffSelAutoComplete.action?word="+OrderBill[0].orderBill_CreateStaffNo+"&selType="+2, null, function(date){
				 var Staff=eval(date);
				 $("#CreateStaffNo").html("&nbsp;"+Staff[0].staff_Name);
			 });
			 $("#AckDate").html("&nbsp;"+formatDate(new Date(OrderBill[0].orderBill_AckDate)));
			 $.get("./bosJson/staffSelAutoComplete.action?word="+OrderBill[0].orderBill_AckStaffNo+"&selType="+2, null, function(date){
				 var Staff=eval(date);
				 $("#AckStaffNo").html("&nbsp;"+Staff[0].staff_Name);
			 });
			 
			 switch(OrderBill[0].orderBill_Unit){
			 	case '1' : 
			 		$("#ActAddVolume").html("&nbsp;"+OrderBill[0].orderBill_ActVolumeM);
			 		break;
			 	case '2' : 
			 		$("#ActAddVolume").html("&nbsp;"+OrderBill[0].orderBill_ActVolumeL);
			 		break;
			 	case '3' : 
			 		$("#ActAddVolume").html("&nbsp;"+OrderBill[0].orderBill_ActWeight);
			 		break;
			 	default : 
			 		break;
			 }
			 $("#CostPrice").html("&nbsp;"+OrderBill[0].orderBill_CostPrice);
			 if(!!OrderBill[0].orderBill_ActPlanDate)
				 $("#ActPlanDate").html("&nbsp;"+formatDate(new Date(OrderBill[0].orderBill_ActPlanDate.time)));
			 $.get("./bosJson/staffSelAutoComplete.action?word="+OrderBill[0].orderBill_ActOrderStaffNo+"&selType="+2, null, function(date){
				 if(!date)return ;
				 var Staff=eval(date);
				 $("#ActOrderStaffNo").html("&nbsp;"+Staff[0].staff_Name);
			 });
			 $.get("./bosJson/staffSelAutoComplete.action?word="+OrderBill[0].orderBill_SignoffStaffNo+"&selType="+2, null, function(date){
				 var Staff=eval(date);
				 $("#SignoffStaffNo").html("&nbsp;"+Staff[0].staff_Name);
			 });
			 $("#RealityDate").html("&nbsp;"+formatDate(new Date(OrderBill[0].orderBill_RealityDate.time)));
		}catch (e) {
			// alert(e);
			 return;
		 }		
	});
};


