var projectList;

window.onload = function () {
    getAllProjects();
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
}

function getAllProjects() {
    $.ajax({
       url : 'getAllProjects.action',
       type : 'post',
       scriptCharset : 'utf-8',
       success : function (result) {
           if(result.message != null){
                layer.msg(result.message);
           }
           else if(result.projects != null)
           {
               projectList = result.projects;
               fillProjects(result.projects);
           }
       } ,
        error : function () {
            layer.open({
                type: 1,
                offset: 'auto',
                id: 'layerDemo1', //防止重复弹出
                content: '<div style="padding: 20px 100px;">' + "请求Action失败！" + '</div>',
                btn: '关闭',
                btnAlign: 'c',
                shade: 0.5,
                title: "信息安全实验室",
                yes: function () {
                    layer.closeAll();
                }
            });
        }
    });
}

function fillProjects() {
    projectList = arguments[0];
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");

    for (var i = 0; i < projectList.length; ++i){
        var project1 = projectList[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.style.textAlign = "center";
        td2.innerText = project1.name;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.style.textAlign = "center";
        td3.innerText = project1.number;
        td3.style.wordWrap = "break-word";
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.innerText = new Date(project1.startdate).Format("yyyy-MM-dd") + " 至 " + new Date(project1.enddate).Format("yyyy-MM-dd");
        td4.style.wordWrap = "break-word";
        tr.appendChild(td4);

        var td5 = document.createElement('td');
        td5.style.textAlign = "center";
        td5.innerText = project1.principal;
        td5.style.wordWrap = "break-word";
        tr.appendChild(td5);

        var td6 = document.createElement('td');
        td6.innerText = project1.type;
        td6.style.wordWrap = "break-word";
        tr.appendChild(td6);

        var td7 = document.createElement('td');
        td7.style.textAlign = "center";
        td7.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editResearch" + (i+1);
        a1.className = "layui-btn layui-btn-normal layui-btn-xs";
        var i1 = document.createElement('i');
        i1.className = "layui-icon layui-icon-edit";
        a1.appendChild(i1);
        a1.innerHTML = a1.innerHTML + "编辑";
        a1.onclick = editProject;

        var a2 = document.createElement('a');
        a2.id = "delResearch" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delProject;
        td7.appendChild(a1);
        td7.appendChild(a2);

        tr.appendChild(td7);
        tbody.appendChild(tr);
    }
}

function addProject() {
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['600px', '500px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_name" autocomplete="off" placeholder="请输入项目名称" class="layui-input" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目编号</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_number" autocomplete="off" placeholder="请输入项目编号" class="layui-input" style="width: 250px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">开始日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_startdate" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">结束日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_enddate" autocomplete="off" placeholder="yyyy-MM-dd" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目负责人</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_principal" autocomplete="off" placeholder="请输入负责人" class="layui-input" style="width: 150px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目类型</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_type" autocomplete="off" placeholder="请输入项目类型" class="layui-input" style="width: 250px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
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
            '            elem: \'#project_startdate\' \n' +
            '        });\n' +
            '        laydate.render({\n' +
            '            elem: \'#project_enddate\' \n' +
            '        });\n' +
            '    });' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "添加项目",
        btn1 : function () {
            var name = $("#project_name").val();
            var number = $("#project_number").val();
            var startdate = $("#project_startdate").val();
            var enddate = $("#project_enddate").val();
            var principal = $("#project_principal").val();
            var type = $("#project_type").val();

            if(name == null || name === ""){
                layer.msg("项目名称不能为空！");
            }
            else if(number == null || number === ""){
                layer.msg("项目编号不能为空！");
            }
            else if(startdate == null || startdate === ""){
                layer.msg("开始日期不能为空！");
            }
            else if(enddate == null || enddate === ""){
                layer.msg("结束日期不能为空！");
            }
            else if(principal == null || principal === ""){
                layer.msg("负责人不能为空！");
            }
            else if(type == null || type === ""){
                layer.msg("项目类型不能为空！");
            }
            else if(new Date(enddate).getTime() < new Date(startdate).getTime()){
                layer.msg("结束日期应该晚于开始日期！")
            }
            else {
                addproject(name, number, startdate, enddate, principal, type);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function addproject(name, number, startdate, enddate, principal, type) {
    var mydata = {
        "name" : name,
        "number" : number,
        "startdate" : startdate,
        "enddate" : enddate,
        "principal" : principal,
        "type" : type
    };
    $.ajax({
        url : 'addProject.action',
        type : 'post',
        data : mydata,
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + "添加成功！" + '</div>',
                    btn: '关闭',
                    btnAlign: 'c',
                    shade: 0.5,
                    title: "信息安全实验室",
                    yes: function () {
                        layer.closeAll();
                    }
                });
                getAllProjects();
            }
            else
            {
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + result.message + '</div>',
                    btn: '关闭',
                    btnAlign: 'c',
                    shade: 0.5,
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
                offset: 'auto',
                id: 'layerDemo2', //防止重复弹出
                content: '<div style="padding: 20px 100px;">' + "添加失败！" + '</div>',
                btn: '关闭',
                btnAlign: 'c',
                shade: 0.5,
                title: "信息安全实验室",
                yes: function () {
                    layer.closeAll();
                }
            });
        }
    });
}

