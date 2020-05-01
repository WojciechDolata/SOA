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
        return em.createQuery("SELECT a FROM Autor a JOIN a.ksiazki k JOIN k.pozycje p JOIN  p.wypozyczenia w JOIN w.czytelnik c group by a.autor_id order by count(w.wypozyczenie_id) DESC", Autor.class)
                .getResultList().get(0);
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
