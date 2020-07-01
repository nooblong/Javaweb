
$(function () {
    $.get("header.html", function (data) {
        $("#header").html(data);
    })

    $.get("GetUserServlet",{},function (data) {
        var username = data.username;
        $("#login_header").html(username);
        $("#login_header")[0].href = "PersonInfoServlet"

        $("#register_header").html("注销");
        $("#register_header")[0].href = "ExitServlet";
    })
})
