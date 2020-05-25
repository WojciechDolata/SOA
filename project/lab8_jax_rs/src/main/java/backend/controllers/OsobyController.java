package backend.controllers;

import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/osoby")
public class OsobyController {

    @Context
    UriInfo uri;

    @GET
    public Response redirectToUserController() throws URISyntaxException {
        return Response.temporaryRedirect(new URI(uri.getRequestUri().toString().replace("osoby", "user"))).build();
    }
}
