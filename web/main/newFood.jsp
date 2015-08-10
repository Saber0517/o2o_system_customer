<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SMS - new food</title>
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
                            <li>
                                <a href="/FoodSerlvet?typeID=${foodTypeItem.foodTypeID}"><i
                                        class="glyphicon glyphicon-cutlery"></i>${foodTypeItem.foodTypeName}</a>
                            </li>
                        </c:forEach>
                        <li role="separator" class="divider"></li>
                        <li><a href="newFood.jsp"><i class="glyphicon glyphicon-plus"></i>New</a></li>
                    </ul>
                </li>
                <li><a href="package.jsp">Package</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button">${currentUser.userName}<span
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
                <li class="active">New food</li>
            </ol>
            <form action="../NewFoodServlet" method="post" class="center-block" style="max-width: 500px"
                  enctype="multipart/form-data">
                <h1 class="page-header">New food</h1>

                <h4 class="text-success">${succesMessage}</h4>

                <h4 class="text-danger">${failMessage}</h4>

                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" class="form-control" required/>
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="number" name="price" class="form-control" required/>
                </div>
                <div class="form-group">
                    <label>Type</label>
                    <select class="form-control" name="type" required>
                        <c:forEach items="${foodTypeEntityList}" var="foodTypeItem">
                            <option value="${foodTypeItem.foodTypeID}">-${foodTypeItem.foodTypeName}-</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Picture</label>
                    <input type="file" name="picture" class="form-control" required/>
                </div>
                <br/>
                <button type="submit" class="btn btn-block btn-primary">Save</button>
                <span class="text-success">${ReminderMessage}</span>
            </form>
        </div>
    </div>
</div>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
