window.onload = function () {
    $.ajax({
        url : 'getAllResearchs.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //研究方向数组
                result.researchs;
            }
        }
    });
}