package nooblong.service;

import nooblong.domain.User;

public interface UserService {
    Boolean regist(User user);
    boolean active(String code);
    User login(User user);
}
