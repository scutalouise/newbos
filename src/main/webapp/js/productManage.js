$(document).ready(function() {
	getTankList();
});

function getTankList(){
	tankNumReturn=$("#tankNumReturn").val();
	/*灌信息*/
	$.get("./bos/selTank_ChangePetrol.action", null, function(date){
		var TankList=eval(date);
		if(tankNumReturn!=null || tankNumReturn!=""){
			$("#TankNum").empty(); //清空下拉列表
			if(tankNumReturn=="-1"){
				$("#TankNum").append('<option value="-1" selected="selected">全部</option>');
			}else{
				$("#TankNum").append('<option value="-1">全部</option>');
			}
			for(var i=0;i<TankList.length;i++){
				if(tankNumReturn==TankList[i].tanksetting_TankNum){
					$("#TankNum").append("<option value='" + TankList[i].tanksetting_TankNum + "' selected='selected'>" + TankList[i].tanksetting_TankNum + "</option>");
				}else{
					$("#TankNum").append("<option value='" + TankList[i].tanksetting_TankNum + "'>" + TankList[i].tanksetting_TankNum + "</option>");
				}
				
			}		
		}else{
			try{
				$("#TankNum").empty(); //清空下拉列表
				$("#TankNum").append('<option value="-1">全部</option>');
				 for(var i=0;i<TankList.length;i++){
					$("#TankNum").append("<option value='" + TankList[i].tanksetting_TankNum + "'>" + TankList[i].tanksetting_TankNum + "</option>");
					}		
				}catch (e) {
					 alert(e);
					 return;
				}		
			}
	});
		
}

