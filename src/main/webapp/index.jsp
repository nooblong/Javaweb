<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<style>
    body {
        min-height: 2000px;
        padding-top: 70px;
    }

    img {
        height: 150px;
        width: 100px;
    }

    #book {
        border-style: solid;
        margin: 0px;
    }

</style>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Index</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<script>
    $(function () {
        $("index").onclick = function () {
            location.reload();
        }
    })


</script>

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">购书系统</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a id="index" href="javascript:void(0)">Home</a></li>
                <li><a href="#about">购物车</a></li>
                <li><a href="#contact">订单</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">分类<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%--                <li><a href="../navbar/">Default</a></li>--%>
                <%--                <li><a href="../navbar-static-top/">Static top</a></li>--%>
                <li class="active"><a href="register.jsp">Register<span class="sr-only">(current)</span></a></li>
                <li class="active"><a href="login.jsp">Login<span class="sr-only">(current)</span></a></li>
            </ul>
        </div><!--/.nav-collapse -->

    </div>
</nav>

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron" id="allBook">
        <h1 id="bookStart">书本推荐</h1>
        <%--                each book--%>


    </div>

</div> <!-- /container -->

<script>
    $(function () {
        // function fun() {
        $.ajax({
            url: "/Javaweb/IndexBookServlet",
            type: "POST",
            async: "false",
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.length; i +=3) {
                    $("[id=bookStart]").append(

                        "<div class=\"row\">\n" +
                        "        <div class=\"col-lg-4\">\n" +
                        "          <h2>"+data[i].name+"</h2>\n" +
                        "          <p class=\"text-danger\">"+data[i].introduce+"</p>\n" +
                        "          <p>"+"<img src='img/smoke.jpg' '>"+"</p>\n" +
                        "          <p><a class=\"btn btn-primary\" href=\"#\" role=\"button\">View details &raquo;</a></p>\n" +
                        "        </div>\n" +
                        "        <div class=\"col-lg-4\">\n" +
                        "          <h2>"+data[i+1].name+"</h2>\n" +
                        "          <p class=\"text-danger\">"+data[i+1].introduce+"</p>\n" +

                        "          <p>"+"<img src='img/smoke.jpg' '>"+"</p>\n" +
                        "          <p><a class=\"btn btn-primary\" href=\"#\" role=\"button\">View details &raquo;</a></p>\n" +
                        "       </div>\n" +
                        "        <div class=\"col-lg-4\">\n" +
                        "          <h2>"+data[i+2].name+"</h2>\n" +
                        "          <p class=\"text-danger\">"+data[i+2].introduce+"</p>\n" +

                        "          <p>"+"<img src='img/smoke.jpg' '>"+"</p>\n" +
                        "          <p><a class=\"btn btn-primary\" href=\"#\" role=\"button\">View details &raquo;</a></p>\n" +
                        "        </div>\n" +
                        "      </div>"

                    );
                }
            }
        })
        // }
    })
</script>
</body>

</html>