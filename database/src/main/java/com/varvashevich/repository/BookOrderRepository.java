package com.varvashevich.repository;

import com.varvashevich.entity.BookOrder;
import com.varvashevich.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookOrderRepository extends CrudRepository<BookOrder, Long> {

    List<BookOrder> getByUser(User user);

    List<BookOrder> getByOrderDate(LocalDate orderDate);
}
