package com.MTOPlayer.helpers;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class SingletonEntityManagerFactory {
    private static EntityManagerFactory entityManagerFactory = null;

    public static EntityManagerFactory getInstance() throws IOException {
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("MTOplayer");
        }

        return entityManagerFactory;
    }
}
