package com.MTOPlayer.service;

import com.MTOPlayer.dao.PasswordDAO;
import com.MTOPlayer.dao.SaltDAO;
import com.MTOPlayer.dao.UserDAO;
import com.MTOPlayer.dao.UserInfoDAO;
import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class BasicSignUpService implements SignUpService {
    private UserDAO userDao;
    private PasswordDAO passwordDAO;
    private SaltDAO saltDAO;
    private UserInfoDAO userInfoDAO;

    @Autowired
    public BasicSignUpService(UserDAO userDAO, PasswordDAO passwordDAO, SaltDAO saltDAO, UserInfoDAO userInfoDAO) {
        this.userDao = userDAO;
        this.passwordDAO = passwordDAO;
        this.saltDAO = saltDAO;
        this.userInfoDAO = userInfoDAO;
    }


    @Override
    public boolean isUserNew(User user) {
        return !userDao.isNewUserInDB(user.getLogin(), user.getEmail());
    }

    @Override
    @Transactional
    public void addNewUser(User user, UserInfo userInfo, String password) throws IOException {
        userDao.addNewUserToDB(user);

        int userId = userDao.getUserId(user.getEmail());
        user.setId(userId);

        Salt salt = saltDAO.createSalt();

        Password securePassword = passwordDAO.createSecurePassword(password, salt.getSalt());

        securePassword.setUser(user);
        userInfo.setUser(user);

        passwordDAO.addNewPasswordToDB(securePassword);
        int passwordId = passwordDAO.getPasswordIdBasedOnUser(userId);
        securePassword.setId(passwordId);
        System.out.println("passwordID: " + passwordId + "userId: " + user.getId());
        salt.setPassword(securePassword);

        saltDAO.addNewSaltToDB(salt);

        LocalDate joinDate = LocalDate.now();
        userInfo.setJoinDate(joinDate);
        userInfoDAO.addNewUserInfoToDB(userInfo);

    }
}
