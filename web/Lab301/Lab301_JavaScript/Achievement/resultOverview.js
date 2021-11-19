window.onload = function () {
    $.ajax({
        url : 'getInfo.action',
        type : 'post',
        data : {"name" : "achievementSummary"},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //成果总览的content，含简介内一些文字的格式，是一段HTML代码
                 fillContents(result.content);
            }
        },
        error : function () {
           alert("请求失败！");
        }
    });
}

function fillContents() {
    var contents = arguments[0];
    console.log(contents);
    var body = document.getElementsByClassName('body')[0];
    var p = document.createElement('p');
    p.innerHTML = contents;
    body.appendChild(p);
}













