package org.bigtheta.goals.repo.impl.postgres.provider;

import org.bigtheta.goals.core.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class AbstractTaskSqlProvider implements SqlProvider<Task> {

    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS task(id serial PRIMARY KEY, value VARCHAR(1024) NOT NULL, status VARCHAR(16) NOT NULL )";

    public static final String INSERT_SQL = "INSERT INTO task (value, status) VALUES (?,?) RETURNING id";
    public static final String SELECT_SQL = "SELECT id, status, value from task";
    public static final String SELECT_ONE_SQL = "SELECT id, status, value from task WHERE id = ?";
    public static final String UPDATE_SQL = "UPDATE task SET value = ?, status = ?  WHERE id = ?";

    protected <R> R execute(Connection dbConnection, String sql, Function<PreparedStatement, R> f) {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement(sql);
            return f.apply(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
