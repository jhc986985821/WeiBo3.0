// JavaScript Document

var Ulogontmp = "";//用户id的恢复变量
/* 提交表单是检测输入的数据是否正确 */
function checkForm(){
	var Ulogon = document.getElementById("Ulogon");
	var Upassword = document.getElementById("Upassword");
	if(Ulogon.value == "请输入正确邮箱|手机号码")//如果本身是提示语,则继续出错的提示
		return false;
	Ulogon.value = trim(Ulogon.value);//去除id框前后空格
	Ulogontmp = Ulogon.value;//将输入id传给变量
	/* 如果字符串里面有@字符,则相应进入邮箱检测环节,否则进入手机号检测*/
	if(Ulogontmp.indexOf("@")>-1){//如果里面有@符号
		/* 如果符合邮箱的规则,并且密码也符合长度的要求 */
		if(Ulogontmp.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/)!=null && trim(Upassword.value).length >=6){
			return true;
		}else{
			return false;
		}
	}else{//进入手机号处理流程
		/* 如果密码长度符合要求 并且也是11位的纯手机号,则返回正确 */
		if(Ulogontmp.match(/[^\d]/g)==null && Ulogontmp.length==11 && Upassword.value.length >=6){
			return true;
		}else{//否则进入出错提示
			if(Ulogontmp.match(/[^\d]/g)!=null || Ulogontmp.length<11){
				Ulogon.value = "请输入正确邮箱|手机号码"
				Ulogon.style.backgroundColor = "#B9E3AB"
			}
			return false;
		}
	}
}
/* 输入框获得焦点时,设置背景色,并且将原来的提示语放回到输入框中 */
function Ulogonfocus(){
	var Ulogon = document.getElementById("Ulogon");
	Ulogon.style.backgroundColor = "#FFF"
    Ulogon.value = Ulogontmp;
	Ulogon.select();
}
/* 密码框获得焦点时,就全选里面内容 */
function Upasswordfocus(){
	document.getElementById("Upassword").select();
}
/* 函数绑定,获得焦点事件,失去焦点事件,鼠标悬停事件,鼠标离开事件 */
window.onload = function(){
	document.getElementById("Ulogon").onmouseover = Ulogonfocus;
	document.getElementById("Ulogon").onfocus = Ulogonfocus;
	document.getElementById("Ulogon").onblur = checkForm;
	document.getElementById("Upassword").onmousemove = Upasswordfocus
}


$(function(){
	$("#button").click(function(){
		$.ajax({
			type: "post",
			data: $("#LoginForm").serialize(),
			url: "user_login",
			dataType : "json",
			success : function(data){
				if(data.code==1){
					alert("登录成功");
					location.href="CustomerIndex.html?user.Uid="+data.obj.Uid;
				}else{
					alert("登录失败,原因：" + data.msg);
					return false;
				}
			}
		});
	});
});
