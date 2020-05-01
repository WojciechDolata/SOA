package library.backend.services.interfaces;

import library.backend.models.Czytelnik;
import library.backend.models.Kategoria;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Date;
import java.util.List;

@Stateless
@Remote
public interface KategoriaServiceInterface {
    public List<Kategoria> getAll();
    public Kategoria getById(int id);
    public void insert(Kategoria kategoria);
    public Kategoria getByName(String typ);

    }
