$(document).ready(function() {
	
});
function formatDate(date){
	var year = date.getFullYear();
	var month = date.getMonth()+1;    //js从0开始取 
	var date1 = date.getDate(); 
	var hour = date.getHours(); 
	var minutes = date.getMinutes(); 
	var second = date.getSeconds();
 	return year+"/"+month+"/"+date1+" "+hour+":"+minutes +":"+second+" ";
}
function getShiftNo() {
	var TransItem_ShiftDate = document.getElementById("TransItem_ShiftDate").value;
	var StationNo = document.getElementById("StationNo").value;
	if(null==TransItem_ShiftDate||""==TransItem_ShiftDate){
		alert("请选择班别日期");
	}else{
		/*班别信息*/
	$.get("./bos/selShiftNo.action?TransItem_ShiftDate=" + TransItem_ShiftDate
			+ "&StationNo=" + StationNo, null, function(date) {
		var ShiftNo = eval(date);
		try {
			$("#ShiftNo").empty(); //清空下拉列表
			for ( var i = 0; i < ShiftNo.length; i++) {
				if(ShiftNo[i].shift_EndTime==null){
					
				}else{
				
					$("#ShiftNo").append(
						"<option value='" + ShiftNo[i].shift_ShiftNo +"-"
						+formatDate(new Date(parseInt(ShiftNo[i].shift_StartTime)))+"-"+formatDate(new Date(parseInt(ShiftNo[i].shift_EndTime)))+ "'>"
								+ ShiftNo[i].shift_ShiftNo +"&nbsp;&nbsp;"+
								formatDate(new Date(ShiftNo[i].shift_StartTime))+"- "
								+formatDate(new Date(ShiftNo[i].shift_EndTime))+ "</option>");
				}
				
			}
		} catch (e) {
			return;
		}
	});
	}
}
function getMonthReportType(){
	var Type = document.getElementById("Type").value;
	if("1"==Type){
		$("#endDate").attr("disabled", "disabled");
		$("#endDate").attr("value", "不可用");
	}else if("2"==Type){
		$("#endDate").removeAttr("disabled");
		$("#endDate").attr("value", "");
	}else if("3"==Type){
		$("#endDate").attr("disabled", "disabled");
		$("#endDate").attr("value", "不可用");
	}
};

function getDetailsReportType(){
	var Type = document.getElementById("Type").value;
	if("1"==Type){
		$("#nozzleSpan").attr("style", "");
		$("#ProductSpan").attr("style", "");
		$("#cardNoSpan").attr("style", "");
		$("#nozzleNo").attr("style", "");
		$("#ProductNum").attr("style", "");
		$("#cardNo").attr("type", "text");
		$("#endDate").removeAttr("disabled");
		$("#endDate").attr("value", "");
		/*枪号*/
		$.get("./bosJson/selNozzle.action", null, function(date) {
			var Nozzle = eval(date);
			try{
				$("#nozzleNo").empty(); //清空下拉列表
				$("#nozzleNo").append("<option value='-1' selected='selected'>全部</option>");
				for(var i=0;i<Nozzle.length;i++){
					$("#nozzleNo").append("<option value='" + Nozzle[i].nozzleSetting_Phy_Noz + "'>" + Nozzle[i].nozzleSetting_Phy_Noz + "</option>");
				}
			}catch (e) {
				 alert(e);
				 return;
			 }
			
		})
		/*产品信息*/
		$.get("./bos/selProduct.action?ProductType=0", null, function(date){
			var ProductList=eval(date);
			try{
				$("#ProductNum").empty(); //清空下拉列表
				$("#ProductNum").append("<option value='-1' selected='selected'>全部</option>");
				for(var i=0;i<ProductList.length;i++){
					$("#ProductNum").append("<option value='" + ProductList[i].product_Num + "'>" + ProductList[i].product_Name + "</option>");
				}
			}catch (e) {
				 alert(e);
				 return;
			 }		
		});
	}else if("2"==Type){
		$("#endDate").attr("disabled", "disabled");
		$("#endDate").attr("value", "不可用");
		$("#nozzleSpan").attr("style", "display:none;");
		$("#ProductSpan").attr("style", "display:none;");
		$("#cardNoSpan").attr("style", "display:none;");
		$("#nozzleNo").attr("style", "display:none;");
		$("#ProductNum").attr("style", "display:none;");
		$("#cardNo").attr("type", "hidden");
	}else{
		$("#endDate").removeAttr("disabled");
		$("#endDate").attr("value", "");
		$("#nozzleSpan").attr("style", "display:none;");
		$("#ProductSpan").attr("style", "display:none;");
		$("#cardNoSpan").attr("style", "display:none;");
		$("#nozzleNo").attr("style", "display:none;");
		$("#ProductNum").attr("style", "display:none;");
		$("#cardNo").attr("type", "hidden");
	}
};
function getSellAnalyzeGraphType(){
	var Type = document.getElementById("Type").value;
	if("1"==Type||"2"==Type||"3"==Type){
		$("#startDate1").attr("type", "text");
		$("#startDate").attr("type", "hidden");
		$("#startDate").attr("value", "");
		$("#endDate").attr("type", "hidden");
		$("#endDate").attr("value", "");
		$("#split").css("display","none");
	}else if("4"==Type){
		$("#startDate1").attr("type", "hidden");
		$("#startDate1").attr("value", "");
		$("#startDate").attr("type", "text");
		$("#endDate").attr("type", "hidden");
		$("#endDate").attr("value", "");
		$("#split").css("display","none");
	}else{
		$("#startDate1").attr("type", "hidden");
		$("#startDate1").attr("value", "");
		$("#startDate").attr("type", "text");
		$("#endDate").attr("type", "text");
		$("#split").css("display","inline");
	}
};
function getsellDayType(cobj){
	var type = cobj.value;
	if("1"==type){
		$("#TransItem_ShiftDate").show();
		$("#endDate").hide();
		$("#endDateAttach").hide();
	}else if("2"==type){
		$("#TransItem_ShiftDate").show();
		$("#endDate").hide();
		$("#endDateAttach").hide();
	}else if("3"==type){
		$("#TransItem_ShiftDate").show();
		$("#endDate").show();
		$("#endDateAttach").show();
	}
};

function getStock_takingReportType(){
	var Type = document.getElementById("Type").value;
	if("1"==Type){
		$("#endDate").removeAttr("disabled");
		$("#endDate").attr("value", "");
	}else if("2"==Type){
		$("#endDate").removeAttr("disabled");
		$("#endDate").attr("value", "");
	}else if("3"==Type){
		$("#endDate").attr("disabled", "disabled");
		$("#endDate").attr("value", "不可用");
	}
};

/**
 * hos报表页面js
*/
function selStation(){
	/*枪号*/
	$.get("./hosJson/selStation.action", null, function(date) {
		var station = eval(date);
		try{
			$("#StationNo").empty(); //清空下拉列表
			$("#StationNo").append("<option value='-1_全部' selected='selected'>全部</option>");
			for(var i=0;i<station.length;i++){
				$("#StationNo").append("<option value='" + station[i].station_No + "_" +station[i].station_Name+"'>" + station[i].station_Name + "</option>");
			}
		}catch (e) {
			 alert(e);
			 return;
		 }
		
	})

}
		
