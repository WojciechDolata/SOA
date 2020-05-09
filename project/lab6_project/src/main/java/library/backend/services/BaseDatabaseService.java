package library.backend.services;

import library.backend.models.*;
import library.backend.services.interfaces.BaseDatabaseServiceInterface;
import library.backend.services.messaging.MessageSendingServiceInterface;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

@Stateless
@Remote
public class BaseDatabaseService implements BaseDatabaseServiceInterface {
    public EntityManager em;

    @EJB
    public MessageSendingServiceInterface messageSendingService;

    public void insert(BaseModel enitity) {
        em.getTransaction().begin();
        em.persist(enitity);
        em.getTransaction().commit();
    }

    private Class<?> getClassFromString(String type) {
        switch (type.toLowerCase().trim()) {
            case "autor":
                return Autor.class;
            case "czytelnik":
                return Czytelnik.class;
            case "kategoria":
                return Kategoria.class;
            case "ksiazka":
                return Ksiazka.class;
            case "pozycja":
                return Pozycja.class;
            case "wypozyczenie":
                return Wypozyczenie.class;
            default:
                return BaseModel.class;
        }
    }

    @Override
    public List<BaseModel> getByCustomQuery(String type, String query) {
        return (List<BaseModel>) em.createQuery(query, getClassFromString(type)).getResultList();
    }

    BaseDatabaseService() {
        em = Persistence.createEntityManagerFactory("MyProvider").createEntityManager();
    }
}
