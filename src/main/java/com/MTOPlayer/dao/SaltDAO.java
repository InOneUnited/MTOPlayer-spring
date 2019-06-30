package com.MTOPlayer.dao;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import com.MTOPlayer.repository.SaltRepository;
import com.MTOPlayer.security.DefaultPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaltDAO {

    private final SaltRepository saltRepository;

    @Autowired
    public SaltDAO(SaltRepository saltRepository) {
        this.saltRepository = saltRepository;
    }

    public byte[] getSalt(Password password) {
        return this.saltRepository.findSaltByPassword(password).getSalt();
    }

    public Salt addNewSaltToDB(Salt salt) {
        return this.saltRepository.save(salt);
    }

    public Salt createSalt() {
        Salt salt = new Salt();
        com.MTOPlayer.security.Password securePasswordMaker = new DefaultPassword();
        byte[] saltValue = securePasswordMaker.getDynamicSalt32();
        salt.setSalt(saltValue);

        return salt;
    }

}
