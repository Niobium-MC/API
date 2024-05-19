package fr.sorway.niobium.api.database.sql;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseManager {
    HikariDataSource getHikariDataSource();
    Connection getConnection() throws SQLException;
}
