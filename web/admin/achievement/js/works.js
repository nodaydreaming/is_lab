var worksList;
var name, intro, photo;
var uploadInst;

window.onload = function () {
    var listAForHover = document.getElementsByClassName("aHover");
    // console.log(listAForHover);
    for (var i= 0; i<listAForHover.length; i++){
        listAForHover[i].onmouseover = aOnHover;
        listAForHover[i].onmouseout = noAHover;
    }
    //此ajax不适用于main.html页面获取管理员信息
    $.ajax({
        url : 'user_getLoginUser.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            var loginUser = result.loginUser;
            if(loginUser.nickname != null){
                var li = document.getElementById('showDlLi');
                // console.log(li.childNodes[1].childNodes);
                li.childNodes[1].childNodes[2].data = loginUser.nickname;
            }
            if(loginUser.photo != null){
                $('.layui-nav-img')[0].src = loginUser.photo;
            }
            if(loginUser.status != 2){
                $('.layui-nav-item')[6].remove();
            }
        },
        error : function () {
            console.log("请求失败！");
        }
    });
    getWorks();
};

function getWorks() {
    $.ajax({
        url : 'getWorks.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                fillWorks(result.works)
            }
        },
        error : function () {
            layer.msg("请求失败！");
        }

    });
}

function fillWorks() {
    worksList = arguments[0];
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");

    for (var i = 0; i < worksList.length; ++i){
        var works = worksList[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.style.textAlign = "center";
        td2.innerText = works.name;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.innerText = works.intro;
        td3.style.wordWrap = "break-word";
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editWorks" + (i+1);
        a1.className = "layui-btn layui-btn-normal layui-btn-xs";
        var i1 = document.createElement('i');
        i1.className = "layui-icon layui-icon-edit";
        a1.appendChild(i1);
        a1.innerHTML = a1.innerHTML + "编辑";
        a1.onclick = editWorks;

        var a2 = document.createElement('a');
        a2.id = "delWorks" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delWorks;
        td4.appendChild(a1);
        td4.appendChild(a2);

        tr.appendChild(td4);
        tbody.appendChild(tr);
    }
}

function openAddWorks() {
    photo = null;
    layer.open({
        type: 1,
        offset: 'auto',
        skin: 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area: ['500px', '600px'],
        content:
            '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px; height: ;">\n' +
            '    <div class="layui-form-item layui-upload">\n' +
            '        <label class="layui-form-label">图片</label>\n' +
            '        <div class="layui-upload-list">\n' +
            '            <img class="layui-upload-img" id="demo1" style="float:left;">\n' +
            '            <div style="float:left;">\n' +
            '                <button type="button" class="layui-btn" id="upload">上传图片</button>\n' +
            '                <p id="demoText" style="width: 100px; margin-top: 20px"></p>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">名称</label>\n' +
            '        <div class="layui-input-block">\n' +
            '            <input class="layui-input" required type="text" id="works_name" autocomplete="off" placeholder="请输入名称" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">简介</label>\n' +
            '        <div class="layui-input-block">\n' +
            '           <textarea class="tcp_content layui-textarea" placeholder="请输入简介"\n' +
            '                     style="width: 80%; height: 130px; resize:none" maxlength="500"\n' +
            '                     onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()"></textarea>\n' +
            '           <span class="t_h" style="float: right; margin-right: 20%"><i>0</i>/500</span>\n' +
            '       </div>\n' +
            '    </div>\n' +
            '</form>\n' +
            '<script>\n' +
            'layui.use(\'form\', function(){\n' +
            '  var form = layui.form;\n' +
            '  form.render();\n' +
            '});' +
            'layui.use(\'upload\', function() {\n' +
            '    var $ = layui.jquery, upload = layui.upload;\n' +
            '    //普通图片上传使用layui上传图片\n' +
            '    uploadInst = upload.render({\n' +
            '        elem: \'#upload\'\n' +
            '        , url: \'uploadWorksImg.action\'\n' +
            '        , accept : \'images\'\n' +
            '        , multiple : false\n' +
            '        , auto : false\n' +
            '        , field : \'upload\'\n' +
            '        , choose: function (obj) {\n' +
            '            //预读本地文件示例，不支持ie8\n' +
            '            obj.preview(function (index, file, result) {\n' +
            '                $(\'.layui-upload\').css("height", "10%");\n' +
            '                $(\'.layui-upload\').css("marginButtom", "10px");\n' +
            '                $(\'#demo1\').attr(\'src\', result); //图片链接（base64）\n' +
            '                $(\'#demo1\').css("maxWidth", "10%");\n' +
            '                $(\'#demo1\').css("marginRight", "10px");\n' +
            '                photo = result;\n' +
            '            });\n' +
            '        }\n' +
            '        , done: function (res) {\n' +
            '            //如果上传失败\n' +
            '            if (res.code > 0) {\n' +
            '                return layer.msg(\'上传失败\');\n' +
            '            }\n' +
            '            //上传成功\n' +
            '            else\n' +
            '            {\n' +
            '                photo = res.src;\n' +
            '                $(\'#demo1\').attr(\'src\', photo);\n' +
            '                addworks(name, photo, intro);\n' +
            '            }\n' +
            '\n' +
            '        }\n' +
            '        , error: function () {\n' +
            '            //演示失败状态，并实现重传\n' +
            '            var demoText = $(\'#demoText\');\n' +
            '            demoText.html(\'<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>\');\n' +
            '            demoText.find(\'.demo-reload\').on(\'click\', function () {\n' +
            '                uploadInst.upload();\n' +
            '            });\n' +
            '        }\n' +
            '    });\n' +
            '});' +
            '</script>',
        btn: ['确定', '取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "添加作品",
        btn1 : function () {
            addWorks();
            return false;
        },
        btn2 : function () {
            photo = null;
            layer.closeAll();
        }
    });
}

function addWorks() {
    name = $('#works_name').val();
    intro = $('.tcp_content').val();
    if(photo == "" || photo == null){
        layer.msg("请上传照片！");
        return false;
    }
    else if(name == "" || name == null){
        layer.msg("名称不能为空！");
    }
    else if(intro == "" || intro == null){
        layer.msg("简介不能为空！");
    }
    else{
        uploadInst.upload();
    }
}

function addworks(name, photo, intro) {
    $.ajax({
        url : 'addWorks.action',
        type : 'post',
        data : {"name" : name, "photo" : photo, "intro" : intro},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null) {
                layer.open({
                    type: 1,
                    offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + "添加成功！" + '</div>',
                    btn: '关闭',
                    btnAlign: 'c', //按钮居中
                    shade: 0.5, //不显示遮罩
                    title: "信息安全实验室",
                    yes: function () {
                        layer.closeAll();
                    }
                });
                getWorks();
            }
            else{
                layer.msg(result.message);
            }
        },
        error : function () {
            layer.msg('添加失败！');
        }
    });
}

