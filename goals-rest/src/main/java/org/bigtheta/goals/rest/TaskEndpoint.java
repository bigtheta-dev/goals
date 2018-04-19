package org.bigtheta.goals.rest;

import org.bigtheta.goals.repo.Repo;
import org.bigtheta.goals.core.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/task")
public class TaskEndpoint {

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        return new JsonConverter().marchall(new Repo().list());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@PathParam("id") String id) {
        return new JsonConverter().marchall(new Repo().read(id));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") String id) {
        return new JsonConverter().marchall(new Repo().delete(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(String input) {
        Task task = new JsonConverter().unmarchall(input);
        return new JsonConverter().marchall(new Repo().create(task));
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(String input) {
        Task task = new JsonConverter().unmarchall(input);
        return new JsonConverter().marchall(new Repo().update(task));
    }

}
