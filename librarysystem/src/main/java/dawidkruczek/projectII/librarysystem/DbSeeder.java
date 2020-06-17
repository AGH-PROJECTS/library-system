package dawidkruczek.projectII.librarysystem;

import dawidkruczek.projectII.librarysystem.model.*;
import dawidkruczek.projectII.librarysystem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private OrderRepository orderRepository;
    private PublisherRepository publisherRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    public DbSeeder(AuthorRepository authorRepository, BookRepository bookRepository, OrderRepository orderRepository, PublisherRepository publisherRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.publisherRepository = publisherRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Author> authors = Arrays.asList(
                new Author("Henryk","Sienkiewicz","05.05.1846"),
                new Author("Olga", "Tokarczuk","29.01.1962"),
                new Author("Adam", "Mickiewicz","24.12.1798")
        );

        authorRepository.deleteAll();
        authorRepository.saveAll(authors);

        List<Category> categories = Arrays.asList(
                new Category("Peozja Epicka")
        );

        categoryRepository.deleteAll();
        categoryRepository.saveAll(categories);

        List<Publisher> publishers = new ArrayList<>(Arrays.asList(
            new Publisher("BOOKS"),
            new Publisher("Aleksander Jełowicki")
        ));

        publisherRepository.deleteAll();
        publisherRepository.saveAll(publishers);


        List<Book> books = Arrays.asList(
                new Book("9788377792124", categories.get(0), "Pan Tadeusz", authors.get(2), publishers.get(1), "2014"),
                new Book("9788377792124", categories.get(0), "Pan Tadeusz", authors.get(2), publishers.get(1), "2014"),
                new Book("9788377792124", categories.get(0), "Pan Tadeusz", authors.get(2), publishers.get(1), "2014")
                //new Book("9788377792124", "Pan Tadeusz", "2014")
        );

        bookRepository.deleteAll();
        bookRepository.saveAll(books);

        List<User> users = Arrays.asList(
            new User("dawid", "Dawid","Kruczek","dawid@gmail.com", "zaq1@WSX", "30.06.1997", Arrays.asList("ROLE_ADMIN"), "97063001370"),
            new User("dawid2", "Dawid","Kruczek","dawid@gmail.com", "zaq1@WSX", "30.06.1997", Arrays.asList("ROLE_USER"), "66563423123")
        );

        userRepository.deleteAll();
        userRepository.saveAll(users);

        List<Order> orders = new ArrayList<>(Arrays.asList(
            new Order(users.get(0),users.get(0),users.get(0),books.get(0),"13.12.2019","13.01.2020","RENTED")
        ));

        orderRepository.deleteAll();
        orderRepository.saveAll(orders);
    }
}
