package library.backend.services;

import library.backend.models.*;
import library.backend.services.interfaces.KsiazkaServiceInterface;
import library.backend.services.interfaces.PozycjaServiceInterface;
import library.backend.services.interfaces.WypozyczeniaServiceInterface;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless
@Remote
public class PozycjaService extends BaseDatabaseService implements PozycjaServiceInterface {

    @EJB
    KsiazkaServiceInterface ksiazkaService;

    @EJB
    WypozyczeniaServiceInterface wypozyczeniaService;

    @Override
    public List<Pozycja> getAll() {
        return em.createQuery("SELECT a FROM Pozycja a", Pozycja.class)
                .getResultList();
    }

    @Override
    public Pozycja getById(int id) {
        try {
            return em.createQuery("SELECT a FROM Pozycja a WHERE a.pozycja_id = :id", Pozycja.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void insert(Pozycja pozycja) {
        pozycja.setKsiazka(ksiazkaService.getByTitle(pozycja.getKsiazka().getTytul()));
        super.insert(pozycja);
    }

    @Override
    public void returnPozycja(int id) {
        em.getTransaction().begin();
        int success = em.createQuery("update Pozycja p set p.status = 'ok' where p.pozycja_id=:id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public int loanPozycja(Ksiazka ksiazka, Czytelnik czytelnik, Wypozyczenie wypozyczenie) {
        Pozycja tmpPozycja;
        try {
            tmpPozycja = em.createQuery("select p from Pozycja p where p.ksiazka.tytul = :title and p.status = 'ok'", Pozycja.class)
                    .setParameter("title", ksiazka.getTytul())
                    .getResultList().get(0);
        } catch (NoResultException ex) {
            return -1;
        }

        wypozyczeniaService.loan(tmpPozycja, czytelnik, wypozyczenie);

        em.getTransaction().begin();
        int success = em.createQuery("update Pozycja p set p.status = 'ZAJETE' where p.pozycja_id=:id")
                    .setParameter("id",tmpPozycja.getPozycja_id())
                    .executeUpdate();
        em.getTransaction().commit();

        return success;
    }
}