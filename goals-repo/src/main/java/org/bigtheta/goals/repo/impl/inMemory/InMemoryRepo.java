package org.bigtheta.goals.repo.impl.inMemory;

import org.bigtheta.goals.core.Task;
import org.bigtheta.goals.core.TaskStatus;
import org.bigtheta.goals.repo.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryRepo implements Repo {
    private static int id = 1;

    private static String nextId() {
        return Integer.toString(id++);
    }

    static List<Task> tasks;


    static {
        tasks = new ArrayList<>();

        tasks.add(new Task(nextId(), "fst"));
        tasks.add(new Task(nextId(), "snd"));
    }

    @Override
    public List<Task> list() {
        return tasks.stream()
                .filter(task -> task.getStatus() != TaskStatus.DELETED)
                .collect(Collectors.toList());
    }

    @Override
    public Task create(Task task) {
        task.setStatus(TaskStatus.ACTIVE);
        task.setId(nextId());
        tasks.add(task);
        return task;
    }

    @Override
    public Task read(String id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Task update(Task task) {
        Task read = read(task.getId());
        read.setValue(task.getValue());

        return read;
    }

    @Override
    public Task delete(String id) {

        Task result = tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (result != null) {
            result.setStatus(TaskStatus.DELETED);
        }
        return result;
    }

}
