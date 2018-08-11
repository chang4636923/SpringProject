<%@ page import="java.util.Map" %>
<%@ page import="com.chang.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="../.././assets/css/base.css" type="text/css">
</head>

<body background="blue">
<%
    Map<String,String> map = (Map<String, String>) session.getAttribute("map");
    User user = (User) session.getAttribute("user");
%>

<h1>hello <%=map.get("username") %></h1>
<h1>hi <%=user.toString()%></h1>

<form action="/memo" method="post">
    <h1><input type="submit" value="便签应用"></h1>
</form>

</body>
</html>
