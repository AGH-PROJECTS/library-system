package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
import org.springframework.stereotype.Service;;
import java.util.List;

@Service
public class AuthorService {
    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Author getAuthor(String id) {
        return repository.findById(id);
    }

    public void addAuthor(Author author) {
        repository.insert(author);
    }

    public void updateAuthor(String id, Author newAuthor) {
        newAuthor.setId(id);
        repository.save(newAuthor);
    }

    public void deleteAuthor(String id) {
        repository.delete(id);
    }
}
