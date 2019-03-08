window.onload = function() {
    $.ajax({
        url : 'getCultureByType.action',
        type : 'post',
        data : {"type" : 2},
        scriptType : "utf-8",
        success : function (result) {
            if(result.message == null){
                //result.cultureList;

            }
        },
        error : function () {
            layer.msg('请求Action失败！');
        }
    });
}