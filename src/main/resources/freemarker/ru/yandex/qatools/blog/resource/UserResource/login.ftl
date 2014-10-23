<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/application.css">
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form class="form-inline" role="form" name='f' action="j_spring_security_check" method='POST'>
            <div class="form-group">
                <label class="sr-only" for="j_username">User name</label>
                <input type='text' name='j_username' value='' placeholder="User name">
            </div>
            <div class="form-group">
                <label class="sr-only" for="j_username">User name</label>
                <input type='password' name='j_password' value='' placeholder="User name">
            </div>
            <button type="submit" class="btn btn-default">Sign in</button>
        </form>
        <div>
            <a href="/user/create">
                Create account
            </a>
        </div>
    </div>
</body>
</html>