package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void updateUser(User user, Long id) {
        User userOld = entityManager.find(User.class, id);
        userOld.setFirstName(user.getFirstName());
        userOld.setLastName(user.getLastName());
        userOld.setAge(user.getAge());
        userOld.setEmail(user.getEmail());
        userOld.setUsername(user.getUsername());
        if (user.getPassword() != null) {
            userOld.setPassword(user.getPassword());
        }
        userOld.setRoles(user.getRoles());
    }

    @Override
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return entityManager.createQuery("select u from User u where u.username= :username", User.class).setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
    }
}
