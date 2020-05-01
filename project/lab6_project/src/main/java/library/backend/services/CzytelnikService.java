package library.backend.services;

import library.backend.models.Autor;
import library.backend.models.Czytelnik;
import library.backend.services.interfaces.CzytelnikServiceInterface;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.List;

@Stateless
@Remote
public class CzytelnikService extends BaseDatabaseService implements CzytelnikServiceInterface {

    @Override
    public List<Czytelnik> getAll() {
        return em.createQuery("SELECT a FROM Czytelnik a", Czytelnik.class)
                .getResultList();
    }

    @Override
    public Czytelnik getById(int id) {
        try {
            return em.createQuery("SELECT a FROM Czytelnik a WHERE a.czytelnik_id = :id", Czytelnik.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Czytelnik getByName(String imie, String nazwisko) {
        try {
            return em.createQuery("SELECT a FROM Czytelnik a WHERE a.imie = :name AND a.nazwisko = :surname", Czytelnik.class)
                    .setParameter("name", imie)
                    .setParameter("surname", nazwisko)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void insert(Czytelnik czytelnik) {
        if (getByName(czytelnik.getImie(), czytelnik.getNazwisko()) == null){
            super.insert(czytelnik);
        }
    }

    @Override
    public List<Czytelnik> getByAuthorAndDates(String authorName, Date from, Date to) {
        return em.createQuery(
                "SELECT c FROM czytelnicy c " +
                        "JOIN wypozyczenia w ON w.wypozyczenia_id = c.wypozyczenia_id " +
                        "JOIN pozycje p ON p.pozycja_id = w.pozycja_id " +
                        "JOIN ksiazki k ON k.ksiazka_id = p.ksiazka_id " +
                        "JOIN autorzy a ON a.autor_id = k.autor_id " +
                        "WHERE a.nazwisko LIKE :name AND w.wypozyczenie_od > :from AND w.wypozyczenie_do < :to", Czytelnik.class)
                .setParameter("name", authorName)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }
}
