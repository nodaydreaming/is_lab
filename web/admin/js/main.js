//鼠标悬浮在左侧菜单某一项时，该项的所在的类别的名字旁边出现标注，鼠标移开，效果取消
window.onload = function(){
	var listAForHover = document.getElementsByClassName("aHover");
	// console.log(listAForHover);
	for (var i= 0; i<listAForHover.length; i++){
		listAForHover[i].onmouseover = aOnHover;
		listAForHover[i].onmouseout = noAHover;
	}
	//此ajax不适用于main.html页面获取管理员信息
	$.ajax({
		url : '../../user_getLoginUser.action',
		type : 'post',
        scriptCharset : 'utf-8',
		success : function (result) {
			console.log(result);
			var loginUser = result.loginUser;
			console.log(loginUser);
			if(loginUser.nickname != null){
				var li = document.getElementById('showDlLi');
				console.log(li.childNodes[1].childNodes);
				li.childNodes[1].childNodes[2].data = loginUser.nickname;
			}
			if(loginUser.photo != null){

			}
        },
        error : function () {
			console.log("请求失败！");
        }
	});
}
function aOnHover() {
	var a = window.event.target;
	var li = a.parentNode.parentNode.parentNode;
	var targetA = li.childNodes[1];
	// console.log(li);
	var offtop = targetA.offsetTop;
	var obj = targetA;
	while(obj = obj.offsetParent){
		offtop += obj.offsetTop;
	}
	// console.log(offtop);
	offtop -= 60;
	var bar = document.getElementById("nav-bar");
	bar.style.height = "45px";
	bar.style.top = offtop + "px";
	bar.style.opacity = 1;
}
function noAHover(){
	var bar = document.getElementById("nav-bar");
	bar.style.height = "0px";
	bar.style.top = "0px";
	bar.style.opacity = 0;
}
//左侧菜单栏的展开与折叠
function aClick(){
	var a = window.event.target;
	var li = a.parentNode;
	var li_class = li.className;
	if(li_class.length > 20){
		li.className = "layui-nav-item";
	}else{
		li.className = "layui-nav-item layui-nav-itemed";
	}
}

// 展示屏幕右上角“我”的下拉菜单
function showDl(){
	var obj= window.event.target;
	while(obj.id != "showDlLi"){
		obj = obj.parentNode;
	}
	var dl = obj.childNodes[3];
	var span = obj.childNodes[1].childNodes[3];

	span.className = "layui-nav-more layui-nav-mored";
	//显示dl
	dl.className = "layui-nav-child layui-anim layui-anim-upbit layui-show";
	// var width1 = obj.offsetWidth;
	// var height1 = obj.offsetHeight;
	
	// var width2 = dl.offsetWidth;
	// var height2 = dl.offsetHeight;

	// var offleft1 = obj.offsetLeft;
	// var offleft2 = dl.offsetLeft;
	// var i = obj;
	// while(i = i.offsetParent){
	// 	offleft1 += i.offsetLeft;
	// }
	// i = dl;
	// while(i = i.offsetParent){
	// 	offleft2 += i.offsetLeft;
	// }
	// var x1 = offleft1 + width1;
	// var y1 = height1;

	// var x2 = offleft2 + width2 + 10;
	// var y2 = y1 + height2 + 10;

	// console.log(x1);
	// console.log(y1);
	// console.log(x2);
	// console.log(y2);

	// var e = event || window.event;
	// var x = e.clientX
	// var y = e.clientY;

	// if((x > offleft1 && x < x1 && y > 0 && y < y1) || (x > offleft2 && x < x2 && y > y1 && y < y2)){
	// 	span.className = "layui-nav-more layui-nav-mored";
	// 	//显示dl
	// 	dl.className = "layui-nav-child layui-anim layui-anim-upbit layui-show";
	// }else {
	// 	span.className = "layui-nav-more";
	// 	//显示dl
	// 	dl.className = "layui-nav-child layui-anim layui-anim-upbit";
	// }
	
}
// 隐藏屏幕右上角“我”的下拉菜单
function closeDl(){
	var obj= window.event.target;
	while(obj.id != "showDlLi"){
		obj = obj.parentNode;
	}
	var dl = obj.childNodes[3];
	var span = obj.childNodes[1].childNodes[3];

	if(dl.className === "layui-nav-child layui-anim layui-anim-upbit layui-show"){
		var e = event || window.event;
		var x = e.clientX
		var y = e.clientY;
		var width = dl.offsetWidth;
		var height = dl.offsetHeight;

		var offleft = dl.offsetLeft;
		var i = dl;
		while(i = i.offsetParent){
			offleft += i.offsetLeft;
		}

		if(x > offleft && x < (width+offleft+10) && y > 0 && y < (height+70)){
			span.className = "layui-nav-more layui-nav-mored";
			//显示dl
			dl.className = "layui-nav-child layui-anim layui-anim-upbit layui-show";
		}
		else {
			span.className = "layui-nav-more";
			//隐藏dl
			dl.className = "layui-nav-child layui-anim layui-anim-upbit";
		}
	}
}
function noShowDl(){

	var obj= window.event.target;
	while(obj.id != "showDlLi"){
		obj = obj.parentNode;
	}
	var dl = obj.childNodes[3];
	var span = obj.childNodes[1].childNodes[3];

	span.className = "layui-nav-more";
	//隐藏dl
	dl.className = "layui-nav-child layui-anim layui-anim-upbit";

	obj.className = "layui-nav-item";
}

function showBar(){
	var obj= window.event.target;
	while(obj.className != "layui-nav-item"){
		obj = obj.parentNode;
	}
	obj.className += " layui-this";
}
