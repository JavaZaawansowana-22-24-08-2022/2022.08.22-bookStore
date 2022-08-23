package pl.szkolenia.comarch.book.store.database;

import pl.szkolenia.comarch.book.store.model.Order;

public interface IOrderDAO {
    void persistOrder(Order order);
}
