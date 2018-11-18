package com.varvashevich.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class BookInLendingTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from BookInLending ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<BookInLending> list =
                    session.createQuery("select bil from BookInLending bil", BookInLending.class).list();
        }
    }

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            BookOrder bookOrder = BookOrder.builder()
                    .orderDate(LocalDate.of(2018, 10, 5))
                    .build();

            BookInLending bookInLending = BookInLending.builder()
                    .bookOrder(bookOrder)
                    .dateOfIssue(LocalDate.of(2018, 10, 5))
                    .returnDate(LocalDate.of(2018, 10, 19))
                    .build();
            Serializable id = session.save(bookInLending);
            assertNotNull(id);
        }
    }
}
