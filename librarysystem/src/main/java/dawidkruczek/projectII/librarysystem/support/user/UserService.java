package dawidkruczek.projectII.librarysystem.support.user;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.User;
import dawidkruczek.projectII.librarysystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean checkUserExist(User user){
        return repository.findById(user.getId()).isPresent();
    }

    public Optional<User> getuser(String username) {
        Optional<User> user = Optional.ofNullable(repository.findByUsername(username));

        if(user.isPresent()) {
            return user;
        } else {
            throw new EntityNotFoundException(username);
        }
    }
}
