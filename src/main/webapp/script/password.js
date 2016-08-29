var userPass;//密码输入框对象
var userRpass;//重复密码输入框对象

function init(){
	userPass = document.getElementById("textfield2");
	userRpass = document.getElementById("textfield3");
}

/* 检测创建的密码,长度大于6位通过检测,否则则设置提示 */
function checkUserPass(imgobj,textobj){
	imgobj.style.visibility = "visible";
	textobj.value = trim(textobj.value);
	if(textobj.value.length<6){
		textobj.style.backgroundColor = "#B9E3AB";
		imgobj.src = "images/err.png";
		return false;
	}
	textobj.style.backgroundColor = "#fff";
	imgobj.src = "images/ok.png";
	passtmp = textobj.value;
	return true;
}
/* 重复密码和上面密码检测,如果对则通过检测,否则就不通过 */
function checkUserRpass(imgobj,textobj){//检测重复的密码
	imgobj.style.visibility = "visible";
	textobj.value = trim(textobj.value);
	if(textobj.value.length<6 || passtmp!=textobj.value){//密码长度大于等6位,并且和创建密码一样
		textobj.style.backgroundColor = "#B9E3AB";
		imgobj.src = "images/err.png";
		return false;
	}
	/* 通过检测的处理  */
	textobj.style.backgroundColor = "#fff";
	imgobj.src = "images/ok.png"; 
	return true;
}

window.onload = function(){
	init();//初始化对象
}

$(function(){
	$("#button").click(function(){
		$.ajax({
			type: "post",
			data: {'Upassword':$("#textfield1").val(),
				'password':$("#textfield2").val()
			},
			url: "user_pwd",
			dataType : "json",
			success : function(data){
				if(data.code==1){
					alert("修改成功");
				}else{
					alert("修改失败");
					return false;
				}
			}
		});
	});
});
