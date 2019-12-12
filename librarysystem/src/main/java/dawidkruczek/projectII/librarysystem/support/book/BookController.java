package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/books")
    public List<Author> getAllAuthors() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Author getAuthor(@PathVariable long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/books")
    public void addAuthor(@RequestBody Author author) {
        bookService.addBook(author);
    }

    @PutMapping("/books/{id}")
    public void updateAuthor(@RequestBody Author author, @PathVariable long id) {
        bookService.updateBook(id, author);
    }

    @DeleteMapping("/books/{id}")
    public void updateAuthor(@PathVariable long id) {
        bookService.deleteBook(id);
    }
}
