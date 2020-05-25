package backend.controllers;

import backend.models.Movie;
import backend.models.User;
import backend.services.UserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.Response.ok;


@Api("user")
@Path("/user")
public class UserController {

    @EJB
    private UserServiceInterface service;

    @GET
    @ApiOperation(
            value = "Finds all users.",
            response = User.class,
            responseContainer = "List")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return ok(service.getUsers()).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Finds user with given id",
            response = User.class)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") String id) {
        try {
            return ok(service.getUserById(Integer.parseInt(id))).build();
        } catch (Exception ex) {
            return Response.status(404, "User not found.").build();
        }
    }

    @POST
    @ApiOperation(
            value = "Adds user.")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        service.addUser(user);
        return ok().build();
    }

    @PUT
    @ApiOperation(
            value = "Updates user.")
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(List<User> users) {
        service.addAll(users);
        return ok().build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(
            value = "Deletes user.")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id) {
        service.deleteUser(Integer.parseInt(id));
        return ok().build();
    }
}
