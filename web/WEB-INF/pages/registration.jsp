<%-- 
    Document   : registration
    Created on : 13.06.2016, 19:58:02
    Author     : Ivan_Fefilov
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>

<t:master-page title="Регистрация">
    <form action="/registration" method="post" style="width: 20em" class = "centered animated fadeInRightBig">
        <h1>Давайте дружить?</h1>
        <%@include file="../fragments/error.jspf" %>
        <input name="login" type="text" class="gap-bottom" placeholder="Логин" value="${login}"> 
        <input name="name" type="text" class="gap-bottom" placeholder="Имя"value="${name}">
        <input name="email" type="email" class="gap-bottom" placeholder="Электронная почта"value="${email}">
        <input name="password" type="password" class="gap-bottom" placeholder="Пароль">
        <input name="confirm" type="password" class="gap-bottom" placeholder="Подтверждение пароля">
        <button class="red center">Зарегистрироваться</button>
    </form>
</t:master-page>