package dawidkruczek.projectII.librarysystem.repository;

import dawidkruczek.projectII.librarysystem.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
    Optional<Publisher> findById(String id);
}
