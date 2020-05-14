package backend.controllers;


import backend.models.Movie;
import backend.services.MovieServiceInterface;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Path("/movie")
public class MovieController {

    @EJB
    private MovieServiceInterface service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        return ok(service.getMovies()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesById(@PathParam("id") String id) {
        try {
            return ok(service.getMovieById(Integer.parseInt(id))).build();
        } catch (Exception ex) {
            return Response.status(404, "Movie not found.").build();
        }
    }

    @GET
    @Path("/byUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByUser(@PathParam("id") String name) {
        try {
            var a = service.getMoviesByUser(name);
            return ok(a).build();
        } catch (Exception ex) {
            return Response.status(404, "Movie not found.").build();
        }
    }

    @GET
    @Path("/title")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByTitle(@QueryParam("title") String title) {
        return ok(service.getMoviesByTitle(title)).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        service.addMovie(movie);
        return ok().build();
    }

    @POST
    @Path("/add/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieToUser(Movie movie, @PathParam("name") String name) {
        service.addMovieToUser(movie, name);
        return ok().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editMovie(Movie movie) {
        service.editMovie(movie);
        return ok().build();
    }

    @PUT
    @Path("/uri")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editMovieUri(Movie movie) {
        service.editMovieUri(movie.getMovie_id(), movie.getUri());
        return ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") String id) {
        service.deleteMovie(Integer.parseInt(id));
        return ok().build();
    }
}
