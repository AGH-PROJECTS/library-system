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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void getBook() {
    }

    @Test
    void addBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}