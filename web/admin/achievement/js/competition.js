var competitionList;

window.onload = function () {
    getAllCompetitions();
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
};

function getAllCompetitions() {
    $.ajax({
        url : 'getCompetitions.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message != null){
                layer.msg(result.message);
            }
            else if(result.competitions != null)
            {
                fillCompetitions(result.competitions);
            }
        } ,
        error : function () {
            layer.msg("请求失败！");
        }
    });
}

function fillCompetitions() {
    competitionList = arguments[0];
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");

    for (var i = 0; i < competitionList.length; ++i){
        var competition = competitionList[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.style.textAlign = "center";
        td2.innerText = competition.name;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.style.textAlign = "center";
        td3.innerText = competition.worksName;
        td3.style.wordWrap = "break-word";
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.innerText = competition.awardLevel;
        td4.style.wordWrap = "break-word";
        tr.appendChild(td4);

        var td5 = document.createElement('td');
        td5.style.textAlign = "center";
        td5.innerText = competition.instructor;
        td5.style.wordWrap = "break-word";
        tr.appendChild(td5);

        var td6 = document.createElement('td');
        td6.style.textAlign = "center";
        td6.innerText = competition.teamMember;
        td6.style.wordWrap = "break-word";
        tr.appendChild(td6);

        var td7 = document.createElement('td');
        td7.style.textAlign = "center";
        td7.innerText = new Date(competition.date).Format("yyyy-MM-dd");
        td7.style.wordWrap = "break-word";
        tr.appendChild(td7);

        var td8 = document.createElement('td');
        td8.style.textAlign = "center";
        td8.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editCompetition" + (i+1);
        a1.className = "layui-btn layui-btn-normal layui-btn-xs";
        var i1 = document.createElement('i');
        i1.className = "layui-icon layui-icon-edit";
        a1.appendChild(i1);
        a1.innerHTML = a1.innerHTML + "编辑";
        a1.onclick = editCompetition;

        var a2 = document.createElement('a');
        a2.id = "delCompetition" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delCompetition;
        td8.appendChild(a1);
        td8.appendChild(a2);

        tr.appendChild(td8);
        tbody.appendChild(tr);
    }
}

function addCompetition() {
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['500px', '450px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">竞赛名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_name" autocomplete="off" placeholder="请输入竞赛名称" class="layui-input" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">作品名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_worksName" autocomplete="off" placeholder="请输入作品名称" class="layui-input" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">获奖等级</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_awardLevel" autocomplete="off" placeholder="请输入获奖等级" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">指导老师</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_instructor" autocomplete="off" placeholder="请输入指导老师" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">参赛队员</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_teamMember" autocomplete="off" placeholder="请输入参赛队员" class="layui-input" style="width: 300px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">获奖日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_date" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
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
            '            elem: \'#competition_date\' \n' +
            '        });\n' +
            '    });' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "添加竞赛",
        btn1 : function () {
            var name = $("#competition_name").val();
            var worksName = $("#competition_worksName").val();
            var date = $("#competition_date").val();
            var awardLevel = $("#competition_awardLevel").val();
            var instructor = $("#competition_instructor").val();
            var teamMember = $("#competition_teamMember").val();

            if(name == null || name == ""){
                layer.msg("竞赛名称不能为空！");
            }
            else if(worksName == null || worksName == ""){
                layer.msg("作品名称不能为空！");
            }
            else if(awardLevel == null || awardLevel == ""){
                layer.msg("获奖等级不能为空！");
            }
            else if(instructor == null || instructor == ""){
                layer.msg("指导老师不能为空！");
            }
            else if(teamMember == null || teamMember == ""){
                layer.msg("参赛学生不能为空！");
            }
            else if(date == null || date == ""){
                layer.msg("获奖日期不能为空！");
            }
            else {
                addcompetition(name, worksName, awardLevel, instructor, teamMember, date);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function addcompetition(name, worksName, awardLevel, instructor, teamMember, date) {
    var mydata = {
        "name" : name,
        "worksName" : worksName,
        "awardLevel" : awardLevel,
        "instructor" : instructor,
        "teamMember" : teamMember,
        "date" : date,
    };
    $.ajax({
        url : 'addCompetition.action',
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
                getAllCompetitions();
            }
            else
            {
                layer.msg(result.message);
            }
        },
        error : function () {
            layer.msg("添加失败！");
        }
    });
}

function editCompetition() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var competition = competitionList[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['500px', '450px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">竞赛名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_name" autocomplete="off" placeholder="请输入竞赛名称" disabled class="layui-input layui-disabled" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value="'+competition.name+'">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">作品名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_worksName" autocomplete="off" placeholder="请输入作品名称" disabled class="layui-input layui-disabled" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value="'+competition.worksName+'">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">获奖等级</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_awardLevel" autocomplete="off" placeholder="请输入获奖等级" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value="'+competition.awardLevel+'">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">指导老师</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_instructor" autocomplete="off" placeholder="请输入指导老师" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value="'+competition.instructor+'">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">参赛队员</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_teamMember" autocomplete="off" placeholder="请输入参赛队员" class="layui-input" style="width: 300px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value="'+competition.teamMember+'">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">获奖日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="competition_date" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value="'+(new Date(competition.date)).Format("yyyy-MM-dd")+'">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</form>\n' +
            '<script>' +
            'layui.use(\'laydate\', function(){\n' +
            '        var laydate = layui.laydate;\n' +
            '\n' +
            '        //执行一个laydate实例\n' +
            '        laydate.render({\n' +
            '            elem: \'#competition_date\' \n' +
            '        });\n' +
            '    });' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "更新竞赛信息",
        btn1 : function () {
            var name = $("#competition_name").val();
            var worksName = $("#competition_worksName").val();
            var date = $("#competition_date").val();
            var awardLevel = $("#competition_awardLevel").val();
            var instructor = $("#competition_instructor").val();
            var teamMember = $("#competition_teamMember").val();

            if(name == null || name == ""){
                layer.msg("竞赛名称不能为空！");
            }
            else if(worksName == null || worksName == ""){
                layer.msg("作品名称不能为空！");
            }
            else if(awardLevel == null || awardLevel == ""){
                layer.msg("获奖等级不能为空！");
            }
            else if(instructor == null || instructor == ""){
                layer.msg("指导老师不能为空！");
            }
            else if(teamMember == null || teamMember == ""){
                layer.msg("参赛学生不能为空！");
            }
            else if(date == null || date == ""){
                layer.msg("获奖日期不能为空！");
            }
            else {
                editcompetition(name, worksName, awardLevel, instructor, teamMember, date);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function editcompetition(name, worksName, awardLevel, instructor, teamMember, date) {
    var mydata = {
        "name" : name,
        "worksName" : worksName,
        "awardLevel" : awardLevel,
        "instructor" : instructor,
        "teamMember" : teamMember,
        "date" : date,
    };
    $.ajax({
        url : 'updateCompetition.action',
        type : 'post',
        data : mydata,
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
                getAllCompetitions();
            }
            else
            {
                layer.msg(result.message);
            }
        },
        error : function () {
            layer.msg("更新失败！");
        }
    });
}

function delCompetition() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var competition = competitionList[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除竞赛 <b>'"+competition.name+"'</b> 吗?" + '</div>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "信息安全实验室",
        yes : function () {
            delcompetition(competition);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delcompetition() {
    var competition = arguments[0];
    $.ajax({
        url: 'delCompetition.action',
        type: 'post',
        data: {
            "competitionId": competition.competitionId,
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
                getAllCompetitions();
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