<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ attribute name="img" required="true" type="net.lafox.muza.entity.Image" %>
<%@ attribute name="w" required="true" type="java.lang.Integer" %>
<%@ attribute name="h" required="true" type="java.lang.Integer" %>
<%@ attribute name="op" required="true" type="java.lang.Character" description="w|h|c|e|o" %>
<%@ attribute name="q" required="false" type="java.lang.Integer" description="jpeg quality 1..100 default 70" %>
<%@ attribute name="ext" required="false" type="java.lang.String" description="jpg|png|gif default jpg" %>

<tags:img img="${img}" w="${w}" h="${h}" op="${op}" q="${q}" ext="${ext}" cssClass="avatarUploader" cssId="avatarUploader"/>
<div>
    <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-image"></i>
        <span>загрузить</span>
        <input id="fileupload" type="file" name="file" accept="image/*"/>
    </span>
</div>

<c:set var="fupath" value="http://static.lafox.net/jQuery-File-Upload-9.9.3"/>
<link rel="stylesheet" href="${fupath}/css/jquery.fileupload.css">
<script src="${fupath}/js/vendor/jquery.ui.widget.js"></script>
<script src="${fupath}/js/jquery.iframe-transport.js"></script>
<script src="${fupath}/js/jquery.fileupload.js"></script>
<script>
    $(document).ready(function () {
        'use strict';
        $('#fileupload').fileupload({
            url: "/json/cab/uploadItemAvatar?id=${img.id}",
            dataType: 'json',
            done: function (e, data) {
                if (data.result['error']) {
                    alert(data.result['error'])
                } else {
                    var REGEXP = /(.*\/)(\d*)(.+-v)(\d*)(\..+)/
                    var av=data.result['avatar'];
                    var newSrc = $("#avatarUploader").prop("src").replace(REGEXP, '$1'+av.id+'$3'+av.version+'$5');
                    $("#avatarUploader").attr("src", newSrc)
                }
            }
        })
    });
</script>