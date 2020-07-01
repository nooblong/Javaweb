package nooblong.servlet.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import nooblong.domain.Book;
import nooblong.domain.Type;
import nooblong.service.BookService;
import nooblong.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

@WebServlet("/SingleBookServlet")
public class SingleBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.getBookById(id);

        //给book的类别替换成文字
        String type = book.getType();
        try {
            Field field = Type.class.getField(type);
            String typeStr = (String)field.get(type);
            book.setType(typeStr);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(), book);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
