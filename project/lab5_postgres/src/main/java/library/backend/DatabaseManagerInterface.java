package library.backend;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.List;

@Remote
@Singleton
public interface DatabaseManagerInterface {
    public void addBook(Book book);

    public void addBooks(List<Book> book);

    public void removeBook(Integer bookId);

    public void updateBook(Book newBook);

    public Book getBookById(Integer bookId);

    public List<Book> getAllBooks();
}
