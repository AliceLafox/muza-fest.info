<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"  trimDirectiveWhitespaces="true"  language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<tags:head
        canonicalUrl=""
        title="Кабинет пользователя: Мои материалы"
        description=""
        imageOg=""
        keywords=""
        jsAtHeader="true"
        />


<body>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css"/>
<tags:menuTop/>


<div class="container">
    <tags:userCabMenu title="Мои материалы"/>

    <tags:addItemForm/>


    <table class="table table-striped table-hover table-bordered" id="aTable">
        <thead>
        <tr>
            <th>id</th>
            <th>название</th>
            <th>изменить</th>
        </tr>
        </thead>
    </table>
</div>


<tags:footer jsAtHeader="true"/>

<script type="text/javascript" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>

<script type="text/javascript">  //page JS!!!
var table
var format = "YYYY-MM-DD"

$(document).ready(function () {
    table = $('#aTable').DataTable({
        serverSide: true,
        ajax: {
            url: '/json/cab/list',
            type: 'POST'
        },
        columns: [
            {data: "id"},
            {data: "title"},
            {data: "id"},
        ],
        lengthMenu: [
            [5, 10, 15, 25, 50, 100, -1],
            [5, 10, 15, 25, 50, 100, "All"]
        ],
        pageLength: 10,
        aoColumnDefs: [{bSortable: false, aTargets: [-1]}],
        order: [[0, "desc"]],
        fnRowCallback: function (nRow, aData, iDisplayIndex) {
//                if(aData['imgVer']>0){
//                    $('td', nRow).eq(0)
//                            .html('<div class="idColumn1"><img src="/img/ad'+aData['id']+'-w58-v'+aData['imgVer']+'.jpg"/><div class="idColumn2">'+aData['id']+'</div></div>')
//                            .addClass("hasImg")
//                }
            $('td', nRow).eq(2).html('' +
            '<a href="/usercab/item/' + aData['id'] + '" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i> edit</a>' +
//                '<button class="btn btn-danger  btn-xs" onclick="delRow(' + aData['id'] + ',\'' + aData['title'] + '\')"><i class="glyphicon glyphicon-trash"></i> del</button>' +
            '')
//                if (aData['dateStart']) $('td', nRow).eq(3).html(moment.unix(aData['dateStart'] / 1000).format(format))
//                if (aData['dateStop']) $('td', nRow).eq(4).html(moment.unix(aData['dateStop'] / 1000).format(format))
//                $('td', nRow).eq(5).html((aData['shop'])?aData['shop']['title']:'')
//                $('td', nRow).eq(6).html((aData['department'])?aData['department']['title']:'')
            if (aData['enabled'] == false) $(nRow).addClass('dataTableRowDisabled')
        }
    });

    $(".reload").click(function () {
        table.ajax.reload(null, false);
    })


    var addForm = $('#addItemForm');
    addForm.bind('submit', function (e) {
        $.post(addForm.attr('action'), addForm.serialize(), function (response) {
            if (response.item.id > 0) window.location = "/usercab/item/" + response.item.id
        })
        e.preventDefault()
    });


});

</script>

</body>
</html>
