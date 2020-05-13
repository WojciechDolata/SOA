package backend.controllers;

import backend.models.User;
import backend.services.UserServiceInterface;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("/user")
public class UserController {

    @EJB
    private UserServiceInterface service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return ok(service.getUsers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") String id) {
        try {
            return ok(service.getUserById(Integer.parseInt(id))).build();
        } catch (Exception ex) {
            return Response.status(404, "User not found.").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@QueryParam("name") String name, @QueryParam("age") String age, @QueryParam("img") String imageUri) {
        service.addUser(new User(name, Integer.parseInt(age), imageUri));
        return ok().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUser(@QueryParam("name") String name, @QueryParam("age") String age, @QueryParam("img") String imageUri) {
        service.editUser(new User(name, Integer.parseInt(age), imageUri));
        return ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id) {
        service.deleteUser(Integer.parseInt(id));
        return ok().build();
    }
}
