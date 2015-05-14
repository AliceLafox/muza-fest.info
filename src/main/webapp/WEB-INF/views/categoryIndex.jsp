<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"  trimDirectiveWhitespaces="true"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<tags:head
        canonicalUrl="http://muza-fest.info"
        title="${categoryTitle} | Муза Непокорных"
        description="Фестиваль «Муза непокорных - 2015» – это декларация основных прав человека и общества, манифест свободного, независимого искусства, раскрытие творческого потенциала общества на пути СОПРОТИВЛЕНИЯ злу и ненависти в его борьбе за Мир,  Свободу и Справедливость!"
        imageOg="http://muza-fest.info/images/facebook.png"
        keywords="музыкальный фестиваль, сопротивление, муза"
        jsAtHeader="false"
        />


<body>
<tags:menuTop/>
<div class="container">
    <h2>${categoryTitle}</h2>
    <hr/>
    <div class="row">

        <c:forEach items="${items}" var="i">
            <div class="col-md-6">
                <tags:itemListBlock item="${i}"/>
            </div>
        </c:forEach>
    </div>
</div>

<tags:footer jsAtHeader="false"/>

</body>
</html>
