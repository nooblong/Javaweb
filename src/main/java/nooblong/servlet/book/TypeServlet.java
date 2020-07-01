package nooblong.servlet.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import nooblong.dao.impl.BookDaoImpl;
import nooblong.domain.Book;
import nooblong.service.BookService;
import nooblong.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();

        //获取分类
        String type = request.getParameter("type");

        System.out.println(type);

        //分类下所有书的列表
        List<Book> books = bookService.getBooks(type);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(), books);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
