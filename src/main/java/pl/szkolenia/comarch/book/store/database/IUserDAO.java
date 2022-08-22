package pl.szkolenia.comarch.book.store.database;

import pl.szkolenia.comarch.book.store.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
}
