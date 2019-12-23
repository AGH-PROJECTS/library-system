package dawidkruczek.projectII.librarysystem.repository;

import dawidkruczek.projectII.librarysystem.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
    Publisher findById(String id);
}
