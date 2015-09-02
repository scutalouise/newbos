$(document).ready(function() {
	getSupplierList();
});
function getSupplierList(){
	$.get("./bosJson/ProductSupplierJSON.action", null, function(date){
		var SupplierList=eval(date);
		try{
				$("#OrderBill_SupplierNum").empty(); //清空下拉列表
				 for(var i=0;i<SupplierList.length;i++){
					$("#OrderBill_SupplierNum").append("<option value='" + SupplierList[i].productSupplier_key + "'>" + SupplierList[i].productSupplier_Name + "</option>");
				}
		}catch (e) {
			 alert(e);
			 return;
		 }		
	});
}