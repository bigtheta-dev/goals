package org.bigtheta.goals.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bigtheta.goals.core.Task;

import java.io.IOException;
import java.util.List;

public class JsonConverter {

    public String marchall(Task task) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String marchall(List<Task> tasks) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(tasks);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Task unmarchall(String input) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readerFor(Task.class).readValue(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
