package com.varvashevich.entity;

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

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Genre").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }


    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<Genre> list =
                    session.createQuery("select g from Genre g", Genre.class).list();
        }
    }

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            Genre genre = Genre.builder()
                    .name("genre")
                    .build();
            Serializable id = session.save(genre);
            assertNotNull(id);
        }
    }
}
