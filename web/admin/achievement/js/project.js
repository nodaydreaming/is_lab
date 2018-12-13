
var projectList;

window.onload = function () {
    getAllProjects();
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
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.style.textAlign = "center";
        td3.innerText = project1.number;
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.innerText = parseDateTime(project1.startdate) + "至" + parseDateTime(project1.enddate);
        tr.appendChild(td4);

        var td5 = document.createElement('td');
        td5.style.textAlign = "center";
        td5.innerText = project1.principal;
        tr.appendChild(td5);

        var td6 = document.createElement('td');
        td6.style.textAlign = "center";
        td6.innerText = project1.type;
        tr.appendChild(td6);

        var td7 = document.createElement('td');
        td7.style.textAlign = "center";
        td7.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editResearch" + (i+1);
        a1.className = "layui-btn layui-btn-xs";
        a1.innerText = "编辑";
        a1.onclick = editProject;

        var a2 = document.createElement('a');
        a2.id = "delResearch" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        a2.innerText = "删除";
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
            '    <label class="layui-form-label">开始时间</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_starttime" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">结束时间</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="project_endtime" autocomplete="off" placeholder="yyyy-MM-dd" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
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
            '</form>' +
            '<script>' +
            'layui.use(\'laydate\', function(){\n' +
            '        var laydate = layui.laydate;\n' +
            '\n' +
            '        //执行一个laydate实例\n' +
            '        laydate.render({\n' +
            '            elem: \'#project_starttime\' \n' +
            '        });\n' +
            '        laydate.render({\n' +
            '            elem: \'#project_endtime\' \n' +
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
            var starttime = $("#project_starttime").val();
            var endtime = $("#project_endtime").val();
            var principal = $("#project_principal").val();
            var type = $("#project_type").val();

            if(name == null || name === ""){
                layer.msg("项目名称不能为空！");
            }
            else if(number == null || number === ""){
                layer.msg("项目编号不能为空！");
            }
            else if(starttime == null || starttime === ""){
                layer.msg("开始时间不能为空！");
            }
            else if(endtime == null || endtime === ""){
                layer.msg("结束时间不能为空！");
            }
            else if(principal == null || principal === ""){
                layer.msg("负责人不能为空！");
            }
            else if(type == null || type === ""){
                layer.msg("项目类型不能为空！");
            }
            else {
                addproject(name, number, starttime, endtime, principal, type);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function addproject(name, number, starttime, endtime, principal, type) {
    var mydata = {
        "name" : name,
        "number" : number,
        "startdate" : starttime,
        "enddate" : endtime,
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
    
}

function editproject() {
    
}

function delProject() {
    
}

function delproject() {
}


function parseDateTime(dat)
{
    var mapping = {
        "Jan" : 1,"Feb" : 2,"Apr" : 3,"May" : 4,"June" : 5,"July" : 6,
        "July" : 7,"Aug" : 8,"Sep" : 9,"Oct" : 10,"Nov" : 11,"Dec" : 12
    };
    var y = dat.substring(dat.indexOf(",")+1, dat.indexOf(",")+6);
    var m = dat.substring(0,3);
    var d = dat.substring(dat.indexOf(" ")+1, dat.indexOf(","));

    return y + "-" + m + "-" + d;
}