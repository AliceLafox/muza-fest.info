<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ attribute name="narrow" required="true" type="java.lang.Boolean" description="narrow true|false" %>--%>

<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" style="padding-top: 6px;margin-right: 8px" href="/" title="Муза непокорных">
                Муза непокорных <br/><small>официальный сайт фестиваля</small>
                <%--<img src="/images/LOGO-000.png" alt="Муза непокорных" width="100" height="32"/>--%>
            </a>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Категории<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                <c:forEach items="${categoryList}" var="i">
                <li><a href="/${i.seoUrl}">${i.title}</a></li>
                </c:forEach>

                    </ul>
                </li>


                <li class="divider-vertical"></li>
                <li class="dropdown">

                    <sec:authorize access="isAnonymous()">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown">войти<strong class="caret"></strong></a>
                        <div class="dropdown-menu" style="padding: 10px">
                            <form method="post" action="j_spring_security_check" accept-charset="UTF-8">
                                <input type="text" placeholder="Username" id="username" name="j_username">
                                <input type="password" placeholder="Password" id="password" name="j_password">
                                <input type="checkbox" name="_spring_security_remember_me" id="remember-me" checked="checked">
                                <label class="string optional" for="remember-me">запомнить меня</label>
                                <input class="btn btn-primary btn-block" type="submit" id="sign-in" value="войти">
                                    <%--<a href="/register" class="btn btn-success btn-block">регистрация</a>--%>
                                <a href="/register">регистрация</a>
                                <a href="/recoverPassword">востановить пароль</a>
                            </form>
                        </div>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <sec:authentication property="principal.username"/><span  class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/usercab">Мой аккаунт</a></li>
                            <li><a href="/usercab/item">Мои материалы</a></li>
                            <li><a href="/usercab/item/1">Edit/1</a></li>
                            <li><a href="#">Изменить инфо</a></li>
                            <li class="divider"></li>
                            <li><a href="/logout">Выйти</a></li>
                        </ul>
                    </sec:authorize>

                </li>
            </ul>

        </div>
    </div>
</nav>