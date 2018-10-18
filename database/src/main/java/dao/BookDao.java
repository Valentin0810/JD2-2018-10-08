package dao;

import connection.ConnectionPool;
import entity.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookDao {

    private static final BookDao INSTANCE = new BookDao();

    private static final String FIND_ALL = "SELECT id, author, "
            + "name, publishing_house, the_year_of_publishing, pages FROM library.book";

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getLong("id"))
                        .author(resultSet.getString("author"))
                        .name(resultSet.getString("name"))
                        .publishing_house(resultSet.getString("publishing_house"))
                        .the_year_of_publishing(resultSet.getInt("the_year_of_publishing"))
                        .pages(resultSet.getInt("pages"))
                        .build();
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}