package com.MTOPlayer.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicPasswordDAO extends DAO implements PasswordDAO {
    @Override
    public int getPasswordIdBasedOnUser(int userId) throws SQLException, IOException {
        int passwordId = 0;
        String query = "SELECT id FROM password WHERE login_id=\'" +userId+"\';";

        ResultSet result = fillResult(query);


        while(result.next()){
            passwordId = result.getInt(1);
        }
        return passwordId;
    }
}
