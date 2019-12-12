package dawidkruczek.projectII.librarysystem.support.order;

import dawidkruczek.projectII.librarysystem.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<Author> getAllAuthors() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Author getAuthor(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/orders")
    public void addAuthor(@RequestBody Author author) {
        orderService.addOrder(author);
    }

    @PutMapping("/orders/{id}")
    public void updateAuthor(@RequestBody Author author, @PathVariable long id) {
        orderService.updateOrder(id, author);
    }

    @DeleteMapping("/orders/{id}")
    public void updateAuthor(@PathVariable long id) {
        orderService.deleteOrder(id);
    }
}
