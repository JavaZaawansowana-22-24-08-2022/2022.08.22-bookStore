package pl.szkolenia.comarch.book.store.database.memory;

import org.springframework.stereotype.Repository;
import pl.szkolenia.comarch.book.store.database.IBookDAO;
import pl.szkolenia.comarch.book.store.model.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Book> getBookById(int id) {
        for(Book book : this.books) {
            if(book.getId() == id) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    @Override
    public void updateBook(Book book) {
        throw new NotImplementedException();
    }


}
