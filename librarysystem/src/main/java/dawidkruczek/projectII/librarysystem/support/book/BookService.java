package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Book;
import dawidkruczek.projectII.librarysystem.repository.BookRepository;
import dawidkruczek.projectII.librarysystem.support.AnswerType;
import dawidkruczek.projectII.librarysystem.support.author.AuthorService;
import dawidkruczek.projectII.librarysystem.support.category.CategoryService;
import dawidkruczek.projectII.librarysystem.support.publisher.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository repository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private CategoryService categoryService;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = repository.findAll();

        if(books.isEmpty()) {
            throw new EntityNotFoundException();
        }
        else {
            return books;
        }
    }

    public Optional<Book> getBook(String id) {
        Optional<Book> book = repository.findById(id);

        if(book.isPresent()) {
            return book;
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public List<String> addBook(Book book) {
        return prepareAnswers(AnswerType.ADDED,book);
    }

    public List<String> updateBook(String id, Book newBook) {
        List<String> books;
        Optional<Book> oldBook = repository.findById(id);

        if(oldBook.isPresent()) {
            books = prepareAnswers(AnswerType.UPDATED,newBook);
            newBook.setId(id);
        }
        else {
            throw new EntityNotFoundException(id);
        }
        return books;
    }

    public String deleteBook(String id) {
        Optional<Book> book = repository.findById(id);
        if(book.isPresent()) {
            repository.delete(book.get());
            return AnswerType.DELETED.toString();
        }
        else {
            throw new EntityNotFoundException(id);
        }
    }

    public List<String > prepareAnswers(AnswerType type, Book book) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<String > answers = new ArrayList<>();
        List<String> authorAnswers = new ArrayList<>();
        List<String > categoryAnswers = new ArrayList<>();
        List<String> publisherAnswers = new ArrayList<>();
        if(book.getAuthor()!=null && book.getCategory()!=null && book.getPublisher()!=null) {
            authorAnswers = authorService.prepareAnswers(AnswerType.ADDED,book.getAuthor());
            categoryAnswers = categoryService.prepareAnswers(AnswerType.ADDED,book.getCategory());
            publisherAnswers = publisherService.prepareAnswers(AnswerType.ADDED,book.getPublisher());

            if(validator.validate(book).size() == 0 && validator.validate(book.getAuthor()).size() == 0 && validator.validate(book.getPublisher()).size() == 0 && validator.validate(book.getCategory()).size() == 0) {
                repository.save(book);
                answers.add(book.getIsbn());
                answers.add(book.getTitle());
                answers.add(book.getYearOfPublish());
                answers.addAll(authorAnswers);
                answers.addAll(categoryAnswers);
                answers.addAll(publisherAnswers);
                answers.add(type.toString());
            }
            else {
                answers.add("Wrong data: ");
                validator.validate(book).forEach(v->answers.add(v.getMessage()));
                validator.validate(book.getAuthor()).forEach(v->answers.add(v.getMessage()));
                validator.validate(book.getPublisher()).forEach(v->answers.add(v.getMessage()));
                validator.validate(book.getCategory()).forEach(v->answers.add(v.getMessage()));
            }
        }
        else {
            answers.add("Wrong data: Specify all required data");
        }

        return answers;
    }


}
