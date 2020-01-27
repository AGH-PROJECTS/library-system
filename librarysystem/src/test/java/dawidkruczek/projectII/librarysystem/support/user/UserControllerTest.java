package dawidkruczek.projectII.librarysystem.support.user;

import dawidkruczek.projectII.librarysystem.security.CustomUserDetailsService;
import dawidkruczek.projectII.librarysystem.security.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTService jwtUtil;

    @Test
    void shouldAuthenticate() throws Exception {
        String authenticationRequest = "{\n" +
                "\t\"username\":\"dawid\",\n" +
                "\t\"password\":\"zaq1@WSX\"\n" +
                "}";
        mvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(authenticationRequest)).andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldNotAuthenticate() throws Exception {
        String authenticationRequest = "{\n" +
                "\t\"username\":\"dawid\",\n" +
                "\t\"password\":\"zaq@WSX\"\n" +
                "}";
        mvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(authenticationRequest)).andExpect(status().is(HttpStatus.UNAUTHORIZED.value())).andReturn();
    }
}