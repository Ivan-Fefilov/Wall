<%-- 
    Document   : EditComment
    Created on : 21.06.2016, 17:18:15
    Author     : Ivan_Fefilov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>

<t:master-page title="Редактирование комментария">
    <form method="post" action="/edit-comment" class="padded gap-bottom">
        <%@include file="../fragments/error.jspf"%>
        <textarea name ="content" type="text" class="gap-bottom" placeholder="Content" >${content}</textarea>
        <button class=" red pull-right ">Сохранить</button>
    </form>
</t:master-page>
