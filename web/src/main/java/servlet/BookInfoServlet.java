package servlet;

import com.varvashevich.entity.Book;
import connection.ContextUtil;
import service.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/book-info")
public class BookInfoServlet extends HttpServlet {

    private BookService bookService = ContextUtil.getContext().getBean("bookService", BookService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.findAll();
        req.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/book-info.jsp")
                .forward(req, resp);
    }
}