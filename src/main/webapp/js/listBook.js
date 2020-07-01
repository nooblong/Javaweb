function listBook(data) {
    var book_html = $("[id=bookStart]");
// if (data.length < 3) {
    var tmp = 0;
    for (var i = 0; i < data.length; i++) {
        if (tmp % 3 === 0) {
            book_html.append(
                "<div class='row'></div>"
            );
        }
        book_html.append(
            "        <div class=\"col-lg-4\">\n" +
            "          <h2>" + data[i].name + "</h2>\n" +
            "          <p class=\"text-danger\">" + data[i].introduce + "</p>\n" +
            "          <p>" + "<img src='img/smoke.jpg' '>" + "</p>\n" +
            "          <p><a class=\"btn btn-primary\" href=\"#\" role=\"button\">View details &raquo;</a></p>\n" +
            "        </div>\n");
        tmp++;
        // }
        // } else {
        //     for (var i = 0; i < data.length; i += 3) {
        //         $("[id=bookStart]").append(
        //             "<div class=\"row\">\n" +
        //             "        <div class=\"col-lg-4\">\n" +
        //             "          <h2>" + data[i].name + "</h2>\n" +
        //             "          <p class=\"text-danger\">" + data[i].introduce + "</p>\n" +
        //             "          <p>" + "<img src='img/smoke.jpg' '>" + "</p>\n" +
        //             "          <p><a class=\"btn btn-primary\" href=\"#\" role=\"button\">View details &raquo;</a></p>\n" +
        //             "        </div>\n" +
        //             "        <div class=\"col-lg-4\">\n" +
        //             "          <h2>" + data[i + 1].name + "</h2>\n" +
        //             "          <p class=\"text-danger\">" + data[i + 1].introduce + "</p>\n" +
        //
        //             "          <p>" + "<img src='img/smoke.jpg' '>" + "</p>\n" +
        //             "          <p><a class=\"btn btn-primary\" href=\"#\" role=\"button\">View details &raquo;</a></p>\n" +
        //             "       </div>\n" +
        //             "        <div class=\"col-lg-4\">\n" +
        //             "          <h2>" + data[i + 2].name + "</h2>\n" +
        //             "          <p class=\"text-danger\">" + data[i + 2].introduce + "</p>\n" +
        //
        //             "          <p>" + "<img src='img/smoke.jpg' '>" + "</p>\n" +
        //             "          <p><a class=\"btn btn-primary\" href=\"#\" role=\"button\">View details &raquo;</a></p>\n" +
        //             "        </div>\n" +
        //             "      </div>"
        //         );
        //     }
    }
    book_html.append(
        "<div class='row'></div>"
    );
}