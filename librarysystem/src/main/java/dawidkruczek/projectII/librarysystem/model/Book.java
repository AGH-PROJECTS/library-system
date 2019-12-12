package dawidkruczek.projectII.librarysystem.model;

public class Book {
    private long id;
    private String isbn;
    private Category category;
    private String title;
    private Author author;
    private Publisher publisher;
    private String yearOfPublish;

    public Book() {}

    public Book(long id, String isbn, Category category, String title, Author author, Publisher publisher, String yearOfPublish) {
        this.id = id;
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublish = yearOfPublish;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(String yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }
}
