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
        <div>
            <h2>Posts</h2>
            <#list model as post>
                <a href="/post/${post.id}">${post.title}</a> <br/>
            </#list>
        </div>
    </div>
</body>
</html>


