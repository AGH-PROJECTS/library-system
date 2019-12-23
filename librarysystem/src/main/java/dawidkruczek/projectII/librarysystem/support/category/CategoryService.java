package dawidkruczek.projectII.librarysystem.support.category;

import dawidkruczek.projectII.librarysystem.model.Category;
import dawidkruczek.projectII.librarysystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllBooks() {
        return repository.findAll();
    }
}
