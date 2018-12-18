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
    console.log(researchs);
    var k = 0;
    //规定每个ul中li的最大数量
    var liMax = 3;
    //计算ul的数量
    var ullength = Math.ceil(researchs.length / liMax);
    //获取div3
    var div3 = document.getElementById('div3');

    for(var i=0; i<ullength && k < researchs.length; ++i) {
        //创建一个ul
        var ul = document.createElement('ul');
        for(var j=0; j<liMax && k < researchs.length; ++j) {
            //创建一个li
            var li = document.createElement('li');
            var a = document.createElement('a');
            a.href = "#";
            console.log(k);
            a.innerText = researchs[k++].researchDirection;
            //将li插入
            li.appendChild(a);
            ul.appendChild(li);
        }
        //将ul插入
        div3.appendChild(ul);
        //如果li过多，则不再创建li
        if(i >= 2) {
            break;
        }
    }
};
//最新动态
// function fillNews() {
//     //var
//     var lilength = 8;
// }







