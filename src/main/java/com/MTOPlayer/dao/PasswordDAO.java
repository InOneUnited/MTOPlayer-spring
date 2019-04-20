package com.MTOPlayer.dao;

import com.MTOPlayer.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordDAO {
    @Autowired
    private PasswordRepository passwordRepository;

    public int getPasswordIdBasedOnUser(int userId) {
        return this.passwordRepository.findPasswordByUserId(userId).getId();
    }

    public byte[] getHashedPassword(int passwordId) {
        return this.passwordRepository.findPasswordById(passwordId).getPasswordValue();
    }
}
