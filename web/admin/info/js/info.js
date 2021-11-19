var ueditor = UE.getEditor('ueditor');

ueditor.ready(function () {
    // console.log($("#btnUpdate").attr("name"));
    $.ajax({
        url : 'getInfo.action',
        type : 'post',
        dataType : 'json',
        data : {"name" : $("#btnUpdate").attr("name")},
        success : function (result) {
            if(result.message == null) {
                ueditor.setContent(result.content);
            }else{
                layer.open({
                    type: 1,
                    offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    id: 'layerDemo1', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + result.message + '</div>',
                    btn: '关闭',
                    btnAlign: 'c', //按钮居中
                    shade: 0.5, //不显示遮罩
                    title: "信息安全实验室",
                    yes: function () {
                        layer.closeAll();
                    }
                });
            }
        },
        error : function () {
            layer.open({
                type: 1,
                offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                id: 'layerDemo1', //防止重复弹出
                content: '<div style="padding: 20px 100px;">' + "请求Action失败！" + '</div>',
                btn: '关闭',
                btnAlign: 'c', //按钮居中
                shade: 0.5, //不显示遮罩
                title: "信息安全实验室",
                yes: function () {
                    layer.closeAll();
                }
            });
        }
    });
});

function btn_update() {
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
        scriptCharset : 'utf-8',
        success : function (result) {
            layer.open({
                type: 1,
                offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                id: 'layerDemo1', //防止重复弹出
                content: '<div style="padding: 20px 100px;">' + result.updateResult + '</div>',
                btn: '关闭',
                btnAlign: 'c', //按钮居中
                shade: 0.5, //不显示遮罩
                title: "信息安全实验室",
                yes: function () {
                    layer.closeAll();
                }
            });
        },
        error : function () {
            layer.open({
                type: 1,
                offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                id: 'layerDemo1', //防止重复弹出
                content: '<div style="padding: 20px 100px;">' + "请求Action失败！" + '</div>',
                btn: '关闭',
                btnAlign: 'c', //按钮居中
                shade: 0.5, //不显示遮罩
                title: "信息安全实验室",
                yes: function () {
                    layer.closeAll();
                }
            });
        }
    });
}