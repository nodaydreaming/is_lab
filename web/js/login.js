function btn_login() {
    var account = $("#username").val();
    var pwd = $("#password").val();

    if(account == null || account == ""){
        layer.open({
            type: 1,
            offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            id: 'layerDemo1', //防止重复弹出
            content: '<div style="padding: 20px 100px;">' + "请输入账号！" + '</div>',
            btn: '关闭',
            btnAlign: 'c', //按钮居中
            shade: 0.5, //不显示遮罩
            title: "信息安全实验室",
            yes: function () {
                layer.closeAll();
            }
            // title: '在线调试'
            // ,content: '可以填写任意的layer代码'
        });
    }
    else if(pwd == null || pwd == ""){
        layer.open({
            type: 1,
            offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            id: 'layerDemo1', //防止重复弹出
            content: '<div style="padding: 20px 100px;">' + "请输入密码！" + '</div>',
            btn: '关闭',
            btnAlign: 'c', //按钮居中
            shade: 0.5, //不显示遮罩
            title: "信息安全实验室",
            yes: function () {
                layer.closeAll();
            }
        });
    }
    else{
        var mydata={
            username : $("#username").val(),
            password : $("#password").val()
        };
        //验证账号和密码
        $.ajax({
            url : 'user_login.action',
            type : 'post',
            data : mydata,
            scriptCharset : 'utf-8',
            success : function (result) {
                console.log(result);
                //账号或密码错误，提示错误信息
                if (result.message != null) {
                    var textMessage = result.message.toString();
                    layer.open({
                        type: 1,
                        offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        id: 'layerDemo1', //防止重复弹出
                        content: '<div style="padding: 20px 100px;">' + textMessage + '</div>',
                        btn: '关闭',
                        btnAlign: 'c', //按钮居中
                        shade: 0.5, //不显示遮罩
                        title: "信息安全实验室",
                        yes: function () {
                            layer.closeAll();
                        }
                    });
                }
                else{
                    if(result.newPage != null){
                        console.log(result.newPage);
                        window.location.href = result.newPage;
                    }
                }
            },
            error : function () {
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo1',
                    content: '<div style="padding: 20px 100px;">' + "请求失败，请重试！" + '</div>',
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

}
