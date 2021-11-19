var paperList;
var name, date, address;
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
    getPapers();
};

function getPapers() {
    $.ajax({
        url : 'getPapers.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                fillPaper(result.papers)
            }
        },
        error : function () {
            layer.msg("请求失败！");
        }

    });
}

function fillPaper() {
    paperList = arguments[0];
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");

    for (var i = 0; i < paperList.length; ++i){
        var paper = paperList[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.innerText = paper.name;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.style.wordWrap = "break-word";
        td4.innerText = new Date(paper.date).Format("yyyy-MM-dd");
        tr.appendChild(td4);

        var td3 = document.createElement('td');
        td3.style.textAlign = "center";
        td3.style.padding = "0px";

        var a0 = document.createElement('a');
        a0.id = "editPaper" + (i+1);
        a0.className = "layui-btn layui-btn-normal layui-btn-xs";
        var filename = paper.address.toString().substring(paper.address.lastIndexOf('/') + 21);
        a0.href = "downFile.action?filename=" + filename + "&address=" + paper.address.substring(7);
        var i0 = document.createElement('i');
        i0.className = "layui-icon layui-icon-download-circle";
        a0.appendChild(i0);
        a0.innerHTML = a0.innerHTML + "下载";

        var a1 = document.createElement('a');
        a1.id = "editPaper" + (i+1);
        a1.className = "layui-btn layui-btn-normal layui-btn-xs";
        var i1 = document.createElement('i');
        i1.className = "layui-icon layui-icon-edit";
        a1.appendChild(i1);
        a1.innerHTML = a1.innerHTML + "编辑";
        a1.onclick = editPaper;

        var a2 = document.createElement('a');
        a2.id = "delPaper" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delPaper;

        td3.appendChild(a0);
        td3.appendChild(a1);
        td3.appendChild(a2);

        tr.appendChild(td3);
        tbody.appendChild(tr);
    }
}

function openAddPaper() {
    address = null;
    layer.open({
        type: 1,
        offset: 'auto',
        skin: 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area: ['600px', '400px'],
        content:
            '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px; height: ;">\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">名称</label>\n' +
            '        <div class="layui-input-block">\n' +
            '           <textarea class="tcp_content layui-textarea" placeholder="请输入名称"\n' +
            '                     style="width: 80%; height: 130px; resize:none" maxlength="200"\n' +
            '                     onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()"></textarea>\n' +
            '           <span class="t_h" style="float: right; margin-right: 20%"><i>0</i>/200</span>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">发表日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="paper_date" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '    <div class="layui-form-item layui-upload">\n' +
            '        <label class="layui-form-label">文件</label>\n' +
            '        <button type="button" class="layui-btn layui-btn-normal" id="upload">\n' +
            '        <i class="layui-icon layui-icon-upload"></i>选择文件</button>\n' +
            '        <input class="layui-upload-file" type="file" accept="undefined" name="file">\n' +
            '    </div>\n' +
            '</form>\n' +
            '<script>\n' +
            'layui.use(\'form\', function(){\n' +
            '  var form = layui.form;\n' +
            '  form.render();\n' +
            '});' +
            'layui.use(\'laydate\', function(){\n' +
            '        var laydate = layui.laydate;\n' +
            '\n' +
            '        //执行一个laydate实例\n' +
            '        laydate.render({\n' +
            '            elem: \'#paper_date\' \n' +
            '        });\n' +
            '    });' +
            'layui.use(\'upload\', function() {\n' +
            '    var $ = layui.jquery, upload = layui.upload;\n' +
            '    //普通图片上传使用layui上传图片\n' +
            '    uploadInst = upload.render({\n' +
            '        elem: \'#upload\'\n' +
            '        , url: \'uploadPaper.action\'\n' +
            '        , accept : \'file\'\n' +
            '        , multiple : false\n' +
            '        , auto : false\n' +
            '        , field : \'upload\'\n' +
            '        , choose: function (obj) {\n' +
            '            //预读本地文件示例，不支持ie8\n' +
            '            obj.preview(function (index, file, result) {\n' +
            '                address = file.name;' +
            '                var span = document.createElement("span");\n' +
            '                span.className = "layui-inline layui-upload-choose";\n' +
            '                span.innerText=file.name;\n' +
            '                document.getElementsByClassName("layui-upload")[0].appendChild(span);\n' +
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
            '                address = res.src;\n' +
            '                addpaper(name, date, address);\n' +
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
        title: "添加论文",
        btn1 : function () {
            addPaper();
            return false;
        },
        btn2 : function () {
            address = null;
            layer.closeAll();
        }
    });
}

function addPaper() {
    name = $('.tcp_content').val();
    date = $("#paper_date").val();
    if(name == "" || name == null){
        layer.msg("名称不能为空！");
    }
    else if(date == null || date == ""){
        layer.msg("发表日期不能为空！");
    }
    else if(address == "" || address == null){
        layer.msg("请上传文件！");
        return false;
    }
    else{
        uploadInst.upload();
    }
}

function addpaper(name, date, address) {
    $.ajax({
        url : 'addPaper.action',
        type : 'post',
        data : {"name" : name, "date" : date, "address" : address},
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
                getPapers();
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

function editPaper() {
    address = null;
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var paper = paperList[num-1];
    var filename = paper.address.substring(paper.address.lastIndexOf('/') + 21);
    layer.open({
        type: 1,
        offset: 'auto',
        skin: 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area: ['600px', '400px'],
        content:
            '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px; height: ;">\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">名称</label>\n' +
            '        <div class="layui-input-block">\n' +
            '           <textarea class="tcp_content layui-textarea layui-disabled" placeholder="请输入名称"\n' +
            '                     disabled style="width: 80%; height: 130px; resize:none" maxlength="200"\n' +
            '                     onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()">'+paper.name+'</textarea>\n' +
            '           <span class="t_h" style="float: right; margin-right: 20%"><i>'+paper.name.length+'</i>/200</span>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">发表日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="paper_date" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value="'+(new Date(paper.date)).Format("yyyy-MM-dd")+'">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '    <div class="layui-form-item layui-upload">\n' +
            '        <label class="layui-form-label">文件</label>\n' +
            '        <button type="button" class="layui-btn layui-btn-normal" id="upload">\n' +
            '        <i class="layui-icon layui-icon-upload"></i>选择文件</button>\n' +
            '        <input class="layui-upload-file" type="file" accept="undefined" name="file">\n' +
            '        <span class="layui-inline layui-upload-choose">'+filename+'</span>' +
            '    </div>\n' +
            '</form>\n' +
            '<script>\n' +
            'layui.use(\'form\', function(){\n' +
            '  var form = layui.form;\n' +
            '  form.render();\n' +
            '});' +
            'layui.use(\'laydate\', function(){\n' +
            '        var laydate = layui.laydate;\n' +
            '\n' +
            '        //执行一个laydate实例\n' +
            '        laydate.render({\n' +
            '            elem: \'#paper_date\' \n' +
            '        });\n' +
            '    });' +
            'layui.use(\'upload\', function() {\n' +
            '    var $ = layui.jquery, upload = layui.upload;\n' +
            '    //普通图片上传使用layui上传图片\n' +
            '    uploadInst = upload.render({\n' +
            '        elem: \'#upload\'\n' +
            '        , url: \'uploadPaper.action\'\n' +
            '        , accept : \'file\'\n' +
            '        , multiple : false\n' +
            '        , auto : false\n' +
            '        , field : \'upload\'\n' +
            '        , choose: function (obj) {\n' +
            '            //预读本地文件示例，不支持ie8\n' +
            '            obj.preview(function (index, file, result) {\n' +
            '                address = file.name;' +
            '                var span = document.createElement("span");\n' +
            '                span.className = "layui-inline layui-upload-choose";\n' +
            '                span.innerText=file.name;\n' +
            '                document.getElementsByClassName("layui-upload")[0].appendChild(span);\n' +
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
            '                address = res.src;\n' +
            '                updatePaper(name, date, address);\n' +
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
        title: "更新论文信息",
        btn1 : function () {
            editpaper(paper);
            return false;
        },
        btn2 : function () {
            photo = null;
            layer.closeAll();
        }
    });
}

function editpaper() {
    var paper = arguments[0];
    name = $('.tcp_content').val();
    date = $('#paper_date').val();
    if(name == "" || name == null){
        layer.msg("名称不能为空！");
    }
    else if(date == null || date == ""){
        layer.msg("发表日期不能为空！");
    }
    else if (address != null && address != paper.address) {
        uploadInst.upload();
    }
    else{
        updatePaper(name, date, paper.address);
    }

}

function updatePaper(name, date, address) {
    $.ajax({
        url : 'updatePaper.action',
        type : 'post',
        data : {"name" : name, "date" : date, "address" : address},
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
                getPapers();
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

function delPaper() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var paper = paperList[num-1];
    layer.open({
        type: 1,
        offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除论文 <b>'" + paper.name + "'</b> 吗？" + '</div>',
        btn: ['确定', '取消'],
        btnAlign: 'c', //按钮居中
        shade: 0.5, //不显示遮罩
        title: "删除论文",
        btn1 : function () {
            delpaper(paper);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delpaper() {
    var id = arguments[0].paperId;
    $.ajax({
        url : 'delPaper.action',
        type : 'post',
        data : {"paperId" : id},
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
                getPapers();
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
    $(".tcp_content").val($(".tcp_content").val().substring(0,200));
    $(".t_h i").html($(".tcp_content").val().length);
    if(window.event.keyCode  == 13){
        return false;
    }
}

Date.prototype.Format = function(fmt)
{
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}