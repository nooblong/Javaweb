package nooblong.servlet.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import nooblong.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/FindEmailServlet")
public class FindEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get email address
        String email = request.getParameter("email");
        //use service find email
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean hasEmail;
        hasEmail = userDaoImpl.findUserByEmail(email) != null;

        Map<String, Object> map = new HashMap<>();

        if (hasEmail) {
            map.put("emailExist", true);
            map.put("msg", "邮箱被使用");
        } else {
            map.put("emailExist", false);
            map.put("msg", "邮箱可用");
        }
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
