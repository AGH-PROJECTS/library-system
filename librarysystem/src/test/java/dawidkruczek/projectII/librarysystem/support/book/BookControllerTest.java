package dawidkruczek.projectII.librarysystem.support.book;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Book;
import dawidkruczek.projectII.librarysystem.model.Category;
import dawidkruczek.projectII.librarysystem.model.Publisher;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
import dawidkruczek.projectII.librarysystem.repository.BookRepository;
import dawidkruczek.projectII.librarysystem.security.CustomUserDetailsService;
import dawidkruczek.projectII.librarysystem.security.JWTService;
import org.junit.jupiter.api.BeforeAll;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTService jwtUtil;

    @Test
    void getAllBooks() throws Exception {
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");


        List<Book> books = Collections.singletonList(book);
        when(repository.findAll()).thenReturn(books);
        mockMvc.perform(get("/books" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"isbn\": \"9788377792124\",\n" +
                        "\"category\": {\n" +
                        "\"id\": \"1\",\n" +
                        "\"name\": \"Peozja Epicka\"\n" +
                        "},\n" +
                        "\"title\": \"Pan Tadeusz\",\n" +
                        "\"author\": {\n" +
                        "\"id\": \"1\",\n" +
                        "            \"firstName\": \"Adam\",\n" +
                        "            \"lastName\": \"Mickiewicz\",\n" +
                        "            \"dateOfBirth\": \"24.12.1798\"\n" +
                        "        },\n" +
                        "        \"publisher\": {\n" +
                        "            \"id\": \"1\",\n" +
                        "            \"name\": \"Aleksander Jełowicki\"\n" +
                        "        },\n" +
                        "        \"yearOfPublish\": \"2014\"}]"));
    }

    @Test
    void getBook() throws Exception {
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(book));
        mockMvc.perform(get("/books/1" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\",\"isbn\": \"9788377792124\",\n" +
                        "\"category\": {\n" +
                        "\"id\": \"1\",\n" +
                        "\"name\": \"Peozja Epicka\"\n" +
                        "},\n" +
                        "\"title\": \"Pan Tadeusz\",\n" +
                        "\"author\": {\n" +
                        "\"id\": \"1\",\n" +
                        "            \"firstName\": \"Adam\",\n" +
                        "            \"lastName\": \"Mickiewicz\",\n" +
                        "            \"dateOfBirth\": \"24.12.1798\"\n" +
                        "        },\n" +
                        "        \"publisher\": {\n" +
                        "            \"id\": \"1\",\n" +
                        "            \"name\": \"Aleksander Jełowicki\"\n" +
                        "        },\n" +
                        "        \"yearOfPublish\": \"2014\"}"));
    }

    @Test
    void addBook() throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(post("/books" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"isbn\": \"9788377792124\",\n" +
                        "    \"category\": {\n" +
                        "        \"id\": \"5e2f3bfd75ac2c07ac4d501e\",\n" +
                        "        \"name\": \"Peozja Epicka\"\n" +
                        "    },\n" +
                        "    \"title\": \"Pan Tadeusz\",\n" +
                        "    \"author\": {\n" +
                        "        \"id\": \"5e2f3bfd75ac2c07ac4d501d\",\n" +
                        "        \"firstName\": \"Adam\",\n" +
                        "        \"lastName\": \"Mickiewicz\",\n" +
                        "        \"dateOfBirth\": \"24 grudnia 1798\"\n" +
                        "    },\n" +
                        "    \"publisher\": {\n" +
                        "        \"id\": \"5e2f3bfd75ac2c07ac4d5020\",\n" +
                        "        \"name\": \"Aleksander Jełowicki\"\n" +
                        "    },\n" +
                        "    \"yearOfPublish\": \"2014\"\n" +
                        "}"))
                .andExpect(content().string("[\"9788377792124\",\"Pan Tadeusz\",\"2014\",\"Adam\",\"Mickiewicz\",\"24 grudnia 1798\",\"added\",\"Peozja Epicka\",\"added\",\"Aleksander Jełowicki\",\"added\",\"added\"]"))
                .andExpect(status().isOk());
    }

    @Test
    void updateBook() throws Exception {
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(book));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(post("/books" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": \"1\",\n" +
                        "    \"isbn\": \"9788377792124\",\n" +
                        "    \"category\": {\n" +
                        "        \"id\": \"1\",\n" +
                        "        \"name\": \"Peozja Epicka\"\n" +
                        "    },\n" +
                        "    \"title\": \"Pan Tadeusz\",\n" +
                        "    \"author\": {\n" +
                        "        \"id\": \"1\",\n" +
                        "        \"firstName\": \"Adam\",\n" +
                        "        \"lastName\": \"Mickiewicz\",\n" +
                        "        \"dateOfBirth\": \"24 grudnia 1798\"\n" +
                        "    },\n" +
                        "    \"publisher\": {\n" +
                        "        \"id\": \"1\",\n" +
                        "        \"name\": \"Aleksander Jełowicki\"\n" +
                        "    },\n" +
                        "    \"yearOfPublish\": \"2014\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBook() throws Exception {
        Author author = new Author("Adam","Mickiewicz","24.12.1798");
        author.setId("1");
        Category category = new Category("Peozja Epicka");
        category.setId("1");
        Publisher publisher = new Publisher("Aleksander Jełowicki");
        publisher.setId("1");
        Book book = new Book("9788377792124", category, "Pan Tadeusz", author, publisher, "2014");
        book.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(book));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(delete("/books/1" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}