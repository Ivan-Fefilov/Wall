<%-- 
    Document   : EditPost
    Created on : 21.06.2016, 17:17:50
    Author     : Ivan_Fefilov
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>

<t:master-page title="Редактирование поста">
    <form method="post" action="/edit-post" class="padded gap-bottom">
        <%@include file="../fragments/error.jspf" %>
        <input name ="title" type="text" class="gap-bottom" placeholder="Title" value="${title}">
        <textarea name ="content" type="text" class="gap-bottom" placeholder="Content" >${content}</textarea>
        <button class=" red pull-right ">Сохранить</button>
    </form>
</t:master-page>