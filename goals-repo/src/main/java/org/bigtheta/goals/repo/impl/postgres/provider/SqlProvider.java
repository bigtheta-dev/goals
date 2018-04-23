package org.bigtheta.goals.repo.impl.postgres.provider;

import java.sql.Connection;
import java.util.List;

/**
 * @param <T> - persistent object
 */
public interface SqlProvider<T> {

    /**
     * insert into database and return id
     *
     * @param connection - database connection
     * @param object     - persistent object
     * @return identifier
     */
    String insert(Connection connection, T object);

    /**
     * insert into database and return id
     *
     * @param connection - database connection
     * @return list of persistent objects
     */
    List<T> select(Connection connection);

    /**
     * insert into database and return id
     *
     * @param connection - database connection
     * @return persistent object
     */
    T select(Connection connection, String id);

    /**
     * insert into database and return id
     *
     * @param connection - database connection
     * @param object     - persistent object
     * @return identifier
     */
    String update(Connection connection, T object);

    /**
     * create database table
     * @param connection - database connection
     * @return
     */
    boolean createTable(Connection connection);
}
