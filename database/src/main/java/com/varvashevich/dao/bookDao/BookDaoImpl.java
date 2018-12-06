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
import lombok.AccessLevel;

import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDaoImpl extends BaseDaoImpl<Long, Book> implements BookDao {

    private static final BookDaoImpl INSTANCE = new BookDaoImpl();

    public static BookDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    public List<Book> findByName(Session session, String name) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root).where(
                cb.equal(root.get(Book_.name), name)
        );
        return session.createQuery(criteria).list();
    }

    @Override
    public List<Book> findByAuthor(Session session, Author author) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        SetJoin<Book, Author> authorJoin = root.join(Book_.authors);

        criteria.select(root).where(
                cb.equal(authorJoin.get(Author_.name), author.getName())
        );

        return session.createQuery(criteria).list();
    }

    @Override
    public List<Book> findByGenre(Session session, Genre genre) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, Genre> bookGenreJoin = root.join(Book_.genre);

        criteria.select(root).where(
                cb.equal(bookGenreJoin.get(Genre_.name), genre.getName())
        );
        return session.createQuery(criteria).list();
    }

    @Override
    public List<Book> findByPublishingHouse(Session session, PublishingHouse publishingHouse) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, PublishingHouse> bookPublishingHouseJoin = root.join(Book_.publishingHouse);

        criteria.select(root).where(
                cb.equal(bookPublishingHouseJoin.get(PublishingHouse_.name), publishingHouse.getName())
        );
        return session.createQuery(criteria).list();
    }

    @Override
    public List<Book> findByYearPublishing(Session session, Integer yearPublishing) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);

        criteria.select(root).where(
                cb.equal(root.get(Book_.yearPublishing), yearPublishing)
        );
        return session.createQuery(criteria).list();
    }

    @Override
    public List<Book> filterBooks(Session session, BookFilterDto filters, LimitOffsetDto limitOffset) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, Genre> bookGenreJoin = root.join(Book_.genre);
        Join<Book, PublishingHouse> bookPublishingHouseJoin = root.join(Book_.publishingHouse);
                criteria.select(root).where(
                cb.equal(bookGenreJoin.get(Genre_.name), String.valueOf(filters.getGenre().getName())),
                cb.equal(bookPublishingHouseJoin.get(PublishingHouse_.name), String.valueOf(filters.getPublishingHouse().getName()))
        );

        return session.createQuery(criteria)
                .setMaxResults(limitOffset.getLimit())
                .setFirstResult(limitOffset.getOffset())
                .list();
    }
}