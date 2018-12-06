package com.varvashevich.dao.bookOrderDao;

import com.varvashevich.dao.baseDao.BaseDao;
import com.varvashevich.entity.BookOrder;
import com.varvashevich.entity.User;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public interface BookOrderDao extends BaseDao<Long, BookOrder> {

    List<BookOrder> getByUser(Session session, User user);

    List<BookOrder> getByOrderDate(Session session, LocalDate orderDate);
}