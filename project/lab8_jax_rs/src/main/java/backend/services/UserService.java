package backend.services;

import backend.models.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public class UserService extends BasicService implements UserServiceInterface {

    @Override
    public User getUserById(int id) {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.movies m WHERE u.user_id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.movies", User.class)
                .getResultList();
    }

    @Override
    public void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void editUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(Integer id) {
        em.getTransaction().begin();
        em.remove(getUserById(id));
        em.getTransaction().commit();
    }
}
