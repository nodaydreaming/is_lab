window.onload = function () {
    $.ajax({
        url : 'getResults.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //result.papers  论文 type2
                //result.patents 专利 type3
                //result.softwares 软著 type4
                //result.competitions 竞赛 type1

                fillCompetitions(result.competitions);
                fillPapers(result.papers);
                fillPatents(result.patents);
                fillSoftwares(result.softwares);
            }
        },
        error : function () {
            alert("请求失败！");
        }
    });
}

function fillCompetitions() {
    var competitions = arguments[0];
    var table = document.getElementsByClassName('v_table2')[0];
    var trlength = competitions.length;
    for(var i=0; i<trlength; ++i) {
        var tr = document.createElement('tr');
        if(i%2 == 1) {
            tr.className = 'tr2';
        } else {
            tr.className = 'tr3';
        }
        table.appendChild(tr);
        for(var j=1; j<=6; ++j) {
            var td = document.createElement('td');
            td.className = 't' + j;
            switch (j) {
                case 1: td.innerHTML = competitions[i].name;break;
                case 2: td.innerHTML = competitions[i].worksName;break;
                case 3: td.innerHTML = competitions[i].awardLevel;break;
                case 4: td.innerHTML = competitions[i].instructor;break;
                case 5: td.innerHTML = competitions[i].teamMember;break;
                case 6: td.innerHTML = new Date(competitions[i].date).Format("yyyy-MM-dd");break;
            }
            tr.appendChild(td);
        }
    }
}

function fillPapers() {
    var papers = arguments[0];
    var div12 = document.getElementsByClassName('div12')[0];
    var plength = papers.length;
    for(var i=0; i<plength; ++i) {
        p = document.createElement('p');
        p.innerHTML = (i+1) + "." + " " + " " + + " " + papers[i].name;
        div12.appendChild(p);
        a = document.createElement('a');
        a.innerHTML = '下载';
        var filename = papers[i].address.toString().substring(papers[i].address.lastIndexOf('/') + 21);
        a.href = "downFile.action?filename=" + filename + "&address=" + papers[i].address.substring(7);
        p.appendChild(a);
    }
}

function fillPatents() {
    var patents = arguments[0];
    var table = document.getElementsByClassName('v_table1')[0];
    var trlength = patents.length;
    for(var i=trlength-1; i>=0; i--) {
        var tr = document.createElement('tr');
        if(i%2 == 1) {
            tr.className = 'tr3';
        } else {
            tr.className = 'tr2';
        }
        table.appendChild(tr);
        for(var j=1; j<=5; ++j) {
            var td = document.createElement('td');
            switch (j) {
                case 1:
                    td.className = 't1';
                    td.innerHTML = patents[i].patentId;
                    break;
                case 2:
                    td.className = 't2';
                    td.innerHTML = patents[i].name;
                    break;
                case 3: td.innerHTML = patents[i].number;
                case 4: td.innerHTML = patents[i].principal;
                case 5: td.innerHTML = new Date(patents[i].date).Format("yyyy-MM-dd");
            }
            tr.appendChild(td);
        }
    }
}

function fillSoftwares() {
    var softwares = arguments[0];
    var div32 = document.getElementsByClassName('div32')[0];
    var plength = softwares.length;
    for(var i=0; i<plength; ++i) {
        p = document.createElement('p');
        p.innerHTML = (i+1) + "." + " " + " " + + " " + softwares[i].name;
        div32.appendChild(p);
        a = document.createElement('a');
        a.innerHTML = '下载';
        var filename = softwares[i].address.toString().substring(softwares[i].address.lastIndexOf('/') + 21);
        a.href = "downFile.action?filename=" + filename + "&address=" + softwares[i].address.substring(7);
        p.appendChild(a);
    }
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
