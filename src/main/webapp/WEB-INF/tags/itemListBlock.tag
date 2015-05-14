<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="item" required="true" type="net.lafox.muza.entity.Item" %>

<div class="media margin-bottom-20">
    <div class="media-body">
        <h4 class="media-heading" id="media-heading"><a href="/item/${item.id}">${item.title}</a></h4>
        ${item.content.length()>200? item.content.substring(0,200).concat(' ...'):item.content}
    </div>
    <div class="media-right">
        <a href="/item/${item.id}">
            <tags:img img="${item.avatar}" w="100" h="100" op="w" q="50" ext="jpg" cssClass="media-object" />
            <%--<img class="media-object" style="width: 100px; "--%>
            <%--src="http://static.lafox.net/metronic-3.3.1/assets/admin/pages/media/gallery/image1.jpg" alt=""/>--%>
        </a>
    </div>
</div>