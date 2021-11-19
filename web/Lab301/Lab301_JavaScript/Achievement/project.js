window.onload = function () {
    $.ajax({
        url : 'getAllProjects.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //项目的数组
                fillProhects(result.projects);
            }
        },
        error : function () {
            alert("请求失败！");
        }
    });
}

function fillProhects() {
    var projects = arguments[0];
    
    var body = document.getElementsByClassName('body')[0];
    var table = document.getElementsByClassName('v_table')[0];
    
    for(var i=0; i<projects.length; ++i) {
        var pro = projects[i];
        
        var tr = document.createElement('tr');
        if(i % 2 == 1) {
            tr.className = 'tr1';
        } else {
            tr.className = 'tr2';
        }
        table.appendChild(tr);
        
        var td1 = document.createElement('td');
        td1.innerHTML = pro.name;
        tr.appendChild(td1);
        
        var td2 = document.createElement('td');
        td2.innerHTML = pro.number;
        tr.appendChild(td2);
        
        var td3 = document.createElement('td');
        td3.innerHTML = new Date(pro.startdate).Format("yyyy-MM-dd") + "-" + new Date(pro.enddate).Format("yyyy-MM-dd");
        tr.appendChild(td3);
        
        var td4 = document.createElement('td');
        td4.innerHTML = pro.principal;
        tr.appendChild(td4);
        
        var td5 = document.createElement('td');
        td5.innerHTML = pro.type;
        tr.appendChild(td5);
    }
}

//定义起止时间字符格式
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
























