package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public Optional<Author> getAuthor(@PathVariable String id) {
        return authorService.getAuthor(id);
    }

    @PostMapping("/authors")
    public List<String> addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/authors/{id}")
    public List<String> updateAuthor(@RequestBody Author author, @PathVariable String id) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable String id) {
        return authorService.deleteAuthor(id);
    }
}
