$(document).ready(function() {
	getFindType();
});
/*查询条件QueryType*/
var getFindType=function(){
	var type=$("#selType").val();
	if(type=='-1'){
		$("#selWord").attr("value", "不可用");
		$("#selWord").attr("disabled", "disabled");
	}else{
		$("#selWord").attr("value", "");
		$("#selWord").removeAttr("disabled");
	}
};