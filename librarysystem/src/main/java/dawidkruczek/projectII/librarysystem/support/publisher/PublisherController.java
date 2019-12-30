package dawidkruczek.projectII.librarysystem.support.publisher;

import dawidkruczek.projectII.librarysystem.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @RequestMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publishers/{id}")
    public Optional<Publisher> getPublisher(@PathVariable String id) {
        return publisherService.getPublisher(id);
    }

    @PostMapping("/publishers")
    public List<String> addPublisher(@RequestBody Publisher publisher) {
        return publisherService.addPublisher(publisher);
    }

    @PutMapping("/publishers/{id}")
    public List<String> updatePublisher(@RequestBody Publisher publisher, @PathVariable String id) {
        return publisherService.updatePublisher(id, publisher);
    }

    @DeleteMapping("/publishers/{id}")
    public String updatePublisher(@PathVariable String id) {
        return publisherService.deletePublisher(id);
    }
}
