package dawidkruczek.projectII.librarysystem.repository;

import dawidkruczek.projectII.librarysystem.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
    Optional<Category> findById(String id);
}
