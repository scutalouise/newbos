$(document).ready(function() {
	getFindType();
});
/*查询条件QueryType*/
var getFindType=function(){
	var type=$("#QueryType").val();
	$("#QueryType").parent().find("[name='QueryContext']").remove();
	
	var $queryContext = null;
	if(type == '4'){
		$queryContext = $("#templateRegion").children(":eq(1)").clone();
	}else{
		$queryContext = $("#templateRegion").children(":eq(0)").clone();
	}
	
	if(type=='0'){
		$queryContext.attr("value", "不可用");
		$queryContext.attr("disabled", "disabled");
	}
	
	$("#QueryType").after($queryContext);
	
	/*}else{
		$("#QueryContext").removeAttr("disabled");
		$("#QueryContext").attr("value", "");
	}*/
};