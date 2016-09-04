// JavaScript Document
var bqvalue = new Array("(smile)", "(亲亲)", "(偷笑)", "(傲慢)", "(再见)", "(冷汗)",
		"(发呆)", "(发怒)", "(可怜)", "(可爱)", "(右哼哼)", "(吐)", "(吓)", "(呲牙)", "(咒骂)",
		"(哈欠)", "(嘘!)", "(困)", "(坏笑)", "(大兵)", "(大哭)", "(奋斗)", "(委屈)", "(害羞)",
		"(尴尬)", "(左哼哼)", "(得意)", "(快哭了)", "(惊恐)", "(惊讶)", "(憨笑)", "(扣鼻)",
		"(抓狂)", "(折磨)", "(撇嘴)", "(擦汗)", "(敲打)", "(晕)", "(流汗)", "(流泪)", "(疑问)",
		"(白眼)", "(睡)", "(糗大了)", "(色)", "(衰)", "(调皮)", "(鄙视)", "(酷)", "(闭嘴)",
		"(阴险)", "(难过)", "(饥饿)", "(骷髅)", "(鼓掌)", "(糗大了)");

var hfObj;
var srcUser;
var client;

/* 插入图片 */
function insertPic() {
	$("#picform").show();
	$("#fileupload").val("");
}

function enter() {
	var divclone = $("#news_pic_show").clone();
	$("#pic").show();
	$("#pic").append(divclone);
	$("#picform").hide();
}

function cancel() {
	$("#picform").hide();
}
/* 设置页面中的主题部分的左栏和右栏部分高度为自动 */
function initDivHeight(divObj1, divObj2) {
	divObj1.style.height = "auto";
	divObj2.style.height = "auto";
}
/* 设置主体部分的高度以实际高度高的那个为准 */
function changeDivHeight() {
	var mainBanner = document.getElementById("mainBanner");
	var mainRight = document.getElementById("mainRight");
	initDivHeight(mainBanner, mainRight);// 设置高度为自动
	var height = mainBanner.offsetHeight > mainRight.offsetHeight ? mainBanner.offsetHeight
			: mainRight.offsetHeight;// 获取高度高的值
	mainBanner.style.height = height + "px";// 为他们的高度都赋高的那个值
	mainRight.style.height = height + "px";//
}
/* 动态的计算文本框里面已经输入的数量 */
function calNum(txtobj, divobj, fg) {
	var str = txtobj.value;
	var n = 140;// 初始化数字
	var tmp = str.replace(/[^\w\s]/g, "");// 将文本框里面的字符中的中文全部替换成空的
	if (tmp != null)
		n = n - (str.length - tmp.length) - Math.floor(0.5 * tmp.length);// 计算，一个中文是1个字符，2个英文是1个
	else
		n = n - Math.floor(0.5 * str.length);// 计算，一个中文是1个字符，2个英文是1个
	if (n < 0) {
		divobj.style.color = "#969";// 设置如果超了，变背景色为红色
	} else {
		if (fg == 1)// 如果标记为1设置白色
			divobj.style.color = "#FFF";
		else
			// 如果标记为0 设置为黑色
			divobj.style.color = "#000";
	}
	/* 将计算好的数存入div中 */
	divobj.innerHTML = n;
}
/* 显示表情框 */
function biaoQingXianShi() {
	var divObj = document.getElementById("biaoqing");
	divObj.style.visibility = "visible";// 设置表情框显示
}

function insertimg() {
	var divObj = document.getElementById("insertimg");
	divObj.style.visibility = "visible";// 设置表情框显示
}

(function($, undefined) {
	$.fn.getCursorPosition = function() {
		var el = $(this).get(0);
		var pos = 0;
		if ('selectionStart' in el) {
			pos = el.selectionStart;
		} else if ('selection' in document) {
			el.focus();
			var Sel = document.selection.createRange();
			var SelLength = document.selection.createRange().text.length;
			Sel.moveStart('character', -el.value.length);
			pos = Sel.text.length - SelLength;
		}
		return pos;
	}
})(jQuery);

