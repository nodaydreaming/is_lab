<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>testAjax+Json</title>
    <script src="js/script.js"></script>
    <script src="js/jquery-1.10.2.min.js"></script>
</head>
<body>
    <h1>TestAjaxJson</h1>
    <div style="width: 100px; height: 200px;">123456</div>
    <button>TestAjax</button>
</body>
<script type="text/javascript">
         console.log($("button").text());
            $.ajax({
                url: 'index.action',
                type: 'Post',
                dataType: 'json',
                async: false,
                success: function (jsonArray) {
                    console.log(jsonArray.result);
                    var text = "";
                    console.log(eval(jsonArray.result).length);
                    var list = JSON.parse(jsonArray.result);
                    console.log(list);
                    for (var i = 0; i < eval(jsonArray.result).length; i++) {
                        console.log(eval(jsonArray.result)[i]);

                        var student = eval(jsonArray.result)[i];
                        text += eval(jsonArray.result)[i];
                    }
                    $("div").text(text);
                }
            });
</script>
</html>