window.onload = function() {
    //速度数值越大速度越慢
    var speed = 500;
    //定义JS对象
    var demo2 = document.getElementById("demo2");
    var demo = document.getElementById("demo");
    var demo1 = document.getElementById("demo1");
    //JS对象转为JQuery对象
    var $demo = $(demo);
    //复制整组图片
    demo2.innerHTML = demo1.innerHTML;
    //图片移动函数
    function Marquee() {
        if(demo2.offsetWidth-demo.scrollLeft<=0) {
            demo.scrollLeft-=demo1.offsetWidth;
        } else {
            $demo.animate({ scrollLeft : '+='+594 }, "slow");
        }
    }
    //函数调用
    var MyMar = setInterval(Marquee, speed);
    //鼠标移入移出事件
    demo.onmouseover = function() {
        clearInterval(MyMar);
    }
    demo.onmouseout = function() {
        MyMar = setInterval(Marquee,speed);
    }
    /*//速度数值越大速度越慢
    var speed = 1500;
    //定义JS对象
    var demo2 = document.getElementById("demo2");
    var demo = document.getElementById("demo");
    var demo1 = document.getElementById("demo1");
    //JS对象转为JQuery对象
    var $demo = $(demo);
    //复制整组图片
    demo2.innerHTML = demo1.innerHTML;
    //图片移动函数
    var i = 0;
    function Marquee() {
        if(demo2.offsetWidth-demo.scrollLeft<=0) {
            console.log(demo.scrollLeft,demo1.offsetWidth,demo2.offsetWidth);
            demo.scrollLeft-=demo1.offsetWidth;
            
        } else {
            $demo.animate({ scrollLeft : '+='+595 }, "slow");
        }
    }
    //函数调用
    var MyMar = setInterval(Marquee(), speed);
    //鼠标移入移出事件
    demo.onmouseover = function() {
        clearInterval(MyMar);
    }
    demo.onmouseout = function() {
        MyMar = setInterval(Marquee,speed);
    }*/
    
}
