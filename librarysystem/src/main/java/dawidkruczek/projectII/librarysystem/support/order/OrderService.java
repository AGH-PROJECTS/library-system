package dawidkruczek.projectII.librarysystem.support.order;

import dawidkruczek.projectII.librarysystem.model.Order;
import dawidkruczek.projectII.librarysystem.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
   /* private List<Order> orders = new ArrayList<>(Arrays.asList(
            new Order(0,0,0,0,0,"13.12.2019","13.01.2020","RENTED"),
            new Order(0,0,0,0,1,"13.12.2019","13.01.2020","RENTED")
    ));*/

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrder(String  id) {
        return repository.findById(id);
    }

    public void addOrder(Order order) {
       repository.insert(order);
    }

    public void updateOrder(String id, Order newOrder) {
        newOrder.setId(id);
        repository.save(newOrder);
    }

    public void deleteOrder(String id) {
        repository.delete(id);
    }
}
