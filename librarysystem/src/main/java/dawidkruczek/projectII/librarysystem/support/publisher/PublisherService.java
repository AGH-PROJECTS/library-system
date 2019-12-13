package dawidkruczek.projectII.librarysystem.support.publisher;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PublisherService {

    private List<Publisher> publishers = new ArrayList<>(Arrays.asList(
            new Publisher(0,"BOOKS")
    ));

    public List<Publisher> getAllPublishers() {
        return publishers;
    }

    public Publisher getPublisher(long id) {
        return publishers.stream().filter(publisher -> publisher.getId()==id).findFirst().get();
    }

    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    public void updatePublisher(long id, Publisher publisher) {
        publishers.set(Math.toIntExact(id),publisher);
    }

    public void deletePublisher(long id) {
        publishers.remove(id);
    }
}