/* 设置如果单击事件，就隐藏表情框 */
function judeState() {
	var divObj = document.getElementById("biaoqing");
	if (divObj.style.visibility == "visible") {
		divObj.style.visibility = "hidden";// 隐藏表情框
	}
}
/* 初始化表情框里面的表情 */
function initImgFace() {
	var n = 1;
	var tabObj = document.getElementById("biaoqingtable");
	var trObj = tabObj.rows;
	for (var i = 0; i < trObj.length; i++) {
		for (var j = 0; j < trObj[i].cells.length; j++) {// 将td里面填充出相应的图片
			trObj[i].cells[j].innerHTML = "<img src='images/biaoqing/" + n
					+ ".gif' width='24' height='24' alt='' title= '"
					+ bqvalue[n - 1] + "'/>";
			n++;
		}
	}
}
/* 当点提交按钮时，对文本框里面的内容进行处理，并进行提交 */
function submitState() {
	var txtObj = document.getElementById("textfield2");
	txtObj.value = trim(txtObj.value);
	$("#pic").hide();
	if (txtObj.value.length > 0) {
		var str = changetxt(txtObj.value);// 替换文本框中的表情
		if (sessionStorage.getItem("src") != undefined) {
			src = sessionStorage.getItem("src");
			str += "<br/><img src='" + src + "' width='100px' height='150px'>";
		}

		var href = location.href;
		var index = href.indexOf("=");
		var params = href.substring(index + 1);
		var indexuid = href.indexOf("=");
		var uid = href.substring(indexuid + 1);

		// 向服务器提交消息
		$
				.ajax({
					url : 'message_add',
					dataType : 'json',
					data : {
						'Mcontent' : str,
						'user.Uid' : params,
						'Mdatetime' : '2016-09-01'
					},
					type : 'post',
					success : function(data) {
						if (data.code == 1) {
							var obj = data.obj;
							txtObj.value = trim(obj.Mcontent);
							str = changetxt(txtObj.value);
							var time = inittime();// 取出当前时间
							var innerht = "<div Mid='"
									+ obj.Mid
									+ "' Cid='"
									+ obj.Cid
									+ "' "
									+ "class='stateShow' onmouseover='stateMouseOver(this)' onmouseout='stateMouseOut(this)'>"
									+ "<div class='stateShowWord'><table width='450' border='0' cellpadding='0' cellspacing='0' class='stateTable'>"
									+ "<tr><td width='70' align='center' valign='top'><a href='#'>"
									+ "<img src='"
									+ obj.user.Uimage
									+ "' alt='' width='48' height='48' /></a></td>"
									+ "<td width='380'><a href='#'>"
									+ obj.user.Ualais
									+ "</a>"
									+ "<img src='images/1.gif' align='absmiddle' style='border:none;' />"
									+ "&nbsp;"
									+ str
									+ "</td></tr></table></div><div class='stateImgShow'>"
									+ "</div><div class='stateShowtime'>"
									+ time
									+ "</div>"
									+ "<div class='stateOp'><a onclick='reXianShi(this)' class='opState'>回复</a>"
									+ "<a class='opState'>转发</a><a onclick='delState(this)' class='opState'>删除</a></div>"
									+ "<div class='huifu'></div></div>";
							var divObj = document
									.getElementById("mainBannerContent");
							sessionStorage.removeItem('src');
							divObj.innerHTML = innerht + divObj.innerHTML;

							message = new Messaging.Message(innerht);
							message.destinationName = uid;
							client.send(message);

							txtObj.value = "";// 清空文本框
							changeDivHeight();// 重设页面高度
						} else {
							alert("大老爷们发什么微博！");
						}
					}
				});
	}
}

/* 生成当前的时间 */
function inittime() {
	var today = new Date();
	var month = today.getMonth();
	var day = today.getDate();
	var hour = today.getHours();
	var minits = today.getMinutes();
	/* 对数字中不到2位数的处理，前面加0 */
	if (month < 9) {
		month += 1;
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minits < 10) {
		minits = "0" + minits;
	}
	var str = " " + month + "月" + day + "日 " + hour + ":" + minits;// 拼出时间字符串并返回
	return str;
}

$('img').error(function() {
	$(this).attr('src', 'images/nopic.jpg');
});

