package dawidkruczek.projectII.librarysystem.support.category;

import dawidkruczek.projectII.librarysystem.model.Book;
import dawidkruczek.projectII.librarysystem.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/categories")
    public ResponseEntity<List<String>> addBook(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<List<String>> updateBook(@RequestBody Category category, @PathVariable String id) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

}
