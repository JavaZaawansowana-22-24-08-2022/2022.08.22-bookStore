package pl.szkolenia.comarch.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szkolenia.comarch.book.store.database.IBookDAO;
import pl.szkolenia.comarch.book.store.database.IOrderDAO;
import pl.szkolenia.comarch.book.store.model.Book;
import pl.szkolenia.comarch.book.store.model.Cart;
import pl.szkolenia.comarch.book.store.model.Order;
import pl.szkolenia.comarch.book.store.model.OrderPosition;
import pl.szkolenia.comarch.book.store.model.view.CartPosition;
import pl.szkolenia.comarch.book.store.services.ICartService;
import pl.szkolenia.comarch.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public void addToCart(int bookId) {
        Integer bookQuantity = this.sessionObject.getCart().getCartPositions().get(bookId);
        if(bookQuantity == null) {
            this.sessionObject.getCart().getCartPositions().put(bookId, 1);
        } else {
            this.sessionObject.getCart().getCartPositions().put(bookId, bookQuantity + 1);
        }
    }

    @Override
    public List<CartPosition> getViewModelCart() {
        List<CartPosition> cartPositions = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : this.sessionObject.getCart().getCartPositions().entrySet()) {
            Optional<Book> bookBox = this.bookDAO.getBookById(entry.getKey());
            CartPosition cartPosition = new CartPosition();
            cartPosition.setTitle(bookBox.get().getTitle());
            cartPosition.setPrice(bookBox.get().getPrice());
            cartPosition.setQuantity(entry.getValue());
            cartPositions.add(cartPosition);
        }

        return cartPositions;
    }

    @Override
    public void confirmOrder() {
        Cart cart = this.sessionObject.getCart();
        Order order = new Order();
        order.setStatus(Order.Status.NEW);
        order.setUser(this.sessionObject.getUser());

        for(Map.Entry<Integer, Integer> entry : cart.getCartPositions().entrySet()) {
            OrderPosition orderPosition = new OrderPosition();
            Optional<Book> bookBox = this.bookDAO.getBookById(entry.getKey());
            orderPosition.setBook(bookBox.get());
            orderPosition.setQuantity(entry.getValue());
            order.getOrderPositions().add(orderPosition);

            bookBox.get().setQuantity(bookBox.get().getQuantity() - entry.getValue());
            this.bookDAO.updateBook(bookBox.get());
        }

        this.orderDAO.persistOrder(order);

        cart.getCartPositions().clear();
    }
}
