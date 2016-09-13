  $('#tabs').tabs();
 
  var tabs = $( "#tabs" ).tabs({
      event: "mouseover",
      collapsible:true
  });
  tabCounter = 2;
  tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>a</span></li>"
	  function addTab(title,oid) {
	    	var label = title;
	        id = "tabs-" + oid;
	        
	        if($("#"+id).val()!=null){
	        	$("#tabs").selectTabByID(id);
	        }else{
	        li = $(  tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) );
	        var tabContentHtml;
	        tabs.find( ".ui-tabs-nav" ).append( li );
	        tabs.append( "<div id='" + id + "' style='height:500px;'></div>" );
	        tabs.tabs( "refresh" );
	        $("#tabs").selectTabByID(id);
	        }
	        if(title=="网站标题"){
	        	var t=sessionStorage.getItem("title");
	        	tabContentHtml='<h1>'+t+'</h1><button type="button" class="btn btn-warning" title="更改">更改</button>';
	        	$('#'+id).html(tabContentHtml);
	        }else if("关键字"==title){
	        	
	        }else if("图标管理"==title){
	        	
	        }else if(title=="服务器信息"){
	        	fetch('systemInfo',{
	        		 method: 'get', 
	        		 headers: { 
	        		      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8" 
	        		    },  
	        	}).then(function(res) {
	        	    if (res.ok) {
	        	        res.json().then(function(o) {
	        				tabContentHtml='<div class="table-responsive"><table class="table">'
	        				+ '<thead><tr><th>说明</th><th>信息</th></tr></thead><tbody>'
	        				+'<tr><td>java运行版本</td><td>'+o.jversion+'</td></tr>'
	        				+'<tr><td>操作系统名称</td><td>'+o.osname+'</td></tr>'
	        				+'<tr><td>操作系统构架</td><td>'+o.osarch+'</td></tr>'
	        				+'<tr><td>操作系统版本</td><td>'+o.osversion+'</td></tr>'
	        				+'<tr><td>用户账户名称</td><td>'+o.username+'</td></tr>'
	        				+'<tr><td>服务器ip地址</td><td>'+o.ip+'</td></tr>'
	        				+'<tr><td>系统当前时间</td><td>'+o.os_date+'</td></tr></tbody>';
	        				tabContentHtml+='</table></div>';
	         				$('#'+id).html(tabContentHtml);
	        			});
	        		}
	        	});
	        }else if("版权管理"==title){
	        	tabContentHtml='版权管理';
	        	$('#'+id).html(tabContentHtml);
	        }else if("logo"==title){
	        	
	        }else if("友情链接"==title){
	        	
	        }else if("商品上架"==title){
	        	tabContentHtml='<form class="form-horizontal" name="myform" id="myform" method="post" enctype="multipart/form-data">'
	            +'<fieldset><div id="legend" class="">'
	            +'<legend class="">商品上架</legend></div><input type="hidden" name="op" id="op" value="addGood"/>'
	            +'<div class="control-group">'
	            +'<label class="control-label" for="fname">菜名</label>'
	            +'<div class="controls"><input id="fname" placeholder="输入菜名" name="fname" value="" class="input-xlarge" type="text"></div></div>'
	            +'<div class="control-group"><label class="control-label" for="normprice">原价</label>'
	            + '<div class="controls"><input placeholder="商品原价" name="normprice" id="normprice"  class="input-xlarge" type="text" > </div></div>'
	            +'<div class="control-group"><label class="control-label" for="realprice">折后价</label>'
	            +'<div class="controls"><input placeholder="输入折后价" name="realprice" id="realprice" class="input-xlarge" type="text" > </div> </div>'   
	            +'<div class="control-group"><label class="control-label" for="detail">商品详情</label>'
	            +'<div class="controls"><div class="textarea"  ><textarea placeholder="输入商品详情" name="detail" id="detail" class="form-control" > </textarea></div></div></div>'   
	            +'<div class="control-group"><label class="control-label">商品图片</label><div class="controls">'    
	            +'<input class="input-file" name="fphoto" id="fphoto" type="file" ></div></div></fieldset></form>';
	        	tabContentHtml+='<br><br><button type="button" class="btn btn-info" onclick="submit()" >提交</button>';
	        	$('#'+id).html(tabContentHtml);
	        	$("#myform").validate({
	        		rules:{
	        			fname:"required",
	        			normprice:{
	        				required:"required",
	        				min:5
	        			},
	        			realprice:{
	        				required:"required",
	        				min:3
	        			},
	        			fphoto:{
	        				required:true,
	        				extension: "jpg|png|jepg|gif"
	        			}
	        		}
	        	});
	        	//打字特效
	        	  superplaceholder({
	        			el:fname,
	        			sentences: [ '请您输入商品名称', '例：西瓜炒蛋', '癞蛤蟆上树' ],
	        			options: {
	        				loop: true,
	        				letterDelay:70,
	        				showCursor: true,
	        		        // String to show as cursor
	        		        cursor: '☆'
	        			}
	        		});
	        	  superplaceholder({
	        			el:normprice,
	        			sentences: [ '这个价格只是参考参考','最低5元起', '最高无上限' ],
	        			options: {
	        				loop: true,
	        				letterDelay:70,
	        				showCursor: true,
	        		        // String to show as cursor
	        		        cursor: '☆'
	        			}
	        		});
	        	  superplaceholder({
	        			el:realprice,
	        			sentences: [ '老板打个5折咯', '行不行呀','可以低至3元！' ],
	        			options: {
	        				loop: true,
	        				letterDelay:70,
	        				showCursor: true,
	        		        // String to show as cursor
	        		        cursor: '☆'
	        			}
	        		});
	        	 
	        }else if("商品下架"==title){
	        	$('#'+id).append('<table id="table1"></table>');
	        	$('#table1').bootstrapTable({
	        	    url:'../manager.action',
	        	    dataType:"json",
	        	    contentType: "application/x-www-form-urlencoded",
	        	    method: 'get', 
	        	    pagination: true, //分页
	        	    singleSelect:false,
	        	    striped: true,	 //使表格带有条纹
	        	    pagination: true,	//在表格底部显示分页工具栏
	        	    pageSize:5,
	        	    pageNumber:1,
	        	    queryParams:'op=showGoods',//参数
	        	    showColumns: true,
	        	    showRefresh: true,
	        	    pageList: [5, 6, 7],
	        	    search: true,
	        	    striped:true,
	        	    uniqueId: "ID",
	        	    minimumCountColumns: 2,
	        	    clickToSelect: true,
	        	    smartDisplay:true,
	        	    sidePagination:"client",
	        	    columns: [{
	        	        field:'fid',
	        	        title:'商品编号'
	        	    }, {
	        	        field:'fname',
	        	        title:'商品名称',
	        	    }, {
	        	        field:'normprice',
	        	        title:'原价',
	        	    },{
	        	        field:'realprice',
	        	        title:'现价',
	        	    }, 
	        	    {
	        	        field:'detail',
	        	        title:'详情',
	        	    }, 
	        	    {
	        	        field:'fphoto',
	        	        title:'图片',
	        	        formatter:function(value,row,index){  
	                        var d = '<img src="../images/'+value+'" width="60px" height="50px"/>';  
	                             return d;  
	                         } 
	        	    }, 
	        	    {
	        	    	field: 'operate',
	                    title: '操作',
	                    align: 'center',
	                    width: 100,
	                    formatter:function(value,row,index){  
	                        var d = '<button type="button" class="btn btn-warning" style="text-shadow: black 5px 3px 3px;"><span class="glyphicon glyphicon-remove"></span>下架</button>';  
	                             return d;  
	                         } 
	        	    }, 
	        	    ]
	        	});
	        	
	        }else if("修改商品信息"==title){
	        	$('#'+id).append('<table id="table2"></table>');
	        	$('#table2').bootstrapTable({
	        	    url:'../manager.action',
	        	    dataType:"json",
	        	    contentType: "application/x-www-form-urlencoded",
	        	    method: 'get', 
	        	    pagination: true, //分页
	        	    singleSelect:false,
	        	    striped: true,	 //使表格带有条纹
	        	    pagination: true,	//在表格底部显示分页工具栏
	        	    pageSize:5,
	        	    pageNumber:1,
	        	    queryParams:'op=showGoods',//参数
	        	    showColumns: true,
	        	    showRefresh: true,
	        	    pageList: [5, 6, 7],
	        	    uniqueId: "ID",
	        	    minimumCountColumns: 2,
	        	    clickToSelect: true,
	        	    smartDisplay:true,
	        	    sidePagination:"client",
	        	    columns: [{
	        	        field:'fid',
	        	        title:'商品编号'
	        	    }, {
	        	        field:'fname',
	        	        title:'商品名称',
	        	        editable:true
	        	    }, {
	        	        field:'normprice',
	        	        title:'原价',
	        	        editable:true
	        	    },{
	        	        field:'realprice',
	        	        title:'现价',
	        	        editable:true
	        	    }, 
	        	    {
	        	        field:'detail',
	        	        title:'详情',
	        	        editable:true
	        	    }, 
	        	    {
	        	        field:'fphoto',
	        	        title:'图片',
	        	        formatter:function(value,row,index){  
	                        var d = '<img src="../images/'+value+'" width="60px" height="50px"/>';  
	                             return d;  
	                         } 
	        	    }, 
	        	    {
	        	    	field: 'operate',
	                    title: '操作',
	                    align: 'center',
	                    width: 100,
	                    formatter:function(value,row,index){  
	                        var d = '<button type="button" class="btn btn-info" style="text-shadow: black 5px 3px 3px;"><span class="glyphicon glyphicon-pencil"></span>修改</button>';  
	                             return d;  
	                         } 
	        	    }, 
	        	    ]
	        	});
	        }else if("统计"==title){
	        	
	        }else if("查看"==title){
	        	$('#'+id).append('<table id="table2"></table>');
	        	$('#table2').bootstrapTable({
	        	    url:'showUser',
	        	    dataType:"json",
	        	    contentType: "application/x-www-form-urlencoded",
	        	    method: 'post', 
	        	    pagination: true, //分页
	        	    singleSelect:false,
	        	    striped: true,	 //使表格带有条纹
	        	    pagination: true,	//在表格底部显示分页工具栏
	        	    pageSize:7,
	        	    pageNumber:1,
	        	    showColumns: true,
	        	    showRefresh: true,
	        	    pageList: [5, 6, 7],
	        	    uniqueId: "ID",
	        	    minimumCountColumns: 2,
	        	    clickToSelect: true,
	        	    smartDisplay:true,
	        	    sidePagination:"client",
	        	    showExport: true,                     //是否显示导出
	                exportDataType: "basic",  
	        	    columns: [{
	        	        field:'Uid',
	        	        title:'客户编号'
	        	    }, {
	        	        field:'Uname',
	        	        title:'客户昵称',
	        	    }, {
	        	        field:'Utel',
	        	        title:'客户电话',
	        	    },{
	        	    	field:'Ulogon',
	        	        title:'客户邮箱',
	        	    },{
	        	    	field:'Region',
	        	        title:'客户地址',
	        	    },{
	        	    	field:'Ublog',
	        	        title:'客户主页',
	        	    },{
	        	    	field:'Udatetime',
	        	        title:'注册时间',
	        	    },{
	        	    	field:'birthString',
	        	        title:'客户生日',
	        	    }
	        	    ]
	        	});
	        }else if("分析"==title){
	        	
	        }else if("生日祝福"==title){
	        	
	        }else if("显示订单"){
	        	$('#'+id).append('<table id="table3"></table>');
	        	$('#table3').bootstrapTable({
	        	    url:'../manager.action',
	        	    dataType:"json",
	        	    contentType: "application/x-www-form-urlencoded",
	        	    method: 'get', 
	        	    pagination: true, //分页
	        	    singleSelect:false,
	        	    striped: true,	 //使表格带有条纹
	        	    pagination: true,	//在表格底部显示分页工具栏
	        	    pageSize:6,
	        	    pageNumber:1,
	        	    queryParams:'op=showOrder',//参数
	        	    showColumns: true,
	        	    showRefresh: true,
	        	    pageList: [5, 6, 7],
	        	    uniqueId: "ID",
	        	    minimumCountColumns: 2,
	        	    clickToSelect: true,
	        	    smartDisplay:true,
	        	    sidePagination:"client",
	        	    columns: [
					{
					    field:'roid',
					    title:'客户编号'
					},
	        	    {
	        	        field:'userid',
	        	        title:'客户编号'
	        	    }, {
	        	        field:'address',
	        	        title:'联系地址'
	        	    }, {
	        	        field:'tel',
	        	        title:'电话号码'
	        	    	},
        	    	{
	        	        field:'ordertime',
	        	        title:'订购时间'
	        	    },{
	        	    	field:'deliverytime',
					    title:'配送时间'
	        	    },{
	        	    	field:'ps',
					    title:'附言'
	        	    },{
	        	    	field:'status',
					    title:'状态',
					    formatter:function(value,row,index){ 
					    	var status;
	                        if(value==1){
	                        	status="未处理";
	                        }else{
	                        	status="已处理";
	                        }
	                        return status;
	                      } 
	        	    	}
	        	    ]
	        	});
	        }else if("处理订单"==title){
	        	
	        }
	}
 $( "#tabs" ).on( "click",'span.ui-icon-close', function() {
      var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
      $( "#" + panelId ).remove();
      /*var sid=id.substring(5);
      sessionStorage.removeItem(sid);*/
      tabs.tabs( "refresh" );
  });
 
  //jquery UI 1.10 选项卡切换
  $.fn.selectTabByID = function (tabID) {
	    $(this).tabs("option", "active", $('#' + tabID).tabIndex());
	};
  $.fn.tabIndex = function () {
	    return $(this).parent().find(this).index() - 1; 
	};
  /*$.fn.selectTabByIndex = function (tabIndex) {
	    $(this).tabs("option", "active", tabIndex);
	};*///可根据index来切换选项卡   
	
  function submit(){
	  	if($("#myform").valid()){
	  	var fname=$("#fname");
	  	var normprice=$("#normprice");
	  	var realprice=$("#realprice");
	  	var detail=$("#detail");
	  	var fphoto=$("#fphoto");
	  	if(fname.val()!=null&&normprice.val()!=null&&realprice.val()!=null&&detail.val!=null&&fphoto.val()!=null){
	 	fetch('../manager.action',{
   		 method: 'post', 
   		 headers: { 
   		      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8" 
   		    },  
   		 body:$('#myform').serialize()+'&fphoto='+$("#fphoto").val()
   	}).then(function(res) {
   	    if (res.ok) {
   	     res.text().then(function(obj) {
   	    	var a='<div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert"aria-hidden="true">'
   	    		+'&times;</button>成功！很好地完成了提交。</div>'
   	    	$('#'+id).append(a);
   	    	fname.val("");
   	    	normprice.val("");
   	    	realprice.val("");
   	    	detail.val("");
   	    	fphoto.val("");
   	     	})
         }
   	   }, function(ex) {
   	     console.log(ex)
   	 });
  }
  }else {
			alert("aaaa");
		}
}

