$(document).ready(function() {
	selInfo();
});

function selInfo() {
	//促销品信息
	$.get("./bosJson/selPromotionInfo.action", null, function(date) {
		var Promotion = eval(date);
		try {
			$("#PromotionInfo").empty(); //清空下拉列表
			for ( var i = 0; i < Promotion.length; i++) {
				$("#PromotionInfo").append(
						"<option value='" + Promotion[i].promotionInfo_Num + "'>"
							+ Promotion[i].promotionInfo_Name+"（"+ Promotion[i].promotionInfo_Remark+ "）</option>");
			}
		} catch (e) {
			alert(e);
			return;
		}
	});
	
	/*产品信息*/
	$.get("./bos/selProduct.action?ProductType=0", null, function(date){
		var ProductList=eval(date);
		try{
				$("#ProductNum").empty(); //清空下拉列表
				 for(var i=0;i<ProductList.length;i++){
					$("#ProductNum").append("<option value='" + ProductList[i].product_Num + "'>" + ProductList[i].product_Name + "</option>");
				}
		}catch (e) {
			 alert(e);
			 return;
		 }		
	});
};