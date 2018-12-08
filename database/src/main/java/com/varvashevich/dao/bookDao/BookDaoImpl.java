package com.varvashevich.dao.bookDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Author;
import com.varvashevich.entity.Author_;
import com.varvashevich.entity.Book;
import com.varvashevich.entity.Book_;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.Genre_;
import com.varvashevich.entity.PublishingHouse;
import com.varvashevich.entity.PublishingHouse_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;

@Repository
public class BookDaoImpl extends BaseDaoImpl<Long, Book> implements BookDao {

    @Override
    public Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    public List<Book> findByName(String name) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root).where(
                cb.equal(root.get(Book_.name), name)
        );
        return getSessionFactory().getCurrentSession().createQuery(criteria).list();
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        SetJoin<Book, Author> authorJoin = root.join(Book_.authors);

        criteria.select(root).where(
                cb.equal(authorJoin.get(Author_.name), author.getName())
        );

        return getSessionFactory().getCurrentSession().createQuery(criteria).list();
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, Genre> bookGenreJoin = root.join(Book_.genre);

        criteria.select(root).where(
                cb.equal(bookGenreJoin.get(Genre_.name), genre.getName())
        );
        return getSessionFactory().getCurrentSession().createQuery(criteria).list();
    }

    @Override
    public List<Book> findByPublishingHouse(PublishingHouse publishingHouse) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, PublishingHouse> bookPublishingHouseJoin = root.join(Book_.publishingHouse);

        criteria.select(root).where(
                cb.equal(bookPublishingHouseJoin.get(PublishingHouse_.name), publishingHouse.getName())
        );
        return getSessionFactory().getCurrentSession().createQuery(criteria).list();
    }

    @Override
    public List<Book> findByYearPublishing(Integer yearPublishing) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);

        criteria.select(root).where(
                cb.equal(root.get(Book_.yearPublishing), yearPublishing)
        );
        return getSessionFactory().getCurrentSession().createQuery(criteria).list();
    }

    @Override
    public List<Book> filterBooks(BookFilterDto filters, LimitOffsetDto limitOffset) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, Genre> bookGenreJoin = root.join(Book_.genre);
        Join<Book, PublishingHouse> bookPublishingHouseJoin = root.join(Book_.publishingHouse);
        criteria.select(root).where(
                cb.equal(bookGenreJoin.get(Genre_.name), String.valueOf(filters.getGenre().getName())),
                cb.equal(bookPublishingHouseJoin.get(PublishingHouse_.name), String.valueOf(filters.getPublishingHouse().getName()))
        );

        return getSessionFactory().getCurrentSession().createQuery(criteria)
                .setMaxResults(limitOffset.getLimit())
                .setFirstResult(limitOffset.getOffset())
                .list();
    }
}