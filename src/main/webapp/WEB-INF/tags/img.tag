<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="img" required="true" type="net.lafox.muza.entity.Image" %>
<%@ attribute name="w" required="true" type="java.lang.Integer" %>
<%@ attribute name="h" required="true" type="java.lang.Integer" %>
<%@ attribute name="op" required="true" type="java.lang.Character" description="w|h|c|e|o" %>
<%@ attribute name="q" required="false" type="java.lang.Integer" description="jpeg quality 1..100 default 70" %>
<%@ attribute name="ext" required="false" type="java.lang.String" description="jpg|png|gif default jpg" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String"  %>
<%@ attribute name="cssId" required="false" type="java.lang.String"  %>

<img id="${cssId}" class="${cssClass}" src="/i/${img.id}-${op}-w${w}-h${h}-q${q>0 && q<=100?q:"70"}-v${img.version}.${ext!=null?ext:"png"}"/>
