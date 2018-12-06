package com.varvashevich.entity;

import com.varvashevich.connection.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class GenreTest {

    private static final SessionFactory SESSION_FACTORY = ConnectionManager.getFactory();

//    @AfterClass
//    public static void closeFactory() {
//        SESSION_FACTORY.close();
//    }

    @Before
    public void clean() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Genre").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }


    @Test
    public void checkGetAll() {
        try (Session session = SESSION_FACTORY.openSession()) {
            List<Genre> list =
                    session.createQuery("select g from Genre g", Genre.class).list();
        }
    }

    @Test
    public void checkSave() {
        try (Session session = SESSION_FACTORY.openSession()) {
            Genre genre = Genre.builder()
                    .name("genre")
                    .build();
            Serializable id = session.save(genre);
            assertNotNull(id);
        }
    }
}