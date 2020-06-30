package nooblong.dao;

import nooblong.domain.User;

public interface UserDao {
    User findUserByEmail(String email);
    User findUserByUserName(String username);
    void save(User registerUser);
    User findUserByCode(String code);
    void updateStatus(User user);
}
