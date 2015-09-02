$(document).ready(function() {
	getProdunctListSellPricePlan();
});
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
		}catch (e) {
			 alert(e);
			 return;
		 }		
	});
};