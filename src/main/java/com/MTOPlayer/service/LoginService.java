package com.MTOPlayer.service;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginService {
    boolean isEmailInDB(String email) throws IOException, SQLException;

    boolean isPasswordCorrect(String email, String password) throws IOException, SQLException;
}
