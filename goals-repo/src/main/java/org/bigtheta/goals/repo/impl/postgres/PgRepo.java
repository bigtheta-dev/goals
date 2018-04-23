package org.bigtheta.goals.repo.impl.postgres;

import org.bigtheta.goals.core.Task;
import org.bigtheta.goals.core.TaskStatus;
import org.bigtheta.goals.repo.Repo;
import org.bigtheta.goals.repo.impl.postgres.provider.TaskSqlProvider;

import java.util.List;
import java.util.stream.Collectors;


public class PgRepo implements Repo {

    private ConnectionManager connectionManager;

    private TaskSqlProvider sqlProvider;


    public PgRepo() {
        sqlProvider = new TaskSqlProvider();
        connectionManager = ConnectionManager.getINSTANCE();
        // create tables if not exist
        connectionManager.executeInTransaction(connection -> sqlProvider.createTable(connection));
    }

    @Override
    public List<Task> list() {
        return connectionManager.executeInTransaction(connection -> sqlProvider.select(connection))
                .stream()
                .filter(task -> task.getStatus() != TaskStatus.DELETED)
                .collect(Collectors.toList());

    }

    @Override
    public Task read(String id) {
        return connectionManager.executeInTransaction(connection -> sqlProvider.select(connection, id));
    }

    @Override
    public Task create(Task task) {
        task.setStatus(TaskStatus.ACTIVE);
        return connectionManager.executeInTransaction(connection -> {
            String id = sqlProvider.insert(connection, task);
            return sqlProvider.select(connection, id);
        });
    }

    @Override
    public Task update(Task task) {
        return connectionManager.executeInTransaction(connection -> {
            sqlProvider.update(connection, task);
            return sqlProvider.select(connection, task.getId());
        });
    }

    @Override
    public Task delete(String id) {
        return connectionManager.executeInTransaction(connection -> {
            Task task = sqlProvider.select(connection, id);
            task.setStatus(TaskStatus.DELETED);
            sqlProvider.update(connection, task);
            return sqlProvider.select(connection, id);
        });
    }
}
