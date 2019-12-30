package dawidkruczek.projectII.librarysystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "Books")
public class Book {
    @Id
    private String id;
    @NotNull(message = "isbn is required")
    private String isbn;
    @NotNull(message = "category is required")
    private Category category;
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "author is required")
    private Author author;
    @NotNull(message = "publisher is required")
    private Publisher publisher;
    @NotNull(message = "yearOfPublish is required")
    private String yearOfPublish;

    public Book() {}

    public Book(String isbn, Category category, String title, Author author, Publisher publisher, String yearOfPublish) {
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublish = yearOfPublish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return isbn + " " + title + " " + yearOfPublish + " " + category.toString() + " " + author.toString()
 + " " + publisher.toString();
    }
}
