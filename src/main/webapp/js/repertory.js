$(document).ready(function() {
	getProdunctList();
});
function getProdunctList(){
	var ProductType=document.getElementById("ProductType").value;
	var StationNo=document.getElementById("StationNo").value;
	/*班别编号*/
	$.get("./bosJson/selShiftNo1.action?StationNo="+StationNo, null, function(date){
		$("#ShiftNo").attr("value",date);
	});
	/*产品信息*/
	$.get("./bosJson/selProduct.action?ProductType="+ProductType, null, function(date){
		var ProductList=eval(date);
		try{
			var $ProductNum = $("#ProductNum");
			$ProductNum.empty(); //清空下拉列表
				for(var i=0;i<ProductList.length;i++){
					$ProductNum.append("<option value='" + ProductList[i].product_Num + "'>" + ProductList[i].product_Name + "</option>");
				}
				 $("#TankNum").empty(); //清空灌号下拉列表
		}catch (e) {
			 alert(e);
			 return;
		 }		
	});
	
	/*设置其他状态*/
	if("1"==ProductType){
		$("#WaterLevel").attr("disabled", "disabled");
		$("#WaterLevel").attr("value", "所选产品类型没有此属性");
		$("#PetrolLevel").attr("disabled", "disabled");
		$("#PetrolLevel").attr("value", "所选产品类型没有此属性");
		$("#TotalVol").attr("disabled", "disabled");
		$("#TotalVol").attr("value", "所选产品类型没有此属性");
		$("#Pressure").removeAttr("disabled"); 
		$("#Pressure").removeAttr("value");
	}else if("0"==ProductType){
		$("#WaterLevel").removeAttr("disabled");
		$("#WaterLevel").removeAttr("value");
		$("#PetrolLevel").removeAttr("disabled");
		$("#PetrolLevel").removeAttr("value");
		$("#TotalVol").removeAttr("disabled");
		$("#TotalVol").removeAttr("value");
		$("#Pressure").attr("disabled", "disabled");
		$("#Pressure").attr("value", "所选产品类型没有此属性");
	}else{
		alert("请选择产品类型。");
	}
	
};
function getTankList(){
	var ProductNum=document.getElementById("ProductNum").value;
	if(null==ProductNum||""==ProductNum){
		alert("请选择产品。");
	}else{
		/*灌信息*/
		$.get("./bosJson/selTank.action?ProductNum="+ProductNum, null, function(date){
			var TankList=eval(date);
			try{
				$("#TankNum").empty(); //清空下拉列表
				 for(var i=0;i<TankList.length;i++){
					$("#TankNum").append("<option value='" + TankList[i].tanksetting_TankNum + "'>" + TankList[i].tanksetting_TankNum + "</option>");
				}		
			}catch (e) {
				 alert(e);
				 return;
			}		
	});
	}	
}

