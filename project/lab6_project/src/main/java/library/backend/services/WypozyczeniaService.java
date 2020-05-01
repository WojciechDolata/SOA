package library.backend.services;

import library.backend.models.Autor;
import library.backend.models.Czytelnik;
import library.backend.models.Pozycja;
import library.backend.models.Wypozyczenie;
import library.backend.services.interfaces.CzytelnikServiceInterface;
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
public class WypozyczeniaService extends BaseDatabaseService implements WypozyczeniaServiceInterface {

    @EJB
    KsiazkaServiceInterface ksiazkaService;

    @EJB
    PozycjaServiceInterface pozycjaService;

    @EJB
    CzytelnikServiceInterface czytelnikService;

    @Override
    public List<Wypozyczenie> getAll() {
        return em.createQuery("SELECT a FROM Wypozyczenie a", Wypozyczenie.class)
                .getResultList();
    }

    @Override
    public void loan(Pozycja pozycja, Czytelnik czytelnik, Wypozyczenie wypozyczenie) {
        wypozyczenie.setPozycja(pozycja);
        czytelnikService.insert(czytelnik);
        Czytelnik tmpCzytelnik = czytelnikService.getByName(czytelnik.getImie(), czytelnik.getNazwisko());
        wypozyczenie.setCzytelnik(tmpCzytelnik);
        insert(wypozyczenie);
    }

    @Override
    public Wypozyczenie getById(int id) {
        try {
            return em.createQuery("SELECT a FROM Wypozyczenie a WHERE a.wypozyczenie_id = :id", Wypozyczenie.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void insert(Wypozyczenie wypozyczenie) {
        super.insert(wypozyczenie);
    }
}
