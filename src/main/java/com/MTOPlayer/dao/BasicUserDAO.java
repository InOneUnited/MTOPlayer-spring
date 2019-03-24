package com.MTOPlayer.dao;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicUserDAO extends DAO implements UserDAO {
    @Override
    public boolean isNewUserInDB(String login, String email) throws SQLException {
        String query = "SELECT * FROM login WHERE  login=\'" + login +"\' OR email=\'"+ email +"\';";

        ResultSet result = fillResult(query);

        try{
            while(result.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("something went wrong with getting results");
        }

        return false;
    }

    @Override
    public void addNewUserToDB(User user) {
        String query = "INSERT INTO login (login, email) VALUES(\'"+ user.getLogin() + "\',\'" + user.getEmail() +"\');";
        System.out.println(query);
        executeQuery(query);
    }

    @Override
    public void addNewPasswordToDB(Password securePassword) {
        String query = "INSERT INTO password(password, login_id) VALUES(" +
                "\'" +securePassword.getPasswordValue()+
                "\',\'"+securePassword.getUser().getId()+"\');";
        System.out.println(query);
        executeQuery(query);
    }

    @Override
    public void addNewSaltToDB(Salt salt){
        String query = "INSERT INTO salt(salt, password_id) VALUES(" +
                "\'" +salt.getSalt()+
                "\',\'"+salt.getPassword().getId()+"\');";
        executeQuery(query);

    }

    @Override
    public void addNewUserInfoToDB(UserInfo userInfo) {
        String query = "INSERT INTO user_info(first_name, gender, birthday, last_name, join_date, login_id)  VALUES(" +
                "\'" +userInfo.getFirstName()+
                "\',\'" +userInfo.getGender()+
                "\',\'" +userInfo.getBirthday()+
                "\',\'" + userInfo.getLastName()+
                "\',\'" + userInfo.getJoinDate()+
                "\',\'"+userInfo.getUser().getId()+"\');";
        executeQuery(query);
    }

    @Override
    public int getUserId(String login) throws SQLException {
        int userId = 0;
        String query = "SELECT id FROM login WHERE login=\'" +login+"\';";

        ResultSet result = fillResult(query);


        while(result.next()){
            userId = result.getInt(1);
        }
        return userId;
    }

    private ResultSet fillResult(String query) throws SQLException {
        Connection connection = this.openDataBase();
        PreparedStatement statement = null;
        ResultSet result;

        try{
            statement = connection.prepareStatement(query);
            result = askDataBaseForData(query, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            closeStatementAndConnection(connection, statement);
            throw new SQLException("something went wrong with connecting");
        }
        return result;
    }
}
