package library.frontend;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "Order")
@ApplicationScoped
public class Order {
    private List<Book> orderedBooks= new LinkedList<>();

    private String currency = "PLN";

    public Order() {
        orderedBooks.add(new Book("Bible", "Jesus", "Religious", 29.99, "PLN", 500));
        orderedBooks.add(new Book("Lord of the Rings", "R. R. Tolkien", "Fantasy", 24.99, "PLN", 400));
        orderedBooks.add(new Book("Alchemist", "P. Coelho", "Fiction", 4.99, "EUR", 300));
        orderedBooks.forEach(book -> book.convertCurrency(getCurrency()));
    }

    public String onCurrencyChange() {
        orderedBooks.forEach(book -> book.convertCurrency(getCurrency()));
        return "podsumowanie";
    }


    public Double getSum() {
        return orderedBooks.stream().map(Book::getPrice).reduce((double) 0, (acc, ite) -> acc + ite);
    }

    public void setOrderedBooks(List<Book> orderedBooks) {
        this.orderedBooks = orderedBooks;
    }

    public List<Book> getOrderedBooks() {
        return orderedBooks;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
