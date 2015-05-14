<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"  trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<tags:head
        canonicalUrl="http://muza-fest.info"
        title="Муза Непокорных - официальный сайт фестиваля"
        description="Фестиваль «Муза непокорных - 2015» – это декларация основных прав человека и общества, манифест свободного, независимого искусства, раскрытие творческого потенциала общества на пути СОПРОТИВЛЕНИЯ злу и ненависти в его борьбе за Мир,  Свободу и Справедливость!"
        imageOg="http://muza-fest.info/images/facebook.png"
        keywords="музыкальный фестиваль, сопротивление, муза"
        jsAtHeader="false"
        />


<body>
<tags:menuTop/>

<%--<h3>gallery or carousel</h3>--%>
<div class="container">
<%--<h2></h2>--%>
    <%--<hr/>--%>
<%--carousel here--%>

<%--<div class="row">--%>
    <%--<div class="col-md-6">--%>
    <%--<div class="embed-responsive embed-responsive-16by9 margin-bottom-20">--%>
    <%--<iframe class="embed-responsive-item"--%>
    <%--src="//www.youtube.com/embed/X3GnKIL3X3E" frameborder="0"--%>
    <%--allowfullscreen></iframe>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="col-md-6">--%>
        <%--анонс--%>
    <%--</div>--%>

<%--</div>--%>
<div class="row">

    <c:forEach items="${items}" var="i">
        <div class="col-md-6">
            <tags:itemListBlock item="${i}"/>
        </div>
    </c:forEach>
</div>
</div>

<%--<div class="row">--%>
    <%--<div class="col-md-5">--%>
        <%--<div class="embed-responsive embed-responsive-16by9 margin-bottom-20">--%>
            <%--<iframe class="embed-responsive-item"--%>
                    <%--src="//www.youtube.com/embed/X3GnKIL3X3E" frameborder="0"--%>
                    <%--allowfullscreen></iframe>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="col-md-5">--%>
        <%--<c:forEach items="${items}" var="i">--%>
            <%--<tags:itemListBlock item="${i}"/>--%>
        <%--</c:forEach>--%>
    <%--</div>--%>
    <%--<div class="col-md-2">--%>
        <%--<div class="panel panel-default"><h5>Зачем эта панель?</h5></div>--%>
        <%--<div>рекламная площадка</div>--%>
        <%--<div class="panel panel-default">--%>
            <%--<div class="panel-heading">Категории</div>--%>
            <%--<ul class="list-group">--%>
                <%--<c:forEach items="${categoryList}" var="i">--%>
                    <%--<li class="list-group-item"><a href="${i.seoUrl}">${i.title}</a></li>--%>
                <%--</c:forEach>--%>

            <%--</ul>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<tags:footer jsAtHeader="false"/>

</body>
</html>
