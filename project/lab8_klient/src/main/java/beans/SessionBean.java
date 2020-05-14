package beans;


import lombok.Getter;
import lombok.Setter;
import models.Movie;
import models.User;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "Session")
@SessionScoped
@Getter
@Setter
public class SessionBean implements Serializable {
    private String basicUrl = "http://localhost:8080/lab8_jax_rs-1.0-SNAPSHOT/myapp";

//    @ManagedProperty(value="#{User}")
    private User user;

    private List<Movie> movies;

    public SessionBean() {
    }

    public String addUser(User user) {
        Client client = ResteasyClientBuilderImpl.newClient();
        var target = client.target(basicUrl+"/user");
        var response = target.request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
        this.user = user;
        return "users";
    }

    public void selectUser(User user) {
        this.user = user;
        getDataForUser();
    }

    public String getDataForUser() {
        Client client = ResteasyClientBuilderImpl.newClient();
        var target = client.target(basicUrl+"/movie/byUser/" + user.getName());
        var response = target.request().get();
        try {
            var a = response.readEntity(new GenericType<List<Movie>>() {});
            movies = a;
        } catch (Exception ex) {
            movies = new LinkedList<>();
        }
        return "users";
    }


}
