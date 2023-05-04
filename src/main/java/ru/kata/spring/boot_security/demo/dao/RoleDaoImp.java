package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void removeRole(Long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String role) {
        try {
            return entityManager.createQuery("from Role where role=:role", Role.class).setParameter("role", role).getSingleResult();
        } catch (Exception e) {
            return getRole(2L);
        }
    }

    @Override
    public List<Role> getListRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }
}
