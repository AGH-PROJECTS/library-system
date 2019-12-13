package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Book;
import dawidkruczek.projectII.librarysystem.model.Category;
import dawidkruczek.projectII.librarysystem.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(0,"9788365458872",new Category(0,"Patriotyczna"),"Pan Tadeusz",new Author(3,"Adam", "Mickiewicz","24.12.1798"),new Publisher(0,"BOOKS"),"2017"),
            new Book(0,"9788365458872",new Category(0,"Patriotyczna"),"Pan Tadeusz",new Author(3,"Adam", "Mickiewicz","24.12.1798"),new Publisher(0,"BOOKS"),"2017"),
            new Book(0,"9788365458872",new Category(0,"Patriotyczna"),"Pan Tadeusz",new Author(3,"Adam", "Mickiewicz","24.12.1798"),new Publisher(0,"BOOKS"),"2017")
    ));

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBook(long id) {
        return books.stream().filter(book -> book.getId()==id).findFirst().get();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(long id, Book book) {
        books.set(Math.toIntExact(id),book);
    }

    public void deleteBook(long id) {
        books.remove(id);
    }
}
