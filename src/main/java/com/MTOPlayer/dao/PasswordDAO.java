package com.MTOPlayer.dao;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.User;
import com.MTOPlayer.repository.PasswordRepository;
import com.MTOPlayer.security.DefaultPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PasswordDAO {

    private PasswordRepository passwordRepository;

    @Autowired
    public void setPasswordRepository(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public int getPasswordIdBasedOnUser(int userId) {
        return passwordRepository.findPasswordByUserId(userId).getId();
    }

    public byte[] getHashedPassword(int passwordId) {
        return passwordRepository.findPasswordById(passwordId).getPasswordValue();
    }

    public Password addNewPasswordToDB(Password securePassword) {
        return passwordRepository.save(securePassword);
    }

    public Password getPasswordById(int passwordId) {
        return passwordRepository.findPasswordById(passwordId);
    }

    public Password createSecurePassword(String password, byte[] salt) throws IOException {
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

}