function editWorks() {
    photo = null;
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var works = worksList[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        skin: 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area: ['500px', '600px'],
        content:
            '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px; height: ;">\n' +
            '    <div class="layui-form-item layui-upload">\n' +
            '        <label class="layui-form-label">照片</label>\n' +
            '        <div class="layui-upload-list">\n' +
            '            <img class="layui-upload-img" id="demo1" style="float:left;max-width: 10%; margin-right: 10px" src="'+works.photo+'">\n' +
            '            <div style="float:left;">\n' +
            '                <button type="button" class="layui-btn" id="upload">上传图片</button>\n' +
            '                <p id="demoText" style="width: 100px; margin-top: 20px"></p>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">名称</label>\n' +
            '        <div class="layui-input-block">\n' +
            '            <input class="layui-input layui-disabled" disabled type="text" id="works_name" autocomplete="off" placeholder="请输入名称" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()" value="'+works.name+'">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">简介</label>\n' +
            '        <div class="layui-input-block">\n' +
            '           <textarea class="tcp_content layui-textarea" placeholder="请输入简介"\n' +
            '                     style="width: 80%; height: 130px; resize:none" maxlength="500"\n' +
            '                     onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()">'+works.intro+'</textarea>\n' +
            '           <span class="t_h" style="float: right; margin-right: 20%"><i>'+works.intro.length+'</i>/500</span>\n' +
            '       </div>\n' +
            '    </div>\n' +
            '</form>\n' +
            '<script>\n' +
            'layui.use(\'form\', function(){\n' +
            '  var form = layui.form;\n' +
            '  form.render();\n' +
            '});' +
            'layui.use(\'upload\', function() {\n' +
            '    var $ = layui.jquery, upload = layui.upload;\n' +
            '    //普通图片上传使用layui上传图片\n' +
            '    uploadInst = upload.render({\n' +
            '        elem: \'#upload\'\n' +
            '        , url: \'uploadWorksImg.action\'\n' +
            '        , accept : \'images\'\n' +
            '        , multiple : false\n' +
            '        , auto : false\n' +
            '        , field : \'upload\'\n' +
            '        , choose: function (obj) {\n' +
            '            //预读本地文件示例，不支持ie8\n' +
            '            obj.preview(function (index, file, result) {\n' +
            '                $(\'.layui-upload\').css("height", "10%");\n' +
            '                $(\'.layui-upload\').css("marginButtom", "10px");\n' +
            '                $(\'#demo1\').attr(\'src\', result); //图片链接（base64）\n' +
            '                $(\'#demo1\').css("maxWidth", "10%");\n' +
            '                $(\'#demo1\').css("marginRight", "10px");\n' +
            '                photo = result;\n' +
            '            });\n' +
            '        }\n' +
            '        , done: function (res) {\n' +
            '            //如果上传失败\n' +
            '            if (res.code > 0) {\n' +
            '                return layer.msg(\'上传失败\');\n' +
            '            }\n' +
            '            //上传成功\n' +
            '            else\n' +
            '            {\n' +
            '                photo = res.src;\n' +
            '                $(\'#demo1\').attr(\'src\', photo);\n' +
            '                updateWorks(name, photo, intro);\n' +
            '            }\n' +
            '\n' +
            '        }\n' +
            '        , error: function () {\n' +
            '            //演示失败状态，并实现重传\n' +
            '            var demoText = $(\'#demoText\');\n' +
            '            demoText.html(\'<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>\');\n' +
            '            demoText.find(\'.demo-reload\').on(\'click\', function () {\n' +
            '                uploadInst.upload();\n' +
            '            });\n' +
            '        }\n' +
            '    });\n' +
            '});' +
            '</script>',
        btn: ['确定', '取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "更新作品信息",
        btn1 : function () {
            editworks(works);
            return false;
        },
        btn2 : function () {
            photo = null;
            layer.closeAll();
        }
    });
}

