package com.MTOPlayer.service;

import com.MTOPlayer.dao.*;
import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;
import com.MTOPlayer.security.DefaultPassword;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;

public class BasicLoginService implements LoginService {
    private UserDAO userDao = new BasicUserDAO();

    @Override
    public boolean isUserNew(User user) throws SQLException, IOException {
        if(userDao.isNewUserInDB(user.getLogin(), user.getEmail())){
            return false;
        }
        return true;
    }

    @Override
    public void addNewUser(User user, UserInfo userInfo, String password) throws IOException, SQLException {
        userDao.addNewUserToDB(user);
        PasswordDAO passwordDAO = new BasicPasswordDAO();

        int userId = userDao.getUserId(user.getEmail());
        user.setId(userId);

        Salt salt = createSalt();
        Password securePassword = createSecurePassword(password, salt.getSalt());

        securePassword.setUser(user);
        userInfo.setUser(user);

        userDao.addNewPasswordToDB(securePassword);
        int passwordId = passwordDAO.getPasswordIdBasedOnUser(userId);
        securePassword.setId(passwordId);

        salt.setPassword(securePassword);

        userDao.addNewSaltToDB(salt);

        Date joinDate = new Date();
        userInfo.setJoinDate(joinDate);
        userDao.addNewUserInfoToDB(userInfo);

    }

    private Password createSecurePassword(String password, byte[] salt) throws IOException {
        com.MTOPlayer.security.Password securePasswordMaker = new DefaultPassword();
        byte[] passwordValue;

        try {
            passwordValue = securePasswordMaker.getHashedPassword(password, salt);
        } catch (IOException e) {
            throw new IOException("creating password gone wrong");
        }
        Password securePassword = new Password();
        securePassword.setPasswordValue(passwordValue);

        return securePassword;
    }

    private Salt createSalt(){
        Salt salt = new Salt();
        com.MTOPlayer.security.Password securePasswordMaker = new DefaultPassword();
        byte[] saltValue = securePasswordMaker.getDynamicSalt32();
        salt.setSalt(saltValue);

        return salt;
    }

    @Override
    public boolean isEmailInDB(String email) {
        return userDao.isEmailInDB(email);
    }

    @Override
    public boolean isPasswordCorrect(String email, String password) throws IOException, SQLException {
        PasswordDAO passwordDAO = new BasicPasswordDAO();
        SaltDAO saltDAO = new BasicSaltDAO();
        com.MTOPlayer.security.Password securePasswordChecker = new DefaultPassword();

        int userId = userDao.getUserId(email);
        int passwordId = passwordDAO.getPasswordIdBasedOnUser(userId);
        byte[] hashedPassword = passwordDAO.getHashedPassword(passwordId);
        byte[] salt = saltDAO.getSalt(passwordId);

        return securePasswordChecker.isPasswordCorrect(password,salt, hashedPassword);
    }

}
