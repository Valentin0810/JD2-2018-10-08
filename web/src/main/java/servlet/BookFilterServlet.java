package servlet;

import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;
import connection.ContextUtil;
import service.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book-filter")
public class BookFilterServlet extends HttpServlet {

    private BookService bookService = ContextUtil.getContext().getBean("bookService", BookService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookFilterDto filters = BookFilterDto.builder().build();
        LimitOffsetDto limitOffset = LimitOffsetDto.builder().build();
        req.setAttribute("booksFiltered", bookService.filterBooks(filters, limitOffset));
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/book-filter.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookFilterDto bookFilterDto = BookFilterDto.builder()
                .genre(Genre.builder().name(req.getParameter("genre")).build())
                .publishingHouse(PublishingHouse.builder().name(req.getParameter("publishingHouse")).build())
                .build();
        LimitOffsetDto limitOffsetDto = LimitOffsetDto.builder()
                .limit(Integer.valueOf(req.getParameter("limit")))
                .offset(Integer.valueOf(req.getParameter("offset")))
                .build();
        resp.sendRedirect("/book-filtered");
    }
}