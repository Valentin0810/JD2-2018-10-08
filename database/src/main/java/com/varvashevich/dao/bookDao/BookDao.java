package com.varvashevich.dao.bookDao;

import com.varvashevich.dao.baseDao.BaseDao;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Author;
import com.varvashevich.entity.Book;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;
import org.hibernate.Session;

import java.util.List;

public interface BookDao extends BaseDao<Long, Book> {

    List<Book> findByName(Session session, String name);

    List<Book> findByAuthor(Session session, Author author);

    List<Book> findByGenre(Session session, Genre genre);

    List<Book> findByPublishingHouse(Session session, PublishingHouse publishingHouse);

    List<Book> findByYearPublishing(Session session, Integer yearPublishing);

    List<Book> filterBooks(Session session, BookFilterDto filters, LimitOffsetDto limitOffset);
}