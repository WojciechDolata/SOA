package library.backend;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Singleton
@Startup
public class DatabaseManager implements DatabaseManagerInterface {

    private EntityManager entityManager;

    public DatabaseManager() {
        var entityManagerFactory = Persistence.createEntityManagerFactory("MyProvider");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addBook(Book book) {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("INSERT INTO book (title, author, type, price, currency, page_count) VALUES (?, ?,?,?,?,?)")
                .setParameter(1, book.getTitle())
                .setParameter(2, book.getAuthor())
                .setParameter(3, book.getType())
                .setParameter(4, book.getPrice())
                .setParameter(5, book.getCurrency())
                .setParameter(6, book.getPageCount())
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void addBooks(List<Book> list) {
        entityManager.getTransaction().begin();
        for (Book b : list) {
            addBook(b);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeBook(Integer bookId) {
        var book = entityManager.find(Book.class, bookId);
        entityManager.getTransaction().begin();
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateBook(Book newBook) {
        var book = entityManager.find(Book.class, newBook.getId());
        entityManager.getTransaction().begin();
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setPrice(newBook.getPrice());
        book.setType(newBook.getType());
        book.setCurrency(newBook.getCurrency());
        book.setPageCount(newBook.getPageCount());
        entityManager.getTransaction().commit();
    }

    @Override
    public Book getBookById(Integer bookId) {
        return entityManager
                .createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)
                .setParameter("id", bookId)
                .getSingleResult();
    }

    @Override
    public List<Book> getAllBooks() {
        return entityManager
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }
}
