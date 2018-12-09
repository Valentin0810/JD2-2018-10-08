package service.service;

import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;
import com.varvashevich.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private BookService bookService;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> filterBooks(BookFilterDto filters, LimitOffsetDto limitOffset) {
        return bookRepository.filterBooks(filters, limitOffset);
    }
}