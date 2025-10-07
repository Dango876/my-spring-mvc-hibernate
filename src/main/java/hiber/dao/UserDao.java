package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    List<User> getAllUser();

    User getUserById(Long id);

    void delete(Long id);
}
