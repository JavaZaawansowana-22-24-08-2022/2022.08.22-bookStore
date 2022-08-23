package pl.szkolenia.comarch.book.store.services;

import pl.szkolenia.comarch.book.store.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
}
