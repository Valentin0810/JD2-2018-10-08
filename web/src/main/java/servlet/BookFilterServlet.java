package servlet;

import com.varvashevich.dto.BookFilterDto;
import com.varvashevich.dto.LimitOffsetDto;
import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book-filter")
public class BookFilterServlet extends HttpServlet {

    private BookFilterDto bookFilterDto;
    private LimitOffsetDto limitOffsetDto;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookFilterDto filters = BookFilterDto.builder().build();

        req.setAttribute("booksFiltered", BookService.getInstance().filterBooks());
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