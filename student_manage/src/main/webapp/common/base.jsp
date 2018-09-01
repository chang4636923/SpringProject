<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getServletContext().getContextPath();
    String basePath = path + "/";
    session.setAttribute("basePath",basePath);
%>
<c:set var="basePath" value="<%=basePath%>"/>
