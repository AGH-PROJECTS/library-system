package dawidkruczek.projectII.librarysystem.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public class AuthenticationResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private List<String> roles;
    private String pesel;
    private final String jwt;

    public AuthenticationResponse(Optional<User> user, String jwt) {
        this.id = user.get().getId();
        this.username = user.get().getUsername();
        this.firstName = user.get().getFirstName();
        this.lastName = user.get().getLastName();
        this.email = user.get().getEmail();
        this.birthDate = user.get().getBirthDate();
        this.roles = user.get().getRoles();
        this.pesel = user.get().getPesel();
        this.jwt = jwt;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getPesel() {
        return pesel;
    }

    public String getJwt() {
        return jwt;
    }
}
