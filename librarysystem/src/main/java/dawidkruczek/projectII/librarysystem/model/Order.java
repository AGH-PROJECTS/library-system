package dawidkruczek.projectII.librarysystem.model;

public class Order {
    private long id;
    private long idUser;
    private long idEmployeeRent;
    private long getIdEmployeeReturn;
    private long idBook;
    private String dateOfRent;
    private String dateOfReturn;
    private String status;

    public Order(){}

    public Order(long id, long idUser, long idEmployeeRent, long getIdEmployeeReturn, long idBook, String dateOfRent, String dateOfReturn, String status) {
        this.id = id;
        this.idUser = idUser;
        this.idEmployeeRent = idEmployeeRent;
        this.getIdEmployeeReturn = getIdEmployeeReturn;
        this.idBook = idBook;
        this.dateOfRent = dateOfRent;
        this.dateOfReturn = dateOfReturn;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdEmployeeRent() {
        return idEmployeeRent;
    }

    public void setIdEmployeeRent(long idEmployeeRent) {
        this.idEmployeeRent = idEmployeeRent;
    }

    public long getGetIdEmployeeReturn() {
        return getIdEmployeeReturn;
    }

    public void setGetIdEmployeeReturn(long getIdEmployeeReturn) {
        this.getIdEmployeeReturn = getIdEmployeeReturn;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
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
