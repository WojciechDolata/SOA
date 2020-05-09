package library.backend.services;

import library.backend.models.Autor;
import library.backend.models.Kategoria;
import library.backend.services.interfaces.KategoriaServiceInterface;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
@Remote
public class KategoriaService extends BaseDatabaseService implements KategoriaServiceInterface {

    @Override
    public List<Kategoria> getAll() {
        messageSendingService.sendMessage("GET ALL CATEGORIES");
        return em.createQuery("SELECT a FROM Kategoria a", Kategoria.class)
                .getResultList();
    }

    @Override
    public Kategoria getById(int id) {
        try {
            messageSendingService.sendMessage("GET CATEGORIES BY ID: " + id);
            return em.createQuery("SELECT a FROM Kategoria a WHERE a.kategoria_id = :id", Kategoria.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void insert(Kategoria kategoria) {

        messageSendingService.sendMessage("INSERT KATEGORIA");
        super.insert(kategoria);
    }

    @Override
    public Kategoria getByName(String typ) {
        try {
            messageSendingService.sendMessage("GET KATEGORIA BY NAME: " + typ);
            return em.createQuery("SELECT a FROM Kategoria a WHERE a.typ = :typ", Kategoria.class)
                    .setParameter("typ", typ)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
