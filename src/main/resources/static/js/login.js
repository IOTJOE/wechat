function changeKaptcha(){
	$("#kaptcha").attr("src","/captchaImage/kaptcha?" + Math.floor(Math.random() * 100));
	$("#verify").select();
}


function dologout(){
	if (confirm("确定要退出？")){
		$.ajax({
			url:"/seller/logout",
			type: 'POST',
			contentType: "application/json",
			dataType: 'json',
			data: {},
			success:logoutOK(),
		});
	}
}


function logoutOK(){
	window.location.href = "http://bls.baojinsuo.cn/seller/login";
}

function pageWidthAll(){
  return $(window).width();
}
function pageHeight(){
  return $(window).height();
}
function hidepopmsg(){
	$("#ajaxmsg").html("");
	$("#ajaxshow").css("display","none");
}
function showpopmsg(msg,w,h,t){
	$("#ajaxmsg").html(msg);
	var left=parseInt((pageWidthAll()-w)/2,10);
	//alert(left);
	var top = (pageHeight()-h)/2 + $(document).scrollTop();
	//alert(top);
	$("#ajaxshow").css("left",left);
	$("#ajaxshow").css("top",top);
	$("#ajaxshow").css("width",w);
	$("#ajaxshow").css("height",h);
	$("#ajaxshow").css("display","block");
	//alert("4");
	if (t>0){
		setTimeout("hidepopmsg()",t);
	}
}