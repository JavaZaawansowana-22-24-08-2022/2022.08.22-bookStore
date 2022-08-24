package pl.szkolenia.comarch.book.store.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IOrderDAO;
import pl.szkolenia.comarch.book.store.model.Order;
import pl.szkolenia.comarch.book.store.model.OrderPosition;

import java.sql.*;
import java.util.List;

public class JDBCOrderDAO implements IOrderDAO {

    @Autowired
    Connection connection;
    @Override
    public void persistOrder(Order order) {
        try {
            String sql = "INSERT INTO torder (user_id, status) VALUES (?,?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getUser().getId());
            preparedStatement.setString(2, order.getStatus().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            order.setId(resultSet.getInt(1));

            for(OrderPosition orderPosition : order.getOrderPositions()) {
                String sql2 = "INSERT INTO torderposition (book_id, order_id, quantity) VALUES (?,?,?)";

                PreparedStatement preparedStatement2 = this.connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                preparedStatement2.setInt(1, orderPosition.getBook().getId());
                preparedStatement2.setInt(2, order.getId());
                preparedStatement2.setInt(3, orderPosition.getQuantity());

                preparedStatement2.executeUpdate();

                ResultSet resultSet2 = preparedStatement2.getGeneratedKeys();
                resultSet2.next();
                orderPosition.setId(resultSet2.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }
}
