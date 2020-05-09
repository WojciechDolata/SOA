package services;


import lombok.Getter;
import lombok.Setter;
import models.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "LoginBean")
@ViewScoped
@Getter
@Setter
public class LoginBean implements Serializable {
    private String name;

    private String password;

    @ManagedProperty(value="#{UserManager}")
    private UserManager userManager;

    @ManagedProperty(value="#{SessionBean}")
    private SessionBean sessionBean;

    public LoginBean() {
    }

    public String login() {
        User user = userManager.login(name, password);
        if (user != null) {
            sessionBean.setUser(user);
            return "dodawanie";
        }
        return "login";
    }

    public String register() {
        userManager.addUser(new User(name, password));
        return login();
    }

}
