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
     * @param username 用户名
     * @return user包含全部数据
     */
    @Override
    public User findUserByUserName(String username) {
        try {
            //language=MySQL
            String sql = "select * from member where username = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    username);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     *
     * 保存User
     * @param user
     * @return null
     */
    @Override
    public void save(User user) {

        //language=MySQL
        String sql = "insert into member (username, password, city, address, tel, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(),
                user.getCity(), user.getAddress(), user.getTel(), user.getEmail());
        System.out.println("save register user successfully");
    }
}
