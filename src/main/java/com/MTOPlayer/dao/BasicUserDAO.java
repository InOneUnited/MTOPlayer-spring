package com.MTOPlayer.dao;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;

import java.io.IOException;
import java.sql.*;

public class BasicUserDAO extends DAO implements UserDAO {
    @Override
    public boolean isNewUserInDB(String login, String email) throws SQLException, IOException {
        String query = "SELECT * FROM login WHERE  login=\'" + login +"\' OR email=\'"+ email +"\';";

        return isResultInDB(query);
    }

    @Override
    public void addNewUserToDB(User user) throws IOException, SQLException {
        String query = "INSERT INTO login(login, email) VALUES(?,?)";

        Connection connection = openDataBase();
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, user.getLogin());
        pst.setString(2, user.getEmail());
        pst.executeUpdate();
        closeStatementAndConnection(connection, pst);
    }

    @Override
    public void addNewPasswordToDB(Password securePassword) throws IOException, SQLException {
        String query = "INSERT INTO password(password, login_id) VALUES(?,?)";

        Connection connection = openDataBase();
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setBytes(1, securePassword.getPasswordValue());
        pst.setInt(2, securePassword.getUser().getId());
        pst.executeUpdate();
        closeStatementAndConnection(connection, pst);
}

    @Override
    public void addNewSaltToDB(Salt salt) throws IOException, SQLException {
        String query = "INSERT INTO salt(salt, password_id) VALUES(?,?)";
        Connection connection = openDataBase();
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setBytes(1, salt.getSalt() );
        pst.setInt(2, salt.getPassword().getId());
        pst.executeUpdate();
        closeStatementAndConnection(connection, pst);
    }

    @Override
    public void addNewUserInfoToDB(UserInfo userInfo) throws IOException, SQLException {
        String query = ("INSERT INTO user_info(first_name, gender, birthday, last_name, join_date, login_id)" +
                " VALUES(?,?,?,?,?,?)");

        Connection connection = openDataBase();
        PreparedStatement pst = connection.prepareStatement(query);

        if(userInfo.getFirstName() == null || userInfo.getFirstName().isEmpty()){
            pst.setString(1, null);
        } else {
            pst.setString(1, userInfo.getFirstName());
        }
        if(userInfo.getGender() == null || userInfo.getGender().isEmpty()){
            pst.setString(2, null);
        } else{
            pst.setString(2, userInfo.getGender());
        }
        if(userInfo.getBirthday() == null){
            pst.setDate(3, null);
        } else {
            pst.setDate(3, Date.valueOf(userInfo.getBirthday()));
        }
        if(userInfo.getLastName() == null || userInfo.getLastName().isEmpty()){
            pst.setString(4, null);
        } else {
            pst.setString(4, userInfo.getLastName());
        }
        pst.setDate(5, Date.valueOf(userInfo.getJoinDate()));
        pst.setInt(6, userInfo.getUser().getId());

        pst.executeUpdate();
        closeStatementAndConnection(connection, pst);
    }

    @Override
    public int getUserId(String email) throws SQLException, IOException {
        int userId = 0;
        String query = "SELECT id FROM login WHERE email=\'" +email+"\';";

        ResultSet result = fillResult(query);


        while(result.next()){
            userId = result.getInt(1);
        }
        return userId;
    }

    @Override
    public boolean isEmailInDB(String email) throws IOException, SQLException {
        String query = "SELECT * FROM login WHERE  email=\'" + email +"\';";

        return isResultInDB(query);
    }

    private boolean isResultInDB(String query) throws IOException, SQLException {
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
}
