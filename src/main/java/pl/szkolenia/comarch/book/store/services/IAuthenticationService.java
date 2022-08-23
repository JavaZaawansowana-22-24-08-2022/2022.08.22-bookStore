package pl.szkolenia.comarch.book.store.services;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void logout();
}
