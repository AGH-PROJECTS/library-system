package dawidkruczek.projectII.librarysystem.support.user;

import dawidkruczek.projectII.librarysystem.model.User;
import dawidkruczek.projectII.librarysystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean checkUserExist(User user){
        return repository.findById(user.getId()).isPresent();
    }
}
