package pl.szkolenia.comarch.book.store.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IOrderDAO;
import pl.szkolenia.comarch.book.store.model.Order;

import java.util.List;

@Repository
public class HibernateOrderDAO implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void persistOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(order);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getOrders() {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.szkolenia.comarch.book.store.model.Order");
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }
}
