package org.bigtheta.goals.rest;

import org.bigtheta.goals.core.JsonConverter;
import org.bigtheta.goals.core.Task;
import org.bigtheta.goals.repo.Repo;
import org.bigtheta.goals.repo.impl.postgres.PgRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/task")
public class TaskEndpoint {

    private final Repo repo;

    public TaskEndpoint() {
        repo = new PgRepo();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        String entity = new JsonConverter().marchall(repo.list());
        return buildResponse(entity);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") String id) {
        String entity = new JsonConverter().marchall(repo.read(id));
        return buildResponse(entity);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String id) {
        String entity = new JsonConverter().marchall(repo.delete(id));
        return buildResponse(entity);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String input) {
        Task task = new JsonConverter().unmarchall(input);
        String entity = new JsonConverter().marchall(repo.create(task));
        return buildResponse(entity);
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String input) {
        Task task = new JsonConverter().unmarchall(input);
        String entity = new JsonConverter().marchall(repo.update(task));
        return buildResponse(entity);
    }

    private Response buildResponse(String entity) {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(entity)
                .build();
    }

}
