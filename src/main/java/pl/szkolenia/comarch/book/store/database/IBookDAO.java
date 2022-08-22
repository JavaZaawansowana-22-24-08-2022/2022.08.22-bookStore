package pl.szkolenia.comarch.book.store.database;

import pl.szkolenia.comarch.book.store.model.Book;

import java.util.List;

public interface IBookDAO {
    List<Book> getBooks();
}
