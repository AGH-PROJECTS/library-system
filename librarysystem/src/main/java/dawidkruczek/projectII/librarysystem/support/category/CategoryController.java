package dawidkruczek.projectII.librarysystem.support.category;

import dawidkruczek.projectII.librarysystem.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllBooks();
    }

    @GetMapping("/categories/{id}")
    public Optional<Category> getCategory(@PathVariable String id) {
        return categoryService.getCategory(id);
    }
}
