package com.agiview.transaction.dao;

import com.agiview.transaction.entity.UserAccount;
import com.agiview.transaction.manager.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserAccountDao {

    @Autowired
    ConnectionManager connectionManager;


    public void update(UserAccount userAccount) {

        Connection connection = connectionManager.getCurrentConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("update t_user_account set name = ?, balance = ? where id = ?");

            preparedStatement.setString(1, userAccount.getName());

            preparedStatement.setBigDecimal(2, userAccount.getBalance());

            preparedStatement.setInt(3, userAccount.getId());

            preparedStatement.execute();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }


    public UserAccount findByName(String name) {

        Connection connection = connectionManager.getCurrentConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user_account where name = ?");

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            UserAccount userAccount = new UserAccount();

            while (resultSet.next()) {
                userAccount.setId(resultSet.getInt(1));
                userAccount.setName(resultSet.getString(2));
                userAccount.setBalance(resultSet.getBigDecimal(3));
            }

            return userAccount;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
