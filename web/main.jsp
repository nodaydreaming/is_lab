<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>杭州师范大学网络与信息安全实验室</title>

    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css">
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/script.js"></script>
</head>
<body>
<!-- 头部模块 -->
<div class="top">

    <div class="header">
        <a href="#"><img class="logo" src="${ pageContext.request.contextPath }/images/hznuLogo.png" /></a>

        <a href="#"><div class="left hznu">杭州师范大学</div></a>
        <a href="#"><div class="left lab">网络与信息安全实验室</div></a>

        <a href="#"><div class="left ename">Network and Information security laboratory@hznu university</div></a>
    </div>

</div>
<!-- 导航模块 -->
<div id="nav">
    <ul class="nav">
        <li><a href="#">首页</a></li>
        <li><a href="#">实验室简介</a></li>
        <li><a href="#">实验室成员</a></li>
        <li><a href="#">成果导览</a></li>
        <li><a href="#">公告通知</a></li>
        <li><a href="#">专业资讯</a></li>
        <li><a href="#">实验室文化</a></li>
        <li><a href="#">关于我们</a></li>
    </ul>
</div>
<!-- 插入页面 -->
<iframe id = "iframe1" src="index.jsp" frameborder="0" scrolling="no"></iframe>

<!-- 底部模块 -->
<div id="footer">
    <div style="width: 1200px; text-align: center;">
        <div style="width: 36%;float: left;">联系方式：15990019420&nbsp;&nbsp;&nbsp;邮箱：805297781@qq.com</div>

        <div style="width: 34%;float: left;">Copyright©2017-2018&nbsp;杭州师范大学网络与信息安全实验室</div>

        <div style="width: 30%;float: right;">地址：杭州师范大学仓前校区勤园13号楼301</div>
    </div>
</div>
</body>

</html>