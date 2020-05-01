package library.backend;

import library.backend.models.*;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.List;

@Remote
@Singleton
public interface LibraryManagerInterface {
    public List<Ksiazka> getBooks();
}
