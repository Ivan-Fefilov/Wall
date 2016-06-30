<%-- 
    Document   : index
    Created on : 13.06.2016, 19:17:31
    Author     : Ivan_Fefilov
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="user" value="${sessionScope.user}"/>
<t:master-page title="Главная страница">
    <c:choose>
        <c:when test="${user != null}">
            <link href="/css/overflow.css" rel="stylesheet">
            <%@include file="../fragments/add-post.jspf" %>
            <c:forEach items="${posts}" var="posts">
                <%@include file="../fragments/post.jspf" %>
                <form id="post-${posts.id}" method="get" action="/comments">
                    <input type="hidden" name="postId" value="${posts.id}">
                    <a class="icon-caret-right link" onclick="document.getElementById('post-${posts.id}').submit()">Показать полностью</a>
                </form>
                <hr>
            </c:forEach>
        </c:when>
        <c:otherwise>
                <p class="message">Чтобы оставить пост, необходимо <a class="link" style="border: 0.01em dashed;" href="/registration">зарегистрироваться</a> или <a class="link" style="border-bottom: 0.01em dashed;" href="/login">войти</a></p>
                <c:forEach items="${posts}" var="posts">
                    <link href="/css/overflow.css" rel="stylesheet">
                    <%@include file="../fragments/post.jspf" %>
                    <form id="post-${posts.id}" method="get" action="/comments">
                        <input type="hidden" name="postId" value="${posts.id}">
                        <a class="icon-caret-right link" onclick="document.getElementById('post-${posts.id}').submit()">Показать полностью</a>
                    </form>
                    <hr>
                </c:forEach>
        </c:otherwise>   
    </c:choose>
</t:master-page>
