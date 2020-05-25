package backend.controllers;


import backend.models.Movie;
import backend.services.MovieServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Api("movie")
@Path("/movie")
public class MovieController {

    @EJB
    private MovieServiceInterface service;

    @GET
    @ApiOperation(
            value = "Finds all movies",
            response = Movie.class,
            responseContainer = "List")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        return ok(service.getMovies()).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Finds movie with given id",
            response = Movie.class)
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
    @ApiOperation(
            value = "Finds movies for user given by name",
            response = Movie.class,
            responseContainer = "List")
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
    @ApiOperation(
            value = "Finds movies with matching title",
            response = Movie.class,
            responseContainer = "List")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByTitle(@QueryParam("title") String title) {
        return ok(service.getMoviesByTitle(title)).build();
    }


    @POST
    @ApiOperation(
            value = "Adds movie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        service.addMovie(movie);
        return ok().build();
    }

    @POST
    @Path("/add/{name}")
    @ApiOperation(
            value = "Adds movie to specified user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieToUser(Movie movie, @PathParam("name") String name) {
        service.addMovieToUser(movie, name);
        return ok().build();
    }

    @PUT
    @ApiOperation(
            value = "Edits movie.")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editMovie(Movie movie) {
        service.editMovie(movie);
        return ok().build();
    }

    @PUT
    @Path("/uri")
    @ApiOperation(
            value = "Edits movie url.")
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
