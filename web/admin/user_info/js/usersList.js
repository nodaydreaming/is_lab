var usersList;

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
    getUsers();
};

function getUsers() {
    $.ajax({
        url : 'user_getAll.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
           if(result.message == null){
               fillUsers(result.users);
           }
           else{
               layer.msg(result.message);
           }
        },
        error : function () {
            layer.msg("请求失败！");
        }
    });
}

function fillUsers() {
    usersList = arguments[0];
    var tbody = document.getElementsByTagName('tbody')[0];
    $('tbody').html("");

    for(var i = 0; i < usersList.length; ++i){
        var user0 = usersList[i];
        var tr = document.createElement('tr');

        var td1 = document.createElement('td');
        td1.style.textAlign = "center";
        td1.innerText = i + 1;
        tr.appendChild(td1);

        var td2 = document.createElement('td');
        td2.style.textAlign = "center";
        td2.innerText = user0.username;
        td2.style.wordWrap = "break-word";
        tr.appendChild(td2);

        var td3 = document.createElement('td');
        td3.style.textAlign = "center";
        td3.innerText = user0.gender;
        td3.style.wordWrap = "break-word";
        tr.appendChild(td3);

        var td4 = document.createElement('td');
        td4.style.textAlign = "center";
        td4.innerText = user0.nickname;
        td4.style.wordWrap = "break-word";
        tr.appendChild(td4);

        var td5 = document.createElement('td');
        td5.style.textAlign = "center";
        td5.innerText = user0.email;
        td5.style.wordWrap = "break-word";
        tr.appendChild(td5);

        var td6 = document.createElement('td');
        td6.style.textAlign = "center";
        td6.innerText = user0.telephone;
        td6.style.wordWrap = "break-word";
        tr.appendChild(td6);

        var td7 = document.createElement('td');
        var a2 = document.createElement('a');
        a2.id = "delUser" + (i+1);
        a2.className = "layui-btn layui-btn-danger layui-btn-xs";
        var i2 = document.createElement('i');
        i2.className = "layui-icon layui-icon-delete";
        a2.appendChild(i2);
        a2.innerHTML = a2.innerHTML + "删除";
        a2.onclick = delUser;
        td7.style.textAlign = "center";
        td7.appendChild(a2);
        tr.appendChild(td7);

        tbody.appendChild(tr);
    }
}

function delUser() {
    var tr = this.parentNode.parentNode;
    var num = tr.childNodes[0].innerText;
    var user0 = usersList[num-1];
    layer.open({
        type: 1,
        offset: 'auto', //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        id: 'layerDemo1', //防止重复弹出
        content: '<div style="padding: 20px 50px;">' + "确定删除管理员 <b>'" + user0.username + "'</b> 吗？" + '</div>',
        btn: ['确定', '取消'],
        btnAlign: 'c', //按钮居中
        shade: 0.5, //不显示遮罩
        title: "删除管理员",
        btn1 : function () {
            deluser(user0);
            return false;
        },
        btn2 : function () {
            layer.closeAll();
        }
    });
}

function deluser() {
    var id = arguments[0].userId;
    $.ajax({
        url : 'user_delete.action',
        type : 'post',
        data : {"userId" : id},
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
                getUsers();
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