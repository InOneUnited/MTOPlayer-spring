package com.MTOPlayer.MTOPlayerSpring.model;

import javax.persistence.*;

@Entity
@Table(name = "Salt")
@NamedQuery(name="allSaltsQuery", query="select t from Salt t")
public class Salt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "salt")
    private byte[] salt;

    @MapsId
    @OneToOne
    @JoinColumn(name = "password_id")
    private Password password;

    public Salt(){}

    public Salt(byte[] salt, Password password) {
        this.salt = salt;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
