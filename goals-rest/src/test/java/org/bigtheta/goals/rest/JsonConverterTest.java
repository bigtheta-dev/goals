package org.bigtheta.goals.rest;

import org.bigtheta.goals.core.JsonConverter;
import org.bigtheta.goals.core.Task;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonConverterTest {

    @Test
    void marchall() {
        JsonConverter converter = new JsonConverter();
        String result = converter.marchall(new Task("payload"));
//        assertEquals("{\"value\":\"payload\"}", result);
    }

    @Test
    void marchallList() {
        JsonConverter converter = new JsonConverter();
        String result = converter.marchall(Collections.singletonList(new Task("payload")));
//        assertEquals("[{\"value\":\"payload\"}]", result);
    }
}