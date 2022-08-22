package pl.szkolenia.comarch.book.store.database.memory;

import org.springframework.stereotype.Component;
import pl.szkolenia.comarch.book.store.database.IBookDAO;
import pl.szkolenia.comarch.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDB implements IBookDAO {
    private List<Book> books = new ArrayList<>();

    public BookDB() {
        this.books.add(new Book(1, "Jakas ksiazka 1", "Jakis Pan Jacek", 25.55, 10));
        this.books.add(new Book(2, "Jakas ksiazka 2", "Jakis Pan Marek", 40.55, 10));
        this.books.add(new Book(3, "Jakas ksiazka 3", "Jakis Pan Artur", 38.55, 10));
        this.books.add(new Book(4, "Jakas ksiazka 4", "Jakis Pan Mateusz", 99.55, 10));
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }
}
