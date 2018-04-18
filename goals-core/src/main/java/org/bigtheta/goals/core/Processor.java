package org.bigtheta.goals.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Processor {
    static List<Task> tasks = Stream.of("fst", "snd").map(Task::new).collect(Collectors.toList());

    public List<Task> list() {
        return tasks;
    }

    public Task fst() {
        return tasks.get(0);
    }
}
