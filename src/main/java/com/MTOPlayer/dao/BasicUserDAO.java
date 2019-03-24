package com.MTOPlayer.dao;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicUserDAO extends DAO implements UserDAO {
    @Override
    public boolean isNewUserInDB(String login, String email) throws SQLException, IOException {
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
    public void addNewUserToDB(User user) throws IOException, SQLException {
        String query = "INSERT INTO login (login, email) VALUES(\'"+ user.getLogin() + "\',\'" + user.getEmail() +"\');";
        System.out.println(query);
        executeQuery(query);
    }

    @Override
    public void addNewPasswordToDB(Password securePassword) throws IOException, SQLException {
        String query = "INSERT INTO password(password, login_id) VALUES(" +
                "\'" +securePassword.getPasswordValue()+
                "\',\'"+securePassword.getUser().getId()+"\');";
        System.out.println(query);
        executeQuery(query);
    }

    @Override
    public void addNewSaltToDB(Salt salt) throws IOException, SQLException {
        String query = "INSERT INTO salt(salt, password_id) VALUES(" +
                "\'" +salt.getSalt()+
                "\',\'"+salt.getPassword().getId()+"\');";
        executeQuery(query);

    }

    @Override
    public void addNewUserInfoToDB(UserInfo userInfo) throws IOException, SQLException {
        if(userInfo.getBirthday() == null){
            System.out.println("NULLNULLNULL!!!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO user_info(first_name, gender, birthday, last_name, join_date, login_id) VALUES(");

        if(userInfo.getFirstName() == null || userInfo.getFirstName().isEmpty()){
            stringBuilder.append(null + ",");
        } else {
            stringBuilder.append("\'" + userInfo.getFirstName() + "\',");
        }
        if(userInfo.getGender() == null || userInfo.getGender().isEmpty()){
            stringBuilder.append(null + ",");
        } else{
            stringBuilder.append("\'" + userInfo.getGender() + "\',");
        }
        if(userInfo.getBirthday() == null){
            stringBuilder.append(null + ",");
        } else {
            stringBuilder.append("\'" + userInfo.getBirthday() + "\',");
        }
        if(userInfo.getLastName() == null || userInfo.getLastName().isEmpty()){
             stringBuilder.append(null + ",");
        } else {
            stringBuilder.append("\'" + userInfo.getLastName() + "\',");
        }
        stringBuilder.append("\'" + userInfo.getJoinDate() + "\',");
        stringBuilder.append("\'" + userInfo.getUser().getId() + "\');");

        String query = stringBuilder.toString();
        executeQuery(query);
    }

    @Override
    public int getUserId(String login) throws SQLException, IOException {
        int userId = 0;
        String query = "SELECT id FROM login WHERE login=\'" +login+"\';";

        ResultSet result = fillResult(query);


        while(result.next()){
            userId = result.getInt(1);
        }
        return userId;
    }
}
