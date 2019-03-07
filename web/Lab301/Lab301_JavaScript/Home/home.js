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
        url : 'index.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null) {
                fillResearchs(result.researchs);
            }
        },
        error : function () {
            alert("请求失败！");
        }
    });
}
//研究方向 gyj
/*function fillResearchs() {
    var researchs = arguments[0];
    //获取列表对象，清空列表
    var ul = document.getElementById('reUl');
    ul.innerHTML = '';
    //循环创建li
    for(var i=0; i<researchs.length; ++i) {
        var li = document.createElement('li');
        var a = document.createElement('a');
        
        a.href = "#";
        a.innerText = researchs[i].researchDirection;
        li.appendChild(a);
        ul.appendChild(li);
        //如果li过多，则不再创建li
        if(i == 4) {
            break;
        }
    }
}*/
//研究方向
function fillResearchs() {
    var researchs = arguments[0];
    //当前第几个li
    var k = 0;
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
            a.innerText = researchs[k++].researchDirection;
            
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







