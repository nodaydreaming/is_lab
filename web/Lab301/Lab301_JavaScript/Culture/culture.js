/*var left = document.getElementById('left');
function hj() {
    var j = left.style.top;
    j = parseInt(j.substr(0, j.length-2));
    console.log("j:" + j);
    left.style.top = (j-10)+"px";
}
setInterval(hj, 1000);*/

window.onscroll = function() {
     //获取头部菜单是否达到浏览器顶部边框
     //scrollY：浏览器被卷上去的像素值
     if(window.scrollY > 300) {
         //设置头部菜单相对浏览器边框定位
         document.getElementById("left").style.position="fixed";
         //距离顶部0距离
         document.getElementById("left").style.top="0px";
     } else {
         //如果距离小于300，也就是滚回，设置定位为空，及不定位
         document.getElementById("left").style.position="static";
     }
 }
