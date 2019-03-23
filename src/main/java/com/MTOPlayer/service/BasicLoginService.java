package com.MTOPlayer.service;

import com.MTOPlayer.dao.BasicUserDAO;
import com.MTOPlayer.dao.UserDAO;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;

import java.sql.SQLException;

public class BasicLoginService implements LoginService {
    @Override
    public boolean isUserNew(User user) throws SQLException {
        UserDAO userDao = new BasicUserDAO();

        if(userDao.isNewUserInDB(user.getLogin(), user.getEmail())){
            return false;
        }
        return true;
    }

    @Override
    public void addNewUser(User user, UserInfo userInfo, String password) {

    }
}
