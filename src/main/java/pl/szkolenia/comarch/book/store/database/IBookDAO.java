package pl.szkolenia.comarch.book.store.database;

import pl.szkolenia.comarch.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> getBooks();
    Optional<Book> getBookById(int id);
    void updateBook(Book book);
}
