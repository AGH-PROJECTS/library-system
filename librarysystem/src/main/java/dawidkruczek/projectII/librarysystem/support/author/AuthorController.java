package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Optional<Author>> getAuthor(@PathVariable String id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PostMapping("/authors")
    public ResponseEntity<List<String>> addAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.addAuthor(author));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<List<String>> updateAuthor(@RequestBody Author author, @PathVariable String id) {
        return ResponseEntity.accepted().body(authorService.updateAuthor(id, author));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String id) {
        return ResponseEntity.accepted().body(authorService.deleteAuthor(id));
    }
}
