package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.model.Book;
import dawidkruczek.projectII.librarysystem.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBook(String id) {
        return repository.findById(id);
    }

    public void addBook(Book book) {
        repository.insert(book);
    }

    public void updateBook(String id, Book newBook) {
        newBook.setId(id);
        repository.save(newBook);
    }

    public void deleteBook(String id) {
        repository.delete(id);
    }
}
