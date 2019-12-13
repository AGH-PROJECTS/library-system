package dawidkruczek.projectII.librarysystem.support.publisher;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @RequestMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publishers/{id}")
    public Publisher getPublisher(@PathVariable long id) {
        return publisherService.getPublisher(id);
    }

    @PostMapping("/publishers")
    public void addPublisher(@RequestBody Publisher publisher) {
        publisherService.addPublisher(publisher);
    }

    @PutMapping("/publishers/{id}")
    public void updatePublisher(@RequestBody Publisher publisher, @PathVariable long id) {
        publisherService.updatePublisher(id, publisher);
    }

    @DeleteMapping("/publishers/{id}")
    public void updatePublisher(@PathVariable long id) {
        publisherService.deletePublisher(id);
    }
}
