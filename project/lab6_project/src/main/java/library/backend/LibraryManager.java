package library.backend;

import library.backend.models.Ksiazka;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;


@Singleton
@Startup
public class LibraryManager implements LibraryManagerInterface {
    private EntityManager em;

    private LibraryManager() {
        em = Persistence.createEntityManagerFactory("MyProvider").createEntityManager();
    }

    @Override
    public List<Ksiazka> getBooks() {
        return null;
    }
}
