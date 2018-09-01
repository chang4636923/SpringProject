<%@ page import="com.chang.entity.MemoInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memoInfo</title>
    <link rel="stylesheet" href="../../assets/css/base.css" type="text/css">
    <%
        List<MemoInfo> memoInfoList = (List<MemoInfo>) session.getAttribute("memoInfoList");
        Iterator<MemoInfo> iterator = memoInfoList.iterator();
    %>

</head>
<body>
<h1>hello this is memoInfo!!</h1>


<table>

    <thead>
    <tr>  <th>标题</th> <th width="600px">内容</th> <th width="200px">创建时间</th> <th width="200px">修改时间</th> <th>详情</th> </tr>
    </thead>

    <tbody>
    <% while (iterator.hasNext()) {
        MemoInfo memoInfo = iterator.next();
    %>
    <tr>
        <td> <%=memoInfo.getTitle()%> </td>
        <td width="600px"> <%=memoInfo.getContent()%></td>
        <td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(memoInfo.getCreatedTime())%></td>
        <td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(memoInfo.getModifyTime())%></td>
        <td><a href="#">查看</a></td>
    </tr>
    <% } %>

    </tbody>
</table>


</body>
</html>
