<%@tag pageEncoding="UTF-8"  trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="canonicalUrl" required="true" type="java.lang.String" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="keywords" required="true" type="java.lang.String" %>
<%@ attribute name="description" required="true" type="java.lang.String" %>

<%@ attribute name="titleOg" required="false" type="java.lang.String" %>
<%@ attribute name="descriptionOg" required="false" type="java.lang.String" %>
<%@ attribute name="imageOg" required="true" type="java.lang.String" %>

<%@ attribute name="jsAtHeader" required="true" type="java.lang.Boolean" description="place jquery and bootstrap JS in header" %>

<c:set var="bootstrapVer" value="3.3.4" scope="request"/>

<head>
    <meta name="author" content="Lafox.Net Developers Team: http://dev.lafox.net"/>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="icon" href="/favicon.png">
    <!--[if IE]><link rel="shortcut icon" href="/favicon.ico"><![endif]-->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/${bootstrapVer}/css/bootstrap.min.css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet/less" type="text/css" href="/css/style.less"/><script src="/js/less.min.js" type="text/javascript"></script>

    <c:if test="${jsAtHeader}">
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/${bootstrapVer}/js/bootstrap.min.js"></script>
    </c:if>

    <title>${title}</title>
    <meta name="description" content="${description}"/>
    <meta name="keywords" content="${keywords}"/>

    <meta property="og:type" content="website"/>
    <meta property="og:url" content="${canonicalUrl}"/>
    <meta property="og:title" content="${not empty titleOg?titleOg:title}"/>
    <meta property="og:description" content="${not empty descriptionOg?descriptionOg:description}"/>
    <meta property="og:image" content="${imageOg}"/>
    <meta property="og:site_name" content="Muza-Fest.info"/>

    <%--<link rel="publisher" href="https://plus.google.com/+LafoxNetScience"/>--%>

    <meta name="twitter:card" content="summary"/>
    <meta name="twitter:url" content="${canonicalUrl}"/>
    <meta name="twitter:title" content="${not empty titleOg?titleOg:title}">
    <meta name="twitter:description" content="${not empty descriptionOg?descriptionOg:description}"/>
    <meta name="twitter:image" content="${imageOg}">

    <link rel='canonical' href='${canonicalUrl}'/>
</head>
