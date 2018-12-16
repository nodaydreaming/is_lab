/**
 * 主页js文件
 * ajax返回主页展示需要的数据result
 * 作品数组  result.works
 * 成果数组  result.results
 * 研究方向数组  result.researchs
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


function fillResearchs() {
    var researchs = arguments[0];

    var ul = document.getElementById('res');
    ul.innerHTML = '';
    console.log(ul);
    for(var i=0;i<researchs.length;++i){
        var li = document.createElement('li');
        var a = document.createElement('a');
        a.href = "#";
        a.innerText = researchs[i].researchDirection;
        li.appendChild(a);
        ul.appendChild(li);
        if(i == 2){
            break;
        }
    }

}