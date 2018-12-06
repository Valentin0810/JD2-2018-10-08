package com.varvashevich.entity;

import com.varvashevich.connection.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class BookTest {

    private static final SessionFactory SESSION_FACTORY = ConnectionManager.getFactory();

//    @AfterClass
//    public static void closeFactory() {
//        SESSION_FACTORY.close();
//    }

    @Before
    public void clean() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Book ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = SESSION_FACTORY.openSession()) {
            List<Book> list =
                    session.createQuery("select b from Book b", Book.class).list();
        }
    }

    @Test
    public void checkSave() {
        try (Session session = SESSION_FACTORY.openSession()) {
            Book book = Book.builder()
                    .name("Сияние")
                    .yearPublishing(1977)
                    .pages(447)
                    .build();
            Serializable id = session.save(book);
            assertNotNull(id);
        }
    }
}