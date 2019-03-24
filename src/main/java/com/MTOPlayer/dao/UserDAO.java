package com.MTOPlayer.dao;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO {
    boolean isNewUserInDB(String login, String email) throws SQLException, IOException;
    void addNewUserToDB(User user) throws IOException, SQLException;
    void addNewPasswordToDB(Password securePassword) throws IOException, SQLException;
    void addNewSaltToDB(Salt salt) throws IOException, SQLException;
    void addNewUserInfoToDB(UserInfo userInfo) throws IOException, SQLException;
    int getUserId(String email) throws SQLException, IOException;
    boolean isEmailInDB(String email) throws IOException, SQLException;
}
