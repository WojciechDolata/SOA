package library.backend.services.interfaces;

import library.backend.models.Autor;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public interface AutorServiceInterface {
    public List<Autor> getAll();
    public Autor getById(int id);
    public Autor getMostPopular();
    public Autor getByName(String name, String surname);
    public void insert(Autor autor);
}
