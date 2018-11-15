window.onload = function(){
	var listAForHover = document.getElementsByClassName("aHover");
	// console.log(listAForHover);
	for (var i= 0; i<listAForHover.length; i++){
		listAForHover[i].onmouseover = aOnHover;
		listAForHover[i].onmouseout = noAHover;
	}
}


function aOnHover() {
	var a = window.event.target;
	var li = a.parentNode.parentNode.parentNode;
	var targetA = li.childNodes[1];
	// console.log(li);
	var offtop = targetA.offsetTop;
	var obj = targetA;
	while(obj = obj.offsetParent){
		offtop += obj.offsetTop
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
	var a = window.event.target;
	var li = a.parentNode;
	var dl = li.childNodes[3];

	var span = a.childNodes[3];
	span.className = "layui-nav-more layui-nav-mored";

	//显示dl
	dl.className = "layui-nav-child layui-anim layui-anim-upbit layui-show";

}
function dlShow(){
	var a = window.event.target;
	var dl = a.parentNode.parentNode;
	var li = dl.parentNode;
	var span = li.childNodes[1].childNodes[3];
	span.className = "layui-nav-more layui-nav-mored";

	dl.className = "layui-nav-child layui-anim layui-anim-upbit layui-show";
}
// 隐藏屏幕右上角“我”的下拉菜单
function noShowDl(){
	var a = window.event.target;
	var dl = a.parentNode.parentNode;
	var li = dl.parentNode;
	var span = li.childNodes[1].childNodes[3];
	span.className = "layui-nav-more";
	var classes = dl.className;

	dl.className = "layui-nav-child layui-anim layui-anim-upbit";

}