<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<tags:head
        canonicalUrl="http://muza-fest.info"
        title="${item.title} | Муза Непокорных"
        description="${item.content.length()>150? item.content.substring(0,150).concat(' ...'):item.content}"
        imageOg="http://muza-fest.info/images/facebook.png"
        keywords="музыкальный фестиваль, сопротивление, муза"
        jsAtHeader="false"
        />


<body>
<tags:menuTop/>

<div class="container">
  <ol class="breadcrumb">
    <li><a href="/">Home</a></li>
    <li><a href="/${item.category.seoUrl}">${item.category.title}</a></li>
    <li class="active">${item.title}</li>
  </ol>

<h1>${item.title}</h1>

<div class="row">
    <div class="col-md-3">
        <fmt:formatDate value="${item.dateStart}" pattern="dd-MM-YYYY"/>, ${item.user.username}
        <tags:img img="${item.avatar}" w="200" h="200" op="w" q="50" ext="jpg" cssClass="media-object" />
    </div>
    <div class="col-md-9">
<div>
  ${contentCompiled}
</div>
    </div>
</div>

</div>

<tags:footer jsAtHeader="false"/>

</body>
</html>