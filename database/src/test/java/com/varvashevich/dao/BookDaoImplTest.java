package com.varvashevich.dao;

import com.varvashevich.configuration.DatabaseConfiguration;
import com.varvashevich.dao.bookDao.BookDaoImpl;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
@Sql("classpath:test-script.sql")
@Transactional
public class BookDaoImplTest {

    @Autowired
    private BookDaoImpl bookDao;


    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void filterTest() {
        BookFilterDto filters = BookFilterDto.of(Genre.builder().name("Ужасы").build(), PublishingHouse.builder().name("АСТ").build());
        LimitOffsetDto limitOffset = LimitOffsetDto.of(1, 0);
        List<Book> books = bookDao.filterBooks(filters, limitOffset);
        List<String> booksNames = books.stream().map(Book::getName).collect(toList());
        assertThat(booksNames, containsInAnyOrder("Оно"));
    }

    @Test
    public void findByIdTest() {
        Book book = bookDao.findById(1L);
        assertEquals(book.getName(), "Оно");
    }

    @Test
    public void saveTest() {
        Book greenMile = Book.builder()
                .name("Зеленая миля")
                .yearPublishing(2014)
                .pages(384)
                .build();
        Long savedBookId = bookDao.save(greenMile);
        assertNotNull(savedBookId);
        bookDao.delete(greenMile);
    }

    @Test
    public void findAllTest() {
        List<Book> results = bookDao.findAll();
        assertThat(results, hasSize(3));
    }

    @Test
    public void updateTest() {
        Book book = bookDao.findById(3L);
        book.setName("Белый клык");
        bookDao.update(book);
        Book nameBook = bookDao.findById(3L);
        assertEquals(book.getName(), "Белый клык");
    }

    @Test
    public void deleteTest() {
        Book greenMile = Book.builder()
                .name("Зеленая миля")
                .yearPublishing(2014)
                .pages(384)
                .build();
        bookDao.save(greenMile);
        Long savedId = bookDao.save(greenMile);
        bookDao.delete(greenMile);
        Book deletedBook = bookDao.findById(savedId);
        assertNull(deletedBook);
    }
}