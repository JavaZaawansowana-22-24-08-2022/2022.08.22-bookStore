package pl.szkolenia.comarch.book.store.model.dto;

import pl.szkolenia.comarch.book.store.model.Order;

import java.util.List;

public class OrderListResponse {
    private List<Order> orders;

    public OrderListResponse(List<Order> orders) {
        this.orders = orders;
    }

    public OrderListResponse() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
