package com.varvashevich.repository;

import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;
import com.varvashevich.entity.Book_;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.Genre_;
import com.varvashevich.entity.PublishingHouse;
import com.varvashevich.entity.PublishingHouse_;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class FilterBookRepositoryImpl implements FilterBookRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Book> filterBooks(BookFilterDto filters, LimitOffsetDto limitOffset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, Genre> bookGenreJoin = root.join(Book_.genre);
        Join<Book, PublishingHouse> bookPublishingHouseJoin = root.join(Book_.publishingHouse);
        criteria.select(root).where(
                cb.equal(bookGenreJoin.get(Genre_.name), String.valueOf(filters.getGenre().getName())),
                cb.equal(bookPublishingHouseJoin.get(PublishingHouse_.name), String.valueOf(filters.getPublishingHouse().getName()))
        );

        return entityManager.createQuery(criteria)
                .setMaxResults(limitOffset.getLimit())
                .setFirstResult(limitOffset.getOffset())
                .getResultList();
    }
}
