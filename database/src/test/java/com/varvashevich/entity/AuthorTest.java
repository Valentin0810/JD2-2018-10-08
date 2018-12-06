package com.varvashevich.entity;

import com.varvashevich.connection.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class AuthorTest {

    private static final SessionFactory SESSION_FACTORY = ConnectionManager.getFactory();

//    @AfterClass
//    public static void closeFactory() {
//        SESSION_FACTORY.close();
//    }

    @Before
    public void clean() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Author").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = SESSION_FACTORY.openSession()) {
            List<Author> allAuthor =
                    session.createQuery("select a from Author a", Author.class).list();
        }
    }

    @Test
    public void checkSave() {
        try (Session session = SESSION_FACTORY.openSession()) {
            Author author = Author.builder()
                    .name("name")
                    .dateBirth("1977-")
                    .dateDeath("наст. время")
                    .briefInformation("brief information")
                    .build();
            Serializable id = session.save(author);
            assertNotNull(id);
        }
    }
}