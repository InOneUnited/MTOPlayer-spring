package models;

import javax.persistence.*;

@Entity
@Table(name = "Songs")
@NamedQuery(name="allSongsQuery", query="select t from Song t")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    private Playlist playlist;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    @Column(name = "name")
    private String songName;

    @Column(name="provider")
    private String songProvider;

    @Column(name="link")
    private String songLink;

    public Song(){}

    public Song(Playlist playlist, User user, String songName, String songProvider, String songLink) {
        this.playlist = playlist;
        this.user = user;
        this.songName = songName;
        this.songProvider = songProvider;
        this.songLink = songLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongProvider() {
        return songProvider;
    }

    public void setSongProvider(String songProvider) {
        this.songProvider = songProvider;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }
}
