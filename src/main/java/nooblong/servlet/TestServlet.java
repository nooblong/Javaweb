package nooblong.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.getWriter().write("<h1>hello world</h1>");
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
