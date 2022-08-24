package pl.szkolenia.comarch.book.store.database;

import pl.szkolenia.comarch.book.store.model.Order;

import java.util.List;

public interface IOrderDAO {
    void persistOrder(Order order);
    List<Order> getOrders();
}
