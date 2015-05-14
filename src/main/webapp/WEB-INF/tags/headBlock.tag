<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="href" required="true" type="java.lang.String" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>


<%--<div class="panel panel-default green"><h4><a  href="${href}">${title}</a></h4></div>--%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h4 class="panel-title"><a href="${href}">${title}</a></h4>
    </div>
    </div>