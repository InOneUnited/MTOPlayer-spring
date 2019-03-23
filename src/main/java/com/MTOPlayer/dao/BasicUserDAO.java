package com.MTOPlayer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicUserDAO extends DAO implements UserDAO {
    @Override
    public boolean isNewUserInDB(String login, String email) throws SQLException {
        Connection connection = this.openDataBase();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM login WHERE  login=\'" + login +"\' OR email=\'"+ email +"\';";
        System.out.println(query);
        try{
            statement = connection.prepareStatement(query);
            result = askDataBaseForData(query, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            closeStatementAndConnection(connection, statement);
            throw new SQLException("something went wrong with connecting");
        }

        try{
            while(result.next()){
                return true;
            }
        } catch (SQLException e) {
            closeStatementAndConnection(connection, statement);
            throw new SQLException("something went wrong with getting results");
        }

        closeStatementAndConnection(connection, statement);

        return false;
    }
}
