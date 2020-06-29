package nooblong.dao.impl;

import nooblong.dao.UserDao;
import nooblong.domain.User;
import nooblong.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     *
     * @param loginUser 只有用户名和密码
     * @return user包含全部数据
     */
    @Override
    public User login(User loginUser) {
        try {
            //language=MySQL
            String sql = "select * from member where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 注册
     * @param registerUser
     * @return null
     */
    @Override
    public User register(User registerUser){

        //language=MySQL
        String sql = "insert into member (username, password, city, address, tel, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, registerUser.getUsername(),registerUser.getPassword(),
                registerUser.getCity(),registerUser.getAddress(),registerUser.getTel(),registerUser.getEmail());
        return null;
    }

    /**
     * 是否存在username
     *
     * @param username
     * @return
     */
    @Override
    public boolean hasUsername(String username) {
        //language=MySQL
        String sql = "select count(*) from member where username = ?";
//        调用方法获得记录数
        int count = template.queryForObject(sql, Integer.class, username);
        if (count > 0)
            return true;
        else
            return false;
    }
}
