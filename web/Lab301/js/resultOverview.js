window.onload = function () {
    $.ajax({
        url : 'getInfo.action',
        type : 'post',
        data : {"name" : ""},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //成果总览的content，含简介内一些文字的格式，是一段HTML代码
                result.content;
            }
        }
    });
}