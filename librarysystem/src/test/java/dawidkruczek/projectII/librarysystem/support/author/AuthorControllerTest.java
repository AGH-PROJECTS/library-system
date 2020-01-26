package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  AuthorRepository authorRepository;


    @Test
    void getAllAuthors() {
 /*
        List<Author> allAuthors = Collections.singletonList(author);
        when(authorRepository.findAll()).thenReturn(allAuthors);
*/
    }

    @Test
    void getAuthor() throws Exception {
        Author author = new Author("Henryk","Sienkiewicz","05.05.1846");
        author.setId("1");
        when(authorRepository.findById("1")).thenReturn(Optional.of(author));

        //authorRepository.save(author);
        mockMvc.perform(get("/authors/1" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\",\"firstName\": \"Henryk\", \"lastName\": \"Sienkiewicz\", \"dateOfBirth\": \"05.05.1846\"}"));
    }

    @Test
    void addAuthor() {
    }

    @Test
    void updateAuthor() {
    }

    @Test
    void deleteAuthor() {
    }
}