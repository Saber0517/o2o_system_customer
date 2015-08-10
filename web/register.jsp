<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">

                <form action="SignInServlet" method="post" class="center-block well" style="max-width: 500px; margin-top: 50px" enctype="multipart/form-data">
                    <h1 class="text-center">Customer Register</h1>
                    <span class="text-danger">${ErrorMessage}</span>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="name" class="form-control" required value="james" />
                        <span class="text-danger">${verifyParameter.name}</span>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="psw" class="form-control" required value="123456789"/>
                        <span class="text-danger">${verifyParameter.pwd}</span>
                    </div>
                    <div class="form-group">
                        <label>Confirm Password</label>
                        <input type="password" name="psw" class="form-control" required value="123456789"/>
                    </div>
                    <div class="form-group">
                        <label>Tel</label>
                        <input type="tel" name="tel" class="form-control" required value="2239823"/>
                        <span class="text-danger">${verifyParameter.tel}</span>
                    </div>
                    <div class="form-group">
                        <label>Id Card</label>
                        <input type="text" name="idCard" class="form-control" required value="48646"/>
                    </div>
                    <div class="form-group">
                        <label>License</label>
                        <input type="file" name="license" class="form-control" required />
                    </div>
                    <br />
                    <span class="text-danger">${ErrorMessage}</span>
                    <button type="submit" class="btn btn-block btn-success">Register</button>
                    <br />
                    <div class="text-right">
                        <a href="login.jsp" class="btn btn-link">Canel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>

</html>
