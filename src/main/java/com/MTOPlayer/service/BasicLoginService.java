package com.MTOPlayer.service;

import com.MTOPlayer.dao.PasswordDAO;
import com.MTOPlayer.dao.SaltDAO;
import com.MTOPlayer.dao.UserDAO;
import com.MTOPlayer.models.Password;
import com.MTOPlayer.security.DefaultPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BasicLoginService implements LoginService {
    private UserDAO userDao;
    private PasswordDAO passwordDAO;
    private SaltDAO saltDAO;

    @Autowired
    public BasicLoginService(UserDAO userDAO, PasswordDAO passwordDAO, SaltDAO saltDAO) {
        this.userDao = userDAO;
        this.passwordDAO = passwordDAO;
        this.saltDAO = saltDAO;
    }

    @Override
    public boolean isEmailInDB(String email) {
        return userDao.isEmailInDB(email);
    }

    @Override
    public boolean isPasswordCorrect(String email, String password) throws IOException {
        com.MTOPlayer.security.Password securePasswordChecker = new DefaultPassword();

        int userId = userDao.getUserId(email);
        int passwordId = passwordDAO.getPasswordIdBasedOnUser(userId);
        Password passwordObj = passwordDAO.getPasswordById(passwordId);
        byte[] hashedPassword = passwordObj.getPasswordValue();

        byte[] salt = saltDAO.getSalt(passwordObj);

        return securePasswordChecker.isPasswordCorrect(password, salt, hashedPassword);
    }
}