function editProject() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var project1 = projectList[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['600px', '500px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_name" autocomplete="off" placeholder="请输入项目名称" disabled class="layui-input layui-disabled" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+project1.name+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目编号</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_number" autocomplete="off" placeholder="请输入项目编号" class="layui-input" style="width: 250px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+project1.number+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">开始日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_startdate" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+new Date(project1.startdate).Format("yyyy-MM-dd")+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">结束日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_enddate" autocomplete="off" placeholder="yyyy-MM-dd" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+new Date(project1.enddate).Format("yyyy-MM-dd")+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目负责人</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_principal" autocomplete="off" placeholder="请输入负责人" class="layui-input" style="width: 150px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+project1.principal+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">项目类型</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_type" autocomplete="off" placeholder="请输入项目类型" class="layui-input" style="width: 250px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+project1.type+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</form>' +
            '<script>' +
            'layui.use(\'laydate\', function(){\n' +
            '        var laydate = layui.laydate;\n' +
            '\n' +
            '        //执行一个laydate实例\n' +
            '        laydate.render({\n' +
            '            elem: \'#project_startdate\' \n' +
            '        });\n' +
            '        laydate.render({\n' +
            '            elem: \'#project_enddate\' \n' +
            '        });\n' +
            '    });' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "更新项目信息",
        btn1 : function () {
            var name = $("#project_name").val();
            var number = $("#project_number").val();
            var startdate = $("#project_startdate").val();
            var enddate = $("#project_enddate").val();
            var principal = $("#project_principal").val();
            var type = $("#project_type").val();

            if(name == null || name === ""){
                layer.msg("项目名称不能为空！");
            }
            else if(number == null || number === ""){
                layer.msg("项目编号不能为空！");
            }
            else if(startdate == null || startdate === ""){
                layer.msg("开始日期不能为空！");
            }
            else if(enddate == null || enddate === ""){
                layer.msg("结束日期不能为空！");
            }
            else if(principal == null || principal === ""){
                layer.msg("负责人不能为空！");
            }
            else if(new Date(enddate).getTime() < new Date(startdate).getTime()){
                layer.msg("结束日期应该晚于开始日期！")
            }
            else if(type == null || type === ""){
                layer.msg("项目类型不能为空！");
            }
            else {
                project1.name = name;
                project1.number = number;
                project1.startdate = startdate;
                project1.enddate  = enddate;
                project1.principal = principal;
                project1.type = type;
                editproject(project1);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function editproject() {
    var pro = arguments[0];
    $.ajax({
        url : 'updateProject.action',
        type : 'post',
        data : {"projectId" : pro.projectId, "name" : pro.name, "number" : pro.number, "startdate" : pro.startdate,
        "enddate" : pro.enddate, "principal" : pro.principal, "type" : pro.type},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + "更新成功！" + '</div>',
                    btn: '关闭',
                    btnAlign: 'c',
                    shade: 0.5,
                    title: "信息安全实验室",
                    yes: function () {
                        layer.closeAll();
                    }
                });
                getAllProjects();
            }
            else
            {
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + result.message + '</div>',
                    btn: '关闭',
                    btnAlign: 'c',
                    shade: 0.5,
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
                offset: 'auto',
                id: 'layerDemo2', //防止重复弹出
                content: '<div style="padding: 20px 100px;">' + "更新失败！" + '</div>',
                btn: '关闭',
                btnAlign: 'c',
                shade: 0.5,
                title: "信息安全实验室",
                yes: function () {
                    layer.closeAll();
                }
            });
        }
    });
}

function delProject() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var project1 = projectList[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除项目 <b>'"+project1.name+"'</b> 吗?" + '</div>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "信息安全实验室",
        yes : function () {
            delproject(project1);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delproject() {
    var pro = arguments[0];
    $.ajax({
        url: 'delProject.action',
        type: 'post',
        data: {
            "projectId": pro.projectId,
        },
        scriptCharset: 'utf-8',
        success: function (result) {
            if(result.message == null){
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + "删除成功！" + '</div>',
                    btn: '关闭',
                    btnAlign: 'c',
                    shade: 0.5,
                    title: "信息安全实验室",
                    yes: function () {
                        layer.closeAll();
                    }
                });
                getAllProjects();
            }
            else
            {
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + result.message + '</div>',
                    btn: '关闭',
                    btnAlign: 'c',
                    shade: 0.5,
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
                offset: 'auto',
                id: 'layerDemo2', //防止重复弹出
                content: '<div style="padding: 20px 100px;">' + "删除失败！" + '</div>',
                btn: '关闭',
                btnAlign: 'c',
                shade: 0.5,
                title: "信息安全实验室",
                yes: function () {
                    layer.closeAll();
                }
            });
        }
    });
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