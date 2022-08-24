package pl.szkolenia.comarch.book.store.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderPosition> orderPositions = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(int id, User user, List<OrderPosition> orderPositions, Status status) {
        this.id = id;
        this.user = user;
        this.orderPositions = orderPositions;
        this.status = status;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        NEW,
        CONFIRMED,
        PAID,
        DONE
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderPositions=" + orderPositions +
                ", status=" + status +
                '}';
    }
}
