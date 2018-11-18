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

public class AuthorTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Author").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }


    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<Author> list =
                    session.createQuery("select a from Author a", Author.class).list();
        }
    }

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            Author author = Author.builder()
                    .name("name")
                    .yearsOfLife("1977-")
                    .briefInformation("brief information")
                    .build();
            Serializable id = session.save(author);
            assertNotNull(id);
        }
    }

}