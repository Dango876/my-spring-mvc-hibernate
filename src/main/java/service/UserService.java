package service;

import java.util.List;

import model.User;

public interface UserService {
    void creatUser(User user);

    void updateUser(User user);

    List<User> findAllUser();

    User findUserById(Long id);

    void deleteUser(Long id);
}
