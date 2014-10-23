<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/application.css">
</head>
<body>
    <div class="container">
        <h1>Create a user</h1>
        <form class="form-inline" role="form" action="/user" method='POST'>
            <div class="form-group">
                <label class="sr-only" for="name">User name</label>
                <input type='text' name='name' value='' placeholder="User name">
            </div>
            <div class="form-group">
                <label class="sr-only" for="password">User name</label>
                <input type='password' name='password' value='' placeholder="User name">
            </div>
            <button type="submit" class="btn btn-default">Sign up</button>
        </form>
    </div>
</body>
</html>