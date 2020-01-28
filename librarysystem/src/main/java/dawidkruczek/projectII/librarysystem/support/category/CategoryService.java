package dawidkruczek.projectII.librarysystem.support.category;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Book;
import dawidkruczek.projectII.librarysystem.model.Category;
import dawidkruczek.projectII.librarysystem.repository.CategoryRepository;
import dawidkruczek.projectII.librarysystem.support.AnswerType;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllBooks() {
        List<Category> categories = repository.findAll();

        if(categories.isEmpty()) {
            throw new EntityNotFoundException();
        }
        else {
            return categories;
        }
    }

    public List<String > prepareAnswers(AnswerType type, Category category) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<String > answers = new ArrayList<>();
        if(validator.validate(category).size() == 0) {
            repository.save(category);
            answers.add(category.getName());
            answers.add(type.toString());
        }
        else {
            answers.add("Wrong data: ");
            validator.validate(category).forEach(v->answers.add(v.getMessage()));
        }

        return answers;
    }

    public Optional<Category> getCategory(String id) {
        Optional<Category> category = repository.findById(id);

        if(category.isPresent()) {
            return category;
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
