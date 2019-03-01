var patentList;

window.onload = function () {
    getAllPatents();
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

function getAllPatents() {
    $.ajax({
        url : 'getPatents.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message != null){
                layer.msg(result.message);
            }
            else if(result.patents != null)
            {
                fillPatents(result.patents);
            }
        } ,
        error : function () {
            layer.msg("请求失败！");
        }
    });
}

function fillPatents() {
    patentList = arguments[0];
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");

    for (var i = 0; i < patentList.length; ++i){
        var patent = patentList[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.style.textAlign = "center";
        td2.innerText = patent.name;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.style.textAlign = "center";
        td3.innerText = patent.number;
        td3.style.wordWrap = "break-word";
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.innerText = patent.principal;
        td4.style.wordWrap = "break-word";
        tr.appendChild(td4);

        var td5 = document.createElement('td');
        td5.style.textAlign = "center";
        td5.innerText = new Date(patent.date).Format("yyyy-MM-dd");
        td5.style.wordWrap = "break-word";
        tr.appendChild(td5);

        var td7 = document.createElement('td');
        td7.style.textAlign = "center";
        td7.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editPatent" + (i+1);
        a1.className = "layui-btn layui-btn-normal layui-btn-xs";
        var i1 = document.createElement('i');
        i1.className = "layui-icon layui-icon-edit";
        a1.appendChild(i1);
        a1.innerHTML = a1.innerHTML + "编辑";
        a1.onclick = editPatent;

        var a2 = document.createElement('a');
        a2.id = "delPatent" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delPatent;
        td7.appendChild(a1);
        td7.appendChild(a2);

        tr.appendChild(td7);
        tbody.appendChild(tr);
    }
}

function addPatent() {
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['600px', '400px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">专利名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_name" autocomplete="off" placeholder="请输入专利名称" class="layui-input" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">专利号</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_number" autocomplete="off" placeholder="请输入专利号" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">第一发明人</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_principal" autocomplete="off" placeholder="请输入第一发明人" class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">授权日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_date" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
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
            '            elem: \'#patent_date\' \n' +
            '        });\n' +
            '    });' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "添加专利",
        btn1 : function () {
            var name = $("#patent_name").val();
            var number = $("#patent_number").val();
            var date = $("#patent_date").val();
            var principal = $("#patent_principal").val();

            if(name == null || name === ""){
                layer.msg("专利名称不能为空！");
            }
            else if(number == null || number === ""){
                layer.msg("专利编号不能为空！");
            }
            else if(date == null || date === ""){
                layer.msg("授权日期不能为空！");
            }
            else if(principal == null || principal === ""){
                layer.msg("负责人不能为空！");
            }
            else {
                addpatent(name, number, date, principal);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function addpatent(name, number, date, principal) {
    var mydata = {
        "name" : name,
        "number" : number,
        "date" : date,
        "principal" : principal,
    };
    $.ajax({
        url : 'addPatent.action',
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
                getAllPatents();
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

function editPatent() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var patent = patentList[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['600px', '400px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">专利名称</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_name" autocomplete="off" placeholder="请输入专利名称" class="layui-input" style="width: 350px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+patent.name+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">专利号</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_number" autocomplete="off" placeholder="请输入专利号" disabled class="layui-input layui-disabled" style="width: 250px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+patent.number+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">第一发明人</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_principal" autocomplete="off" placeholder="请输入第一发明人" class="layui-input" style="width: 150px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+patent.principal+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">授权日期</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="patent_date" autocomplete="off" placeholder="yyyy-MM-dd"class="layui-input" style="width: 200px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+new Date(patent.date).Format("yyyy-MM-dd")+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</form>' +
            '<script>' +
            'layui.use(\'laydate\', function(){\n' +
            '        var laydate = layui.laydate;\n' +
            '\n' +
            '        //执行一个laydate实例\n' +
            '        laydate.render({\n' +
            '            elem: \'#patent_date\' \n' +
            '        });\n' +
            '    });' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "更新专利信息",
        btn1 : function () {
            var name = $("#patent_name").val();
            var number = $("#patent_number").val();
            var date = $("#patent_date").val();
            var principal = $("#patent_principal").val();

            if(name == null || name === ""){
                layer.msg("专利名称不能为空！");
            }
            else if(number == null || number === ""){
                layer.msg("专利号不能为空！");
            }
            else if(date == null || date === ""){
                layer.msg("授权日期不能为空！");
            }
            else if(principal == null || principal === ""){
                layer.msg("第一发明人不能为空！");
            }
            else {
                patent.name = name;
                patent.number = number;
                patent.date = date;
                patent.principal = principal;
                editpatent(patent);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function editpatent() {
    var patent = arguments[0];
    $.ajax({
        url : 'updatePatent.action',
        type : 'post',
        data : {"name" : patent.name, "number" : patent.number, "date" : patent.date, "principal" : patent.principal},
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
                getAllPatents();
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

function delPatent() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var patent = patentList[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除专利 <b>'"+patent.name+"'</b> 吗?" + '</div>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "信息安全实验室",
        yes : function () {
            delpatent(patent);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delpatent() {
    var patent = arguments[0];
    $.ajax({
        url: 'delPatent.action',
        type: 'post',
        data: {
            "patentId": patent.patentId,
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
                getAllPatents();
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