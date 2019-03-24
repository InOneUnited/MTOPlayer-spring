package com.MTOPlayer.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface PasswordDAO {
    int getPasswordIdBasedOnUser(int userId) throws SQLException, IOException;
}
