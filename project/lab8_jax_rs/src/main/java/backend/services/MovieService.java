package backend.services;

import backend.models.Movie;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote
public class MovieService extends BasicService implements MovieServiceInterface {

    @EJB
    UserServiceInterface userService;

    @Override
    public Movie getMovieById(int id) {
        return em.createQuery("SELECT m FROM Movie m LEFT JOIN FETCH m.users u WHERE m.movie_id = :id", Movie.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Movie> getMoviesByTitle(String title) {
        return em.createQuery("SELECT m FROM Movie m LEFT JOIN FETCH m.users u WHERE m.title = :title", Movie.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Movie> getMovies() {
        return em.createQuery("SELECT m FROM Movie m LEFT JOIN FETCH m.users", Movie.class)
                .getResultList();
    }

    @Override
    public void addMovieToUser(Movie movie, String name) {
        em.getTransaction().begin();
        var user = userService.getUserByName(name);
        var movieDB = getMoviesByTitle(movie.getTitle()).get(0);
        movieDB.getUsers().add(user);
        user.getMovies().add(movieDB);
        em.merge(user);
        em.merge(movieDB);
        em.getTransaction().commit();
    }

    @Override
    @Transactional
    public List<Movie> getMoviesByUser(String name) {
        return em.createQuery("SELECT m FROM Movie m LEFT JOIN FETCH m.users u where u.name = :name", Movie.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public void addMovie(Movie movie) {
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
    }

    @Override
    public void editMovieUri(Integer id, String uri) {
        em.getTransaction().begin();
        var movie = getMovieById(id);
        movie.setUri(uri);
        em.persist(movie);
        em.getTransaction().commit();
    }

    @Override
    public void editMovie(Movie movie) {
        Movie m = getMovieById(movie.getMovie_id());
        m.setUri(movie.getUri());
        m.setTitle(movie.getTitle());
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    @Override
    public void deleteMovie(Integer id) {
        em.getTransaction().begin();
        em.remove(getMovieById(id));
        em.getTransaction().commit();
    }
}
