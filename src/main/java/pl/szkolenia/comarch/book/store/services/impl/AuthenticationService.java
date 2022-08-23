package pl.szkolenia.comarch.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szkolenia.comarch.book.store.database.IUserDAO;
import pl.szkolenia.comarch.book.store.model.User;
import pl.szkolenia.comarch.book.store.services.IAuthenticationService;
import pl.szkolenia.comarch.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if(userBox.isPresent() && userBox.get().getPassword().equals(password)) {
            this.sessionObject.setUser(userBox.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }
}
