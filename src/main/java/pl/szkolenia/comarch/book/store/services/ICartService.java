package pl.szkolenia.comarch.book.store.services;

import pl.szkolenia.comarch.book.store.model.view.CartPosition;

import java.util.List;

public interface ICartService {
    void addToCart(int bookId);
    List<CartPosition> getViewModelCart();
    void confirmOrder();
}
