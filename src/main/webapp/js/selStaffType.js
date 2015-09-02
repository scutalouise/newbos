
var highlightindex = -1;
var timeoutId;
$(document).ready(function() {
	var selType=document.getElementById("selType").value;
	var wordInput;
	var wordInputOffset;
	if("1"==selType){
		/*员工名称*/
		document.getElementById("sel").innerHTML='<input type="text" name="Staff_Name" id="Staff_Name" size="10"/>';	
		wordInput = $("#Staff_Name");
   	    wordInputOffset = wordInput.offset();
   		 //自动补全框最开始应该隐藏起来
    	$("#auto").hide().css("border","1px black solid").css("position","absolute").css("background-color","white")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);
	}else if("2"==selType){
		/*员工编号*/
		document.getElementById("sel").innerHTML='<input type="text" name="Staff_No" id="Staff_No" size="14"/>';
		wordInput = $("#Staff_No");
   	    wordInputOffset = wordInput.offset();
   		 //自动补全框最开始应该隐藏起来
    	$("#auto").hide().css("border","1px black solid").css("position","absolute").css("background-color","white")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);
	}else if("3"==selType){
		/*员工身份证号*/
		document.getElementById("sel").innerHTML='<input type="text" name="Staff_ID" id="Staff_ID" size="18"/>';
		wordInput = $("#Staff_ID");
   	    wordInputOffset = wordInput.offset();
   		 //自动补全框最开始应该隐藏起来
    	$("#auto").hide().css("border","1px black solid").css("position","absolute").css("background-color","white")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);
	}else if("4"==selType){
		document.getElementById("sel").innerHTML='<select id="Statue" size="1" name="Statue" style="width: 90px;">' +
						'<option value="1" selected="selected">在职</option>' +
						'<option value="2">离职</option>' +
						'<option value="3">已换站</option>' +
						'</select>';
	}else{
		document.getElementById("sel").innerHTML='<input disabled="disabled" type="text" size="10"/>';
	}
})

/**
 * 根据不同类型，显示出不同的输入形式
 */
