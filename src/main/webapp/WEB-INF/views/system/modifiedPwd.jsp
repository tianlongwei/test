<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/5
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
    <form method="post" action="/modifiedPwd">
        <table>
            <caption>修改密码</caption>
            <tr>
                <td>新密码:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <input type="submit" name="提交"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
