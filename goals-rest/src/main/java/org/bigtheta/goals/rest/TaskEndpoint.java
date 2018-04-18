package org.bigtheta.goals.rest;

import org.bigtheta.goals.core.Processor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/task")
public class TaskEndpoint {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        return new JsonConverter().marchall(new Processor().list());
    }

    @GET
    @Path("fst")
    @Produces(MediaType.APPLICATION_JSON)
    public String fst() {
        return new JsonConverter().marchall(new Processor().fst());
    }

    @GET
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "welcome!";
    }
}
