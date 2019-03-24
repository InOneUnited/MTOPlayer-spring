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

    @Override
    public byte[] getHashedPassword(int passwordId) throws IOException, SQLException {
        byte[] hashedPassword = new byte[0];

        String query = "SELECT password FROM password WHERE id=\'" +passwordId+"\';";

        ResultSet result = fillResult(query);


        while(result.next()){
            hashedPassword = result.getBytes(1);
        }
        return hashedPassword;
    }
}
