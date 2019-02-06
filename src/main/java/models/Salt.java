package models;

import javax.persistence.*;

@Entity
@Table(name = "Salt")
@NamedQuery(name="allSaltsQuery", query="select t from Salt t")
public class Salt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "content")
    private String salt;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    public Salt(){}

    public Salt(String salt, User user) {
        this.salt = salt;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
