package com.varvashevich.dao;

import com.varvashevich.entity.Book;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDao {
    private static final BookDao INSTANCE = new BookDao();

    public List<Book> findAll(Session session) {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session findAllBookSession = sessionFactory.openSession()) {
            findAllBookSession.beginTransaction();
            List<Book> books = findAllBookSession
                    .createQuery("select b from Book b", Book.class).list();
            findAllBookSession.getTransaction().commit();
            return books;
        }
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}