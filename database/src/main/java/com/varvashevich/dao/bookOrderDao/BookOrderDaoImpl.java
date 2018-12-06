package com.varvashevich.dao.bookOrderDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.BookOrder;
import com.varvashevich.entity.BookOrder_;
import com.varvashevich.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookOrderDaoImpl extends BaseDaoImpl<Long, BookOrder> implements BookOrderDao {

    private static final BookOrderDaoImpl INSTANCE = new BookOrderDaoImpl();

    public static BookOrderDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<BookOrder> getEntityClass() {
        return BookOrder.class;
    }


    @Override
    public List<BookOrder> getByUser(Session session, User user) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BookOrder> criteria = cb.createQuery(BookOrder.class);
        Root<BookOrder> root = criteria.from(BookOrder.class);
        criteria.select(root)
                .where(
                        cb.equal(root.get(BookOrder_.user), user)
                );
        return session.createQuery(criteria).list();
    }

    @Override
    public List<BookOrder> getByOrderDate(Session session, LocalDate orderDate) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BookOrder> criteria = cb.createQuery(BookOrder.class);
        Root<BookOrder> root = criteria.from(BookOrder.class);
        criteria.select(root)
                .where(
                        cb.equal(root.get(BookOrder_.orderDate), orderDate)
                );
        return session.createQuery(criteria).list();
    }

}