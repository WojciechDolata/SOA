import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Objects;

@ManagedBean(name = "Book")
@ViewScoped
public class Book implements Serializable {
    private static Integer index = 0;
    private Integer id;
    private String title;
    private String author;
    private String type;
    private Double price;
    private String currency;
    private Integer pageCount;


    /* currency is one of the following: "PLN", "EUR" or "USD" */
    public void convertCurrency(String curr) {
        if (!curr.equalsIgnoreCase(getCurrency())) {
            switch (curr) {
                case "PLN":
                    switch (getCurrency()){
                        case "EUR":
                            setCurrency("PLN");
                            setPrice(4.54*getPrice());
                            break;

                        case "USD":
                            setCurrency("PLN");
                            setPrice(4.18*getPrice());
                            break;
                    }
                    break;

                case "EUR":
                    switch (getCurrency()){
                        case "PLN":
                            setCurrency("EUR");
                            setPrice(0.22*getPrice());
                            break;

                        case "USD":
                            setCurrency("EUR");
                            setPrice(0.92*getPrice());
                            break;
                    }
                    break;

                case "USD":
                    switch (getCurrency()){
                        case "EUR":
                            setCurrency("USD");
                            setPrice(1.09*getPrice());
                            break;

                        case "PLN":
                            setCurrency("USD");
                            setPrice(0.24*getPrice());
                            break;
                    }
                    break;
            }
        }
    }

    public Book() {

    }

    public Book(String title, String author, String type, double price, String currency, int pageCount) {
        this.id = index;
        index += 1;
        this.title = title;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) &&
                author.equals(book.author) &&
                type.equals(book.type) &&
                price.equals(book.price) &&
                currency.equals(book.currency) &&
                pageCount.equals(book.pageCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, type, price, currency, pageCount);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
