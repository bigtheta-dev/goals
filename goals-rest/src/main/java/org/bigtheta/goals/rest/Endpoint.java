package org.bigtheta.goals.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Endpoint {
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "result";
    }
}
