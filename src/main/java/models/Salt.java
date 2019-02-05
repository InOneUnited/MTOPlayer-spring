package models;

import javax.persistence.*;

@Entity
@Table(name = "Salt")
public class Salt {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name = "content")
    private String salt;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;

}
