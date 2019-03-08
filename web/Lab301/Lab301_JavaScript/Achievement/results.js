window.onload = function () {
    $.ajax({
        url : 'getResults.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //result.papers  论文
                //result.patents 专利
                //result.softwares 软著
                //result.competitions 竞赛
            }
        },
        error : function () {
            alert("请求失败！");
        }
    });
}