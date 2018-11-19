function btn_update() {
    var ueditor = UE.getEditor('ueditor');
    var introName = "libIntro";
    var introContent = ueditor.getContent();

    var mydata = {
        name : introName,
        content : introContent
    };
    console.log(mydata);
    $.ajax({
        url : 'info_update.action',
        type : 'post',
        data : mydata,
        success : function () {
            console.log("修改成功");
        },
        error : function () {
            console.log("请求action失败！");
        }
    });
}