package models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "Sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    @Temporal(TemporalType.TIME)
    @Column(name = "time_start")
    private Time timeStart;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_expiration")
    private Date dateExpiration;

    @Temporal(TemporalType.TIME)
    @Column(name = "time_expiration")
    private Time timeExpiration;

    @Column(name = "ip")
    private String ip;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;

    public Session() {}

    public Session(Date dateStart, Time timeStart, Date dateExpiration, Time timeExpiration, String ip, User user) {
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.dateExpiration = dateExpiration;
        this.timeExpiration = timeExpiration;
        this.ip = ip;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getdateStart() {
        return dateStart;
    }

    public void setdateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getdateExpiration() {
        return dateExpiration;
    }

    public void setdateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Time getTimeExpiration() {
        return timeExpiration;
    }

    public void setTimeExpiration(Time timeExpiration) {
        this.timeExpiration = timeExpiration;
    }
}
