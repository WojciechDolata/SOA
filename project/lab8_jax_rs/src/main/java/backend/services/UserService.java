package backend.services;

import backend.models.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    public User getUserByName(String name) {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.movies m WHERE u.name = :id", User.class)
                .setParameter("id", name)
                .getSingleResult();
    }

    @Override
    public void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void addAll(List<User> users) {
        em.getTransaction().begin();
        users.forEach(item -> em.persist(item));
        em.getTransaction().commit();
    }

    @Override
    public void editUser(User user) {
        User usr = getUserById(user.getUser_id());
        usr.setAge(user.getAge());
        usr.setName(user.getName());
        usr.setImage_uri(user.getImage_uri());
        em.getTransaction().begin();
        em.persist(usr);
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(Integer id) {
        em.getTransaction().begin();
        em.remove(getUserById(id));
        em.getTransaction().commit();
    }
}
