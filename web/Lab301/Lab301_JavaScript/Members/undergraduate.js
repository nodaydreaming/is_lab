window.onload = function() {
    $.ajax({
        url : 'getStudents.action',
        type : 'post',
        data : {"type" : 1},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                fillStudents(result.students)
            }
        },
        error : function () {
            layer.msg("请求失败！");
        }
    });
}

var body = document.getElementsByClassName('body')[0];

function fillStudents() {
    var students = arguments[0];
    //每行照片张数
    var lineNumber = 4;
    //照片行数
    var line = Math.ceil(students.length/lineNumber);
    //第几张照片（从0开始）
    var number = -1;
    
    for(var i=0; i<line; ++i) {
        var row = document.createElement('div');
        row.className = 'row';
        body.appendChild(row);
        
        for(var j=0; j<lineNumber; ++j) {
            number ++;
            
            var flip = document.createElement('div');
            flip.className = 'flip-container';
            row.appendChild(flip);
            var flipper = document.createElement('div');
            flipper.className = 'flipper';
            flip.appendChild(flipper);
            //正面
            var front = document.createElement('div');
            front.className = 'front';
            flipper.appendChild(front);
            //背面
            var back = document.createElement('div');
            back.className = 'back';
            flipper.appendChild(back);
            //创建看不见的样式区域，目的是占用空间以达到图片从左到右排列的样式效果
            if(number >= students.length) {
                continue;
            }
            
            var stu = students[number];
            //正面
            var img = document.createElement('img');
            img.src = stu.photo;
            img.alt = stu.name;
            front.appendChild(img);
            //背面
            var name = document.createElement('h4');
            name.innerHTML = "姓名：" + stu.name;
            back.appendChild(name);
            var gender = document.createElement('p');
            gender.innerHTML = "性别：" + stu.gender;
            back.appendChild(gender);
            var grade = document.createElement('p');
            grade.innerHTML = "年级：" + stu.grade;
            back.appendChild(grade);
            var research = document.createElement('p');
            research.innerHTML = "研究方向：" + stu.research;
            back.appendChild(research);
        }
    }
}



























