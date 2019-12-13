package dawidkruczek.projectII.librarysystem.support.order;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/orders")
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @PutMapping("/orders/{id}")
    public void updateOrder(@RequestBody Order order, @PathVariable long id) {
        orderService.updateOrder(id, order);
    }

    @DeleteMapping("/orders/{id}")
    public void updateOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
    }
}
