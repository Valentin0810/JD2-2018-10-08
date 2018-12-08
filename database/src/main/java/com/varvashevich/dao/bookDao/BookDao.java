package com.varvashevich.dao.bookDao;

import com.varvashevich.dao.baseDao.BaseDao;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Author;
import com.varvashevich.entity.Book;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;

import java.util.List;

public interface BookDao extends BaseDao<Long, Book> {

    List<Book> findByName(String name);

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(Genre genre);

    List<Book> findByPublishingHouse(PublishingHouse publishingHouse);

    List<Book> findByYearPublishing(Integer yearPublishing);

    List<Book> filterBooks(BookFilterDto filters, LimitOffsetDto limitOffset);
}