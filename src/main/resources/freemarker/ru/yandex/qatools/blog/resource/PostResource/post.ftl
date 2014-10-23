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
        <div class="row panel panel-default">
            <div class=" panel-heading">
                <ul class="list-inline">
                    <li><h4>${model.title}</42></li>
                    <li><a href="/post/user/${model.user}">${model.user}</a></li>
                </ul>
            </div>
            <div class="panel-body">${model.text}</div>
        </div>

        <div class="row">
            <form class="form" role="form" action="/post/${model.id}/comment" method='POST'>
                <div class="form-group">
                    <label class="sr-only" for="title">Title</label>
                    <input type='text' name='title' value='' placeholder="Title">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="text">Text</label>
                    <textarea name='text' value='' placeholder="Text"></textarea>
                </div>
                <button type="submit" class="btn btn-default">Add comment</button>
            </form>
        </div>


        <#if model.comments??>
        <div class="row">
            <h2>Comments</h2>
            <#list model.comments as comment>
               <div>
                   <ul class="list-inline">
                       <li><div><h4>${comment.title}<h4></div></li>
                       <li><a href="/post/user/${comment.user}">${comment.user}</a></li>
                   </ul>

                    <div>${comment.text}</div>
               </div>
               <hr/>
            </#list>
        </div>
        </#if>
    </div>
</body>
</html>