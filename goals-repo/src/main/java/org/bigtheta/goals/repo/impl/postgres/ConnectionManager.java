package org.bigtheta.goals.repo.impl.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

public class ConnectionManager {

    private static String url = "jdbc:postgresql://db:5432/goals";
    private static String user = "goals";
    private static String password = "goals";
    private static String driver = "org.postgresql.Driver";

    private static ConnectionManager INSTANCE = new ConnectionManager();

    static ConnectionManager getINSTANCE() {
        return INSTANCE;
    }


    private Connection connection;

    private ConnectionManager() {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            this.connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                connection.setAutoCommit(false);
            } else {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public <T> T executeInTransaction(Function<Connection, T> f) {
        T apply = f.apply(connection);
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return apply;
    }
}
