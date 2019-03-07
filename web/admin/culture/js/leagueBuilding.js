var cultureList;
var culture;
var ue;
window.onload = function(){
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
    getCultures();
};

function getCultures() {
    $.ajax({
        url : 'getCultureByType.action',
        type : 'post',
        data : {"type" : 2},
        scriptType : "utf-8",
        success : function (result) {
            if(result.message == null){
                cultureList = result.cultureList;
                fillCulture(cultureList);
            }
        },
        error : function () {
            layer.msg('请求Action失败！');
        }
    });
}

function fillCulture() {
    var list = arguments[0];
    var ul = document.getElementById('culture_list');
    var title = document.getElementById('culture_title');
    var content = document.getElementById('culture_content');
    var date = document.getElementById('culture_date');
    ul.innerHTML = "";
    title.innerHTML = "";
    content.innerHTML = "";

    for(var i = 0; i < list.length; ++i){
        culture = list[i];
        var a = document.createElement('a');
        a.style.cursor = "pointer";
        a.id = "culture" + i;
        a.innerText = (i + 1) + "." + culture.title;
        a.onclick = changeCulture;
        var li = document.createElement('li');
        li.appendChild(a);
        ul.appendChild(li);
    }

    culture = list[0];
    title.innerText = culture.title;
    content.innerHTML = culture.content;
    date.innerHTML = new Date(culture.date).Format("yyyy-MM-dd");
}

function delCulture() {
    layer.open({
        type: 1,
        offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 100px;">' + "确定删除此文章吗？" + '</div>',
        btn: ['确定','取消'],
        btnAlign: 'c', //按钮居中
        shade: 0.5, //不显示遮罩
        title: "信息安全实验室",
        btn1 : function () {
            delculture();
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function delculture() {
    $.ajax({
        url : 'delCulture.action',
        type : 'post',
        data : {"cultureId" : culture.cultureId},
        scriptType : "utf-8",
        success : function (result) {
            if(result.message == null){
                getCultures();
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
                    },
                });
            }
            else{
                layer.msg(result.message);
            }
        },
        error : function () {
            layer.msg('请求Action失败！');
        }
    });
}

function changeCulture() {
    var target = window.event.target;
    var num = target.id.toString().charAt(target.id.toString().length - 1);
    var title = document.getElementById('culture_title');
    var content = document.getElementById('culture_content');

    title.innerHTML = "";
    content.innerHTML = "";
    culture = cultureList[num];
    title.innerText = culture.title;
    content.innerHTML = culture.content;
}

function updateCulture(){
    layer.open({
        type: 1,
        offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        id: 'layerDemo1', //防止重复弹出
        area: ['900px', '600px'],
        content: '<form class="layui-form" method="post" enctype="multipart/form-data" style="margin-top: 30px; text-align: left">' +
            '<div class="layui-form-item" style="margin-left: 20px">\n' +
            '     <label class="layui-form-label">标题</label>\n' +
            '     <div class="layui-input-block">\n' +
            '         <input type="text" name="title" id="cultureTitle" autocomplete="off" disabled placeholder="请输入标题" class="layui-input layui-disabled" style="width: 350px;" value="'+culture.title+'" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
            '     </div>\n' +
            '</div>' +
            '<div class="layui-form-item" style="padding-top: 20px;">\n' +
            '     <div style="width: 100%; text-align: center;">\n' +
            '          <script id="ueditor" type="text/plain" style="width: 90%; height: 400px; margin-left: 10%;">\n' +
            '          </script>\n' +
            '     </div>\n' +
            '</div>\n' +
            '</form>' +
            '<script>' +
            'layui.use(\'form\', function(){\n' +
            '            var form = layui.form;\n' +
            '            form.render();\n' +
            '            form.verify();\n' +
            '        });\n' +
            '        ue = ue!=null ? ue : UE.getEditor("ueditor",{toolbars: [[\n' +
            '                \'undo\', \'redo\', \'|\',\n' +
            '                \'bold\', \'italic\', \'underline\', \'fontborder\', \'strikethrough\', \'superscript\', \'subscript\', \'removeformat\', \'formatmatch\', \'autotypeset\', \'blockquote\', \'pasteplain\', \'|\', \'forecolor\', \'backcolor\', \'insertorderedlist\', \'insertunorderedlist\', \'selectall\', \'cleardoc\', \'|\',\n' +
            '                \'rowspacingtop\', \'rowspacingbottom\', \'lineheight\', \'|\',\n' +
            '                \'customstyle\', \'paragraph\', \'fontfamily\', \'fontsize\', \'|\',\n' +
            '                \'directionalityltr\', \'directionalityrtl\', \'indent\', \'|\',\n' +
            '                \'justifyleft\', \'justifycenter\', \'justifyright\', \'justifyjustify\', \'|\', \'touppercase\', \'tolowercase\', \'|\',\n' +
            '                \'link\', \'unlink\', \'anchor\', \'|\',\n' +
            '                \'simpleupload\', \'insertimage\', \'emotion\', \'scrawl\', \'insertvideo\', \'|\',\n' +
            '                \'horizontal\', \'date\', \'time\', \'spechars\', \'|\',\n' +
            '                \'inserttable\', \'deletetable\', \'insertparagraphbeforetable\', \'insertrow\', \'deleterow\', \'insertcol\', \'deletecol\', \'mergecells\', \'mergeright\', \'mergedown\', \'splittocells\', \'splittorows\', \'splittocols\', \'charts\', \'|\',\n' +
            '                \'searchreplace\'\n' +
            '            ]]});\n' +
            '</script>',
        btn: ['确定','取消'],
        btnAlign: 'c', //按钮居中
        shade: 0.5, //不显示遮罩
        title: "信息安全实验室",
        success : function(){
            ue.ready(function () {
                ue.setContent(culture.content);
            });
        },
        cancel: function(index, layero){
            console.log(ue);
            layer.close(index);
            return false;
        },
        btn1 : function () {
            updateculture();
            return false;
        },
        btn2 : function () {
            (ue.Editor).destroy();
            console.log(ue);
            layer.closeAll();
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