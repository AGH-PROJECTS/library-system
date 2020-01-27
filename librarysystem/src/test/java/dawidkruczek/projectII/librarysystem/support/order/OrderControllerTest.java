package dawidkruczek.projectII.librarysystem.support.order;

import com.google.gson.Gson;
import dawidkruczek.projectII.librarysystem.model.*;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
import dawidkruczek.projectII.librarysystem.repository.OrderRepository;
import dawidkruczek.projectII.librarysystem.security.CustomUserDetailsService;
import dawidkruczek.projectII.librarysystem.security.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTService jwtUtil;

    @Test
    void getAllOrders() throws Exception {
        User user = new User("dawid", "Dawid","Kruczek","dawid@gmail.com", "zaq1@WSX", "30.06.1997", Arrays.asList("ROLE_USER","ROLE_ADMIN"), "97063001370");
        user.setId("1");
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");
        Order order  = new Order(user,user,user,book,"13.12.2019","13.01.2020","RENTED");
        order.setId("1");
        List<Order> orders = Collections.singletonList(order);
        when(repository.findAll()).thenReturn(orders);
        mockMvc.perform(get("/orders" ))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getOrder() throws Exception {
        User user = new User("dawid", "Dawid","Kruczek","dawid@gmail.com", "zaq1@WSX", "30.06.1997", Arrays.asList("ROLE_USER","ROLE_ADMIN"), "97063001370");
        user.setId("1");
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");
        Order order  = new Order(user,user,user,book,"13.12.2019","13.01.2020","RENTED");
        order.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(order));
        mockMvc.perform(get("/orders/1" ))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addOrder() throws Exception {
        User user = new User("dawid", "Dawid","Kruczek","dawid@gmail.com", "zaq1@WSX", "30.06.1997", Arrays.asList("ROLE_USER","ROLE_ADMIN"), "97063001370");
        user.setId("1");
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");
        Order order  = new Order(user,user,user,book,"13.12.2019","13.01.2020","RENTED");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(post("/orders" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(order)))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrder() throws Exception {
        User user = new User("dawid", "Dawid","Kruczek","dawid@gmail.com", "zaq1@WSX", "30.06.1997", Arrays.asList("ROLE_USER","ROLE_ADMIN"), "97063001370");
        user.setId("2");
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("2");
        Category category = new Category("Peozja Epicka");
        category.setId("2");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("2");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("2");
        Order order  = new Order(user,user,user,book,"13.12.2019","13.01.2020","RENTED");
        order.setId("2");
        when(repository.findById("2")).thenReturn(Optional.of(order));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(put("/orders/2" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(order)))
                .andExpect(status().is(200));
    }

    @Test
    void deleteOrder() throws Exception {
        User user = new User("dawid", "Dawid","Kruczek","dawid@gmail.com", "zaq1@WSX", "30.06.1997", Arrays.asList("ROLE_USER","ROLE_ADMIN"), "97063001370");
        user.setId("1");
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");
        Order order  = new Order(user,user,user,book,"13.12.2019","13.01.2020","RENTED");
        order.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(order));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(delete("/orders/1" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}