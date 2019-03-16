package com.MTOPlayer.security;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public final class DefaultPassword implements Password {

    private static final Random RANDOM = new SecureRandom();
    private static final int DEFAULT_SIZE = 64;

    @Override public String getStaticSalt() throws IllegalStateException, IOException {
        List<String> inputStream = Files.readAllLines(Paths.get("../staticSalt/salt.txt"));
        String staticSalt = "";

        for(String word: inputStream){
            staticSalt = new StringBuilder().append(staticSalt).append(word).toString() ;
        }

        if(staticSalt.isEmpty()){
            throw new IllegalStateException("salt can not be null");
        }

        return staticSalt;
    }

    @Override public byte[] getDynamicSalt64() {
        return getDynamicSalt(DEFAULT_SIZE);
    }

    @Override public byte[] getDynamicSalt32() {
        return getDynamicSalt(32);
    }

    @Override public byte[] getDynamicSalt(int size) {
        final byte[] salt;

        if (size < 32) {
            salt = new byte[DEFAULT_SIZE];
        } else {
            salt = new byte[size];
        }

        RANDOM.nextBytes(salt);
        return salt;
    }

    @Override public byte[] getHashedPassword(String password, byte[] salt) throws IllegalStateException, IOException {

        Validate.notNull(password, "Password must not be null");
        Validate.notNull(salt, "Salt must not be null");

        byte[] hashedPassword = createHashedPassword(password, salt);

        return hashedPassword;

    }

    @Override public boolean isPasswordCorrect(final String password, final byte[] salt, final byte[] hash) throws NullPointerException, IllegalStateException, IOException {

        Validate.notNull(password, "Password must not be null");
        Validate.notNull(salt, "Salt must not be null");
        Validate.notNull(hash, "Hash must not be null");

        final byte[] digest = createHashedPassword(password, salt);

        return Arrays.equals(digest, hash);
    }

    //UnsupportedEncodingException
    //FileNotFound with static hash
    //IllegalStateExcecption with salt being null
    //IOException with reading lines from static file
    private final byte[] createHashedPassword(final String password, final byte[] salt) throws IllegalStateException, IOException {
        String staticSalt = getStaticSalt();
        String passwordWithStaticSalt = password + staticSalt;
        final byte[] passwordBytes = passwordWithStaticSalt.getBytes("UTF-8");
        final byte[] all = ArrayUtils.addAll(passwordBytes, salt);
        SHA3.DigestSHA3 md = new SHA3.Digest512();
        md.update(all);
        return md.digest();
    }

}

