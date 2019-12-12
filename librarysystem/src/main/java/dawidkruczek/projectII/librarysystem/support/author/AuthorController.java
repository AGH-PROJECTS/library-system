package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthor(@PathVariable long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping("/authors")
    public void addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
    }

    @PutMapping("/authors/{id}")
    public void updateAuthor(@RequestBody Author author, @PathVariable long id) {
        authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/authors/{id}")
    public void updateAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
    }
}
