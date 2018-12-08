package com.varvashevich.dao.bookOrderDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.BookOrder;
import com.varvashevich.entity.BookOrder_;
import com.varvashevich.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookOrderDaoImpl extends BaseDaoImpl<Long, BookOrder> implements BookOrderDao {

    @Override
    public Class<BookOrder> getEntityClass() {
        return BookOrder.class;
    }


    @Override
    public List<BookOrder> getByUser(User user) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<BookOrder> criteria = cb.createQuery(BookOrder.class);
        Root<BookOrder> root = criteria.from(BookOrder.class);
        criteria.select(root)
                .where(
                        cb.equal(root.get(BookOrder_.user), user)
                );
        return getSessionFactory().getCurrentSession().createQuery(criteria).list();
    }

    @Override
    public List<BookOrder> getByOrderDate(LocalDate orderDate) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<BookOrder> criteria = cb.createQuery(BookOrder.class);
        Root<BookOrder> root = criteria.from(BookOrder.class);
        criteria.select(root)
                .where(
                        cb.equal(root.get(BookOrder_.orderDate), orderDate)
                );
        return getSessionFactory().getCurrentSession().createQuery(criteria).list();
    }
}