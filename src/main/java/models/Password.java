package models;

import javax.persistence.*;

@Entity
@Table(name = "Passwords")
@NamedQuery(name="allPasswordsQuery", query="select t from Password t")
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="password")
    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    public Password() { }

    public Password(String password, User user) {
        this.password = password;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
