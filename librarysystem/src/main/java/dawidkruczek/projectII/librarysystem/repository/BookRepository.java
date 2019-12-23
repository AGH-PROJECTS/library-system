package dawidkruczek.projectII.librarysystem.repository;

import dawidkruczek.projectII.librarysystem.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Book findById(String id);
}
