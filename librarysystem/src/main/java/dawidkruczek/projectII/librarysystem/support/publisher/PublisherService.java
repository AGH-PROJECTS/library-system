package dawidkruczek.projectII.librarysystem.support.publisher;

import dawidkruczek.projectII.librarysystem.model.Publisher;
import dawidkruczek.projectII.librarysystem.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public List<Publisher> getAllPublishers() {
        return repository.findAll();
    }

    public Publisher getPublisher(String id) {
        return repository.findById(id);
    }

    public void addPublisher(Publisher publisher) {
        repository.insert(publisher);
    }

    public void updatePublisher(String id, Publisher publisher) {
        publisher.setId(id);
        repository.save(publisher);
    }

    public void deletePublisher(String id) {
        repository.delete(id);
    }
}
