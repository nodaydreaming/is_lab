$.ajax({
    url: 'index.action',
    type: 'post',
    dataType: 'json',
    success: function (result) {
        console.log(result[0]);

        console.log(eval(result));
        console.log(result[0].abc);


    },
    error:function (result) {
        console.log("请求失败！");
    }
});