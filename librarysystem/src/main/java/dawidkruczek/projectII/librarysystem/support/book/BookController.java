package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/books")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable long id) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public void updateBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }
}
