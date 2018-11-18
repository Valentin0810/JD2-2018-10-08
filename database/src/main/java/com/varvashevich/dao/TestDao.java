package com.varvashevich.dao;

import com.varvashevich.entity.Book;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class TestDao {
    public static void main(String[] args) {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Book> query = session
                .createQuery("select b from Book b", Book.class);
        query.list().forEach(System.out::println);
        session.getTransaction().commit();
    }
}