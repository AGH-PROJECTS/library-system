package dawidkruczek.projectII.librarysystem.support.publisher;

import com.google.gson.Gson;
import dawidkruczek.projectII.librarysystem.model.Author;
import dawidkruczek.projectII.librarysystem.model.Publisher;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
import dawidkruczek.projectII.librarysystem.repository.PublisherRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTService jwtUtil;

    @Test
    void getAllPublishers() throws Exception {
        Publisher publisher =  new Publisher("BOOKS");
        publisher.setId("1");
        List<Publisher> publishers = Collections.singletonList(publisher);
        when(repository.findAll()).thenReturn(publishers);
        mockMvc.perform(get("/publishers" ))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getPublisher() throws Exception {
        Publisher publisher =  new Publisher("BOOKS");
        publisher.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(publisher));
        mockMvc.perform(get("/publishers/1" ))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addPublisher() throws Exception {
        Publisher publisher =  new Publisher("BOOKS");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        when(repository.findById("1")).thenReturn(Optional.of(publisher));
        mockMvc.perform(post("/publishers" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(publisher)))
                .andExpect(status().isOk());
    }

    @Test
    void updatePublisher() throws Exception {
        Publisher publisher =  new Publisher("BOOKS");
        publisher.setId("1");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        when(repository.findById("1")).thenReturn(Optional.of(publisher));
        mockMvc.perform(put("/publishers/1" )
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(publisher)))
                .andExpect(status().isOk());
    }

    @Test
    void deletePublisher() throws Exception {
        Publisher publisher =  new Publisher("BOOKS");
        publisher.setId("1");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("dawid", "zaq1@WSX"));
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername("dawid");
        final String jwt = jwtUtil.generateToken(userDetails);
        when(repository.findById("1")).thenReturn(Optional.of(publisher));
        mockMvc.perform(delete("/publishers/1" )
                .header("Authorization", "Bearer " + jwt))
                .andExpect(status().isOk());
    }

}