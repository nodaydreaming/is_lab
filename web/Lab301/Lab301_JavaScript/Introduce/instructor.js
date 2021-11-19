window.onload = function () {
    $.ajax({
       url : 'getAllInstructors.action',
       type : 'post',
       scriptCharset : 'utf-8',
       success : function (result) {
           if(result.message == null) {
               //指导老师的数组
               fillInstructors(result.instructors);
           }
       },
       error : function () {
           alert("请求失败！");
       }
    });
}

function fillInstructors() {
    var instructors = arguments[0];
    
    var body = document.getElementsByClassName('body')[0];
    for(var i=0; i<instructors.length; ++i) {
        var instr = instructors[i];
        
        var div = document.createElement('div');
        div.className = 'div1';
        body.appendChild(div);
        var img = document.createElement('img');
        //插入图片地址
        img.src = instr.photo;
        img.alt = "This picture is not visible!";
        div.appendChild(img);
        var p = document.createElement('p');
        p.innerHTML = instr.intro;
        div.appendChild(p);
    }
}
















