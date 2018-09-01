
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<div style="width: 500px">
    <form action="/login" method="post">
        用户：<input name="username" type="text" placeholder="请输入用户名"><br>
        密码：<input name="password" type="password" placeholder="请输入密码"><br>
        年龄：<input name="age" type="text"><br>
        性别：<input name="sex" type="radio" value="male">男 <input name="sex" type="radio" value="female">女<br>
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
