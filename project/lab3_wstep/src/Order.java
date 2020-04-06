import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "Order")
@RequestScoped
public class Order {
    private List<Book> orderedBooks= new LinkedList<Book>();

    public Order() {

    }

    public void setOrderedBooks(List<Book> orderedBooks) {
        this.orderedBooks = orderedBooks;
    }

    public List<Book> getOrderedBooks() {
        return orderedBooks;
    }
}
