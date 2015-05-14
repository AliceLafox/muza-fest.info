<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<html>
<tags:head
        canonicalUrl=""
        title="Редактор материала"
        description=""
        imageOg=""
        keywords=""
        jsAtHeader="true"
        />
<body>
<tags:menuTop/>
<link rel="stylesheet" type="text/css" href="/js/bootstrap-datetimepicker-4/bootstrap-datetimepicker.min.css"/>
<div class="container">
    <tags:userCabMenu title="Редактор материала"/>

    <div class="" role="alert" id="messageContainer"></div>

    <form:form id="editItemForm" modelAttribute="itemForm">
        <form:hidden path="id"/>
        <div class="row">
            <div class="col-md-9">

                <div class="form-group">
                    <div class="input-group margin-bottom">
                        <span class="input-group-addon">Заголовок:</span>
                        <form:input path="title" cssClass="form-input form-control" placeholder="заголовок" required="true"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">Категория:</span>
                                <form:select dataType="select" path="category" cssClass="form-control"  required="true">
                                    <form:option value="" label="----   Выберите категорию   ----"/>
                                    <form:options items="${categoryList}" itemValue="seoUrl" itemLabel="title"/>
                                </form:select>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">

                    <div class="col-md-8">
                        <div class="form-group">
                            <div class='input-group date margin-bottom' id='datetimepicker'>
                                <span class="input-group-addon">дата публикации:</span>
                                <form:input dataType="date" path="dateStart" data-format="YYYY/MM/DD HH:mm"
                                            cssClass="form-input-datestart form-control"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                            </div>
                        </div>

                    </div>

                </div>


            </div>
            <div class="col-md-3"><tags:avatarUpload img="${itemForm.avatar}" op="c" w="100" h="100" ext="jpg" q="80"/></div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Полный текст материала (используйте wiki-разметку): </h3>
            </div>
            <div class="panel-body">
                <form:textarea path="content" cssClass="form-input-content form-input"  required="true"
                               cssStyle="height: 400px; width: 100%"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6"></div>
            <div class="col-md-3">

                <div class="input-group">
                    <span class="form-control">разрешить публикацию</span>
        <span class="input-group-addon">
        <form:checkbox dataType="checkbox" path="enabled" cssClass="form-input-enabled dataType-checkbox"/>
        </span>
                </div>
            </div>
            <div class="col-md-3  pull-right">
                <button type="submit" class="btn btn-success pull-right">Сохранить</button>
            </div>
        </div>
    </form:form>
</div>
<tags:footer jsAtHeader="true"/>

<script type="text/javascript" src="/js/moment.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-datetimepicker-4/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        var FORM = $('#editItemForm');
        var MSG = $('#messageContainer');
        FORM.bind('submit', function (e) {
            $.post('/json/cab/save', FORM.serialize(), function (response) {
                FORM.find('.has-error').removeClass('has-error')
                MSG.removeClass("alert").removeClass("alert-danger").removeClass("alert-success")
                MSG.empty()
                if (response.status == 'SUCCESS') {
                    MSG.addClass("alert").addClass("alert-success")
                    MSG.append( "OK")
                } else {
                    MSG.addClass("alert").addClass("alert-danger");
                    jQuery.each(response.errorMessageList, function (i, val) {
                        $("#" + val.field).parent().addClass("has-error")
                        MSG.append("<li>" + val.defaultMessage + "</li>")
                    })
                }
            }, 'json')
            e.preventDefault()
            return false;
        })
    })

</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#datetimepicker').datetimepicker({//http://eonasdan.github.io/bootstrap-datetimepicker/
            format: 'YYYY-MM-DD HH:mm'
        });
    });
</script>
</body>
</html>
