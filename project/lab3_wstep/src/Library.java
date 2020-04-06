import jdk.jfr.Name;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "Library")
@ApplicationScoped
public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Book> filteredBooks = new ArrayList<>();

    public Library() {
        books.add(new Book("Bible", "Jesus", "Religious", 29.99, "PLN", 500));
        books.add(new Book("Lord of the Rings", "R. R. Tolkien", "Fantasy", 24.99, "PLN", 400));
        books.add(new Book("Alchemist", "P. Coelho", "Fiction", 4.99, "EUR", 300));
        books.add(new Book("Mahjong Rules", "--", "Education", 19.99, "PLN", 500));
        books.add(new Book("Quran", "Mahomet", "Religious", 59.99, "PLN", 200));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<Book> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }
}
