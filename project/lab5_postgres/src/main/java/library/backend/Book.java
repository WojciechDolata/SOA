package library.backend;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table( name = "book")
public class Book implements Serializable{
//    private static Integer index = 0;
    private Integer id;
    private String title;
    private String author;
    private String type;
    private Double price;
    private String currency;
    private Integer pageCount;

    public Book() {
        super();
    }

    public Book(Integer id, String title, String author, String type, Double price, String currency, Integer pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.pageCount = pageCount;
    }

    public Book(String title, String author, String type, Double price, String currency, Integer pageCount) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.pageCount = pageCount;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "currency", nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "page_count", nullable = false)
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


}
