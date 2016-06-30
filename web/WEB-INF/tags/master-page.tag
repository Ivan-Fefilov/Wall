<%-- 
    Document   : MasterPage
    Created on : 13.06.2016, 19:07:11
    Author     : Ivan_Fefilov
--%>
<%@tag import="com.wall.models.AppUser" %>
<%@tag description="This is a master page"
       pageEncoding="UTF-8"%>
<%@taglib prefix="c" 
          uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title"%>

<%
    AppUser user = (AppUser) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <script src="/js/libs/jquery-1.10.2.min.js"></script>
        <script src="/js/libs/modernizr-2.6.2.min.js"></script>
        <script src="/js/groundwork.all.js"></script>
        <link href="/css/design.css" rel="stylesheet">
    </head>
    <body>

        <nav class="centred nav small-tablet  charcoal margin" title="Меню" >
            <ul>
                <li>
                    <a href="/" class="icon-home"> Главная</a>
                </li>

                <c:choose>
                    <c:when test = "${user == null}">
                        <li>
                            <a href="/login" class="icon-signin">Вход</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a class="icon-user">
                                ${user.info.name}
                            </a>
                            <ul>
                                <div class="padded-desktop">
                                    <li>
                                        <a href="/settings" class="icon-cog">Настройки</a>                              
                                    </li>
                                    <li>
                                        <a href="/signout" class="icon-signout">Выход</a>
                                    </li>
                                </div>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
        <div class ="centered padded three fourths">
            <jsp:doBody/>
        </div>

    </body>
</html>