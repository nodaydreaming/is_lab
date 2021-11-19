window.onload = function() {
    $.ajax({
        url : 'getCultureByType.action',
        type : 'post',
        data : {"type" : 1},
        scriptType : "utf-8",
        success : function (result) {
            if(result.message == null){
                //result.cultureList;
                fillCultureList(result.cultureList);
            }
        },
        error : function () {
            layer.msg('请求Action失败！');
        }
    });
}

function fillCultureList() {
    var cultureList = arguments[0];
    var ul = document.getElementsByClassName('nav')[0];
    var right = document.getElementById('right');

    var lilength = cultureList.length;
    for(var i=0; i<lilength; ++i) {
        var a = document.createElement('a');
        a.href = '#' + cultureList[i].cultureId;
        ul.appendChild(a);
        var li = document.createElement('li');
        li.innerHTML = cultureList[i].title;
        a.appendChild(li);

        var div = document.createElement('div');
        div.className = 'party';
        div.id = cultureList[i].cultureId;
        right.appendChild(div);
        var h2 = document.createElement('h2');
        div.appendChild(h2);
        var span = document.createElement('span');
        span.innerHTML = cultureList[i].title;
        h2.appendChild(span);
        var divs = document.createElement('div');
        divs.innerHTML = cultureList[i].content;
        div.appendChild(divs);
    }
}