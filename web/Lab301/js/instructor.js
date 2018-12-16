window.onload = function () {
    $.ajax({
       url : 'getAllInstructors.action',
       type : 'post',
       scriptCharset : 'utf-8',
       success : function (result) {
           if(result.message == null) {
               //指导老师的数组
               result.instructors;
           }
       }
    });
}