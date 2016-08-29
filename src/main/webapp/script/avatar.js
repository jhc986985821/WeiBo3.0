
$(function(){
	$("#button").click(function(){
		$.ajax({
			type: "post",
			data: $("#fileField").val(),
			url: "file_upload",
			dataType : "json",
			success : function(data){
				if(data.code==1){
					alert("上传成功");
				}else{
					alert("上传失败,原因：" + data.msg);
					return false;
				}
			}
		});
	});
});

$(function(){
	$("#button").click(function(){
		$.ajax({
			type: "post",
			data: $("#LoginForm").serialize(),
			url: "",
			dataType : "json",
			success : function(data){
				if(data.code==1){
					alert("上传成功");
				}else{
					alert("上传失败,原因：" + data.msg);
					return false;
				}
			}
		});
	});
});