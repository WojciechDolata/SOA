package library.frontend;

import library.backend.DatabaseManager;
import library.backend.DatabaseManagerInterface;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "Library")
@ApplicationScoped
public class Library {
    @EJB
    private DatabaseManagerInterface databaseManager;

    private List<Book> books = new ArrayList<>();
    private List<Book> filteredBooks = new ArrayList<>();
    private String currency = "PLN";
    private List<Book> selectedBooks = new ArrayList<>();

    @ManagedProperty(value="#{Order}")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }

    @PostConstruct
    public void postConstruct(){
        books = getBooks();
        books.forEach(book -> book.convertCurrency(getCurrency()));
    }

    public Library() {

    }

    public String editBook() {
//        currentBook = selectedBooks.get(0);
        return "edit";
    }

    public String deleteBook() {
        for (Book b : selectedBooks) {
            databaseManager.removeBook(b.getId());
        }
        return "library";
    }

    public String createNewOrder() {
        order.setOrderedBooks(selectedBooks);
        order.setCurrency(currency);
        return "podsumowanie";
    }

    public String onCurrencyChange() {
        books.forEach(book -> book.convertCurrency(getCurrency()));
        return "library";
    }

    public List<Book> getBooks() {
        return databaseManager.getAllBooks().stream().map(Book::convert).collect(Collectors.toList());
    }

    public Book getBookById(Integer id) {
        return Book.convert(databaseManager.getBookById(id));
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Book> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(List<Book> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }
}
