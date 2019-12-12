package dawidkruczek.projectII.librarysystem.support.publisher;

import dawidkruczek.projectII.librarysystem.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @RequestMapping("/publishers")
    public List<Author> getAllAuthors() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publishers/{id}")
    public Author getAuthor(@PathVariable long id) {
        return publisherService.getPublisher(id);
    }

    @PostMapping("/publishers")
    public void addAuthor(@RequestBody Author author) {
        publisherService.addPublisher(author);
    }

    @PutMapping("/publishers/{id}")
    public void updateAuthor(@RequestBody Author author, @PathVariable long id) {
        publisherService.updatePublisher(id, author);
    }

    @DeleteMapping("/publishers/{id}")
    public void updateAuthor(@PathVariable long id) {
        publisherService.deletePublisher(id);
    }
}
