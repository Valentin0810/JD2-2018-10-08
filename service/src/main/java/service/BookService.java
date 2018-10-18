package service;

import dao.BookDao;
import entity.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookService {

    private static final BookService INSTANCE = new BookService();

    public List<Book> findAll() {
        return BookDao.getInstance().findAll();
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}