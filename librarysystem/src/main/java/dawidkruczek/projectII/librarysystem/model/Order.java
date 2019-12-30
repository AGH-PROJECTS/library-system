package dawidkruczek.projectII.librarysystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "Orders")
public class Order {
    @Id
    private String id;
    @NotNull(message = "user is required")
    private User user;
    @NotNull(message = "employeeRent is required")
    private User employeeRent;
    @NotNull(message = "employeeReturn is required")
    private User employeeReturn;
    @NotNull(message = "book is required")
    private Book book;
    @NotNull(message = "dateOfRent is required")
    private String dateOfRent;
    @NotNull(message = "dateOfReturn is required")
    private String dateOfReturn;
    @NotNull(message = "status is required")
    private String status;

    public Order(){}

    public Order(User user, User employeeRent, User employeeReturn, Book book, String dateOfRent, String dateOfReturn, String status) {
        this.user = user;
        this.employeeRent = employeeRent;
        this.employeeReturn = employeeReturn;
        this.book = book;
        this.dateOfRent = dateOfRent;
        this.dateOfReturn = dateOfReturn;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getEmployeeRent() {
        return employeeRent;
    }

    public void setEmployeeRent(User employeeRent) {
        this.employeeRent = employeeRent;
    }

    public User getEmployeeReturn() {
        return employeeReturn;
    }

    public void setEmployeeReturn(User employeeReturn) {
        this.employeeReturn = employeeReturn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getDateOfRent() {
        return dateOfRent;
    }

    public void setDateOfRent(String dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
