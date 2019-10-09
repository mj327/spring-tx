package com.agiview.transaction.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionManager {

    private  ThreadLocal<Connection> connectionTL = new ThreadLocal<>();

    @Autowired
    DataSource dataSource;

    public  Connection getCurrentConnection() {

        Connection connection = connectionTL.get();

        if (connection == null) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionTL.set(connection);
        }

        return connection;
    }

    public void removeCurrentConnection() {
        connectionTL.remove();
    }
}
