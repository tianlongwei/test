<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/2
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>
list
你好<% request.getSession().getAttribute("currentUser");%>
<a href="/logout" >登出</a>
<a href="/toModifiedPwd">修改密码</a>
</body>
</html>
