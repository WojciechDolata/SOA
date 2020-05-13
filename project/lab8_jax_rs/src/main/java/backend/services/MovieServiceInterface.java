package backend.services;


import backend.models.Movie;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public interface MovieServiceInterface {

    Movie getMovieById(int id);
    List<Movie> getMoviesByTitle(String title);
    List<Movie> getMovies();
    void addMovie(Movie movie);
    void editMovie(Movie movie);
    void editMovieUri(Integer id, String uri);
    void deleteMovie(Integer id);

}
