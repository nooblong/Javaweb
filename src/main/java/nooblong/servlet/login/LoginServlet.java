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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //获取完整的User信息
        User loginUser = userService.login(user);

        //检查是否被冻结
        boolean isFreeze = true;
        if (loginUser != null)
            isFreeze = loginUser.getFreeze() == 1;

        ResultInfo resultInfo = new ResultInfo();

        if (loginUser == null) {
            //密码错误
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("账号或密码错误");
        } else {
            //密码正确
            resultInfo.setFlag(true);
            //检查是否被冻结
            if (!isFreeze){
                //没有被冻结
                //写入session
                request.getSession().setAttribute("user", loginUser);
            }else {
                //被冻结
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号被冻结/未激活");
            }
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
