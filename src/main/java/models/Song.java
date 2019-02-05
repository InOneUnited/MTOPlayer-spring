package models;

import javax.persistence.*;

@Entity
@Table(name = "Songs")
public class Song {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @Column(name = "name")
    private String songName;

    @Column(name="provider")
    private String songProvider;

    @Column(name="link")
    private String songLink;

}
