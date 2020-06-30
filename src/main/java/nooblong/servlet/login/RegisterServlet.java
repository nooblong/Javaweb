package nooblong.servlet.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import nooblong.dao.impl.UserDaoImpl;
import nooblong.domain.ResultInfo;
import nooblong.domain.User;
import nooblong.service.UserService;
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

        //调用service完成注册
        UserService userService = new UserServiceImpl();
        Boolean flag = userService.regist(user);
        ResultInfo resultInfo = new ResultInfo();
        if (flag) {
            //注册成功
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("注册成功");
        } else {
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
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
