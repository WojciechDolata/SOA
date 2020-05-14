package backend.services;


import backend.models.Movie;
import backend.models.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public interface UserServiceInterface {

    User getUserById(int id);

    User getUserByName(String name);

    List<User> getUsers();

    void addUser(User user);

    void addAll(List<User> users);

    void editUser(User user);

    void deleteUser(Integer id);
}
