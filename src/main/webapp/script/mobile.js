$(function(){
	$("#button").click(function(){
		$.ajax({
			type: "post",
			data: $("#form1").serialize(),
			url: "user_update1",
			dataType : "json",
			success : function(data){
				if(data.code==1){
					alert("绑定成功");
				}else{
					alert("绑定失败,原因：" + data.msg);
					return false;
				}
			}
		});
	});
});

$(function(){
	
	//发ajax请求,当quicklogin页已加载，就要访问服务，查看自己是否登录
		
		$.ajax({
			url:'user_checklogin',
			dataType:'JSON',
			type:"post",
			success:function(data){
				if(data.code==1){
					
					if(data.obj.Utel != ""){
					$("#Utel").val(data.obj.Utel);
					}
				}
			}
		});
	});

