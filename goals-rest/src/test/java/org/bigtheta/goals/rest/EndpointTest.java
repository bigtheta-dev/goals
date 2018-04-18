package org.bigtheta.goals.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndpointTest {

    @Test
    void get() {
        Endpoint endpoint = new Endpoint();
        assertEquals("result", endpoint.get());
    }
}