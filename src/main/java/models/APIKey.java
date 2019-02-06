package models;

import javax.persistence.*;

@Entity
@Table(name="APIkeys")
@NamedQuery(name="allAPIKeysQuery", query="select t from APIKey t")
public class APIKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="token")
    private String apiToken;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    public APIKey(){}

    public APIKey(String apiToken, User user) {
        this.apiToken = apiToken;
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
}
