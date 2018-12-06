package com.varvashevich.dao;

import com.varvashevich.connection.ConnectionManager;
import com.varvashevich.dao.bookDao.BookDaoImpl;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BookDaoImplTest {

    private BookDaoImpl bookDaoImpl = BookDaoImpl.getInstance();
    private static final SessionFactory Factory = ConnectionManager.getFactory();

    @BeforeClass
    public static void insertTestData() {
        try (Session session = Factory.openSession()) {
            PublishingHouse act = savePublishingHouse(session, "АСТ");
            PublishingHouse eksmo = savePublishingHouse(session, "Эксмо");
            PublishingHouse amfora = savePublishingHouse(session, "Амфора");

            Genre horror = saveGenre(session, "Ужасы");
            Genre adventure = saveGenre(session, "Приключения");
            Genre fantastic = saveGenre(session, "Фантастика");

            saveBook(session, horror,"Оно", act, 2015, 450);
            saveBook(session, adventure,"Бездна Голодных глаз", eksmo, 2015, 450);
            saveBook(session, fantastic,"Зов предков", amfora, 2015, 450);
        }
    }

    @Test
    public void filterTest() {
        try (Session session = Factory.openSession()) {
            session.beginTransaction();
            BookFilterDto filters = BookFilterDto.of(Genre.builder().name("Ужасы").build(),PublishingHouse.builder().name("АСТ").build());
            LimitOffsetDto limitOffset = LimitOffsetDto.of(1,0);

            List<Book> books = BookDaoImpl.getInstance().filterBooks(session, filters, limitOffset);
            List<String> booksNames = books.stream().map(Book::getName).collect(toList());
            assertThat(booksNames, containsInAnyOrder("Оно"));
        }
    }

    @Test
    public void findByIdTest() {
        try (Session session = Factory.openSession()) {
            Book book = BookDaoImpl.getInstance().findById(session, 1L);
            assertEquals(book.getName(), "Оно");
        }
    }

    @Test
    public void saveTest() {
        try (Session session = Factory.openSession()) {
            session.beginTransaction();
            Book greenMile = Book.builder()
                    .name("Зеленая миля")
                    .yearPublishing(2014)
                    .pages(384)
                    .build();
            Long savedBookId = BookDaoImpl.getInstance().save(session, greenMile);
            session.getTransaction().commit();
            assertNotNull(savedBookId);
            session.beginTransaction();
            session.delete(greenMile);
            session.getTransaction().commit();
        }
    }

    @Test
    public void findAllTest() {
        try (Session session = Factory.openSession()) {
            session.beginTransaction();
            List<Book> results = bookDaoImpl.findAll(session);
            assertThat(results, hasSize(3));
            session.getTransaction().commit();
        }
    }

    @Test
    public void updateTest() {
        try (Session session = Factory.openSession()) {
            session.beginTransaction();
            Book book = session.find(Book.class, 3L);
            book.setName("Белый клык");
            bookDaoImpl.update(session, book);
            session.getTransaction().commit();
            book = session.find(Book.class, 3L);
            assertEquals(book.getName(), "Белый клык");
        }
    }

    @Test
    public void deleteTest() {
        try (Session session = Factory.openSession()) {
            session.beginTransaction();
            Book greenMile = Book.builder()
                    .name("Зеленая миля")
                    .yearPublishing(2014)
                    .pages(384)
                    .build();
            bookDaoImpl.save(session, greenMile);
            Long savedId = (Long) session.save(greenMile);
            session.getTransaction().commit();
            session.beginTransaction();
            bookDaoImpl.delete(session, greenMile);
            session.getTransaction().commit();
            session.beginTransaction();
            Book deletedBook = session.find(Book.class, savedId);
            assertNull(deletedBook);
        }
    }

    private static Book saveBook(Session session, Genre genre, String name, PublishingHouse publishingHouse, Integer yearPublishing, Integer pages) {
        Book book = Book.builder()
                .genre(genre)
                .name(name)
                .publishingHouse(publishingHouse)
                .yearPublishing(yearPublishing)
                .pages(pages)
                .build();
        session.save(book);
        return book;
    }

    private static PublishingHouse savePublishingHouse(Session session, String name) {
        PublishingHouse publishingHouse = PublishingHouse.builder()
                .name(name)
                .build();
        session.save(publishingHouse);
        return publishingHouse;
    }
    private static Genre saveGenre(Session session, String name) {
        Genre genre = Genre.builder()
                .name(name)
                .build();
        session.save(genre);
        return genre;
    }
}