/* 转换文本框里面的内容,将所有的图像的值,替换为相应的imghtml语言,并且返回 */
function changetxt(str) {
	var ustr = new Array();
	var reg = /\([^()\s]+\)/;// 正则匹配图像的值
	var strtmp = str;
	while (strtmp.match(reg) != null) {// 匹配是否有符合图像值的
		var temp = strtmp.match(reg);
		var repstr = createImg(temp);
		if (repstr != null) {
			strtmp = strtmp.replace(temp, repstr);// 将值和生成的值相应的html语言替换
		} else {// 如果匹配不到就替换成特定的字符，并保存原来的值，避免进入死循环
			strtmp = strtmp.replace(temp, "#$#");
			ustr.push(temp);
		}
	}
	/* 将原来匹配出来的不符合 图像的值替换回去 */
	if (ustr != null && ustr.length > 0) {
		for (var i = 0; i < ustr.length; i++) {
			strtmp = strtmp.replace("#$#", ustr[i]);
		}
	}
	return strtmp;// 返回已经替换之后的记过
}
/* 单击图像之后,为输入框添加图像相应的值并且设置焦点为文本框 */
function addImg(e) {
	var txtObj = document.getElementById("textfield2");
	var theEvent = window.event || e;
	var imgObj = theEvent.srcElement;
	if (!imgObj) {
		imgObj = theEvent.target;
	}
	txtObj.value = txtObj.value + imgObj.title;// 将图像的值写入文本框
	txtObj.focus();// 设置文本框焦点
}
/* 通过传进去的图像的含义，从而生成图像的路径，并且生成img的代码 */
function createImg(title) {
	/* 通过设置的图像值,挨个匹配寻找路径 */
	for (var i = 0; i < bqvalue.length; i++) {
		if (title == bqvalue[i]) {
			break;
		}
	}
	/* 如果找不到值就返回null */
	if (i == bqvalue.length)
		return null;
	/* 如果找到值就拼出img标记并且返回 */
	var str = "<img src='images/biaoqing/"
			+ (i + 1)
			+ ".gif' width='24' height='24' alt='' align='absmiddle' title= ''/>"
	return str;
}
/* 删除状态 */
function delState(divObj) {
	divObj = divObj.parentNode.parentNode;
	var mid = divObj.getAttribute("mid");
	console.log(mid);
	var fg = confirm("确认删除吗");
	if (fg == true) {
		divObj.parentNode.removeChild(divObj);
		changeDivHeight();
		$.ajax({
			dataType : 'json',
			url : 'delByMid',
			data : {
				"Mid" : mid
			},
			type : 'post',
			success : function(data) {
				if (data.code == 1) {
					console.log("删除成功！");
				}
			}
		});
	}
}

/* 搜索 */
function showfriends() {
	$("#friends").css("visibility", "visible");
}

function hidefriends() {
	$("#friends").css("visibility", "hidden");
}

function inputtext(text) {
	var tx = $("#textfield2").val();
	$("#textfield2").val(tx + text);
	$("#at").css("visibility", "hidden");
}
/* 鼠标悬停时，设置背景为深色 */
function stateMouseOver(divObj) {
	divObj.style.backgroundColor = "#f1f1f1";
}
/* 鼠标离开时，设置背景为白色 */
function stateMouseOut(divObj) {
	divObj.style.backgroundColor = "#ffffff";
}

function retransmission(cid) {
	$.ajax({
		type : "post",
		data : {
			'cid' : cid
		},
		url : "user_retransmission",
		dataType : "json",
		success : function(data) {
			if (data.code == 1) {
			} else {
				alert("转发失败,原因：" + data.msg);
				return false;
			}
		}
	});
}

function reform1(div) {
	var messageDiv = div.parentNode.parentNode;

	sessionStorage.setItem("mid", messageDiv.getAttribute("mid"));
	sessionStorage.setItem("cid", messageDiv.getAttribute("cid"));

	$("#reform1").dialog("open");
}

function reform(div) {
	var messageDiv = div.parentNode.parentNode;
	sessionStorage.setItem("mid", messageDiv.getAttribute("mid"));
	$("#reform").dialog("open");
}
/* 函数绑定 */
function searchhot(word) {
	$
			.ajax({
				type : 'post',
				data : {
					"word" : word
				},
				url : "searchhot",
				dataType : "json",
				success : function(data) {
					if (data) {
						var html="";
						for (var i = 0; i < data.length; i++) {
							html += '<div id="'
									+ data[i].Mid
									+ '" Mid="'
									+ data[i].Mid
									+ '" Cid="'
									+ 1
									+ '" class="stateShow" onmouseout="stateMouseOut(this)" onmouseover="stateMouseOver(this)" style="background-color: rgb(255, 255, 255);">';
							html += '<div class="stateShowWord"><table class="stateTable" width="450" cellspacing="0" cellpadding="0" border="0">';
							html += '<tbody><tr><td width="70" valign="top" align="center"><a href="#"><img width="48" height="48" alt="" src="'
									+ data[i].user.Uimage + '">';
							html += '</a></td><td width="380"><a href="#">'
									+ data[i].user.Ualais
									+ '</a><img align="absmiddle" style="border: none;" src="images/1.gif">';
							html += data[i].Mcontent
									+ '</td></tr></tbody></table></div><div class="stateShowtime">'
									+ data[i].Mdatetime
									+ '</div><div class="stateOp">';
							html += '<a class="opState" onclick="reform(this)">回复</a><a class="opState" onclick="reform1(this)">转发</a>';
						}
						$("#mainBannerContent").html(html);
					}
				}
			});
}

