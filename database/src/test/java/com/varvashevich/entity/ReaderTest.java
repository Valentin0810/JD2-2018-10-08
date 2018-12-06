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

public class ReaderTest {

    private static final SessionFactory SESSION_FACTORY = ConnectionManager.getFactory();

//    @AfterClass
//    public static void closeFactory() {
//        SESSION_FACTORY.close();
//    }

    @Before
    public void clean() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Reader").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }


    @Test
    public void checkGetAll() {
        try (Session session = SESSION_FACTORY.openSession()) {
            List<Reader> list =
                    session.createQuery("select r from Reader r", Reader.class).list();
        }
    }

    @Test
    public void checkSave() {
        try (Session session = SESSION_FACTORY.openSession()) {
            UserDetail userDetail = UserDetail.builder()
                    .address("address")
                    .phoneNumber(7777777)
                    .build();
            User reader = new Reader("Reader", userDetail, Role.READER, "login", "password", "passport");
            Serializable id = session.save(reader);
            assertNotNull(id);
        }
    }
}