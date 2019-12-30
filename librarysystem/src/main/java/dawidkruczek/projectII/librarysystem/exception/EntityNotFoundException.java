package dawidkruczek.projectII.librarysystem.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {
    private HttpStatus status = HttpStatus.NOT_FOUND;
    private String id;
    private String message;
    public EntityNotFoundException(String id) {
        this.id = id;
        this.message = "Item of id: " + id + " not found";
    }

    public EntityNotFoundException() {
        this.message = "Items not found";
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
