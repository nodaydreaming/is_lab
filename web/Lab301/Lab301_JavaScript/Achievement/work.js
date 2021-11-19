window.onload = function () {
    $.ajax({
        url : 'getWorks.action',
        type : 'post',
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                //作品的数组
                fillWorks(result.works);
            }
        },
        error : function () {
           alert("请求失败！");
        }
    });
}

function fillWorks() {
    var work = arguments[0];
    console.log(work);
    var body = document.getElementsByClassName('body')[0];
    var table = document.createElement('table');
    body.appendChild(table);
    
    for(var i=0; i<work.length; ++i) {
        var wor = work[i];
        
        var tr = document.createElement('tr');
        table.appendChild(tr);
        
        var tdone = document.createElement('td');
        tdone.className = 'td1';
        tr.appendChild(tdone);
        var img = document.createElement('img');
        img.src = wor.photo;
        img.alt = "This picture is not visible!";
        tdone.appendChild(img);
        
        var tdtwo = document.createElement('td');
        tdtwo.className = 'td2';
        tr.appendChild(tdtwo);
        var h3 = document.createElement('h3');
        h3.innerHTML = wor.name;
        tdtwo.appendChild(h3);
        var p = document.createElement('p');
        p.innerHTML = wor.intro;
        tdtwo.appendChild(p);
    }
}
























