package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.exception.EntityNotFoundException;
import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTService jwtUtil;

    @Test
    void getAllAuthors() throws Exception {
        Author author = new Author("Henryk","Sienkiewicz","05.05.1846");
        author.setId("1");
        List<Author> allAuthors = Collections.singletonList(author);
        when(authorRepository.findAll()).thenReturn(allAuthors);
        mockMvc.perform(get("/authors" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"firstName\": \"Henryk\", \"lastName\": \"Sienkiewicz\", \"dateOfBirth\": \"05.05.1846\"}]"));

    }

    @Test
    void getAuthor() throws Exception {
        Author author = new Author("Henryk","Sienkiewicz","05.05.1846");
        author.setId("1");
        when(authorRepository.findById("1")).thenReturn(Optional.of(author));
        mockMvc.perform(get("/authors/1" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\",\"firstName\": \"Henryk\", \"lastName\": \"Sienkiewicz\", \"dateOfBirth\": \"05.05.1846\"}"));
    }

    @Test
    void addAuthor() throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(post("/authors" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Henryk\", \"lastName\": \"Sienkiewicz\", \"dateOfBirth\": \"05.05.1846\"}"))
                .andExpect(content().string("[\"Henryk\",\"Sienkiewicz\",\"05.05.1846\",\"added\"]"))
                .andExpect(status().isCreated());

    }

    @Test
    void updateAuthor() throws Exception {
        Author author = new Author("Henryk","Sienkiewicz","05.05.1846");
        author.setId("1");
        when(authorRepository.findById("1")).thenReturn(Optional.of(author));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(put("/authors/1" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"firstName\": \"Henryk\", \"lastName\": \"Sienkiewicz\", \"dateOfBirth\": \"05.05.1846\"}"))
                .andExpect(status().isAccepted());
    }

    @Test
    void deleteAuthor() throws Exception {
        Author author = new Author("Henryk","Sienkiewicz","05.05.1846");
        author.setId("1");
        when(authorRepository.findById("1")).thenReturn(Optional.of(author));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        mockMvc.perform(delete("/authors/1" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}