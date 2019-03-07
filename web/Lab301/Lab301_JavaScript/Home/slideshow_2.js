var secLeft = document.getElementById("secLeft");
var secRight = document.getElementById("secRight");
var secInside = document.getElementById("secInside");
var secUl = secInside.children[0];
var secLi = secUl.getElementsByTagName("li");
var page = Math.ceil(secLi.length / 3);
var secPic = 0;
var secImgWidth = secInside.offsetWidth;
//函数
function autoLeft() {
    if(secPic != 0) {
        secPic --;
        animate(secUl, -secPic*secImgWidth, 10);
    } else if(secPic == 0) {
        secPic = page-1;
        animate(secUl, -secPic*secImgWidth, 2);
    }
}
function autoRight() {
    if(secPic != page-1) {
        secPic ++;
        animate(secUl, -secPic*secImgWidth-2, 10);
    } else if(secPic == page-1) {
        secPic = 0;
        animate(secUl, -secPic*secImgWidth, 2);
    }
}
//响应事件
secRight.onclick = autoRight;
secLeft.onclick = autoLeft;