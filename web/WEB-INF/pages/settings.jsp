<%-- 
    Document   : settings
    Created on : 15.06.2016, 19:50:08
    Author     : Ivan_Fefilov
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>

<c:set var="user" value="${sessionScope.user}"/>

<t:master-page title="Настройки">
    <form action="/settings" method="post" style="width: 20em" class = "centered animated fadeInRightBig">
        <h1>Настройки</h1>
        <%@include file="../fragments/error.jspf" %>
        <input name="login" type="text" readonly class="gap-bottom" placeholder="Логин" value="${user.login}"> 
        <input name="name" type="text" class="gap-bottom" placeholder="Имя" value="${user.info.name}">
        <input name="email" type="email" class="gap-bottom" placeholder="Электронная почта"value="${user.info.email}">
        <button class="red pull-right">Сохранить</button>
    </form>
</t:master-page>
