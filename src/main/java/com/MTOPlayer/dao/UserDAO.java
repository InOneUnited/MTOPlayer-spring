package com.MTOPlayer.dao;

import java.sql.SQLException;

public interface UserDAO {
    boolean isNewUserInDB(String login, String email) throws SQLException;
}
