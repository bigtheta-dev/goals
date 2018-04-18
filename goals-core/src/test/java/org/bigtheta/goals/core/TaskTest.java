package org.bigtheta.goals.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void testMarshall() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Task task = mapper.readerFor(Task.class).readValue("{\"value\":\"fst\"}");
        System.out.println(task);
    }

    @Test
    void testUnMarshall() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Task task = new Task("blah");
        System.out.println(mapper.writeValueAsString(task));
    }
}