package nooblong.servlet.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import nooblong.dao.impl.UserDaoImpl;
import nooblong.domain.Book;
import nooblong.domain.ResultInfo;
import nooblong.domain.User;
import nooblong.service.BookService;
import nooblong.service.UserService;
import nooblong.service.impl.BookServiceImpl;
import nooblong.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        Book book = new Book();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(book, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service完成添加
        boolean flag = bookService.addBook(book);
        ResultInfo resultInfo = new ResultInfo();
        if (flag) {
            //成功
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("添加成功");
        } else {
            //失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("添加失败");
        }

        //将resultInfo序列化成json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        //通过response发送
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
