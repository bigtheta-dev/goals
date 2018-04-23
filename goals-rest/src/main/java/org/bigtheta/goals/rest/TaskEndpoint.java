package org.bigtheta.goals.rest;

import org.bigtheta.goals.core.JsonConverter;
import org.bigtheta.goals.repo.Repo;
import org.bigtheta.goals.repo.impl.inMemory.InMemoryRepo;
import org.bigtheta.goals.core.Task;
import org.bigtheta.goals.repo.impl.postgres.PgRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/task")
public class TaskEndpoint {

    private final Repo repo;

    public TaskEndpoint() {
        repo = new PgRepo();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        return new JsonConverter().marchall(repo.list());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@PathParam("id") String id) {
        return new JsonConverter().marchall(repo.read(id));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") String id) {
        return new JsonConverter().marchall(repo.delete(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(String input) {
        Task task = new JsonConverter().unmarchall(input);
        return new JsonConverter().marchall(repo.create(task));
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(String input) {
        Task task = new JsonConverter().unmarchall(input);
        return new JsonConverter().marchall(repo.update(task));
    }

}
