<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SMS - home</title>
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
            <a class="navbar-brand" href="/LoginServlet">SMS</a>
        </div>
        <div class="collapse navbar-collapse" id="main-nav">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Food <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${foodTypeEntityList}" var="foodTypeItem">
                            <li><a href="../FoodSerlvet?typeID=${foodTypeItem.foodTypeID}"><i class="glyphicon glyphicon-cutlery"></i>${foodTypeItem.foodTypeName}
                            </a></li>
                            <!--<li><a href="mainCourse.jsp"><i class="glyphicon glyphicon-cutlery"></i>Main course</a></li>-->
                        </c:forEach>
                        <!--<li><a href="mainCourse.jsp"><i class="glyphicon glyphicon-cutlery"></i>Main course</a></li>
                    <li><a href="sideDishes.jsp"><i class="glyphicon glyphicon-leaf"></i>Side dishes</a></li>
                    <li><a href="drink.jsp"><i class="glyphicon glyphicon-glass"></i>Drink</a></li>-->
                        <li role="separator" class="divider"></li>
                        <li><a href="newFood.jsp"><i class="glyphicon glyphicon-plus"></i>New</a></li>
                    </ul>
                </li>
                <li><a href="../PackageServlet">Package</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">${currentUser.userName}
                        <span class="caret"></span></a>
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
            <h1>Welcome to seller management system.</h1>
        </div>
    </div>
</div>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
