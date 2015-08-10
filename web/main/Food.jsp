<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>SMS - main course</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style>
        nav.navbar i {
            width: 30px;
        }

    </style>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">SMS</a>
        </div>
        <div class="collapse navbar-collapse" id="main-nav">
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Food <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${foodTypeEntityList}" var="foodTypeItem">
                            <li><a href="/FoodSerlvet?typeID=${foodTypeItem.foodTypeID}"><i
                                    class="glyphicon glyphicon-cutlery"></i>${foodTypeItem.foodTypeName}
                            </a></li>
                            <!--<li><a href="mainCourse.jsp"><i class="glyphicon glyphicon-cutlery"></i>Main course</a></li>-->
                        </c:forEach>
                        <li role="separator" class="divider"></li>
                        <li><a href="main/newFood.jsp"><i class="glyphicon glyphicon-plus"></i>New</a></li>
                    </ul>
                </li>
                <li><a href="PackageServlet">Package</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button">${currentUser.userName} <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../login.jsp"><i class="glyphicon glyphicon-log-out"></i>&emsp;Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="index.jsp">Home</a></li>
                <li class="active">${currentFoodTypeName}</li>
            </ol>
            <span class="text-danger">${ErrorMessage}</span>
            <span class="text-success">${SuccessMessage}</span>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Picture</th>
                    <th>Status</th>
                    <th style="width:150px">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resultList}" var="foodTypeItem">
                    <tr>
                        <td>${foodTypeItem.foodID}</td>
                        <td>${foodTypeItem.foodName}</td>
                        <td>${foodTypeItem.price}</td>
                        <td>${foodTypeItem.pictureURL}</td>
                        <td>
                            <c:forEach items="${statusEntityList}" var="statusItem">
                                <c:choose>
                                    <c:when test="${statusItem.statusID eq foodTypeItem.statusID}">
                                        ${statusItem.statusName}
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="../UpdataFoodServlet?foodId=${foodTypeItem.foodID}&price=${foodTypeItem.price}&update=false">update</a>&emsp;
                            <a href="../DeleteFoodSerlvet?foodId=${foodTypeItem.foodID}"
                               onclick="return confirm('Delete this item?')">delete</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <nav class="text-right">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
