package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
import dawidkruczek.projectII.librarysystem.support.AnswerType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = repository.findAll();

        if(authors.isEmpty()) {
            throw new EntityNotFoundException();
        }
        else {
            return authors;
        }
    }

    public Optional<Author> getAuthor(String id) {
        Optional<Author> author = repository.findById(id);
        if(author.isPresent()) {
            return author;
        }
        else {
            throw new EntityNotFoundException(id);
        }
    }

    public List<String> addAuthor(Author author) {
        return prepareAnswers(AnswerType.ADDED, author);
    }

    public List<String> updateAuthor(String id, Author newAuthor) {
        List<String> answers;
        Optional<Author> oldAuthor = repository.findById(id);

        if(oldAuthor.isPresent()) {
            answers = prepareAnswers(AnswerType.UPDATED,newAuthor);
            newAuthor.setId(id);
        }
        else {
            throw new EntityNotFoundException(id);
        }
        return answers;
    }

    public String deleteAuthor(String id) {
        Optional<Author> author = repository.findById(id);
        if(author.isPresent()) {
            repository.delete(author.get());
            return AnswerType.DELETED.toString();
        }
        else {
            throw new EntityNotFoundException(id);
        }
    }

    public List<String > prepareAnswers(AnswerType type, Author author) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<String > answers = new ArrayList<>();
        if(validator.validate(author).size() == 0) {
            repository.insert(author);
            answers.add(author.getFirstName());
            answers.add(author.getLastName());
            answers.add(author.getDateOfBirth());
            answers.add(type.toString());
        }
        else {
            answers.add("Wrong data: ");
            validator.validate(author).forEach(v->answers.add(v.getMessage()));
        }

        return answers;
    }
}
