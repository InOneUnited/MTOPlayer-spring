package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Song")
@NamedQuery(name="allSongsQuery", query="select t from Song t")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="playlist_song",
            joinColumns = {@JoinColumn(name="song_id")},
            inverseJoinColumns = {@JoinColumn(name="playlist_id")})
    private Set<Playlist> playlists;

    @Column(name = "name")
    private String songName;

    @Column(name="link")
    private String songLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="apikey_id")
    private APIKey apiKey;

    public Song(){}

    public Song(Set<Playlist> playlists, String songName, String songLink, APIKey apiKey) {
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

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public APIKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(APIKey apiKey) {
        this.apiKey = apiKey;
    }
}
