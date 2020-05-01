package library.backend.services;

import library.backend.models.Autor;
import library.backend.services.interfaces.AutorServiceInterface;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
@Remote
public class AutorService extends BaseDatabaseService implements AutorServiceInterface {

    @Override
    public List<Autor> getAll() {
        return em.createQuery("SELECT a FROM Autor a", Autor.class)
                .getResultList();
    }

    @Override
    public Autor getById(int id) {
        try {
            return em.createQuery("SELECT a FROM Autor a WHERE a.autor_id = :id", Autor.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Autor getMostPopular() {
        return em.createQuery("SELECT a FROM Autor a " +
                "JOIN Ksiazka k ON k.autor_id = a.autor_id " +
                "JOIN Pozycja p ON p.ksiazka_id = k.ksiazka_id " +
                "JOIN Wypozyczenie w ON w.pozycja_id = c.pozycja_id " +
                "ORDER BY COUNT(w.wypozyczenia_id) DESC ", Autor.class)
                .getSingleResult();
    }

    @Override
    public Autor getByName(String name, String surname) {
        try {
            return em.createQuery("SELECT a FROM Autor a WHERE a.imie = '" + name + "' AND a.nazwisko = '" + surname + "'", Autor.class).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void insert(Autor autor) {
        if(getByName(autor.getImie(),autor.getNazwisko()) == null) {
            super.insert(autor);
        }
    }
}
