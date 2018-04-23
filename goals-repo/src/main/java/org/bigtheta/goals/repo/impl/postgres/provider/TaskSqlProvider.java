package org.bigtheta.goals.repo.impl.postgres.provider;

import org.bigtheta.goals.core.Task;
import org.bigtheta.goals.core.TaskStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskSqlProvider extends AbstractTaskSqlProvider {

    @Override
    public boolean createTable(Connection dbConnection) {
        return execute(dbConnection, TaskSqlProvider.CREATE_TABLE_SQL, pst -> {
            try {
                return pst.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<Task> select(Connection dbConnection) {
        return execute(dbConnection, TaskSqlProvider.SELECT_SQL, pst -> {
            try {
                ResultSet rs = pst.executeQuery();
                List<Task> tasks = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String status = rs.getString("status");
                    String value = rs.getString("value");

                    tasks.add(new Task(id, value, Enum.valueOf(TaskStatus.class, status)));
                }
                return tasks;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public Task select(Connection dbConnection, String id) {
        return execute(dbConnection, TaskSqlProvider.SELECT_ONE_SQL, pst -> {
            try {
                pst.setInt(1, Integer.parseInt(id));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    rs.getInt("id");
                    String status = rs.getString("status");
                    String value = rs.getString("value");
                    return new Task(id, value, Enum.valueOf(TaskStatus.class, status));
                } else {
                    throw new RuntimeException();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public String update(Connection dbConnection, Task object) {
        return Integer.toString(execute(dbConnection, TaskSqlProvider.UPDATE_SQL, pst -> {
            try {
                String value = object.getValue();
                String status = object.getStatus().name();
                int id = Integer.parseInt(object.getId());

                pst.setString(1, value);
                pst.setString(2, status);
                pst.setInt(3, id);

                return pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));
    }


    @Override
    public String insert(Connection dbConnection, Task object) {
        return execute(dbConnection, TaskSqlProvider.INSERT_SQL, pst -> {
            try {

                String value = object.getValue();
                String status = object.getStatus().name();

                pst.setString(1, value);
                pst.setString(2, status);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return Integer.toString(id);
                } else {
                    throw new RuntimeException();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
