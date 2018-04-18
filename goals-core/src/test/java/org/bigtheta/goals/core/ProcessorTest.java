package org.bigtheta.goals.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {

    @Test
    void list() {
        Processor processor = new Processor();
        assertEquals(processor.list(), "list");
    }
}