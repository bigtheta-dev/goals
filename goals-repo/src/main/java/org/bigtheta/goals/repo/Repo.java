package org.bigtheta.goals.repo;

import org.bigtheta.goals.core.Task;

import java.util.List;

public interface Repo {

    List<Task> list();

    Task create(Task task);

    Task read(String id);

    Task update(Task task);

    Task delete(String id);
}
