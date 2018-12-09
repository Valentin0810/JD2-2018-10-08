package com.varvashevich.repository;

import com.varvashevich.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Long>, FilterBookRepository {


    List<Book> findAll();

    Optional<Book> findBookByName(String name);

    Set<Book> findByAuthors(String name);

    List<Book> findBooksByGenre(String name);

    List<Book> findBooksByPublishingHouse(String name);
}