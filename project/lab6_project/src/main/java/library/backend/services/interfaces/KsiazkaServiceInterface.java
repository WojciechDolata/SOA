package library.backend.services.interfaces;

import library.backend.models.Kategoria;
import library.backend.models.Ksiazka;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public interface KsiazkaServiceInterface {
    public List<Ksiazka> getAll();
    public Ksiazka getById(int id);
    public void insert(Ksiazka ksiazka);
    public Ksiazka getByTitle(String title);
    public Ksiazka getByPozycjaId(int id);
    }
