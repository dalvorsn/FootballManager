<%--
    Document   : template
    Created on : 15/04/2020, 23:13:52
    Author     : dalvo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:catch var="ex">
    <c:if test="${param.page == null}">
        <jsp:include page="pages/home.jsp" />
    </c:if>
    <c:if test="${param.page != null}">
        <jsp:include page="pages/${param.page}.jsp" />
    </c:if>

</c:catch>
<c:if test="${ex != null}" >
    <h1>ERRO</h1>
    ${ex.message}
</c:if>