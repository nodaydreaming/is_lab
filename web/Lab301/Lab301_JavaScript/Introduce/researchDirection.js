window.onload = function () {
    $.ajax({
        url : 'getAllResearchs.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //研究方向数组
                fillResearchs(result.researchs);
                console.log(result.researchs);
            }
        },
        error : function () {
           alert("请求失败！");
        }
    });
}

function fillResearchs() {
    var researchs = arguments[0];
    
    var body = document.getElementsByClassName('body')[0];
    var table = document.createElement('table');
    body.appendChild(table);
    for(var i=0; i<researchs.length; ++i) {
        var res = researchs[i];
        
        var tr = document.createElement('tr');
        table.appendChild(tr);
        var th = document.createElement('th');
        tr.appendChild(th);
        var h2 = document.createElement('h2');
        h2.innerHTML = res.researchDirection;
        th.appendChild(h2);
        var td = document.createElement('td');
        tr.appendChild(td);
        var p = document.createElement('p');
        p.innerHTML = res.introduction;
        td.appendChild(p);
    }
};
















