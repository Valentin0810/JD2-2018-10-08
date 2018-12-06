package service;

import com.varvashevich.connection.ConnectionManager;
import com.varvashevich.dao.bookDao.BookDaoImpl;
import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookService {

    private static final BookService INSTANCE = new BookService();
    public static final SessionFactory Factory = ConnectionManager.getFactory();

    public Book findById(){
        try (Session session = Factory.openSession()){
            return BookDaoImpl.getInstance().findById(session,1L);
        }
    }

    public List<Book> findAll() {
        try (Session session = Factory.openSession()) {
            return BookDaoImpl.getInstance().findAll(session);
        }
    }

    public List<Book> filterBooks(){
        try (Session session = Factory.openSession()){
            BookFilterDto filters = BookFilterDto.builder().build();
            LimitOffsetDto limitOffset = LimitOffsetDto.builder().build();
            return BookDaoImpl.getInstance().filterBooks(session,filters,limitOffset);
        }
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}