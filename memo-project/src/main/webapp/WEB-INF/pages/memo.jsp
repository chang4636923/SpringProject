<%@ page import="com.chang.entity.MemoGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../assets/css/base.css" type="text/css">
    <title>memo</title>
    <%
        List<MemoGroup> list = (List<MemoGroup>) session.getAttribute("memoGroups");
        Iterator<MemoGroup> iterator = list.iterator();
    %>
  

</head>
<body>

<table>

    <thead>
    <tr>  <th>标题</th> <th width="200px">创建时间</th> <th width="200px">修改时间</th> <th>操作</th> <th>详情</th> </tr>
    </thead>

    <tbody>
    <% while (iterator.hasNext()) {
        MemoGroup memoGroup = iterator.next();
    %>
    <tr>
        <td>
            <a href="/memoInfo?id=<%=memoGroup.getId()%>"><%=memoGroup.getName()%></a>
        </td>
        <td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(memoGroup.getCreatedTime())%></td>
        <td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(memoGroup.getModifyTime())%></td>
        <td><a href="#">修改</a></td>
        <td></td>
    </tr>
    <% } %>
    <td colspan="5"><a href="/add_memo.jsp">添加</a></td>
    </tbody>
</table>

<a href="index.jsp" style="float: right; size: 20px;color: white;font-weight: bold;">返回主页</a>

</body>
</html>
