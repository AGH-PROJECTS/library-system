package dawidkruczek.projectII.librarysystem.support.order;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private List<Order> orders = new ArrayList<>(Arrays.asList(
            new Order(0,0,0,0,0,"13.12.2019","13.01.2020","RENTED"),
            new Order(0,0,0,0,1,"13.12.2019","13.01.2020","RENTED")
    ));

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrder(long id) {
        return orders.stream().filter(order -> order.getId()==id).findFirst().get();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void updateOrder(long id, Order order) {
        orders.set(Math.toIntExact(id),order);
    }

    public void deleteOrder(long id) {
        orders.remove(id);
    }
}
