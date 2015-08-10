<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SMS - update package</title>
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
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Food <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="mainCourse.jsp"><i class="glyphicon glyphicon-cutlery"></i>Main course</a></li>
                            <li><a href="sideDishes.jsp"><i class="glyphicon glyphicon-leaf"></i>Side dishes</a></li>
                            <li><a href="drink.jsp"><i class="glyphicon glyphicon-glass"></i>Drink</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="newFood.jsp"><i class="glyphicon glyphicon-plus"></i>New</a></li>
                        </ul>
                    </li>
                    <li class="active"><a href="package.jsp">Package</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">{uname} <span class="caret"></span></a>
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
                    <li><a href="package.jsp">Package</a></li>
                    <li class="active">Update package</li>
                </ol>

                <form action="index.jsp" method="post">
                    <h1 class="page-header">Update Package</h1>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="name" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" name="price" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label>Foods</label>
                        <input type="text" name="foods" class="form-control" required/>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">
                            <h4>Main course</h4>
                            <div class="form-group">
                                <select class="form-control" required>
                                    <option>Main course 1</option>
                                    <option>Main course 2</option>
                                    <option>Main course 3</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-success" data-role="add-food"><i class="glyphicon glyphicon-plus"></i>&emsp;Add</button>
                        </div>
                        <div class="col-xs-4">
                            <h4>Side dishes</h4>
                            <div class="form-group">
                                <select class="form-control" required>
                                    <option>Side dishes 1</option>
                                    <option>Side dishes 2</option>
                                    <option>Side dishes 3</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-success" data-role="add-food"><i class="glyphicon glyphicon-plus"></i>&emsp;Add</button>
                        </div>
                        <div class="col-xs-4">
                            <h4>Drink</h4>
                            <div class="form-group">
                                <select class="form-control" required>
                                    <option>Drink 1</option>
                                    <option>Drink 2</option>
                                    <option>Drink 3</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-success" data-role="add-food"><i class="glyphicon glyphicon-plus"></i>&emsp;Add</button>
                        </div>
                    </div>
                    <br />
                    <button type="submit" class="btn btn-block btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            $('button[data-role=add-food]').click(function() {
                $this = $(this);
                inputFoods = $('input[name=foods]').focus();
                if (inputFoods.val() == '') {
                    inputFoods.val($this.prev().find('select').val());
                } else {
                    inputFoods.val(inputFoods.val() + ',' + $this.prev().find('select').val());
                }
            });
        })

    </script>
</body>

</html>
