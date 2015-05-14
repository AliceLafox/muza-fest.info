<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form action="/json/cab/addItem" id="addItemForm" class="form-horizontal">
    <div class="row">
        <div class="col-md-6  pull-right">
                <div class="input-group margin-bottom">
                    <input class="form-input form-control" placeholder="введите заголовок нового материала" required="true" name="title"/>
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-success pull-right">Добавить</button>
                    </div>
            </div>
        </div>

    </div>
</form>
<hr/>