function getSelStaffType(){
	var selType=document.getElementById("selType").value;
	var wordInput;
	var wordInputOffset;
	if("1"==selType){
		/*员工名称*/
		document.getElementById("sel").innerHTML='<input type="text" name="Staff_Name" id="Staff_Name" size="10"/>';	
		wordInput = $("#Staff_Name");
   	    wordInputOffset = wordInput.offset();
   		 //自动补全框最开始应该隐藏起来
    	$("#auto").hide().css("border","1px black solid").css("position","absolute").css("background-color","white")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);
	}else if("2"==selType){
		/*员工编号*/
		document.getElementById("sel").innerHTML='<input type="text" name="Staff_No" id="Staff_No" size="14"/>';
		wordInput = $("#Staff_No");
   	    wordInputOffset = wordInput.offset();
   		 //自动补全框最开始应该隐藏起来
    	$("#auto").hide().css("border","1px black solid").css("position","absolute").css("background-color","white")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);
	}else if("3"==selType){
		/*员工身份证号*/
		document.getElementById("sel").innerHTML='<input type="text" name="Staff_ID" id="Staff_ID" size="18"/>';
		wordInput = $("#Staff_ID");
   	    wordInputOffset = wordInput.offset();
   		 //自动补全框最开始应该隐藏起来
    	$("#auto").hide().css("border","1px black solid").css("position","absolute").css("background-color","white")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);
	}else if("4"==selType){
		document.getElementById("sel").innerHTML='<select id="Statue" size="1" name="Statue" style="width: 90px;">' +
						'<option value="1" selected="selected">在职</option>' +
						'<option value="2">离职</option>' +
						'<option value="3">已换站</option>' +
						'</select>';
	}else{
		document.getElementById("sel").innerHTML='<input disabled="disabled" type="text" size="10"/>';
	}
		//给文本框添加键盘按下并弹起的事件
    wordInput.keyup(function(event) {
        //处理文本框中的键盘事件
        var myEvent = event || window.event;
        var keyCode = myEvent.keyCode;
        //如果输入的是字母，应该将文本框中最新的信息发送给服务器
        //如果输入的是退格键或删除键，也应该将文本框中的最新信息发送给服务器
        if (keyCode != 40 && keyCode != 38 && keyCode !=13) {
            //1.首先获取文本框中的内容
        	var wordText="";
        	var autoNode = $("#auto");
        	if("1"==selType){
        		 wordText = $("#Staff_Name").val();
        		 clearTimeout(timeoutId);
        		 timeoutId = setTimeout(function(){
        			 $.get("./bosJson/staffSelAutoComplete.action?word="+wordText+"&selType="+selType, null, function(date){
            		var jqueryObj=eval(date);
            		autoNode.html("");
            		for(var i=0;i<jqueryObj.length;i++){
            			var newDivNode = $("<div>").attr("id",jqueryObj[i].staff_No);	
            			newDivNode.html(jqueryObj[i].staff_Name).appendTo(autoNode);
            			newDivNode.mouseover(function(){
	            			if (highlightindex != -1) {
			                   	//如果原来存在高亮节点，则将背景色改称白色
			                    $("#auto").children("div").eq(highlightindex).css("background-color","white");
			               	 }
	            			 highlightindex = $(this).attr("id");
	            			 $(this).css("background-color","red");
            			});
            			newDivNode.mouseout(function(){
            			 	$(this).css("background-color","white");
            			});
            			newDivNode.click(function(){
            				//取出高亮节点的文本内容
			                var comText = $(this).text();
			                $("#auto").hide();
			                highlightindex = -1;
			                //文本框中的内容变成高亮节点的内容
                			$("#Staff_Name").val(comText);
                			$("#Staff_Name").get(0).blur();
            			});
            			
            		}
            		 //如果服务器段有数据返回，则显示弹出框
                    if (jqueryObj.length > 0) {
                        autoNode.show();
                    } else {
                        autoNode.hide();
                        //弹出框隐藏的同时，高亮节点索引值也制成-1
                        highlightindex = -1;
                    }
            	});
        			 
        	  },1000);
        		 
        	}else if("2"==selType){
        		 wordText = $("#Staff_No").val();
        		 clearTimeout(timeoutId);
        		 timeoutId = setTimeout(function(){
        			 $.get("./bosJson/staffSelAutoComplete.action?word="+wordText+"&selType="+selType, null, function(date){
            		var jqueryObj=eval(date);
            		autoNode.html("");
            		for(var i=0;i<jqueryObj.length;i++){
            			var newDivNode = $("<div>").attr("id",jqueryObj[i].staff_No);	
            			newDivNode.html(jqueryObj[i].staff_No).appendTo(autoNode);
            			newDivNode.mouseover(function(){
	            			if (highlightindex != -1) {
			                   	//如果原来存在高亮节点，则将背景色改称白色
			                    $("#auto").children("div").eq(highlightindex).css("background-color","white");
			               	 }
	            			 highlightindex = $(this).attr("id");
	            			 $(this).css("background-color","red");
            			});
            			newDivNode.mouseout(function(){
            			 	$(this).css("background-color","white");
            			});
            			newDivNode.click(function(){
            				//取出高亮节点的文本内容
			                var comText = $(this).text();
			                $("#auto").hide();
			                highlightindex = -1;
			                //文本框中的内容变成高亮节点的内容
                			$("#Staff_No").val(comText);
                			$("#Staff_No").get(0).blur();
            			});
            			
            		}
            		 //如果服务器段有数据返回，则显示弹出框
                    if (jqueryObj.length > 0) {
                        autoNode.show();
                    } else {
                        autoNode.hide();
                        //弹出框隐藏的同时，高亮节点索引值也制成-1
                        highlightindex = -1;
                    }
            	});
        			 
        	  },1000);
        	}else if("3"==selType){
        		 wordText = $("#Staff_ID").val();
        		 clearTimeout(timeoutId);
        		 timeoutId = setTimeout(function(){
        			 $.get("./bosJson/staffSelAutoComplete.action?word="+wordText+"&selType="+selType, null, function(date){
            		var jqueryObj=eval(date);
            		autoNode.html("");
            		for(var i=0;i<jqueryObj.length;i++){
            			var newDivNode = $("<div>").attr("id",jqueryObj[i].staff_No);	
            			newDivNode.html(jqueryObj[i].staff_ID).appendTo(autoNode);
            			newDivNode.mouseover(function(){
	            			if (highlightindex != -1) {
			                   	//如果原来存在高亮节点，则将背景色改称白色
			                    $("#auto").children("div").eq(highlightindex).css("background-color","white");
			               	 }
	            			 highlightindex = $(this).attr("id");
	            			 $(this).css("background-color","red");
            			});
            			newDivNode.mouseout(function(){
            			 	$(this).css("background-color","white");
            			});
            			newDivNode.click(function(){
            				//取出高亮节点的文本内容
			                var comText = $(this).text();
			                $("#auto").hide();
			                highlightindex = -1;
			                //文本框中的内容变成高亮节点的内容
                			$("#Staff_ID").val(comText);
                			$("#Staff_ID").get(0).blur();
            			});
            			
            		}
            		 //如果服务器段有数据返回，则显示弹出框
                    if (jqueryObj.length > 0) {
                        autoNode.show();
                    } else {
                        autoNode.hide();
                        //弹出框隐藏的同时，高亮节点索引值也制成-1
                        highlightindex = -1;
                    }
            	});
        			 
        	  },1000);
        	}else {
                autoNode.hide();
                highlightindex = -1;
            }
            
        } else if ((keyCode == 38 || keyCode == 40) && keyCode!=13) {
            //如果输入的是向上38向下40按键
            if (keyCode == 38) {
                //向上
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {
                    //如果原来存在高亮节点，则将背景色改称白色
                    autoNodes.eq(highlightindex).css("background-color","white");
                    highlightindex--;
                } else {
                    highlightindex = autoNodes.length - 1;    
                }
                if (highlightindex == -1) {
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素
                    highlightindex = autoNodes.length - 1;
                }
                //让现在高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color","red");
            }
            if (keyCode == 40) {
                //向下
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {
                    //如果原来存在高亮节点，则将背景色改称白色
                    autoNodes.eq(highlightindex).css("background-color","white");
                }
                highlightindex++;
                if (highlightindex == autoNodes.length) {
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素
                    highlightindex = 0;
                }
                //让现在高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color","red");
            }
        } else if (keyCode == 13 && !(keyCode == 38 || keyCode == 40)) {
            //如果输入的是回车
            //下拉框有高亮内容
            if (highlightindex != -1) {
                //取出高亮节点的文本内容
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();
                //文本框中的内容变成高亮节点的内容
                if("1"==selType){
                	$("#Staff_Name").val(comText);
                	 highlightindex = -1;
                }else if("2"==selType){
                	 $("#Staff_No").val(comText);
                	 highlightindex = -1;
                }else if("3"==selType){
                	 $("#Staff_ID").val(comText);
                	 highlightindex = -1;
                }
            } else {
                //下拉框没有高亮内容
                $("#auto").hide();
                if("1"==selType){
                	$("#Staff_Name").get(0).blur();
                }else if("2"==selType){
                	$("#Staff_No").get(0).blur();
                }else if("3"==selType){
                	$("#Staff_ID").get(0).blur();
                }
                
            }
        }
    });
}

function completed(wordInput){

}


