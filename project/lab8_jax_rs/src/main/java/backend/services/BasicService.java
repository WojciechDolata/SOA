package backend.services;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BasicService {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyProvider");
    public EntityManager em;

    public BasicService() {
        em = entityManagerFactory.createEntityManager();
    }
}
