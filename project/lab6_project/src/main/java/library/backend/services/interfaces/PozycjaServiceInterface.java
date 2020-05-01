package library.backend.services.interfaces;

import library.backend.models.Czytelnik;
import library.backend.models.Ksiazka;
import library.backend.models.Pozycja;
import library.backend.models.Wypozyczenie;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public interface PozycjaServiceInterface {
    public List<Pozycja> getAll();
    public Pozycja getById(int id);
    public void insert(Pozycja pozycja);
    public int loanPozycja(Ksiazka ksiazka, Czytelnik czytelnik, Wypozyczenie wypozyczenie);
    public void returnPozycja(int id);

    }
