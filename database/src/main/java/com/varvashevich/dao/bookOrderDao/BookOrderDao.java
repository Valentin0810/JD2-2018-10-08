package com.varvashevich.dao.bookOrderDao;

import com.varvashevich.dao.baseDao.BaseDao;
import com.varvashevich.entity.BookOrder;
import com.varvashevich.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface BookOrderDao extends BaseDao<Long, BookOrder> {

    List<BookOrder> getByUser(User user);

    List<BookOrder> getByOrderDate(LocalDate orderDate);
}