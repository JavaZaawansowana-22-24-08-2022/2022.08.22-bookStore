package pl.szkolenia.comarch.book.store.model.view;

public class CartPosition {
    private String title;
    private double price;
    private int quantity;

    public CartPosition(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public CartPosition() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