window.onload = function() {
	$("#reform")
			.dialog(
					{
						autoOpen : false,
						height : 150,
						width : 350,
						model : true,
						buttons : {
							"回复" : function() {
								var mid = sessionStorage.getItem('mid');
								$
										.ajax({
											dataType : 'json',
											url : 'add_reply',
											data : $("#reformtext").serialize()
													+ '&Mid=' + mid,
											type : 'post',
											success : function(data) {
												if (data.code == 1) {
													$("#reform")
															.dialog("close");
													var html = '';
													var obj = data.obj;
													var divid = obj.mid;
													html += '<div id="mainBannerContent2PeopleWordBack"><table width="400" cellspacing="0" cellpadding="0" border="0">';
													html += '<tbody><tr><td><img width="25" align="absmiddle" height="25" src="'
															+ obj.Ruimage
															+ '"><a class="a1" href="#">'
															+ obj.Runame
															+ '</a>：'
															+ obj.Rcontent;
													html += '   <span style="color:#ccc">'
															+ obj.Rdatetime
															+ '</span><a href="#">回复</a></td></tr></table></div><br/><br/><br/>';
													$("#" + divid).after(html);
													// window.location.reload();
												} else {
													alert("回复失败！");
												}
											}
										});
							},
							"取消" : function() {
								$(this).dialog("close");
							}
						}
					});
	$("#reform1").dialog({
		autoOpen : false,
		height : 150,
		width : 350,
		model : true,
		buttons : {
			"转发" : function() {
				var mid = sessionStorage.getItem('mid');
				var text = sessionStorage.getItem('text');
				var cid = sessionStorage.getItem('cid');
				if (cid == null || cid == 0) {
					cid = mid;
				}
				var uid = location.href; // 取得整个地址栏
				var num = uid.indexOf("=");
				uid = uid.substr(num + 1);
				var Mcontent = document.getElementById("Mcontent").value;
				$.ajax({
					type : 'post',
					data : {
						'Cid' : cid,
						'user.Uid' : uid,
						'Mcontent' : Mcontent
					},
					url : "user_retransmission",
					dataType : "json",
					success : function(data) {
						if (data.code == 1) {
							$("#reform1").dialog("close");
						} else {
							alert("转发失败,原因：" + data.msg);
							$("#reform1").dialog("close");
						}
					}
				});
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});

	changeDivHeight();// 开始的时候设置左栏和右栏的高度
	initImgFace();// 初始化table里面的图像
	document.onclick = judeState;// 设置单机取消显示
	var imgObj = document.getElementById("biaoqing")
			.getElementsByTagName("img");// 为biaoqingDIV下的所有img设置单机事件
	for (var i = 0; i < imgObj.length; i++) {
		imgObj[i].onclick = addImg;// 绑定单机事件
	}
	// 隐藏 #back-top 先
	$("#backtop").hide();
	// 滚动条距顶100px显示 #back-top
	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) {
				$('#backtop').fadeIn();
			} else {
				$('#backtop').fadeOut();
			}
		});
		// 点击事件 回到顶部
		$('#backtop a').click(function() {
			$('body,html').animate({
				scrollTop : 0
			}, 600);
			return false;
		});

		$("#textfield2")
				.keydown(
						function(e) {
							var keyCode = e.keyCode || e.which || e.charCode;
							var shiftKey = e.shiftKey;
							if (shiftKey && keyCode == 50) {
								$("#at").css("visibility", "visible");
								$("#at")
										.css(
												"left",
												310
														+ ($('#textfield2')
																.getCursorPosition() * 16)
														+ "px");
							}
							if (keyCode == 8) {
								$("#at").css("visibility", "hidden");
							}
						});
	});

	$(function() {
		var href = location.href;
		var index = href.indexOf("?");
		var params = href.substring(index + 1);
		var indexuid = href.indexOf("=");
		var uid = href.substring(indexuid + 1);

		// 存session 方便之后的网页取
		sessionStorage.setItem("userindex", "CustomerIndex.html?" + params);
		sessionStorage.setItem("userhtml", "MyWB.html?" + params);

		$("#index").attr("href", sessionStorage.getItem('userindex'));
		$("#MyWB").attr("href", sessionStorage.getItem('userhtml'));

		var htmlstring = '<ul><a href="MyWB.html?'
				+ params
				+ '" class="a1"><li><font class="style1">2</font><br /> <font class="style2">微博</font></li></a>';
		htmlstring += '<a href="friend.html?'
				+ params
				+ '" class="a1"><li><font class="style1">12</font><br /> <font class="style2">关注</font></li></a>';
		htmlstring += '<a href="focusonyou.html?uid='
				+ uid
				+ '" class="a1"><li><font class="style1">23</font><br /> <font class="style2">粉丝</font></li></a>';
		htmlstring += '</ul>';
		$("#mainRightPostionFirstLineWord2").html(htmlstring);

		// 每次加载就创建indexdb本地数据库
		createDatabase();

		var getting = {
			dataType : 'json',
			url : 'selectAllByUid',
			data : params,
			type : 'get',
			success : function(data) {
				if (data.code == 1) {
					$("#mainBannerContent").html("");

					var obj = data.obj;
					var html = "";
					for (var i = 0; i < obj.length; i++) {
						cid = obj[i].Cid ? obj[i].Cid : 0;
						html += '<div id="'
								+ obj[i].Mid
								+ '" Mid="'
								+ obj[i].Mid
								+ '" Cid="'
								+ cid
								+ '" class="stateShow" onmouseout="stateMouseOut(this)" onmouseover="stateMouseOver(this)" style="background-color: rgb(255, 255, 255);">';
						html += '<div class="stateShowWord"><table class="stateTable" width="450" cellspacing="0" cellpadding="0" border="0">';
						html += '<tbody><tr><td width="70" valign="top" align="center"><a href="#"><img width="48" height="48" alt="" src="'
								+ obj[i].user.Uimage + '">';
						html += '</a></td><td width="380"><a href="#">'
								+ obj[i].user.Ualais
								+ '</a><img align="absmiddle" style="border: none;" src="images/1.gif">';
						html += obj[i].Mcontent
								+ '</td></tr></tbody></table></div><div class="stateShowtime">'
								+ obj[i].Mdatetime
								+ '</div><div class="stateOp">';
						html += '<a class="opState" onclick="reform(this)">回复</a><a class="opState" onclick="reform1(this)">转发</a>';
						if (obj[i].user.Uid == uid) {
							html += '<a class="opState" onclick="delState(this)">删除</a>';
						}
						html += '</div><div class="huifu"></div></div></div><br>';
						for (var k = 0; k < obj[i].messageReply.length; k++) {
							html += '<div id="mainBannerContent2PeopleWordBack"><table width="400" cellspacing="0" cellpadding="0" border="0">';
							html += '<tbody><tr><td><img width="25" align="absmiddle" height="25" src="'
									+ obj[i].messageReply[k].Ruimage
									+ '"><a class="a1" href="#">'
									+ obj[i].messageReply[k].Runame
									+ '</a>：'
									+ obj[i].messageReply[k].Rcontent;
							html += '   <span style="color:#ccc">'
									+ obj[i].messageReply[k].Rdatetime
									+ '</span><a href="#">回复</a></td></tr></table></div><br><br>';
						}
						// 存indexdb
						// populateDatabase(obj);

					}

					$("#mainBannerContent").html(html);

					// 个人头像
					$("#mainRightPostionFirstLineIcon").html("");
					var icon = '<a href="MyWB.html?user.Uid='
							+ data.users.Uid
							+ '"><img src="'
							+ data.users.Uimage
							+ '" alt="" width="48" '
							+ ' height="48" align="absmiddle" title="" border="0" /></a>';
					$("#mainRightPostionFirstLineIcon").html(icon);

					// 个人简介
					$("#mainRightPostionFirstLineWord1").html('');
					var xinxi = '&nbsp;<font color="#005DC3"><b><a href="MyWB.html?user.Uid='
							+ data.users.Uid
							+ '" class="a1">'
							+ data.users.Ualais
							+ '</a></b></font><br /> &nbsp;'
							+ data.users.Region;
					$("#mainRightPostionFirstLineWord1").html(xinxi);

					// 个人朋友
					$("#myfriends").html("");
					var friends = '';
					var searchfriends = "";
					var friend = '';
					var uidarr = new Array();
					for (var i = 0; i < data.userslist.length; i++) {
						uidarr.push(data.userslist[i].Uid);
						friends += '<a href="MyWB.html?user.Uid='
								+ data.userslist[i].Uid
								+ '" class="a1"><li><img src="'
								+ data.userslist[i].Uimage
								+ '" border="0" width="50px" height="50px" /><br /> <font class="style2">'
								+ data.userslist[i].Ualais + '</font></li></a>';
						searchfriends += '<img src="'
								+ data.userslist[i].Uimage
								+ '" width="20px" height="20px" /><a href="javascript:fetchUalaisMessage(\''
								+ data.userslist[i].Ualais + '\')">'
								+ data.userslist[i].Ualais + '</a><br>';
						friend += '<a href="javascript:inputtext(\''
								+ data.userslist[i].Ualais
								+ '\')"><li><img src="'
								+ data.userslist[i].Uimage
								+ '" border="0" /> <font class="style2">'
								+ data.userslist[i].Ualais + '</li></a> ';
					}
					sessionStorage.setItem("uidarr", uidarr);
					showchat();

					var hot = "";

					for (var i = 0; i < data.hotWords.length; i++) {
						hot += '<a href="javascript:searchhot(\''
								+ data.hotWords[i].word
								+ '\')" class="a1"><li><font class="style2">'
								+ data.hotWords[i].word + '('
								+ data.hotWords[i].total + ')</font></li></a>';
					}

					$("#ul2").html(hot);
					$("#friends").html(searchfriends);
					$("#myfriends").html(friends);
					$("#at").html(friend);
				}
			}
		};

		// 监听message框改变
		$("#message").bind('DOMNodeInserted', function(e) {
			var divmessage = document.getElementById("message");
			var divObj = document.getElementById("mainBannerContent");
			divObj.innerHTML = divmessage.innerHTML + divObj.innerHTML;
			changeDivHeight();// 重设页面高度
		});

		// 延时ajax 防止indexdb创建过慢获取不到localDatabase.db
		window.setTimeout(function() {
			$.ajax(getting)
		}, 500);

		$("#sub")
				.click(
						function() {
							var response = "";
							$
									.ajax({
										url : 'robotchat',
										data : {
											"word" : $("#input").val()
										},
										method : "post",
										dataType : 'JSON',
										success : function(data) {
											if (data.code == 100000) {
												$("#response").val("");
												var input = $("#input").val();
												var date = new Date();
												var time = date
														.toLocaleTimeString();
												var oldresponse = sessionStorage
														.getItem('response');
												if (oldresponse == null) {
													oldresponse = "";
												}
												response += "<p><a href='#'>HCQ：</a>&nbsp;&nbsp;&nbsp;<span>"
														+ time
														+ "</span></p> "
														+ $("#input").val()
														+ "<p><a href='#'>小机器：</a>&nbsp;&nbsp;&nbsp;<span>"
														+ time
														+ "</span></p> "
														+ data.text;
												$("#response")
														.html(
																oldresponse
																		+ "<p><a href='#'>HCQ：</a>&nbsp;&nbsp;&nbsp;<span>"
																		+ time
																		+ "</span></p>"
																		+ input
																		+ "<p><a href='#'>小机器：</a>&nbsp;&nbsp;&nbsp;<span>"
																		+ time
																		+ "</span></p> "
																		+ data.text);
												$("#input").val("");
												sessionStorage.setItem(
														'response', response);
											}
										}
									});
						});

		$('#fileupload').fileupload(
				{
					dataType : 'json',
					url : 'file_upload',
					acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
					add : function(e, data) {
						data.context = $("#enter").text("上传").appendTo(
								"#picform").click(function() {
							data.submit();
						});
					},
					done : function(e, data) {
						var data = data.result;
						if (data.code == 1) {
							console.log(data.obj);
							sessionStorage.setItem("src", data.obj);
						} else {
							alert("文件上传失败，请检查文件类型或重新上传");
						}
					}
				});
	});
}