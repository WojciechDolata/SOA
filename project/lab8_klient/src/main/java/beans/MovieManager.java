package beans;


import lombok.Getter;
import lombok.Setter;
import models.Movie;
import models.User;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "MovieManager")
@SessionScoped
@Getter
@Setter
public class MovieManager implements Serializable {
    private String basicUrl = "http://localhost:8080/lab8_jax_rs-1.0-SNAPSHOT/myapp";
    private List<Movie> movies = new LinkedList<>();
    private List<Movie> selectedMovies = new ArrayList<>();

    public MovieManager() {
        fetchMovies();
    }

    public String addMovieForUser(Movie movie, User user) {
        Client client = ResteasyClientBuilderImpl.newClient();
        var target = client.target(basicUrl+"/movie/add/" + user.getName());
        var response = target.request().post(Entity.entity(movie, MediaType.APPLICATION_JSON));
        return "users";
    }

    public String fetchMovies() {
        Client client = ResteasyClientBuilderImpl.newClient();
        var target = client.target(basicUrl+"/movie");
        var response = target.request().get();
        var list = response.readEntity(new GenericType<List<Movie>>() {});
        movies = new LinkedList<>();
        movies.addAll(list);
        return "index";
    }

    public String deleteMovie(Movie movie) {
        Client client = ResteasyClientBuilderImpl.newClient();
        var target = client.target(basicUrl+"/movie/" + movie.getMovie_id());
        var response = target.request().delete();
        return "index";
    }

    public String addMovie(Movie movie) {
        Client client = ResteasyClientBuilderImpl.newClient();
        var target = client.target(basicUrl+"/movie");
//        target.queryParam("Name")
        var response = target.request().post(Entity.entity(movie, MediaType.APPLICATION_JSON));
        return "index";
    }
}
