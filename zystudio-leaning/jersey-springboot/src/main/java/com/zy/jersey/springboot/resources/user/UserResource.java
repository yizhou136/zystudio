package com.zy.jersey.springboot.resources.user;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

/**
 * @author by zy.
 */
@Singleton
@Path("/user/{username}")
public interface UserResource {

    @GET
    @Produces("application/json")
    String userName(@PathParam("username") String un);


    @GET
    @Path("/{make}/{model}/{year}")
    @Produces("image/jpeg")
    String getPicture(@PathParam("make") String make,
                      @PathParam("model") PathSegment car,
                      @PathParam("year") String year,
                      @Context UriInfo info);

    @GET
    @Path("/{id}")
    @Produces("application/json")
    User getCustomer(@PathParam("id") int id);
}
