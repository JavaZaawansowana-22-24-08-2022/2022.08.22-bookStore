package pl.szkolenia.comarch.book.store.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IUserDAO;
import pl.szkolenia.comarch.book.store.model.User;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class HibernateUserDAO implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query =
                session.createQuery("FROM pl.szkolenia.comarch.book.store.model.User WHERE login = :login");
        query.setParameter("login", login);
        try {
            User result = query.getSingleResult();
            session.close();
            return Optional.of(result);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }
}
