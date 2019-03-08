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
                console.log(result.cultureImages);
                console.log(result.researchs);
                console.log(result.works);
                console.log(result.cultures);
                //type: 1 ：竞赛；2 ：论文；3 ：专利；4 : 软著
                console.log(result.results);
                fillResults(result.results);

                fillResearchs(result.researchs);
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
    var ullength = Math.ceil(researchs.length / 2);
    var div3 = document.getElementById('div3');
    //创建ullength列ul
    for(var i=0; i<ullength; ++i) {
        var ul = document.createElement('ul');
        //创建liMax行li
        for(var j=0; j<liMax; ++j) {
            var li = document.createElement('li');
            var a = document.createElement('a');
            a.href = "Lab301_HTML/Introduce/ResearchDirection.html";
            a.innerText = researchs[++k].researchDirection;
            
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
function  fillResults() {
    console.log(10);
    var results = arguments[0];
    var lilength = results.length;
    var ul = document.getElementsByClassName('divs')[0].getElementsByTagName('ul')[0];
    for(var i=0; i<lilength; ++i) {
        var li = document.createElement('li');
        ul.appendChild(li);
        var time = document.createElement('time');
        time.innerHTML = new Date(results.date).Format("yyyy-MM-dd");
        li.appendChild(time);
        var a = document.createElement('a');
        if(results.type == 1) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type1";
        } else if(results.type == 2) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type2";
        } else if(results.type == 3) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type3";
        } else if(results.type == 4) {
            a.href = "Lab301/Lab301_HTML/Achievement/Results.html#type4";
        }
        a.title = results.name;
        li.appendChild(a);
    }
};






