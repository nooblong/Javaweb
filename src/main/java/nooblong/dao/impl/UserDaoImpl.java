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
     * 根据邮箱查找用户
     * @param email 邮箱
     * @return user包含全部数据
     */
    @Override
    public User findUserByEmail(String email) {
        try {
            //language=MySQL
            String sql = "select * from member where email = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    email);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 登录方法
     * 查找用户名用户并返回
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
        String sql = "insert into member (username, password, city, address, tel, email, code) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(),
                user.getCity(), user.getAddress(), user.getTel(), user.getEmail(), user.getCode());
        System.out.println("save register user successfully");
    }

    /**
     * 通过激活码查询用户
     * @param code
     * @return
     */
    @Override
    public User findUserByCode(String code) {
        try {
            //language=MySQL
            String sql = "select * from member where code = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    code);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 更新用户状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        try {
            //language=MySQL
            String sql = "update member set freeze = 0 where id = ?";
            template.update(sql, user.getId());

        } catch (DataAccessException ignored) {
        }
    }
}
