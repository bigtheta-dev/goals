package org.bigtheta.goals.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskEndpointTest {

    @Test
    void get() {
        TaskEndpoint taskEndpoint = new TaskEndpoint();
        assertEquals("[{\"value\":\"fst\"},{\"value\":\"snd\"}]", taskEndpoint.list());
    }
}