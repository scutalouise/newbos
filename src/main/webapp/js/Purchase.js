$(document).ready(function() {
	getProdunctList();
});
function getProdunctList(){
	var ProductType=document.getElementById("ProductType").value;
	var StationNo=document.getElementById("StationNo").value;
	/*产品信息*/
	$.get("./bos/selProduct.action?ProductType="+ProductType, null, function(date){
		var ProductList=eval(date);
		try{
				$("#ProductNum").empty(); //清空下拉列表
				 for(var i=0;i<ProductList.length;i++){
					$("#ProductNum").append("<option value='" + ProductList[i].product_Num + "'>" + ProductList[i].product_Name + "</option>");
				}
				$("#TankNum").empty(); //清空油罐下拉列表
				getTankCheckbox();
		}catch (e) {
			 alert(e);
			 return;
		 }		
	});
};
function getProdunctListSellPricePlan(){
	var ProductType=document.getElementById("ProductType1").value;
	/*产品信息*/
	$.get("./bos/selProduct.action?ProductType="+ProductType, null, function(date){
		var ProductList=eval(date);
		try{
				$("#ProductNum1").empty(); //清空下拉列表
				$("#ProductNum1").append("<option value='-1'>全部</option>");
				 for(var i=0;i<ProductList.length;i++){
					$("#ProductNum1").append("<option value='" + ProductList[i].product_Num + "'>" + ProductList[i].product_Name + "</option>");
				}
				getTankCheckbox(); 
		}catch (e) {
			 alert(e);
			 return;
		 }		
	});
};
function getTankList(){
	var ProductNum=document.getElementById("ProductNum").value;
	if(null==ProductNum||""==ProductNum){
		alert("请选择产品。");
	}else{
		/*灌信息*/
		$.get("./bos/selTank.action?ProductNum="+ProductNum, null, function(date){
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

function getTankCheckbox(){
	if($("#tankId").size()<1)return ;
	var ProductNum=document.getElementById("ProductNum").value;
	if(null==ProductNum||""==ProductNum){
		alert("请选择产品。");
	}else{
		/*灌信息*/
		$.get("./bos/selTank.action?ProductNum="+ProductNum, null, function(date){
			var TankList=eval(date);
			$("#tankId").empty(); //清空下拉列表
			try{
				 for(var i=0;i<TankList.length;i++){
					$("#tankId").append("<input type='checkbox' value='"+TankList[i].tanksetting_TankNum+"' name='TankNum'>"+TankList[i].tanksetting_TankNum+"</input>"); 	
				}		
			}catch (e) {
				 alert(e);
				 return;
			}		
	});
	}	
}

