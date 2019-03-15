package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Login")
@NamedQuery(name="allUsersQuery", query="select t from User t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="login")
    private String userLogin;

    @Column(name="email")
    private String email;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<APIKey> apiKeys;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Password password;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Session> sessions;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Playlist> playlists;

    public User() {}

    public User(String userLogin, String email, List<APIKey> apiKeys, Password password, Salt salt, List<Session> sessions, List<Playlist> playlists) {
        this.userLogin = userLogin;
        this.email = email;
        this.apiKeys = apiKeys;
        this.password = password;
        this.sessions = sessions;
        this.playlists = playlists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<APIKey> getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(List<APIKey> apiKeys) {
        this.apiKeys = apiKeys;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
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
}
