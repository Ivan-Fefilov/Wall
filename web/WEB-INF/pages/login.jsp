<%-- 
    Document   : login
    Created on : 13.06.2016, 19:57:45
    Author     : Ivan_Fefilov
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>

<t:master-page title="Вход">
    <form action="/login" method="post" style="width: 19em" class = "centered animated fadeInLeftBig">
        <h1>Рады Вас видеть!</h1>
        <%@include file="../fragments/error.jspf" %>
        <input name="login" type="text" class="gap-bottom" placeholder="Логин" value="${login}"> 
        <input name="password" type="password" class="gap-bottom" placeholder="Пароль">
        <a class="link" href="/registration">Регистрация</a>
        <button class=" red pull-right">Войти</button>
    </form>
</t:master-page>

