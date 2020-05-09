package services;

import lombok.Getter;
import lombok.Setter;
import models.User;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "UserManager")
@ApplicationScoped
@Getter
@Setter
public class UserManager implements Serializable {
    private List<User> users = new LinkedList<User>();

    public UserManager() {
        users.add(new User("admin", "admin1"));
    }

    public User login(String login, String password) {
        List<User> list = users.stream()
                .filter((user) -> user.getNick().equals(login) && user.getPasswordHash().equals(password.hashCode()))
                .collect(Collectors.toList());

        return list.isEmpty() ? null : list.get(0);
    }

    public void addUser(User user) {
        if(!users.contains(user))
            users.add(user);
    }

}
