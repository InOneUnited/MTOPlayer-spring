package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Songs")
@NamedQuery(name="allSongsQuery", query="select t from Song t")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(cascade = {CascadeType.REMOVE})
    private List<Playlist> playlists;

    @Column(name = "name")
    private String songName;

    @Column(name="link")
    private String songLink;

    @ManyToOne(cascade = {CascadeType.ALL})
    private APIKey apiKey;

    public Song(){}

    public Song(List<Playlist> playlists, String songName, String songLink, APIKey apiKey) {
        this.playlists = playlists;
        this.songName = songName;
        this.songLink = songLink;
        this.apiKey = apiKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
