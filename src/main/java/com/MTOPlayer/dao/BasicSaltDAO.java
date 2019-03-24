package com.MTOPlayer.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicSaltDAO extends DAO implements SaltDAO {
    @Override
    public byte[] getSalt(int passwordId) throws IOException, SQLException {
        byte[] salt = new byte[0];

        String query = "SELECT salt FROM salt WHERE password_id=\'" +passwordId+"\';";

        ResultSet result = fillResult(query);


        while(result.next()){
            salt = result.getBytes(1);
        }
        return salt;
    }
}
