window.onload = function () {
    $.ajax({
        url : 'getAllWorks.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //作品的数组
                result.works;
            }
        }
    });
}