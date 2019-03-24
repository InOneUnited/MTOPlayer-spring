package com.MTOPlayer.dao;

import java.sql.SQLException;

public interface PasswordDAO {
    int getPasswordIdBasedOnUser(int userId) throws SQLException;
}
