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

@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get username
        String username = request.getParameter("username");
        //use service find user
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean hasUsername;
        hasUsername = userDaoImpl.findUserByUserName(username) != null;

        Map<String, Object> map = new HashMap<>();

        if (hasUsername) {
            map.put("userExist", true);
            map.put("msg", "用户名被使用");
        } else {
            map.put("userExist", false);
            map.put("msg", "用户名可用");
        }
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
