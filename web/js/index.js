console.log($("button").text());

$.ajax({
    url: 'index.action',
    type: 'post',
    dataType: 'json',
    async: false,
    success: function (jsonArray) {
        console.log(jsonArray.result);

        console.log(eval(jsonArray.result));
        var map = JSON.parse(jsonArray.result)[0];
        console.log(map);
        var stringList = map.abc;
        console.log(stringList);
        var userList = map.user;
        console.log(userList);
        var user = userList[0];
        console.log(user);
        console.log(user.password);

    }
});