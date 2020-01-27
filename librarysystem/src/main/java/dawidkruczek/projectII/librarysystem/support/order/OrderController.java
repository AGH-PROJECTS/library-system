package dawidkruczek.projectII.librarysystem.support.order;

import dawidkruczek.projectII.librarysystem.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Optional<Order> getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/orders")
    public List<String> addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/orders/{id}")
    public List<String> updateOrder(@RequestBody Order order, @PathVariable String id) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }
}
