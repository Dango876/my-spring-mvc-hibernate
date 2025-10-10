package dao;

import java.util.List;

import model.User;


public interface UserDao {
    void createUser(User user);

    void updateUsers(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    void deleteUser(Long id);
}
