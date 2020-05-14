import models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("movie")
public interface MovieService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<User> getAll();

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    User getUserById(@PathParam("id") String id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    String getQueryParam(@QueryParam("name") String name, @QueryParam("age") String age, @QueryParam("img") String imageUri);

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void putCollection(@PathParam("id") String id, @QueryParam("name") String name, @QueryParam("age") String age, @QueryParam("img") String imageUri);


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteUser(@PathParam("id") String id);
}
