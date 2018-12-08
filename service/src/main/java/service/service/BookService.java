package service.service;

import com.varvashevich.dao.bookDao.BookDao;
import com.varvashevich.dao.bookDao.BookDaoImpl;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookDaoImpl bookDao;

    @Autowired
    public BookService bookService;

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public List<Book> filterBooks(BookFilterDto filters, LimitOffsetDto limitOffset) {
        return bookDao.filterBooks(filters, limitOffset);
    }
}