package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping("/books")
    public ResponseEntity<List<String>> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<List<String>> updateBook(@RequestBody Book book, @PathVariable String id) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
