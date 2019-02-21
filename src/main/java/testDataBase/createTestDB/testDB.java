package testDataBase.createTestDB;

import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testDB {
    public static void populateDb(EntityManager em) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date dateNow = new Date();

        User user = new User();
        user.setUserLogin("mati");

        APIKey apiKey = new APIKey();
        apiKey.setApiToken("asdkpok23okpxl");
        apiKey.setUser(user);

        Salt salt = new Salt();
        salt.setSalt("askdoapsdkop");
        salt.setUser(user);

        Password password = new Password();
        password.setPassword("haslo");
        password.setUser(user);

        Playlist playlist = new Playlist();
        playlist.setPlaylistName("Do biegania");
        playlist.setCreationDate(dateNow);
        playlist.setUser(user);

        Song song = new Song();
        song.setSongName("Nothing Else Matter");
        song.setSongProvider("Dropbox");
        song.setSongLink("https://www.dropbox.com/mati/asdapsdko");
        song.setUser(user);
        song.setPlaylist(playlist);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(apiKey);
        em.persist(salt);
        em.persist(password);
        em.persist(playlist);
        em.persist(song);
        em.persist(user);


        transaction.commit();
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MTOplayer");
        EntityManager em = emf.createEntityManager();

        populateDb(em);
        em.clear(); //clear hibernate cache - force next statements to read data from db

        em.close();
        emf.close();

    }
}
