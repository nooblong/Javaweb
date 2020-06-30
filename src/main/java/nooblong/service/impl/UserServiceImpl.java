package nooblong.service.impl;

import nooblong.dao.UserDao;
import nooblong.dao.impl.UserDaoImpl;
import nooblong.domain.User;
import nooblong.service.UserService;

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
        //查询数据库是否有这个人
        User u = userDao.findUserByUserName(user.getUsername());
        if (u == null)
            //如果没有，保存这个人
            userDao.save(user);
        else
            //如果有，返回false
            return false;
        return true;
    }
}
