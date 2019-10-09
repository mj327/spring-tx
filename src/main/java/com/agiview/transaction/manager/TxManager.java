package com.agiview.transaction.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class TxManager {

    @Autowired
    ConnectionManager connectionManager;

    public void open() {
        try {
            connectionManager.getCurrentConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connectionManager.getCurrentConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connectionManager.getCurrentConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connectionManager.getCurrentConnection().close();
            connectionManager.removeCurrentConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
