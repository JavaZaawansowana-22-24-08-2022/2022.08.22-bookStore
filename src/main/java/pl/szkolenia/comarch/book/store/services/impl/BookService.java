package pl.szkolenia.comarch.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szkolenia.comarch.book.store.database.IBookDAO;
import pl.szkolenia.comarch.book.store.model.Book;
import pl.szkolenia.comarch.book.store.services.IBookService;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getBooks();
    }
}
