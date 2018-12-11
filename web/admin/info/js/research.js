var list;
window.onload = function () {
    $.ajax({
        url : 'getAllResearch.action',
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
};

function fill() {
    list = arguments[0];
    // console.log(list);
    var tbody = document.getElementsByTagName('tbody')[0];
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
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.innerText = research1.introduction;
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.style.padding = "0px";
        var a1 = document.createElement('a');
        a1.id = "editResearch" + (i+1);
        a1.className = "layui-btn layui-btn-xs";
        a1.innerText = "编辑";
        var a2 = document.createElement('a');
        a2.id = "delResearch" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        a2.innerText = "删除";
        td4.appendChild(a1);
        td4.appendChild(a2);

        tr.appendChild(td4);
        tbody.appendChild(tr);
    }
}

function addResearch(){
    var index = layer.open({
        type: 1,
        offset: 'auto',
        skin : 'layui-layer-lan',
        // id: 'layerDemo1', //防止重复弹出
        area :  ['600px', '400px'],
        content: '<form class="layui-form" action="">\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">研究方向</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入研究方向" class="layui-input" style="width: 100px">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">简介</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" name="intro" lay-verify="title" autocomplete="off" placeholder="请输入研究方向" class="layui-input" style="width: 100px">\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</form>',
        btn: ['确定','取消'],
        btnAlign: 'c',
        shade: 0.5,
        title: "信息安全实验室—添加研究方向",
        btn1 : function () {
            layer.msg("不能为空！");
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}