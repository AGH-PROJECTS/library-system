package dawidkruczek.projectII.librarysystem.support.order;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Book;
import dawidkruczek.projectII.librarysystem.model.Order;
import dawidkruczek.projectII.librarysystem.model.User;
import dawidkruczek.projectII.librarysystem.repository.OrderRepository;
import dawidkruczek.projectII.librarysystem.support.AnswerType;
import dawidkruczek.projectII.librarysystem.support.book.BookService;
import dawidkruczek.projectII.librarysystem.support.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository repository;
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = repository.findAll();

        if(orders.isEmpty()) {
            throw new EntityNotFoundException();
        }
        else {
            return orders;
        }
    }

    public Optional<Order> getOrder(String  id) {
        Optional<Order> order = repository.findById(id);

        if(order.isPresent()) {
            return order;
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public List<String> addOrder(Order order) {
        return prepareAnswers(AnswerType.ADDED,order);
    }

    public List<String> updateOrder(String id, Order newOrder) {
        List<String> orders;
        Optional<Order> oldOrder = repository.findById(id);

        if(oldOrder.isPresent()) {
            orders = prepareAnswers(AnswerType.UPDATED,newOrder);
            newOrder.setId(id);
        }
        else {
            throw new EntityNotFoundException(id);
        }
        return orders;
    }

    public String deleteOrder(String id) {
        Optional<Order> order = repository.findById(id);
        if(order.isPresent()) {
            repository.delete(order.get());
            return AnswerType.DELETED.toString();
        }
        else {
            throw new EntityNotFoundException(id);
        }
    }

    private List<String> prepareAnswers(AnswerType type, Order order) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<String > answers = new ArrayList<>();
        List<String > bookAnswers = bookService.prepareAnswers(AnswerType.ADDED,order.getBook());
        List<User> users = Arrays.asList(order.getEmployeeRent(),order.getEmployeeReturn(),order.getUser());
        boolean isUsersExist = true;

        for (User user: users) {
          if(!userService.checkUserExist(user)){
              isUsersExist = false;
          }
        }

        if(isUsersExist && validator.validate(order).size() == 0 && validator.validate(order.getBook()).size() == 0) {
            repository.insert(order);
            answers.add(order.getUser().toString());
            answers.add(order.getEmployeeRent().toString());
            answers.add(order.getEmployeeReturn().toString());
            answers.addAll(bookAnswers);
            answers.add(order.getDateOfRent());
            answers.add(order.getDateOfReturn());
            answers.add(order.getStatus());
            answers.add(type.toString());
        }
        else {
            if(!isUsersExist) {
                answers.add("Users must exist");
            }
            else {
                answers.add("Wrong data: ");
                validator.validate(order).forEach(v->answers.add(v.getMessage()));
                validator.validate(order.getBook()).forEach(v->answers.add(v.getMessage()));
            }
        }

        return answers;
    }

}
