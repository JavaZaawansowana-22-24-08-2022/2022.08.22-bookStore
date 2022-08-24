package pl.szkolenia.comarch.book.store.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IBookDAO;
import pl.szkolenia.comarch.book.store.model.Book;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class HibernateBookDAO implements IBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Book> getBooks() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.szkolenia.comarch.book.store.model.Book");
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            Query<Book> query = session.createQuery("FROM pl.szkolenia.comarch.book.store.model.Book WHERE id = :id");
            query.setParameter("id", id);
            Book book = query.getSingleResult();
            session.close();
            return Optional.of(book);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List<Book> getBooksFromProcedure() {
        /*Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createSQLQuery("CALL procedureName(:parametrs)");
        query.setParameter("parameters", "asd");
        return query.getResultList();*/

        Session session = this.sessionFactory.openSession();
        Query query = session.getNamedQuery("procedura").setParameter("parameter", "jhsgdf");
        return query.getResultList();
    }
}
