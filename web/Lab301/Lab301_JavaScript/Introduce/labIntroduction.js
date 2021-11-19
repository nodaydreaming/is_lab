window.onload  = function () {
    $.ajax({
        url : 'getInfo.action',
        type : 'post',
        data : {"name" : "libIntro"},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null) {
                console.log(result.content);
                //简介的content，含简介内一些文字的格式，是一段HTML代码
                fillIntroduce(result.content);
            }
        },
        error : function () {
           alert("请求失败！");
        }
    });
}

function fillIntroduce() {
    var introduce = arguments[0];
    var body = document.getElementsByClassName('body')[0];
    body.innerHTML = introduce;
};