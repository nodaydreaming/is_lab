var postList;
var name, gender, grade, telephone, research, intro, photo;
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
    getStudents();
};

function getStudents() {
    $.ajax({
        url : 'getStudents.action',
        type : 'post',
        data : {"type" : 2},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                fillStudents(result.students)
            }
        },
        error : function () {
            layer.msg("请求失败！");
        }
    });
}

function fillStudents() {
    postList = arguments[0];
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");

    for (var i = 0; i < postList.length; ++i){
        var student = postList[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.style.textAlign = "center";
        td2.innerText = student.name;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.style.textAlign = "center";
        td3.innerText = student.gender;
        td3.style.wordWrap = "break-word";
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.innerText = student.grade;
        td4.style.wordWrap = "break-word";
        tr.appendChild(td4);

        var td5 = document.createElement('td');
        td5.style.textAlign = "center";
        td5.innerText = student.telephone;
        td5.style.wordWrap = "break-word";
        tr.appendChild(td5);

        var td6 = document.createElement('td');
        td6.style.textAlign = "center";
        td6.innerText = student.research;
        td6.style.wordWrap = "break-word";
        tr.appendChild(td6);

        var td7 = document.createElement('td');
        td7.innerText = student.intro;
        td7.style.wordWrap = "break-word";
        tr.appendChild(td7);

        var td8 = document.createElement('td');
        td8.style.textAlign = "center";
        td8.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editStudent" + (i+1);
        a1.className = "layui-btn layui-btn-normal layui-btn-xs";
        var i1 = document.createElement('i');
        i1.className = "layui-icon layui-icon-edit";
        a1.appendChild(i1);
        a1.innerHTML = a1.innerHTML + "编辑";
        a1.onclick = editStudent;

        var a2 = document.createElement('a');
        a2.id = "delStudent" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delStudent;
        td8.appendChild(a1);
        td8.appendChild(a2);
        tr.appendChild(td8);

        tbody.appendChild(tr);
    }
}

function openAddStudent() {
    photo = null;
    layer.open({
        type: 1,
        offset: 'auto',
        skin: 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area: ['500px', '610px'],
        content:
            '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px; height: ;">\n' +
            '    <div class="layui-form-item layui-upload">\n' +
            '        <label class="layui-form-label">照片</label>\n' +
            '        <div class="layui-upload-list">\n' +
            '            <img class="layui-upload-img" id="demo1" style="float:left;">\n' +
            '            <div style="float:left;">\n' +
            '                <button type="button" class="layui-btn" id="upload">上传图片</button>\n' +
            '                <p id="demoText" style="width: 100px; margin-top: 20px"></p>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">姓名</label>\n' +
            '        <div class="layui-input-block">\n' +
            '            <input class="layui-input" required type="text" id="student_name" autocomplete="off" placeholder="请输入姓名" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">性别</label>\n' +
            '        <div class="layui-input-block">\n' +
            '            <input type="radio" name="sex" value="男" title="男" checked="">' +
            '            <div class="layui-unselect layui-form-radio layui-form-radioed">' +
            '                <i class="layui-anim layui-icon layui-anim-scaleSpring"></i>' +
            '                <p>男</p>' +
            '            </div>\n' +
            '            <input type="radio" name="sex" value="女" title="女">' +
            '            <div class="layui-unselect layui-form-radio">' +
            '                <i class="layui-anim layui-icon"></i>' +
            '                <p>女</p>' +
            '            </div>\n' +
            '        </div>' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">年级</label>\n' +
            '        <div class="layui-input-block">\n' +
            '            <input type="text" id="student_grade"  autocomplete="off" placeholder="请输入年级" class="layui-input" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">手机号</label>\n' +
            '        <div class="layui-input-block">\n' +
            '            <input class="layui-input" id="student_telephone" lay-verify="required|phone" type="text" name="telephone" placeholder="请输入手机号" autocomplete="off" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">研究方向</label>\n' +
            '        <div class="layui-input-block">\n' +
            '            <input class="layui-input" id="student_research" lay-verify="required" type="text" name="research" placeholder="请输入研究方向" autocomplete="off" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="layui-form-item">\n' +
            '        <label class="layui-form-label">简介</label>\n' +
            '        <div class="layui-input-block">\n' +
            '           <textarea class="tcp_content layui-textarea" placeholder="请输入简介"\n' +
            '                     style="width: 80%; height: 100px; resize:none" maxlength="100"\n' +
            '                     onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()"></textarea>\n' +
            '           <span class="t_h" style="float: right; margin-right: 20%"><i>0</i>/100</span>\n' +
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
            '        , url: \'uploadStudentImg.action\'\n' +
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
            '                addstudent(name, gender, photo, grade, telephone, research, intro);\n' +
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
        title: "添加研究生",
        btn1 : function () {
            addStudent();
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function addStudent() {
    name = $('#student_name').val();
    intro = $('.tcp_content').val();
    telephone = $('#student_telephone').val();
    gender = ((document.getElementsByClassName('layui-form-radioed'))[0]).childNodes[1].innerText;
    grade = $('#student_grade').val();
    research = $('#student_research').val();
    if(photo == "" || photo == null){
        layer.msg("请上传照片！");
        return false;
    }
    else if(name == "" || name == null){
        layer.msg("姓名不能为空！");
    }
    else if(gender == "" || gender == null){
        layer.msg("请选择性别！");
    }
    else if(grade == "" || grade == null){
        layer.msg("年级不能为空！");
    }
    else if(telephone == "" || telephone == null) {
        layer.msg("手机号不能为空！ ");
    }
    else if(research == "" || research == null) {
        layer.msg("研究方向不能为空！ ");
    }
    else{
        uploadInst.upload();
    }
}

function addstudent(name, gender, photo, grade, telephone, research, intro) {
    $.ajax({
        url : 'addStudent.action',
        type : 'post',
        data : {"name" : name, "photo" : photo, "gender" : gender, "telephone" : telephone, "research" : research, "grade" : grade, "intro" : intro, "type" : 2},
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
                getStudents();
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

function editStudent() {
    photo = null;
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var student = postList[num-1];
    if(student.gender == "男"){
        layer.open({
            type: 1,
            offset: 'auto',
            skin: 'layui-layer-lan',
            id: 'layerDemo1', //防止重复弹出
            area: ['500px', '640px'],
            content:
                '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px;">\n' +
                '    <div class="layui-form-item layui-upload">\n' +
                '        <label class="layui-form-label">照片</label>\n' +
                '        <div class="layui-upload-list">\n' +
                '            <img class="layui-upload-img" id="demo1" style="float:left;max-width: 10%; margin-right: 10px" src="'+student.photo+'">\n' +
                '            <div style="float:left;">\n' +
                '                <button type="button" class="layui-btn" id="upload">上传图片</button>\n' +
                '                <p id="demoText" style="width: 100px; margin-top: 20px"></p>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">姓名</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input class="layui-input" lay-verify="required" type="text" id="student_name" autocomplete="off" placeholder="请输入姓名" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()" value="'+student.name+'">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">性别</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="radio" name="sex" value="男" title="男" checked="">' +
                '            <div class="layui-unselect layui-form-radio layui-form-radioed">' +
                '                <i class="layui-anim layui-icon layui-anim-scaleSpring"></i>' +
                '                <p>男</p>' +
                '            </div>\n' +
                '            <input type="radio" name="sex" value="女" title="女">' +
                '            <div class="layui-unselect layui-form-radio">' +
                '                <i class="layui-anim layui-icon"></i>' +
                '                <p>女</p>' +
                '            </div>\n' +
                '        </div>' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">年级</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" id="student_grade"  autocomplete="off" placeholder="请输入年级" class="layui-input" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()" value="'+student.grade+'">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">手机号</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input class="layui-input layui-disabled" disabled id="student_telephone" lay-verify="required|phone" type="text" name="telephone" placeholder="请输入手机号" autocomplete="off" style="width: 250px;" value="'+student.telephone+'" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">研究方向</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" id="student_research"  autocomplete="off" placeholder="请输入研究方向" class="layui-input" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()" value="'+student.research+'">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">简介</label>\n' +
                '        <div class="layui-input-block">\n' +
                '           <textarea class="tcp_content layui-textarea" placeholder="请输入简介"\n' +
                '                     style="width: 80%; height: 130px; resize:none" maxlength="100"\n' +
                '                     onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()">'+student.intro+'</textarea>\n' +
                '           <span class="t_h" style="float: right; margin-right: 20%"><i>'+student.intro.length+'</i>/100</span>\n' +
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
                '        , url: \'uploadStudentImg.action\'\n' +
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
                '                updateStudent(name, gender, photo, telephone, grade, research, intro);\n' +
                '}\n' +
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
            title: "更新研究生信息",
            btn1 : function () {
                editstudent(student);
                return false;
            },
            btn2 : function () {
                layer.closeAll();
            }
        });
    }
    else{
        layer.open({
            type: 1,
            offset: 'auto',
            skin: 'layui-layer-lan',
            id: 'layerDemo1', //防止重复弹出
            area: ['500px', '640px'],
            content:
                '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px;">\n' +
                '    <div class="layui-form-item layui-upload">\n' +
                '        <label class="layui-form-label">照片</label>\n' +
                '        <div class="layui-upload-list">\n' +
                '            <img class="layui-upload-img" id="demo1" style="float:left;max-width: 10%; margin-right: 10px" src="'+student.photo+'">\n' +
                '            <div style="float:left;">\n' +
                '                <button type="button" class="layui-btn" id="upload">上传图片</button>\n' +
                '                <p id="demoText" style="width: 100px; margin-top: 20px"></p>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">姓名</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input class="layui-input" lay-verify="required" type="text" id="student_name" autocomplete="off" placeholder="请输入姓名" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()" value="'+student.name+'">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">性别</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="radio" name="sex" value="男" title="男" checked="">' +
                '            <div class="layui-unselect layui-form-radio">' +
                '                <i class="layui-anim layui-icon"></i>' +
                '                <p>男</p>' +
                '            </div>\n' +
                '            <input type="radio" name="sex" value="女" title="女">' +
                '            <div class="layui-unselect layui-form-radio layui-form-radioed">' +
                '                <i class="layui-anim layui-icon layui-anim-scaleSpring"></i>' +
                '                <p>女</p>' +
                '            </div>\n' +
                '        </div>' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">年级</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" id="student_grade"  autocomplete="off" placeholder="请输入年级" class="layui-input" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()" value="'+student.grade+'">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">手机号</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input class="layui-input layui-disabled" disabled id="student_telephone" lay-verify="required|phone" type="text" name="telephone" placeholder="请输入手机号" autocomplete="off" style="width: 250px;" value="'+student.telephone+'" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">研究方向</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" id="student_research"  autocomplete="off" placeholder="请输入研究方向" class="layui-input" style="width: 250px;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()" value="'+student.research+'">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">简介</label>\n' +
                '        <div class="layui-input-block">\n' +
                '           <textarea class="tcp_content layui-textarea" placeholder="请输入简介"\n' +
                '                     style="width: 80%; height: 130px; resize:none" maxlength="100"\n' +
                '                     onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()">'+student.intro+'</textarea>\n' +
                '           <span class="t_h" style="float: right; margin-right: 20%"><i>'+student.intro.length+'</i>/100</span>\n' +
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
                '        , url: \'uploadStudentImg.action\'\n' +
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
                '                updateStudent(name, gender, photo, telephone, grade, research, intro);\n' +
                '}\n' +
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
            title: "更新研究生",
            btn1 : function () {
                editstudent(student);
                return false;
            },
            btn2 : function () {
                layer.closeAll();
            }
        });
    }
}

function editstudent() {
    var student = arguments[0];
    name = $('#student_name').val();
    intro = $('.tcp_content').val();
    telephone = $('#student_telephone').val();
    gender = ((document.getElementsByClassName('layui-form-radioed'))[0]).childNodes[1].innerText;
    grade = $('#student_grade').val();
    research = $('#student_research').val();
    if(name == "" || name == null){
        layer.msg("姓名不能为空！");
    }
    else if(gender == "" || gender == null){
        layer.msg("请选择性别！");
    }
    else if(grade == "" || grade == null){
        layer.msg("年级不能为空！");
    }
    else if(telephone == "" || telephone == null) {
        layer.msg("手机号不能为空！ ");
    }
    else if(research == "" || research == null) {
        layer.msg("研究方向不能为空！ ");
    }
    else if (photo == null) {
        updateStudent(name, gender, student.photo, telephone, grade, research, intro);
    }
    else if (photo != student.photo) {
            uploadInst.upload();
    }
    else {
        updateStudent(name, gender, student.photo, telephone, grade, research, intro);
    }

}

function updateStudent(name, gender, photo, telephone, grade, research, intro) {
    $.ajax({
        url : 'updateStudent.action',
        type : 'post',
        data : {"name" : name, "photo" : photo, "gender" : gender, "telephone" : telephone, "research" : research, "grade" : grade, "intro" : intro, "type" : 2},
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
                getStudents();
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

function delStudent() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var student = postList[num-1];
    layer.open({
        type: 1,
        offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除研究生 <b>'" + student.name + "'</b> 吗？" + '</div>',
        btn: ['确定', '取消'],
        btnAlign: 'c', //按钮居中
        shade: 0.5, //不显示遮罩
        title: "删除研究生信息",
        btn1 : function () {
            delstudent(student);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delstudent() {
    var id = arguments[0].studentId;
    $.ajax({
        url : 'delStudent.action',
        type : 'post',
        data : {"studentId" : id},
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
                console.log(123);
                getStudents();
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
    $(".tcp_content").val($(".tcp_content").val().substring(0,100));
    $(".t_h i").html($(".tcp_content").val().length);
    if(window.event.keyCode  == 13){
        return false;
    }
}