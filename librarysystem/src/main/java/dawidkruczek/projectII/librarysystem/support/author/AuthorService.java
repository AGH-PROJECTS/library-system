package dawidkruczek.projectII.librarysystem.support.author;

import dawidkruczek.projectII.librarysystem.model.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {

    private List<Author> authors = new ArrayList<> (Arrays.asList(
            new Author(0,"Henryk","Sienkiewicz","05.05.1846"),
            new Author(1,"Maria","Konopnicka","23 maja 1842"),
            new Author(2,"Olga", "Tokarczuk","29 stycznia 1962")
    ));

    public List<Author> getAllAuthors() {
        return authors;
    }

    public Author getAuthor(long id) {
        return authors.stream().filter(author -> author.getId() == id).findFirst().get();
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }


    public void updateAuthor(long id, Author updatedAuthor) {
        authors.set(Math.toIntExact(id),updatedAuthor);
    }

    public void deleteAuthor(long id) {
        authors.remove(id);
    }
}
