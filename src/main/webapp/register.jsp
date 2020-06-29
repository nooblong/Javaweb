<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
    }

    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }

    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }

    .form-signin .checkbox {
        font-weight: normal;
    }

    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }

    .form-signin .form-control:focus {
        z-index: 2;
    }

    #email {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    #password, #city, #username, #address {
        margin-bottom: -1px;
        border-radius: 0;
    }

    #tel {
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        margin-bottom: 10px;
    }


</style>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Register</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<div class="container">

    <form class="form-signin" method="post" action="${pageContext.request.contextPath}/RegisterServlet">
        <h2 class="form-signin-heading">Register</h2>

        <label for="email" class="sr-only">Email</label>
        <input name="email" type="email" id="email" class="form-control" placeholder="Email" autofocus>

        <script>
            //是否存在该用户名
            $(function () {
                $("input[id=username]").blur(function () {
                    //get username
                    var username = $(this).val();
                    <%--为什么不能用${pageContext.request.contextPath}--%>
                    $.get("/Javaweb/FindUserServlet", {username: username}, function (data) {
                        //userExsit:true/false
                        if (data.userExsit) {
                            $("#username_s").html(data.msg).css("color","red");
                        } else {
                            $("#username_s").html(data.msg).css("color","green");
                        }
                    }, "json")
                })
            })
        </script>
        <label for="username" class="sr-only">Username</label>
        <input name="username" type="text" id="username" class="form-control text-nowrap" placeholder="Username" required autofocus>
        <span id="username_s"></span>

        <label for="password" class="sr-only">Password</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Password" required>

        <label for="city" class="sr-only">City</label>
        <input name="city" type="text" id="city" class="form-control" placeholder="City" autofocus>

        <label for="address" class="sr-only">Address</label>
        <input name="address" type="text" id="address" class="form-control" placeholder="Address" autofocus>

        <label for="tel" class="sr-only">Tel</label>
        <input name="tel" type="tel" id="tel" class="form-control" placeholder="Tel" autofocus>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="read"> I have read.
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form>

</div>

<script
        src="https://code.jquery.com/jquery-1.12.4.js"
        integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
        crossorigin="anonymous"></script>

</body>
</html>