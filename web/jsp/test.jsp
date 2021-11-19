<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>信息安全实验室</title>

  <link rel="stylesheet" href="css/style.css">
  <script src="js/script.js"></script>
</head>
<body>
  <!-- 头部模块 -->
  <div class="top">
    <div class="header">
      <img class="logo" src="${ pageContext.request.contextPath }/images/hznuLogo.png" />
      <div class="left"><div class="hznu">杭州师范大学</div></div>
      <div class="left"><div class="islab">信息安全实验室</div></div>
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

  <div>
    <s:actionerror/>
    <s:form action="user_addUser" method="POST" namespace="/">
      <table>
        <tr>
          <td><label for="username">用户名：</label></td>
          <td><input name="username" type="text" id="username"/></td>
        </tr>
        <tr>
          <td><label for="password">密码：</label></td>
          <td><input name="password" type="password" id="password"/></td>
        </tr>
        <tr>
          <td><label for="telephone">手机号：</label></td>
          <td><input name="telephone" type="text" id="telephone"/></td>
        </tr>
        <tr>
          <td colspan="2"><button type="submit">添加</button></td>
        </tr>
      </table>
    </s:form>
  </div>

  <!-- 底部模块 -->
  <div id="footer">
    杭州师范大学信息安全实验室 版权所有 &copy; 2017-2018&nbsp;&nbsp;&nbsp;&nbsp;
  </div>
</body>
</html>