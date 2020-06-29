package nooblong.dao;

import nooblong.domain.User;

public interface UserDao {
    public User login(User loginUser);
    public User register(User registerUser);
    public boolean hasUsername(String username);
}
