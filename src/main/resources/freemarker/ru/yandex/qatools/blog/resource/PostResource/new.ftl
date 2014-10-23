<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/application.css">
</head>
<body>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid bgimg">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Switter</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/post/all">All</a></li>
                    <li><a href="/post/my">My</a></li>
                    <li><a href="/post/new">Create</a></li>
                    <li><a href="/j_spring_security_logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <form class="form" role="form" action="/post" method="post">
            <div class="form-group">
                <label class="sr-only" for="title">Title</label>
                <input type='text' name='title' value='' placeholder="Title">
            </div>
            <div class="form-group">
                <label class="sr-only" for="text">Text</label>
                <textarea name='text' value='' placeholder="Text"></textarea>
            </div>
            <button type="submit" class="btn btn-default">Post</button>
        </form>
    </div>
</body>
</html>