package nooblong.dao;

import nooblong.domain.User;

public interface UserDao {
    public User findUserByUserName(String username);
    public void save(User registerUser);
}
