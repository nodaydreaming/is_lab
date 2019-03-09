setTimeout(function () {
    secUl = secInside.children[0];
    secLi = secUl.getElementsByTagName("li");
    slidepage2 = Math.ceil(secLi.length / 3);
    // console.log(slidepage2);
    // console.log(secLi.length);
    // console.log(secLi);
    }, 100);
var secLeft = document.getElementById("secLeft");
var secRight = document.getElementById("secRight");
var secInside = document.getElementById("secInside");
var secUl = secInside.children[0];
var secLi = secUl.getElementsByTagName("li");
var slidepage2 = Math.ceil(secLi.length / 3);
// console.log(slidepage2);
// console.log(secLi.length);
// console.log(secLi);
var secPic = 0;
var secImgWidth = secInside.offsetWidth;
//函数
function autoLeft() {
    if(secPic != 0) {
        secPic --;
        animate(secUl, -secPic*secImgWidth, 10);
    } else if(secPic == 0) {
        secPic = slidepage2-1;
        animate(secUl, -secPic*secImgWidth, 2);
    }
}
function autoRight() {
    if(secPic != slidepage2-1) {
        secPic ++;
        animate(secUl, -secPic*secImgWidth-2, 10);
    } else if(secPic == slidepage2-1) {
        secPic = 0;
        animate(secUl, -secPic*secImgWidth, 2);
    }
}
//响应事件
secRight.onclick = autoRight;
secLeft.onclick = autoLeft;

