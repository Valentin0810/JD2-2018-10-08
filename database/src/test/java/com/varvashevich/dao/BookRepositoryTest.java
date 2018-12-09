package com.varvashevich.dao;

import com.varvashevich.configuration.DatabaseConfiguration;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;
import com.varvashevich.repository.BookRepository;
import com.varvashevich.repository.GenreRepository;
import com.varvashevich.repository.PublishingHouseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
@Sql("classpath:test-script.sql")
@Transactional
public class BookRepositoryTest {

    @Autowired
    private EntityManager manager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Test
    public void filterTest() {
        BookFilterDto filters = BookFilterDto.of(Genre.builder().name("Ужасы").build(), PublishingHouse.builder().name("АСТ").build());
        LimitOffsetDto limitOffset = LimitOffsetDto.of(1, 0);
        List<Book> books = bookRepository.filterBooks(filters, limitOffset);
        List<String> booksNames = books.stream().map(Book::getName).collect(toList());
        assertThat(booksNames, containsInAnyOrder("Оно"));
    }

    @Test
    public void findByIdTest() {
        Optional<Book> ono = bookRepository.findBookByName("Оно");
        Long bookId = null;
        if (ono.isPresent()) {
            bookId = ono.get().getId();
        }
        manager.clear();
        Optional<Book> book = bookRepository.findById(Objects.requireNonNull(bookId));
        assertTrue(book.isPresent());
    }

    @Test
    public void saveTest() {
        Book greenMile = Book.builder()
                .name("Зеленая миля")
                .yearPublishing(2014)
                .pages(384)
                .build();
        Book savedBookId = bookRepository.save(greenMile);
        assertNotNull(savedBookId);
        bookRepository.delete(greenMile);
    }

    @Test
    public void findAllTest() {
        List<Book> results = bookRepository.findAll();
        assertThat(results, hasSize(3));
    }

    @Test
    public void deleteTest() {
        Book greenMile = Book.builder()
                .name("Зеленая миля")
                .yearPublishing(2014)
                .pages(384)
                .build();
        Book book = bookRepository.save(greenMile);
        Long savedBookId = book.getId();
        bookRepository.delete(greenMile);
        manager.flush();
        manager.clear();
        List<Book> allGames = new ArrayList<>();
        bookRepository.findAll().forEach(allGames::add);
        boolean present = allGames.stream().anyMatch(x -> savedBookId.equals(x.getId()));
        assertFalse(present);
    }
}