<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form action="LoginServlet" method="post" class="center-block well" style="max-width: 500px; margin-top: 50px">
                    <h1 class="text-center">Customer Login</h1>
                    <span class="text-success">${ReminderMessage}</span>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="name" class="form-control" value="james" required />
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="psw" class="form-control" value="321" required />
                    </div>
                    <br />
                    <span class="text-danger">${ErrorMessage}</span>
                    <button type="submit" class="btn btn-block btn-primary">Login</button>
                    <br />
                    <div class="text-right">
                        <a href="register.jsp" class="btn btn-link">Register!</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>

</html>
