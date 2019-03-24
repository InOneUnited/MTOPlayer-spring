package com.MTOPlayer.dao;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;

import java.sql.SQLException;

public interface UserDAO {
    boolean isNewUserInDB(String login, String email) throws SQLException;
    void addNewUserToDB(User user);
    void addNewPasswordToDB(Password securePassword);
    void addNewSaltToDB(Salt salt);
    void addNewUserInfoToDB(UserInfo userInfo);
    int getUserId(String login) throws SQLException;
}
