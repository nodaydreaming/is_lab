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
    // body.innerHTML = '';
    var table = document.createElement('table');
    table.className = 'v_table';
    body.appendChild(table);
    var tbody = document.createElement('tbody');
    table.appendChild(tbody);
    console.log(1);
    for(var i=0; i<researchs.length; ++i) {
        var res = researchs[i];
console.log(2);
        var tr = document.createElement('tr');
        tbody.appendChild(tr);
        var th = document.createElement('th');
        tr.appendChild(th);
        var h2 = document.createElement('h2');
        h2.innerHTML = res.researchDirection;
        th.appendChild(h2);
        var td = document.createElement('td');
        td.innerHTML = res.introduction;
        tr.appendChild(td);
    }
};
















