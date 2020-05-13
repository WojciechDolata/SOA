package library.backend.services;

import library.backend.models.Autor;
import library.backend.models.Kategoria;
import library.backend.models.Ksiazka;
import library.backend.services.interfaces.AutorServiceInterface;
import library.backend.services.interfaces.KategoriaServiceInterface;
import library.backend.services.interfaces.KsiazkaServiceInterface;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
@Remote
public class KsiazkaService extends BaseDatabaseService implements KsiazkaServiceInterface {

    @EJB
    AutorServiceInterface autorService;

    @EJB
    KategoriaServiceInterface kategoriaService;

    @Override
    public List<Ksiazka> getAll() {
        messageSendingService.sendMessage("GET ALL BOOKS");

        return em.createQuery("SELECT a FROM Ksiazka a", Ksiazka.class)
                .getResultList();
    }

    @Override
    public Ksiazka getById(int id) {
        try {
            messageSendingService.sendMessage("GET BOOK BY ID: " + id);

            return em.createQuery("SELECT a FROM Ksiazka a WHERE a.ksiazka_id = :id", Ksiazka.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void insert(Ksiazka ksiazka) {
        messageSendingService.sendMessage("INSERTING BOOK: " + ksiazka);

        if(getByTitle(ksiazka.getTytul()) != null) return;

        Autor tmpAutor = autorService.getByName(ksiazka.getAutor().getImie(), ksiazka.getAutor().getNazwisko());
        if(tmpAutor == null) {
            autorService.insert(ksiazka.getAutor());
            tmpAutor = autorService.getByName(ksiazka.getAutor().getImie(), ksiazka.getAutor().getNazwisko());
        }

        Kategoria tmpKategoria = kategoriaService.getByName(ksiazka.getKategoria().getTyp());
        if(tmpKategoria == null) {
            kategoriaService.insert(ksiazka.getKategoria());
            tmpKategoria = kategoriaService.getByName(ksiazka.getKategoria().getTyp());
        }

        ksiazka.setKategoria(tmpKategoria);
        ksiazka.setAutor(tmpAutor);
        super.insert(ksiazka);
    }

    @Override
    public Ksiazka getByTitle(String title) {
        try {
            messageSendingService.sendMessage("GET BOOK BY TITLE: " + title);

            return em.createQuery("SELECT a FROM Ksiazka a WHERE a.tytul = '" + title + "'", Ksiazka.class).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }


    @Override
    public Ksiazka getByPozycjaId(int id) {
        try {
            return em.createQuery("select k from Ksiazka k join k.pozycje p where p.pozycja_id = :id", Ksiazka.class)
                    .setParameter("id", id)
                    .getResultList().get(0);
        } catch (NoResultException ex) {
            return null;
        }
    }
}
