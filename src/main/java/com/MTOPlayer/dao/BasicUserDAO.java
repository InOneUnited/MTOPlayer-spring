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
}
