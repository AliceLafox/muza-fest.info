<%@tag pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="jsAtHeader" required="true" type="java.lang.Boolean" description="place jquery and bootstrap JS in header" %>

<div id="ln-footer">
  <div class="container">
      <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
          Фестиваль Муза Непокорных
        </div>

        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
          <ul class="list-inline lafox-soc">
            <li><a target="_blank" href="https://www.facebook.com/events/434179623408364/" class="soc40 soc40-facebook" title="facebook"></a></li>
            <li><a target="_blank" href="http://vk.com/event89297594" class="soc40 soc40-vk" title="vk"></a></li>
            <%--<li><a target="_blank" href="https://www.youtube.com/channel/UCfHQMwLAbMbb0gnShRn7PCg" class="soc40 soc40-youtube" title="youtube"></a></li>--%>
            <%--<li><a target="_blank" href="/rss" class="soc40 soc40-rss" title="RSS"></a></li>--%>
          </ul>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
          <div class=" pull-right"> &copy; Muza-Fest <fmt:formatDate value="<%=new java.util.Date()%>" pattern="YYYY"/></div>
        </div>
      </div>


    </div>
</div>
<c:if test="${!jsAtHeader}">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/${bootstrapVer}/js/bootstrap.min.js"></script>
</c:if>

<%--
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-50526902-1', 'lafox.net');
  ga('send', 'pageview');
</script>
--%>
