package com.varvashevich.repository;

import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;

import java.util.List;

public interface FilterBookRepository {

    List<Book> filterBooks(BookFilterDto bookFilterDto, LimitOffsetDto limitOffsetDto);
}
