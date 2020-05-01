package library.backend.services.interfaces;

import library.backend.models.Autor;
import library.backend.models.Czytelnik;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Date;
import java.util.List;

@Stateless
@Remote
public interface CzytelnikServiceInterface {
    public List<Czytelnik> getAll();
    public Czytelnik getById(int id);
    public void insert(Czytelnik czytelnik);
    public List<Czytelnik> getByAuthorAndDates(String authorName, Date from, Date to);
    public Czytelnik getByName(String imie, String nazwisko);


    }
