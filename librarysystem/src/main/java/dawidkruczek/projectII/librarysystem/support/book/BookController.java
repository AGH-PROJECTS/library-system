package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @PostMapping("/books")
    public List<String> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/books/{id}")
    public List<String> updateBook(@RequestBody Book book, @PathVariable String id) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }
}
