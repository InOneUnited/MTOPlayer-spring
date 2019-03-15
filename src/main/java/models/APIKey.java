package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="APIKey")
@NamedQuery(name="allAPIKeysQuery", query="select t from APIKey t")
public class APIKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="token")
    private String apiToken;

    @Column(name="api_username")
    private String apiUsername;

    @Column(name="api_name")
    private String apiName;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "apiKey")
    private List<Song> songs;

    public APIKey(){}

    public APIKey(String apiToken, String apiUsername, String apiName, User user) {
        this.apiToken = apiToken;
        this.apiUsername = apiUsername;
        this.apiName = apiName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getApiUsername() {
        return apiUsername;
    }

    public void setApiUsername(String apiUsername) {
        this.apiUsername = apiUsername;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
