package com.MTOPlayer.models;

public class TemporaryPassword {
    private String password;

    public TemporaryPassword(String password) {
        this.password = password;
    }

    public TemporaryPassword() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
