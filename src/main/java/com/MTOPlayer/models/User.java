package com.MTOPlayer.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Login")
@NamedQuery(name="allUsersQuery", query="select t from User t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="email")
    private String email;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<APIKey> apiKeys;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Password password;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "user")
    private UserInfo userInfo;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Session> sessions;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Playlist> playlists;

    public User() {}

    public User(String login, String email, List<APIKey> apiKeys, Password password, Salt salt, List<Session> sessions, List<Playlist> playlists, UserInfo userInfo) {
        this.login = login;
        this.email = email;
        this.apiKeys = apiKeys;
        this.password = password;
        this.sessions = sessions;
        this.playlists = playlists;
        this.userInfo = userInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<APIKey> getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(List<APIKey> apiKeys) {
        this.apiKeys = apiKeys;
    }


    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
