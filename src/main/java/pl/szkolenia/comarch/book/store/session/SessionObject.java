package pl.szkolenia.comarch.book.store.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.szkolenia.comarch.book.store.model.Cart;
import pl.szkolenia.comarch.book.store.model.User;

@Component
@SessionScope
public class SessionObject {
    private User user = null;
    private Cart cart = new Cart();

    public boolean isLogged() {
        return user != null;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
