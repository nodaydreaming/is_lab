/**
 * 主页js文件
 * ajax返回主页展示需要的数据result
 * 作品数组  result.works
 * 成果数组  result.results
 * 研究方向数组  result.researchs
 * 链接数组  result.links
 * 实验室文化数组  result.cultures
 * 实验室文化上方图片地址数组  result.culture_photos
 */
window.onload = function () {
    $.ajax({
        url : 'indexInfo.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null) {
                // console.log(result.cultureImages);
                // console.log(result.researchs);
                // console.log(result.works);
                // console.log(result.cultures);
                // //type: 1 ：竞赛；2 ：论文；3 ：专利；4 : 软著
                // console.log(result.results);

                fillResults(result.results);
                fillResearchs(result.researchs);
                fillCultureImages(result.cultureImages);
                fillCultures(result.cultures);
                fillWorks(result.works);
            }
        },
        error : function () {
            alert("请求失败！");
        }
    });
}
//研究方向
function fillResearchs() {
    var researchs = arguments[0];
    //当前第几个li
    var k = -1;
    //规定每个ul中li的最大数量
    var liMax = 3;
    //计算ul的数量
    var lilength = researchs.length;
    var ullength = Math.ceil(lilength / 2);
    if(ullength > 2) {
        ullength = 2;
    }
    var div3 = document.getElementById('div3');
    //创建ullength列ul
    for(var i=0; i<ullength; ++i) {
        var ul = document.createElement('ul');
        //创建liMax行li
        for(var j=0; j<liMax; ++j) {
            var li = document.createElement('li');
            var a = document.createElement('a');
            a.href = "Lab301/Lab301_HTML/Introduce/ResearchDirection.html";
            if(k >= lilength-1) {
                a.innerHTML = '';
                li.style.listStyle = 'none';
            } else {
                a.innerText = researchs[++k].researchDirection;
            }
            li.appendChild(a);
            ul.appendChild(li);
        }
        div3.appendChild(ul);
        //如果ul达到2列，则不再创建ul
        if(i >= 2) {
            break;
        }
    }
};
//最新动态
function fillResults() {
    var results = arguments[0];
    var lilength = 8;
    var ul = document.getElementsByClassName('divs')[0].getElementsByTagName('ul')[0];
    for(var i=0; i<lilength; ++i) {
        var li = document.createElement('li');
        ul.appendChild(li);
        var time = document.createElement('time');
        time.innerHTML = "【" + new Date(results[i].date).Format("yyyy-MM-dd") + "】" ;
        li.appendChild(time);
        var a = document.createElement('a');
        if(results[i].type == 1) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type1";
        } else if(results[i].type == 2) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type2";
        } else if(results[i].type == 3) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type3";
        } else if(results[i].type == 4) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type4";
        }
        a.title = results[i].name;
        li.appendChild(a);
        span = document.createElement('span');
        span.innerHTML = cutString(results[i].name, 30);
        a.appendChild(span);
    }
};
//实验室文化
function fillCultureImages() {
    var cultureImages = arguments[0];
    var lilength = cultureImages.length;
    var ul = document.getElementById('secInside').getElementsByTagName('ul')[0];
    ul.innerHTML = "";
    for(var i=lilength-1; i>=0; i--) {
        var li = document.createElement('li');
        var a = document.createElement('a');
        var img = document.createElement('img');
        img.src = cultureImages[i];
        a.appendChild(img);
        li.appendChild(a);
        ul.appendChild(li);
    }
}
function fillCultures() {
    var cultures = arguments[0];
    var lilength = 3;
    var ul = document.getElementById('cultureFoot');
    for(var i=0; i<lilength; ++i) {
        var li = document.createElement('li');
        ul.appendChild(li);
        var time = document.createElement('time');
        time.innerHTML = "【" + new Date(cultures[i].date).Format("yyyy-MM-dd") + "】";
        li.appendChild(time);
        var a = document.createElement('a');
        if(cultures[i].type == 1) {
            a.href = "Lab301/Lab301_HTML/Culture/Lifesketch.html";
        } else if(cultures[i].type == 2) {
            a.href = "Lab301/Lab301_HTML/Culture/Teamactivity.html"
        } else if(cultures[i].type == 3) {
            a.href = "Lab301_HTML/Culture/Thegraduation.html";
        }
        a.title = cultures[i].title;
        li.appendChild(a);
        span = document.createElement('span');
        span.innerHTML = cultures[i].title;
        a.appendChild(span);
    }
}
//lbt
function fillWorks() {
    var works = arguments[0];
    var ul = document.getElementsByClassName('inner')[0].getElementsByTagName('ul')[0];
    var lilength = works.length;
    for(var i=0; i<lilength; ++i) {
        var li = document.createElement('li');
        ul.appendChild(li);
        var a = document.createElement('a');
        a.href = "Lab301/Lab301_HTML/Achievement/Works.html";
        li.appendChild(a);
        img = document.createElement('img');
        img.src = works[i].photo;
        a.appendChild(img);
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

function cutString(str, len) {
    if(str.length*2 <= len) {
        return str;
    }
    var strlen = 0;
    var s = "";
    for(var i = 0;i < str.length; i++) {
        s = s + str.charAt(i);
        if (str.charCodeAt(i) > 128) {
            strlen = strlen + 2;
            if(strlen >= len){
                return s.substring(0,s.length-1) + "...";
            }
        } else {
            strlen = strlen + 1;
            if(strlen >= len){
                return s.substring(0,s.length-2) + "...";
            }
        }
    }
    return s;
}
var iu = cutString("sflasj", 5);








