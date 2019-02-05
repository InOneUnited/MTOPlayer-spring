package models;

import javax.persistence.*;

@Entity
@Table(name = "Passwords")
public class Password {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="password")
    private String password;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;

}
