var list;
window.onload = function () {
    getAllResearchs();
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

function getAllResearchs() {
    $.ajax({
        url : 'getAllResearchs.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            fill(result.researchs);
        },
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

function fill() {
    list = arguments[0];
    // console.log(list);
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");
    // console.log(tbody);
    for (var i = 0; i < list.length; ++i){
        var research1 = list[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.style.textAlign = "center";
        td2.innerText = research1.researchDirection;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.innerText = research1.introduction;
        td3.style.wordWrap = "break-word";
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editResearch" + (i+1);
        a1.className = "layui-btn layui-btn-normal layui-btn-xs";
        var i1 = document.createElement('i');
        i1.className = "layui-icon layui-icon-edit";
        a1.appendChild(i1);
        a1.innerHTML = a1.innerHTML + "编辑";
        a1.onclick = editResearch;

        var a2 = document.createElement('a');
        a2.id = "delResearch" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delResearch;

        td4.appendChild(a1);
        td4.appendChild(a2);

        tr.appendChild(td4);
        tbody.appendChild(tr);
    }
}

function editResearch() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var research = list[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['600px', '400px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">研究方向</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="research_title" lay-verify="title" autocomplete="off" placeholder="请输入研究方向" disabled class="layui-input layui-disabled" style="width: 250px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" value='+research.researchDirection+'>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">简&nbsp;&nbsp;&nbsp;介</label>\n' +
            '    <div class="layui-input-block">\n' +
            '       <textarea class="tcp_content layui-textarea" placeholder="请输入简介" style="width: 80%; height: 50%; resize:none" maxlength="200" onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()">'+research.introduction+'</textarea>' +
            '       <span class="t_h" style="float: right; margin-right: 20%"><i>'+research.introduction.length+'</i>/200</span>' +
            '    </div>\n' +
            '  </div>\n' +
            '</form>\n' +
            '<script>\n' +
            'layui.use(\'form\', function(){\n' +
            '  var form = layui.form;\n' +
            '  form.render();\n' +
            '});' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "编辑研究方向",
        btn1 : function () {
            var intro = $(".tcp_content").val();
            var title = $("#research_title").val();
            if(title == null || title === ""){
                layer.msg("研究方向不能为空！");
            }
            else if(intro == null || intro === ""){
                layer.msg("简介不能为空！");
            }
            else {
                research.researchDirection = title;
                research.introduction = intro;
                editresearch(research);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function editresearch(research) {
    var mydata = {
        "researchId" : research.researchId,
        "researchDirection" : research.researchDirection,
        "introduction" : research.introduction,
        "priority" : research.priority
    }
    $.ajax({
        url : 'updateResearch.action',
        type : 'post',
        scriptCharset : 'utf-8',
        data : mydata,
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
                getAllResearchs();
            }
            else
            {
                layer.msg(result.message);
            }
        }
    });
}

function delResearch() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var research = list[num-1];
    layer.open({
        type: 1,
        offset: 'auto',
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除研究方向  <b>'"+ research.researchDirection + "'</b> 吗？" + '</div>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "信息安全实验室",
        yes : function () {
            delresearch(research);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delresearch(research) {
    var mydata = {
        "researchId" : research.researchId,
        "researchDirection" : research.researchDirection,
        "introduction" : research.introduction,
        "priority" : research.priority
    };
    $.ajax({
        url : 'delResearch.action',
        type : 'post',
        scriptCharset : 'utf-8',
        data : mydata,
        success : function (result) {
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
                getAllResearchs();
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
        }
    });
}

function addResearch(){
    layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        id: 'layerDemo1', //防止重复弹出
        area :  ['600px', '400px'],
        content: '<form class="layui-form" action="" style="margin-top: 30px">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">研究方向</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="research_title" lay-verify="title" autocomplete="off" placeholder="请输入研究方向" class="layui-input" style="width: 250px" onkeypress="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">简&nbsp;&nbsp;&nbsp;介</label>\n' +
            '    <div class="layui-input-block">\n' +
            '       <textarea class="tcp_content layui-textarea" placeholder="请输入简介" style="width: 80%; height: 50%; resize:none" maxlength="200" onchange="textarea_fun()" onkeydown="textarea_fun()" onkeyup="textarea_fun()"></textarea>' +
            '       <span class="t_h" style="float: right; margin-right: 20%"><i>0</i>/200</span>' +
            '    </div>\n' +
            '  </div>\n' +
            '</form>\n' +
            '<script>\n' +
            'layui.use(\'form\', function(){\n' +
            '  var form = layui.form;\n' +
            '  form.render();\n' +
            '});' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "添加研究方向",
        btn1 : function () {
            var intro = $(".tcp_content").val();
            var title = $("#research_title").val();
            if(title == null || title === ""){
                layer.msg("研究方向不能为空！");
            }
            else if(intro == null || intro === ""){
                layer.msg("简介不能为空！");
            }
            else {
                addresearch(title, intro);
                return false;
            }
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function addresearch(title, intro) {
    $.ajax({
       url : 'addResearch.action',
       type : 'post',
       data : {"researchDirection" : title, "introduction" : intro},
       scriptCharset : 'utf-8',
       success : function (result) {
           if(result.message == null) {
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
               getAllResearchs();
           }else{
               layer.msg(result.message);
           }
       } ,
        error : function () {
            layer.open({
                type: 1,
                offset: 'auto',
                id: 'layerDemo2', //防止重复弹出
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

function textarea_fun(){
    $(".tcp_content").val($(".tcp_content").val().substring(0,200));
    $(".t_h i").html($(".tcp_content").val().length);
    if(window.event.keyCode  == 13){
        return false;
    }
}
