package helpers;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SingletonEntityManagerFactory {
    private static EntityManagerFactory entityManagerFactory = null;

    public static EntityManagerFactory getInstance() throws IOException {
        if(entityManagerFactory == null){
            Properties props = new Properties();
            props.load(new FileInputStream("../resources/META-INF/persistence.properties"));
            entityManagerFactory = Persistence.createEntityManagerFactory("MTOPlayer");
        }

        return entityManagerFactory;
    }
}
