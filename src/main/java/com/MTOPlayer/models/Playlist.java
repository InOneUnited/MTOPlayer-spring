package com.MTOPlayer.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Playlist")
@NamedQuery(name = "allPlaylistsQuery", query = "select t from Playlist t")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String playlistName;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_day")
    private Date creationDay;

    @Column(name = "creation_time")
    private Time creationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id")
    private User user;

    @ManyToMany(mappedBy = "playlists")
    private Set<Song> songs;


    public Playlist() {
    }

    public Playlist(String playlistName, Date creationDay, Time creationTime, User user, Set<Song> songs) {
        this.playlistName = playlistName;
        this.creationDay = creationDay;
        this.creationTime = creationTime;
        this.user = user;
        this.songs = songs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Date getCreationDay() {
        return creationDay;
    }

    public void setCreationDay(Date creationDay) {
        this.creationDay = creationDay;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }
}
