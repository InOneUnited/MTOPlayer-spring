package com.MTOPlayer.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface SaltDAO {
    byte[] getSalt(int passwordId) throws IOException, SQLException;
}
