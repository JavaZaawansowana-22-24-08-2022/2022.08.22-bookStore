package pl.szkolenia.comarch.book.store.database.memory;

import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IOrderDAO;
import pl.szkolenia.comarch.book.store.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDB implements IOrderDAO {

    private List<Order> orders = new ArrayList<>();


    @Override
    public void persistOrder(Order order) {
        this.orders.add(order);
    }
}
