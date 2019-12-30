package dawidkruczek.projectII.librarysystem.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private HttpStatus status;
    private int statusVlue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.statusVlue = status.value();
        this.message = message;
    }

    ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.statusVlue = status.value();
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusVlue() {
        return statusVlue;
    }

    public void setStatusVlue(int statusVlue) {
        this.statusVlue = statusVlue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