function editworks() {
    var works = arguments[0];
    name = $('#works_name').val();
    intro = $('.tcp_content').val();
    photo = $('#demo1').attr("src");
    if(name == "" || name == null){
        layer.msg("名称不能为空！");
    }
    else if(intro == "" || intro == null){
        layer.msg("简介不能为空！");
    }
    else if (photo != works.photo) {
        uploadInst.upload();
    }
    else{
        updateWorks(name, works.photo, intro);
    }

}

function updateWorks(name, photo, intro) {
    $.ajax({
        url : 'updateWorks.action',
        type : 'post',
        data : {"name" : name, "photo" : photo, "intro" : intro},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null) {
                layer.open({
                    type: 1,
                    offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + "更新成功！" + '</div>',
                    btn: '关闭',
                    btnAlign: 'c', //按钮居中
                    shade: 0.5, //不显示遮罩
                    title: "信息安全实验室",
                    yes: function () {
                        layer.closeAll();
                    }
                });
                getWorks();
            }
            else{
                layer.msg(result.message);
            }
        },
        error : function () {
            layer.msg('更新失败！');
        }
    });
}

function delWorks() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var works = worksList[num-1];
    layer.open({
        type: 1,
        offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除作品 <b>'" + works.name + "'</b> 吗？" + '</div>',
        btn: ['确定', '取消'],
        btnAlign: 'c', //按钮居中
        shade: 0.5, //不显示遮罩
        title: "删除作品",
        btn1 : function () {
            delworks(works);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delworks() {
    var id = arguments[0].worksId;
    $.ajax({
        url : 'delWorks.action',
        type : 'post',
        data : {"worksId" : id},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                layer.open({
                    type: 1,
                    offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + "删除成功！" + '</div>',
                    btn: '确定',
                    btnAlign: 'c', //按钮居中
                    shade: 0.5, //不显示遮罩
                    title: "信息安全实验室",
                    yes : function () {
                        layer.closeAll();
                    }
                });
                getWorks();
            }
            else
            {
                layer.msg(result.message);
            }
        },
        error : function () {
            layer.msg("删除失败！");
        }
    });
}

function textarea_fun(){
    $(".tcp_content").val($(".tcp_content").val().substring(0,500));
    $(".t_h i").html($(".tcp_content").val().length);
    if(window.event.keyCode  == 13){
        return false;
    }
}