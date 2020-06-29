package nooblong.servlet.login;

import nooblong.dao.impl.UserDaoImpl;
import nooblong.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (!userDaoImpl.hasUsername(user.getUsername())){
            //二次检验是否有此用户名
            userDaoImpl.register(user);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/SuccessServlet").forward(request, response);
        }else {
            request.getRequestDispatcher("/FailServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
