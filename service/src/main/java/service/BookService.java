package service;

import com.varvashevich.dao.BookDao;
import com.varvashevich.entity.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookService {

    private static final BookService INSTANCE = new BookService();
    public static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();


    public List<Book> findAll() {
        try (Session session = SESSION_FACTORY.openSession()) {
            return BookDao.getInstance().findAll(session);
        }
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
