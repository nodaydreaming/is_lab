function btn_update() {
    var ueditor = UE.getEditor('ueditor');
    var introName = $("#btnUpdate").attr("name");
    var introContent = ueditor.getContent();
    console.log(introName);
    console.log(introContent);
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