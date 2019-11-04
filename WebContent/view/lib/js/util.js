function aj(TYPE,URL,DATA,SUCCESS,ERROR){
	$.ajax({
        type:TYPE,
        url:URL,
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(DATA),
        dataType:"json",
        success:function(data){
        	SUCCESS(data);
        },
        error:function(){
        	if(typeof ERROR === "function"){
        		ERROR();
        	}
        },
    });
}
function ad(TYPE,URL,DATA,SUCCESS,ERROR){
	$.ajax({
        type:TYPE,
        url:URL,
        data:DATA,
        success:function(data){
        	SUCCESS(data);
        },
        error:function(){
        	if(typeof ERROR === "function"){
        		ERROR();
        	}
        },
    });
}
function af(TYPE,URL,DATA,SUCCESS,ERROR){
	$.ajax({
		type:TYPE,
        url:URL,
        data:DATA,
		processData: false,
	    contentType: false,
        success:function(data){
        	SUCCESS(data);
        },
        error:function(e){
        	if(typeof ERROR === "function"){
        		ERROR();
        	}else{
        		
        	}
        },
    });
}
function setCookie(name, value, date) {
	let oDate = new Date();
	oDate.setDate(oDate.getDate() + date);
	document.cookie = name + '=' + value + ';expires=' + oDate;
}
function getCookie(name){
	let arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
function delCookie(name){
	let exp = new Date();
	exp.setTime(exp.getTime() - 1);
	let cval=getCookie(name);
	if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}