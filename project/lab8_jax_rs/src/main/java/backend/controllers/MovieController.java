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
    public Response getMovieById(@PathParam("id") String id) {
        try {
            return ok(service.getMovieById(Integer.parseInt(id))).build();
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
    public Response addMovie(@QueryParam("title") String title, @QueryParam("uri") String uri) {
        service.addMovie(new Movie(title, uri));
        return ok().build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editMovie(@PathParam("id") Integer id, @QueryParam("title") String title, @QueryParam("uri") String uri) {
        service.editMovie(new Movie(id, title, uri));
        return ok().build();
    }

    @PUT
    @Path("/{id}/uri")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editMovieUri(@PathParam("id") String id, @QueryParam("uri") String uri) {
        service.editMovieUri(Integer.parseInt(id), uri);
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
