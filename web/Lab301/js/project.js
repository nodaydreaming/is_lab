window.onload = function () {
    $.ajax({
        url : 'getAllProjects.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //项目的数组
                result.projects;
            }
        }
    });
}