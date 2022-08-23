package pl.szkolenia.comarch.book.store.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IBookDAO;
import pl.szkolenia.comarch.book.store.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCBookDAO implements IBookDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return Optional.of(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Book book) {
        try {
            String sql = "UPDATE tbook SET title = ?, author = ?, price = ?, quantity = ? WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
