package com.MTOPlayer.service;

import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;

import java.sql.SQLException;

public interface LoginService {
    boolean isUserNew(User user) throws SQLException;
    void addNewUser(User user, UserInfo userInfo, String password);
}
