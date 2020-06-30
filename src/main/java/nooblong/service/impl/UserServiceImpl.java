package nooblong.service.impl;

import nooblong.dao.UserDao;
import nooblong.dao.impl.UserDaoImpl;
import nooblong.domain.User;
import nooblong.service.UserService;
import nooblong.utils.MailUtils;
import nooblong.utils.UUIDUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public Boolean regist(User user) {
        //查询m名字是否存在
        User u = userDao.findUserByUserName(user.getUsername());
        //查询邮箱是否存在
        User m = userDao.findUserByEmail(user.getEmail());
        if (u == null && m == null) {
            //如果没有，保存这个人
            //设置激活码
            String code = UUIDUtil.getUUID();
            user.setCode(code);
            userDao.save(user);

            //发送邮件
//            String content = "<a href='http://localhost/Javaweb/ActiveUserServlet?code="+code+">点击激活</a>";
            String content = "http://localhost:8080/Javaweb/ActiveUserServlet?code=" + code;
            MailUtils.sendMail(user.getEmail(), content, "激活");
        } else
            //如果有，返回false
            return false;
        return true;
    }

    @Override
    public boolean active(String code) {
        User user = userDao.findUserByCode(code);
        if (user != null) {
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    /**
     * 登录
     *
     * @param user 包含用户名和密码
     * @return 完整的User
     */
    @Override
    public User login(User user) {
        User userByUserName = userDao.findUserByUserName(user.getUsername());
        //有这用户且检测密码是正确
        if (userByUserName != null && user.getPassword().equals(userByUserName.getPassword())) {
            return userByUserName;
        } else
            return null;
    }
}
