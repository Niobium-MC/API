package fr.sorway.niobium.api.database.sql;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseManager {

    /**
     * Gets the HikariCP data source.
     *
     * @return the HikariDataSource instance
     */
    HikariDataSource getHikariDataSource();

    /**
     * Gets a connection from the data source.
     *
     * @return a Connection instance
     * @throws SQLException if a database access error occurs
     */
    Connection getConnection() throws SQLException;
}
