<%-- 
    Document   : comments
    Created on : 17.06.2016, 12:01:30
    Author     : Ivan_Fefilov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="user" value="${sessionScope.user}"/>

<t:master-page title="Комментарии">
    <c:choose>
        <c:when test="${user != null}">
            <%@include file="../fragments/post.jspf" %>
            <hr>
            <c:forEach items="${comments}" var="comments">
                <%@include file="../fragments/comment.jspf" %>
            </c:forEach>
            <%@include file="../fragments/add-comment.jspf" %>
        </c:when>
        <c:otherwise>
            <%@include file="../fragments/post.jspf" %>
            <hr>
            <c:forEach items="${comments}" var="comments">
                <%@include file="../fragments/comment.jspf" %>
            </c:forEach>
            <p class="message comment" style="border: 0.01em solid black;color: #383636;">Чтобы оставить комментарий, необходимо <a class="link" style="border: 0.01em dashed ;" href="/registration">зарегистрироваться</a> или <a class="link" style="border-bottom: 0.01em dashed ;" href="/login">войти</a></p>
        </c:otherwise>
    </c:choose>
</t:master-page>
