package pl.szkolenia.comarch.book.store.database.memory;

import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IUserDAO;
import pl.szkolenia.comarch.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDB implements IUserDAO {

    List<User> users = new ArrayList<>();

    public UserDB() {
        this.users.add(new User(1, "admin", "admin"));
        this.users.add(new User(2, "janusz", "janusz123"));
        this.users.add(new User(3, "karol", "karol123"));
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return this.users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
    }
}
