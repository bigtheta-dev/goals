package org.bigtheta.goals.cli;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bigtheta.goals.core.JsonConverter;
import org.bigtheta.goals.core.Task;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.HttpHeaders.USER_AGENT;


public class Processor {

    public static final String PORT = "8080";
    public static final String HOST = "http://localhost";

    private static String url = HOST + ":" + PORT + "/api/task";
    private HttpClient client = HttpClientBuilder.create().build();
    private JsonConverter jsonConverter = new JsonConverter();

    public String process(String[] args) {

        try {
            if (args.length == 0) {
                throw new IllegalArgumentException();
            }

            switch (args[0]) {
                case "help":
                    return "list - show all goals\n" +
                            "delete {id} - delete goal with id\n" +
                            "read {id} - read goal with id\n" +
                            "create {value} - create goal with value\n" +
                            "update {id} {value} - update goal value";
                case "list":
                    if (args.length != 1) {
                        throw new IllegalArgumentException();
                    }
                    return handleList();

                case "delete":
                    if (args.length != 2) {
                        throw new IllegalArgumentException();
                    }
                    return handleDelete(args[1]);

                case "read":
                    if (args.length != 2) {
                        throw new IllegalArgumentException();
                    }
                    return handleRead(args[1]);

                case "create":
                    if (args.length < 2) {
                        throw new IllegalArgumentException();
                    }
                    String task = String.join(" ", Arrays.asList(args).subList(1, args.length));
                    return handleCreate(task);

                case "update":
                    if (args.length < 3) {
                        throw new IllegalArgumentException();
                    }
                    String updateTask = String.join(" ", Arrays.asList(args).subList(2, args.length));
                    return handleUpdate(args[1], updateTask);

                default:
                    throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException iae) {
            return "bad command";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String handleList() throws IOException {
        HttpGet list = new HttpGet(url);

        // add request header
        list.addHeader("User-Agent", USER_AGENT);

        HttpResponse listResponse = client.execute(list);

        String listResponseJson = IOUtils.toString(listResponse.getEntity().getContent(), UTF_8.name());
        List<Task> tasks = jsonConverter.unmarchallList(listResponseJson);
        System.out.println("id\tvalue");
        return tasks.stream().map(Task::toString).collect(Collectors.joining("\n"));
    }

    private String handleDelete(String id) throws IOException {
        HttpDelete deleteRequest = new HttpDelete(url + "/" + id);

        // add request header
        deleteRequest.addHeader("User-Agent", USER_AGENT);

        HttpResponse deleteResponse = client.execute(deleteRequest);

        String deleteResponseJson = IOUtils.toString(deleteResponse.getEntity().getContent(), UTF_8.name());

        return jsonConverter.unmarchall(deleteResponseJson).toString();
    }

    private String handleRead(String id) throws IOException {
        HttpGet readRequest = new HttpGet(url + "/" + id);

        // add request header
        readRequest.addHeader("User-Agent", USER_AGENT);

        HttpResponse readResponse = client.execute(readRequest);

        String getResponseJson = IOUtils.toString(readResponse.getEntity().getContent(), UTF_8.name());

        return jsonConverter.unmarchall(getResponseJson).toString();
    }

    private String handleUpdate(String id, String updateTask) throws IOException {
        HttpPut updateRequest = new HttpPut(url);

        // add request header
        updateRequest.addHeader("User-Agent", USER_AGENT);

        updateRequest.setHeader("Accept", "application/json");
        updateRequest.setHeader("Content-type", "application/json");

        String updateJson = "{\"id\":\"" + id + "\",\"value\":\"" + updateTask + "\"}";
        StringEntity updateEntity = new StringEntity(updateJson);
        updateRequest.setEntity(updateEntity);

        HttpResponse updateResponse = client.execute(updateRequest);

        String updateResponseJson = IOUtils.toString(updateResponse.getEntity().getContent(), UTF_8.name());
        return jsonConverter.unmarchall(updateResponseJson).toString();
    }

    private String handleCreate(String task) throws IOException {
        HttpPost createRequest = new HttpPost(url);

        // add request header
        createRequest.addHeader("User-Agent", USER_AGENT);
        createRequest.setHeader("Accept", "application/json");
        createRequest.setHeader("Content-type", "application/json");

        String json = "{\"value\":\"" + task + "\"}";
        StringEntity entity = new StringEntity(json);
        createRequest.setEntity(entity);

        HttpResponse createResponse = client.execute(createRequest);

        String createResponseJson = IOUtils.toString(createResponse.getEntity().getContent(), UTF_8.name());
        return jsonConverter.unmarchall(createResponseJson).toString();
    }
}
