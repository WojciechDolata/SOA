package library.backend.services.interfaces;

import library.backend.models.Czytelnik;
import library.backend.models.Pozycja;
import library.backend.models.Wypozyczenie;
import library.backend.services.WypozyczeniaService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public interface WypozyczeniaServiceInterface {
    public List<Wypozyczenie> getAll();
    public Wypozyczenie getById(int id);
    public void insert(Wypozyczenie wypozyczenie);
    public void loan(Pozycja pozycja, Czytelnik czytelnik, Wypozyczenie wypozyczenie);

    }
