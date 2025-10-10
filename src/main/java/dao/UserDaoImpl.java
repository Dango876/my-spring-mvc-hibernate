package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String GET_ALL_USER_QUERY = "SELECT u FROM User u";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUsers(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> findAllUsers() {
//        String jpql = GET_ALL_USER_QUERY;
        return entityManager.createQuery(GET_ALL_USER_QUERY, User.class).getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
