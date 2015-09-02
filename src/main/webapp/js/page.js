function chenckPageNum(){
	var pageNum=document.getElementById("to_PageNum").value;
	var pages=document.getElementById("pages").value;
	if(""==pageNum.replace(/(^\s*)|(\s*$)/g, "")){
		alert("请输入页数才可以进行跳转。");
		return false;
	}else if((pageNum>pages) || !(/^\d+$/.test(pageNum))){
		alert("请输入正确的页数[1-"+pages+"]才可以进行跳转。");
		return false;
	}else{
		return true;
	}
}